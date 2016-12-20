$(function() {
    $.ajax({
        url: null,
        type: "get",
        success: function(data) {

            // data是数组，所有有待考试的试卷信息，每一项是一个对象
            // data[i].paper_id;data[i].paper_name;data[i].paper_start;data[i].paper_end;

            $(".paper_num").html(data.length);
            for (var i = 0; i < data.length; i++) {
                if (i !== 0) {　
                    var paperinfo = $(".paper-info").clone();
                    $(".log-test").append(paperinfo);
                }
                var panel = $(".paper-info")[i];
                var paperId = data.paper_id;
                var paperName = data.paper_name;
                var paperStart = data.paper_start;
                var paperEnd = data.paper_end;

                panel.find("b").html("试卷信息" + (i + 1));
                panel.find(".paper-name").html(paperName);
                panel.attr("id", "paperinfo-" + (i + 1));
                panel.find("ok-btn").attr("id", paperId);
                panel.find(".paper-start", paperStart);
                panel.find(".paper-end", paperEnd);
                // new Date("11/17/2016 23:30:18").getTime()
                if (new Date(paperStart).getTime() - Date.now()) {
                    panel.find(".ok-btn").css("display", "block");
                    panel.find(".exam-status").html("考试开始");
                } else {
                    panel.find(".ok-btn").css("display", "none");
                    panel.find(".exam-status").html("等待考试");
                }
            }
        }
    })
});


$(".next-btn").bind("click", function() {
    var paper_num = $(".paper-info").length;
    var paper_current = $($(this).parents(".paper-info")[0]).attr("id").split("-")[1];
    $(".paper-info").css("display", "none");
    var paper_show = $("#paperinfo-" + (Number(paper_current) + 1));
    paper_show.css("display", "block");
    if ((Number(paper_current) + 1) == paper_num) {
        paper_show.find(".next-btn").css("display", "none");
    } else {
        paper_show.find(".next-btn").css("display", "block");
    }
    paper_show.find(".prev-btn").css("display", "block");


});
$(".prev-btn").bind("click", function() {
    var paper_num = $(".paper-info").length;
    var paper_current = $($(this).parents(".paper-info")[0]).attr("id").split("-")[1];
    $(".paper-info").css("display", "none");
    var paper_show = $("#paperinfo-" + (Number(paper_current) - 1));
    paper_show.css("display", "block");
    if ((Number(paper_current) - 1) == 1) {
        paper_show.find(".prev-btn").css("display", "none");
    } else {
        paper_show.find(".prev-btn").css("display", "block");
    }
    paper_show.find(".next-btn").css("display", "block");


});


$(".ok-btn").bind("click", function() {
    var paperId = $(this).attr("id");
    var studentId = $(".student-info").find("b").attr("id");
    $.ajax({
        url: null,
        type: "post",
        data: { "paper_id": paperId, "student_id": studentId }
    })
})
