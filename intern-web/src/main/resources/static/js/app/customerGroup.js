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
        $('#openCustomer').on('click', function (){
            _this.openCustomer();
        })
    },
    save : function () {
        // var csrfHeader = $("meta[name='_csf_header']").attr("content");
        // var csrfToken = $("meta[name='_csrf']").attr("content");

        var groupName = $('#groupName').val();

        var data = {
            groupName : groupName
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/customer/group',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            // beforeSend: function (xhr) {
            //     //데이터를 전송하기 전에 헤더에 csrf값을 설정한다
            //     xhr.setRequestHeader(csrfHeader, csrfToken);
            // },
        }).done(function () {
            alert('고객 정보가 추가되었습니다.');
            window.location.href = '/customer/group';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    openCustomer : function (){
      window.open()
    },
    update : function () {
        // var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        // var csrfToken = $("meta[name='_csrf']").attr("content");

        var groupName = $('#groupName').val();

        var data = {
            groupName : groupName
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/customer/group/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
            // beforeSend: function (xhr) {
            //     //데이터를 전송하기 전에 헤더에 csrf값을 설정한다
            //     xhr.setRequestHeader(csrfHeader, csrfToken);
            // },
        }).done(function() {
            alert('고객정보가 수정되었습니다.');
            window.location.href = '/customer/group';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        // var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        // var csrfToken = $("meta[name='_csrf']").attr("content");

        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/customer/group/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            // beforeSend: function (xhr) {
            //     //데이터를 전송하기 전에 헤더에 csrf값을 설정한다
            //     xhr.setRequestHeader(csrfHeader, csrfToken);
            // },
        }).done(function() {
            alert('고객 정보가 삭제되었습니다.');
            window.location.href = '/customer/group';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

send.init();