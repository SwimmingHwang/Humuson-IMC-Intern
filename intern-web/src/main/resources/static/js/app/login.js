var login = {
    init: function () {
        var _this = this;
        $('#btn-user-save').on('click', function () {
            _this.save();
        });
    },
    save: function () {
        var data = {
            msg: $('#username').val(),
            email: $('#email').val(),
            password: $('#password').val(),
            phoneNumber: $('#phoneNumber').val()
        };

        $.ajax({
            type: 'POST',
            url: '/user/sign-up',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('회원가입 되셨습니다.');
            window.location.href = '/user/login';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

login.init();