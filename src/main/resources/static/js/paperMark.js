var currentPaperId = null; //全局变量
$(function() {
    //初始化
    init();
    //getAnswerPaperByStuId();
    // 请求对应试题 学生答卷
    $("#paper_list a").bind("click", function() {
        $("#paper_list a").removeClass('active');
        $(this).addClass('active');
        var paper_id = $(this).attr("id");
        currentPaperId = paper_id;
        // 获取试题
        getstudentidList(currentPaperId);
    });

});

function init() {
    // / 请求初始话paper数据 不需要参数
    $.ajax({
        url: "/getMarkPapers",
        type: "get",
        success: function(result) {
            // var result = ['登录教师id', '登录教师名', 'paperList内容'];
            var $temp = $('#teacher_info')[0].innerHTML.trim();
            $temp = $temp.replace('{TEACHERID}', result.teacherId)
                .replace('{TEACHERNAME}', result.teacherName);
            $('#teacher_info').html($temp);

            var $ul = $("ul#paper_list");
            $ul.html('');
            var paperList = result.papersMarkInfoList;
            for (var i = 0; i < paperList.length; i++) {
                var $template = $('#paperListTemplate')[0].innerHTML;
                var paperName = paperList[i]['paper_name'];
                var paperId = paperList[i]['paper_id'];
                $template = $template.replace('{PAPERID}', paperId).replace('{PAPERNAME}', paperName);
                $ul.append($template);
            }
        },
        error: function() {
            //test
            var paperList = [{
                paper_id: "paper_id1",
                paper_name: "数据库试题1"
            }, {
                paper_id: "paper_id2",
                paper_name: "数据库试题2"
            }];
            var result = ['登录教师id', '登录教师名', paperList];
            var $temp = $('#teacher_info')[0].innerHTML.trim();
            $temp = $temp.replace('{TEACHERID}', result[0])
                .replace('{TEACHERNAME}', result[1]);
            $('#teacher_info').html($temp);
            paperList = result[2];

            var $ul = $("ul#paper_list");
            for (var i = 0; i < paperList.length; i++) {
                var $template = $('#paperListTemplate')[0].innerHTML;
                var paperName = paperList[i].paper_name;
                var paperId = paperList[i].paper_id;
                $template = $template.replace('{PAPERID}', paperId).replace('{PAPERNAME}', paperName);
                $ul.append($template);
            }
        }
    });
}


function getstudentidList(paperId) {
    console.log(paperId);
    $.ajax({
        url: "/getstudentIdListForMark/" + paperId,
        type: 'get',
        success: function(result) {
            var studentidList = result["student_id_list"];
            var $ul = $("ul#studentidList");
            $ul.html('');
            for (var i = 0; i < studentidList.length; i++) {
                var $template = $('#stuListTemplate')[0].innerHTML;
                var studentId = studentidList[i]["student_id"];
                var studentName = studentidList[i]["student_name"];
                $template = $template.replace('{STUDENTID}', studentId).replace('{STUDENTNAME}', studentName);
                $ul.append($template);
            }
        },
        error: function() {
            var studentidList = [
                { "student_id": 102430560243, "student_name": "小白" }, { "student_id": 102430560241, "student_name": "小黑" }, { "student_id": 102430560247, "student_name": "小红" }
            ];
            var $ul = $("ul#studentidList");
            $ul.html('');
            for (var i = 0; i < studentidList.length; i++) {
                var $template = $('#stuListTemplate')[0].innerHTML;
                var studentId = studentidList[i]["student_id"];
                var studentName = studentidList[i]["student_name"];
                $template = $template.replace('{STUDENTID}', studentId).replace('{STUDENTNAME}', studentName);
                $ul.append($template);
            }
        }
    })

}


