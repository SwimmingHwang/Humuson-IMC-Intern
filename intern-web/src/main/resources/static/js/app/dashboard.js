var atMsgs = [];
var mtMsgs = [];
var atReport = [];
var mtReport = [];
var chartData;
var chartLabel;
var chartData2;
var chartLabel2;
var chart;
var chart2;
var sdt;
var edt;

var dashboard = {
    init: function () {
        var _this = this;

        _this.initCount(); // 달라지는 값 체크해서 객체들을 리스트에 저장
        _this.initDatePicker(); // datepicker 세팅
        setTimeout(function () {
            _this.initChart(); // 차트 세팅
        }, 700)


    },
    initCount: function () {
        // 달라지는 값 체크해서 변경사항 있을 시 계속 변경
        console.log("init Count");
        changeAllCount();
        setInterval(function () { // 7초당 한번씩 데이터 업데이트
            changeAllCount();
        }, 7000);

        // api 호출한 뒤 변경사항 있으면 변경
        function changeAllCount() {
            // api 호출하여 객체 데이터 json 형식으로 가져옴
            callApi();
            setTimeout(function () {
                /**
                 * 발송량, 과금액 구현 부분
                 */
                var today = new Date();
                var monthAtCount = monthCountSet(today, atReport);
                var monthMtCount = monthCountSet(today, mtReport);
                var monthCount = monthAtCount + monthMtCount;
                var monthBill = countBill(monthAtCount, monthMtCount);
                // mc 변수의 형식과 같게 설정
                if (!($('#monthCount').text() == monthCount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','))) {
                    new numberCounter("monthCount", monthCount);
                    new numberCounter("monthBill", monthBill);
                }
                var dayAtCount = dayCountSet(today, atReport);
                var dayMtCount = dayCountSet(today, mtReport);
                var dayCount = dayAtCount + dayMtCount;
                var dayBill = countBill(dayAtCount, dayMtCount);
                if (!($('#dayCount').text() == dayCount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','))) {
                    new numberCounter("dayCount", dayCount);
                    new numberCounter("dayBill", dayBill);
                }
            }, 1000);
        };

        // number rolling
        function numberCounter(target_frame, target_number) {
            this.count = 0;
            this.diff = 0;
            this.target_count = parseInt(target_number);
            this.target_frame = document.getElementById(target_frame);
            this.timer = null;
            this.counter();
        };
        numberCounter.prototype.counter = function () {
            var self = this;
            this.diff = this.target_count - this.count;

            if (this.diff > 0) {
                self.count += Math.ceil(this.diff / 5);
            }

            this.target_frame.innerHTML = this.count.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');

            if (this.count < this.target_count) {
                this.timer = setTimeout(function () {
                    self.counter();
                }, 20);
            } else {
                clearTimeout(this.timer);
            }
        };

        // api 호출 함수
        function callApi() {
            $.ajax({
                type: 'GET',
                url: '/api/v1/at-msgs-info/list',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
            }).done(function (data) {
                atMsgs = data;
            }).fail(function (error) {
                console.log(JSON.stringify(error));
            });
            $.ajax({
                type: 'GET',
                url: '/api/v1/mt-msgs-info/list',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
            }).done(function (data) {
                mtMsgs = data;
            }).fail(function (error) {
                console.log(JSON.stringify(error));
            });
            $.ajax({
                type: 'GET',
                url: '/api/v1/at-report-info/list',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
            }).done(function (data) {
                atReport = data;
            }).fail(function (error) {
                console.log(JSON.stringify(error));
            });
            $.ajax({
                type: 'GET',
                url: '/api/v1/mt-report-info/list',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
            }).done(function (data) {
                mtReport = data;
            }).fail(function (error) {
                console.log(JSON.stringify(error));
            });
        }

        // 금월 발송건 카운트
        function monthCountSet(today, obj) {
            var count = 0;
            var ty = today.getFullYear();
            var tm = today.getMonth();
            for (var i = 0; i < obj.length; i++) {
                if (obj[i].report_code != "0000") continue;
                var date = changeDate(obj[i].response_date);
                var y = date.getFullYear();
                var m = date.getMonth();
                if (y == ty && m == tm) {
                    count++;
                }
            }
            return count;
        }

        // 금일 발송건 카운트
        function dayCountSet(today, obj) {
            var count = 0;
            var ty = today.getFullYear();
            var tm = today.getMonth();
            var td = today.getDate();
            for (var i = 0; i < obj.length; i++) {
                if (obj[i].report_code != "0000") {
                    continue;
                }
                var date = changeDate(obj[i].response_date);
                var y = date.getFullYear();
                var m = date.getMonth();
                var d = date.getDate();
                if (y == ty && m == tm && d == td) {
                    count++;
                }
            }
            return count;
        }

        // 알림톡, 문자톡 총 금액 합계 (알림톡 6원, 문자 8원)
        function countBill(atBill, mtBill) {
            return (atBill * 6) + (mtBill * 8);
        }
    },
    initDatePicker: function () {
        console.log("init Date Picker");
        var _this = this;

        // 초기 datepicker 값 설정
        var today = new Date();
        var lastWeek = new Date(today.getFullYear(), today.getMonth(), today.getDate() - 6);
        $("#datepicker1").datepicker({
            maxDate: today,
            minDate: new Date(today.getFullYear(), today.getMonth(), today.getDay() - 30),
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
                        minDate: new Date(today.getFullYear(), today.getMonth(), today.getDay() - 30) // 30일 전만 가능하게 설정
                    });
                }
                sDate.datepicker({
                    startDate: lastWeek, // 일주일 전을 시작으로 설정
                    language: 'ko',
                    autoClose: true,
                    onSelect: function () { // 버튼 클릭 시 이벤트 발생
                        datePickerSet(sDate, eDate);
                        updateChartVal();
                        updateChartData(chartData, chartLabel);
                        updateChart2Data(chartData2, chartLabel2);
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
                        updateChartVal();
                        updateChartData(chartData, chartLabel);
                        updateChart2Data(chartData2, chartLabel2);
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
                    maxDate: today,
                    onSelect: function () {
                        updateChartVal();
                        updateChartData(chartData, chartLabel);
                        updateChart2Data(chartData2, chartLabel2);
                    }
                });
            }

            function isValidStr(str) {
                if (str == null || str == undefined || str == "")
                    return true;
                else
                    return false;
            }
        };
    },
    initChart: function () {
        console.log("init Chart");
        /**
         * 차트 구현 부분
         */
        updateChartVal();
        setInterval(function() {
            updateChartVal();
            updateChartData(chartData, chartLabel);
            updateChart2Data(chartData2, chartLabel2);
        }, 7000);
        drawChart(chartData, chartLabel);
        drawChart2(chartData2, chartLabel2);

        function drawChart(data, labels) {
            var ctx = document.getElementById('myChart').getContext('2d');
            chart = new Chart(ctx, {
                // The type of chart we want to create
                type: 'bar',

                // The data for our dataset
                data: {
                    // labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
                    labels: labels,
                    datasets: [{
                        label: '메세지 전송량',
                        // barPercentage: 0,
                        barThickness: 30,
                        maxBarThickness: 50,
                        minBarLength: 0,
                        backgroundColor: 'rgba(28,200,138,0.7)',
                        borderColor: 'rgba(28,200,138,0.8)',
                        hoverBackgroundColor: 'rgba(28,200,138,0.9)',
                        hoverBorderColor: 'rgba(28,200,138,1)',
                        hoverBorderWidth: 1,
                        data: data
                    }]
                },

                // Configuration options go here
                options: {
                    responsive: false,
                    legend: {
                        display: false
                    },
                }
            });
        }
        function drawChart2(data, labels) {
            var ctx2 = document.getElementById('myChart2').getContext('2d');
            chart2 = new Chart(ctx2, {
                type: 'horizontalBar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: '메세지 전송량',
                        // barPercentage: 0,
                        barThickness: 15,
                        maxBarThickness: 15,
                        minBarLength: 0,
                        backgroundColor: [
                            'rgb(255, 205, 86, 0.8)',
                            'rgb(75, 192, 192, 0.8)',
                            'rgb(255, 99, 132, 0.8)'
                        ],
                        borderColor: [
                            'rgb(255, 205, 86, 0.9)',
                            'rgb(75, 192, 192, 0.9)',
                            'rgb(255, 99, 132, 0.9)'
                        ],
                        hoverBackgroundColor: [
                            'rgb(255, 205, 86, 1)',
                            'rgb(75, 192, 192, 1)',
                            'rgb(255, 99, 132, 1)'
                        ],
                        hoverBorderColor: [
                            'rgb(255, 205, 86, 1)',
                            'rgb(75, 192, 192, 1)',
                            'rgb(255, 99, 132, 1)'
                        ],
                        hoverBorderWidth: 1,
                        data: data
                    }]
                },
                options: {
                    responsive: false,
                    legend: {
                        display: false
                    },
                }
            });
        }
    },

}

