window.onload=function(){
    $.ajax({
        url:null,
        type:"get",
        success:function(data){
            for(var i=0;i<data.length;i++){
                var option=document.createElement("option");
                option.setAttribute("value",data.pno);
                var text=document.createTextNode(data.pname);
                option.appendChild(text);
                document.getElementsByTagName("select")[0].appendChild(option);
            }
        }
    });
    $("button").click(function(){
        $.ajax({
            url:null,
            data:$("option:selected").attr("value"),
            type:"post"
        });
    })
};