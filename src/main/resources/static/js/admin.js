$(function() {
    init();
    $("#addClass_bb #cancle").bind("click", function() {
        $("#addClass_dd").dialog("close");
    });
    $("#addClass_bb #save").bind("click", function() {
        $.messager.progress(); // display the progress bar
        var data = {};
        data.class_id = $("input[name='add_class_id']").val();
        data.class_name = $("input[name='add_class_name']").val();
        addClass(JSON.stringify(data));
    });
});

function init() {

    // / 请求初始化班级数据 不需要参数
    $.ajax({
        url: null,
        type: "get",
        dataType: 'json',
        success: function(data) {
            var $ul = $("#class_list ul");
            var $li = $("#class_list li:nth-of-type(1)").clone();
            $ul.find("li").remove();
            //形式看data.js
            var classes = data[0];
            var teacheres = data[1];
            for (var i = 0; i < classes.length; i++) {
                if (i !== 0) {
                    var $li = $("#class_list li:nth-of-type(1)").clone();
                }
                $li.find('a').attr("id", classes[i].class_id);
                $li.find('a').html(classes[i].class_name);
                $ul.append($li);
            }
            $('#teacher_dg').datagrid('loadData', teacheres);
        },
        error: function() {
            //test
            var classes = [{
                class_id: "class_id1",
                class_name: "网络工程"
            }, {
                class_id: "class_id2",
                class_name: "信息安全"
            }];
            var teacheres = {
                'total': 2,
                'rows': [{
                    teacher_name: '董守玲',
                    teacher_id: "111222333444",
                    password: "123456",
                    class_name: '网络工程'
                }, {
                    teacher_name: '贺小箭',
                    teacher_id: "222333444555",
                    password: "123456",
                    class_name: '网络工程'
                }]
            };
            var data = [classes, teacheres];
            var $ul = $("#class_list ul");
            var $li = $("#class_list li:nth-of-type(1)").clone();
            $ul.find("li").remove();
            var classes = data[0];
            var teacheres = data[1];
            for (var i = 0; i < classes.length; i++) {
                if (i !== 0) {
                    var $li = $("#class_list li:nth-of-type(1)").clone();
                }
                $li.find('a').attr("id", classes[i].class_id);
                $li.find('a').html(classes[i].class_name);
                $ul.append($li);
            }
            $('#teacher_dg').datagrid('loadData', teacheres);
        }
    });
}

function addClass(classInfo) {
    $.ajax({
        url: "新增班级请求路径",
        type: "post",
        data: classInfo,
        dataType: 'json',
        success: function(msg) {
            $.messager.progress("close");
            if (msg.success) {
                $.messager.alert('info', "添加班级成功");
                $("#addClass_dd").dialog("close");
                var $li = $("#class_list li:nth-of-type(1)").clone();
                $li.find('a').attr("id", data.class_id);
                var $ul = $("#class_list ul");
                $li.find('a').html(data.class_name);
                $ul.append($li);
            } else {
                $.messager.alert('Warning', "添加班级失败，请检查该班级是否存在");
            }
        },
        error: function() {
            //test
            $.messager.progress("close");
        }
    }); //ajax end
}

function openEast() {
    $("#ll").layout("expand", "east");
};

function closeEast() {
    $("#ll").layout("collapse", "east");
}

function addClass() {
    $("#addClass_dd").dialog({
        closed: false,
    })
}
// 效果添加
$(function() {
    $("#addClass_dd input").bind("change", function(event) {
        addIcons($(event.target));
    });
});

function addIcons(ele) {
    if (ele.val() !== "") {
        ele.parent().find('label').addClass("ok");
    };
}
