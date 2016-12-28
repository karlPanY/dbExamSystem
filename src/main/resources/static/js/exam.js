$(function() {

    $.ajax({
        url: "/student/getPaper",
        type: "get",
        cache:false,
        dataType: 'json',
        success: function(result) {
            var paperId = result["paper_id"];
            var paperName = result["paper_name"];
            var studentId = result["student_id"];
            var studentName = result["student_name"];
            var datas = result["datas"];

            var paperTime = result["exam_second"];


            var $template = $('#student_info')[0].innerHTML.trim();
            $template = $template.replace('{PAPERID}', paperId)
                .replace('{PAPERNAME}', paperName).
            replace('{STUDENTID}', studentId).
            replace('{STUDENTNAME}', studentName);
            $('#student_info').html($template);


            // data
            for (var i = 0; i < datas.length; i++) {
                insertType(datas[i]);
            }
            //计时器
            var theCounter = new Counter("#timer", paperTime, 0);
            theCounter.countDown();

        }
    }); //ajax end
});
$(function() {

    //滚动效果
    scroll();
    $('#submit').bind('click', function() {
        submitPaper();
    });
});

// 插入题目
// 插入题目
function insertType(question) {

    var questionId = question["question_id"];
    var questionTitle = question["question_title"];
    var questionType = question["question_type"];
    var questiontypeId, questiontypeIndex = 1;
    switch (questionType) {
        case "选择题":
            questiontypeId = "#type1";
            questiontypeIndex = '1';
            break;
        case "判断题":
            questiontypeId = "#type2";
            questiontypeIndex = '2';
            break;
        case "填空题":
            questiontypeId = "#type3";
            questiontypeIndex = '3';
            break;
        case "简答题":
            questiontypeId = "#type4";
            questiontypeIndex = '4';
            break;
        default:
            questiontypeId = null;
            break;
    }

    var template = $("#template" + questiontypeIndex)[0].innerHTML.trim();

    if (questionType == '选择题') {
        //插入选择题
        var e = questionTitle.split('#');
        var q1 = e[0],
            q2 = e[1],
            q3 = e[2],
            q4 = e[3],
            q5 = e[4];
        template = template.replace(/{QUESTIONTITLE}/g, q1).replace('{QUESTIONCONTENT1}', q2).replace('{QUESTIONCONTENT2}', q3).replace('{QUESTIONCONTENT3}', q4).replace('{QUESTIONCONTENT4}', q5).replace(/{QUESTIONID}/g, questionId);
        $(questiontypeId).append(template);
    } else {
        template = template.replace(/{QUESTIONTITLE}/g, questionTitle).replace(/{QUESTIONID}/g, questionId);
        $(questiontypeId).append(template);
    }

}



function submitPaper() {

    //提交试卷
    var paperId = $(".paper_id").attr("id");
    var studentId = $(".student_id").attr("id");
    var answers = [];

    var checkedInputs = $('#exam_body input:checked');
    for (var i = 0; i < checkedInputs.length; i++) {
        var answer = { "answer_id": checkedInputs[i].name, "answer_text": $(checkedInputs[i]).val() };
        answers.push(answer);
    }
    var textareas = $('#exam_body textarea');
    for (var i = 0; i < textareas.length; i++) {
        var answer = { "answer_id": textareas[i].name, "answer_text": $(textareas[i]).val() };
        answers.push(answer);
    }
    var postdata = { paper_id: paperId, student_id: studentId, answer: answers };
    postdata = JSON.stringify(postdata);
    console.log(postdata);
    $.ajax({
        type: "post",
        url:"/student/upload",
        data: postdata,
        contentType: "application/json;charset=utf-8",
        success: function(data) {
            alertMsg("success", "考试结束，客观题成绩为" + data);
            window.history.back(-1);
        }
    })

}





// 计时器
function Counter(id, start, end) {
    this.id = id;
    this.current = start;
    this.end = end;
    this.countDown = function() {
        if (this.current < this.end) {

            this.countDown = null;

            submitPaper();

        } else {
            var hour = parseInt(this.current / 1000 / 60 / 60);
            var min = parseInt(this.current / 1000 % (60 * 60) / 60);
            var sec = parseInt(this.current / 1000 % (60 * 60) % 60);
            $(this.id).html(checkTime(hour) + ":" + checkTime(min) + ":" + checkTime(sec));
            this.current = this.current - 1000;
            setTimeout(bind(this, this.countDown), 1000);
        }
    };
    return this;
}
//为什么 要这样绑定！！ 底层js！！！
function bind(obj, method) {
    return function(event) {
        method.call(obj, event || window.event);
    }
}

function checkTime(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

function alertMsg(status, msg) {
    var $alertDiv = $("<div class='alert'></div>");
    var $alertInfo;
    if (status === "success") {
        $alertInfo = $("<div class='alert-content alert-success'>" +
            "<a href='#' class='close' data-dismiss='alert'>&times;</a><strong>" + msg + "</strong></div>");
    } else {
        $alertInfo = $("<div class='alert alert-warning'>" +
            "<a href='#' class='close' data-dismiss='alert'>&times;</a><strong>" + msg + "</strong></div>");
    }
    $alertDiv.append($alertInfo)
    $('body').append($alertDiv);
    $(".alert").hide().fadeIn(200)
}

$(function() {
    $(".close").click(function() {
        $('.alert').alert();
    });
});

function scroll() {

    $('.scroll').click(function(event) {
        event.preventDefault();
        $('html,body').animate({
            scrollTop: $(this.hash).offset().top - 200
        }, 500);
        var node = event.target;
        if (node.nodeName !== 'A') {
            node = $(node).parent('a');
        }
        var index = $(node).data('target').split('_')[1];
        var $top = Number($('.mouse').css('top').split('px')[0]);
        $top = (index - 5) * 52;
        $('.mouse').css('top', $top + 'px');
    });

    $(window).scroll(function() {
        var scrollTop = $(this).scrollTop();
        if (scrollTop > 600)
            $('#back-to-top').css('display', 'block');
        else {
            $('#back-to-top').css('display', 'none');
        }
    });
    $('#back-to-top').click(function(event) {
        event.preventDefault();
        $('html,body').animate({
            scrollTop: 0
        }, 700);
    });
}
