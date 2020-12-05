let flag = false;
let msg_status = false;
let isGroupInfoUpload = false;
let isCustomerInfoUpload = false;
var recipientsGroupList = [];
var recipientsCustomerList = [];
var groupJsonList = [];
var atSend = {
    init: function () {
        var _this = this;
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
        $('#templateCode').on('change', function () {
            $('#msg').val($(this).val());
            atSend.alertLimit();
        });
        $('#msg').keyup(function () {
            atSend.alertLimit();
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
            if(!isCustomerInfoUpload) {
                // atSend.getCustomerInput
                atSend.setCustomerTable(); // table upload
                // atSend.tableInit(); // add table event listener
            }
        });
        $('#btn-customerChkOK').on('click', function(){
            $('#customersModal').modal('hide');
            $('#selectedCustomerCount').text($("form").serializeObject().customers.length);
        });
        $('#btn-customerChkCancel').on('click', function(){
            $('#customersModal').modal('hide');
        });

        /* end of modal listener*/

    },
    groupTableInit: function(){ // table listener
        $('input:checkbox[name="groupChkAll"]').off().on('click', function(){
            recipientsGroupList = []
            $("#recipientsGroupList").empty();
            if (this.checked){
                $('input:checkbox[name="groups"]').each(function() {
                    $(this).prop("checked",true);
                    var valStr = $(this).prop("value");
                    recipientsGroupList.push(valStr);
                    atSend.recipientsGroupListAdd(valStr, $(this).parent().parent().children().eq(1).text())

                    // 그룹의 고객들 체크
                    var customers = groupJsonList[parseInt(valStr)-1].customers;
                    $.each(customers, function(index, customer) {
                        atSend.recipientsCustomerListAdd(parseInt(valStr)-1, customer.id.toString(),customer.name)
                    });

                });
            }else{
                $('input:checkbox[name="groups"]').each(function() {
                    $(this).prop("checked",false);
                });
            }
            // $('#selectedCustomerCount2').text($("form").serializeObject().customers.length);
        });

        $('#inputGroupTable tr').click(function (event) {
            var groupId = parseInt($(this).children().eq(0).attr("group_id")); // db id라 1부터 시작
            if (groupJsonList != null){
                // 해당 클래스 show or hide
                // atSend.setCustomerTable(groupId-1, groupJsonList[groupId-1].customers)
            }
        });

    },
    customerTableInit:function(){
        $('input:checkbox[name="customerChkAll"]').off().on('click', function(){
            if (this.checked){
                $('input:checkbox[name="customers"]').each(function() {
                    if ($(this).prop("checked")===false) {
                        $(this).prop("checked", true);
                        var valStr = $(this).prop("value");//sy
                        recipientsCustomerList.push(valStr);//sy
                        atSend.recipientsCustomerListAdd(valStr, $(this).parent().parent().children().eq(1).text())//sy
                    }
                });
                console.log(recipientsCustomerList)
            }else{
                $('input:checkbox[name="customers"]').each(function() {
                    if ($(this).prop("checked")===true) {
                        $(this).prop("checked", false);
                        var valStr = $(this).prop("value");//sy
                        recipientsCustomerList.pop(valStr);//sy
                    }
                });
            }
        });

        $('#inputCustomerTable tr').click(function (event) {
            var val;
            var checkbox = $(this).find('input');//sy

            if(checkbox.prop("name")=="customerChkAll")
                return

            if(event.target.type ==='checkbox') {

            } else {
                // 해당 row 체크박스에 checked or unchecked 해주기
                checkbox = $(this).find('input'); //sy
                checkbox.prop('checked', !checkbox.is(':checked'));

                // val = checkbox.val();
            }
            val = checkbox.val();
            if (checkbox.prop("checked") === true) {
                // 받는 대상 리스트에 추가
                recipientsCustomerList.push(val);
                console.log(recipientsCustomerList)
                atSend.recipientsCustomerListAdd(val-1, checkbox.parent().parent().children().eq(1).text())
            } else {
                // 받는 대상 리스트에 삭제
                const idx = recipientsCustomerList.indexOf(val);
                if (idx > -1) recipientsCustomerList.splice(idx, 1);
                atSend.recipientsCustomerListDelete(val)
                console.log(recipientsCustomerList)

            }

            // }
            //     if(event.target.type ==='checkbox'){
            //     checkbox = $(this).find('input');
            //     val = checkbox.val();
            //     if(checkbox.prop("checked") === true) {
            //         console.log(val)
            //
            //         // 받는 대상 리스트에 그룹 추가
            //         recipientsCustomerList.push(val);
            //         if (val ==="on") // head 눌렀을 때
            //             return;
            //         var apIdx = atSend.recipientsGroupListAdd(val, checkbox.parent().parent().children().eq(1).text())
            //
            //         // // 그룹의 고객들 체크
            //         // var customers = groupJsonList[parseInt(val)-1].customers;
            //         // $.each(customers, function(index, customer) {
            //         //     atSend.recipientsCustomerListAdd(apIdx,customer.id.toString(),customer.name)
            //         // });
            //
            //         // $('#customerChkAll').prop("checked",true);
            //         // atSend.
            //         // recipientsCustomerList.push(val);
            //     }
            //     else {
            //         // 받는 대상 리스트에 삭제
            //         const idx = recipientsGroupList.indexOf(val);
            //         if (idx > -1) recipientsGroupList.splice(idx, 1);
            //         atSend.recipientsGroupListDelete(val)
            //     }
            //
            // }else {
            //     var groupId = parseInt($(this).children().eq(0).attr("group_id")); // db id라 1부터 시작
            //
            //     if (groupJsonList != null){
            //         atSend.setCustomerTable(groupJsonList[groupId-1].customers)
            //     }
            // }

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
            atSend.setGroupTable(groupJsonList);
            // atSend.setCustomerTable(groupJsonList[0].customers)
        }).fail(function(error) {
            console.log(JSON.stringify(error));
        });
    },
    setGroupTable : function(groupJsonList){
        // add row
        $.each(groupJsonList, function(index, group) {
            if ($("#inputGroupTable tbody").length == 0) {
                $("#inputGroupTable").append("<tbody></tbody>");
            }
            $("#inputGroupTable tbody").append(atSend.groupBuildTableRow(group));
        });
        isGroupInfoUpload = true;
        isCustomerInfoUpload = true;
        atSend.groupTableInit(); // add table event listener
        atSend.customerTableInit();
    },
    groupBuildTableRow : function(group) {
        var ret = "<tr>"
            // +<td><input type="checkbox" name = "groups" value="' + group.id + '" class="cbx"></td>'
            + "<td group_id="+ group.id+">" + group.groupName + "</td>"
            + "<td>" + group.customerCount+ "</td>"
            + "</tr>";
        return ret;
    }, /* end of get and get group table*/
    setCustomerTable : function(){
        // Init table
        // $.each(groupJsonList,function (index, group) {
        //
        // })
        // // for groupJsonList.length
        var customerJsonList = groupJsonList[groupIdx].customers;

        // $("#inputCustomerTable tbody").empty()
        $("#inputCustomerTable tbody").hide()
        $("#inputCustomerTable tbody").show()

        $("#inputCustomerTable thead").find('input').prop("checked",false);
        $.each(customerJsonList, function(index, customer) {
            if ($("#inputCustomerTable tbody").length == 0) {
                $("#inputCustomerTable").append("<tbody></tbody>");
            }
            $("#inputCustomerTable tbody").append(atSend.customerBuildTableRow(groupIdx, customer));
        });

        isCustomerInfoUpload = true;
        atSend.customerTableInit();
    },
    customerBuildTableRow : function(groupIdx, customer) {
        if (customer.phoneNumber == null){
            customer.phoneNumber = "";
        }
        var ret = '<tr class="group_'+ groupIdx
            +'"><td><input type="checkbox" name = "customers" value="'
            + customer.id + '" class="cbx"></td>'
            + "<td>" + customer.name + "</td>"
            + "<td>" + customer.phoneNumber + "</td>"
            + "</tr>";
        return ret;
    }, /* end of get and get customer table*/
    recipientsGroupListAdd : function(idStr, text){
        var ul_list = $("#recipientsGroupList"); //ul_list선언
        ul_list.append("<li id='"+ idStr +"'>"+text+"</li>"); //ul_list안쪽에 li추가

        return $("#recipientsGroupList > li").length-1 //customer 추가할 위치
    },
    recipientsGroupListDelete : function(idText){
        $("#"+idText).remove(); //해당id의 li태그 삭제하기
    },
    recipientsCustomerListAdd : function(groupId, groupName, idStr, text){
        console.log("group ul에 group and customer add ")

        // 그룹 블럭에 li없으면 추가
        atSend.recipientsGroupListAdd(groupId, groupName)


        if ($('#recipientsGroupList > li:eq('+apIdxAtr +') > ul').length === 0)
        {
            $('#recipientsGroupList > li:eq('+apIdxAtr+')').append("<ul></ul>")
        }
        $('#recipientsGroupList > li:eq('+apIdxAtr +') > ul').append("<li id=" +idStr + ">"+text+"</li>");
    },
    recipientsCustomerListDelete : function(idText){
        $("#c"+idText).remove(); //해당id의 li태그 삭제하기
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
    sendImmediate: function() {
        this.initTime();
        this.initDatePicker();
        $('#datepicker').datepicker('destroy')

        $('#datepicker').prop('readonly', true);
        $('#time').prop('readonly', true);
        $('#datepicker').css('backgroundColor', '#eaecf4');
        $('#time').css('backgroundColor', '#eaecf4');
    },
    sendReserve: function() {
        this.initDatePicker();

        $('#datepicker').prop('readonly', false);
        $('#time').prop('readonly', false);
        $('#datepicker').css('backgroundColor', '#fff');
        $('#time').css('backgroundColor', '#fff');
        $("#datepicker").prop('disabled', false);
    }
}

atSend.init();