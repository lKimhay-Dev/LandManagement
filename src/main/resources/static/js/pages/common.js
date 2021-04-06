/* Ajax GET Request*/
function getRequest(url, callback) {
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

/* Ajax JSON POST Request*/
function postRequest(url, data, callback) {
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

/* Ajax JSON PUT Request*/
function putRequest(url, data, callback) {
    $.ajax({
        type: "PUT",
        url: url,
        data: JSON.stringify(data && "" === data ? {} : data),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        complete: function (dataRes) {
            callback(dataRes);
        }
    });
}

/* Ajax JSON DELETE Request*/
function deleteRequest(url, callback) {
    $.ajax({
        type: "DELETE",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        complete: function (dataRes) {
            callback(dataRes);
        }
    });
}


/* Validate phone number */
function validatePhoneNumber(phone) {
    let phoneNumberFormat = /^\(?([0-9]{3})\)?[ . ]?([0-9]{3})[ . ]?([0-9]{3,4})$/;
    return phone.val().match(phoneNumberFormat);
}

/* Format phone number */
function phoneNumberFormat(phone) {
    phone.val(phone.val().replace(/\D*(\d{3})\D*(\d{3})\D*(\d{3})\D*/, '$1 $2 $3'));
}

/* Validate input fields */
function validateInputField(fieldElements) {
    for (let i = 0; i < fieldElements.length; i++) {
        let field = fieldElements[i];
        if (field.val().trim().toString() === "") {
            field.focus();
            toastAlertError("Invalid input " + field.attr("name"));
            return false;
        }
    }
    return true;
}


/* Initialize Toast SweetAlert2 */
const Toast = Swal.mixin({
    toast: true,
    position: 'top-end',
    showConfirmButton: false,
    timer: 2000
});

/* Error alert */
function toastAlertError(msg) {
    Toast.fire({
        icon: 'error',
        title: '&nbsp;' + msg + '!'
    });
}