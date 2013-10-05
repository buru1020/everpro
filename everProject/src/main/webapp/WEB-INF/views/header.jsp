<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Header Start -->
        <div class="grid_12 header-repeat">
            <div id="branding">
                <div id="header_logo_div" class="floatleft">
                	<a href="${rootPath}/main.do"><img id="header_logo_img" src="${rootPath}/res/logo_text_green_2.png" alt="Logo" ></a></div>
                <div class="floatright">
                    <div class="floatleft">
					<c:choose>
					<c:when test="${sessionScope.member.photos[0] != null}">
						<img src="${rootPath}/res/photo/${sessionScope.member.photos[0]}" alt="Profile Pic" class="header_member_photo_img" >
					</c:when>
					<c:otherwise>
						<img src="${rootPath}/img/img-profile.jpg" alt="Profile Pic" class="header_member_photo_img" />
					</c:otherwise>
					</c:choose>                    
                    </div>    
                    <div class="floatleft marginleft10">
                        <ul class="inline-ul floatleft">
                            <li>Hello ${sessionScope.member.name}</li>
                            <li><a href="${rootPath}/member/updateMyInfo.do">Config</a></li>
                            <li><a href="${rootPath}/auth/logout.do">Logout</a></li>
                        </ul>
                        <br />
                        <span class="small grey">${sessionScope.member.tel}</span>
                        <br />
                        <span class="small grey">${sessionScope.member.email}</span>
                    </div>
                </div>
                <div class="clear">
                </div>
            </div>
        </div>
        <div class="clear">
        </div>

        <div class="grid_12">
            <ul class="nav main">
            	<li class="ic-typography"><a href="${rootPath}/main.do"><span>Main</span></a></li>
            	<li class="ic-form-style"><a href="${rootPath}/feed/listAll.do"><span>Feed</span></a></li>
            	<li class="ic-dashboard"><a href="${rootPath}/project/list.do"><span>Project</span></a></li>
            	<li class="ic-notifications"><a href="${rootPath}/member/list.do"><span>Member</span></a></li>
            	
            	<!-- 
                <li class="ic-dashboard"><a href="dashboard.html"><span>Dashboard</span></a> </li>
                <li class="ic-form-style"><a href="javascript:"><span>Controls</span></a>
                    <ul>
                        <li><a href="form-controls.html">Forms</a> </li>
                        <li><a href="buttons.html">Buttons</a> </li>
                        <li><a href="form-controls.html">Full Page Example</a> </li>
                        <li><a href="table.html">Page with Sidebar Example</a> </li>
                    </ul>
                </li>
                <li class="ic-typography"><a href="typography.html"><span>Typography</span></a></li>
				<li class="ic-charts"><a href="charts.html"><span>Charts & Graphs</span></a></li>
                <li class="ic-grid-tables"><a href="table.html"><span>Data Table</span></a></li>
                <li class="ic-gallery dd"><a href="javascript:"><span>Image Galleries</span></a>
               		 <ul>
                        <li><a href="image-gallery.html">Pretty Photo</a> </li>
                        <li><a href="gallery-with-filter.html">Gallery with Filter</a> </li>
                    </ul>
                </li>
                <li class="ic-notifications"><a href="notifications.html"><span>Notifications</span></a></li>
 				-->
            </ul>
        </div>
        <div class="clear">
        </div>
<!-- Header End -->
    
