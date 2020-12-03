

var dashboard = {
    init : function() {
        var _this = this;
        var atMsgs;
        var mtMsgs;
        var atReport;
        var mtReport;

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

        // api 호출 함수
        function callApi() {
            /*$.ajax({
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
            });*/
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
                var today = new Date();
                var mc = monthCount(today, atReport) + monthCount(today, mtReport);
                if (!($('#monthCount').text() == mc.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','))) {
                    new numberCounter("monthCount", mc);
                    new numberCounter("monthBill", mc * 7);
                }
                var dc = dayCount(today, atReport) + dayCount(today, mtReport);
                if (!($('#dayCount').text() == dc.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','))) {
                    new numberCounter("dayCount", dc);
                    new numberCounter("dayBill", dc * 5);
                }
            }, 1000);
        }

        changeAll();
        setInterval(function() {
            changeAll();
        }, 7000);
    },


}

dashboard.init();
