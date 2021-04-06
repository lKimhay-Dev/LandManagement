$(document).ready(function () {

    // $('#example').DataTable({
    //     paging: true,
    //     searching: true,
    //     pageLength: 10,
    //     lengthMenu: [[10, 25, -1], [10, 25, "All"]],
    // });
    (function () {
        var baseUrl = "/api/v1/users";

        var oTable = $('#data-table').DataTable({
            responsive: true,
            "ajax" : {
                "url": baseUrl,
                "type": "GET",
                "dataSrc": function (d) {
                    return d.data
                }
            },
            "columnDefs": [
                {"className": "dt-center", "targets": "_all"}
            ],
            "columns": [
                {"data": null},
                {"data": "username"},
                {"data": "role"},
                {"data": null, "width": "5%"}
            ],
            "createdRow": function (row, data) {
                var $cell = $(row).find("td");
                var id = data.id;
                var link = "<div class='ellipsis'><a class='link' href='/users/update?userId=" + id + "' data-btn='link' title='" + id + "'>" + id + "</a></div>";
                var btnDelete = "<button type='button' class='btn btn-danger' data-btn='delete' data-seq='" + id + "' title='Delete'>Delete</button>";

                $cell.eq(0).html(link);
                $cell.eq(3).html(btnDelete);
            }
        });

        // reload datatable
        function reLoadTable() {
            oTable.ajax.reload();
        }

        // open delete message popup
        var deleteUserId = "";
        $(document).on("click", "[data-btn='delete']", function () {
            deleteUserId = $(this).attr("data-seq");
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
                        url: "api/v1/users/" + deleteUserId,
                        type: "DELETE",
                        contentType: "application/json",
                        dataType: "json",
                        success: function (data) {
                            if (data.status === "SUCCESS") {
                                Swal.fire({
                                    icon: 'success',
                                    title: 'User has been deleted.',
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