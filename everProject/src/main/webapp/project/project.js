$(function() {
	if (session.level == 1) {
		$("#btnAddProject").css("display", "none");
	}
	
	listProject();
	
	$("#btnAddProject").click(function(event){
		$("#divProjectList").css("display", "none");	// 등록버튼 보이기
		
		$("#btnReset").click();
		$("#h2ProjectView").html("신규 프로젝트 등록");
		
		$(".new-project").css("display", "");	// 수정버튼, 번호입력창 감추기
		$(".view-project").css("display", "none"); // view 섹션 보이기
		$("#divProjectView").css("display", "");
		$("#divProjectMemberView").css("display", "");
	});
	
	$("#btnList").click(function(event) {
		$("#divProjectView").css("display", "none");
		$("#divProjectMemberView").css("display", "none");
		$("#divProjectList").css("display", "");
	});
	
	$("#btnAdd").click(function() {
		addProject();
	});
	
	$("#btnUpdate").click(function() {
		updateProject();
	});
	
	$("#btnDelete").click(function() {
		deleteProject();
	});
	
	$("#btnTask").click(function() {
		var event = new MouseEvent('taskManagement', {
		    'view': window,
		    'bubbles': true,
		    'cancelable': true
		  });
		this.dispatchEvent(event);
	});
	
});

function listProject() {
	$.ajax("project/list.do", {
		type: "GET",
		success: function(result) {
			if (result.status == "success") {
				var projects = result.data;
				
				$(".data-projectTableRow").remove();
				
				for( var i = 0; i < projects.length; i++ ) {
					var plYn = "";
					if (projects[i].plEmail == session.email ) {
						plYn = "★";
					}
					$("<tr>")
							.addClass("data-projectTableRow")
							.attr("data-projectNo", projects[i].no)
							.click(function(event) {
									viewDetailProject( $(this).attr("data-projectNo") );
							})
						.append( $("<td>").text(projects[i].title).css("text-align", "left") )
						.append( $("<td>").text(projects[i].startDate) )
						.append( $("<td>").text(projects[i].endDate) )
						.append( $("<td>").text(projects[i].plName) )
						.append( $("<td>").text( plYn ) )
						.appendTo( $("#tbodyProject") );
				}
			} else {
				alert("실행중 오류 발생");
				console.log(result.data);
			}
		},
		error: function(message) {
			alert("서버와의 통신이 원활하지 않습니다.\n잠시 후 다시 시도하세요.");
		}
	});
}

function addProject(projectNo) {
	$.ajax("project/add.do", {
		type: "POST",
		data: {
			title: $("#projectTitle").val(),
			content: $("#projectContent").val(),
			startDate: $("#projectStartDate").val(),
			endDate: $("#projectEndDate").val(),
			tag: $("#projectTag").val()
		},
		success: function(result) {
			if (result.status == "success") {
				listProject();
				$("#divProjectView").css("display", "none");
				$("#divProjectMemberView").css("display", "none");
				$("#divProjectList").css("display", "");
			} else {
				alert("실행중 오류 발생");
				console.log(result.data);
			}
		},
		error: function(message) {
			alert("서버와의 통신이 원활하지 않습니다.\n잠시 후 다시 시도하세요.");
		}
	});
}

function updateProject(projectNo) {
	$.ajax("project/update.do", {
		type: "POST",
		data: {
			no: $("#projectNo").val(),
			title: $("#projectTitle").val(),
			content: $("#projectContent").val(),
			startDate: $("#projectStartDate").val(),
			endDate: $("#projectEndDate").val(),
			tag: $("#projectTag").val()
		},
		success: function(result) {
			if (result.status == "success") {
				listProject();
				$("#divProjectView").css("display", "none");
				$("#divProjectMemberView").css("display", "none");
				$("#divProjectList").css("display", "");
			} else {
				alert("실행중 오류 발생");
				console.log(result.data);
			}
		},
		error: function(message) {
			alert("서버와의 통신이 원활하지 않습니다.\n잠시 후 다시 시도하세요.");
		}
	});
}

function viewDetailProject(projectNo) {
	$.ajax("project/view.do?projectNo=" + projectNo, {
		type: "GET",
		success: function(result) {
			if (result.status == "success") {
				var project = result.project;
				$("#projectNo").val(project.no);
				$("#projectTitle").val(project.title);
				$("#projectContent").val(project.content);
				$("#projectStartDate").val(project.startDate);
				$("#projectEndDate").val(project.endDate);
				$("#projectTag").val(project.tag);
				
				var projectMemberList = result.projectMemberList;
				for( var i = 0; i < projectMemberList.length; i++ ) {
					console.log(projectMemberList[i]);
					var projectMemberName = projectMemberList[i].name;
					if (projectMemberList[i].projectLevel == 0) {
						projectMemberName += " ★";
					}
					$("<tr>").addClass("odd gradeX")
							.append( $("<td>").text(projectMemberList[i].name) )
							.append( $("<td>").text(projectMemberList[i].email) )
							.append( $("<td>").text(projectMemberList[i].tel) )
							.append( $("<td>").text(projectMemberList[i].blog) )
							.appendTo( $("#tbodyProjectMember") );
				}
				
				$("#divProjectList").css("display", "none");
				$(".new-project").css("display", "none");
				$("#h2ProjectView").html("프로젝트 정보");
				$(".view-project").css("display", "");
				$("#divProjectView").css("display", "");
				$("#divProjectMemberView").css("display", "");
				
			} else {
				alert("실행중 오류 발생");
				console.log(result.data);
			}
		},
		error: function(message) {
			alert("서버와의 통신이 원활하지 않습니다.\n잠시 후 다시 시도하세요.");
		}
	});
}

function deleteProject(projectNo) {
	$.ajax("project/delete.do?projectNo=" + $("#projectNo").val(), {
		type: "GET",
		success: function(result) {
			if (result.status == "success") {
				listProject();
				$("#divProjectView").css("display", "none");
				$("#divProjectMemberView").css("display", "none");
				$("#divProjectList").css("display", "");
			} else {
				alert("실행중 오류 발생");
				console.log(result.data);
			}
		},
		error: function(message) {
			alert("서버와의 통신이 원활하지 않습니다.\n잠시 후 다시 시도하세요.");
		}
	});
}