// yyyymmdd 형식의 데이터를 변경 -> Date(yyyy, mm, dd);
function changeDate(_str) {
    var year = _str.substring(0, 4);
    var month = _str.substring(4, 6) - 1;
    var day = _str.substring(6, 8);
    return new Date(year, month, day)
}

// chart.js 다시 그릴 시 이전 차트 남아있는 버그 있으므로 remove 이후 추가하는 걸로 대체
function updateChartData(_data, _labels) {
    // chart.data.labels.forEach(label =>
    //     chart.data.labels.pop()
    // )
    // chart.data.datasets.forEach((dataset) => {
    //     dataset.data.pop();
    // });
    // chart.update();
    // chart.data.datasets.forEach((dataset) => {
    //     dataset.data.push(_data);
    //     chart.data.labels.push(_labels);
    // });
    chart.data.labels = _labels;
    chart.data.datasets[0].data = _data;
    chart.update();
}

function updateChart2Data(_data, _labels) {
    // chart.data.labels.forEach(label =>
    //     chart.data.labels.pop()
    // )
    // chart.data.datasets.forEach((dataset) => {
    //     dataset.data.pop();
    // });
    // chart.update();
    // chart.data.datasets.forEach((dataset) => {
    //     dataset.data.push(_data);
    //     chart.data.labels.push(_labels);
    // });
    chart2.data.datasets[0].data = _data;
    chart2.update();
}

