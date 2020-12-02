var send = {
    init : function () {
        var _this = this;
        /*
            @author https://github.com/macek/jquery-serialize-object
        */
        $.fn.serializeObject = function () {
            'use strict';
            var result = {customers:Array()};
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
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () {
            _this.update();
        });
        $('#btn-delete').on('click', function () {
            _this.delete();
        });
        $('#btn-customerChkOK').on('click', function(){
            $('#customersModal').modal('hide');
            $('#selectedCustomerCount').text($("form").serializeObject().customers.length);
        });
        $('#btn-customerChkCancel').on('click', function(){
            $('#customersModal').modal('hide');
        });
        $('input:checkbox[name="customerChkAll"]').change(function(){
            if (this.checked){
                $('input:checkbox[name="customers"]').each(function() {
                    $(this).prop("checked",true);
                });
            }else{
                $('input:checkbox[name="customers"]').each(function() {
                    $(this).prop("checked",false);
                });
            }
        });

    },
    save : function () {

        var data = $("form").serializeObject();

        $.ajax({
            type: 'POST',
            url: '/api/v1/customer/group',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
        }).done(function () {
            alert('그룹이 생성되었습니다.');
            window.location.href = '/customer/group';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var id = $('#id').val();
        var data = $("form").serializeObject();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/customer/group/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),

        }).done(function() {
            alert('그룹 정보가 수정되었습니다.');
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
            alert('그룹 정보가 삭제되었습니다.');
            window.location.href = '/customer/group';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

send.init();