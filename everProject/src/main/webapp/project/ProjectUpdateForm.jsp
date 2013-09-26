<%@page import="net.bitacademy.java41.vo.ProjectEx"%>
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
<jsp:include page="/Header.jsp"></jsp:include>
        
<!-- Sidebar -->
<jsp:include page="/Sidebar.jsp"></jsp:include>

<!-- Content -->
   
   
   
	<!-- Container Start -->


	<div class="grid_10">
            <div class="box round first grid">
                <h2>프로젝트 변경</h2>
                <div class="block ">
                    <form action="update.do" method="post">
                    <table class="form">
                        <tbody>
                        <tr>
                        <td class="col1"><label>번호</label></td>
                        <td class="col2"><input type="text" value="${project.no}" readonly="readonly" name="no"/><br></td>
                        </tr>
                        <tr>
                            <td class="col1"><label>프로젝트 명 </label></td>
                            <td class="col2"><input type="text" class="mini" value="${project.title}" name="title"/><br></td>
                        </tr>
                        <tr>
                            <td><label>내용</label></td>
                            <td><textarea rows="10" cols="50" name="content">${project.content}</textarea><br></td>
                        </tr>
                        <tr>
                            <td><label>시작일</label></td>
                            <td> <input type="text" class="mini" value="${project.startDate}" placeholder="예)2013-4-5" name="startDate"/>
                            <br></td>
                        </tr>
                        <tr>
                            <td><label>종료일</label></td>
                            <td> <input type="text" class="mini" value="${project.endDate}" placeholder="예)2013-4-5" name="endDate"/>
                            <br></td>
                        </tr>
                         <tr>
                            <td><label>태그</label></td>
                            <td> <input type="text" class="mini" value="${project.tag}" placeholder="예)태그1 태그2 태그3 (공백으로 구분)" name="tag"/>
                            <br></td>
                        </tr>
                   
                     
                    </tbody></table>
                    <div class="form_submit_div">
						<input type="submit" value="등록" class="btn btn-green submit">
						<input type="reset" value="취소" 
						onclick="document.location.href='${rootPath}/project/view.do?no=${project.no}';"class="btn btn-grey reset">
						<input type="reset" value="목록" 
						onclick="document.location.href='${rootPath}/project/list.do';" class="btn btn-yello reset">
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
<jsp:include page="/Tail.jsp"></jsp:include>

</body>
</html>
			


<%-- 	<!-- Container Start -->
	<div id="container">

		<jsp:include page="/Sidebar.jsp"></jsp:include>


		<!-- Section Start -->
		<section class=main_content_section>
			<h1>신규 프로젝트 변경</h1>
			<form action="update.do" method="post">
				번호: <input type="text" name="no" value="${project.no}" readonly="readonly"><br>
				프로젝트명: <input type="text" name="title" value="${project.title}"><br> 
				내용:<br>
				<textarea name="content" rows="4" cols="50">${project.content}</textarea><br> 
				시작일: <input type="text" name="startDate" value="${project.startDate}" placeholder="예)2013-4-5"><br> 
				종료일: <input type="text" name="endDate" value="${project.endDate}" placeholder="예)2013-6-5"><br> 
				태그: <input type="text" name="tag" value="${project.tag}" placeholder="예)태그1 태그2 태그3 (공백으로 구분)"><br>
				<input type="submit" value="등록">
				<a href="${rootPath}/project/view.do?no=${project.no}">취소</a><br>
			</form>
			<p>
				<a href="list.do">[목록]</a>
			</p>
		</section>
		<!-- Section End -->



	</div>
	<!-- Container End -->

--%>
