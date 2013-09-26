<%@page import="net.bitacademy.java41.vo.Project"%>
<%@page import="net.bitacademy.java41.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>EverProject</title>
    <link rel="icon" type="image/png" href="${rootPath}/res/logo_sim.png">
    
    <link rel="stylesheet" type="text/css" href="${rootPath}/css/reset.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="${rootPath}/css/text.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="${rootPath}/css/grid.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="${rootPath}/css/layout.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="${rootPath}/css/nav.css" media="screen" />
    <!--[if IE 6]><link rel="stylesheet" type="text/css" href="${rootPath}/css/ie6.css" media="screen" /><![endif]-->
    <!--[if IE 7]><link rel="stylesheet" type="text/css" href="${rootPath}/css/ie.css" media="screen" /><![endif]-->
    <link href="${rootPath}/css/table/demo_page.css" rel="stylesheet" type="text/css" />
    <!-- BEGIN: load jquery -->
    <script src="${rootPath}/js/jquery-1.6.4.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${rootPath}/js/jquery-ui/jquery.ui.core.min.js"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.ui.widget.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.ui.accordion.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.effects.core.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.effects.slide.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.ui.mouse.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.ui.sortable.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/table/jquery.dataTables.min.js" type="text/javascript"></script>
    <!-- END: load jquery -->
    <script type="text/javascript" src="${rootPath}/js/table/table.js"></script>
    <script src="${rootPath}/js/setup.js" type="text/javascript"></script>
    <script type="text/javascript">

        $(document).ready(function () {
            setupLeftMenu();

            $('.datatable').dataTable();
			setSidebarHeight();


        });
    </script>
    
    
    <!-- 추가  CSS-->
    <link rel="stylesheet" type="text/css" href="${rootPath}/css/header.css" />
    <link rel="stylesheet" type="text/css" href="${rootPath}/css/sidebar.css"/>
    <link rel="stylesheet" type="text/css" href="${rootPath}/css/content.css"/>
    
    <!-- //추가 CSs-->
</head>
<body>
    <div class="container_12">
<!-- Header -->
<jsp:include page="/Header.jsp"></jsp:include>
        
<!-- Sidebar -->
<jsp:include page="/Sidebar.jsp"></jsp:include>

<!-- Content -->
        <div class="grid_10">
            <div class="box round first grid">
                <h2>
					화원 목록</h2>
                <div class="block">
                    <table class="data display datatable" id="example">
					<thead>
						<tr>
							<th>이름</th>
							<th>이메일</th>
							<th>전화</th>
							<th>구분</th>
							<!-- <th>CSS grade</th> -->
						</tr>
					</thead>
					<tbody>
<c:forEach var="member" items="${memberList}">				
					<tr class="odd gradeX" onclick="document.location.href='${rootPath}/member/view.do?email=${member.email}';">
						<td >${member.name}</td>
						<td>${member.email}</td>
						<td>${member.tel}</td>
						<td><c:choose>
							<c:when test="${member.level == 0}">[일반]</c:when>
							<c:when test="${member.level == 1}">[관리자]</c:when>
							<c:when test="${member.level == 2}">[PM강사]</c:when>
							<c:when test="${member.level == 9}">[손님]</c:when>
						</c:choose></td>
					</tr>
</c:forEach>
						
					</tbody>
				</table>
				<div class="table_bottom_btns_div">
<c:if test="${member.level == 1}">				
					<button class="btn-icon btn-green btn-person" 
							onclick="document.location.href='${rootPath}/member/add.do';"><span></span>회원 등록</button>
</c:if>							
				</div>
                    
                </div>
            </div>
        </div>
<!-- //Content -->


        <div class="clear">
        </div>
    </div>
<!-- Tail -->
<jsp:include page="/Tail.jsp"></jsp:include>

</body>
</html>


