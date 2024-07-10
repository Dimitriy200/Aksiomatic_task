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

    var http = require('http');
    var url = require('url');
    var fs = require('fs');

    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "PUT", res_Url, false ); // false for synchronous request
    xmlHttp.send( null );
//    document.write(xmlHttp.responseText)
    return xmlHttp.responseText;

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


//    var test_str = "http://localhost:8080/approved";

function LoadFile() {
    var oFrame = document.getElementById("frmFile");
    var strRawContents = oFrame.contentWindow.document.body.childNodes[0].innerHTML;
    while (strRawContents.indexOf("\r") >= 0)
        strRawContents = strRawContents.replace("\r", "");
    var arrLines = strRawContents.split("\n");
    alert("File " + oFrame.src + " has " + arrLines.length + " lines");
    for (var i = 0; i < arrLines.length; i++) {
        var curLine = arrLines[i];
        alert("Line #" + (i + 1) + " is: '" + curLine + "'");
    }
}


function getAllClients(){
    urlGetAllClients = "http://localhost:8080/allUsers";

    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", urlGetAllClients, false ); // false for synchronous request
    xmlHttp.send( null );

    listUsers = JSON.parse(xmlHttp.responseText);
//    for(user : listUsers){
//        document.write(user.client_name)
//    }

    for(var i = 0; i < listUsers.length; i++){
        for (var key in listUsers[i]) {
        document.write(key + " = ")
           for (var j= 0; j<listUsers[i][key].length; j++) {
               document.write(listUsers[i][key][j] + "   ");
           }
        }
           document.write("<br>");
    }


//    arrListUsers = Array.from(listUsers)
//    document.getElementById("getAllClientsOut").value = listUsers;

//    window.location.href = urlGetAllClients;

}


function getClientsOnNamePonePassport(){
    var client_name = document.getElementById('ClientsOnName').value;
    var client_url = "client_name=";
    var get_client_name = client_url.concat(client_name); // client_name.innerText

    var passport_data = document.getElementById('ClientsOnPassport').value;
    var passport_data_url = "&passport_data=";
    var get_passport_data = passport_data_url.concat(passport_data);

    var phone_number = document.getElementById('ClientsOnPone').value;
    var phone_number_url = "&phone_number=";
    var get_phone_number = phone_number_url.concat(phone_number);

    var main_Url = "http://localhost:8080/getClientOnNamePhonPassport?";

    var res_Url = main_Url.concat(get_client_name, get_passport_data, get_phone_number);

//    window.location.href = res_Url;

    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", res_Url, false ); // false for synchronous request
    xmlHttp.send( null );

    listUsers = JSON.parse(xmlHttp.responseText);

    for(var i = 0; i < listUsers.length; i++){
        for (var key in listUsers[i]) {
        document.write(key + " = ")
           for (var j= 0; j<listUsers[i][key].length; j++) {
               document.write(listUsers[i][key][j] + "   ");
           }
        }
           document.write("<br>");
    }
}


function GetClientApproved(){
    var res_Url = "http://localhost:8080/getClientApproved?";

    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", res_Url, false ); // false for synchronous request
    xmlHttp.send( null );

    listUsers = JSON.parse(xmlHttp.responseText);

    for(var i = 0; i < listUsers.length; i++){
        for (var key in listUsers[i]) {
        document.write(key + " = ")
           for (var j= 0; j<listUsers[i][key].length; j++) {
               document.write(listUsers[i][key][j] + "   ");
           }
        }
           document.write("<br>");
    }

}


function getContractSubscribe(){
    var res_Url = "http://localhost:8080/getContractSubscribe?";

        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "GET", res_Url, false ); // false for synchronous request
        xmlHttp.send( null );

        listUsers = JSON.parse(xmlHttp.responseText);

        for(var i = 0; i < listUsers.length; i++){
            for (var key in listUsers[i]) {
            if (key != 'contract_data'){
                document.write(key + " = ")

                for (var j= 0; j < listUsers[i][key].length; j++) {
                    document.write(listUsers[i][key][j]);
                }
                document.write(" | ")
            }
        }
    document.write("<br>");
    }

}