/*
* send.js만의 스코프 지정
* */
var send = {
    init : function () {
        var _this = this;
        if($('#iswrongPW').val()){
            alert("아이디 혹은 비밀번호가 틀렸습니다.")
        }
    }
}
send.init();