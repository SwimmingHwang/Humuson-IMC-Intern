var signup = {
    init : function () {
        var checkEmail = $('#checkEmail').val();
        if(checkEmail) {
            alert("존재하는 이메일 입니다.");
        }
    },
    check: function () {
        var username = $('#username').val();
        var email = $('#email').val();
        var password = $('#password').val();
        var phoneNumber = $('#phoneNumber').val();

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
            }
        }
        if(password == ""){
            alert("비밀번호를 입력해 주세요.");
            $('#password').focus();
            return false;
        }
        if(phoneNumber == "") {
            alert("핸드폰 번호를 입력해 주세요.");
            $('#phoneNumber').focus();
        } else {
            var regExp =/(01[016789])([1-9]{1}[0-9]{2,3})([0-9]{4})$/;
            if(!regExp.test(phoneNumber)){
                alert("알맞은 휴대폰 번호 형식이 아닙니다.");
                $('#phoneNumber').focus();
                return false;
            }
        }
        return true;
    }
}
signup.init();