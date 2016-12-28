var currentPaperId = null; //全局变量
$(function() {
    //初始化
    init();
});

function bindBtn() {
    // 请求对应考卷信息
    $("#paper_list a").bind("click", function() {
        $("#paper_list a").removeClass('active');
        $(this).addClass('active');
        var paper_id = $(this).attr("id");
        getPaperStuGrade(paper_id);
    });
}

function init() {
    // / 请求初始话paper数据 不需要参数
    $.ajax({
        url: "/getAllMarkedPapers",
        cache:false,
        type: "get",
        success: function(result) {
            // var result = ['登录教师id', '登录教师名', 'paperList内容'];
            var $temp = $('#teacher_info')[0].innerHTML.trim();
            $temp = $temp.replace('{TEACHERID}', result['teacherId'])
                .replace('{TEACHERNAME}', result['teacherName']);
            $('#teacher_info').html($temp);

            var $ul = $("ul#paper_list");
            $ul.html('');
            var paperList = result['papersMarkInfoList'];
            for (var i = 0; i < paperList.length; i++) {
                var $template = $('#paperListTemplate')[0].innerHTML;
                var paperName = paperList[i]['paper_name'];
                var paperId = paperList[i]['paper_id'];
                $template = $template.replace('{PAPERID}', paperId).replace('{PAPERNAME}', paperName);
                $ul.append($template);
            }
            bindBtn();
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
            bindBtn();
        }
    });
}

function getPaperStuGrade(paperId) {
    console.log('get' + paperId);
    $.ajax({
        url: '/getStudentScoreByPaperId/' + paperId,
        cache:false,
        type: "get",
        success: function(data) {
            //清空 装入数据
            loadData(data['rows']);
            checkScore();
        },
        error: function() {
            var data = {
                'total': 3,
                'rows': [{
                    student_name: '廖晓娟',
                    student_id: "201430560243",
                    grade: '59',
                    rank: "55"
                }, {
                    student_name: '邹浩阳',
                    student_id: "201430560241",
                    grade: '96',
                    rank: "1"
                }, {
                    student_name: '要利娇',
                    student_id: "201430560240",
                    grade: '95',
                    rank: "2"
                }]
            };
            //清空 装入数据
            loadData(data['rows']);
            checkScore();
        }
    }); // ajax end
}
// To-do:设置一个统计不及格人数

function loadData(data) {
    console.log(data)
    $('#table').bootstrapTable({
        columns: [{
            field: 'student_name',
            title: '姓名'
        }, {
            field: 'student_id',
            title: '学号'
        }, {
            field: 'grade',
            title: '成绩',
            sortable: true,
            formatter: gradeFormatter
        }, {
            field: 'rank',
            title: 'Item ID',
            sortable: true
        }],
        data: data
    });
}

function Sorter(a, b) {

    if (a > b) return 1;
    if (a < b) return -1;
    return 0;
}

function gradeFormatter(value) {
    if (value < 60) {
        return '<div  style="color: red">' + value + '<span class="glyphicon glyphicon-thumbs-down pull-right"></span></div>';
    }
    if (value > 90) {
        return '<div>' + value + '<span class="glyphicon glyphicon-thumbs-up pull-right"></span></div>';
    } else return value;
}

function alertMsg(
    title, msg) {
    var $template = $('#alert-template')[0].innerHTML;
    var type;
    if (title == 'Warning') { type = 'danger' } else if (title == 'Info') { type = 'warning' };
    $template = $template.replace('{ALERTTYPE}', type).replace('{ALERTTITLE}', title).replace('{ALERTMSG}', msg);
    $('#alertBox').append($template);
}
