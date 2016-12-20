var toolbar = [{
    text: '查看试卷',
    iconCls: 'icon-search',
    handler: previewPaper
}, {
    text: '发布试卷',
    iconCls: 'icon-edit',
    handler: subscribePaper
}, {
    text: '编辑试卷',
    iconCls: 'icon-edit',
    handler: editPaper
}];
var toolbar2 = [{
    text: '查看/修改试题',
    iconCls: 'icon-edit',
    handler: modifyQuestion
}, {
    text: '关闭',
    iconCls: 'icon-no',
    handler: function() {
        $('#paper_detail_panel').panel('close');
    }
}];
var dd_buttons = [{
    text: '确定',
    iconCls: 'icon-ok'
}, {
    text: '取消',
    iconCls: 'icon-no'
}];
$(function() {

    var teacher_id = $("#top strong").attr("id");
    // 初始化 根据teacher_id 请求所有试题信息 如下

    // var data = {
    //     'total': 2,
    //     'rows': [{
    //         paper_name: '数据库试题1',
    //         paper_start: "11/17/2016 23:30:18",
    //         paper_end: '11/17/2016 23:30:18',
    //         paper_status: "1" //已发布
    //     }, {
    //         paper_name: '数据库试题2',
    //         paper_start: "12/19/2016 23:30:18",
    //         paper_end: '12/20/2016 23:30:18',
    //         paper_status: "1" //已发布
    //     }, {
    //         paper_name: '数据库试题3',
    //         paper_start: "",
    //         paper_end: "",
    //         paper_status: "0" //未发布
    //     }]
    // };

    var paperStatus;
    var paperEnd;
    $.ajax({
        url: '根据teacher_id 请求所有试题信息url',
        type: "post",
        data: {
            teacher_id: teacher_id
        },
        success: function(data) {
    data.rows.forEach(function(item) {
        paperStatus = item["paper_status"];
        paperEnd = item["paper_end"];
        if (paperStatus == "0") {
            paperStatus = "未发布...";
        } else if (new Date(paperEnd).getTime() - Date.now() > 0) {
            paperStatus = "发布中...";
        } else {
            paperStatus = "过期...";
        };
        item["paper_status"] = paperStatus;
    });
    $('#paper_dg').datagrid('loadData', data);
    }
   })

});


function openDg(id_dg) {
    $($(id_dg).parents(".datagrid")[0]).css("display", "table");
}

function openPaperDg() {
    openDg("#paper_dg")
}

function openPanel() {
    $('#paper_detail_panel').panel('open');
    $("#paper_detail_panel").find("strong").html(currentRow.paper_name)
    $(".datagrid").css("display", "none");
    openDg("#paper_detail_dg");
}
//获取datagrid点击状态
var currentRow;
var currentIndex;
var currentIndex2;
var currentRow2;
$('#paper_dg').datagrid({
    onClickRow: function(index, row) {
        currentRow = row;
        currentIndex = index;
    }
});
$('#paper_detail_dg').datagrid({
    onClickRow: function(index, row) {
        currentRow2 = row;
        currentIndex2 = index;
    }
});
//获取datagrid点击状态 end
// 查看试题
function previewPaper() {
    if (currentRow === undefined) {
        msgTipBox("请选择一份试题")
    } else {
        var paper_name = currentRow.paper_name;
        //根据试题名称请求试题内容
        openPanel();
        // var data = {
        //     'total': 2,
        //     'rows': [{
        //         question_type: "选择题",
        //         question_title: '1、数据库（DB），数据库系统（DBS）和数据库管理系统（DBMS）之间的关系是（   　）。#A.　DBS包括DB和DBMS#B.　DBMS包括DB和DBS  #C.　DB包括DBS和DBMS  # D.　DBS就是DB，也就是DBMS',
        //         question_id: "0001",
        //         question_score: "2",
        //         question_answer: "A"
        //     }, {
        //         question_type: "填空题",
        //         question_title: '3、 用户选作元组标识的一个候选码为主码时，其属性不能取_____',
        //         question_id: "0002",
        //         question_score: "2",
        //         question_answer: "空值"
        //     }]
        // };
        $.ajax({
            url: '根据试题名称请求试题内容url',
            type: "post",
            data: {
                paper_name: paper_name
            },
            success: function(data) {
                $('#paper_detail_dg').datagrid('loadData', data);
            }
        })

    }
}

