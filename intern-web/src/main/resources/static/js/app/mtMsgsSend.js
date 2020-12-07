let flag = false;
let msg_status = false;
var mtSend = {
    init: function () {
        var _this = this;
        $(function () {
            _this.initTime();
            _this.initDatePicker();
            $('#datepicker').datepicker('destroy');
        });
        $('#send-immediate').on('click', function () {
            _this.sendImmediate();
        });
        $('#send-reserve').on('click', function () {
            _this.sendReserve();
        });
        $(function() {
            $('#msg-text').keyup(function () {
                bytesHandler(this);
            });
        });
        function getTextLength(str) {
            var len = 0;
            for (var i = 0; i < str.length; i++) {
                if (escape(str.charAt(i)).length == 6) len++;
                len++;
            }
            return len;
        }
        function bytesHandler(obj) {
            var text = $(obj).val();
            var textLen = getTextLength(text);

            if(textLen <= 2000) {
                if (textLen <= 80) flag = false;
                else flag = true;
                if (msg_status != flag) { // 상태 변경 되었을 때만 changLimit 호출
                    msg_status = flag.valueOf();
                    changeLimit();
                }
                $('#byte-count').text(textLen);
            } else {
                alert("최대 2000byte 크기까지 보낼 수 있습니다.");
                var nextLen = textLen;
                var nextStr = text;
                while (nextLen > 2000) {
                    nextStr = nextStr.substring(0, nextStr.length-1);
                    nextLen = getTextLength(nextStr);
                }
                $('#msg-text').focus().val(nextStr);
                $('#byte-count').text(nextLen);
            }
        }
        function changeLimit() {
            var text_limit = $('#byte-limit');
            var text_count = $('#byte-count');
            var msg_type = $('#msg-type');
            var send_button = $('#send-button');
            if(flag) {
                text_limit.removeClass("text-success");
                text_limit.addClass("text-warning");
                text_count.removeClass("text-success");
                text_count.addClass("text-warning");
                text_limit.text("/ 2000 byte");

                msg_type.removeClass("text-success");
                msg_type.addClass("text-warning");
                msg_type.text("LMS");

                send_button.removeClass("btn-success");
                send_button.addClass("btn-warning");

                alert("LMS 타입으로 변경되었습니다.")
            } else {
                text_limit.removeClass("text-warning");
                text_limit.addClass("text-success");
                text_count.removeClass("text-warning");
                text_count.addClass("text-success");
                text_limit.text("/ 80 byte");

                msg_type.removeClass("text-warning");
                msg_type.addClass("text-success");
                msg_type.text("SMS");

                send_button.removeClass("btn-warning");
                send_button.addClass("btn-success");
            }
        }

        function applyButton(nt) {
            var len = getTextLength(nt);
            if(len > 2000) {
                alert("최대 2000byte 크기까지 보낼 수 있습니다.");
            } else {
                $('#msg-text').focus().val(nt);
                $('#byte-count').text(len);
            }
        }
        $('#var-name').on('click', function () {
            var nextText = $('#msg-text').val() + "#{이름}";
            applyButton(nextText);
        });
        $('#var-1').on('click', function () {
            var nextText = $('#msg-text').val() + "#{변수1}";
            applyButton(nextText);
        });
        $('#var-2').on('click', function () {
            var nextText = $('#msg-text').val() + "#{변수2}";
            applyButton(nextText);
        });
        $('#var-3').on('click', function () {
            var nextText = $('#msg-text').val() + "#{변수3}";
            applyButton(nextText);
        });
    },
    initTime: function() {
        var date = new Date();

        var hour = date.getHours();
        var min = date.getMinutes();

        hour = (hour < 10 ? "0" : "") + hour;
        min = (min < 10 ? "0" : "") + min;

        $("#time").val(hour + ":" + min);
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
            todayHighlight : true ,	//오늘 날짜에 하이라이팅 기능 기본값 :falsse
            toggleActive : true,	//이미 선택된 날짜 선택하면 기본값 : false인경우 그대로 유지 true인 경우 날짜 삭제
            weekStart : 0 ,//달력 시작 요일 선택하는 것 기본값은 0인 일요일
            language: 'ko'
        });
        $('#datepicker').datepicker('update',today);
    },
    sendImmediate: function() {
        this.initTime();
        this.initDatePicker();
        $('#datepicker').datepicker('destroy')

        $('#datepicker').prop('readonly', true);
        $('#time').prop('readonly', true);

        $('#send-reserve').removeClass("bg-gray-400");
        $('#send-reserve').addClass("bg-light");

        $('#datepicker').removeClass("bg-light");
        $('#time').removeClass("bg-light");
        $('#datepicker').addClass("bg-gray-400");
        $('#time').addClass("bg-gray-400");

        $('#send-immediate').removeClass("bg-light");
        $('#send-immediate').addClass("bg-gray-400");
    },
    sendReserve: function() {
        this.initDatePicker();

        $('#datepicker').prop('readonly', false);
        $('#time').prop('readonly', false);

        $('#send-immediate').removeClass("bg-gray-400");
        $('#send-immediate').addClass("bg-light");

        $('#datepicker').removeClass("bg-gray-400");
        $('#time').removeClass("bg-gray-400");
        $('#datepicker').addClass("bg-light");
        $('#time').addClass("bg-light");

        $("#datepicker").prop('disabled', false);
        $('#send-reserve').removeClass("bg-light");
        $('#send-reserve').addClass("bg-gray-400");
    }
}

mtSend.init();