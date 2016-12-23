$(function() {

    $.ajax({
        url: null,
        type: "get",
        success: function(result) {

            // result=['paperId','paperName','学生id','学生名','试卷内容'];
            // 试卷内容data是个数组，每一项包括了问题id,类型和问题
            // data[i][0]=qusetionid;
            // data[i][1]=qusetionType;
            // 比如：选择题 type1;填空题 2 type2
            // data[i][2]=question内容

            var $template = $('#student_info')[0].innerHTML.trim();
            $template = $template.replace('{PAPERID}', result[0])
                .replace('{PAPERNAME}', result[1]).
            replace('{STUDENTID}', result[2]).
            replace('{STUDENTNAME}', result[3]);
            $('#student_info').html($template);

            var data = result[4];
            // data
            for (var i = 0; i < data.length; i++) {
                insert(data[i]);
            }
        }
    });

});

$(function() {
    var theCounter = new Counter("#timer", 2 * 1000, 0);
    // theCounter.countDown();
});

// To-do:
function insertType(data) {

    var questionid = data[0];
    var questiontype = data[1].match(/[0-9]+/g)[0];
    var questiontitle = data[2];
    var template = $("#template" + questiontype)[0].innerHTML.trim();

    if (questiontype == '1') {
        //插入选择题
        var e = questiontitle.split('#');
        var q1 = e[0],
            q2 = e[1],
            q3 = e[2],
            q4 = e[3],
            q5 = e[4];
        template = template.replace(/{QUESTIONTITLE}/g, q1).
        replace('{QUESTIONCONTENT1}', q1).replace('{QUESTIONCONTENT1}', q2).replace('{QUESTIONCONTENT1}', q3).replace('{QUESTIONCONTENT1}', q4).replace(/{QUESTIONID}/g, questionid);
        $('#' + questiontype).append(template);
    } else {
        template = template.replace(/{QUESTIONTITLE}/g, questiontitle).replace(/{QUESTIONID}/g, questionid);
        $('#' + questiontype).append(template);
    }

}





// 计时器
function Counter(id, start, end) {
    this.id = id;
    this.current = start;
    this.end = end;
    this.countDown = function() {
        if (this.current < this.end) {

            this.countDown = null;

            //提交试卷
            var paperId = $(".paper_id").attr("id");
            var studentId = $(".student_id").attr("id");
            var answer = {};
            var answer1 = $("input").serialize();
            var answer2 = $("textarea").serialize();
            answer.push(answer1);
            answer.push(answer2);
            $.ajax({
                type: "post",
                url: null,
                data: { paper_id: paperId, student_id: studentId, answer: answer },
                success: function(data) {
                    alertMsg("success", "考试结束，客观题成绩为" + data);
                    window.history.back(-1);
                }

            })

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
    console.log(i);
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
