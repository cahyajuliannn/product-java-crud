    var Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: 3000
    });
    //--***************************************START NOTIFIKASI SUKSES ******************************************//
    function NotifikasiSukses(title, text) {
      Toast.fire({
            title: title,
            text: text,
            icon: 'success'
      });
    }
    //--***************************************END NOTIFIKASI SUKSES ******************************************//
     //--***************************************START NOTIFIKASI GAGAL ******************************************//
    function NotifikasiError(title, text) {
      Toast.fire({
            title: title,
            text: text,
            icon: 'error'
      });
    }
     //--***************************************END NOTIFIKASI GAGAL ******************************************//

    //--***************************************START NOTIFIKASI GAGAL ******************************************//
    function NotifikasiAlert(title, text) {
      Toast.fire({
            title: title,
            text: text,
            icon: 'warning'
      });
    }
     //--***************************************END NOTIFIKASI GAGAL ******************************************//
