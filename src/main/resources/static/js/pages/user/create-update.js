$(document).ready(function () {

    const userApiUrl = "/api/v1/users";

    let data = {};

    if (action === 'update') {
        getUpdateData();
    }

    /* Get input element */
    const $username = $("input[name='username']");
    const $pwd = $("input[name='pwd']");
    const $confPwd = $("input[name='confPwd']");
    const $seller = $("select.select2").children("option:selected");


    function validateAndAssignData() {

        console.log($seller.val());

        let isValidate = validateInputField(
            [
                $username,
                $pwd,
                $confPwd
            ]
        );

        if (!isValidate) {
            return false;
        }

        /* Get input value */
        const username = $username.val();
        const pwd = $pwd.val();
        const confPwd = $confPwd.val();
        const seller = $seller.val();

        if (pwd !== confPwd) {
            toastAlertError("Password not match");
            $confPwd.focus();
            return false;
        }

        if (!seller || seller === '0') {
            toastAlertError("Please select a seller");
            $seller.focus();
            return false;
        }

        data = {
            "username": username,
            "password": pwd,
            "role": "SELLER",
            "seller": {
                "id": +seller
            },
            "status": true
        };
        return data;
    }

    $('[data-btn="save"]').on("click", function () {
        if (!validateAndAssignData()) return false;
        createUser();
    });

    $('[data-btn="update"]').on("click", function () {
        if (!validateAndAssignData()) return false;
        data["id"] = userId;
        updateUser();
    });

    function createUser() {
        postRequest(userApiUrl, data, function (dataRes) {
            if (dataRes.status === 200 && dataRes.responseJSON.statusCode === 200) {
                Swal.fire({
                    icon: 'success',
                    title: 'User created.',
                    showConfirmButton: false,
                    timer: 1500
                }).then(() => $(location).attr('href', "/user-list"))
            } else {
                let errorAlert = dataRes.responseJSON.message;
                toastAlertError(errorAlert);
            }
        })
    }

    function updateUser() {
        putRequest(userApiUrl, data, function (dataRes) {
            if (dataRes.status === 200 && dataRes.responseJSON.statusCode === 200) {
                Swal.fire({
                    icon: 'success',
                    title: 'User updated.',
                    showConfirmButton: false,
                    timer: 1500
                }).then(() => $(location).attr('href', "/user-list"))
            } else {
                let errorAlert = dataRes.responseJSON.message;
                toastAlertError(errorAlert);
            }
        })
    }

    function getUpdateData() {
        getRequest(findUserUrl + "/" + userId, function (dataRes) {
            if (dataRes.status === 200 && dataRes.responseJSON.statusCode === 200) {
                let data = dataRes.responseJSON.data;
                $name.val(data.username);
                $pwd.val(data.password).prop('disabled', true);
                $confPwd.val(data.password).prop('disabled', true);
                $("#seller").val(data.seller.name + ' / ' + data.seller.phone1).trigger('change');
            } else {
                let errorAlert = dataRes.responseJSON.message;
                toastAlertError(errorAlert);
            }
        });
    }
});