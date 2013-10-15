$(function() {
	listTask();
	
	$("#btnAddProject").click(function(event){
		$("#divTaskList").css("display", "none");	// 등록버튼 보이기
		
		$("#btnReset").click();
		
		$(".new-task").css("display", "");	// 수정버튼, 번호입력창 감추기
		$(".view-task").css("display", "none"); // view 섹션 보이기
		$("#divTaskView").css("display", "");
	});
	
	$("#btnList").click(function(event) {
		$("#divTaskView").css("display", "none");
		$("#divTaskList").css("display", "");
	});
	
	$("#btnAdd").click(function() {
		addTask();
	});
	
//	$("#btnUpdate").click(function() {
//		updateProject();
//	});
//	
//	$("#btnDelete").click(function() {
//		deleteProject();
//	});
	
});

function listTask(projectNo) {
	$.ajax("task/list.do?projectNo=" + projectNo, {
		type: "GET",
		success: function(result) {
			if (result.status == "success") {
				var tasks = result.data;
/*
				<c:forEach var="project" items="${totalProjectList}">			
				<tr onclick="document.location.href='${rootPath}/project/view.do?no=${project.no}'">
					<td style="text-align: left;">${project.title}</td>
					<td>${project.startDate}</td>
					<td>${project.endDate}</td>
					<td><c:choose>
						<c:when test="${project.plName != ''}">${project.plName}</c:when>
						<c:otherwise>-</c:otherwise>
					</c:choose></td>
					<td><c:if test="${project.plEmail == sessionScope.loginInfo.email}">★</c:if></td>
				</tr>
*/				
				
				$(".data-taskTableRow").remove();
				
				for( var i = 0; i < tasks.length; i++ ) {
					$("<tr>").attr("class", "data-taskTableRow")
								.attr("data-taskNo", tasks[i].no)
								.click(function(event) {
									viewDetailTask( $(this).attr("data-taskNo") );
								})
							.append( $("<td>").html(tasks[i].title).css("text-align", "left") )
							.append( $("<td>").html(tasks[i].startDate) )
							.append( $("<td>").html(tasks[i].endDate) )
							.append( $("<td>").html(tasks[i].status) )
							.appendTo( $("#tbodyTask") );
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
//
//function addTask(projectNo) {
//	$.ajax("project/add.do", {
//		type: "POST",
//		data: {
//			title: $("#title").val(),
//			content: $("#pcontent").val(),
//			startDate: $("#startDate").val(),
//			endDate: $("#endDate").val(),
//			tag: $("#tag").val()
//		},
//		success: function(result) {
//			if (result.status == "success") {
//				listProject();
//				$("#divProjectView").css("display", "none");
//				$("#divProjectList").css("display", "");
//			} else {
//				alert("실행중 오류 발생");
//				console.log(result.data);
//			}
//		},
//		error: function(message) {
//			alert("서버와의 통신이 원활하지 않습니다.\n잠시 후 다시 시도하세요.");
//		}
//	});
//}
//
//function updateTask(projectNo) {
//	$.ajax("project/update.do", {
//		type: "POST",
//		data: {
//			no: $("#no").val(),
//			title: $("#title").val(),
//			content: $("#pcontent").val(),
//			startDate: $("#startDate").val(),
//			endDate: $("#endDate").val(),
//			tag: $("#tag").val()
//		},
//		success: function(result) {
//			if (result.status == "success") {
//				listProject();
//				$("#divProjectView").css("display", "none");
//				$("#divProjectList").css("display", "");
//			} else {
//				alert("실행중 오류 발생");
//				console.log(result.data);
//			}
//		},
//		error: function(message) {
//			alert("서버와의 통신이 원활하지 않습니다.\n잠시 후 다시 시도하세요.");
//		}
//	});
//}
//
//function viewDetailTask(projectNo) {
//	$.ajax("project/view.do?projectNo=" + projectNo, {
//		type: "GET",
//		success: function(result) {
//			if (result.status == "success") {
//				var project = result.data;
//				$("#projectNo").val(project.no);
//				$("#projectTitle").val(project.title);
//				$("#projectContent").val(project.content);
//				$("#projectStartDate").val(project.startDate);
//				$("#projectEndDate").val(project.endDate);
//				$("#projectTag").val(project.tag);
//				
//				$("#divProjectList").css("display", "none");
//				$(".new-project").css("display", "none");
//				$(".view-project").css("display", "");
//				$("#divProjectView").css("display", "");
//			} else {
//				alert("실행중 오류 발생");
//				console.log(result.data);
//			}
//		},
//		error: function(message) {
//			alert("서버와의 통신이 원활하지 않습니다.\n잠시 후 다시 시도하세요.");
//		}
//	});
//}
//
//function deleteTask(projectNo) {
//	$.ajax("project/delete.do?no=" + $("#no").val(), {
//		type: "GET",
//		success: function(result) {
//			if (result.status == "success") {
//				listProject();
//				$("#divProjectView").css("display", "none");
//				$("#divProjectList").css("display", "");
//			} else {
//				alert("실행중 오류 발생");
//				console.log(result.data);
//			}
//		},
//		error: function(message) {
//			alert("서버와의 통신이 원활하지 않습니다.\n잠시 후 다시 시도하세요.");
//		}
//	});
//}

