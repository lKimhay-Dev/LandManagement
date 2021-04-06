$(document).ready(function () {

    const sellerApiUrl = "/api/v1/sellers";

    const oTable = $('#data-table').DataTable({
        responsive: true,
        "ajax": {
            "url": sellerApiUrl,
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
            {"data": "name"},
            {"data": "address"},
            {"data": null},
            {"data": null},
            {"data": "status"},
            {"data": null, "width": "5%"}
        ],
        "createdRow": function (row, data) {
            const $cell = $(row).find("td");
            const id = data.id;
            const datetime = new Date(data.createdDate).toLocaleDateString('en-GB');
            const link = "<div class='ellipsis'><a class='link' href='/seller/update?sellerId=" + id + "' data-btn='link' title='" + id + "'>" + id + "</a></div>";
            const btnDelete = "<button type='button' class='btn btn-danger' data-btn='delete' data-seq='" + id + "' title='Delete'>Delete</button>";

            $cell.eq(0).html(link);
            $cell.eq(3).html(data.phone1 ? data.phone1 : 'n/a');
            $cell.eq(4).html(data.phone2 ? data.phone2 : 'n/a');
            $cell.eq(5).html(datetime);
            $cell.eq(6).html(btnDelete);
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
                deleteRequest(sellerApiUrl + "/" + deleteUserId, data => {
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
                });
                $.ajax({
                    url: "api/v1/sellers/" + deleteUserId,
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
                    complete: function () {
                        // loadingStop();

                    }
                });
            }
        })
    });

});