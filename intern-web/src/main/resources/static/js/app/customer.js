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
        $.fn.serializeObject = function () {
            'use strict';
            var result = {};
            var extend = function (i, element) {
                var node = result[element.name];
                if ('undefined' !== typeof node && node !== null) {
                    if ($.isArray(node)) {
                        node.push(element.value);
                    } else {
                        result[element.name] = [node, element.value];
                    }
                } else {
                    result[element.name] = element.value;
                }
            };

            $.each(this.serializeArray(), extend);
            return result;
        };
    },
    save : function () {

        var data = $("form").serializeObject();

        $.ajax({
            type: 'POST',
            url: '/api/v1/customer',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('고객 정보가 추가되었습니다.');
            window.location.href = '/customer';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {

        var id = $('#id').val();
        var data = $("form").serializeObject();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/customer/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
            // beforeSend: function (xhr) {
            //     //데이터를 전송하기 전에 헤더에 csrf값을 설정한다
            //     xhr.setRequestHeader(csrfHeader, csrfToken);
            // },
        }).done(function() {
            alert('고객정보가 수정되었습니다.');
            window.location.href = '/customer';
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
            url: '/api/v1/customer/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            // beforeSend: function (xhr) {
            //     //데이터를 전송하기 전에 헤더에 csrf값을 설정한다
            //     xhr.setRequestHeader(csrfHeader, csrfToken);
            // },
        }).done(function() {
            alert('고객 정보가 삭제되었습니다.');
            window.location.href = '/customer';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

send.init();