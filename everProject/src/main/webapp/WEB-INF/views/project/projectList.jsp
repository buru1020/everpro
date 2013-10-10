<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
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
<jsp:include page="/header.do"></jsp:include>
        
<!-- Sidebar -->
<jsp:include page="/sidebar.do"></jsp:include>

<!-- Content -->
	 <section class="grid_10">
            <div class="box round first grid">
                <h2>프로젝트 목록</h2>
                <div class="block">
                    <table class="data display datatable" id="example">
					<thead>
						<tr><th class="sorting_asc" rowspan="1" colspan="1" style="width: 253px;">프로젝트 명</th>
						<th class="sorting" rowspan="1" colspan="1" style="width: 313px;">시작일</th>
						<th class="sorting" rowspan="1" colspan="1" style="width: 294px;">종료일</th>
						<th class="sorting" rowspan="1" colspan="1" style="width: 212px;">PL</th>
						<th class="sorting" rowspan="1" colspan="1" style="width: 153px;">등급</th>
						</tr>
					</thead>
					
					<tbody>
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
</c:forEach>
						
					</tbody></table>
				<div class="table_bottom_btns_div">
					 <c:if test="${!(sessionScope.loginInfo.level == 1)}">
					 <button class="btn-icon btn-green btn-person" 
							onclick="document.location.href='${rootPath}/project/add.do';"><span></span>신규 프로젝트 등록</button>
					</c:if> 
				</div>
                    
                </div>
            </div>
        </section>
<!-- //Content -->


        <div class="clear">
        </div>
    </div>
<!-- Tail -->
<jsp:include page="/tail.do"></jsp:include>

</body>
</html>
				

