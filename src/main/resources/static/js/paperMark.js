$(function() {
    var rowNavs_li = $(".rowNav li");
    for (var i = 0; i < 3; i++) {
        $(rowNavs_li[i]).before().css("background", "url('../images/score1.ico') no-repeat left center")
    }

    $.ajax({
        url:url,
        type:"get",
        success:function(data){
           processResponseText(data.questions);
           $(".form").attr("id",data.sno);
    }});
               
    $("#btn_before").bind("click", addScore);
    // 请求下一份试卷
    $("#btn_next").bind("click",function(){
        $.ajax({url:url,success:function(data){
        processResponseText(data.questions);
          $(".form").attr("id",data.sno);
    }});
    })

})
function processResponseText(questions){
      var $types = {};
        for (var i = 0, len = questions.length; i < len; i++) {

            if (!$types.hasOwnProperty(questions[i][0])) {
                $types[questions[i][0]] = $("<div id='" + questions[i][0] + "'' class='content'><h3>" + questions[i][0] + "</h3></div>");
            }
            var $div = addQuestion(questions[i][1], questions[i][2]);
            $types[questions[i][0]].append($div);
        }
        $(".form").remove();
        for (var j in $types) {
            $(".form").append($types[j])
        }        //封装收到的答案 end
}
function addQuestion(qNo, qAnswer) {   
    var $div = $("<div class='question'><h4>" + qNo + "<span>Answer:</span></h4><div>" + qAnswer + "</div></div>");
    var $input = $("<span>打分:<input  size='5' type='text' name='" +qNo + "'/></span>");
    $div.append($input);
    return $div;
}



function addScore() {
    var form = document.getElementById('form');

    var inputs = form.getElementsByTagName('input');
    var sum = 0;
    for (var i = 0; i < inputs.length; i++) {
        if(inputs[i].value!==""){
            sum += parseInt(inputs[i].value); 
        }     
    }  
    var btn1 = "<button id='btn_cancle' class='btn btn-danger'>取消</button>";
    var btn2 = "<button id='btn_sure' class='btn btn-success'>确定</button>";
    alertMsg("success", "<div>客观题 评分为<b>" + sum + "</b></div><div>" + btn1 + btn2 + "</div>");


    $("#btn_cancle").bind("click", function() {
        $('.alert').remove();
    });
    $("#btn_sure").bind("click", function() {
     // 传送结果给后台
        var contents=$("#paperS").find(".content");
            var results={};
            contents.each(function(index,item){         
                var result=$(item).find("input:text").serialize();           
                 results["'"+id+"'"]=result;          
            });
        $.ajax({
            url:null,
            type:"post",
            data:{sno:$(".form").attr("id"),results:results},
            success:function(data){
                $("input:text").disabled=true;
            }
        });
        $('.alert').remove();
    });
}
