$(document).ready(function () {

    (function () {
        var baseUrl = "/api/v1/customers";

        var oTable = $('#data-table').DataTable({
//            order: [[ 0, "desc" ]],
            responsive: true,
            "ajax" : {
                "url": baseUrl,
                "type": "GET",
                "dataSrc": function (d) {
                    console.log(d);
                    return d.data;
                }
            },

            "columnDefs": [
                {"className": "dt-center", "targets": "_all"}
            ],
            "columns": [
                        {"data": null},
                        {"data":"name"},
                        {"data": null},
                        {"data":"address"},
                        {"data":"idCard"},
                        {"data":"idCardNo"},
                        {"data": null},
                        {"data":"gender"},
                        {"data":"nationality"},
                        {"data": null},
                        {"data": null, "width": "5%"}
            ],

            "createdRow": function (row, data) {
                var $cell = $(row).find("td");
                var id = data.id;
                var birth = new Date(data.dob).toLocaleDateString('en-GB');
                var card_date = new Date(data.idCardIssueDate).toLocaleDateString('en-GB');
                var link = "<div class='ellipsis'><a class='link' href='/customer/update?customerId=" + id + "' data-btn='link' title='" + id + "'>" + id + "</a></div>";
                var btnDelete = "<button type='button' class='btn btn-danger' data-btn='delete' data-seq='" + id + "' title='Delete'>Delete</button>";

                $cell.eq(0).html(link);
                $cell.eq(2).html(birth);
                $cell.eq(6).html(card_date);
                $cell.eq(9).html(data.phone1 + " / " +data.phone2);
                $cell.eq(10).html(btnDelete);
            }
        });

        // reload datatable
        function reLoadTable() {
            oTable.ajax.reload();
        }

        // // delete message popup
        var deleteCustomerId = "";
        $(document).on("click", "[data-btn='delete']", function () {
                    deleteCustomerId = $(this).attr("data-seq");
                    // deleteDialog.dialog("open");
                    Swal.fire({
                        title: 'Are you sure?',
                        text: "You won't be able to revert this!",
                        icon: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: 'Yes, delete it!'
                    }).then((rslasd) => {
                        if (rslasd.value) {
                            $.ajax({
                                url: "/api/v1/customers/delete/" + deleteCustomerId,
                                type: "DELETE",
                                contentType: "application/json",
                                dataType: "json",
                                success: function (data) {
                                    if (data.status === "SUCCESS") {
                                        Swal.fire({
                                            icon: 'success',
                                            title: 'Land has been deleted.',
                                            showConfirmButton: false,
                                            timer: 1500
                                        }).then(()=>reLoadTable())
                                    } else {
                                        Toast.fire({
                                            icon: 'error',
                                            title: '&nbsp;'+ data.message +'!'
                                        });
                                    }
                                },
                                beforeSend: function () {
                                    // loadingStart();
                                },
                                complete: function () {
                                    // loadingStop();
                                    $(this).prop("disabled", false);
                                }
                            });
                        }
                    })
                });
    }());
});