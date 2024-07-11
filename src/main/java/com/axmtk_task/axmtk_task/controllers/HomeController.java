package com.axmtk_task.axmtk_task.controllers;

import com.axmtk_task.axmtk_task.models.AppTable;
import com.axmtk_task.axmtk_task.managers.DBManager;
import com.axmtk_task.axmtk_task.models.ContractStatus;
import com.axmtk_task.axmtk_task.models.SolutionStatus;
import com.axmtk_task.axmtk_task.services.CreditSolutionService;
import com.axmtk_task.axmtk_task.services.CreditSolution;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

//@RestController
//@RequestMapping("/")
@Controller
public class HomeController {

    static Logger LOGGER = Logger.getLogger(HomeController.class.getName());

    private List<AppTable> appTableList = new LinkedList<AppTable>();
    private DBManager dbManager;

    /*
    Home page
    templates
    */
    @GetMapping("/application")
    public String application(Model model){
        System.out.println("ПОСЕЩЕНИЕ ГЛАВНОЙ СТРАНИЦЫ");
        return "application";
    }

    /*
    Получаем данные клиента и либо регистрируем как нового, либо добавляем новую заявку к существующему клиенту.

    Фамилия, имя, отчество
    Паспортные данные
    Семейное положение
    Адрес прописки
    Контактный телефон
    Сведения о занятости(период работы, должность, название организации)
    Сумма желаемого кредита.
    */

    /*
    http://localhost:8080/application?client_name=TestUser&passport_data=12345&family_status=ststusF&address=someAddress&phone_number=888888888&employment_information=someEmplInfo&credit_amount=9999
    */
    @GetMapping("/credit")
    @ResponseBody
    public RedirectView change_client(@RequestParam("client_name") String client_name,
                                      @RequestParam("passport_data") String passport_data,
                                      @RequestParam("family_status") String family_status,
                                      @RequestParam("address") String address,
                                      @RequestParam("phone_number") String phone_number,
                                      @RequestParam("employment_information") String employment_information,
                                      @RequestParam("credit_amount") String credit_amount) throws Exception{

        System.out.println("ЗАПОЛНЕНИЕ ФОРМЫ, ПАРАМЕТЫ:"
                                            + " " + client_name
                                            + " " + passport_data
                                            + " " + family_status
                                            + " " + address
                                            + " " + phone_number
                                            + " " + employment_information
                                            + " " + credit_amount
        );

        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();

        run.setText("client_name: " + client_name);
        run.addBreak();
        run.setText("passport_data: " + passport_data);
        run.addBreak();
        run.setText("family_status: " + family_status);
        run.addBreak();
        run.setText("address: " + address);
        run.addBreak();
        run.setText("phone_number: " + phone_number);
        run.addBreak();
        run.setText("employment_information: " + employment_information);
        run.addBreak();
        run.setText("credit_amount: " + credit_amount);
        run.addBreak();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        document.write(out);
        out.close();
        document.close();
        byte[] contract_data = out.toByteArray();

        long long_credit_amount = Long.parseLong(credit_amount);

        /*
        Logic solution
        if true add new client/contract to lists/bd and return approved
        else not add and return denied
        */

        CreditSolutionService creditSolutionService = new CreditSolutionService(new CreditSolution(long_credit_amount));
        SolutionStatus resultSolution = creditSolutionService.getSolutionStatus();

        AppTable newAppTable = new AppTable(client_name,
                                                passport_data,
                                                family_status,
                                                address,
                                                phone_number,
                                                employment_information,
                                                ContractStatus.notSubscribe.toString(),
                                                resultSolution.toString(),
                                                contract_data,
                                                long_credit_amount);

        this.dbManager = new DBManager();
        this.dbManager.init();

        if (resultSolution == SolutionStatus.approved){
            this.appTableList.add(newAppTable);

            System.out.println("ПАРАМЕТРЫ NEW_CLIENT:"
                    + " " + newAppTable.getAppId()
                    + " " + newAppTable.getUserName()
                    + " " + newAppTable.getPassport_data());

            this.dbManager.addAppTable(newAppTable);

            System.out.println("ВОЗВРАЩАЮ ССЫЛКУ НА НОВУЮ HTML");
            return new RedirectView("approved");

        }else{
            System.out.println("ПАРАМЕТРЫ NEW_CLIENT:"
                    + " " + newAppTable.getAppId()
                    + " " + newAppTable.getUserName()
                    + " " + newAppTable.getPassport_data());

            System.out.println("ВОЗВРАЩАЮ ССЫЛКУ НА НОВУЮ HTML denied");

            this.appTableList.add(newAppTable);

            this.dbManager.addAppTable(newAppTable);

            return new RedirectView("denied");
        }

//        return new RedirectView("approved");
    }