function updateChartVal() {
    /**
     * 가로 막대 차트 부분 (발송량 조회)
     */
    chartData = [];
    chartLabel = [];
    chartData2 = [0, 0, 0];
    chartLabel2 = ["대기", "성공", "실패"];

    sdt = dateFormatChange($('#datepicker1').val());
    edt = dateFormatChange($('#datepicker2').val());

    // 기간 내의 성공한 데이터만 조회
    var perAtReport = periodObj(sdt, edt, atReport);
    var perMtReport = periodObj(sdt, edt, mtReport);

    // 총 일자
    var totalDay = diffPeriod(sdt, edt);
    for (var i = 0; i <= totalDay; i++) {
        chartData[i] = 0;
        chartLabel[i] = "";
    }
    // y축 : 날짜 세팅
    var ndt = sdt;
    for (var i = 0; i <= totalDay; i++) {
        var m = ndt.getMonth() + 1;
        var d = ndt.getDate();
        if (m.toString().length == 1) m = "0" + m;
        if (d.toString().length == 1) d = "0" + d;
        chartLabel[i] = ndt.getFullYear() + "년 " + m + "월 " + d + "일";
        ndt.setDate(ndt.getDate()+1);
    }
    // x축 : 데이터 세팅
    for (var i = 0; i < perAtReport.length; i++) {
        var ndt = changeDate(perAtReport[i].response_date);
        var diffPer = diffPeriod(sdt, ndt);
        chartData[totalDay - diffPer + 1]++;
    }
    for (var i = 0; i < perMtReport.length; i++) {
        var ndt = changeDate(perMtReport[i].response_date);
        var diffPer = diffPeriod(sdt, ndt);
        chartData[totalDay - diffPer + 1]++;
    }

    /**
     * 세로 막대 차트 부분 (발송 현황 조회) - status 1/2/3 대기중/성공/실패
     */
    // y축 데이터 세팅
    // 대기 count
    periodWaitData(sdt, edt, atMsgs, atReport);
    periodWaitData(sdt, edt, mtMsgs, mtReport);
}

// 페이지 기간 설정 datepicker의 데이터를 변경 (yyyy-mm-dd -> yyyymmdd 로 변경)
function dateFormatChange(_date) {
    var year = parseInt(_date.substring(0, 4));
    var month = parseInt(_date.substring(5, 7)) - 1;
    var day = parseInt(_date.substring(8, 10));
    return new Date(year, month, day);
}

// 기간 내의 객체만 추출 및 성공 실패 추출
function periodObj(_sdt, _edt, _obj) {
    var nobj = [];
    for (var i = 0; i < _obj.length; i++) {
        var date = changeDate(_obj[i].response_date);
        if (_sdt <= date && date <= _edt) {
            if (_obj[i].report_code != "0000") {
                chartData2[2]++;
                continue;
            }
            chartData2[1]++;
            var m = date.getMonth() + 1;
            var d = date.getDate();
            if (m.toString().length == 1) m = "0" + m;
            if (d.toString().length == 1) d = "0" + d;
            _obj[i].response_date = date.getFullYear() + "" + m + "" + d;
            nobj.push(_obj[i]);
        }
    }
    return nobj;
}

// 기간 내의 대기중인 데이터 추출
function periodWaitData(_sdt, _edt, _obj1) {
    for (var i = 0; i < _obj1.length; i++) {
        var date = changeDate(_obj1[i].reserved_date);
        var status = _obj1[i].status;
        if (_sdt <= date && date <= _edt) {
            if(status == "3") continue;
            chartData2[0]++;
        }
    }
}

// Date 일수 차이 구하기
function diffPeriod(_sdt, _edt) {
    // var time = Math.floor((edt.getTime() - sdt.getTime()) / 1000 / 60 / 60 / 24);
    // return time < 0 ? 0 : time;
    var timeDif = Math.abs(_edt.getTime() - _sdt.getTime());
    return Math.ceil(timeDif / (1000 * 3600 * 24));
}

// 차트 데이터 업데이트 되었는지 검사
/*function checkChartDate(_sdt, _edt) {
    var s = dateFormatChange($('#datepicker1').val());
    var e = dateFormatChange($('#datepicker2').val());
    if(_sdt.getFullYear() == s.getFullYear() || _sdt.getMonth() == s.getMonth() || _sdt.getDate() == s.getDate()) {
        if(_edt.getFullYear() == e.getFullYear() || _edt.getMonth() == e.getMonth() || _edt.getDate() == e.getDate()) {
            return false;
        }
    }
    return true;
}*/

dashboard.init();
