$(document).ready(function() {
	$("#mainHeader").load("header.html", function() { header_onload(); } );
	$("#mainSidebar").load("sidebar.html", function() { sidebar_onload(); } );
	$("#mainFooter").load("footer.html");
	
	document.body.addEventListener("projectManagement", function(event) {
		$("#mainContent").load("project/project.html", function() { project_onload(); } );
	});
	
//	document.body.addEventListener("memberManagement", function(event) {
//		$("#mainContent").load("member/member.html");
//	});
//	
//	document.body.addEventListener("taskManagement", function(event) {
//		$("#mainContent").load("task/task.html");
//	});
//	
//	document.body.addEventListener("feed", function(event) {
//		$("#mainContent").load("feed/feed.html");
//	});
}); 

function loadLoginInfo() {
	$.ajax("auth/loginInfo.do", {
		type: "GET",
		success: function(result) {
			if (result.status == "success") {
				$("#userName").html("Hello " + result.data.name);
				$("#userTel").html(result.data.tel);
				$("#userEmail").html(result.data.email);
				if (result.data.photoPath != undefined) {
					$("#memberPhoto").html(result.data.photoPath);
				} else {
					$("#memberPhoto").setAttribute = "images/test01.png";
				}
			} else {
				location.href = "auth/login.html";
			}
		},
		error: function(message) {
			alert("서버와의 통신이 원활하지 않습니다.\n잠시 후 다시 시도하세요.");
		}
	});
}

function loadMyProjects() {
	$.ajax("project/myProjectList.do", {
		type: "GET",
		success: function(result) {
			if (result.status == "success") {
				var myProjectList = result.data;
				var projectTitle = "";
				$(".data-row").remove();
				
				for( var i = 0; i <  myProjectList.length; i++ ) {
					console.log(myProjectList[i].title);
					projectTitle = myProjectList[i].title;
					if (myProjectList[i].level == 0) {
						projectTitle += "★";
					}
					$("<li>")
							.attr("class", "data-row")
							.append("<a>").attr("href", "#")
												.attr("data-no", myProjectList[i].no)
												.html( projectTitle )
												.click(function() {
													alert("click---" + $(this).attr("data-no"));
												})
							.appendTo( $(".ulProject") );
				}
			} else {
				location.href = "auth/login.html";
			}
		},
		error: function(message) {
			alert("서버와의 통신이 원활하지 않습니다.\n잠시 후 다시 시도하세요.");
		}
	});
}