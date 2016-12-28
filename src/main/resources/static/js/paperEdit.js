$(function() {
    $('.operation button').bind('click', function(event) {
        var target = $(event.target).data('target');
        var paperContainer = $('.paper-panel');
        var $template = $(target)[0].innerHTML.trim();
        paperContainer.append($template);
        bindBtn();
    });
    $('#btn-submit').bind('click', function() {
        if ($('#btn-submit').html() == '新添试题') {
            $(".paper-panel").html("");
        }
        $('#btn-submit').html('上传试题');
        $('.operation button').each(function(index, item) {
            item.disabled = false;
        });
        var $paperContentList = getPaperContent();
        if ($paperContentList != null) {

            // [{"questionTitle":"选择题1#选项A#选项B#选项C#选项D#","questionType":"选择题","questionAnswer":"A","questionScore":"3"},{"questionTitle":"选择题2#选项A#选项B#选项C#选项D#","questionType":"选择题","questionAnswer":"C","questionScore":"3"},{"questionTitle":"判断题1","questionType":"判断题","questionAnswer":"true","questionScore":"2"},{"questionTitle":"填空题1","questionType":"填空题","questionAnswer":"这道填空题的答案是。。。","questionScore":"3"},{"questionTitle":"简答题1","questionType":"简答题","questionAnswer":"这道简答题我不会做","questionScore":"5"}]
            var $inputs = $('#primaryPaperInfo').find('input');
            var $paperName, $paperStart, $paperEnd;

            $paperName = $inputs[0].value;
            $paperStart = $inputs[1].value;
            $paperEnd = $inputs[2].value;
            var postdata = { 'paperName': $paperName, 'paperStart': $paperStart, 'paperEnd': $paperEnd, 'paperContentList': $paperContentList };
            console.log(JSON.stringify(postdata));

            $.ajax({
                url: '/createPaper',
                type: 'post',
                contentType: "application/json; charset=utf-8",
                data:JSON.stringify(postdata),
                success: function(msg) {
                    if (msg.success) {
                        alertMsg('Info', '上传成功');
                        $('#btn-submit').html('新添试题');
                        $('.operation button').each(function(index, item) {
                            item.disabled = true;
                        });
                    } else {
                        alertMsg('Warning', 'There was a INTERNAL ERROR');
                    }
                },
                error: function() {
                    alertMsg('Info', '上传成功');
                    $('#btn-submit').html('新添试题');
                    $('.operation button').each(function(index, item) {
                        item.disabled = true;
                    });
                    $(".paper-panel button").each(function(index, item) {
                        item.disabled = true;
                    });
                    $(".paper-panel input").each(function(index, item) {
                        item.disabled = true;
                    });
                    $(".paper-panel textarea").each(function(index, item) {
                        item.disabled = true;
                    });

                }
            })


        }
    });

    $('#datetimepicker0').datetimepicker({
        // viewMode: 'years',
        format: "YYYY-MM-DD HH:mm:ss"
    });
    $('#datetimepicker0').data("DateTimePicker").minDate(new Date());
    $('#datetimepicker1').datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss'
    });
    $("#datetimepicker0").on("dp.change", function(e) {
        $('#datetimepicker1').data("DateTimePicker").minDate(e.date);
    });
});



function getPaperContent() {

    if (document.forms.length !== 0) {

        var $forms = document.forms;

        var paperContentList = [];
        var $qusetionId, $questionTitle,
            $questionType, $questionAnswer, $questionScore;
        for (var i = 0; i < $forms.length; i++) {
            var paperContent = {};
            $questionTitle = ""; //!!!清空

            var $titles = $forms[i].elements['questionTitle'];

            if (Object.prototype.toString.call($titles) === "[object RadioNodeList]") {
                var label = ['', '#A.', '#B.', '#C.', '#D.'];
                for (var j = 0, len = $titles.length; j < len; j++) {
                    $questionTitle += label[j] + $titles[j].value;
                }
            } else {
                $questionTitle = $titles.value;
            }
            $questionType = $($forms[i]).data('type');
            $questionScore = $forms[i].elements['questionScore'].value;
            $questionAnswer = $forms[i].elements['questionAnswer'].value;

            paperContent["questionTitle"] = $questionTitle;
            paperContent["questionType"] = $questionType;
            paperContent["questionAnswer"] = $questionAnswer;
            paperContent["questionScore"] = $questionScore;
            paperContentList.push(paperContent);

        }
        console.log(JSON.stringify(paperContentList));

        return paperContentList;
    } else {
        return null;
    }

}


function bindBtn() {
    $(".btn-del").bind("click", function(event) {
        $(event.target).closest('.panel-info').remove();
    });
    $(".btn-save").bind("click", function(event) {
        $(event.target).closest('.panel-info').find('input').attr('disabled', 'true');
        $(event.target).closest('.panel-info').find('textarea').attr('disabled', 'true');
    });
    $(".btn-modify").bind("click", function(event) {
        $(event.target).closest('.panel-info').find('input').each(function(index, item) {
            item.disabled = false;
        });
        $(event.target).closest('.panel-info').find('textarea').each(function(index, item) {
            item.disabled = false;
        });
    });
}

function alertMsg(title, msg) {
    var $template = $('#alert-template')[0].innerHTML;
    var type;
    if (title == 'Warning') { type = 'danger' } else if (title == 'Info') { type = 'warning' };
    $template = $template.replace('{ALERTTYPE}', type).replace('{ALERTTITLE}', title).replace('{ALERTMSG}', msg);
    $('#alertBox').append($template);
    setTimeout(function() {
        $('.alert').alert('close');
    }, 1000);
}
