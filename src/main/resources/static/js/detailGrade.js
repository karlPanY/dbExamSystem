 $(function() {

     $("#gradeD").find(".dropdown-toggle").click(function() {
         $(this).parent().find(".dropdown-menu").toggle(400);
     });
     $("#gradeD").find(".dropdown-menu button").click(function(event) {
         var $target = $(event.target);

         var $paperName= $target.html();
         $("caption>span.title").html($paperName);
        $.post(url,{paperName:$paperName},function(msg){
            if(msg.success){

                var $tbody=$("<tbody></tbody>");
                         for (var i = 0; i < msg.stuGrades.length; i++) {
                            var $td0 = $("<td></td>"),
                             $td1 = $("<td></td>"),
                             $td2 = $("<td></td>"),
                             $td3 = $("<td></td>"),
                             $td4 = $("<td></td>");
                             $td0.html((i + 1));
                             $td1.html(stuGrades[i][0]);
                             $td2.html(stuGrades[i][1]);
                             $td3.html(stuGrades[i][2]);
                             $td4.html(stuGrades[i][3]);
                             var $tr = $("<tr></tr>");
                             $tr.append($td0);
                             $tr.append($td1);
                             $tr.append($td2);
                             $tr.append($td3);$tr.append($td4); 
                             $tbody.append($tr);          
                         }
                         $(".main-table").find("tbody").replaceWith($tbody)

            }else{
                alertMsg("error","发生不明错误")
            }
        })
       

         
     })
 });
