var index="/index.html";

/**全局的ajax访问设置，处理ajax请求时sesion超时或未登录*/
$.ajaxSetup({
    contentType : "application/x-www-form-urlencoded;charset=utf-8",
    complete : function(XMLHttpRequest, textStatus) {
        var SessionStatus = XMLHttpRequest.getResponseHeader("SessionStatus"); // 通过XMLHttpRequest取得响应头，sessionstatus，
        if (SessionStatus === "timeout") {
            if(login == true) {
                // 如果超时就处理 ，指定要跳转的页面
                alert("超时未动作，请重新登录。");
                window.location.replace("/login.html");
            }
            else {
                //还未登录，不能请求数据
                alert("请先登录!");
                window.location.replace(index);
            }
        }
    },
    beforeSend: function() {
        if(arguments[1].url != getThisUser && login==false){
            alert("请先登录！");
            window.location.replace(index);
            return false;
        }
    }
});

/**登陆控制， 没有登录的情况下，服务器会返回0，当其试图访问数据时引导其登录*/
var login = false;          //全局变量，便于浏览器判断是否已登陆
var UserId = 0;
var getThisUser = "/getThisUser";
$(document).ready(function () {
    $.getJSON(getThisUser,function (data) {
        var id = parseInt(JSON.stringify(data));
        if(id != 0 && id != null){
            login=true;     //取得id，登录成功
            UserId=id;
            /***页面右上角的用户信息显示***/
            $.getJSON("/getUserInfo/"+id,function (user) {
                $("#dropdownMenu1").attr("data-toggle", "dropdown");  //登录后加入下拉菜单

                $("#dropdownMenu1").addClass("btn");                  //登录后增加下拉项的按钮效果
                $("#dropdownMenu1").text(user.name+" - "+user.userGroup.userGroupName+" ").append('<span class="caret"></span>');
            });

            /** 刷新数据页 */
            $(".sift-rt").trigger("click");
        }
    })
});
/**登陆控制END*/


/****** 用户退出 ******/
$(document).ready(function () {
    $("#exit").click(function () {              //按下退出按钮
        $.get("/userExit",function (data) {
            window.location.replace(index);
        })
    });
});


//左导航的jQuery
//运行状态查询的筛选导航
$(document).ready(function () {
    $("#OS .sift-t").click(function () {
        $("#OS .type").addClass("active");
        $("#OS .type").text($(this).text());
    });
    $("#OS .sift-a").click(function () {
        $("#OS .area").addClass("active");
        $("#OS .area").text($(this).text());
    });
    $("#OS .sift-rt").click(function () {
        $("#OS .type").removeClass("active");
        $("#OS .type").text('');
    });
    $("#OS .sift-ra").click(function () {
        $("#OS .area").removeClass("active");
        $("#OS .area").text('');
    });
});

//设备管理查询的筛选导航
$(document).ready(function () {
    $("#EM .sift-t").click(function () {
        $("#EM .type").addClass("active");
        $("#EM .type").text($(this).text());
    });
    $("#EM .sift-a").click(function () {
        $("#EM .area").addClass("active");
        $("#EM .area").text($(this).text());
    });
    $("#EM .sift-rt").click(function () {
        $("#EM .type").removeClass("active");
        $("#EM .type").text('');
    });
    $("#EM .sift-ra").click(function () {
        $("#EM .area").removeClass("active");
        $("#EM .area").text('');
    });
});

//日志查询的筛选导航
$(document).ready(function () {
    $("#JN .sift-t").click(function () {
        $("#JN .type").addClass("active");
        $("#JN .type").text($(this).text());
    });
    $("#JN .sift-a").click(function () {
        $("#JN .area").addClass("active");
        $("#JN .area").text($(this).text());
    });
    $("#JN .sift-rt").click(function () {
        $("#JN .type").removeClass("active");
        $("#JN .type").text('');
    });
    $("#JN .sift-ra").click(function () {
        $("#JN .area").removeClass("active");
        $("#JN .area").text('');
    });
});

//用户查询的筛选导航
$(document).ready(function () {
    $("#UM .sift-t").click(function () {
        $("#UM .type").addClass("active");
        $("#UM .type").text($(this).text());
    });
    $("#UM .sift-a").click(function () {
        $("#UM .area").addClass("active");
        $("#UM .area").text($(this).text());
    });
    $("#UM .sift-rt").click(function () {
        $("#UM .type").removeClass("active");
        $("#UM .type").text('');
    });
    $("#UM .sift-ra").click(function () {
        $("#UM .area").removeClass("active");
        $("#UM .area").text('');
    });
});


/**
 * ********隐藏和显示面板
 **/
$(document).ready(function () {

    $(".my-panel").not(".panel-show").hide();

    $(".panel-chooser").click(function(){
        var pid = "#" + $(this).attr("pid");                //取出被点击的panel id

        $(".panel-chooser").removeClass("active1");
        $(this).addClass("active1");

        $(".my-panel").removeClass("panel-show").hide();    //先移除所有panel的panel-show
        $(pid).addClass("panel-show");                      //仅给pid加上panel-show
        $(".panel-show").show();                            //show
    });

});

var toOI="";
/*点击实时信息，进入到实时运行数据界面*/
$(document).ready(function () {
    $(".to-OI").click(function () {
        toOI="I";
    });
});



//

