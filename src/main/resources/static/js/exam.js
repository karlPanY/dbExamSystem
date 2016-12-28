$(function() {

    $.ajax({
        url: null,
        type: "get",
        success: function(result) {

            var $template = $('#student_info')[0].innerHTML.trim();
            $template = $template.replace('{PAPERID}', result[0])
                .replace('{PAPERNAME}', result[1]).
            replace('{STUDENTID}', result[2]).
            replace('{STUDENTNAME}', result[3]);
            $('#student_info').html($template);

            var datas = result[4];
            // data
            for (var i = 0; i < datas.length; i++) {
                insertType(datas[i]);
            }

        },
        error: function() {
            //test
            var result = ['试卷id', '数据库考试', '学生id', '廖晓娟', [
                ['qusetionid1', '选择题', '题目#选项1#选项2#选项3#选项4'],
                ['qusetionid2', '选择题', '题目#选项1#选项2#选项3#选项4'],
                ['qusetionid3', '填空题', '题目'],
                ['qusetionid4', '填空题', '题目'],
                ['qusetionid5', '简单题', '题目']
            ]];

            var $template = $('#student_info')[0].innerHTML.trim();
            $template = $template.replace('{PAPERID}', result[0])
                .replace('{PAPERNAME}', result[1]).
            replace('{STUDENTID}', result[2]).
            replace('{STUDENTNAME}', result[3]);
            $('#student_info').html($template);

            var datas = result[4];
            // data
            for (var i = 0; i < datas.length; i++) {
                insertType(datas[i]);
            }
        }
    }); //ajax end

});
$(function() {
    //计时器
    var theCounter = new Counter("#timer", 5 * 1000, 0);
    theCounter.countDown();

    //滚动效果
    scroll();
    $('#submit').bind('click', function() {
        submitPaper();
    });
});

// 插入题目
function insertType(data) {

    var questionid = data[0];
    var questiontype = data[1];
    var questiontypeId, questiontypeIndex = 1;
    switch (questiontype) {
        case "选择题":
            questiontypeId = "#type1";
            questiontypeIndex = '1';
            break;
        case "判断题":
            questiontypeId = "#type4";
            questiontypeIndex = '4';
            break;
        case "填空题":
            questiontypeId = "#type2";
            questiontypeIndex = '2';
            break;
        case "简答题":
            questiontypeId = "#type3";
            questiontypeIndex = '3';
            break;
        default:
            questiontypeId = null;
            break;
    }
    var questiontitle = data[2];
    var template = $("#template" + questiontypeIndex)[0].innerHTML.trim();

    if (questiontype == '选择题') {
        //插入选择题
        var e = questiontitle.split('#');
        var q1 = e[0],
            q2 = e[1],
            q3 = e[2],
            q4 = e[3],
            q5 = e[4];
        template = template.replace(/{QUESTIONTITLE}/g, q1).replace('{QUESTIONCONTENT1}', q2).replace('{QUESTIONCONTENT2}', q3).replace('{QUESTIONCONTENT3}', q4).replace('{QUESTIONCONTENT4}', q5).replace(/{QUESTIONID}/g, questionid);
        $(questiontypeId).append(template);
    } else {
        template = template.replace(/{QUESTIONTITLE}/g, questiontitle).replace(/{QUESTIONID}/g, questionid);
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
        answers.push([checkedInputs[i].name, $(checkedInputs[i]).val()]);
    }
    var textareas = $('#exam_body textarea');
    for (var i = 0; i < textareas.length; i++) {
        answers.push([textareas[i].name, $(textareas[i]).val()]);
    }
    var postdata = { paper_id: paperId, student_id: studentId, answer: answers };
    postdata = JSON.stringify(postdata);
    $.ajax({
        type: "post",
        url: null,
        data: postdata,
        contentType: "application/json;charset=utf-8",
        success: function(data) {
            alertMsg("success", "考试结束，客观题成绩为" + data);
            window.history.back(-1);
        },
        error: function() {
            //test
            console.log('测试提交数据' + postdata)
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
        $top = (index - 4) * 52;
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
