function getAppl(){

    var client_name = document.getElementById('client_name').value;
    var client_url = "client_name=";
    var text_client_name = client_url.concat(client_name); // client_name.innerText

    var passport_data = document.getElementById('passport_data').value;
    var passport_data_url = "&passport_data=";
    var text_passport_data = passport_data_url.concat(passport_data);

    var family_status = document.getElementById('family_status').value;
    var family_status_url = "&family_status=";
    var text_family_status = family_status_url.concat(family_status);

    var address = document.getElementById('address').value;
    var address_url = "&address=";
    var text_address = address_url.concat(address);

    var phone_number = document.getElementById('phone_number').value;
    var phone_number_url = "&phone_number=";
    var text_phone_number = phone_number_url.concat(phone_number);

    var employment_information = document.getElementById('employment_information').value;
    var employment_information_url = "&employment_information=";
    var text_employment_information = employment_information_url.concat(employment_information);

    var credit_amount = document.getElementById('credit_amount').value;
    var credit_amount_url = "&credit_amount=";
    var text_credit_amount = credit_amount_url.concat(credit_amount);
//
//    const the_Url = "http://localhost:8080/application?";
//    var res_Url = the_Url.concat(text_client_name, text_passport_data, text_family_status, text_address, text_phone_number, text_employment_information, text_credit_amount)


//    var client_name = document.getElementById('client_name').value;
//    var client_url = "client_name=";
//    var text_client_name = client_url.concat(client_name.textContent); // client_name.innerText

//    var test_str = "http://localhost:8080/credit?client_name=test&passport_data=12345&family_status=ststusF&address=someAddress&phone_number=888888888&employment_information=someEmplInfo&credit_amount=9999";
    var main_Url = "http://localhost:8080/credit?";

//    var test1_str = text_client_name.concat(test_str);
    var res_Url = main_Url.concat(text_client_name, text_passport_data, text_family_status, text_address, text_phone_number, text_employment_information, text_credit_amount);

    window.location.href = res_Url;
//    return false;
//    return res_Url

//    var xmlHttp = new XMLHttpRequest();
//        xmlHttp.onreadystatechange = function() {
//            if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
//                callback(xmlHttp.responseText);
//        }
//        xmlHttp.open("PUT", res_Url);
//        xmlHttp.responseType = "document";
//        xmlHttp.send();
//
//        xmlHttp.onload = function() {
//          let responseObj = xhr.response;
//          alert(responseObj.message);
//        };

//    var xmlHttp = new XMLHttpRequest();
//    xmlHttp.open( "PUT", res_Url, false ); // false for synchronous request
//    xmlHttp.send( null );
////    document.write(xmlHttp.responseText)
//    return xmlHttp.responseText;

//    return xmlHttp.responseText;

//    window.location.href = "res_Url";
}

//function httpGet(res_Url){
//        var xmlHttp = new XMLHttpRequest();
//        xmlHttp.open( "GET", res_Url, false ); // false for synchronous request
//        xmlHttp.send( null );
//        return xmlHttp.responseText;
//    }

//document.getElementById('gotores').onclick = window.location.href = 'https://ya.ru';