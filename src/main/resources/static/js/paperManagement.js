var toolbar = [{
    text: '查看试卷',
    iconCls: 'icon-search',
    handler: previewPaper
}, {
    text: '发布试卷',
    iconCls: 'icon-edit',
    handler: subscribePaper
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

    init();

});

function init() {
    var paperStatus;
    var paperEnd;
    $.ajax({
        url: '/getAllPapers',
        type: "get",
        success: function(result) {
            var $temp = $('#teacher_info')[0].innerHTML.trim();
            $temp = $temp.replace('{TEACHERID}', result["teacherId"])
                .replace('{TEACHERNAME}', result["teacherName"]);
            $('#teacher_info').html($temp);
            var data = { "total": result['data'].length, 'rows': result['data'] };
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
        },
        error: function() {
            //test
            var data = [{
                paper_id: 1,
                paper_name: '数据库试题1',
                paper_start: "11/17/2016 23:30:18",
                paper_end: '11/17/2016 23:30:18',
                paper_status: "1" //已发布
            }, {
                paper_id: 2,
                paper_name: '数据库试题2',
                paper_start: "12/27/2016 16:30:18",
                paper_end: '12/27/2016 23:30:18',
                paper_status: "1" //已发布
            }, {
                paper_id: 3,
                paper_name: '数据库试题3',
                paper_start: "",
                paper_end: "",
                paper_status: "0" //未发布
            }];
            var result = { "teacherId": 100, "teacherName": '教师100', "data": data };
            var $temp = $('#teacher_info')[0].innerHTML.trim();
            $temp = $temp.replace('{TEACHERID}', result["teacherId"])
                .replace('{TEACHERNAME}', result["teacherName"]);
            $('#teacher_info').html($temp)
            var data = { "total": result['data'].length, 'rows': result['data'] };
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
}


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
        msgTipBox("请选择一份试题");
    } else {
        var paper_id = currentRow.paper_id; //测试
        //根据试题 id 请求试题内容
        openPanel();
        $.ajax({
            url: '/getPaperContent/' + paper_id,
            type: "get",
            success: function(result) {
                var data = { 'total': result['total'], 'rows': result['questionInfoList'] };
                $('#paper_detail_dg').datagrid('loadData', data);
            },
            error: function() {
                //test
                var result = {
                    'total': 2,
                    'questionInfoList': [{
                        question_type: "选择题",
                        question_title: '1、数据库（DB），数据库系统（DBS）和数据库管理系统（DBMS）之间的关系是（   　）。#A.　DBS包括DB和DBMS#B.　DBMS包括DB和DBS  #C.　DB包括DBS和DBMS  # D.　DBS就是DB，也就是DBMS',
                        question_id: "0001",
                        question_score: "2",
                        question_answer: "A"
                    }, {
                        question_type: "填空题",
                        question_title: '3、 用户选作元组标识的一个候选码为主码时，其属性不能取_____',
                        question_id: "0002",
                        question_score: "2",
                        question_answer: "空值"
                    }]
                };
                var data = { 'total': result['total'], 'rows': result['questionInfoList'] };
                $('#paper_detail_dg').datagrid('loadData', data);
            }
        });

    }
}

function subscribePaper() {
    if (currentRow === undefined) {
        msgTipBox("请选择一份试题")
    } else {
        var paperId = currentRow.paper_id;
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
                var postdata = {
                    paper_id: paperId,
                    paper_start: transformDate(paperStart),
                    paper_end: transformDate(paperEnd)
                };
                //TODO 根据试题id 发布试题
                $.ajax({
                    url: '/setPaperTime',
                    type: "post",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(postdata),
                    success: function(msg) {
                        alert(msg == 'true');
                        if (msg == 'true') {
                            //提示发布成功
                            msgTipBox("发布成功,请关闭窗口");
                            $('#dd').dialog({
                                closed: true,
                            });
                            $("#paper_dg").datagrid('updateRow', {
                                index: currentIndex,
                                row: postdata
                            });
                        }
                    },
                    error: function() {
                        //test
                        msgTipBox("发布失败,请关闭窗口");
                        $('#dd').dialog({
                            closed: true,
                        });
                        $("#paper_dg").datagrid('updateRow', {
                            index: currentIndex,
                            row: data
                        });
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
            row['question_title'] = '';
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
            console.log(row['question_title'])
            row['type'] = currentRow2.question_type;

            // var postdata={
            //     question_id: 3,
            //     question_answer: "",
            //     question_score: 4,
            //     question_title: "testing11",
            //     type: "填空题"
            // };

            var postdata = JSON.stringify(row);
            $.ajax({
                url: "/changeQuestions",
                type: "post",
                data: postdata,
                contentType: "application/json; charset=utf-8",
                success: function(msg) {
                    if (msg.success) {
                        $('#dd').dialog({
                            closed: true,
                        });
                        msgTipBox("修改成功");
                        $("#paper_detail_dg").datagrid('updateRow', {
                            index: currentIndex2,
                            row: row
                        });
                    }
                },
                error: function() {
                    //test
                    $('#dd').dialog({
                        closed: true,
                    });

                    msgTipBox("修改成功");
                    $("#paper_detail_dg").datagrid('updateRow', {
                        index: currentIndex2,
                        row: row
                    });
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
    //To-do
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

function transformDate(date1) {
    // var date1="12/21/2016 21:55:03"
    // var date2="2016-01-01 11:59:59"
    var part1 = date1.split(" ")[0].split('/');
    var part2 = date1.split(" ")[1];
    var date2 = part1[2] + '-' + part1[0] + '-' + part1[1] + " " + part2;
    return date2;
}
