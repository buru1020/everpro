<%@page import="net.bitacademy.java41.vo.Project"%>
<%@page import="net.bitacademy.java41.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
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
   
   
   
	<!-- Container Start -->
	<div class="grid_10">
            <div class="box round first grid">
                <h2>피드</h2>
                <div class="block ">
                    <form action="add.do" method="post">
                    <input type="hidden" name="projectNo" value="${ project.no}">
                    <input type="hidden" name="email" value="${ sessionScope.member.email}">
                    
                    <c:choose>
					<c:when test="${sessionScope.member.photos[0] != null}">
						<img src="${rootPath}/res/photo/${sessionScope.member.photos[0]}" alt="Profile Pic" class="header_member_photo_img" >
					</c:when>
					<c:otherwise>
						<img src="${rootPath}/img/img-profile.jpg" alt="Profile Pic" class="header_member_photo_img" />
					</c:otherwise>
					</c:choose>
					${sessionScope.member.name}(${sessionScope.member.email}) 
					<br>
					<textarea rows="auto" cols="auto" name="content"  placeholder="내용을 입력해주세요."></textarea>
					<br>
		<c:forEach var="feed" items="#{feedList}">		
						
					<div>
						<div>
	                    <c:choose>
						<c:when test="${feed.photoUrl != ''}">
							<img src="${rootPath}/res/photo/${feed.photoUrl}" alt="Profile Pic" class="header_member_photo_img" >
						</c:when>
						<c:otherwise>
							<img src="${rootPath}/img/img-profile.jpg" alt="Profile Pic" class="header_member_photo_img" />
						</c:otherwise>
						</c:choose>
							${feed.name}
							${feed.regDate}
						</div>
						<div>
							${feed.content} ${feed.projectNo}<a href="${rootPath}/feed/delete.do?pno=${feed.projectNo}&fno=${feed.feedNo}"> 삭제</a>
							<input type="reset" value="삭제" 
						onclick="document.location.href='${rootPath}/feed/delete.do?projectNo=${feed.projectNo}&feedNo=${feed.feedNo}';"class="btn btn-grey reset">
						</div>
					</div>
</c:forEach>					
                    
                    <div class="form_submit_div">
						<input type="reset" value="목록" 
						onclick="document.location.href='${rootPath}/project/list.do';" class="btn btn-yello reset">
						<input type="submit" value="등록" class="btn btn-green submit">
						<input type="reset" value="취소" 
						onclick="document.location.href='${rootPath}/feed/list.do?no=${project.no}';"class="btn btn-grey reset">
						
					
					</div>
                   
				
                    </form>
            
                </div>
            </div>
        </div>
	
<!-- //Content -->


        <div class="clear">
        </div>
    </div>
<!-- Tail -->
<jsp:include page="/tail.do"></jsp:include>

</body>
</html>