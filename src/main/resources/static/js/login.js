$("input[type='text']").keyup(function(){
    var pattern=/\d{12}/;
    if(pattern.test($(this).val())){
        $(this).siblings(".icon2").css("display","inline-block");
        $(this).siblings(".icon1").css("display","none");
    }
    if($(this).val()===""||!pattern.test($(this).val())){
        $(this).siblings(".icon2").css("display","none");
        $(this).siblings(".icon1").css("display","inline-block");
    }
});

$("input[type='password']").keyup(function(){
    var pattern=/[0-9a-zA-Z]{6,}/;
    if(pattern.test($(this).val())){
        $(this).siblings(".icon2").css("display","inline-block");
        $(this).siblings(".icon1").css("display","none");
    }
    if($(this).val()===""||!pattern.test($(this).val())){
        $(this).siblings(".icon2").css("display","none");
        $(this).siblings(".icon1").css("display","inline-block");
    }
});
$("#btn_submit").click(function (event) {
    event.preventDefault();
    var data = new Object();
    data.username = $('#username').val();
    data.password = $('#password').val();
    data.flag=1;
    if(data.username == "") {
        layer.tips('用户名不能为空','#username');
        return;
    }
    if(data.password == "") {
        layer.tips('密码不能为空','#password');
        return;
    }
    $.ajax({
        type: "POST",
        url: "/toLogin",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success:function (data) {
            alert(data);
            location.href=data;
        },
        error: function (XMLHttpRequest, status, errorThrown) {
            alert(status + " " + errorThrown);
        }
    })
});
