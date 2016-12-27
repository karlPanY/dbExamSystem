$(function() {
    $('#paper_grade_dg').datagrid({
        remoteSort: false,
        columns: [
            [{
                field: 'student_name',
                title: '学生姓名',
                width: 150,
                align: 'center'
            }, {
                field: 'student_id',
                title: '学号',
                width: 120,
                sortable: true,
                align: 'center'
            }, {
                field: 'grade',
                title: '成绩',
                width: 120,
                sortable: true,
                align: 'center',
                sorter: function(a, b) {
                    return (a > b ? 1 : -1);
                }
            }, {
                field: 'rank',
                title: '排名',
                width: 80,
                align: 'center'
            }]
        ],
        rowStyler: function(index, row) {
            if (row.grade < 60) {
                return 'color:red;';
            }
        }
    });

});

$(function() {
    //初始化
    init();
    getPaperStuGrade(1);
    // 请求对应考卷信息
    $("#paper_list a").bind("click", function() {
        $("#paper_list a").removeClass('active');
        $(this).addClass('active');
        var paper_id = $(this).attr("id");
        getPaperStuGrade(paper_id);
    });
});

function init() {
    $.ajax({
        url: "/getAllMarkedPapers",
        type: "get",
        success: function(result) {
            var $temp = $('#teacher_info')[0].innerHTML.trim();
            $temp = $temp.replace('{TEACHERID}', result['teacher_id'])
                .replace('{TEACHERNAME}', result['teacher_name']);
            $('#teacher_info').html($temp);
            var data = result['data'];
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
            var data = [{
                paper_id: "paper_id1",
                paper_name: "数据库试题1"
            }, {
                paper_id: "paper_id2",
                paper_name: "数据库试题2"
            }];
            var result = ['登录教师id', '登录教师名', data];
            var $temp = $('#teacher_info')[0].innerHTML.trim();
            $temp = $temp.replace('{TEACHERID}', result[0])
                .replace('{TEACHERNAME}', result[1]);
            $('#teacher_info').html($temp)
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

function getPaperStuGrade(paperId) {
    $.ajax({
        url: '/getStudentScoreByPaperId/'+paperId,
        type: "get",
        success: function(data) {
            $('#paper_grade_dg').datagrid('loadData', data);
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
            $('#paper_grade_dg').datagrid('loadData', data);
        }
    }); // ajax end
}
// To-do:设置一个统计不及格人数
