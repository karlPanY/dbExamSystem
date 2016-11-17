$(function() {
    var rowNavs_li = $(".rowNav li");
    var basicUrl = "../images/";
    var icons = [basicUrl + '4_info.ico',
        basicUrl + '5_subscribe.ico',
        basicUrl + '6_reload.ico'
    ]
    for (var i = 0, len = rowNavs_li.length; i < len; i++) {
        $(rowNavs_li[i]).before().css("background", "url(" + icons[i] + ") no-repeat left center")
    }
});

function paperPreview(paperNo) {
    //处理预览信息

    $.post(url, {
        paperNo: paperNno
    }, function(msg) {
        if (msg.success) {
            var $tbody = $("#tbl_preview").find("tbody");
            $tbody.remove("tr");
            var $tr, $td0, $td1, $td2, $td3, $td4, $td5;
            $("#tbl_preview").find("caption.title").html(paperNo);
            var paper=msg.paper;

            for (var i = 0, len = paper.length; i < len; i++) {
                $tr = $("<tr></tr>");
                var question = paper[i];
                $td0 = "<td>" + (i + 1) + "</td>"
                $td1 = "<td>" + question.qType + "-" + question.qNo + "</td>";
                $td2 = "<td>" + question.qTitle + "</td>";
                $td3 = "<td>" + question.qAnswer + "</td>";
                $td4 = "<td>" + question.qScore + "</td>";
                $td5 = "<td><button class='btn btn-danger'>修改</button></td>";

                $tr.append($td0);
                $tr.append($td1);
                $tr.append($td2);
                $tr.append($td3);
                $tr.append($td4);
                $tr.append($td5);
                $tbody.append($tr);
            }
            showPanel("#paperPreview");
        } else {
            // 弹出错误信息 msg.error
        }
    });
}

function paperModify() {

    $modal = $(".modal");
    var pInfo = $modal.find(".modal-title").html().split("-");
    var qTitle = $modal.find("input[name='qTitle']").val();
    var qAnswer = $modal.find("input[name='qAnswer']").val();
    var qScore = $modal.find("input[name='qScore']").val();
    $.post(url, {
        paperNo: pInfo[0],
        qType: pInfo[1],
        qNo: pInfo[2],
        qTite: qTitle,
        qAnswer: qAnswer,
        qScore: qScore
    }, function(msg) {
        if (msg.success) {
            alertMsg("success", "成功修改,关闭窗口后请刷新页面")
        } else {
            alertMsg("error", "修改不成功")
        }
    })


}
$(function() {


    $(".modal").find("button.close").click(function() {
        $('#myModal').slideUp("200").addClass('hide')
    });
    $(".modal").find("button#btn-cancle").click(function() {
        $('#myModal').slideUp("200").addClass('hide')
    });
    $("#tbl_preview").bind("click", function(event) {

        var $target = $(event.target);
        var $td = $target.parent("td").siblings("td");
        var $pNo = $("#pNo_pre").html();
        var $qType = $td.eq(1).html();
        var $qTitle = $td.eq(2).html();
        var $qAnswer = $td.eq(3).html();
        var $qScore = $td.eq(4).html();
        var $modal = $(".modal");
        $modal.find(".modal-title").html($pNo + "-" + $qType);
        $modal.find("input[name='qTitle']").val($qTitle);
        $modal.find("input[name='qAnswer']").val($qAnswer);
        $modal.find("input[name='qScore']").val($qScore);
        $(".modal").removeClass("hide").hide().slideDown(500);
    });
})


// 发布试题
//将要发布的试题编号传到后台
function subscribe() {

    showPanel("#paperM");
    var $btn = $("#btn_subPaper");
    $btn.removeClass('hide').hide().slideDown(100);
    $btn.bind("click", function() {
        var paperNos = [];
        var $chk = $(".subscribe").find('input:checked');
        $chk.each(function(item) {
            paperNos.push($(this).attr("name"));
        });
        var url;
        $.post(url, {
            paperNos: paperNos
        }, function(msg) {
            if (msg.success) {           
                alertMsg("success", "成功发布")
            } else {
                alertMsg("error", "发布出错")
            }
        });
        $("input[type='checkbox']").attr("checked", false);
    })
}
