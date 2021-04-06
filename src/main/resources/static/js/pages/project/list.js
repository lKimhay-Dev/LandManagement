$(document).ready(function () {

    (function () {
        var baseUrl = "/api/v1/projects";

        var oTable = $('#data-table').DataTable({
            responsive: true,
            "ajax" : {
                "url": baseUrl,
                "type": "GET",
                "dataSrc": function (d) {
                    return d
                }
            },
            "columnDefs": [
                {"className": "dt-center", "targets": "_all"}
            ],
            "columns": [
                        {"data":"id"},
                        {"data":"name"},
                        {"data":"location"},
                        {"data":"size"},
                        {"data":"address"},
                        {"data":"price"},
                        {"data": null, "width": "5%"}
            ],
            "createdRow": function (row, data) {
                var $cell = $(row).find("td");
                var id = data.id;
                var link = "<div class='ellipsis'><a class='link' href='/projects/update?projectId=" + id + "' data-btn='link' title='" + id + "'>" + id + "</a></div>";
                var btnDelete = "<button type='button' class='btn btn-danger' data-btn='delete' data-seq='" + id + "' title='Delete'>Delete</button>";

                $cell.eq(0).html(link);
                // $cell.eq(1).html(data.role + " " + data.lastname);
                $cell.eq(6).html(btnDelete);
            }
        });

        // reload datatable
        function reLoadTable() {
            oTable.ajax.reload();
        }

        // // delete message popup
        var deleteProjectId = "";
        $(document).on("click", "[data-btn='delete']", function () {
                    deleteProjectId = $(this).attr("data-seq");
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
                                url: "api/v1/projects/delete/" + deleteProjectId,
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