<%@page import="net.bitacademy.java41.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<jsp:include page="/header.jsp"></jsp:include>
        
<!-- Sidebar -->
<jsp:include page="/sidebar.do"></jsp:include>

<!-- Content -->
        <div class="grid_10">
            <div class="box round first fullpage">
                <h2>
					회원정보 변경</h2>
                <div class="block ">
                    <form action="updateMyInfo.do" method="post" enctype="multipart/form-data">
                    <table class="form">
                        <tr>
                            <td>
                                <label>
                                    	이메일</label>
                            </td>
                            <td>
                                <input type="text" class="mini" name="email" value="${member.email}" readonly="readonly">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                    	비밀번호</label>
                            </td>
                            <td>
                                <input type="password" class="mini" name="password" required>
                                <input type="button" class="btn btn-small btn-grey" value="비밀번호 변경" 
										onclick="document.location.href='${rootPath}/member/passwordChange.do'">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                    	이름</label>
                            </td>
                            <td>
                                <input type="text" class="mini" name="name" value="${member.name}" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                    	전화</label>
                            </td>
                            <td>
                                <input type="text" class="mini" name="tel" value="${member.tel}" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
										사진</label>
                            </td>
                            <td>
                                <input type="file" class="mini" name="photo">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                    	블로그</label>
                            </td>
                            <td>
                                <input type="text" class="mini" name="blog" value="${member.blog}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                    	우편변호</label>
                            </td>
                            <td>
                                <input type="text"class="mini"  name="postNo" value="${member.postNo}">
								<input type="button" value="우편번호 찾기" class="btn btn-small btn-grey"
								onclick="">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                   	  	기본주소</label>
                            </td>
                            <td>
                                <input type="text" class="mini" name="basicAddress" value="">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                    	상세주소</label>
                            </td>
                            <td>
                                <input type="text" class="mini" name="detailAddress" value="${member.detailAddress}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                    	태그</label>
                            </td>
                            <td>
                                <input type="text" name="tag" class="mini" value="${member.tag}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                    	권한</label>
                            </td>
                            <td>
                                <select id="select" name="level">
								<c:choose>
									<c:when test="${member.level == 0}"><option value="0" selected>일반회원</option></c:when>
									<c:when test="${member.level == 1}"><option value="1" selected>관리자</option></c:when>
									<c:when test="${member.level == 2}"><option value="2" selected>PM</option></c:when>
									<c:when test="${member.level == 9}"><option value="9" selected>손님</option></c:when>
								</c:choose>                      
                                </select>
                            </td>
                        </tr>
                    </table>
                    <div class="form_submit_div">
						<input type="submit" value="변경" class="btn btn-green submit">
						<input type="button" value="취소" class="btn btn-grey reset"
								onclick="document.location.href='${rootPath}/main.do'">
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
<jsp:include page="/tail.jsp"></jsp:include>

</body>
</html>

