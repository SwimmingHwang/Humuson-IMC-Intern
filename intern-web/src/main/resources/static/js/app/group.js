/*
* send.js만의 스코프 지정
* */
var checkArr = [];     // 배열 초기화
var uncheckArr = [];     // 배열 초기화

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
        $('#btn-customerChkOK').on('click', function(){
            _this.saveTempCustomers();
        });
        $('#btn-customerChkCancel').on('click', function(){
            checkArr = [];
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
        // var csrfHeader = $("meta[name='_csf_header']").attr("content");
        // var csrfToken = $("meta[name='_csrf']").attr("content");

        var groupName = $('#groupName').val();

        send.saveTempCustomers();

        console.log(checkArr);

        var data = {
            groupName : groupName,
            customers : $("form").serialize()
        };

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
    saveTempCustomers : function (){
        checkArr = [];     // 배열 초기화

        $("input[name='customers']:checked").each(function(i) {
            checkArr.push($(this).val());     // 체크된 것만 값을 뽑아서 배열에 push
        });

        $('#customersModal').modal('hide');

    },
    // saveTempNotCustomers : function(){
    //     uncheckArr = [];     // 배열 초기화
    //
    //     $("input[name='customers']:checked",false).each(function(i) {
    //         uncheckArr.push($(this).val());     // 체크된 것만 값을 뽑아서 배열에 push
    //     });
    //     console.log(uncheckArr);
    // },
    update : function () {
        // var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        // var csrfToken = $("meta[name='_csrf']").attr("content");

        var groupName = $('#groupName').val();

        var data = {
            groupName : groupName,
            notCustomerIdStrList : uncheckArr
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