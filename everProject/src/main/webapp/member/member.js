memberjs_onload = function() {
	if ( $("#sessionLevel").val() != 1 ) {
			$("#btnNewForm").css("display", "none");
	}
	listMember();
	
	$("#btnNewForm").click(function(event){
		$("#list").css("display", "none");
		$("#pwchange").css("display", "none");
		
		/*var resetEvent = new MouseEvent('click', {
		    'view': window,
		    'bubbles': true,
		    'cancelable': true
		});*/
//		$("#btnReset").trigger(resetEvent);
//		(resetEvent);
		$("#btnReset").click();
		
		$(".new-member").css("display", ""); // 등록버튼 보이기
		$(".view-member").css("display", "none"); // 수정버튼, 번호입력창 감추기
		$("#view").css("display", ""); // view 섹션 보이기
	});
	
	$("#btnList").click(function(event){
		$("#viewemail").css("readonly", "readonly");
		$("#view").css("display", "none");
		$("#pwchange").css("display", "none");
		$("#list").css("display", "");
	});
	
	$("#btnAdd").click(function(){
		addMember();
	});
	
	$("#btnUpdate").click(function(){
		updateMember();
	});
	
	$("#btnDelete").click(function(){
		deleteMember();
	});

	$("#btnpwChange").click(function(){
		$("#view").css("display", "none");
		$("#list").css("display", "none");
		$("#pwchange").css("display", "");
	});
	$("#btnpwChange").click(function(){
		ChangePassword();
	});
};

function ChangePassword() {
	$.ajax("member/passwordChange.do", {
		type:"POST",
		data: {
			oldPassword: $("oldPassword").val(),
			newPassword: $("newPassword").val(),
			newPassword2: $("newPassword2").val()
		},
		success: function(result) {
			if(result.status == "success") {
				viewMember();
				$("#view").css("display", "");
				$("#list").css("display", "none");
				$("#pwchange").css("display", "none");
			} else {
				alert("변경될 암호가 일치하지 않거나\n " +
						"패스워드를 잘못입력했습니다.");
				console.log(result.data);
			}
		},
		error: function(message) {
			alert("서버와의 통신이 원활하지 않습니다. \n잠시후 다시 시도하세요.");
		}
	});	
}

function listMember() {
	$.ajax("member/list.do", {
		type:"GET",
		success: function(result) {
			if(result.status == "success") {
				var members = result.data;
//				console.log(result.data);
				$(".data-row").remove();
				var table = $("#memberTable");
				for (var i in members) {
					$("<tr>")
					.attr("class", "data-row")
					.append($("<td>").html( members[i].email ))
					.append($("<td>").append(
						$("<a>").html(members[i].name)
						.attr("href", "#")
						.attr("member-email", members[i].email)
						.click(function(event){
							viewDetailMember($(this).attr("member-email"));
						})))
					.append($("<td>").html( members[i].tel ))
					.append($("<td>").html( members[i].blog ))
					.appendTo(table);
				}
			} else {
				alert("실행중 오류발생!");
				console.log(result.data);
			}
		},
		error: function(message) {
			alert("서버와의 통신이 원활하지 않습니다. \n잠시후 다시 시도하세요.");
		}
	});
}

function updateMember() {
	$.ajax("member/update.do", {
		type:"POST",
		data: {
			email: $("#viewemail").val(),
			name: $("#viewname").val(),
			password: $("#viewpassword").val(),
//			photo: $("#viewphoto").val(),
			tel: $("#viewtel").val(),
			blog: $("#viewblog").val(),
			basicAddress: $("#basicAddress").val(),
			detailAddress: $("#detailAddress").val(),
			tag: $("#viewtag").val()
		},
		success: function(result) {
			if(result.status == "success") {
				listMember();
				$("#view").css("display", "none");
				$("#pwchange").css("display", "none");
				$("#list").css("display", "");
			} else {
				alert("실행중 오류발생!");
				console.log(result.data);
			}
		},
		error: function(message) {
			alert("서버와의 통신이 원활하지 않습니다. \n잠시후 다시 시도하세요.");
		}
	});	
}

function addMember() {
	$.ajax("member/add.do", {
		type:"POST",
		data: {
			email: $("#viewemail1").val(),
			name: $("#viewname").val(),
			password: $("#viewpassword").val(),
			tel: $("#viewtel").val(),
			blog: $("#viewblog").val(),
			basicAddress: $("#basicAddress").val(),
			detailAddress: $("#detailAddress").val(),
			tag: $("#viewtag").val()
		},
		success: function(result) {
			if(result.status == "success") {
				listMember();
				$("#view").css("display", "none");
				$("#pwchange").css("display", "none");
				$("#list").css("display", "");
			} else {
				alert("실행중 오류발생!");
				console.log(result.data);
			}
		},
		error: function(message) {
			alert("서버와의 통신이 원활하지 않습니다. \n잠시후 다시 시도하세요.");
		}
	});
}

function viewDetailMember(email) {
	$.ajax("member/view.do?email=" + email, {
		type:"GET",
		success: function(result) {
			if(result.status == "success") {
				var member = result.data;
				console.log(result.data.email);
				console.log($("#sessionEmail").val());
				console.log($("#email"));
				$("#viewemail").val(member.email);
				$("#viewname").val(member.name);
				$("#viewpassword").val("");
				$("#viewtel").val(member.tel);
				$("#viewblog").val(member.blog);
				$("#basicAddress").val(member.basicAddress);
				$("#detailAddress").val(member.detailAddress);
				$("#viewtag").val(member.tag);
				$("#viewlevel").val(member.level);
				
				$("#list").css("display", "none");
				$("#pwchange").css("display", "none");
				$(".new-member").css("display", "none");
				if ( $("#sessionEmail").val() == member.email || $("#sessionLevel").val() == 1 ) {
					$(".view-member").css("display", "");
				} else {
					$(".view-member").css("display", "none");
				}
				$("#view").css("display", "");
			} else {
				alert("실행중 오류발생!");
				console.log(result.data);
			}
		},
		error: function(message) {
			alert("서버와의 통신이 원활하지 않습니다. \n잠시후 다시 시도하세요.");
		}
	});
}

function deleteMember() {
	$.ajax("member/delete.do?email=" + $("#viewemail").val(), {
		type:"GET",
		success: function(result) {
			if(result.status == "success") {
				listMember();
				$("#view").css("display", "none");
				$("#pwchange").css("display", "none");
				$("#list").css("display", "");
			} else {
				alert("실행중 오류발생!");
				console.log(result.data);
			}
		},
		error: function(message) {
			alert("서버와의 통신이 원활하지 않습니다. \n잠시후 다시 시도하세요.");
		}
	});
}

