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

				<h2>작업등록</h2>

				<div class="block ">
					<form action="add.do" method="post" enctype="multipart/form-data">
						<input type="hidden" name="projectNo" value="${project.no}">
						<table class="form">
							<tbody>
								<tr>
									<td class="col1"><label>프로젝트 번호 </label></td>
									<td class="col2">${project.no}</td>
								</tr>
								<tr>
									<td class="col1"><label>프로젝트명 </label></td>
									<td class="col2">${project.title}</td>
								</tr>
								<tr>
									<td class="col1"><label>작업명 </label></td>
									<td class="col2"><input type="text" name="title"
										class="mini" value="" required="required"><br></td>
								</tr>
								<tr>
									<td class="col1"><label>UI프로토타입 </label></td>
									<td class="col2"><input type="file" class="mini"
										name="uiProto"><br></td>
								</tr>
								<tr>
									<td><label>내용</label></td>
									<td><textarea name="content" rows="6" cols="50" required="required"></textarea><br></td>
								</tr>
								<tr>
									<td><label>시작일</label></td>
									<td><input type="text" class="mini" name="startDate"
										placeholder="예)2013-4-5" required="required"> <br></td>
								</tr>
								<tr>
									<td><label>종료일</label></td>
									<td><input type="text" class="mini" name="endDate"
										placeholder="예)2013-4-5" required="required"> <br></td>
								</tr>
								<tr>
									<td class="col1"><label>상태 </label></td>
									<td class="col2"><select name="status">
											<option value="0" selected="selected">등록</option>
											<option value="1">진행</option>
											<option value="2">완료</option>
											<option value="3">지연</option>
									</select><br></td>
								</tr>
							</tbody>
						</table>
				
				
			
					
						<div class="form_submit_div">
							<input type="submit" value="등록" class="btn btn-green submit">
							<input type="reset" value="취소" class="btn btn-grey reset">
							<input type="reset" value="목록"
								onclick="document.location.href='${rootPath}/task/list.do?projectNo=${project.no}';"
								class="btn btn-yello reset">
						</div>
					</form>
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