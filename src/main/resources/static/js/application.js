function getAppl(){

    var theUrl = "http://localhost:8080/application?client_name&passport_data&family_status&address&phone_number&employment_information&credit_amount"

    var xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function() {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                callback(xmlHttp.responseText);
        }
        xmlHttp.open("PUT", theUrl, true); // true for asynchronous
        xmlHttp.send(null);

    return xmlHttp.responseText;
}