$(function() {
    // 小图标动态添加
    var rowNavs_li = $(".rowNav li");
    var basicUrl = "../images/";
    var icons = [basicUrl + '0_info.ico',
        basicUrl + '1_add.ico',
        basicUrl + '2_delete.ico',
        basicUrl + '3_reload.ico'
    ]
    for (var i = 0, len = rowNavs_li.length; i < len; i++) {
        $(rowNavs_li[i]).before().css("background", "url(" + icons[i] + ") no-repeat left center")
    }
    // 小图标动态添加 end

});

function addStuInfo() {
    var $tbl = $("#addStuInfo");
    var sname = $tbl.find("input[name='sname']").val();
    var sno = $tbl.find("input[name='sno']").val();
    var ssex = $tbl.find("input[name='ssex']:checked").val();
    var tno = $tbl.find("input[name='tno']").val();
    $.post(url, {
         sname: sname, sno: sno, ssex: ssex, tno: tno 
    }, function(msg) {
        if (msg.success) {
            // 后台返回成功信息
            alertMsg("success", "添加成功，请刷新");
        } else {
            alertMsg("error", "添加失败");
        }
    });
}

function delStuInfo() {

    var $btn = $("#btn_delStu");
    showTable("#StuInfo");
    $btn.removeClass("hide");
    $btn.bind("click", function() {
        var $chk = $(".chk:checked");
        var sNos = [];
        $chk.each(function(item) {
            sNos.push($(this).attr("name"));
        });
        $.post(url, {
            sNos: sNos
        }, function(msg) {
            if (msg.success) {
                alertMsg("success", "删除成功，请刷新页面");
            } else {
                alertMsg("error", "删除失败");
            }
        });
        $("input[type='checkbox']").attr("checked", false);
    });
}

function getStuGrade(stuSno) {

    var url = null;
    $.post(url, {
        stuSno: stuSno
    }, function(msg) {
        if (msg.success) {
            var $tbody = $("#stuGradeDetail").find("tbody");       
            var $tr, $td0, $td1, $td2;
            for (var i = 0; i < msg.stuGrades.length; i++) {
                $tr = $("<tr></tr>");
                $td0 = "<td>" + stuGrades[i].paperName + "</td>";
                $td1 = "<td>" + stuGrades[i].grade + "</td>";
                $td2 = "<td>" + stuGrades[i].rank + "</td>";
                $tr.append($td0);
                $tr.append($td1);
                $tr.append($td2);
                $tbody.append($tr);
            }
            showTable("#stuGradeDetail");
        } else {
            alertMsg("error", "发生不明错误");
        }
    });
}
