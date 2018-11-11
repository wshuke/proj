var getFaultUrl = "/getFaultLogNotClear";

//各种故障的icon，其下标就对应着故障id
icon = ["glyphicon-fire", "glyphicon-flash", "glyphicon-flash", "glyphicon-fire", "glyphicon-equalizer", "glyphicon-equalizer", "glyphicon-equalizer"];

function queryForFault(list){
    list.contents().remove();
    $.getJSON(getFaultUrl, function (faults) {

        $.each(faults, function (index, fault) {

            var appendBtn = '<button data-toggle="collapse" data-parent="#accordion" href="#collapse1" class="btn btn-danger b3" fid="' + fault.id + '">' + //fid为该故障日志的id
                "长沙市" + fault.device.urbanArea.name + fault.device.id + "号" + fault.device.deviceType.name + fault.faultType.name +
                '<span class="glyphicon ' +icon[fault.faultType.id-1]+ '"></span>'
                "</button>";

            list.append(appendBtn);
        });

    });
}
var interval = null;
$(document).ready(function () {

    $(".WI-panel").click(function () {
        if(interval == null)
            interval = setInterval(queryForFault($("#WIList")), 5000);
    });

});