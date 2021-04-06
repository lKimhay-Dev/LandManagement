$(document).ready(function () {

    const Toast = Swal.mixin({
       toast: true,
       position: 'top-end',
       showConfirmButton: false,
       timer: 10000
   });

    /*customer api url*/
    const apiUrl = "/api/v1/customers";
    const findCustomerUrl = "/api/v1/customers";
    const updateUrl = "/api/v1/customers/update";

    if (action === 'update') {
        getUpdateData();
    }

    /*get input element*/
    const $name = $("input[name='name']");
    const $address = $("input[name='address']");
    const $id_card = $("input[name='idCard']");
    const $id_card_no = $("input[name='idCardNo']");
    const $phone1 = $("input[name='phone1']");
    const $phone2 = $("input[name='phone2']");

    // Phone Format
    $phone1.keyup(function () {

        $phone1.val($phone1.val().replace(/\D*(\d{3})\D*(\d{3})\D*(\d{3})\D*/, '$1 $2 $3'));

    });
    $phone2.keyup(function () {


        $phone2.val($phone2.val().replace(/\D*(\d{3})\D*(\d{3})\D*(\d{3})\D*/, '$1 $2 $3'));

    });

    function validateInputField(fieldElements) {
        for (let i = 0; i < fieldElements.length; i++) {
            let field = fieldElements[i];
            if (field.val().trim().toString() === "") {
                tostAlert("Please input field.");
                field.focus();
                return false;
            }
        }
        return true;
    }

    function validateAndAssignData() {
        let isValidate = validateInputField(
            [
                $name,
                $address,
                $id_card,
                $id_card_no,
                $phone1,
                $phone2

            ]
        );

        /*get input value*/
        var name = $name.val();
        var dob = new Date();
        var address = $address.val();
        var id_card = $id_card.val();
        var id_card_no = $id_card_no.val();
        var id_card_issue_date = new Date();
        var nationality = $('#nationality').select2('val');
        var phone1 = $phone1.val();
        var phone2 = $phone2.val();
        var relationship = $('#relationship').select2('val');
        var gender = $('#gender').select2('val');

//        if (!isValidate) return;

        if(!isPhoneNumber(phone1)){

            tostAlert("Invalid Phone Number");

        }else if(phone2 && !isPhoneNumber(phone2)){

            tostAlert("Invalid Phone 2 Number");

        }
        data = {
            "name": name,
            "dob": dob,
            "address": address,
            "idCard": id_card,
            "idCardNo": id_card_no,
            "idCardIssueDate": id_card_issue_date,
            "nationality": nationality,
            "phone1": phone1,
            "phone2": phone2,
            "relationship": relationship,
            "gender": gender,
        };
    }

    $('.select2').select2({
        placeholder: "-- Select --",
        allowClear: true,
    });


   $('#create-customer').click(function() {
        validateAndAssignData();
        saveCustomer(apiUrl);
   });

   $('#update-customer').click(function () {
       validateAndAssignData();
       data['id'] = customerId;
       saveCustomer(updateUrl);
   });

    function saveCustomer(url) {
        console.log(data);
        jsonPostRequest(url, data, function (dataRes) {
        console.log(dataRes);
            if (dataRes.status === 200 && dataRes.responseJSON.status === "SUCCESS") {
                Swal.fire({
                    icon: 'success',
                    title: 'Customer has been saved.',
                    showConfirmButton: false,
                    timer: 1500
                    }).then(()=> $(location).attr('href', "/customer-list"))
            } else {
                let errorAlert = dataRes.status === 200
                    ? dataRes.responseJSON.message
                    : "Internal Server Error! Please try again or contact to developer.";
                tostAlert(errorAlert);
                $(location).attr('href', "/customer-list");
            }
        })
    }

    function getUpdateData() {
        loadPage(findCustomerUrl +"/"+ customerId, function (dataRes){
            if (dataRes.status === 200 && dataRes.responseJSON.status === "SUCCESS") {
                let data = dataRes.responseJSON.data;
                $name.val(data.name);
                $dob.val(data.dob);
                $address.val(data.address);
                $id_card.val(data.id_card);
                $id_card_no.val(data.id_card_no);
                $id_card_issue_date.val(data.id_card_issue_date);
                $nationality.val(data.nationality);
                $phone1.val(data.phone1);
                $phone2.val(data.phone2);
                $("#relationship").val(data.relationship).trigger('change');
                $("#gender").val(data.gender).trigger('change');

            } else {
                let errorAlert = dataRes.status === 200
                    ? dataRes.responseJSON.message
                    : "Internal Server Error! Please try again or contact to developer.";
                tostAlert(errorAlert);
                $(location).attr('href', "/customer-list");
            }
        });
    }

    function jsonPostRequest(url, data, callback) {
        $.ajax({
            type: "POST",
            url: url,
            data: JSON.stringify(data && "" === data ? {} : data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            complete: function (dataRes) {
                callback(dataRes);
            }
        });
    }

    function tostAlert(msg) {
            Toast.fire({
                icon: 'error',
                title: '&nbsp;'+ msg +'!'
            });
        }
    function isPhoneNumber(phone) {
            let phoneNumberFormat = /^\(?([0-9]{3})\)?[ . ]?([0-9]{3})[ . ]?([0-9]{3,4})$/;
            if (phone.match(phoneNumberFormat)) {
                return true;
            } else {
                return false;
            }
    }

    function loadPage(url, callback) {
        $.ajax({
            type: "GET",
            url: url,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            complete: function (dataRes) {
                callback(dataRes);
            }
        });
    }

});