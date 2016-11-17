$(function() {

	$.ajax({
		url: null,
		type:"get",
		success: function(data) {
			for (var i = 0; i< data.types.length; i++) {
				insertType(data.types[i], data.questions[i]);
			}
		}
	});



});

$(function() {

	var theCounter = new Counter("#timer", 2*1000, 0);
	theCounter.countDown();


	$("#sidebar").affix({
		offset: {
			top: 125
		}
	});
	$("#sidebar").find("a").click(function(event) {

		console.log(event)
		event.preventDefault()
		var $target = $($(event.target).attr("href"));
		$("body").animate({
			scrollTop: $target.offset().top - 100
		}, 500);
	});
});


function insertType(type,data) {

	for (var k = 0, len = data.length - 1; k < len; k++) {
		var a = $("#" + type).find(".question").eq(0).clone();
		$("#" + type).append(a);
	}
	var questions = $("#" + type).find(".question");
	for (var i = 0; i < questions.length; i++) {
		$(questions[i]).find(".title").html(data[i].split("#")[0]);
		if (type === 'type1') {
			var inputs = $(questions[i]).find("input");
			for (var j = 0; j < inputs.length; j++) {
				$(inputs[j]).attr("name", "Q" + (i + 1))
				$(inputs[j]).after(data[i].split("#")[j + 1]);
			}
		} else {
			var textareas = $(questions[i]).find("textarea");
			for (var j = 0; j < textareas.length; j++) {
				$(textareas[j]).attr("name", "Q" + (i + 1))
			}
		}
	}
}

// 计时器
function Counter(id, start, end) {
	this.id = id;
	this.current = start;
	this.end = end;
	this.countDown = function() {
		if (this.current < this.end) {
			
		  this.countDown = null;
		

			var contents=$("#exam_body").find(".content");
			var datas={};
			contents.each(function(index,item){
				if(index===0){
					var data=$(item).find("input:checked").serialize();
					var id=$(item).attr("id");
					datas["'"+id+"'"]=data;
				}else{
                 var data=$(item).find("textarea").serialize();
					var id=$(item).attr("id");
					datas["'"+id+"'"]=data;
				}
            
			});

		 	//提交试卷 
          $.ajax({
          	type:"post",
          	url:null,
          	data:datas,
          	success:function(data){
          		alertMsg("success", "考试结束，客观题成绩为"+data);
          		window.history.back(-1);
          	}

          })

		} else {
			var hour = parseInt(this.current / 1000 / 60 / 60);
			var min = parseInt(this.current / 1000 % (60 * 60) / 60);
			var sec = parseInt(this.current / 1000 % (60 * 60) % 60);
			$(this.id).html(checkTime(hour) + ":" + checkTime(min) + ":" + checkTime(sec));
			this.current = this.current - 1000;
			setTimeout(bind(this, this.countDown), 1000);
		}
	};
	return this;
}
//为什么 要这样绑定！！ 底层js！！！
function bind(obj, method) {
	return function(event) {
		method.call(obj, event || window.event);
	}
}

function checkTime(i) {
	if (i < 10) {
		i = "0" + i;
	}
	console.log(i);
	return i;
}


function alertMsg(status, msg) {
	var $alertDiv = $("<div class='alert'></div>");
	var $alertInfo;
	if (status === "success") {
		$alertInfo = $("<div class='alert-content alert-success'>" +
			"<a href='#' class='close' data-dismiss='alert'>&times;</a><strong>" + msg + "</strong></div>");
	} else {
		$alertInfo = $("<div class='alert alert-warning'>" +
			"<a href='#' class='close' data-dismiss='alert'>&times;</a><strong>" + msg + "</strong></div>");
	}
	$alertDiv.append($alertInfo)
	$('body').append($alertDiv);
	$(".alert").hide().fadeIn(200)
}

$(function() {
	$(".close").click(function() {
		$('.alert').alert();
	});
});