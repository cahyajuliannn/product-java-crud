    $(document).ready(function () {
      TableTets1.buat();
    });

    //Load Data
    var TableTets1 = {
        buat: function () {
            // jika table tersebut datatable, maka clear and dostroy
            if ($.fn.DataTable.isDataTable('#TableTets1')) {
                // table yg sudah dibentuk menjadi datatable harus d rebuild lagi
                // untuk di instantiasi ulang
                $('#TableTets1').DataTable().clear();
                $('#TableTets1').DataTable().destroy();
            }
            $.ajax({
                url: '/api/CategoryProduct',
                method: 'get',
                contentType: 'application/json',
                success: function (res, status, xhr) {
                debugger;
                console.log(res);
                    if (xhr.status == 200 || xhr.status == 201) {
                        $('#TableTets1').DataTable({
                            data: res,
                            columns: [
                                {title: "Name", data: "categoryName"},
                                {title: "Description", data: "deskripsi"},
                                {title: "Action", data: null, render: function (data, type, row)
                                    {
                                        return "<button type='button' class='btn btn-primary' data-toggle='modal' data-target='#exampleModalCenter'onclick=GetById('" + data.productCategoryId + "')>'Edit'</button>" +
                                                "<button class='btn btn-danger' onclick=DeleteCategory('" + data.productCategoryId + "')>Delete</button>"

                                    }
                                }
                            ]
                        });
                    } else {
                    }
                },
                erorrr: function (err) {
                    NotifikasiError("Error", err);
                }
            });
        }
    };

    //Modal Save Kategori Barang
    $('#btn-modal-save').click(function () {
      $('#modal-save').modal('show')
    });

    //Save Category
    function SaveCategory(){
      if($('#form-katbar').parsley().validate()){
        var datas = {
          categoryName: $('#namakatbar').val(),
          deskripsi: $('#deskripsi').val()
        };
        $.ajax({
          url: '/api/CategoryProduct/save',
          method: 'post',
          contentType: 'application/json',
          dataType: 'json',
          data: JSON.stringify(datas),
          success: function (res, status, xhr) {
                  if (xhr.status == 200 || xhr.status == 201) {
                    NotifikasiSukses("Success", "Data saved successfully");
                    $('#modal-save').modal('hide');
                    TableTets1.buat();
                    } else {
                    NotifikasiAlert("Warning", xhr.responseText);
                    }
                    ClearForm();
                  },
          error: function(xhr, status, error) {
            var err = eval("(" + xhr.responseText + ")");
              NotifikasiAlert("Error", xhr.responseText);
              ClearForm();
          }
        });
      }
    }

    //Get By Id Category
    function GetById(id){
      ClearForm();
      $.ajax({
        url: '/api/CategoryProduct/' + id,
	      method: 'get',
	      contentType: 'application/json',
	      dataType: 'json',
	      success: function (res, status, xhr) {
	            	console.log(res);
	                if (xhr.status == 200 || xhr.status == 201) {
	                    $('#form-katbar-edit').fromJSON(JSON.stringify(res));
	                    $('#form-katbar-edit').parsley().reset();
	                    $('#productCategoryId-edit').val(res.productCategoryId);
	                    $('#namakatbar-edit').val(res.categoryName);
	                    $('#deskripsi-edit').val(res.deskripsi);
	                    $('#modal-edit').modal('show');
	                } else {

	                }
	            },
	      erorrr: function (err) {
	        console.log(err);
	      }
	    });
    }

    //Submit Edit Category
    function EditCategory(){
      if($('#form-katbar-edit').parsley().validate()){
          var idKat = $('#productCategoryId-edit').val();
	    		var dataKategori = {
          categoryName: $('#namakatbar-edit').val(),
          deskripsi: $('#deskripsi-edit').val()
	    		}
	      $.ajax({
	            url: '/api/CategoryProduct/' + idKat,
	            method: 'put',
	            contentType: 'application/json',
	            dataType: 'json',
	            data: JSON.stringify(dataKategori),
	            success: function (res, status, xhr) {
                  if (xhr.status == 200 || xhr.status == 201) {
                    NotifikasiSukses("Success", "Data edited successfully");
                    $('#modal-edit').modal('hide');
                    TableTets1.buat();
                    } else {
                    NotifikasiAlert("Warning", xhr.responseText);
                    }
                    ClearForm();
                  },
	            error: function (err) {
	                NotifikasiError("Error", err);
                  ClearForm();
	            }
	        });
	    }
    }

    //Delete Category
    function DeleteCategory(id){
	    if (confirm("Are you sure?")) {
	        $.ajax({
	          url: '/api/CategoryProduct/delete/' + id,
	          method: 'delete',
	          success: function (res, status, xhr) {
	          debugger;
              NotifikasiSukses("Success", "Data deleted successfully");
	        	  TableTets1.buat();
	          },
	          erorrr: function (xhr, status, err) {
	          debugger;
	            NotifikasiError("Error", xhr.responseText);
	            console.log(status);
	          }
	        })
	    }
    }

    //Clear Form
    function ClearForm(){
      $('#form-katbar')[0].reset();
      $('#form-katbar').parsley().reset();
    }

