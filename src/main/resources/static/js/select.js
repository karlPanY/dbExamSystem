$(function() {
    $.ajax({
        url: null,
        type: "get",
        success: function(result) {

            var studentName = result['student_name'];
            var studentId = result['student_id'];
            var paperInfoList = result['paper_info_list'];
            $('.student-info b').attr('id', studentId);
            $('.student-info b').html(studentName);

            // data是数组，所有有待考试的试卷信息，每一项是一个对象 看下面
            // data[i].paper_id;data[i].paper_name;data[i].paper_start;data[i].paper_end;
            $(".paper_num").html(paperInfoList.length);
            var $panelBody = $(".panel-body");
            for (var i = 0; i < paperInfoList.length; i++) {
                var $template = $('#paperInfoTempalte')[0].innerHTML;
                var paperId = paperInfoList[i].paper_id;
                var paperName = paperInfoList[i]["paper_name"];
                var paperStart = dapaperInfoListta[i].paper_start;
                var paperEnd = paperInfoList[i].paper_end;

                $template = $template.replace("{PAPERINDEX}", i + 1).replace("{PAPERID}", paperId).replace("{PAPERNAME}", paperName).replace("{PAPERSTART}", paperStart).replace("{PAPEREND}", paperEnd),
                    $panelBody.append($template);
            }
            $panelBody.find('.paper-info:nth-of-type(1)').addClass('active');
        },
        error: function() {

            var studentName = '廖晓娟';
            var studentId = '201430560243';
            $('.student-info b').attr('id', studentId);
            $('.student-info b').html(studentName);
            var paperInfoList = [{ "paper_id": '试题id1', "paper_name": '数据库考试1', "paper_start": "12/17/2016 23:30:18", "paper_end": "12/18/2016 23:30:18" }, { "paper_id": '试题id2', "paper_name": '数据库考试2', "paper_start": "12/17/2016 23:30:18", "paper_end": "12/18/2016 23:30:18" }, { "paper_id": '试题id3', "paper_name": '数据库考试3', "paper_start": "12/17/2016 23:30:18", "paper_end": "12/18/2016 23:30:18" }];


            $(".paper_num").html(paperInfoList.length);
            var $panelBody = $(".panel-body");
            for (var i = 0; i < paperInfoList.length; i++) {
                var $template = $('#paperInfoTempalte')[0].innerHTML;
                var paperId = paperInfoList[i].paper_id;
                var paperName = paperInfoList[i]["paper_name"];
                var paperStart = paperInfoList[i].paper_start;
                var paperEnd = paperInfoList[i].paper_end;

                $template = $template.replace("{PAPERINDEX}", i + 1).replace("{PAPERID}", paperId).replace("{PAPERNAME}", paperName).replace("{PAPERSTART}", paperStart).replace("{PAPEREND}", paperEnd),
                    $panelBody.append($template);
            }
            $panelBody.find('.paper-info:nth-of-type(1)').addClass('active');
        }
    }); //ajax结束


    $(".next-btn").bind("click", function() {
        var $currentPaper = $('.paper-info.active');
        var nextPaper = $currentPaper.next('.paper-info:not(.active)')[0];
        $(nextPaper).addClass('active');
        $currentPaper.removeClass('active');


    });
    $(".prev-btn").bind("click", function() {
        var $currentPaper = $('.paper-info.active');
        var prevPaper = $currentPaper.prev('.paper-info:not(.active)')[0];
        $(prevPaper).addClass('active');
        $currentPaper.removeClass('active');
    });


    //=============进入考试========== 这里的考试还是你来修改吧
    $(".ok-btn").bind("click", function() {
        var paperId = $(this).attr("id");
        var studentId = $(".student-info").find("b").attr("id");
        console.log('测试数据：' + [paperId, studentId]);
        //加到url中
        $.ajax({
            url: null,
            type: "get",
            data: null
        })
    })

});
