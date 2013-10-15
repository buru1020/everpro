var session;

$(document).ready(function() {
	loadSessionInfo();
	$("#mainHeader").load("header.html", function() { header_onload(); } );
	$("#mainSidebar").load("sidebar.html", function() { sidebar_onload(); } );
	$("#mainFooter").load("footer.html");
	
	document.body.addEventListener("projectManagement", function(event) {
		$("#mainContent").load("project/project.html", function() { project_onload(); } );
	});
	
	document.body.addEventListener("clickProject", function(event) {
		$("#mainContent").load("project/project.html", function() { project_onload(event.projectNo); } );
	});
	
	document.body.addEventListener("memberManagement", function(event) {
		$("#mainContent").load("member/member.html", function(){ memberjs_onload(); });
	});
	
	document.body.addEventListener("updateMyInfo", function(event) {
		$("#mainContent").load("member/member.html", function(){ viewDetailMember($("#sessionEmail").val()); });
	});
	
	document.body.addEventListener("taskManagement", function(event) {
		$("#mainContent").load("task/task.html");
	});
	
//	document.body.addEventListener("feed", function(event) {
//		$("#mainContent").load("feed/feed.html");
//	});
}); 

function loadSessionInfo() {
	$.ajax("auth/loginInfo.do", {
		type: "GET",
		dataType: "json",
		async: false,
		success: function(result) {
			if (result.status == "success") {
				session = result.session;
				
			} else {
				session = result.session;
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
													var event = new MouseEvent("clickProject", {
														'view': window,
														'bubbles': true,
														'cancelable': true
													});
													event.projectNo = $(this).attr("data-no");
													this.dispatchEvent(event);
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