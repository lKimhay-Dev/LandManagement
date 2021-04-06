$(document).ready(function () {

    const Toast = Swal.mixin({
       toast: true,
       position: 'top-end',
       showConfirmButton: false,
       timer: 10000
   });

    /*user api url*/
    const apiUrl = "/api/v1/projects";
    const findUserUrl = "/api/v1/projects";

    /*get input element*/
    const $name = $("input[name='name']");
    const $location= $("input[name='location']");
    const $size = $("input[name='size']");
    const $address = $("input[name='address']");
    const $price = $("input[name='price']");

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
                $location,
                $size,
                $address,
                $price
            ]
        );

        /*get input value*/
        var name = $name.val();
        var location = $location.val();
        var size = $size.val();
        var address = $address.val();
        var price = $price.val();
        if (!isValidate) return;
        data = {
            "name": name,
            "location": location,
            "size": +size,
            "address": address,
            "price": +price
        };
    }

   $( "#saveProject" ).click(function() {
        validateAndAssignData();
        saveProject(apiUrl);
   });

    function saveProject(url) {
        console.log(data);
        jsonPostRequest(url, data, function (dataRes) {
            if (dataRes.status === 200 && dataRes.responseJSON.status === "SUCCESS") {
                Swal.fire({
                    icon: 'success',
                    title: 'Project has been saved.',
                    showConfirmButton: false,
                    timer: 1500
                    }).then(()=> $(location).attr('href', "/project-list"))
            } else {
                let errorAlert = dataRes.status === 200
                    ? dataRes.responseJSON.message
                    : "Internal Server Error! Please try again or contact to developer.";
                tostAlert(errorAlert);
                $(location).attr('href', "/project-list");
            }
        })
    }

    function getUpdateData() {
        loadPage(findUserUrl +"/"+ projectId, function (dataRes){
            if (dataRes.status === 200 && dataRes.responseJSON.status === "SUCCESS") {
                let data = dataRes.responseJSON.data;
                $name.val(data.name);
                $location.val(data.location);
                $size.val(data.size);
                $address.val(data.address);
                $price.val(data.price);

            } else {
                let errorAlert = dataRes.status === 200
                    ? dataRes.responseJSON.message
                    : "Internal Server Error! Please try again or contact to developer.";
                tostAlert(errorAlert);
                $(location).attr('href', "/project-list");
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