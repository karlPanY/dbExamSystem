var editIndex = undefined;
var currentClass = null;
var flag = 0;
var originData;
var newAppend = false;

function endEditing() {
    if (editIndex == undefined) {
        return true
    }
    return false;
}

$(function() {
    init();
    $('#btn_addClass').bind('click', function() {
        var data = {};
        data.class_teacher = $("input[name='add_class_teacher']").val();
        data.class_name = $("input[name='add_class_name']").val();
        console.log(['测试', JSON.stringify(data)]);
        addClass(data);
    });
    $("#class_list").bind("click", function(event) {
        $('#class_list a').removeClass('active');
        $(event.target).addClass('active');
        getClass(event.target);
    });
    $('.mainLnk').bind('click', function(event) {
        $('.mainLnk').removeClass('active');
        $(event.target).addClass('active');
    });
});


function init() {
    // / 请求初始化班级数据 不需要参数
    $.ajax({
        url: "/admin/init",
        type: "get",
        cache:false,
        dataType: 'json',
        success: function(data) {
            var $ul = $("ul#class_list .last");
            var classes = data;
            for (var i = 0; i < classes.length; i++) {
                var $template = $('#classListTemplate')[0].innerHTML;
                var className = classes[i].class_name;
                var classId = classes[i].class_id;
                $template = $template.replace('{CLASSID}', classId).replace('{CLASSNAME}', className);
                $ul.before($template);
            }
        }
    });
}

function addClass(classInfo) {

    var postdata = JSON.stringify(classInfo);
    $.ajax({
        url: "/admin/addClass",
        cache:false,
        type: "post",
        data: postdata,
        contentType: "application/json; charset=utf-8",
        success: function(msg) {
            var status = msg.success;
            var classId = msg.class_id;
            var className = classInfo.class_name;
            if (status) {
                alertMsg('info', "添加班级成功");
                var $template = $('#classListTemplate')[0].innerHTML;
                $template = $template.replace('{CLASSID}', classId).replace('{CLASSNAME}', className);
                var $ul = $("ul#class_list .last");
                $ul.before($template);
                setTimeout(function() {
                    $('.alert').alert('close');
                }, 500);
            } else {
                alertMsg("Warning", "添加班级失败，请检查该班级或该老师是否存在");
                setTimeout(function() {
                    $('.alert').alert('close');
                }, 500);
            }
        }
}); //ajax end
}

function getClass(target) {

    editIndex = undefined;
    var classId = $(target).attr("id");

    $.ajax({
        url: "/admin/getClassStu",
        cache:false,
        type: "get",
        data: { 'class_id': classId },
        dataType: 'json',
        success: function(data) {
            datagrid('#student_dg', 'loadData', data['rows'][0]);
            for (var i = 1, len = data['rows'].length; i < len; i++) {
                datagrid('#student_dg', 'appendData', data['rows'][i]);
            }
        }
    }); // ajax end
}

function getTeacher() {
    editIndex = undefined;
    $.ajax({
        url: "/admin/getTeacher",
        cache:false,
        type: "get",
        success: function(data) {
            datagrid('#teacher_dg', 'loadData', data['rows'][0]);
            for (var i = 1, len = data['rows'].length; i < len; i++) {
                datagrid('#teacher_dg', 'appendData', data['rows'][i]);
            }

        }
    }); // ajax end
}



function openPanel(type) {
    $('.main').addClass('hide');
    $(type).removeClass('hide');
    if (type == '.teacher_panel') {
        getTeacher();
    }
}


// ========datagrid=======

function getRowIndex(target) {
    var totalLen = $(target).closest('table').find('tbody').find('tr').length;
    var nextLen = $(target).closest('tr').nextAll('tr').length;
    return totalLen - nextLen;
}

