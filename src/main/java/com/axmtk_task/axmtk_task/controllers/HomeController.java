package com.axmtk_task.axmtk_task.controllers;

import com.axmtk_task.axmtk_task.models.Client;
import com.axmtk_task.axmtk_task.models.Contract;
import com.axmtk_task.axmtk_task.managers.DBManager;
import com.axmtk_task.axmtk_task.models.SolutionStatus;
import com.axmtk_task.axmtk_task.services.CreditSolutionService;
import com.axmtk_task.axmtk_task.services.CreditSolution;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//@RestController
//@RequestMapping("/")
@Controller
public class HomeController {

    static Logger LOGGER = Logger.getLogger(HomeController.class.getName());

    private List<Client> clientList = new LinkedList<Client>();
    private List<Contract> contractList = new LinkedList<Contract>();
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
                                      @RequestParam("credit_amount") String credit_amount){

        System.out.println("ЗАПОЛНЕНИЕ ФОРМЫ, ПАРАМЕТЫ:"
                                            + " " + client_name
                                            + " " + passport_data
                                            + " " + family_status
                                            + " " + address
                                            + " " + phone_number
                                            + " " + phone_number
                                            + " " + employment_information
                                            + " " + credit_amount
        );

        byte contract_data = 111;
        long long_credit_amount = Long.parseLong(credit_amount);
//        Byte contract_data = Byte.parseByte();

        CreditSolutionService creditSolutionService = new CreditSolutionService(new CreditSolution(long_credit_amount));
        SolutionStatus resultSolution = creditSolutionService.getSolutionStatus();

        Contract new_contract = new Contract(contract_data,
                                        long_credit_amount,
                                        resultSolution);

        Client new_client = new Client(client_name,
                                passport_data,
                                family_status,
                                address,
                                phone_number,
                                employment_information,
                                new_contract);

        /*
            Logic solution
            if true add new client/contract to lists and return approved
            else not add and return denied
        */

        this.clientList.add(new_client);
        this.contractList.add(new_contract);
//
//        DBManager userManager = new DBManager();
//        userManager.init();
//        userManager.addContract(contract);
//        userManager.addUser(client);
//
//        return resultSolution.toString();
        System.out.println("ПАРАМЕТРЫ NEW_CLIENT:"
                + " " + new_client.getClient_id()
                + " " + new_client.getClient_name()
                + " " + new_client.getPassport_data());

        System.out.println("ПАРАМЕТРЫ NEW_CONTRACT:"
                + new_contract.getContract_id());

        System.out.println("ВОЗВРАЩАЮ ССЫЛКУ НА НОВУЮ HTML");
        return new RedirectView("approved");
    }

//    http://localhost:8080/test_get_file
    @GetMapping("/test_get_file")
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

        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("Test Text");
        run.addBreak();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        document.write(out);
        out.close();
        document.close();
        byte[] fileContent = out.toByteArray();

        System.out.println("ТЕСТОВАЯ ОТПРАВКА ФАЙЛА КЛИЕНТУ");
        return fileContent;
    }

//    @RequestMapping(value = "/testLoadFile", method = RequestMethod.GET)
    @GetMapping("/testLoadFile")
    @ResponseBody
    public RedirectView testLoadFile(@PathVariable("file_name") MultipartFile fileName){

        System.out.println("ТЕСТОВАЯ ЗАГРУЗКА ФАЙЛА ОТ КЛИЕНТА");
        return new RedirectView("application");

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
}