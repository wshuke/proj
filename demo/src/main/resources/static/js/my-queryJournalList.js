var getJournalUrl = "/getFaultLogs";

function processNull(value) {
    if(value==null)
        return '无';
    else
        return value;
}

function queryForJournal(DR, list){
    list.contents().remove();
    $.post(getJournalUrl,{DynamicRequest:JSON.stringify(DR)}, function (response) {

        var dataList = response.responseData;

        $.each(dataList, function (index, journals) {
            var faultClear;
            var faultClearColor="black";
            if(journals.faultClear===false){
                faultClear = "待处理";
                faultClearColor="red";

                var clearDate = "无";
            }
            else {
                faultClear = "已消除";
                clearDate=$.format.date(new Date(journals.faultClearTime), 'yyyy/MM/dd HH:mm:ss');
            }

            var appendTable = "<tr style='color: "+faultClearColor+"'"+">" +
                "<td>" + journals.device.deviceType.name+"</td>" +
                "<td>" + journals.device.urbanArea.name+"</td>" +
                "<td>" + journals.device.id+"</td>" +
                "<td>" + journals.faultType.name+"</td>" +
                "<td>" + $.format.date(new Date(journals.faultTime), 'yyyy/MM/dd HH:mm:ss') +"</td>" +
                "<td>" + faultClear+"</td>" +
                "<td>" + processNull(journals.faultDealUserName) +"</td>" +
                "<td>" + processNull(journals.maintenanceInfo) +"</td>" +
                "<td>" + clearDate +"</td>";

            list.append(appendTable);
        });

    });
}

var JNDR = {equalBy:{device_deviceType_id:null, device_urbanArea_id:null}, sortBy:[{id:"DESC"}], page:0, pageSize:10};
$(document).ready(function () {

    //queryForJournal(JNDR, $("#JNList"));

    $("#JN .sift-t").click(function () {            //设备类型筛选
        JNDR.equalBy.device_deviceType_id = parseInt($(this).attr("tid"));
        queryForJournal(JNDR, $("#JNList"));
    });
    $("#JN .sift-a").click(function () {            //设备地区筛选
        JNDR.equalBy.device_urbanArea_id = parseInt($(this).attr("aid"));
        queryForJournal(JNDR, $("#JNList"));
    });
    $("#JN .sift-rt").click(function () {           //清除设备类型筛选
        JNDR.equalBy.device_deviceType_id = null;
        queryForJournal(JNDR, $("#JNList"));
    });
    $("#JN .sift-ra").click(function () {           //清除设备地区筛选
        JNDR.equalBy.device_urbanArea_id = null;
        queryForJournal(JNDR, $("#JNList"));
    });

});