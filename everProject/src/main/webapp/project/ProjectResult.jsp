<%@page import="net.bitacademy.java41.vo.Project"%>
<%@page import="net.bitacademy.java41.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> -->
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Refresh" content="2;url=${returnUrl}">
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
    <!-- BEGIN: load jquery -->
    <script src="${rootPath}/js/jquery-1.6.4.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${rootPath}/js/jquery-ui/jquery.ui.core.min.js"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.ui.widget.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.ui.accordion.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.effects.core.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.effects.slide.min.js" type="text/javascript"></script>
    <!-- END: load jquery -->
    <!-- BEGIN: load jqplot -->
    <link rel="stylesheet" type="text/css" href="${rootPath}/css/jquery.jqplot.min.css" />
    <!--[if lt IE 9]><script language="javascript" type="text/javascript" src="${rootPath}/js/jqPlot/excanvas.min.js"></script><![endif]-->
    <script language="javascript" type="text/javascript" src="${rootPath}/js/jqPlot/jquery.jqplot.min.js"></script>
    <script language="javascript" type="text/javascript" src="${rootPath}/js/jqPlot/plugins/jqplot.barRenderer.min.js"></script>
    <script language="javascript" type="text/javascript" src="${rootPath}/js/jqPlot/plugins/jqplot.pieRenderer.min.js"></script>
    <script language="javascript" type="text/javascript" src="${rootPath}/js/jqPlot/plugins/jqplot.categoryAxisRenderer.min.js"></script>
    <script language="javascript" type="text/javascript" src="${rootPath}/js/jqPlot/plugins/jqplot.highlighter.min.js"></script>
    <script language="javascript" type="text/javascript" src="${rootPath}/js/jqPlot/plugins/jqplot.pointLabels.min.js"></script>
    <!-- END: load jqplot -->
    <script src="${rootPath}/js/setup.js" type="text/javascript"></script>
    <script type="text/javascript">

        $(document).ready(function () {
            setupDashboardChart('chart1');
            setupLeftMenu();
			setSidebarHeight();


        });
    </script>
    
    
    <!-- 추가  CSS-->
    <link rel="stylesheet" type="text/css" href="${rootPath}/css/header.css" media="screen" />
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
            
            <!-- Section Start -->
	<section class=main_content_section>
		<c:choose>
		<%-- 프로젝트 수정 --%>
		<c:when test="${status == 'UPDATE_FAIL'}">프로젝트 정보 변경 실패하였습니다..</c:when>
		<c:when test="${status == 'UPDATE_SUCCESS'}">프로젝트 정보가 변경되었습니다.</c:when>
		<%-- 프로젝트 삭제 --%>
		<c:when test="${status == 'DELETE_FAIL'}">프로젝트 삭제 실패하였습니다.</c:when>
		<c:when test="${status == 'DELETE_SUCCESS'}">프로젝트 삭제되었습니다.</c:when>
		<c:otherwise>오류상황</c:otherwise>
		</c:choose><br>
	</section>
	<!-- Section End -->
            
            
            
            
            </div>
        </div>



	<!-- Container End -->
    
   </div>

    
<!-- Tail -->
<jsp:include page="/Tail.jsp"></jsp:include>

</body>
</html>










<%--

<%@page import="net.bitacademy.java41.vo.Project"%>
<%@page import="net.bitacademy.java41.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Refresh" content="2;url=${returnUrl}">
<meta charset="utf-8">
<title>EverProject</title>
<link rel="icon" type="image/png" href="${rootPath}/res/logo_sim.png">
	
<link rel="stylesheet" href="${rootPath}/css/base.css">	
<link rel="stylesheet" href="${rootPath}/css/signup_1.css" type="text/css" />
<link rel="stylesheet" href="${rootPath}/css/signup_2.css" type="text/css" />


</head>


<body class="production workflow-layout">


<jsp:include page="/Header.jsp"></jsp:include>



<!-- Container Start -->
<div id="container">

<jsp:include page="/Sidebar.jsp"></jsp:include>
	
	
	<!-- Section Start -->
	<section class=main_content_section>
		<c:choose>
		<!-- 프로젝트 수정 -->
		<c:when test="${status == 'UPDATE_FAIL'}">프로젝트 정보 변경 실패하였습니다..</c:when>
		<c:when test="${status == 'UPDATE_SUCCESS'}">프로젝트 정보가 변경되었습니다.</c:when>
		<!-- 프로젝트 삭제 -->
		<c:when test="${status == 'DELETE_FAIL'}">프로젝트 삭제 실패하였습니다.</c:when>
		<c:when test="${status == 'DELETE_SUCCESS'}">프로젝트 삭제되었습니다.</c:when>
		<c:otherwise>오류상황</c:otherwise>
		</c:choose><br>
	</section>
	<!-- Section End -->
	
	

</div>
<!-- Container End -->

<jsp:include page="/Tail.jsp"></jsp:include>

</body>
</html>

--%>