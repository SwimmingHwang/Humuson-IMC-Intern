

var dashboard = {
    init : function() {
        var _this = this;
        var atMsgs;
        var mtMsgs;
        var atReport;
        var mtReport;
        
        // 초기 datepicker 값 설정
        var today = new Date();
        var lastWeek = new Date(today.getFullYear(), today.getMonth(), today.getDate()-7);
        $("#datepicker1").datepicker({
            maxDate: today,
            minDate: new Date(today.getFullYear(), today.getMonth(), today.getDay()-30),
            startDate: lastWeek,
            language: 'ko',
            autoClose: true
        });
        $("#datepicker1").val(lastWeek);
        $("#datepicker2").datepicker({
            maxDate: today,
            startDate: today,
            language: 'ko',
            autoClose: true
        });
        $("#datepicker2").val(today);
        // datepicker setting
        datePickerSet($("#datepicker1"), $("#datepicker2"), true);

        // 달라지는 값 체크해서 변경사항 있을 시 계속 변경
        changeAll();
        setInterval(function() {
            changeAll();
        }, 7000);

        // datepicker setting
        function datePickerSet(sDate, eDate, flag) {
            //시작 ~ 종료 2개 짜리 달력 datepicker
            if (!isValidStr(sDate) && !isValidStr(eDate) && sDate.length > 0 && eDate.length > 0) {
                var sDay = sDate.val();
                var eDay = eDate.val();

                if (flag && !isValidStr(sDay) && !isValidStr(eDay)) { //처음 입력 날짜 설정, update...
                    var sdp = sDate.datepicker().data("datepicker");
                    sdp.selectDate(new Date(sDay.replace(/-/g, "-")));  //익스에서는 그냥 new Date하면 -을 인식못함 replace필요

                    var edp = eDate.datepicker().data("datepicker");
                    edp.selectDate(new Date(eDay.replace(/-/g, "-")));  //익스에서는 그냥 new Date하면 -을 인식못함 replace필요
                }
                //시작일자 세팅하기 날짜가 없는경우엔 제한을 걸지 않음
                if (!isValidStr(eDay)) {
                    sDate.datepicker({
                        maxDate: new Date(eDay.replace(/-/g, "-")),
                        minDate: new Date(today.getFullYear(), today.getMonth(), today.getDay()-30) // 30일 전만 가능하게 설정
                    });
                }
                sDate.datepicker({
                    startDate: lastWeek, // 일주일 전을 시작으로 설정
                    language: 'ko',
                    autoClose: true,
                    onSelect: function () {
                        datePickerSet(sDate, eDate);
                    }
                });

                //종료일자 세팅하기 날짜가 없는경우엔 제한을 걸지 않음
                if (!isValidStr(sDay)) {
                    eDate.datepicker({
                        minDate: new Date(sDay.replace(/-/g, "-")),
                        maxDate: new Date() // 내일은 검색 안되게  설정
                    });
                }
                eDate.datepicker({
                    startDate: today, // 오늘을 시작으로 설정
                    language: 'ko',
                    autoClose: true,
                    onSelect: function () {
                        datePickerSet(sDate, eDate);
                    }
                });
                //한개짜리 달력 datepicker
            } else if (!isValidStr(sDate)) {
                var sDay = sDate.val();
                if (flag && !isValidStr(sDay)) { //처음 입력 날짜 설정, update...
                    var sdp = sDate.datepicker().data("datepicker");
                    sdp.selectDate(new Date(sDay.replace(/-/g, "-"))); //익스에서는 그냥 new Date하면 -을 인식못함 replace필요
                }
                sDate.datepicker({
                    startDate: today,
                    language: 'ko',
                    autoClose: true,
                    maxDate: today
                });
            }
            function isValidStr(str) {
                if (str == null || str == undefined || str == "")
                    return true;
                else
                    return false;
            }
        };

        // number rolling
        function numberCounter(target_frame, target_number) {
            this.count = 0; this.diff = 0;
            this.target_count = parseInt(target_number);
            this.target_frame = document.getElementById(target_frame);
            this.timer = null;
            this.counter();
        };
        numberCounter.prototype.counter = function() {
            var self = this;
            this.diff = this.target_count - this.count;

            if(this.diff > 0) {
                self.count += Math.ceil(this.diff / 5);
            }

            this.target_frame.innerHTML = this.count.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');

            if(this.count < this.target_count) {
                this.timer = setTimeout(function() { self.counter(); }, 20);
            } else {
                clearTimeout(this.timer);
            }
        };
        // count
        function monthCount(today, obj) {
            var count = 0;
            var ty = today.getFullYear();
            var tm = today.getMonth();

            for(var i=0; i<obj.length; i++) {
                if(obj[i].report_code != "0000") continue;
                var responseDate = obj[i].response_date;
                var y = parseInt(responseDate.substring(0,4));
                var m = parseInt(responseDate.substring(4,6)) - 1;
                if(y == ty && m == tm) {
                    count++;
                }
            }
            return count;
        }
        function dayCount(today, obj) {
            var count = 0;
            var ty = today.getFullYear();
            var tm = today.getMonth();
            var td = today.getDate();

            for(var i=0; i<obj.length; i++) {
                if(obj[i].report_code != "0000") continue;
                var responseDate = obj[i].response_date;
                var y = parseInt(responseDate.substring(0,4));
                var m = parseInt(responseDate.substring(4,6)) - 1;
                var d = parseInt(responseDate.substring(6,8));
                if(y == ty && m == tm && d == td) {
                    count++;
                }
            }
            return count;
        }
        // 기간 내의 객체만 추출
        function periodObj(prevDate, nextDate, obj) {
            var nobj = [];
            for(var i=0; i<obj.length; i++) {
                if(obj[i].report_code != "0000") continue;
                var responseDate = obj[i].response_date;
                var y = parseInt(responseDate.substring(0,4));
                var m = parseInt(responseDate.substring(4,6)) - 1;
                var d = parseInt(responseDate.substring(6,8));
                var date = new Date(y, m, d);
                if(prevDate <= date && date <= nextDate) {
                    nobj.push(obj[i]);
                }
            }
            return nobj;
        }

        // api 호출 함수
        function callApi() {
            $.ajax({
                type : 'GET',
                url : '/api/v1/at-msgs/list',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
            }).done(function(data) {
                atMsgs = data;
            }).fail(function(error) {
                console.log(JSON.stringify(error));
            });
            $.ajax({
                type : 'GET',
                url : '/api/v1/mt-msgs/list',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
            }).done(function(data) {
                mtMsgs = data;
            }).fail(function(error) {
                console.log(JSON.stringify(error));
            });
            $.ajax({
                type : 'GET',
                url : '/api/v1/at-report/list',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
            }).done(function(data) {
                atReport = data;
            }).fail(function(error) {
                console.log(JSON.stringify(error));
            });
            $.ajax({
                type : 'GET',
                url : '/api/v1/mt-report/list',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
            }).done(function(data) {
                mtReport = data;
            }).fail(function(error) {
                console.log(JSON.stringify(error));
            });
        }

        // api 호출한 뒤 변경사항 있으면 변경
        function changeAll() {
            callApi();
            setTimeout(function() {
                // count 변경 부분
                var today = new Date();
                var mc = monthCount(today, atReport) + monthCount(today, mtReport);
                // mc의 형식과 같게 설정
                if (!($('#monthCount').text() == mc.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','))) {
                    new numberCounter("monthCount", mc);
                    new numberCounter("monthBill", mc * 7);
                }
                var dc = dayCount(today, atReport) + dayCount(today, mtReport);
                if (!($('#dayCount').text() == dc.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','))) {
                    new numberCounter("dayCount", dc);
                    new numberCounter("dayBill", dc * 5);
                }

                // string -> Date
                var date1 = dateFormatChange($('#datepicker1').val());
                var date2 = dateFormatChange($('#datepicker2').val());
                // 기간 내의 데이터만 조회
                var perAtReport = periodObj(date1, date2, atReport);
                var perMtReport = periodObj(date1, date2, mtReport);

                console.log(perAtReport.length);
                console.log(perMtReport.length);

                //가로 막대 차트 부분 (발송량 조회)


                // 세로 막대 차트 부분 (발송 현황 조회)

            }, 1000);
        }
        // date form yyyymmdd 로 변경
        function dateFormatChange(date) {
            var year = parseInt(date.substring(0, 4));
            var month = parseInt(date.substring(5, 7))-1;
            var day = parseInt(date.substring(8, 10));
            return new Date(year, month, day);
            /*var year = parseInt(date.substring(date.indexOf("년")-5, date.indexOf("년")));
            var month = parseInt( date.substring(date.indexOf("월")-2, date.indexOf("월")))-1;
            var day = parseInt(date.substring(date.indexOf("일")-2, date.indexOf("일")));
            return new Date(year, month, day);*/
        }

    },
    /*initDatePicker1: function() {
        var date = new Date();
        var lastWeek = lastWeek(date);
        function lastWeek(date) {
            var dayOfMonth = date.getDate()
            date.setDate(dayOfMonth - 7)
            return date;
        }
        $('#datepicker1').datepicker({
            format: "yyyy년 mm월 dd일",	//데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
            // startDate: '-30d',	//달력에서 선택 할 수 있는 가장 빠른 날짜. 이전으로는 선택 불가능 ( d : 일 m : 달 y : 년 w : 주)
            // endDate: '0d',	//달력에서 선택 할 수 있는 가장 느린 날짜. 이후로 선택 불가 ( d : 일 m : 달 y : 년 w : 주)
            // autoclose : true,	//사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
            // calendarWeeks : false, //캘린더 옆에 몇 주차인지 보여주는 옵션 기본값 false 보여주려면 true
            // clearBtn : false, //날짜 선택한 값 초기화 해주는 버튼 보여주는 옵션 기본값 false 보여주려면 true
            // daysOfWeekHighlighted : [0], //강조 되어야 하는 요일 설정
            // immediateUpdates: false,	//사용자가 보는 화면으로 바로바로 날짜를 변경할지 여부 기본값 :false
            // multidate : false, //여러 날짜 선택할 수 있게 하는 옵션 기본값 :false
            // multidateSeparator :",", //여러 날짜를 선택했을 때 사이에 나타나는 글짜 2019-05-01,2019-06-01
            // templates : {
            //     leftArrow: '&laquo;',
            //     rightArrow: '&raquo;'
            // }, //다음달 이전달로 넘어가는 화살표 모양 커스텀 마이징
            // showWeekDays : true ,// 위에 요일 보여주는 옵션 기본값 : true
            // todayHighlight : true ,	//오늘 날짜에 하이라이팅 기능 기본값 :false
            // toggleActive : true,	//이미 선택된 날짜 선택하면 기본값 : false인경우 그대로 유지 true인 경우 날짜 삭제
            // weekStart : 0 ,//달력 시작 요일 선택하는 것 기본값은 0인 일요일
            language: 'ko'
        });
        $('#datepicker1').datepicker('update',lastWeek);
    },
    initDatePicker2: function() {
        var date = new Date();
        var today = setDay(date);
        function setDay(date) {
            var day = date.getDate(),
                month = date.getMonth() + 1,
                year = date.getFullYear();
            month = (month < 10 ? "0" : "") + month;
            day = (day < 10 ? "0" : "") + day;
            return year + "" + month + "" + day;
        }
        $('#datepicker2').datepicker({
            format: "yyyy년 mm월 dd일",	//데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
            language: 'ko'
        });
        $('#datepicker2').datepicker('update',today);
    },*/

}

dashboard.init();
