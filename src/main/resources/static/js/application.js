function getAppl(){

//    "http://localhost:8080/application?client_name=TestUser&passport_data=12345&family_status=ststusF&address=someAddress&phone_number=888888888&employment_information=someEmplInfo&credit_amount=9999";

    var client_name = document.getElementById('*client_name');
    var client_url = "client_name=";
    var text_client_name = client_url.concat((client_name.innerText || client_name.textContent));

    var client_name = document.getElementById('*passport_data');
    var client_url = "passport_data=";
    var text_client_name = client_url.concat((client_name.innerText || client_name.textContent));

    var client_name = document.getElementById('*family_status');
    var client_url = "family_status=";
    var text_client_name = client_url.concat((client_name.innerText || client_name.textContent));

    var client_name = document.getElementById('*address');
    var client_url = "address=";
    var text_client_name = client_url.concat((client_name.innerText || client_name.textContent));

    var client_name = document.getElementById('*phone_number');
    var client_url = "phone_number=";
    var text_client_name = client_url.concat((client_name.innerText || client_name.textContent));

    var client_name = document.getElementById('*employment_information');
    var client_url = "employment_information=";
    var text_client_name = client_url.concat((client_name.innerText || client_name.textContent));

    var client_name = document.getElementById('*credit_amount');
    var client_url = "credit_amount=";
    var text_client_name = client_url.concat((client_name.innerText || client_name.textContent));

    const the_Url = "http://localhost:8080/application?";
    var res_Url = the_Url.concat()

    var xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function() {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                callback(xmlHttp.responseText);
        }
        xmlHttp.open("PUT", res_Url, true); // true for asynchronous
        xmlHttp.send(null);

    return xmlHttp.responseText;
}