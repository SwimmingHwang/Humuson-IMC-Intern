/*
* send.js만의 스코프 지정
* */

var login = {
    init: function () {
        var _this = this;
        $('#sbt-login-save').on('click', function () {
            _this.check();
        });
    },
    check: function () {
        var email = $('#email').val();
        var password = $('#password').val();

        if(email == "") {
            alert("이메일을 입력해 주세요.");
            $('#email').focus();
            return false;
        } else {
            var regExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z\-]+/;
            if(!regExp.test(email)) {
                alert("이메일 형식이 일치하지 않습니다.");
                $('#email').focus();
                return false;
            }
        }
        if(password == ""){
            alert("비밀번호를 입력해 주세요.");
            $('#password').focus();
            return false;
        }
        return true;
    }
};

login.init();