var currentPaper = null; //全局变量
$(function() {
    //初始化
    init();
    // 请求对应试题 学生答卷
    $("#paper_list a").bind("click", function() {
        $("#paper_list a").removeClass('active');
        $(this).addClass('active');
        var paper_id = $(this).attr("id");
        currentPaper = paper_id;
        // 获取试题
        getAnswerPaper(currentPaper, 1);
    });
    $('#pp').pagination({
        onSelectPage: function(pageNumber, pageSize) {
            $(this).pagination('loading');
            getAnswerPaper(currentPaper, pageNumber);
            $(this).pagination('loaded');
        }
    });

});

function init() {
    $.ajax({
        url: null,
        type: "get",
        data: null,
        success: function(result) {
            var $temp = $('#teacher_info')[0].innerHTML.trim();
            $temp = $temp.replace('{TEACHERID}', result[0])
                .replace('{TEACHERNAME}', result[1]);
            $('#teacher_info').html($temp);
            var data = result[2];
            var $ul = $("#paper_list ul");
            $ul.find("li").remove();
            for (var i = 0; i < data.length; i++) {
                var $template = $("#template_paper_list")[0].innerHTML.trim();
                $template = $template.replace('{PAPERID}', data[i]['paper_id'])
                    .replace('{PAPERNAME}', data[i]['paper_name']);
                $ul.append($template);
            }
        },
        error: function() {
            //test
            var result = ['登录教师id', '登录教师名', data];
            var data = [{
                paper_id: "paper_id1",
                paper_name: "数据库试题1"
            }, {
                paper_id: "paper_id2",
                paper_name: "数据库试题2"
            }];
            var $temp = $('#teacher_info')[0].innerHTML.trim();
            $temp = $temp.replace('{TEACHERID}', result[0])
                .replace('{TEACHERNAME}', result[1]);
            $('#teacher_info').html($temp);
            var data = result[2];
            var $ul = $("#paper_list ul");
            $ul.find("li").remove();
            for (var i = 0; i < data.length; i++) {
                var $template = $("#template_paper_list")[0].innerHTML.trim();
                $template = $template.replace('{PAPERID}', data[i]['paper_id'])
                    .replace('{PAPERNAME}', data[i]['paper_name']);
                $ul.append($template);
            }
        }
    }); // ajax end
}


// 根据paper_id 和pageNumber 发起请求 第pageNumber份试卷 (包括学生id 学生姓名 学生答案 学生成绩-判断是否已经评分)
function getAnswerPaper(paper_id, index) {
    $.ajax({
        url: '根据paper_id,请求第index份试题',
        data: { 'paper_id': paper_id, 'index': index },
        type: 'post',
        success: function(result) {
            var num = result[0];
            var data = result[1];
            if (num > 0) {
                $('#pp').pagination({
                    total: num, //代表该份试题参与学生人数
                    pageList: [1],
                    pageSize: 1
                });
                render(data.student_id, data.student_name, data.student_paper, data.student_score);
            } else {
                alert("该份试题，暂时没有参加考试！")
            }
        },
        error: function() {
            var data = {
                "student_id": '201430560243',
                "student_name": '廖晓娟',
                "student_paper": [
                    ['题目1-id', '题目1', '答案1', '5(分值)'],
                    ['题目2-id', '题目2', '答案2', '5']
                ],
                "student_score": 0
            };
            var result = [1, data];
            var num = result[0];
            var data = result[1];
            if (num > 0) {
                $('#pp').pagination({
                    total: num, //代表该份试题参与学生人数
                    pageList: [1],
                    pageSize: 1
                });
                render(data.student_id, data.student_name, data.student_paper, data.student_score);
            }
        }
    });
}


function render(student_id, student_name, paper, score) {

    $('#student_paper').html('');
    var $template1 = $("#template_info")[0].innerHTML.trim();
    var $template2;
    $template1 = $template1.replace('{STUDENTNAME}', student_name).replace('{STUDENTSCORE}', score);
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
    $(".operation_div>button").bind('click', function() {
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
            alert('请给一个合法的分数')
        } else if (Number(score) > max) {
            alert('评分不可以超过这道题的分值哦~~·');
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
    var paper_id = currentPaper;
    var totalScore = $('#sum-score').html();
    $.ajax({
        url: '提交评改成绩url',
        type: 'post',
        data: { 'paper_id': paper_id, 'student_id': student_id, 'score': totalScore },
        success: function(msg) {
            if (msg.success) {
                alert('评分完成，请选择下一份试卷~~~~');
            } else {
                //提示错误？？
            }
        }
    })
}


// 效果添加

$('#back-to-top').click(function(event) {
    event.preventDefault();
    $('html,body').animate({
        scrollTop: 0
    }, 700);
});