function getAnswerPaperByStuId(target) {
    var studentId = $(event.target).attr('id');
    $('a.studentItem').removeClass('active');
    $(event.target).addClass('active');
    console.log([currentPaperId, studentId]);
    currentPaperId=1;
    studentId=201430561000;
    $.ajax({
        url: '/getMarkingPaper/' + currentPaperId + '/' + studentId,
        type: 'get',
        success: function(result) {
            var num = result[0];
            var data = result[1];
            render(data.student_id, data.student_name, data.student_paper, data.student_score);
        },
        error: function() {
            var data = {
                "student_id": '201430560243',
                "student_name": '廖晓娟',
                "student_paper": [
                    ['题目1-id', '题目1', '答案1', '5'],
                    ['题目2-id', '题目2', '答案2', '5']
                ],
                "student_score": 0
            };
            var result = [1, data];
            var num = result[0];
            var data = result[1];
            render(data.student_id, data.student_name, data.student_paper, data.student_score);
        }
    });
}

function render(student_id, student_name, paper, score) {

    $('#student_paper').html('');
    var $template1 = $("#template_info")[0].innerHTML.trim();
    var $template2;
    $template1 = $template1.replace('{STUDENTSCORE}', score);
    if (score > 0) {
        $('#student_paper button').attr('disabled', 'true');
        $('.question_score').attr('disabled', 'true');
    }
    $('#student_paper').append($template1);
    for (var i = 0, len = paper.length; i < len; i++) {
        $template2 = $("#template_question")[0].innerHTML.trim();
        var question = paper[i];
        $template2 = $template2.replace('{QUESTIONTITLE}', question[1]).replace('{QUESTIONANSWER}', question[2]).replace(/{QUESTIONSCORE}/g, question[3]).replace(/{QUESTIONID}/g, question[0]);
        $('#student_paper').append($template2);
    }
    //试卷加载完毕后，绑定按钮事件
    addTotalScore();
    $("#btn-submitScore").bind('click', function() {
        submitScore(student_id);
    });
}


// 处理评分数据
function addTotalScore() {
    $('.btn-score').bind('click', function(event) {
        var totalScore = Number($('#sum-score').html());
        var target = $(event.target).data('target');
        var max = Number($(target).data('max'));
        var score = $(target).val();
        if (!/\d+/.test(score)) {
            alertMsg('Warning', '请给一个合法的分数');
        } else if (Number(score) > max) {
            alertMsg('Warning', '评分不可以超过这道题的分值哦');
        } else {
            totalScore += Number(score);
            $('#sum-score').html(totalScore);
            $(target).attr('disabled', 'true');
            $(event.target).attr('disabled', 'true');
        }
    });
    $('.btn-modify-score').bind('click', function(target) {
        var target = $(event.target).data('target');
        $(target).get(0).removeAttribute('disabled')
        $(event.target).siblings('button').get(0).removeAttribute('disabled')
        var score = Number($(target).val());
        var totalScore = Number($('#sum-score').html());
        $('#sum-score').html((totalScore - score));
        $(target).val('');
    })

}

function submitScore(student_id) {
    var paper_id = currentPaperId;
    var totalScore = $('#sum-score').html();
    var postdata = { 'paper_id': paper_id, 'student_id': student_id, 'score': totalScore };
    $.ajax({
        url: '提交评改成绩url',
        type: 'post',
        data: JSON.stringify(postdata),
        success: function(msg) {
            if (msg.success) {
                alertMsg('info', "评分完成，请选择下一份试卷~~~~");
                $("#btn-submitScore").attr('disabled', 'true')
            } else {
                //提示错误？？
            }
        },
        error: function() {
            console.log('测试提交数据' + JSON.stringify(postdata));
            $("#btn-submitScore").attr('disabled', 'true');
        }
    })
}


function alertMsg(
    title, msg) {
    var $template = $('#alert-template')[0].innerHTML;
    var type;
    if (title == 'Warning') { type = 'danger' } else if (title == 'Info') { type = 'warning' };
    $template = $template.replace('{ALERTTYPE}', type).replace('{ALERTTITLE}', title).replace('{ALERTMSG}', msg);
    $('#alertBox').append($template);
    setTimeout(function() {
        $('.alert').alert('close');
    }, 1000);
}


// 效果添加

$('#back-to-top').click(function(event) {
    event.preventDefault();
    $('html,body').animate({
        scrollTop: 0
    }, 700);
});
