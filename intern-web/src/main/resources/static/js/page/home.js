/*
* send.js만의 스코프 지정
* */
var home = {
    init : function () {
        this.setToken();
    },
    setToken : function () {
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        var csrfToken = $("meta[name='_csrf']").attr("content");

        var data = {
            csrfHeader: csrfHeader,
            csrfToken: csrfToken
        };
        $.ajax({
            type: 'POST',
            url: '/api/vi/tokenPost',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            beforeSend: function (xhr) {
                //데이터를 전송하기 전에 헤더에 csrf값을 설정한다
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

home.init();