function subscribePaper() {
    if (currentRow === undefined) {
        msgTipBox("请选择一份试题")
    } else {
        var paperName = currentRow.paper_name;
        //根据试题名称 发布试题
        $('#dd').dialog({
            closed: false,
        });
        $(".dd").css("display", "none");
        $("#paper_dd").css("display", "table");
        $(".dialog-button").find("a").eq(0).bind("click", function() {
            var paperStart = $(".textbox-value")[2].value;
            var paperEnd = $(".textbox-value")[3].value;
            if (paperStart === "" || paperEnd === "") {
                msgTipBox("时间不能为空");
            } else {
                var data = {
                    paper_name: paperName,
                    paper_start: paperStart,
                    paper_end: paperEnd
                };
                // 提交数据：data= {paper_name: "数据库试题1", paper_start: "11/23/2016 10:40:38", paper_end: "11/22/2016 10:40:40"}
                // 服务器修改 该paper发布状态，返回提示 msg.success是否为true
                $.ajax({
                    url: '根据试题名称 发布试题',
                    type: "post",
                    success: function(msg) {
                        data["paper_status"] = "发布中";
                        if (msg.success) {
                            //提示发布成功
                            msgTipBox("发布成功,请关闭窗口");
                            $("#paper_dg").datagrid('updateRow', {
                                index: currentIndex,
                                row: data
                            });
                        }
                    }
                }); //ajax end

            }

        });
        $(".dialog-button").find("a").eq(1).bind("click", function() {
            $('#dd').dialog({
                closed: true,
            });

        });
    }
}
// 修改试题
function modifyQuestion() {
    if (currentRow2 === undefined) {
        msgTipBox("请选择要修改的题目")
    } else {
        openPanel();
        var paper_name = currentRow.paper_name;
        var question_id = currentRow2.question_id;
        //根据试卷名称 和试题id修改试题
        $('#dd').dialog({
            closed: false,
        });
        $(".dd").css("display", "none");
        $("#paper_detail_dd").css("display", "table");
        var inputs = $("#paper_detail_dd").find("input[type='text']");
        inputs.each(function(index, item) {
            var $item = $(item);
            $item.val(currentRow2[$item.attr("name")])
        });
        if (currentRow2.question_type === "选择题") {
            $("#paper_detail_dd .choice").css("display", "table-row");
            var title = currentRow2["question_title"].split("#");
            console.log(title)
            var textareas = $("#paper_detail_dd").find("textarea");
            textareas.each(function(index, item) {
                var $item = $(item);
                $item.val(title[index]);
            });
        } else {
            $("#paper_detail_dd").find("textarea[name='question_title']").val(currentRow2["question_title"]);
            $("#paper_detail_dd .choice").css("display", "none");
        }
        $(".dialog-button").find("a").eq(0).bind("click", function() {
            var inputs = $("#paper_detail_dd").find("input[type='text']");
            var row = {};
            inputs.each(function(index, item) {
                var $item = $(item);
                row[$item.attr("name")] = $item.val();
            });
            // 添加选项里的信息
            var textareas = $("#paper_detail_dd").find("textarea");
            textareas.each(function(index, item) {
                var $item = $(item);
                row['question_title'] += $item.val() + "#";
            });

            // 提交修改试题申请
            // 提交json数据
            // 浏览器提交数据：row={question_answer: "答案答案答案",question_id: "0002",question_score: "2",question_title: "3、 用户选作元组标识的一个候选码为主码时，其属性不能取_____####"}
            // 服务器返回提示信息：msg.success是否为true
            $.ajax({
                url: "修改试题url",
                type: "post",
                data: row,
                success: function(msg) {
                    if (msg.success) {
                        msgTipBox("修改成功");
                        $("#paper_detail_dg").datagrid('updateRow', {
                            index: currentIndex2,
                            row: row
                        });
                    }
                }
            }); // ajax end
        });
        $(".dialog-button").find("a").eq(1).bind("click", function() {
            $('#dd').dialog({
                closed: true,
            });
        });

    }
};

function editPaper() {
    // body...
}

// 附加功能 日历
$('#calender').calendar({
    current: new Date()
});

function msgTipBox(message) {
    $("#msgTipBox").html(message + "<div class=\"close\">×</div>").css("display", "block");
    $("#msgTipBox .close").bind("click", function() {
        $("#msgTipBox").css("display", "none");
    });
}
