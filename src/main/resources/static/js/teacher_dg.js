$(function() {
    $('#teacher_dg').datagrid({
        title: '教师信息管理',
        iconCls: 'icon-edit',
        width: 722,
        height: 'auto',
        rownumbers: true,
        idField: 'teacher_id',
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function() {
                append('#teacher_dg');
            }
        }, {
            text: '全选',
            iconCls: 'icon-add',
            handler: function() {
                $('#teacher_dg').datagrid('selectAll');
            }
        }, {
            text: '全不选',
            iconCls: 'icon-add',
            handler: function() {
                $('#teacher_dg').datagrid('unselectAll');
            }
        }, {
            text: '删除所选行',
            iconCls: 'icon-remove',
            handler: function(){
                deleteSelected('#teacher_dg')
            }
        }],
        columns: [
            [{
                field: 'teacher_id',
                title: '职工号',
                width: 120,
                editor: {
                    type: 'numberbox',
                    options: {
                        required: true
                    }
                },  sortable: true,
                 sorter: function(a, b) {
                    return (a > b ? 1 : -1);
                }
            }, {
                field: 'teacher_name',
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
                title: '分配班级',
                width: 120
            }, {
                field: 'action',
                title: 'Action',
                width: 240,
                align: 'center',
                formatter: function(value, row, index) {
                    var s = '<button  onclick="saverow(this)">Save</button> ';
                    var c = '<button disabled="true" onclick="cancelrow(this)">Cancel</button> ';
                    var e = '<button onclick="editrow(this)">Edit</button> ';
                    var d = '<button onclick="deleterow(this)">Delete</button>';
                    return s + c + e + d;

                }
            }]
        ],
        onClickRow: onClickRow
    });
});
