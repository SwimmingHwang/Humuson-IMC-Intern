/*
* send.js만의 스코프 지정
* */
var send = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function () {
        // var csrfHeader =  $("meta[name='_csrf_header']").attr("content");
        // var csrfToken = $("meta[name='_csrf']").attr("content");

        var data = {
            msg: $('#msg').val(),
            phoneNumber: $('#phoneNumber').val(),
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/ft-msgs',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            // beforeSend : function(xhr) {
            //     //데이터를 전송하기 전에 헤더에 csrf값을 설정한다
            //     xhr.setRequestHeader(csrfHeader, csrfToken);
            // },
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/send/ft-send';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            msg: $('#msg').val(),
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/ft-msgs/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/ft-msgs/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

send.init();