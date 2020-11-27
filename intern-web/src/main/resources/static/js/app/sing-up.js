var singup = {
    init : function () {
        var _this = this;
        $('#sbt-singup-save').on('click', function () {
            _this.check();
        });
        // if($('#checkEmail').val()) {
        //     alert("존재하는 이메일 입니다.");
        // }
    },
    check: function () {
        var username = $('#username').val();
        var email = $('#email').val();
        var password = $('#password').val();
        var phoneNumber = $('#phoneNumber').val();
        var checkEmail = $('#checkEmail').val();

        if(username == "") {
            alert("성함을 입력해 주세요.");
            $('#username').focus();
            return false;
        }
        if(email == "") {
            alert("이메일을 입력해 주세요.");
            $('#email').focus();
            return false;
        } else {
            var regExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z\-]+/;
            if(!regExp.test(email)) {
                alert("알맞은 이메일 형식이 아닙니다.");
                $('#email').focus();
                return false;
            } else {
                if(checkEmail) {
                    alert("존재하는 이메일 입니다.");
                    return false;
                }
            }
        }
        if(password == ""){
            alert("비밀번호를 입력해 주세요.");
            $('#password').focus();
            return false;
        }
        if(phoneNumber == ""){
            alert("핸드폰 번호를 입력해 주세요.");
            $('#phoneNumber').focus();
            return false;
        }

        return true;
    }

}
singup.init();