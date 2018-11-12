var getUserUrl = "/getUsers";

function queryForUser(DR, list){
    list.contents().remove();

    $.post(getUserUrl,{DynamicRequest:JSON.stringify(DR)}, function (response) {

        var dataList = response.responseData;
        $.each(dataList, function (index, user) {

            var appendTable = "<tr>" +
                "<td>" + user.id+"</td>" +
                "<td>" + user.name+"</td>" +
                "<td>" + user.count+"</td>" +
                "<td>" + user.contactInformation+"</td>" +
                "<td>" + user.userGroup.userGroupName+"</td>";

            appendTable=appendTable+'<td><a class="to-UI" href="" onclick="return false" uid="' + user.id + '">详细信息</a></td>' +        //uid--用户ID
                    "</tr>";

            list.append(appendTable);
        });

    });
}

var UMDR = {equalBy:{userGroup_id:null}, page:0, pageSize:10};
$(document).ready(function () {

    //queryForUser(UMDR, $("#UMList"));

    $("#UM .sift-t").click(function () {            //用户组筛选
        UMDR.equalBy.userGroup_id = parseInt($(this).attr("tid"));
        queryForUser(UMDR, $("#UMList"));
    });

    $("#UM .sift-rt").click(function () {           //清除用户组筛选
        UMDR.equalBy.userGroup_id = null;
        queryForUser(UMDR, $("#UMList"));
    });


});