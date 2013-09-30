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
<jsp:include page="/header.do"></jsp:include>
        
<!-- Sidebar -->
<jsp:include page="/sidebar.do"></jsp:include>

<!-- Content -->
        <div class="grid_10">
            <div class="box round first fullpage">
                <h2>
					회원정보 변경</h2>
                <div class="block ">
                    <form action="update.do" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="projectNo" value="${task.projectNo}">
					<input type="hidden" name="taskNo" value="${task.taskNo}">
                    <table class="form">
                        <tr>
                            <td>
                                <label>
                                    	프로젝트명</label>
                            </td>
                            <td>
                                ${project.title}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                    	작업명</label>
                            </td>
                            <td>
                                <input type="text" class="mini" name="title" value="${task.title}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                    	UI프로토타입</label>
                            </td>
                            <td>
                                <input type="file" name="uiProto" >
                                <input type="hidden" name="tmpFilename" value="${task.uiProtoUrl}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                    	내용</label>
                            </td>
                            <td>
                                <textarea class="tinymce" id="mce_0" aria-hidden="true" style="display: none;" name="content">${task.content}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
										시작일</label>
                            </td>
                            <td>
                                <input type="text" class="mini" name="startDate" value="${task.startDate}" placeholder="예)2013-4-5">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                    	종료일</label>
                            </td>
                            <td>
                                <input type="text" class="mini" name="endDate" value="${task.endDate}" placeholder="예)2013-6-5">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>
                                    	상태</label>
                            </td>
                            <td>
                                <select name="status">
									<option value="0" <c:if test="${task.status == 0}">selected</c:if>>등록</option>
									<option value="1" <c:if test="${task.status == 1}">selected</c:if>>진행</option>
									<option value="2" <c:if test="${task.status == 2}">selected</c:if>>완료</option>
									<option value="3" <c:if test="${task.status == 3}">selected</c:if>>지연</option>
								</select>
                            </td>
                        </tr>
                    </table>
                    <div class="form_submit_div">
						<input type="button" value="목록" class="btn btn-orange"
								onclick="document.location.href='list.do?projectNo=${task.projectNo}'">
						<input type="submit" value="변경" class="btn btn-green submit">
						<input type="button" value="취소" class="btn btn-grey reset"
								onclick="document.location.href='view.do?projectNo=${task.projectNo}&taskNo=${task.taskNo}'">
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