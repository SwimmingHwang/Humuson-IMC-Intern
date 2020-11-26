/*
* send.js만의 스코프 지정
* */
var send = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        // $('#btn-update').on('click', function () {
        //     _this.update();
        // });
        //
        // $('#btn-delete').on('click', function () {
        //     _this.delete();
        // });
    },
    save : function () {
        // TODO : 입력된 데이터가 없다면 alert 보내기
        // var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        // var csrfToken = $("meta[name='_csrf']").attr("content");

        var phoneNum = $('#phoneNumber').val();
        phoneNum = phoneNum.substring(1);
        // TODO : 국가번호 한국으로만 함
        phoneNum = "82" + phoneNum;

        var data = {
            profileId: $('#profileId').val(),
            senderName: $('#senderName').val(),
            phoneNumber: phoneNum,
        };

        $.ajax({
            type: 'POST',
            url: '/profile/create',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            // beforeSend: function (xhr) {
            //     //데이터를 전송하기 전에 헤더에 csrf값을 설정한다
            //     xhr.setRequestHeader(csrfHeader, csrfToken);
            // },
        }).done(function () {
            alert('플러스친구 ID가 추가되었습니다.');
            window.location.href = '/profile/create';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

send.init();