<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <header class="grid_12 header-repeat">
            <div id="branding">
                <div id="header_logo_div" class="floatleft">
                	<a href="${rootPath}/main.do"><img id="header_logo_img" src="${rootPath}/res/logo_text_green_2.png" alt="Logo" ></a></div>
                <div class="floatright">
                    <div class="floatleft">
					<c:choose>
					<c:when test="${sessionScope.loginInfo.photo != null}">
						<img src="${rootPath}/res/photo/${sessionScope.loginInfo.photo}" alt="Profile Pic" class="header_member_photo_img" >
					</c:when>
					<c:otherwise>
						<img src="${rootPath}/img/img-profile.jpg" alt="Profile Pic" class="header_member_photo_img" />
					</c:otherwise>
					</c:choose>                    
                    </div>    
                    <div class="floatleft marginleft10">
                        <ul class="inline-ul floatleft">
                            <li>Hello ${sessionScope.loginInfo.name}</li>
                            <li><a href="${rootPath}/member/updateMyInfo.do">Config</a></li>
                            <li><a href="${rootPath}/auth/logout.do">Logout</a></li>
                        </ul>
                        <br />
                        <span class="small grey">${sessionScope.loginInfo.tel}</span>
                        <br />
                        <span class="small grey">${sessionScope.loginInfo.email}</span>
                    </div>
                </div>
                <div class="clear">
                </div>
            </div>
        </header>
        <div class="clear">
        </div>

        <nav class="grid_12">
            <ul class="nav main">
            	<li class="ic-typography"><a href="${rootPath}/main.do"><span>Main</span></a></li>
            	<li class="ic-form-style"><a href="#"><span>Feed</span></a>
            		<ul>
            		<c:forEach var="project" items="${myProjectList}">
                        <li><a href="${rootPath}/feed/list.do?projectNo=${project.no}">${project.title}</a></li>
					</c:forEach>                        
                    </ul>
            	</li>
            	<li class="ic-dashboard"><a href="${rootPath}/project/list.do"><span>Project</span></a>
            		<ul>
            		<c:forEach var="project" items="${myProjectList}">
                        <li><a href="${rootPath}/project/view.do?no=${project.no}">${project.title}</a></li>
					</c:forEach>
					</ul>
            	</li>
            	<li class="ic-notifications"><a href="${rootPath}/member/list.do"><span>Member</span></a></li>
            </ul>
        </nav>
        <div class="clear">
        </div>
    
