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
    // 根据teacher_id请求所有班级信息
    var teacher_id = $("#top strong").attr("id");
    $.ajax({
        url: '请求所有班级信息',
        type: "post",
        data: {
            teacher_id: teacher_id
        },
        // 返回数据data=[{class_id:"",class_name:"网络工程"},{class_id:"",class_name:"信息安全"}];
        success: function(data) {
            var $ul = $("#class_list ul");

            for (var i = 0; i < data.length; i++) {
                var $template = $("#template_class_list")[0].innerHTML;
                $template = $template.replace('{CLASSID}', data[i].class_id).replace('{CLASSNAME}', data[i].class_name);
                $ul.append($template);
            }
        }
    }); //ajax结束
});

$(function() {
    // 根据class_id请求对应班级信息
    $("#class_list a").bind("click", function() {
        var class_id = $(this).attr("id");
        // var data = {
        //     'total': 2,
        //     'rows':[{student_name: '廖晓娟',student_id: "201430560243", class_name: '网络工程'},
        //  {student_name: '要利娇',student_id: "201430560241",class_name: '网络工程'}]
        // };
        $.ajax({
            url: '请求班级学生信息',
            type: "post",
            data: {
                class_id: class_id
            },
            success: function(data) {
                $('#class_student_dg').datagrid('loadData', data);
            }
        }); // ajax end
    });
});

var currentRow;
$('#class_student_dg').datagrid({
    onClickRow: function(index, row) {
        currentRow = row;
    }
});

function getStuGrade() {
    $("#east strong").html(currentRow.student_name);
    var teacher_id = $("#top strong").attr("id");
    var student_id = currentRow.student_id;
    // 根据teacher_id，student_id请求学生成绩
    // var data = {
    //     'total': 3,
    //     'rows':[{paper_name:"数据库1",grade:"97",rank:"3"},
    // {paper_name:"数据库2",grade:"88",rank:"12"},
    // {paper_name:"数据库3",grade:"85",rank:"12"}]
    // };
    $.ajax({
        url: '获取学生考试成绩',
        type: "post",
        data: {
            teacher_id: teacher_id,
            student_id: student_id
        },
        success: function(data) {
            $("#student_grade_dg").datagrid('loadData', data);
        }
    })
};
// To-do功能 修改学生成绩
