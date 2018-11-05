//左导航的jQuery
$(document).ready(function () {
    $("#sbzl").click(function () {
        $("#area").hide();
        $("#type").addClass("active");
        $("#type").text("全部");
    });
});

$(document).ready(function () {
    $("#byq").click(function () {
        $("#area").hide();
        $("#type").addClass("active");
        $("#type").text($("#byq").text());
    });
});

$(document).ready(function () {
    $("#gfnbq").click(function () {
        $("#area").hide();
        $("#type").addClass("active");
        $("#type").text("光伏逆变器");
    });
});

$(document).ready(function () {
    $("#fz").click(function () {
        $("#area").hide();
        $("#type").addClass("active");
        $("#type").text("负载");
    });
});

$(document).ready(function () {
    $("#dnzl").click(function () {
        $("#area").hide();
        $("#type").addClass("active");
        $("#type").text("电能质量");
    });
});

$(document).ready(function () {
    $("#kgsb").click(function () {
        $("#area").hide();
        $("#type").addClass("active");
        $("#type").text("开关设备");
    });
});


//左地区的jQuery
$(document).ready(function () {
    $("#dq").click(function () {
        $("#area").show();
        $("#area").addClass("active");
        $("#area").text("长沙市全部");
    });
});

$(document).ready(function () {
    $("#ylq").click(function () {
        $("#area").show();
        $("#area").addClass("active");
        $("#area").text("长沙市岳麓区");
    });
});

$(document).ready(function () {
    $("#txq").click(function () {
        $("#area").show();
        $("#area").addClass("active");
        $("#area").text("长沙市天心区");
    });
});

$(document).ready(function () {
    $("#yhq").click(function () {
        $("#area").show();
        $("#area").addClass("active");
        $("#area").text("长沙市雨花区");
    });
});

$(document).ready(function () {
    $("#wcq").click(function () {
        $("#area").show();
        $("#area").addClass("active");
        $("#area").text("长沙市望城区");
    });
});

$(document).ready(function () {
    $("#kfq").click(function () {
        $("#area").show();
        $("#area").addClass("active");
        $("#area").text("长沙市开福区");
    });
});

$(document).ready(function () {
    $("#csx").click(function () {
        $("#area").show();
        $("#area").addClass("active");
        $("#area").text("长沙市长沙县");
    });
});












