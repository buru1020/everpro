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
    <link href="${rootPath}/css/fancy-button/fancy-button.css" rel="stylesheet" type="text/css" />
    <!--Jquery UI CSS-->
    <link href="${rootPath}/css/themes/base/jquery.ui.all.css" rel="stylesheet" type="text/css" />
    <!-- BEGIN: load jquery -->
    <script src="${rootPath}/js/jquery-1.6.4.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${rootPath}/js/jquery-ui/jquery.ui.core.min.js"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.ui.widget.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.ui.accordion.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.effects.core.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.effects.slide.min.js" type="text/javascript"></script>
    <!-- END: load jquery -->
    <!--jQuery Date Picker-->
    <script src="${rootPath}/js/jquery-ui/jquery.ui.widget.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.ui.datepicker.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.ui.progressbar.min.js" type="text/javascript"></script>
    <!-- jQuery dialog related-->
    <script src="${rootPath}/js/jquery-ui/external/jquery.bgiframe-2.1.2.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.ui.mouse.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.ui.draggable.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.ui.position.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.ui.resizable.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.ui.dialog.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.effects.core.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.effects.blind.min.js" type="text/javascript"></script>
    <script src="${rootPath}/js/jquery-ui/jquery.effects.explode.min.js" type="text/javascript"></script>
    <!-- jQuery dialog end here-->
    <script src="${rootPath}/js/jquery-ui/jquery.ui.accordion.min.js" type="text/javascript"></script>
    <!--Fancy Button-->
    <script src="${rootPath}/js/fancy-button/fancy-button.js" type="text/javascript"></script>
    <script src="${rootPath}/js/setup.js" type="text/javascript"></script>
    <!-- Load TinyMCE -->
    <script src="${rootPath}/js/tiny-mce/jquery.tinymce.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            setupTinyMCE();
            setupProgressbar('progress-bar');
            setDatePicker('date-picker');
            setupDialogBox('dialog', 'opener');
            $('input[type="checkbox"]').fancybutton();
            $('input[type="radio"]').fancybutton();
        });
    </script>
    <!-- /TinyMCE -->
    <style type="text/css">
        #progress-bar
        {
            width: 400px;
        }
    </style>
    
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
            <div class="box round first fullpage">
                <h2>
					회원정보</h2>
                <div class="block ">
                    <table class="form">
                    <tr>
                            <td>
                                <label>
                                    	</label>
                            </td>
                            <td>
                               <c:choose>
							<c:when test="${memberInfo.photos[0] != null}">
								<img src="${rootPath}/res/photo/${memberInfo.photos[0]}" alt="Profile Pic" class="content_member_photo_img" >
							</c:when>
							<c:otherwise>
								<img src="${rootPath}/img/img-profile.jpg" alt="Profile Pic" class="content_member_photo_img" >
							</c:otherwise>
							</c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                    	구분</label>
                            </td>
                            <td>
                                <c:choose>
									<c:when test="${memberInfo.level == 0}">일반</c:when>
									<c:when test="${memberInfo.level == 1}">관리자</c:when>
									<c:when test="${memberInfo.level == 2}">PM강사</c:when>
									<c:when test="${memberInfo.level == 9}">손님</c:when>
								</c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                    	이름</label>
                            </td>
                            <td>
                                <input type="text" class="mini" value="${memberInfo.name}" disabled />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                   		이메일</label>
                            </td>
                            <td>
                                <input type="text" class="mini" value="${memberInfo.email}" disabled />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
										전화</label>
                            </td>
                            <td>
                                <input type="text" class="mini" value="${memberInfo.tel}" disabled />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
										블로그</label>
                            </td>
                            <td>
                                <input type="text" class="mini" value="${memberInfo.blog}" disabled />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                    	우편번호</label>
                            </td>
                            <td>
                                <input type="text" class="mini" value="" disabled />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                    	기본주소</label>
                            </td>
                            <td>
                                <input type="text" class="mini" value="" disabled />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                   	  	상세주소</label>
                            </td>
                            <td>
                                <input type="text" class="mini" value="" disabled />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                    	태그</label>
                            </td>
                            <td>
                                <input type="text" class="mini" value="${memberInfo.tag}" disabled />
                            </td>
                        </tr>
                    </table>
                    <div class="form_submit_div">
                    	<button class="btn btn-orange" 
                    			onclick="document.location.href='list.do';">목록</button>
                    	<button class="btn btn-green" 
                    			onclick="document.location.href='updateForm.do?email=${memberInfo.email}';">변경</button>
                    	<button class="btn btn-grey" 
                    			onclick="document.location.href='delete.do?email=${memberInfo.email}';">삭제</button>
					</div>
                </div>
            </div>
            
			<div class="box round">
                <h2>
					참여 프로젝트</h2>
                <div class="block">
                    <table class="data display datatable" id="example">
					<thead>
						<tr>
							<th>프로젝트명</th>
							<th>PL</th>
							<th>구분</th>
							<th> </th>
							<th> </th>
						</tr>
					</thead>
					<tbody>
<c:forEach var="userProject" items="${projectList}">				
					<tr class="odd gradeX">
						<td >${userProject.title}</td>
						<td>${userProject.plName}</td>
						<td><c:if test="${userProject.level == 0}">★</c:if></td>
						<td> </td>
						<td> </td>
					</tr>
</c:forEach>
						
					</tbody>
				</table>
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