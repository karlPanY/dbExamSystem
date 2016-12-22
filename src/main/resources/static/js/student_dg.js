$(function() {
    // 根据class_id请求 班级学生信息
    $("#class_list a").bind("click", function() {
        editIndex = undefined;
        var class_id = $(this).attr("id");
        var class_name = $(this).html();
        $.ajax({
            url: null,
            type: "get",
            data: {
                class_id: class_id
            },
            success: function(data) {
                $('#student_dg').datagrid('loadData', data);
            }
        }); // ajax end
    });
});

$(function() {
    $('#student_dg').datagrid({
        title: '学生信息管理',
        iconCls: 'icon-edit',
        width: 722,
        height: 'auto',
        idField: 'student_id',
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function() {
                append('#student_dg');
            }
        }, {
            text: '全选',
            iconCls: 'icon-add',
            handler: function() {
                if (editIndex !== undefined) {
                    alert("请保存或取消正在编辑项")
                } else {
                    $('#student_dg').datagrid('selectAll');
                }

            }
        }, {
            text: '全不选',
            iconCls: 'icon-add',
            handler: function() {
                $('#student_dg').datagrid('unselectAll');
            }
        }, {
            text: '删除所选行',
            iconCls: 'icon-remove',
            handler: function() {
                deleteSelected('#student_dg')
            }
        }],
        columns: [
            [{
                field: 'student_id',
                title: '学号',
                width: 120,
                editor: {
                    type: 'numberbox',
                    options: {
                        required: true
                    }
                }
            }, {
                field: 'student_name',
                title: '姓名',
                width: 120,
                align: 'right',
                editor: {
                    type: 'text',
                    options: {
                        required: true
                    }
                }
            }, {
                field: 'password',
                title: '密码',
                width: 120,
                align: 'right',
                editor: 'text'
            }, {
                field: 'class_name',
                title: '所属班级',
                width: 120
            }, {
                field: 'action',
                title: 'Action',
                width: 240,
                align: 'center',
                formatter: function(value, row, index) {
                    var s = '<button  onclick="saverow(this)">Save</button> ';
                    var c = '<button disabled="true" onclick="cancelrow(this)">Cancel</button> ';
                    var e = '<button  onclick="editrow(this)">Edit</button> ';
                    var d = '<button onclick="deleterow(this)">Delete</button>';
                    return s + c + e + d;

                }
            }]
        ],
        onClickRow: onClickRow
    });
});
