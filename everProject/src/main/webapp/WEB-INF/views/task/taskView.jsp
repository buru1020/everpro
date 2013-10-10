<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
	<title>EverProject</title>
	<link rel="icon" type="image/png" href="${rootPath}/res/logo_sim.png">
	
	<link rel="stylesheet" type="text/css" href="${rootPath}/css/reset.css"
		media="screen" />
	<link rel="stylesheet" type="text/css" href="${rootPath}/css/text.css"
		media="screen" />
	<link rel="stylesheet" type="text/css" href="${rootPath}/css/grid.css"
		media="screen" />
	<link rel="stylesheet" type="text/css" href="${rootPath}/css/layout.css"
		media="screen" />
	<link rel="stylesheet" type="text/css" href="${rootPath}/css/nav.css"
		media="screen" />
	<!--[if IE 6]><link rel="stylesheet" type="text/css" href="${rootPath}/css/ie6.css" media="screen" /><![endif]-->
	<!--[if IE 7]><link rel="stylesheet" type="text/css" href="${rootPath}/css/ie.css" media="screen" /><![endif]-->
	<link href="${rootPath}/css/table/demo_page.css" rel="stylesheet"
		type="text/css" />
	<!-- BEGIN: load jquery -->
	<script src="${rootPath}/js/jquery-1.6.4.min.js" type="text/javascript"></script>
	<script type="text/javascript"
		src="${rootPath}/js/jquery-ui/jquery.ui.core.min.js"></script>
	<script src="${rootPath}/js/jquery-ui/jquery.ui.widget.min.js"
		type="text/javascript"></script>
	<script src="${rootPath}/js/jquery-ui/jquery.ui.accordion.min.js"
		type="text/javascript"></script>
	<script src="${rootPath}/js/jquery-ui/jquery.effects.core.min.js"
		type="text/javascript"></script>
	<script src="${rootPath}/js/jquery-ui/jquery.effects.slide.min.js"
		type="text/javascript"></script>
	<script src="${rootPath}/js/jquery-ui/jquery.ui.mouse.min.js"
		type="text/javascript"></script>
	<script src="${rootPath}/js/jquery-ui/jquery.ui.sortable.min.js"
		type="text/javascript"></script>
	<script src="${rootPath}/js/table/jquery.dataTables.min.js"
		type="text/javascript"></script>
	<!-- END: load jquery -->
	<script type="text/javascript" src="${rootPath}/js/table/table.js"></script>
	<script src="${rootPath}/js/setup.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			setupLeftMenu();
	
			$('.datatable').dataTable();
			setSidebarHeight();
	
		});
	</script>
	
	
	
	<!-- 추가  CSS-->
	<link rel="stylesheet" type="text/css" href="${rootPath}/css/header.css" />
	<link rel="stylesheet" type="text/css"
		href="${rootPath}/css/sidebar.css" />
	<link rel="stylesheet" type="text/css"
		href="${rootPath}/css/content.css" />
	
	<!-- //추가 CSs-->
</head>

<body>
	<div class="container_12">
		<!-- Header -->
		<jsp:include page="/header.do"></jsp:include>

		<!-- Sidebar -->

		<jsp:include page="/sidebar.do"></jsp:include>


		<!-- Content -->
		<section class="grid_10">
			<div class="box round first grid">
				<h2>작업 정보</h2>
				<div class="block ">
					<div>
						<table class="form">
							<tr>
								<td><label>작업명</label></td>
								<td>${task.title}</td>
							</tr>
							<tr>
								<td><label>프로젝트명</label></td>
								<td> ${project.title}</td>
							</tr>
							<tr>
								<td><label>내용</label></td>
								<td>${task.content}</td>
							</tr>
							<tr>
								<td><label>시작일</label></td>
								<td>${task.startDate} </td>
							</tr>
							<tr>
								<td><label>종료일</label></td>
								<td>${task.endDate}</td>
							</tr>
							<tr>
								<td><label>상태</label></td>
								<td><c:choose>
										<c:when test="${task.status == 0}">등록</c:when>
										<c:when test="${task.status == 1}">진행</c:when>
										<c:when test="${task.status == 2}">완료</c:when>
										<c:when test="${task.status == 3}">지연</c:when>
									</c:choose></td>
							</tr>
							<tr>
								<td><label>UI프로토타입</label></td>
								<td></td>
							</tr>
							<tr>
								<td><label></label></td>
								<td><c:if test="${task.uiProtoUrl != null}">
										<img src="${rootPath}/res/ui/${task.uiProtoUrl}">
									</c:if></td>
							</tr>
						</table>
							
						<div class="form_submit_div">
							<input type="submit" value="작업목록" class="btn btn-orange"
								onclick="document.location.href='${rootPath}/task/list.do?projectNo=${task.projectNo}';">
					<c:forEach var="projectMember" items="${projectMemberList}"> 					
						<c:if test="${projectMember.email == sessionScope.loginInfo.email}">
							<input type="submit" value="변경" class="btn btn-green submit"
								onclick="document.location.href='${rootPath}/task/update.do?projectNo=${task.projectNo}&taskNo=${task.taskNo}';">
							<input type="reset" value="삭제" class="btn btn-grey"
								onclick="document.location.href='${rootPath}/task/delete.do?projectNo=${task.projectNo}&taskNo=${task.taskNo}';">
						</c:if>
					</c:forEach>
						<c:if test="${sessionScope.loginInfo.level == 1}">
							<input type="submit" value="변경" class="btn btn-green submit"
								onclick="document.location.href='${rootPath}/task/update.do?projectNo=${task.projectNo}&taskNo=${task.taskNo}';">
							<input type="reset" value="삭제" class="btn btn-grey"
								onclick="document.location.href='${rootPath}/task/delete.do?projectNo=${task.projectNo}&taskNo=${task.taskNo}';">						 
						</c:if>
						</div>
						</div>
					
					</div>

				</div>
			</section>
			<!-- //Content -->


			<div class="clear"></div>
		</div>
		<!-- Tail -->
		<jsp:include page="/tail.do"></jsp:include>
</body>
</html>