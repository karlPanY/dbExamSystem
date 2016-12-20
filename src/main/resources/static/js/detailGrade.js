$(function() {
    // 根据teacher_id请求所有已经评阅的试卷信息
    var teacher_id = $("#top strong").attr("id");
    $.ajax({
        url: null,
        type: "post",
        data: { teacher_id: teacher_id },

        // var data = [{
        //     paper_id: "paper_id1",
        //     paper_name: "数据库试题1"
        // }, {
        //     paper_id: "paper_id2",
        //     paper_name: "数据库试题2"
        // }];
        success: function(data) {
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
});
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
    // 请求对应班级信息
    $("#paper_list a").bind("click", function() {
        $("#paper_list a").removeClass('active');
        $(this).addClass('active');
        var paper_id = $(this).attr("id");

        // var data = {
        //     'total': 3,
        //     'rows': [{
        //         student_name: '廖晓娟',
        //         student_id: "201430560243",
        //         grade: '59',
        //         rank: "55"
        //     }, {
        //         student_name: '邹浩阳',
        //         student_id: "201430560241",
        //         grade: '96',
        //         rank: "1"
        //     }, {
        //         student_name: '要利娇',
        //         student_id: "201430560240",
        //         grade: '95',
        //         rank: "2"
        //     }]
        // };
        $.ajax({
            url: '根据paper_id请求对应班级学生成绩信息',
            type: "post",
            data: {
                paper_id: paper_id
            },
            success: function(data) {
                $('#paper_grade_dg').datagrid('loadData', data);
            }
        }); // ajax end
    });
});

// To-do:设置一个统计不及格人数