function datagrid(dg, action, data) {
    //清空 装入数据
    if (action == 'loadData') {
        $(dg).find('tbody').html('');
        var $template = $($(dg + '_template').html());
        for (var j in data) {
            $template.find("input[name='" + j + "']").val(data[j]);
        }
        $(dg).find('tbody').append($template);
    }
    if (action == 'appendData') {
        var $template = $($(dg + '_template').html());
        for (var j in data) {
            $template.find("input[name='" + j + "']").val(data[j]);
        }
        $(dg).find('tbody').append($template);
    }
    if (action == 'getRows') {
        var index = data;
        var $inputs = $(dg).find('tbody tr:nth-of-type(' + index + ')').find('input:text');
        originData = {};
        for (var i = 0, len = $inputs.length; i < len; i++) {
            var key, value;
            key = $($inputs[i]).attr('name');
            value = $($inputs[i]).val();
            originData[key] = value;
        }
        return originData;
    }
    if (action == 'deleteRow') {
        var index = data;
        $(dg).find('tbody tr:nth-of-type(' + index + ')').remove();
    }
}

function append(dgId) {

    var _flag = $(dgId).attr("id") == "student_dg" ? 0 : 1;
    if (_flag !== flag) {
        flag = _flag;
        editIndex = undefined;
    }
    if (endEditing()) {
        editIndex = $(dgId).find('tbody').find('tr').length + 1;
        var $template = $($(dgId + '_template').html());
        $(dgId).find('tbody').append($template);

        var currentClassName = $("#class_list").find('a.active').html();
        $(dgId).find('tbody tr:nth-of-type(' + editIndex + ')').find("input[name='class_name']").val(currentClassName);

        var $inputs = $(dgId).find('tbody tr:nth-of-type(' + editIndex + ')').find('input:text');
        for (var i = 0, len = $inputs.length; i < len; i++) {
            $inputs[i].value = '';
            $inputs[i].disabled = false;
        }
        $(dgId).find('tbody tr:nth-of-type(' + editIndex + ')').find('button:nth-child(1)').get(0).disabled = false;
        $(dgId).find('tbody tr:nth-of-type(' + editIndex + ')').find('button:nth-child(2)').get(0).disabled = false;
        $(dgId).find('tbody tr:nth-of-type(' + editIndex + ')').find('button:nth-child(4)').get(0).disabled = true;
        newAppend = true;
    } else {
        alertMsg('Warning', '请保存或者取消正在编辑项目');
        setTimeout(function() {
            $('.alert').alert('close');
        }, 1500);
    }
}

function saverow(target) {

    if (getRowIndex(target) === editIndex) {
        var _dg = $(target).closest('table').attr("id");

        var row = datagrid('#' + _dg, 'getRows', editIndex);
        var currentClassId = $("#class_list").find('a.active').attr('id');
        row['news'] = newAppend;
        row['class_id'] = currentClassId;
        var postdata = JSON.stringify(row);

        var RowId, RowName, url;
        if (_dg == "student_dg") {
            RowId = row.student_id;
            RowName = row.student_name;
            url = "/admin/addStudent";
        } else {
            RowId = row.teacher_id;
            RowName = row.teacher_name;
            url = "/admin/addTeacher";
        }

        if (/\d{6,}/.test(RowId) && /\S{1,}/.test(RowName)) {
            $.ajax({
                url: url,
                cache:false,
                type: 'post',
                data: postdata,
                contentType: "application/json; charset=utf-8",
                success: function(msg) {
                    if (msg.success) {
                        var inputs = $(target).closest('tr').find('input:text');
                        for (var i = 0, len = inputs.length; i < len; i++) {
                            inputs[i].disabled = true;
                        }
                        $(target).get(0).disabled = true;
                        $(target).closest('td').find('button:nth-child(2)').get(0).disabled = true;
                        $(target).closest('td').find('button:nth-child(4)').get(0).disabled = false;
                        newAppend = false;
                        editIndex = undefined;
                    } else {
                        alertMsg('Warning', '请检查新添成员是否已经存在,班级名是否正确');
                    }
                }
            }); //ajax end
        } else {
            alertMsg('Warning', '请检查学号/职工号是否为6位以上,姓名是否正确');
            setTimeout(function() {
                $('.alert').alert('close');
            }, 500);
        }

    } else {
        alertMsg('Warning', '未进行任何修改');
        setTimeout(function() {
            $('.alert').alert('close');
        }, 1500);
    }
}

