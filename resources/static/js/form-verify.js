$(document).ready(function () {

    $("form :input").blur(function () {
        var input = $(this).val();
        var $parent = $(this).parent();
        var $msg = $parent.contents(".l2");
        var id = $parent.attr('id');

        $msg.css({"color":"crimson"});

        if(id==="count"){  //表单处理 count
            if(input.indexOf(" ") >= 0){
                $msg.text("不能包含空格");
            }
            else if(input.length < 8){
                $msg.text("长度必须大于7个字符");
            }
            else if(input.length > 20){
                $msg.text("长度不能超出20个字符");
            }
            else{
                $msg.css({"background":"transparent", "color":"forestgreen"});
                $msg.text("√");
            }
        }
        else if(id==="password"){//表单处理 password
            if(input.indexOf(" ") >= 0){
                $msg.text("不能包含空格");
            }
            else if(input.length < 8){
                $msg.text("长度必须大于7个字符");
            }
            else if(input.length > 20){
                $msg.text("长度不能超出20个字符");
            }
            else{
                $msg.css({"color":"forestgreen"});
                $msg.text("√");
            }
        }
        else if(id==="name"){  //表单处理 name
            input = $.trim(input);
            if(input.length < 2){
                $msg.text("长度必须大于1个字符");
            }
            else if(input.length > 20){
                $msg.text("长度不能超出18个字符");
            }
            else{
                $msg.css({"color":"forestgreen"});
                $msg.text("√");
            }
        }
        else if(id==="contact"){ //表单处理 contact
            if(input.length < 6){
                $msg.text("长度必须大于6个字符");
            }
            else if(input.length > 20){
                $msg.text("长度不能超出20个字符");
            }
            else{
                $msg.css({"color":"forestgreen"});
                $msg.text("√");
            }
        }
        else if(id==="key"){ //表单处理 key
            if(input.length > 20){
                $msg.text("长度不能超出20个字符");
            }
            else{
                $msg.css({"color":"forestgreen"});
                $msg.text("√");
            }
        }
        else if(id==="desc"){//表单处理 desc
            if(input.length > 20){
                $msg.text("长度不能超出40个字");
            }
            else{
                $msg.css({"color":"forestgreen"});
                $msg.text("√");
            }
        }
        else if(id==="agree"){//checkbox
            if($("#check[type='checkbox']").is(':checked')==false){
                $msg.text("需要勾选");
            }
            else{
                $msg.text("");
            }
        }
    }).keyup(function () {
        $(this).triggerHandler("blur");
    });

    //------表单处理  END------

    /*提交前验证*/
    $("#send").click(function () {
        $("form :input").trigger("blur");
        var msgs = $(".indiv").find(".l2");

        if(msgs.text().length>7){
            return false;
        }
    });
});