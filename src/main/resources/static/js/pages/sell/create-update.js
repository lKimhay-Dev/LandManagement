$(document).ready(function () {

    let index = 0;

    /*API url*/
    const apiUrl = "/api/v1/sells";
    const findSellerUrl = "/api/v1/sells";
    const updateUrl = "/api/v1/sells/update";

    const getSellerUrl = "/api/v1/sellers";
    const getCustomerUrl = "/api/v1/customers";
    const getLandUrl = "/api/v1/lands";

    //Initialize Toast SweetAlert2
    const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 10000
    });

    //Initialize Select2 Elements
    select2Data("#seller","-- Choose Seller --", getSellerUrl);
    select2Data("#customer ","-- Choose Customer --", getCustomerUrl);
    select2Data("#land","-- Choose Land --", getLandUrl);

    // Hide/Show block input installment
    $('input[name="optionsRadios"]').click(function () {
        $('#is-install').prop('checked') ?
            $('#block-installment').attr('hidden',false)
            :
            $('#block-installment').attr('hidden',true);
    });


    // Click Create Row
    $('[data-btn="moreLand"]').on("click", function () {
        index++;
        $( htmlBlockLand(index) ).insertBefore( "#blockLand" );
    });

    // Click Remove Row
    $('[data-btn="removeLand"]').on("click", function () {
        $('#row'+index).remove();
        if(index>0) index--;
    });

    // Click Save Land
    $('[data-btn="save"]').on("click", function () {
        for (let i=0; i <= index; i++){
            console.log($("input[name='discount"+i+"']").val());
        }
    });

    function openModal() {
        alert("Open Model")
    }

    function select2Data(element,placHolder, getDataUrl) {
        $(element).select2({
            placeholder: placHolder,
            allowClear: true,
            ajax: {
                url: getDataUrl,
                processResults: function (res) {
                    return {
                        results: $.map(res.data, function (response) {
                            return {
                                text: `${ response.name }`,
                                id: response.id
                            }
                        })
                    };
                }
            }
        });
    }

    // Init more row for input land info
    function htmlBlockLand(index) {
        return "<div id=\"row"+index+"\" class=\"row\">\n" +
            "                            <div class=\"col-md-4\">\n" +
            "                                <div class=\"form-group\">\n" +
            "                                    <label>Land</label>\n" +
            "                                    <select id=\"land"+index+"\" name=\"land"+index+"\" class=\"form-control select2\" style=\"width: 100%;\">\n" +
            "                                        <option></option>\n" +
            "                                    </select>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-md-4\">\n" +
            "                                <div class=\"form-group\">\n" +
            "                                    <label>Discount</label>\n" +
            "                                    <div class=\"input-group\">\n" +
            "                                        <input name=\"discount"+index+"\" type=\"number\" class=\"form-control\" placeholder=\"Enter Discount\">\n" +
            "                                        <div class=\"input-group-append\">\n" +
            "                                            <span class=\"input-group-text\"><i class=\"fas fa-tags\"></i></span>\n" +
            "                                        </div>\n" +
            "                                    </div>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-md-4\">\n" +
            "                                <div class=\"form-group\">\n" +
            "                                    <label>Final Price</label>\n" +
            "                                    <div class=\"input-group\">\n" +
            "                                        <input name=\"finalPrice"+index+"\" type=\"text\" class=\"form-control\" disabled>\n" +
            "                                        <div class=\"input-group-append\">\n" +
            "                                            <span class=\"input-group-text\"><i class=\"fas fa-dollar-sign\"></i></span>\n" +
            "                                        </div>\n" +
            "                                    </div>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </div>";
    }

});