package com.axmtk_task.axmtk_task.controllers;

import com.axmtk_task.axmtk_task.models.Client;
import com.axmtk_task.axmtk_task.models.Contract;
import com.axmtk_task.axmtk_task.managers.DBManager;
import com.axmtk_task.axmtk_task.models.SolutionStatus;
import com.axmtk_task.axmtk_task.services.CreditSolutionService;
import com.axmtk_task.axmtk_task.services.CreditSolution;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.logging.Level;
import java.util.logging.Logger;

//@RestController
//@RequestMapping("/")
@Controller
public class HomeController {

    static Logger LOGGER = Logger.getLogger(HomeController.class.getName());;
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
//
        Contract contract = new Contract(contract_data,
                                        long_credit_amount,
                                        resultSolution);

        Client client = new Client(client_name,
                                passport_data,
                                family_status,
                                address,
                                phone_number,
                                employment_information,
                                contract);
//
//        DBManager userManager = new DBManager();
//        userManager.init();
//        userManager.addContract(contract);
//        userManager.addUser(client);
//
//        return resultSolution.toString();

        System.out.println("ВОЗВРАЩАЮ ССЫЛКУ НА НОВУЮ HTML");
        return new RedirectView("denied");
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