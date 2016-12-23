$(function() {
    // 全选功能
    $(".selectAll").click(function() {
        var $chk = $(this).parents("thead").siblings("tbody").find("input[type='checkbox']");
        var $this = $(this);
        if ($this.is(":checked")) {

            $chk.each(function(item) {
                $(this).attr("checked", true);
            });
        } 
    });
    // 全选功能 end
});
$(function(){
    $(".mainNav").find(".dropdown-toggle").click(function() {
         $(this).parent().find(".dropdown-menu").slideToggle(300);
     });
});
function refresh() {
    window.location.reload();
}
function alertMsg(status, msg) {
    var $alertDiv = $("<div class='alert'></div>");
    var $alertInfo;
    if (status === "success") {
        $alertInfo = $("<div class='alert-info alert-success'>" + msg +
            "<a href='#' class='close' data-dismiss='alert'>&times;</a></div>");
    } else {
        $alertInfo = $("<div class='alert-info alert-danger'>" + msg +
            "<a href='#' class='close' data-dismiss='alert'>&times;</a></div>");
    }
    $alertDiv.append($alertInfo);
    $('body').append($alertDiv);
    $(".alert").hide().fadeIn(200)
}

$(function(){
      $(".close").click(function() {
        $('.alert').alert()

    });
});

function showPanel(panel_id){
    $(".panel").addClass("hide");
    $(panel_id).removeClass("hide");
    $(".panel").find(".table").addClass("hide");
    $(panel_id).find(".main-table").removeClass("hide");
    //隐藏删除按钮
    $(".btn_hide").addClass('hide');
}
// 切换子面板展示区
function showTable(tbl_id) {

    var $tbl = $(tbl_id);
    if($tbl.hasClass("main-table")){
        $tbl.find(".btn_hide").addClass("hide");
    }
    $tbl.parent().find(".table").addClass("hide");
    $tbl.removeClass("hide");

}

