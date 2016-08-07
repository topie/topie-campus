;
(function ($, window, document, undefined) {
    $("#login_btn").on("click", function () {
        var fields = JSON.stringify({
                "username": $("#username").val(), "password": $("#password").val()
            });
        $.ajax({
           type: 'POST',
           url: "../api/auth",
           contentType: "application/json",
           dataType: "json",
           data: fields,
           success: function (result) {
               if (result.code == 200) {
                   $.cookie('tc_t', result.token, {expires: 7, path: '/'});
                   window.location.href = "./index.html";
               } else {
                   alert(result.message);
               }
           },
           error: function (err) {

           }
        });
    });
})(jQuery, window, document);