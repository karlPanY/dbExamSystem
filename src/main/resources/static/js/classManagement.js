var toolbar = [{
    text: '查看具体成绩',
    iconCls: 'icon-search',
    handler: getStuGrade
}];
var dd_buttons = [{
    text: '确定'
}, {
    text: '取消'
}];
$(function() {

    init();
    // 绑定事件，根据class_id请求对应班级信息
    $("#class_list a").bind("click", function() {
        var class_id = $(this).attr("id");
        getClassStu(class_id);
    });

});


function init() {
    $.ajax({
        url: '页面初始化后请求所有班级信息',
        type: "get",
        success: function(result) {
            var $temp = $('#teacher_info')[0].innerHTML.trim();
            $temp = $temp.replace('{TEACHERID}', result[0])
                .replace('{TEACHERNAME}', result[1]);
            $('#teacher_info').html($temp)
            var data = result[2];
            var $ul = $("#class_list ul");
            for (var i = 0; i < data.length; i++) {
                var $template = $("#template_class_list")[0].innerHTML;
                $template = $template.replace('{CLASSID}', data[i].class_id).replace('{CLASSNAME}', data[i].class_name);
                $ul.append($template);
            }
        },
        error: function() {
            //test
            var data = [{ class_id: "wlgc", class_name: "网络工程" }, { class_id: "xxaq", class_name: "信息安全" }];
            var result = ['登录教师teacher_id', '登录教师名', data];
            var $temp = $('#teacher_info')[0].innerHTML.trim();
            $temp = $temp.replace('{TEACHERID}', result[0])
                .replace('{TEACHERNAME}', result[1]);
            $('#teacher_info').html($temp)
            var data = result[2];
            var $ul = $("#class_list ul");
            for (var i = 0; i < data.length; i++) {
                var $template = $("#template_class_list")[0].innerHTML;
                $template = $template.replace('{CLASSID}', data[i].class_id).replace('{CLASSNAME}', data[i].class_name);
                $ul.append($template);
            }
        }
    }); //ajax结束
}




var currentRow;
$('#class_student_dg').datagrid({
    onClickRow: function(index, row) {
        currentRow = row;
    }
});


function getClassStu(classId) {


    $.ajax({
        url: '请求班级学生信息',
        type: "get",
        data: {
            class_id: classId
        },
        success: function(data) {
            $('#class_student_dg').datagrid('loadData', data);
        },
        error: function() {
            //test
            var data = {
                'total': 2,
                'rows': [{ student_name: '廖晓娟', student_id: "201430560243", class_name: '网络工程' },
                    { student_name: '要利娇', student_id: "201430560241", class_name: '网络工程' }
                ]
            };
            $('#class_student_dg').datagrid('loadData', data);
        }
    }); // ajax end
}

function getStuGrade() {
    $("#east strong").html(currentRow.student_name);
    var teacher_id = $("#top strong").attr("id");
    var student_id = currentRow.student_id;
    // 根据teacher_id，student_id请求学生成绩

    $.ajax({
        url: '获取学生考试成绩',
        type: "post",
        data: {
            teacher_id: teacher_id,
            student_id: student_id
        },
        success: function(data) {
            $("#student_grade_dg").datagrid('loadData', data);
        },
        error: function() {
            //test
            var data = {
                'total': 3,
                'rows': [{ paper_name: "数据库1", grade: "97", rank: "3" },
                    { paper_name: "数据库2", grade: "88", rank: "12" },
                    { paper_name: "数据库3", grade: "85", rank: "12" }
                ]
            };
            $("#student_grade_dg").datagrid('loadData', data);
        }
    })




};
// To-do功能 修改学生成绩