function cancelrow(target) {

    var _flag = $(target).closest('table').attr("id") == "student_dg" ? 0 : 1;
    if (_flag !== flag) { newAppend = false; }

    if (!endEditing() && getRowIndex(target) == editIndex) {
        editIndex = undefined;
    }
    if (newAppend) {
        newAppend = false;
        $(target).closest('tr').remove();
    } else {
        $(target).get(0).disabled = true;
        $(target).closest('td').find('button:nth-child(1)').get(0).disabled = true;
        //to-do 恢复原始数据
        var inputs = $(target).closest('tr').find('input:text');
        for (var i = 0, len = inputs.length; i < len; i++) {
            inputs[i].disabled = true;
        }
    }

}

function editrow(target) {
    var _flag = $(target).closest('table').attr("id") == "student_dg" ? 0 : 1;

    if (endEditing()) {
        $(target).closest('td').find('button:nth-child(2)').get(0).disabled = false;
        $(target).closest('td').find('button:nth-child(1)').get(0).disabled = false;
        editIndex = getRowIndex(target);
        var inputs = $(target).closest('tr').find('input:text');
        for (var i = 1, len = inputs.length; i < len; i++) {
            inputs[i].disabled = false;
        }
    } else {
        alertMsg('Warning', '请保存正在编辑项');
        setTimeout(function() {
            $('.alert').alert('close');
        }, 1500);
    }
}

function deleterow(target) {

    var _dg = $(target).closest('table');;

    var index = getRowIndex(target);
    var row = datagrid(_dg, 'getRows', index);
    var postdata = JSON.stringify(row);
    var url;
    if ($(_dg).attr("id") == "student_dg") {
        url = "/admin/deleteStudent";
    } else {
        url = "/admin/deleteTeacher";
    }
    // 提交信息：{"student_id":"201430560243","student_name":"廖晓娟","password":"123456","new":false}
    $.ajax({
        url: url,
        type: "post",
        cache:false,
        data: postdata,
        contentType: "application/json; charset=utf-8",
        success: function(msg) {
            if (msg.success) {
                alertMsg('Info', '删除成功');
                if (editIndex == index) {
                    editIndex = undefined;
                }
                datagrid(_dg, 'deleteRow', index);
                setTimeout(function() {
                    $('.alert').alert('close');
                }, 500);
            } else {
                alertMsg('Warning', '删除失败');
                setTimeout(function() {
                    $('.alert').alert('close');
                }, 1500);
            }
        },
        error: function() {
            //test
            datagrid(_dg, 'deleteRow', index);
            console.log('测试删除数据：' + postdata);
        }
    }); //ajax结束

}

function selectAll(dgId) {
    $(dgId).find('input:checkbox').attr('checked', true);
}

function diselectAll(dgId) {
    $(dgId).find('input:checkbox').attr('checked', false);
}

function deleteSelected(dgId) {

    while ($(dgId).find('input:checked').length !== 0) {
        var target = $(dgId).find('input:checked').get(0);
        deleterow(target);
    }
}

function alertMsg(
    title, msg) {
    var $template = $('#alert-template')[0].innerHTML;
    var type;
    if (title == 'Warning') { type = 'danger' } else if (title == 'Info') { type = 'warning' };
    $template = $template.replace('{ALERTTYPE}', type).replace('{ALERTTITLE}', title).replace('{ALERTMSG}', msg);
    $('#alertBox').append($template);
}
