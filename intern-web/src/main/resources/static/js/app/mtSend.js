/*
* send.js만의 스코프 지정
* */
var send = {
    init : function () {
        var _this = this;
        $(function () {
            var date = new Date();
            var hour = date.getHours();
            var min = date.getMinutes();
            hour = (hour < 10 ? "0" : "") + hour;
            min = (min < 10 ? "0" : "") + min;
            document.getElementById("time").defaultValue = hour + ":" + min;
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
        $(function () {
            _this.initDatePicker();
            $('#datepicker').datepicker('destroy');
            $('#reservedDate').val($('#datepicker').val()+$('#time').val().toString().replace(/:/gi,"")+"00",)

        });
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
    initDate: function() {
        var date = new Date();

        var day = date.getDate(),
            month = date.getMonth() + 1,
            year = date.getFullYear();

        $("#datepicker").val(year + "년 " + month + "월 " + day + "일");

    },
    initDatePicker: function() {
        var date = new Date();

        var day = date.getDate(),
            month = date.getMonth() + 1,
            year = date.getFullYear();

        month = (month < 10 ? "0" : "") + month;
        day = (day < 10 ? "0" : "") + day;

        var today = year + "" + month + "" + day;

        $.fn.datepicker.defaults.format = "yyyymmdd";

        $('#datepicker').datepicker({
            format: "yyyy년 mm월 dd일",	//데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
            startDate: '0d',	//달력에서 선택 할 수 있는 가장 빠른 날짜. 이전으로는 선택 불가능 ( d : 일 m : 달 y : 년 w : 주)
            endDate: '+30d',	//달력에서 선택 할 수 있는 가장 느린 날짜. 이후로 선택 불가 ( d : 일 m : 달 y : 년 w : 주)
            autoclose : true,	//사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
            calendarWeeks : false, //캘린더 옆에 몇 주차인지 보여주는 옵션 기본값 false 보여주려면 true
            clearBtn : false, //날짜 선택한 값 초기화 해주는 버튼 보여주는 옵션 기본값 false 보여주려면 true
            // datesDisabled : ['2019-06-24','2019-06-26'],//선택 불가능한 일 설정 하는 배열 위에 있는 format 과 형식이 같아야함.
            // daysOfWeekDisabled : [0,6],	//선택 불가능한 요일 설정 0 : 일요일 ~ 6 : 토요일
            daysOfWeekHighlighted : [0], //강조 되어야 하는 요일 설정
            // disableTouchKeyboard : false,	//모바일에서 플러그인 작동 여부 기본값 false 가 작동 true가 작동 안함.
            immediateUpdates: false,	//사용자가 보는 화면으로 바로바로 날짜를 변경할지 여부 기본값 :false
            multidate : false, //여러 날짜 선택할 수 있게 하는 옵션 기본값 :false
            multidateSeparator :",", //여러 날짜를 선택했을 때 사이에 나타나는 글짜 2019-05-01,2019-06-01
            templates : {
                leftArrow: '&laquo;',
                rightArrow: '&raquo;'
            }, //다음달 이전달로 넘어가는 화살표 모양 커스텀 마이징
            showWeekDays : true ,// 위에 요일 보여주는 옵션 기본값 : true
            //title: "테스트",	//캘린더 상단에 보여주는 타이틀
            todayHighlight : true ,	//오늘 날짜에 하이라이팅 기능 기본값 :false
            toggleActive : true,	//이미 선택된 날짜 선택하면 기본값 : false인경우 그대로 유지 true인 경우 날짜 삭제
            weekStart : 0 ,//달력 시작 요일 선택하는 것 기본값은 0인 일요일
        });
        $('#datepicker').datepicker('update',today);
    },
    save : function () {
        // var csrfHeader =  $("meta[name='_csrf_header']").attr("content");
        // var csrfToken = $("meta[name='_csrf']").attr("content");

        var mtType = $('#mtType').val();
        var callback= $('#callback').val();
        var msg = $('#msg-text').val();
        var phoneNumber = $('#phoneNumber').val();

        if(mtType==null) {
            alert("메시지 유형을 선택해 주세요.");
            return;
        }
        if(callback===""){
            alert("발신 번호를 입력해 주세요.");
            return;
        }
        if(msg===""){
            alert("메시지 내용을 입력해 주세요.");
            return;
        }
        if(phoneNumber===""){
            alert("전화번호를 입력해 주세요.");
            return;
        }

        var aF = "";
        if($("input:checkbox[name='adFlag']").prop("checked") == true){
            aF = "Y";
        }
        else{
            aF = null;
        }

        var date = $('#datepicker').val().replace(/[^0-9]/g,'');

        var data = {
            mtType : mtType,
            adFlag : aF,
            reservedDate :  date+$('#time').val().toString().replace(/:/gi,"")+"00",
            callback : callback,
            msg: msg,
            phoneNumber: phoneNumber,
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/mt-msgs',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            // beforeSend : function(xhr) {
            //     //데이터를 전송하기 전에 헤더에 csrf값을 설정한다
            //     xhr.setRequestHeader(csrfHeader, csrfToken);
            // },
        }).done(function (stringStatusCode) {
            if (stringStatusCode =="200") {
                alert('발송 예약이 완료되었습니다.');
                window.location.href = '/send/mt-send';
            }
            else {
                var error = "";
                if (stringStatusCode == "9000"){
                    error = " 9000: kafka 서버 예외 발생";
                }
                else if(stringStatusCode == "4000"){
                    error = " 4000: API Server Connection Error";

                }
                else {
                    error = stringStatusCode;
                }
                alert('문제가 발생했습니다. 다시 시도해 주세요.\nerror code'+error);
            }
        }).fail(function (data, textStatus, errorThrown) {
            alert(JSON.stringify('문제가 발생했습니다. 다시 시도해 주세요.'+
                data+textStatus+errorThrown));
        });
    },
    update : function () {
        // var csrfHeader =  $("meta[name='_csrf_header']").attr("content");
        // var csrfToken = $("meta[name='_csrf']").attr("content");

        var mtType = $('#mtType').val();
        var callback= $('#callback').val();
        var msg = $('#msg').val();
        var phoneNumber = $('#phoneNumber').val();

        var aF = "";
        if($("input:checkbox[name='adFlag']").prop("checked") == true){
            aF = "Y";
        }
        else{
            aF = null;
        }

        var data = {
            mtType : mtType,
            adFlag : aF,
            reservedDate :  $('#datePicker').val()+$('#time').val().toString().replace(/:/gi,"")+"00",
            callback : callback,
            msg: msg,
            phoneNumber: phoneNumber,
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/mt-msgs/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
            // beforeSend : function(xhr) {
            //     //데이터를 전송하기 전에 헤더에 csrf값을 설정한다
            //     xhr.setRequestHeader(csrfHeader, csrfToken);
            // },
        }).done(function() {
            alert('수정되었습니다.');
            window.location.href = '/send/mt-record';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        // var csrfHeader =  $("meta[name='_csrf_header']").attr("content");
        // var csrfToken = $("meta[name='_csrf']").attr("content");

        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/mt-msgs/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            // beforeSend : function(xhr) {
            //     //데이터를 전송하기 전에 헤더에 csrf값을 설정한다
            //     xhr.setRequestHeader(csrfHeader, csrfToken);
            // },
        }).done(function() {
            alert('삭제되었습니다.');
            window.location.href = '/send/mt-record';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

send.init();