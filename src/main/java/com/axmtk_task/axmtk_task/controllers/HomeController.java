package com.axmtk_task.axmtk_task.controllers;

import com.axmtk_task.axmtk_task.models.Client;
import com.axmtk_task.axmtk_task.models.Contract;
import com.axmtk_task.axmtk_task.managers.DBManager;
import com.axmtk_task.axmtk_task.models.ContractStatus;
import com.axmtk_task.axmtk_task.models.SolutionStatus;
import com.axmtk_task.axmtk_task.services.CreditSolutionService;
import com.axmtk_task.axmtk_task.services.CreditSolution;
import com.google.gson.Gson;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

//@RestController
//@RequestMapping("/")
@Controller
public class HomeController {

    static Logger LOGGER = Logger.getLogger(HomeController.class.getName());

    private List<Client> clientList = new LinkedList<Client>();
    private List<Contract> contractList = new LinkedList<Contract>();
//    private List<byte[]> contractDataList = new LinkedList<byte[]>();
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

//        byte contract_data = 111;

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
//        Byte contract_data = Byte.parseByte();

        /*
        Logic solution
        if true add new client/contract to lists/bd and return approved
        else not add and return denied
        */

        CreditSolutionService creditSolutionService = new CreditSolutionService(new CreditSolution(long_credit_amount));
        SolutionStatus resultSolution = creditSolutionService.getSolutionStatus();

        Contract new_contract = new Contract(contract_data,
                long_credit_amount,
                ContractStatus.notSubscribe.toString(),
                resultSolution.toString());

        Client new_client = new Client(client_name,
                passport_data,
                family_status,
                address,
                phone_number,
                employment_information,
                new_contract);

        this.dbManager = new DBManager();
        this.dbManager.init();

        if (resultSolution == SolutionStatus.approved){
            this.clientList.add(new_client);
            this.contractList.add(new_contract);

            System.out.println("ПАРАМЕТРЫ NEW_CLIENT:"
                    + " " + new_client.getClient_id()
                    + " " + new_client.getClient_name()
                    + " " + new_client.getPassport_data());

            System.out.println("ПАРАМЕТРЫ NEW_CONTRACT:"
                    + " " + new_contract.getContract_id()
                    + " " + new_contract.getContract_status()
                    + " " + new_contract.getCredit_amount());

            this.dbManager.addContract(new_contract);
            this.dbManager.addClient(new_client);

            System.out.println("ВОЗВРАЩАЮ ССЫЛКУ НА НОВУЮ HTML");
            return new RedirectView("approved");

        }else{
            System.out.println("ПАРАМЕТРЫ NEW_CLIENT:"
                    + " " + new_client.getClient_id()
                    + " " + new_client.getClient_name()
                    + " " + new_client.getPassport_data());

            System.out.println("ПАРАМЕТРЫ NEW_CONTRACT:"
                    + " " + new_contract.getContract_id()
                    + " " + new_contract.getContract_status()
                    + " " + new_contract.getCredit_amount());
            System.out.println("ВОЗВРАЩАЮ ССЫЛКУ НА НОВУЮ HTML denied");

            this.clientList.add(new_client);
            this.contractList.add(new_contract);

            this.dbManager.addContract(new_contract);
            this.dbManager.addClient(new_client);

            return new RedirectView("denied");
        }

//        return new RedirectView("approved");
    }




    /*
    http://localhost:8080/test_get_file
    */
    @GetMapping("/get_contract_doc")
    public @ResponseBody byte[] test_get_file(Model model) throws Exception {

//        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
//        MainDocumentPart mainDocumentPart = wordMLPackage.getMainDocumentPart();

//        mainDocumentPart.addStyledParagraphOfText("Title", "Hello World!");
//        mainDocumentPart.addParagraphOfText("Welcome To Baeldung");

//        File exportFile = new File("welcome.docx");
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        mainDocumentPart.marshal(baos);

//        byte[] fileContent = Files.readAllBytes(exportFile.toPath());

//        XWPFDocument doc = new XWPFDocument();
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        doc.write(out);
//        XWPFTable table = doc.createTable(3, 3);
//        table.getRow(1).getCell(1).setText("EXAMPLE OF TABLE");
//        table.getRow(2).getCell(2).setText("only text");

//        FileOutputStream out = new FileOutputStream("simpleTable.docx");
//        doc.write(out);
//        out.close();

//        XWPFDocument document = new XWPFDocument();
//        XWPFParagraph paragraph = document.createParagraph();
//        XWPFRun run = paragraph.createRun();
//        run.setText("Test Text");
//        run.addBreak();
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//        document.write(out);
//        out.close();
//        document.close();
//        byte[] fileContent = out.toByteArray();

        byte[] fileContent = this.contractList.getLast().getContract_data();
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

//        Contract contract = this.dbManager.getAllContract().getLast();
        Contract contract = this.dbManager.getContract(this.contractList.getLast());
        contract.setContract_status(ContractStatus.subscribe.toString());
        contract.setContract_data(newContractData);

//        this.dbManager.deleteContract(this.contractList.getLast());
        this.dbManager.updateContract(contract);

        this.contractList.clear();
        this.clientList.clear();

        return new RedirectView("/subscribe");
    }

    @GetMapping("/allUsers")
    public @ResponseBody List<Client> getAllUsers(){

        this.dbManager = new DBManager();
        this.dbManager.init();
        List<Client> allClients = this.dbManager.getAllClient();

        System.out.println("ПОЛУЧЕНИЕ СПИСКА ВСЕХ ПОЛЬЗОВАТЕЛЕЙ, ДЛИНА = " + allClients.size());
        return allClients;
    }

    @GetMapping("/getClientOnNamePhonPassport")
    @ResponseBody
    public List<Client> getClientOnNamePhonPassport(@RequestParam("client_name") String client_name,
                                      @RequestParam("passport_data") String passport_data,
                                      @RequestParam("phone_number") String phone_number) throws Exception{

        this.dbManager = new DBManager();
        this.dbManager.init();
        List<Client> allClients = this.dbManager.getClientOnNamePhonPassport(client_name,
                                                                            passport_data,
                                                                            phone_number);

        System.out.println("ПОЛУЧЕНИЕ СПИСКА ПОЛЬЗОВАТЕЛЕЙ ПО ИМЕНИ, НОМЕРУ И ПАСПОРТУ, ДЛИНА = " + allClients.size());
        return allClients;
    }

    @GetMapping("/getClientApproved")
    @ResponseBody
    public List<Client> getClientApproved(){

        this.dbManager = new DBManager();
        this.dbManager.init();
        List<Client> allClients = this.dbManager.getClientApproved();

        System.out.println("ПОЛУЧЕНИЕ СПИСКА ПОЛЬЗОВАТЕЛЕЙ ПО СТАТУСУ ОДОБРЕНИЯ, ДЛИНА = " + allClients.size());
        return allClients;
    }

    @GetMapping("/getContractSubscribe")
    @ResponseBody
    public List<Contract> getContractSubscribe(){

        this.dbManager = new DBManager();
        this.dbManager.init();
        List<Contract> contracts = this.dbManager.getContractSubscribe(ContractStatus.subscribe);

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