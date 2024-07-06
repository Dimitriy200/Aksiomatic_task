package com.axmtk_task.axmtk_task.controllers;

import com.axmtk_task.axmtk_task.models.Client;
import com.axmtk_task.axmtk_task.models.Contract;
import com.axmtk_task.axmtk_task.services.DB_Manager;
import org.springframework.web.bind.annotation.*;

@RestController("/")
public class HomeController {

    /*
    Home page
    */
    @GetMapping("/application")
    public String login(){

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
    http://localhost:8080/application?client_name&passport_data&family_status&address&phone_number&employment_information&credit_amount
    */
    @PutMapping("/application")
    public String change_client(@RequestParam("client_name") String client_name,
                                @RequestParam("passport_data") String passport_data,
                                @RequestParam("family_status") String family_status,
                                @RequestParam("address") String address,
                                @RequestParam("phone_number") String phone_number,
                                @RequestParam("employment_information") String employment_information,
                                @RequestParam("credit_amount") String credit_amount){

        Byte test_byte = 111;
        Contract contract = new Contract(test_byte,
                                    credit_amount);

        Client client = new Client(client_name,
                                passport_data,
                                family_status,
                                address,
                                phone_number,
                                employment_information,
                                contract);

        DB_Manager userManager = new DB_Manager();
        userManager.init();
        userManager.addContract(contract);
        userManager.addUser(client);

        return "application_completed";
    }
}