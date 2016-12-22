var editIndex = undefined;
var newAppend = false;

function endEditing() {
    if (editIndex == undefined) {
        return true
    }
    return false;
}

function getRowIndex(target) {
    var tr = $(target).closest('tr.datagrid-row');
    return parseInt(tr.attr('datagrid-row-index'));
}

function append(_dg) {
    if (endEditing()) {
        editIndex = $(_dg).datagrid('getRows').length;
        $(_dg).datagrid('insertRow', {
            index: editIndex,
            row: {}
        });
        $(_dg).datagrid('selectRow', editIndex)
            .datagrid('beginEdit', editIndex);
        newAppend = true;
    } else {
        $.messager.alert('Warning', '请保存或者取消正在编辑项目');
    }
}

function saverow(target) {
    if (getRowIndex(target) === editIndex) {
        var _dg = $(target).closest('.datagrid-view').find('.dg');
        $(_dg).datagrid('endEdit', editIndex);
        var row = $(_dg).datagrid('getRows')[editIndex];

        var RowId, RowName, url;
        if ($(_dg).attr("id") == "student_dg") {
            RowId = row.student_id;
            RowName = row.student_name;
            url = "添加/更新学生请求路径";
        } else {
            RowId = row.teacher_id;
            RowName = row.teacher_name;
            url = "添加/更新教师请求路径";
        }
        row['new'] = newAppend;
        if (/\d{6,}/.test(RowId) && /\S{1,}/.test(RowName)) {
            $.ajax({
                url: url,
                type: 'post',
                data: JSON.stringify(row),
                success: function(msg) {
                    if (msg.success) {
                        newAppend = false;
                        editIndex = undefined;
                    } else {
                        $.messager.alert('Warning', '请检查新添成员是否已经存在');
                    }
                },
                error: function() {

                }
            }); //ajax end
        } else {
            $.messager.alert('Warning', '请检查学号/职工号是否为6位以上,姓名是否正确');
            $(_dg).datagrid('beginEdit', editIndex);
        }
    } else {
        $.messager.alert('Warning', '未进行任何修改');
    }
}

function editrow(target) {
    if (endEditing()) {
        var _dg = $(target).closest('.datagrid-view').find('.dg');
        editIndex = getRowIndex(target);
        $(_dg).datagrid('beginEdit', editIndex);
        $(target).closest('tr.datagrid-row').find("button")[0].disabled = false;
        $(target).closest('tr.datagrid-row').find("button")[1].disabled = false;

    } else {
        alert('请保存正在编辑项');
    }
}

function deleterow(target) {
    var _dg = $(target).closest('.datagrid-view').find('.dg')
    $.messager.confirm('Confirm', 'Are you sure?', function(r) {
        if (r) {
            var index = getRowIndex(target);
            var row = JSON.stringify($(_dg).datagrid('getRows')[index]);
            var url;
            if ($(_dg).attr("id") == "student_dg") {
                url = "删除学生请求路径";
            } else {
                url = "删除教师请求路径";
            }
            // 提交信息：{"student_id":"201430560243","student_name":"廖晓娟","password":"123456","new":false}
            $.ajax({
                url: url,
                type: "post",
                data: row,
                success: function(msg) {
                    if (msg.success) {
                        $.messager.alert('Info', '删除成功');
                        if (editIndex == index) {
                            editIndex = undefined;
                        }
                        $(_dg).datagrid('deleteRow', index);
                    } else {
                        $.messager.alert('Warning', '删除失败');
                    }
                },
                error: function() {
                    //test
                    console.log('测试删除数据：' + row);
                }
            }); //ajax结束

        }
    });
}

function cancelrow(target) {
    if (!endEditing() && getRowIndex(target) == editIndex) {
        var _dg = $(target).closest('.datagrid-view').find('.dg')
        $(_dg).datagrid('cancelEdit', editIndex);
        editIndex = undefined;
    }
}

function deleteSelected(_dg) {
    var rows = $(_dg).datagrid("getSelections");
    if (rows !== undefined) {
        var rowsIds = [],
            RowId, url;
        if ($(_dg).attr("id") == "student_dg") {
            RowId = "student_id";
            url = "同上面删除学生路径"
        } else {
            RowId = "teacher_id";
            url = "同上面删除教师路径"
        }
        for (var i = 0; i < rows.length; i++) {
            rowsIds.push(rows[i][RowId]);
        }
        for (var j = 0; j < rowsIds.length; j++) {
            var index = $(_dg).datagrid('getRowIndex', rowsIds[j]);
            //to-do:要传送给后台的删除数据
            var deleterow = JSON.stringify($(_dg).datagrid('getRows')[index]);
            $.ajax({
                url: url,
                type: "post",
                data: deleterow,
                success: function(msg) {
                    if (msg.success) {
                        $(_dg).datagrid('deleteRow', index);
                    } else {
                        $.messager.alert('Warning', '删除第<b> ' + index + ' </b>条数据失败');
                    }
                },
                error: function() {
                    //test
                    console.log('删除数据：' + deleterow);
                }
            }); //end ajax
        }
    }
}


var currentIndex;

function onClickRow(index, row) {

    if (currentIndex == index) {
        $(this).datagrid('unselectRow', index);
        currentIndex = null;
    } else {
        if (endEditing()) {
            currentIndex = index;
        } else {
            $(this).datagrid('unselectRow', index)
            $(this).datagrid('selectRow', editIndex);
            currentIndex = editIndex;
        }
    }
}
