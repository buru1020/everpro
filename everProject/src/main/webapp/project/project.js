project_onload = function() {
	listProject();
	
	
	$("#btnAddProject").click(function(event){
		$("#divProjectList").css("display", "none");	// 등록버튼 보이기
		
//		var resetEvent = new MouseEvent('click', {
//		    'view': window,
//		    'bubbles': true,
//		    'cancelable': true
//		});
//		$("#btnReset").dispatchEvent(resetEvent);
		$("#btnReset").click();
		
		$(".new-project").css("display", "");	// 수정버튼, 번호입력창 감추기
		$(".view-project").css("display", "none"); // view 섹션 보이기
		$("#divProjectView").css("display", "");
	});
	
	$("#btnList").click(function(event) {
		$("#divProjectView").css("display", "none");
		$("#divProjectList").css("display", "");
	});
	
	$("#btnAdd").click(function() {
		addProject();
	});
	
//	$("#btnUpdate").click(function() {
//		updateProject();
//	});
//	
//	$("#btnDelete").click(function() {
//		deleteProject();
//	});
	
};

function listProject() {
	$.ajax("project/list.do", {
		type: "GET",
		success: function(result) {
			if (result.status == "success") {
				var projects = result.data;
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
				
				$(".data-projectTableRow").remove();
				
				for( var i = 0; i < projects.length; i++ ) {
					$("<tr>").attr("class", "data-projectTableRow")
								.attr("data-projectNo", projects[i].no)
								.click(function(event) {
									viewDetailProject( $(this).attr("data-projectNo") );
								})
							.append( $("<td>").html(projects[i].title).css("text-align", "left") )
							.append( $("<td>").html(projects[i].startDate) )
							.append( $("<td>").html(projects[i].endDate) )
							.append( $("<td>").html(projects[i].plName) )
							.append( $("<td>").html( "★" ) )
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
			title: $("#title").val(),
			content: $("#pcontent").val(),
			startDate: $("#startDate").val(),
			endDate: $("#endDate").val(),
			tag: $("#tag").val()
		},
		success: function(result) {
			if (result.status == "success") {
				listProject();
				$("#divProjectView").css("display", "none");
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
			no: $("#no").val(),
			title: $("#title").val(),
			content: $("#pcontent").val(),
			startDate: $("#startDate").val(),
			endDate: $("#endDate").val(),
			tag: $("#tag").val()
		},
		success: function(result) {
			if (result.status == "success") {
				listProject();
				$("#divProjectView").css("display", "none");
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
				var project = result.data;
				$("#projectNo").val(project.no);
				$("#projectTitle").val(project.title);
				$("#projectContent").val(project.content);
				$("#projectStartDate").val(project.startDate);
				$("#projectEndDate").val(project.endDate);
				$("#projectTag").val(project.tag);
				
				$("#divProjectList").css("display", "none");
				$(".new-project").css("display", "none");
				$(".view-project").css("display", "");
				$("#divProjectView").css("display", "");
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
	$.ajax("project/delete.do?no=" + $("#no").val(), {
		type: "GET",
		success: function(result) {
			if (result.status == "success") {
				listProject();
				$("#divProjectView").css("display", "none");
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