    /*
    http://localhost:8080/test_get_file
    */
    @GetMapping("/get_contract_doc")
    public @ResponseBody byte[] test_get_file(Model model) throws Exception {

        byte[] fileContent = this.appTableList.getLast().getContract_data();
        System.out.println("ОТПРАВКА ФАЙЛА КЛИЕНТУ");
        return fileContent;
    }




//    @RequestMapping(value = "/testLoadFile", method = RequestMethod.GET)
    @PostMapping("/loadDoc")
    @ResponseBody
    public RedirectView loadDoc(@RequestPart(name = "myfile", required = false) MultipartFile fileName) throws Exception{

        System.out.println("ЗАГРУЗКА ФАЙЛА ОТ КЛИЕНТА");

        /*
        Получить нужный объект контакта
        записать новые данные
        изменить статус
        вставить вместо старого
        */
        this.dbManager = new DBManager();
        this.dbManager.init();

        byte [] newContractData = fileName.getBytes();

        AppTable oldAppTable = this.dbManager.getAppTable(this.appTableList.getLast());
        oldAppTable.setContract_status(ContractStatus.subscribe.toString());
        oldAppTable.setContract_data(newContractData);

        this.dbManager.updateAppTable(oldAppTable);
        this.appTableList.clear();

        return new RedirectView("/subscribe");
    }

    @GetMapping("/allUsers")
    public @ResponseBody List<AppTable> getAllUsers(){

        this.dbManager = new DBManager();
        this.dbManager.init();
        List<AppTable> allClients = this.dbManager.getAllAppTable();

        System.out.println("ПОЛУЧЕНИЕ СПИСКА ВСЕХ ПОЛЬЗОВАТЕЛЕЙ, ДЛИНА = " + allClients.size());
        return allClients;
    }

    @GetMapping("/getClientOnNamePhonPassport")
    @ResponseBody
    public List<AppTable> getClientOnNamePhonPassport(@RequestParam("client_name") String client_name,
                                      @RequestParam("passport_data") String passport_data,
                                      @RequestParam("phone_number") String phone_number) throws Exception{

        this.dbManager = new DBManager();
        this.dbManager.init();
        List<AppTable> allClients = this.dbManager.getAppNamePhonePassport(client_name,
                                                                            passport_data,
                                                                            phone_number);

        System.out.println("ПОЛУЧЕНИЕ СПИСКА ПОЛЬЗОВАТЕЛЕЙ ПО ИМЕНИ, НОМЕРУ И ПАСПОРТУ, ДЛИНА = " + allClients.size());
        return allClients;
    }

    @GetMapping("/getClientApproved")
    @ResponseBody
    public List<AppTable> getClientApproved(){

        this.dbManager = new DBManager();
        this.dbManager.init();
        List<AppTable> allClients = this.dbManager.getAppTableApproved();

        System.out.println("ПОЛУЧЕНИЕ СПИСКА ПОЛЬЗОВАТЕЛЕЙ ПО СТАТУСУ ОДОБРЕНИЯ, ДЛИНА = " + allClients.size());
        return allClients;
    }

    @GetMapping("/getContractSubscribe")
    @ResponseBody
    public List<AppTable> getContractSubscribe(){

        this.dbManager = new DBManager();
        this.dbManager.init();
        List<AppTable> contracts = this.dbManager.getAppTableSubscribe(ContractStatus.subscribe);

        System.out.println("ПОЛУЧЕНИЕ СПИСКА ПОЛЬЗОВАТЕЛЕЙ ПО СТАТУСУ ОДОБРЕНИЯ, ДЛИНА = " + contracts.size());
        return contracts;
    }

    @GetMapping("/approved")
    public String approved(Model model){
        System.out.println("ПОСЕЩЕНИЕ СТРАНИЦЫ approved");
        return "approved";
    }

    @GetMapping("/denied")
    public String denied(Model model){
        System.out.println("ПОСЕЩЕНИЕ СТРАНИЦЫ denied");
        return "denied";
    }

    @GetMapping("/subscribe")
    public String subscribe(Model model){
        System.out.println("ПОСЕЩЕНИЕ СТРАНИЦЫ subscribe");
        return "subscribe";
    }
}