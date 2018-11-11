var getDevicesUrl = "/getDevices";

function queryForDevice(DR, list){
    list.contents().remove();
    $.post(getDevicesUrl,{DynamicRequest:JSON.stringify(DR)}, function (response) {

        var dataList = response.responseData;
        var num=0;
        $.each(dataList, function (index, device) {
            num = num + 1;
            var appendTable = "<tr>" +
                "<td>" + num +"</td>" +
                "<td>" + device.deviceType.name+"</td>" +
                "<td>" + device.urbanArea.name+"</td>" +
                "<td>" + device.id+"</td>" +
                "<td>" + device.deviceState.name+"</td>" +
                "<td>" + device.user.name+"</td>";

            if(list.is("#OSList")){
                appendTable=appendTable+'<td><a class="to-OI" href="" onclick="return false" did="' + device.id + '">实时信息</a></td>' +        //did--设备ID
                    "</tr>";
            }
            else if(list.is("#EMList")){
                appendTable=appendTable+'<td><a href="" onclick="return false" did="' + device.id + '">设备信息</a></td>' +        //did--设备ID
                    "</tr>";
            }
            list.append(appendTable);
        });

    });
}

var OSDR = {equalBy:{deviceType_id:null, urbanArea_id:null}, page:0, pageSize:10};
$(document).ready(function () {

    //queryForDevice(OSDR, $("#OSList"));

    $("#OS .sift-t").click(function () {            //设备类型筛选
        OSDR.equalBy.deviceType_id = parseInt($(this).attr("tid"));
        queryForDevice(OSDR, $("#OSList"));
    });
    $("#OS .sift-a").click(function () {            //设备地区筛选
        OSDR.equalBy.urbanArea_id = parseInt($(this).attr("aid"));
        queryForDevice(OSDR, $("#OSList"));
    });
    $("#OS .sift-rt").click(function () {           //清除设备类型筛选
        OSDR.equalBy.deviceType_id = null;
        queryForDevice(OSDR, $("#OSList"));
    });
    $("#OS .sift-ra").click(function () {           //清除设备地区筛选
        OSDR.equalBy.urbanArea_id = null;
        queryForDevice(OSDR, $("#OSList"));
    });

});

var EMDR = {equalBy:{deviceType_id:null, urbanArea_id:null}, page:0, pageSize:10};
$(document).ready(function () {

    //queryForDevice(EMDR, $("#EMList"));

    $("#EM .sift-t").click(function () {            //设备类型筛选
        EMDR.equalBy.deviceType_id = parseInt($(this).attr("tid"));
        queryForDevice(EMDR, $("#EMList"));
    });
    $("#EM .sift-a").click(function () {            //设备地区筛选
        EMDR.equalBy.urbanArea_id = parseInt($(this).attr("aid"));
        queryForDevice(EMDR, $("#EMList"));
    });
    $("#EM .sift-rt").click(function () {           //清除设备类型筛选
        EMDR.equalBy.deviceType_id = null;
        queryForDevice(EMDR, $("#EMList"));
    });
    $("#EM .sift-ra").click(function () {           //清除设备地区筛选
        EMDR.equalBy.urbanArea_id = null;
        queryForDevice(EMDR, $("#EMList"));
    });

});

