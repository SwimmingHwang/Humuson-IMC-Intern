let flag = false;
let msg_status = false;
let isGroupInfoUpload = false;
let isCustomerInfoUpload = false;
var recipientsGroupList = [];
var recipientsCustomerList = [];
var groupJsonList = [];
var customerJsonList = [];
var groupId;
var groupName;
var fileCustomerList = new Array();
var notChancedReservedDate = $('#reservedDate').val();

var atSend = {
    init: function () {
        var _this = this;
        $.fn.serializeObject = function () {
            'use strict';
            var count = recipientsCustomerList.length;

            var result = {customers:Array(), count:count};
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
            // TODO : 선택된거 없는 경우는 alert해줘야함
            if (result.customers.length === 0)
            {
                if ($('#recipientsGroupList').attr("field").slice(1,-1).split(",") ==="")
                    return result
                else
                    result.customers = $('#recipientsGroupList').attr("field").slice(1,-1).split(",");
            }
            return result;
        };
        $(function () {
            _this.initNotChangeReservedDate();
            // _this.getCustomerInput();
            _this.initRecipients();
        });
        $('#send-immediate').on('click', function () {
            _this.sendImmediate();
        });
        $('#send-not-change').on('click', function () {
            _this.sendNotChange();
        });
        $('#send-reserve').on('click', function () {
            _this.sendReserve();
        });
        $('#templateContent').on('change', function () {
            $('#msg').val($(this).val());
            atSend.alertLimit();
        });
        $('#msg').keyup(function () {
            atSend.alertLimit();
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
        $(function() {
            var varElements = document.getElementsByClassName("vars");
            Array.from(varElements).forEach(function(element) {
                element.addEventListener('click', atSend.applyVars);
            });
        });
        /* modal listener */
        $('#customersModal').on('show.bs.modal' , function(){
            if(!isGroupInfoUpload){
                atSend.getGroupInput(); // table upload
            }
        });
        $('#btn-customerChkOK').on('click', function(){
            // $('input:hidden[name=customers]').val(recipientsCustomerList);
            // console.log("customers :" + recipientsCustomerList);
            $('#recipientsGroupList-out').empty();
            $('#recipientsGroupList-out').append($('#recipientsGroupList > li').clone())
            $('#customersModal').modal('hide');
        });
        $('#btn-customerChkCancel').on('click',function () {
            $("input[type=checkbox]").prop("checked",false);
            $('#recipientsGroupList').empty();
        })
        /* end of modal listener*/

        $('#btn-phoneNumber').on('click', function () {
            atSend.loadPhoneNums();
        });
    },
    initRecipients:function(){
        $('#recipientsGroupList-out').empty();

        var recStrList = $('#recipientsGroupList').attr("field")
        var recList = JSON.parse("["+recStrList+"]")

        var data = recList[0];

        $.ajax({
            type: 'POST',
            url: '/api/v1/customer/list',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
        }).done(function (set) {
            customerJsonList = set;
            customerJsonList.forEach(function(item){
                $('#recipientsGroupList-out').append("<li>"+item.phoneNumber +" "+ item.name+"</li>");
            });

        }).fail(function (error) {
            console.log("init recipients failed")
        });
    },
    loadPhoneNums: function () {
        var input = document.createElement("input");
        input.type = "file";
        input.accept = "text/plain,.csv"; // 확장자가 xxx, yyy 일때, ".xxx, .yyy"
        input.onchange = function (event) {
            res = atSend.processFile(event.target.files[0]);
        };
        input.click();
    },
    processFile: function (file) {
        var reader = new FileReader();
        reader.onload = function () {
            var sep = "";
            if (file.name.split(".").pop() == "txt")
                sep = " ";
            else
                sep = ",";

            res = reader.result.split("\n");
            // var tbody = document.getElementById('tbody');
            var tbody = document.getElementById('tbody');
            while (tbody.rows.length >= 1)
                // my_tbody.deleteRow(0); // 상단부터 삭제
                tbody.deleteRow(tbody.rows.length - 1); // 하단부터 삭제
            fileCustomerList = [];
            msgList = [];

            for (var i in res) {
                li = [];
                if (res[i].length <= 2)
                    break;
                var row = tbody.insertRow(tbody.rows.length); // 하단에 추가
                var cell1 = row.insertCell(0); // 순서
                var cell2 = row.insertCell(1); // 아이디
                var cell3 = row.insertCell(2); // 이름
                var cell4 = row.insertCell(3); // 전화번호
                var cell5 = row.insertCell(4); // address

                cellData = res[i].split(sep);
                // cellData[6] = cellData[6].replace(/\r/gm,"")/

                cell1.innerHTML = cellData[0];
                cell2.innerHTML = cellData[1];
                cell3.innerHTML = cellData[2];
                cell4.innerHTML = cellData[3];
                cell5.innerHTML = cellData[4];

                li.push(cellData[0]);
                li.push(cellData[1]);
                li.push(cellData[2]);
                li.push("82"+cellData[3].substring(1));
                li.push(cellData[4])

                fileCustomerList.push(li);

            }

        };
        reader.readAsText(file, /* optional */ "UTF-8");
        $('#csvCustomersModal').modal('show');

    },
    groupTableInit: function(){ // table listener
        $('#inputGroupTable tr').click(function (event) {
            if ($(this).attr("group_id") === "0"){
                return
            }
            groupId = parseInt($(this).attr("group_id")); // db id라 1부터 시작
            groupName = $(this).children().eq(0).text()
            atSend.setCustomerTable(groupId);
            $('#customerChkAll').prop("checked", false)
        });

    },
    customerTableInit:function(){
        $('input:checkbox[name="customerChkAll"]').off().on('click', function(){
            if (this.checked){
                $('input:checkbox.group_'+groupId).each(function() {
                    if ($(this).prop("checked")===false) {
                        $(this).prop("checked", true);
                        var valStr = $(this).prop("value");//sy
                        var customerName = $(this).parent().parent().children().eq(1).text();
                        recipientsCustomerList.push(valStr);//sy
                        atSend.recipientsCustomerListAdd(groupId, groupName, parseInt(valStr), customerName)

                    }
                });
                console.log(recipientsCustomerList)
            }else{
                $('input:checkbox.group_'+groupId).each(function() {
                    if ($(this).prop("checked")===true) {
                        $(this).prop("checked", false);
                        var valStr = $(this).prop("value");//sy
                        recipientsCustomerList.pop(valStr);//sy
                        atSend.recipientsCustomerListDelete(groupId,parseInt(valStr))
                    }
                });
            }
        });

        $('#inputCustomerTable tr').click(function (event) {
            var val;
            var checkbox = $(this).find('input');//sy

            if(checkbox.prop("name")==="customerChkAll")
                return
            if(event.target.type ==='checkbox') {
            } else {
                // 해당 row 체크박스에 checked or unchecked 해주기
                checkbox = $(this).find('input'); //sy
                checkbox.prop('checked', !checkbox.is(':checked'));
            }
            val = checkbox.val();
            var groupIdxStr = $(this).attr("class").slice(6);
            var groupIdxInt = parseInt(groupIdxStr);
            groupName = $('#inputGroupTable tr').get(groupIdxInt).children[0].textContent;
            var customerName = $(this).children().eq(1).text();

            if (checkbox.prop("checked") === true) {
                // 받는 대상 리스트에 추가
                recipientsCustomerList.push(val);
                console.log(recipientsCustomerList)
                atSend.recipientsCustomerListAdd(groupIdxInt, groupName, val, customerName)
            } else {
                // 받는 대상 리스트에 삭제
                const idx = recipientsCustomerList.indexOf(val);
                if (idx > -1) recipientsCustomerList.splice(idx, 1);
                atSend.recipientsCustomerListDelete(groupIdxInt, val)
                console.log(recipientsCustomerList)
            }
        });
    },
    alertLimit: function() {
        var msgSelector = $('#msg');
        var len = msgSelector.val().length;

        if (len > 1000) {
            alert("최대 1000자 크기까지 보낼 수 있습니다.");
            msgSelector.val(msgSelector.val().substring(0, 1000));
        }
        len = msgSelector.val().length;
        $('#byte-count').text(len);
    },
    applyVars : function(){
        var varStr = this.getAttribute("value");

        var msgSelector = $('#msg');

        var cursorPos = msgSelector.prop('selectionStart');
        var v = msgSelector.val();
        var textBefore = v.substring(0, cursorPos);
        var textAfter = v.substring(cursorPos, v.length);

        msgSelector.val(textBefore + varStr + textAfter);

        atSend.alertLimit();
    },
    /* get and set group table  */
    getGroupInput : function(){
        $.ajax({
            type : 'GET',
            url : '/api/v1/customer/group/list',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function(groupJsonListRes) {
            groupJsonList = groupJsonListRes
            atSend.setTables(groupJsonList);
        }).fail(function(error) {
            console.log(JSON.stringify(error));
        });
    },
    // getCustomerInput:function () {
    //     $.ajax({
    //         type : 'GET',
    //         url : ' /api/v1/customer/list',
    //         dataType: 'json',
    //         contentType: 'application/json; charset=utf-8',
    //     }).done(function(customerJsonListRes) {
    //         customerJsonList = customerJsonListRes
    //     }).fail(function(error) {
    //         console.log(JSON.stringify(error));
    //     });
    // },
    setTables : function(groupJsonList){
        /* Set group and customer table */
        if ($("#inputGroupTable tbody").length == 0) {
            $("#inputGroupTable").append("<tbody></tbody>");
        }
        if ($("#inputCustomerTable tbody").length == 0) {
            $("#inputCustomerTable").append("<tbody></tbody>");
        }
        $.each(groupJsonList, function(index, group) {
            $("#inputGroupTable tbody").append(atSend.groupBuildTableRow(group));
            $.each(group.customers, function(index, customer) {
                $("#inputCustomerTable tbody").append(atSend.customerBuildTableRow(group.id, customer));
            });
        });
        isGroupInfoUpload = true;
        isCustomerInfoUpload = true;
        atSend.groupTableInit(); // add table event listener
        atSend.customerTableInit();
        atSend.hideCustomerTable();
    },
    groupBuildTableRow : function(group) {
        var ret = "<tr group_id="+ group.id+">"
            // +<td><input type="checkbox" name = "groups" value="' + group.id + '" class="cbx"></td>'
            + "<td>" + group.groupName + "</td>"
            + "<td>" + group.customerCount+ "</td>"
            + "</tr>";
        return ret;
    }, /* end of get and get group table*/
    customerBuildTableRow : function(groupIdx, customer) {
        if (customer.phoneNumber == null){
            customer.phoneNumber = "";
        }
        var ret = '<tr class="group_'+ groupIdx
            +'"><td><input type="checkbox" name = "customers" value="'
            + customer.id + '" class="group_'+groupIdx+'"></td>'
            + "<td>" + customer.name + "</td>"
            + "<td>" + customer.phoneNumber + "</td>"
            + "</tr>";
        return ret;
    }, /* end of get and get customer table*/
    setCustomerTable : function(groupId){
        groupJsonList.forEach(function(item){
                if(item.id === groupId)
                    $(".group_"+item.id).show()
                else{
                    $(".group_"+item.id).hide()
                }
        })
    },
    hideCustomerTable : function(groupId){
        groupJsonList.forEach(function(item){
                $(".group_"+item.id).hide()
        })
    },
    recipientsGroupListAdd : function(groupIdx, groupName){
        var ul_list = $("#recipientsGroupList"); //ul_list선언
        if ($("#group_li_"+groupIdx).length ===0)
            ul_list.append("<li id=group_li_"+ groupIdx +">"+groupName+"</li>"); //ul_list안쪽에 li추가

        // return $("#recipientsGroupList > li").length-1 //customer 추가할 위치
    },
    recipientsGroupListDelete : function(idText){
        $("#"+idText).remove(); //해당id의 li태그 삭제하기
    },
    recipientsCustomerListAdd : function(groupId, groupName, customerId, customerName){
        console.log("group ul에 group and customer add ")

        // 그룹 블럭에 li없으면 추가
        atSend.recipientsGroupListAdd(groupId, groupName);

        if ($('#group_li_'+groupId+' > ul').length === 0)
        {
            $('#group_li_'+groupId+'').append("<ul></ul>");
        }
        $('#group_li_'+groupId+' > ul').append("<li id=customer_li_" +customerId + ">"+customerName+"</li>");
    },
    recipientsCustomerListDelete : function(groupIdx, customerId){
        $("#customer_li_"+customerId).remove(); //해당id의 li태그 삭제하기
        if ($("#group_li_"+groupIdx).children().children().length===0){
            $("#group_li_"+groupIdx).remove()
        }

        },
    save : function () {
        var date = $('#datepicker').val().replace(/[^0-9]/g,'');
        $('#reservedDate').val(date+$('#time').val().toString().replace(/:/gi,"")+"00",)

        var data = $("form").serializeObject();

        $.ajax({
            type: 'POST',
            url: '/api/v1/at-campaign',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            // success :
        }).done(function (status) {
            alert('알림톡 대량 발송이 예약되었습니다.');
            window.location.href = '/';

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var date = $('#datepicker').val().replace(/[^0-9]/g,'');
        $('#reservedDate').val(date+$('#time').val().toString().replace(/:/gi,"")+"00",)

        var data = $("form").serializeObject();

        var id = $("#id").val();
        $.ajax({
            type: 'PUT',
            url: '/api/v1/at-campaign/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            // success :
        }).done(function (status) {
            alert('알림톡 대량 발송 정보가 수정되었습니다.');
            window.location.href = '/send/at-camp-record';

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete: function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/at-campaign/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert('예약 정보가 삭제되었습니다.');
            window.location.href = '/send/at-camp-record';
        }).fail(function (error) {
            alert(JSON.stringify(error));
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
            todayHighlight : true ,	//오늘 날짜에 하이라이팅 기능 기본값 :false
            toggleActive : true,	//이미 선택된 날짜 선택하면 기본값 : false인경우 그대로 유지 true인 경우 날짜 삭제
            weekStart : 0 ,//달력 시작 요일 선택하는 것 기본값은 0인 일요일
        });
        $('#datepicker').datepicker('update',today);
    },
    initNotChangeReservedDate :function(){
        // "20201214165700"
        var hour = notChancedReservedDate.substr(8,2);
        var min = notChancedReservedDate.substr(10,2);


        $("#time").val(hour + ":" + min);

        var day = notChancedReservedDate.substr(6,2),
            month = notChancedReservedDate.substr(4,2),
            year = notChancedReservedDate.substr(0,4);

        $("#datepicker").val(year + "년 " + month + "월 " + day + "일");
    },
    sendNotChange: function(){
        atSend.initNotChangeReservedDate();

        $('#datepicker').datepicker('destroy')

        $('#datepicker').prop('readonly', true);
        $('#time').prop('readonly', true);

        $('#datepicker').removeClass("bg-light");
        $('#time').removeClass("bg-light");
        $('#datepicker').addClass("bg-gray-400");
        $('#time').addClass("bg-gray-400");

        $('#send-reserve').removeClass("bg-gray-400");
        $('#send-reserve').addClass("bg-light");
        $('#send-immediate').removeClass("bg-gray-400");
        $('#send-immediate').addClass("bg-light");

        $('#send-not-change').removeClass("bg-light");
        $('#send-not-change').addClass("bg-gray-400");
    },
    sendImmediate: function() {
        this.initTime();
        this.initDatePicker();
        $('#datepicker').datepicker('destroy')

        $('#datepicker').prop('readonly', true);
        $('#time').prop('readonly', true);

        $('#datepicker').removeClass("bg-light");
        $('#time').removeClass("bg-light");
        $('#datepicker').addClass("bg-gray-400");
        $('#time').addClass("bg-gray-400");

        $('#send-reserve').removeClass("bg-gray-400");
        $('#send-reserve').addClass("bg-light");
        $('#send-not-change').removeClass("bg-gray-400");
        $('#send-not-change').addClass("bg-light");

        $('#send-immediate').removeClass("bg-light");
        $('#send-immediate').addClass("bg-gray-400");
    },
    sendReserve: function() {
        this.initDatePicker();

        $('#datepicker').prop('readonly', false);
        $('#time').prop('readonly', false);

        $('#datepicker').removeClass("bg-gray-400");
        $('#time').removeClass("bg-gray-400");
        $('#datepicker').addClass("bg-light");
        $('#time').addClass("bg-light");

        $("#datepicker").prop('disabled', false);

        $('#send-immediate').removeClass("bg-gray-400");
        $('#send-immediate').addClass("bg-light");
        $('#send-not-change').removeClass("bg-gray-400");
        $('#send-not-change').addClass("bg-light");

        $('#send-reserve').removeClass("bg-light");
        $('#send-reserve').addClass("bg-gray-400");
    },
}

atSend.init();