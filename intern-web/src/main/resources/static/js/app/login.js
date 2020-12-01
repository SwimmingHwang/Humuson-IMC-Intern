var login = {
    init : function () {
        if($('#iswrongPW').val()){
            alert("아이디 혹은 비밀번호가 틀렸습니다.")
        }
    },
    check : function() {
        var email = $('#email').val();
        var password = $('#password').val();

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
        return true;
    }
}
login.init();