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
				<h2>프로젝트 정보</h2>
				<div class="block ">

					<div>
						<div id="submenu">
							<input type="submit" value="기본정보" class="btn btn-yellow btn-small"
								onclick="document.location.href='${rootPath}/project/view.do?no=${project.no}';">
							<input type="submit" value="작업들" class="btn btn-yellow btn-small"
								onclick="document.location.href='${rootPath}/task/list.do?projectNo=${project.no}';">								
					<c:forEach var="projectMember" items="${projectMemberList}"> 					
						<c:if test="${projectMember.email == sessionScope.loginInfo.email}"> 								
							<input type="submit" value="피드" class="btn btn-yellow btn-small"
								onclick="document.location.href='${rootPath}/feed/list.do?projectNo=${project.no}';">
						</c:if>
					</c:forEach>			
						<c:if test="${sessionScope.loginInfo.level == 1 || sessionScope.loginInfo.level == 2}"> 
							<input type="submit" value="피드" class="btn btn-yellow btn-small"
								onclick="document.location.href='${rootPath}/feed/list.do?projectNo=${project.no}';">
						</c:if>						
						</div>

					
						<table class="form">
							<tr>
								<td><label>프로젝트 번호</label></td>
								<td>${project.no}</td>
							</tr>
							<tr>
								<td><label>프로젝트 명</label></td>
								<td>${project.title}</td>
							</tr>
							<tr>
								<td><label>PL</label></td>
								<td><c:choose>
										<c:when test="${project.plName == ''}">없음</c:when>
										<c:otherwise>${project.plName} ( ${project.plEmail} )</c:otherwise>
									</c:choose></td>
							</tr>
							<tr>
								<td><label>내용</label></td>
								<td><input type="text" class="mini"
									value="${project.content}" disabled /></td>
							
							</tr>
							<tr>
								<td><label>시작일</label></td>
								<td><input type="text" class="mini"
									value="${project.startDate}" disabled /></td>
							</tr>
							<tr>
								<td><label>종료일</label></td>
								<td><input type="text" class="mini"
									value="${project.endDate}" disabled /></td>
							</tr>
							<tr>
								<td><label>태그</label></td>
								<td><input type="text" class="mini"
									value="${project.tag}" disabled /></td>
							</tr>
						</table>

						<div class="form_submit_div">
							<input type="submit" value="목록" class="btn btn-yello"
								onclick="document.location.href='${rootPath}/project/list.do';">
					<c:forEach var="projectMember" items="${projectMemberList}"> 					
						<c:if test="${projectMember.email == sessionScope.loginInfo.email}"> 
							<input type="submit" value="변경" class="btn btn-green submit"
								onclick="document.location.href='${rootPath}/project/update.do?no=${project.no}';">
							<input type="reset" value="삭제" class="btn btn-grey"
								onclick="document.location.href='${rootPath}/project/delete.do?no=${project.no}';">
						</c:if>
					</c:forEach>
						<c:if test="${sessionScope.loginInfo.level == 1}"> 
							<input type="submit" value="변경" class="btn btn-green submit"
								onclick="document.location.href='${rootPath}/project/update.do?no=${project.no}';">
							<input type="reset" value="삭제" class="btn btn-grey"
								onclick="document.location.href='${rootPath}/project/delete.do?no=${project.no}';">
						</c:if>								
						</div>
					</div>
				</div>
				</div>


				<div class="box round">
					<h2>프로젝트 멤버</h2>
					<div class="block">
						<table class="data display datatable" id="example">
							<thead>
								<tr>
									<th>이름</th>
									<th>이메일</th>
									<th>전화</th>
									<th>블로그</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="projectMember" items="${projectMemberList}">
									<tr class="odd gradeX">
										<td>${projectMember.name}<c:if
												test="${projectMember.projectLevel == 0}">★</c:if></td>
										<td>${projectMember.email}</td>
										<td>${projectMember.tel}</td>
										<td><c:choose>
												<c:when test="${projectMember.blog != ''}">${projectMember.blog}</c:when>
												<c:otherwise>-</c:otherwise>
											</c:choose></td>

									</tr>
								</c:forEach>

							</tbody>
						</table>
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