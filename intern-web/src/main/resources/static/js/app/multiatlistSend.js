/*
* send.js만의 스코프 지정
* */
var customerList = new Array();
var send = {
    init: function () {
        var _this = this;
        $(function () {
            var date = new Date();
            var hour = date.getHours();
            var min = date.getMinutes();
            hour = (hour < 10 ? "0" : "") + hour;
            min = (min < 10 ? "0" : "") + min;
            document.getElementById("time").defaultValue = hour + ":" + min;
        });
        $('#btn-phoneNumber').on('click', function () {
            _this.loadPhoneNums();
        });
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
        $('#btn-applyVars').on('click', function () {
            _this.applyVars();
        });
        $('#upload').on('click',function(){
            _this.uploadFile();
        })
        $(function () {
            var date = new Date();

            var day = date.getDate(),
                month = date.getMonth() + 1,
                year = date.getFullYear();

            month = (month < 10 ? "0" : "") + month;
            day = (day < 10 ? "0" : "") + day;

            var today = year + "" + month + "" + day;

            $.fn.datepicker.defaults.format = "yyyymmdd";

            $('#datePicker').datepicker({
                format: "yyyymmdd",	//데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
                startDate: '0d',	//달력에서 선택 할 수 있는 가장 빠른 날짜. 이전으로는 선택 불가능 ( d : 일 m : 달 y : 년 w : 주)
                endDate: '+30d',	//달력에서 선택 할 수 있는 가장 느린 날짜. 이후로 선택 불가 ( d : 일 m : 달 y : 년 w : 주)
                autoclose : true,	//사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
                calendarWeeks : false, //캘린더 옆에 몇 주차인지 보여주는 옵션 기본값 false 보여주려면 true
                clearBtn : false, //날짜 선택한 값 초기화 해주는 버튼 보여주는 옵션 기본값 false 보여주려면 true
                // datesDisabled : ['2019-06-24','2019-06-26'],//선택 불가능한 일 설정 하는 배열 위에 있는 format 과 형식이 같아야함.
                daysOfWeekDisabled : [0,6],	//선택 불가능한 요일 설정 0 : 일요일 ~ 6 : 토요일
                // daysOfWeekHighlighted : [3], //강조 되어야 하는 요일 설정
                disableTouchKeyboard : false,	//모바일에서 플러그인 작동 여부 기본값 false 가 작동 true가 작동 안함.
                immediateUpdates: false,	//사용자가 보는 화면으로 바로바로 날짜를 변경할지 여부 기본값 :false
                multidate : false, //여러 날짜 선택할 수 있게 하는 옵션 기본값 :false
                multidateSeparator :",", //여러 날짜를 선택했을 때 사이에 나타나는 글짜 2019-05-01,2019-06-01
                templates : {
                    leftArrow: '&laquo;',
                    rightArrow: '&raquo;'
                }, //다음달 이전달로 넘어가는 화살표 모양 커스텀 마이징
                showWeekDays : true ,// 위에 요일 보여주는 옵션 기본값 : true
                title: "테스트",	//캘린더 상단에 보여주는 타이틀
                todayHighlight : true ,	//오늘 날짜에 하이라이팅 기능 기본값 :false
                toggleActive : true,	//이미 선택된 날짜 선택하면 기본값 : false인경우 그대로 유지 true인 경우 날짜 삭제
                weekStart : 0 ,//달력 시작 요일 선택하는 것 기본값은 0인 일요일
            });
            $('#datePicker').datepicker('update',today);
        });

    },
    loadPhoneNums: function () {
        // phone list setting
       if (confirm("고객 정보 수정 화면으로 이동합니다. 작성한 정보는 사라집니다.")){
           window.location.href = '/customer';
       }
       else{
       }
    },
    applyVars : function (){
        var msg = $('#msg').val();
        var var1 = "#{변수1}";
        var var2 = "#{변수2}";
        var var3 = "#{변수3}";

        if($("input:checkbox[name='var1']").prop("checked") == true)
            var1="변수1";
        if($("input:checkbox[name='var2']").prop("checked") == true)
            var2="변수2";
        if($("input:checkbox[name='var3']").prop("checked") == true)
            var3="변수3";

        msg = msg.replace(/#{변수1}/gi, var1);
        msg = msg.replace(/#{변수1}/gi, var1);
        msg = msg.replace(/#{변수2}/gi, var2);
        msg = msg.replace(/#{변수3}/gi, var3);

        // var exMsg = document.getElementById('exMsg');
        $('#exMsg').text(msg);
        if (msg.includes("#{변수")){
            alert("매칭되지 않은 변수가 있습니다.\n변수를 잘 선택했는지 확인해 주세요.")
        }
    },
    save: function () {
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        var csrfToken = $("meta[name='_csrf']").attr("content");

        var varCheckList = [];

        if($("input:checkbox[name='var1']").prop("checked") == true)
            varCheckList.push(1);
        if($("input:checkbox[name='var2']").prop("checked") == true)
            varCheckList.push(2);
        if($("input:checkbox[name='var3']").prop("checked") == true)
            varCheckList.push(3);

        var templateCode = $('#templateCode').val();
        var msg = $('#msg').val();

        if(templateCode==null) {
            alert("템플릿 코드를 선택해 주세요.");
            return;
        }
        if(msg ===""){
            alert("메시지 내용을 입력해 주세요.");
            return;
        }
        if ($('#dataTable tbody tr').length==0){
            alert("고객주소록으로 이동하여 고객 정보를 먼저 추가해 주세요.");
            return;
        }

        var data = {
            msg: msg,
            templateCode : templateCode,
            reservedDate: $('#datePicker').val()+$('#time').val().toString().replace(/:/gi,"")+"00",
            varCheckList : varCheckList
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/multi-at-msgs/list',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            beforeSend: function (xhr) {
                //데이터를 전송하기 전에 헤더에 csrf값을 설정한다
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
        }).done(function () {
            // TODO : 예약후 시간되면 post로 바껴서 여기서 서버 예외가 발생함은 알 수 없음.
                alert('발송 예약이 완료되었습니다.');
                window.location.href = '/send/at-send';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        var data = {
            msg: $('#msg').val(),
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/at-msgs/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete: function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/at-msgs/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

send.init();