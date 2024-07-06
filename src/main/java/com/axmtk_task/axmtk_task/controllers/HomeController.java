package com.axmtk_task.axmtk_task.controllers;

import com.axmtk_task.axmtk_task.models.Client;
import com.axmtk_task.axmtk_task.models.Contract;
import com.axmtk_task.axmtk_task.services.UserManager;
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
    @PutMapping("/application/" +
            "{client_name}&" +
            "{passport_data}&" +
            "{family_status}&" +
            "{address}&" +
            "{phone_number}&" +
            "{employment_information}" +
            "{credit_amount}")
    public String change_client(@PathVariable String client_name,
                                          String passport_data,
                                          String family_status,
                                          String address,
                                          String phone_number,
                                          String employment_information,
                                          String credit_amount){

        Byte test_byte = 12;
        Contract contract = new Contract(test_byte);
        Client client = new Client(client_name,
                                passport_data,
                                family_status,
                                address,
                                phone_number,
                                employment_information,
                                credit_amount,
                                contract);

        UserManager userManager = new UserManager();
        userManager.init();
        userManager.addUser(client);


        return "application_completed";
    }
}