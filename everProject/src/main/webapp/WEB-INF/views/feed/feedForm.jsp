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
   
    <link rel="stylesheet" type="text/css" href="${rootPath}/css/feed/feed.css"/>
    <%-- <link rel="stylesheet" type="text/css" href="${rootPath}/css/feed/face_feed_1.css"/>
    <link rel="stylesheet" type="text/css" href="${rootPath}/css/feed/face_feed_2.css"/> --%>
	<link type="text/css" rel="stylesheet" href="https://fbstatic-a.akamaihd.net/rsrc.php/v2/yj/r/ahxBhqHhzKM.css" />
    <link type="text/css" rel="stylesheet" href="https://fbstatic-a.akamaihd.net/rsrc.php/v2/yd/r/3738UXBt1Su.css" /> 
    
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

			 	<!-- 입력창 -->
<c:choose>
	<c:when test="${isProjectMember || sessionScope.member.level == 1 || sessionScope.member.level == 2}">
		<c:if test="${isProjectMember}">
			 	<ul>
					<li>
					 	<div>
						 	<div style="float: left;">
						 		<img src="${rootPath}/res/photo/${sessionScope.member.photos[0]}" alt="Profile Pic" class="header_member_photo_img" >
							</div>
						<div style="margin-left: 60px;">
						    <div id="u_0_x" class="_119 stat_elem focus_target mtm mbl _5bsm child_was_focused" onclick="Bootloader.loadComponents(ComposerXControllerBootload, emptyFunction);" data-location="maincolumn">
						        <div class="_55d0">
						            <form action="add.do" method="post" enctype="multipart/form-data">
						            	<input type="hidden" name="projectNo" value="${ project.no}">
                    					<input type="hidden" name="email" value="${ sessionScope.member.email}">
                    					<input type="hidden" name="photoUrl" value="${ sessionScope.member.photos[0]}">
						                <div class="_2yg">
						                            <div id="u_0_18" class="uiMentionsInput _11a">
						                                <div id="u_0_19" class="uiTypeahead composerTypeahead mentionsTypeahead" style="height: auto;">
						                                    <div class="wrap">
						                                        <div class="innerWrap">
						                                        <!-- Text Area -->
						                                            <textarea name="content" class="uiTextareaAutogrow input autofocus mentionsTextarea textInput DOMControl_placeholder" > </textarea>
						                                        </div>
						                                    </div>
						                                </div>
						                            </div>
						                    <div class="_1dsp _4-">
						                        <div class="clearfix">
						                        <div id="u_g_y" class="lfloat">

												    <div id="u_0_14" class="_6a _m _1dsq _1dsw">
												        <a id="u_0_15" class="_1dsr" rel="ignore" role="presentation">
												            <span class="_4-px ellipsis">
												
												                사진 추가
												
												            </span>
												            <div class="_3jk">
												                <input id="js_12" class="_n _5f0v" type="file" aria-label="회원님의 게시물에 사진을 추가" name="feedPhoto" multiple="1" title="업로드 할 파일 선택"></input>
												            </div>
												        </a>
												    </div>
												</div>
									                            
									                           
						                            <ul class="uiList _1dso rfloat _509- _4ki _6-h _6-j _6-i">
						                                  <button class="_42ft _42fu _11b selected _42g-" type="submit" value="Submit">Submit</button>
						                            </ul>
						                        </div>
						                    </div>
						                </div>
						            </form>
						        </div>
						    </div>
						 </div>
					 </div>
				 	</li>
				</ul>	
		</c:if>
			 	<!-- 출력 -->
					<ul>
					<c:forEach var="feed" items="${feedList}" varStatus="status">	
						<li>
						<div class="message 
						<c:choose>
						<c:when test="${(status.count % 3) == 0}">
                            success
						</c:when>
						<c:when test="${(status.count % 3) == 1}">
                            info
						</c:when>
						<c:otherwise>
                            warning
						</c:otherwise>
                        </c:choose>">
                           <%-- <div class="message info">
                            <div class="message warning">
                            <div class="message error"> --%>
                            <c:choose>
							<c:when test="${feed.photoUrl != ''}">
								<img src="${rootPath}/res/photo/${feed.photoUrl}" alt="Profile Pic" class="header_member_photo_img" >
							</c:when>
							<c:otherwise>
								<img src="${rootPath}/img/img-profile.jpg" alt="Profile Pic" class="header_member_photo_img" />
							</c:otherwise>
							</c:choose> 
                                <span class="feed_name">${feed.name}</span>
                                <span style="float: right;">${feed.regDate}</span>
                                <img src="${rootPath}/res/feed/${feed.feedUrl}" width="100">
                                <p>
                                    ${feed.content}
                                </p>
                                <div class="feed_btn">
                                	<span class="feed_file">첨부파일: </span><a href="#">${feed.feedUrl}</a>
                                <c:if test="${feed.email == sessionScope.member.email || sessionScope.member.level == 1}">
                                	<span class="feed_delete_btn">
                                		<a href="${rootPath}/feed/delete.do?projectNo=${feed.projectNo}&feedNo=${feed.feedNo}" 
                                			class="btn-mini btn-red btn-cross"><span></span>Delete</a>
                                	</span>
                                </c:if>
                                </div>
                            </div>
						</li>
					</c:forEach>
					</ul>
	</c:when>
	<c:otherwise>
		<ul>
					<c:forEach var="feed" items="${feedList}" varStatus="status">	
						<li>
						<div class="message 
						<c:choose>
						<c:when test="${(status.count % 3) == 0}">
                            success
						</c:when>
						<c:when test="${(status.count % 3) == 1}">
                            info
						</c:when>
						<c:otherwise>
                            warning
						</c:otherwise>
                        </c:choose>">
                           <%-- <div class="message info">
                            <div class="message warning">
                            <div class="message error"> --%>
                            <c:choose>
							<c:when test="${feed.photoUrl != ''}">
								<img src="${rootPath}/res/photo/${feed.photoUrl}" alt="Profile Pic" class="header_member_photo_img" >
							</c:when>
							<c:otherwise>
								<img src="${rootPath}/img/img-profile.jpg" alt="Profile Pic" class="header_member_photo_img" />
							</c:otherwise>
							</c:choose> 
                                <span class="feed_name">${feed.name}</span>
                                <span style="float: right;">${feed.regDate}</span>
                                <img src="${rootPath}/res/feed/${feed.feedUrl}" width="100">
                                <p>
                                    ${feed.content}
                                </p>
                                <div class="feed_btn">
                                	<span class="feed_file">첨부파일: </span><a href="#">${feed.feedUrl}</a>
                                <c:if test="${feed.email == sessionScope.member.email || sessionScope.member.level == 1}">
                                	<span class="feed_delete_btn">
                                		<a href="${rootPath}/feed/delete.do?projectNo=${feed.projectNo}&feedNo=${feed.feedNo}" 
                                			class="btn-mini btn-red btn-cross"><span></span>Delete</a>
                                	</span>
                                </c:if>
                                </div>
                            </div>
						</li>
					</c:forEach>
					</ul>
	</c:otherwise>
</c:choose>	
						
			<div class="feed_bottom_btns_div">
				 <button class="btn-icon btn-green btn-person" 
							onclick="document.location.href='${rootPath}/project/list.do';"><span></span>프로젝트 정보</button>
			</div>
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