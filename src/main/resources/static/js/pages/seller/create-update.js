$(document).ready(function () {

    const sellerApiUrl = "/api/v1/sellers";
    let data = {};


    if (action === 'update') {
        getUpdateData();
    }

    /* get input element */
    const $name = $("input[name='name']");
    const $address = $("input[name='address']");
    const $phone1 = $("input[name='phone1']");
    const $phone2 = $("input[name='phone2']");

    // Phone Number Format
    $phone1.keyup(function () {
        phoneNumberFormat($phone1);
    });
    $phone2.keyup(function () {
        phoneNumberFormat($phone2);
    });

    function validateAndAssignData() {
        let isValidate = validateInputField(
            [
                $name,
                $address,
                $phone1,
            ]
        );
        if (!isValidate) {
            return false;
        }

        if (!validatePhoneNumber($phone1)) {
            $phone1.focus();
            toastAlertError("Invalid input phone1");
            return false;
        }

        if ($phone2.val() && !validatePhoneNumber($phone2)) {
            $phone2.focus();
            toastAlertError("Invalid input phone2");
            return false;
        }

        /* Get input value */
        const name = $name.val();
        const address = $address.val();
        const phone1 = $phone1.val();
        const phone2 = $phone2.val();

        data = {
            "name": name,
            "address": address,
            "phone1": phone1,
            "phone2": phone2
        };
        return data;
    }

    $('[data-btn="save"]').on("click", function () {
        if (!validateAndAssignData()) return;
        createSeller();
    });

    $('[data-btn="update"]').on("click", function () {
        if (!validateAndAssignData()) return;
        updateSeller();
    });

    function createSeller() {
        postRequest(sellerApiUrl, data, dataRes => {
            if (dataRes.status === 200) {
                Swal.fire({
                    icon: 'success',
                    title: 'Seller created.',
                    showConfirmButton: false,
                    timer: 1500
                }).then(() => $(location).attr('href', "/seller-list"))
            } else {
                toastAlertError(dataRes.responseJSON.message);
            }
        })
    }

    function updateSeller() {
        data.id = sellerId;
        putRequest(sellerApiUrl, data, dataRes => {
            if (dataRes.status === 200) {
                Swal.fire({
                    icon: 'success',
                    title: 'Seller updated.',
                    showConfirmButton: false,
                    timer: 1500
                }).then(() => $(location).attr('href', "/seller-list"))
            } else {
                toastAlertError(dataRes.responseJSON.message);
            }
        })
    }

    function getUpdateData() {
        getRequest(sellerApiUrl + "/" + sellerId, function (dataRes) {
            if (dataRes.status === 200 && dataRes.responseJSON.status === "SUCCESS") {
                let data = dataRes.responseJSON.data;
                $name.val(data.name);
                $address.val(data.address);
                $phone1.val(data.phone1);
                $phone2.val(data.phone2);
                $("#seller")
                    .append(new Option(data.seller.sellername, data.seller.id, true, true))
                    .trigger('change.select2');
            } else {
                let errorAlert = dataRes.status === 200
                    ? dataRes.responseJSON.message
                    : "Internal Server Error! Please try again or contact to developer.";
                toastAlertError(errorAlert);
                $(location).attr('href', "/seller-list");
            }
        });
    }
});