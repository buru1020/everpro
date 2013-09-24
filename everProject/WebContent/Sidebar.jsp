<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="member" scope="session" type="net.bitacademy.java41.vo.Member"/>
<jsp:useBean id="projectService" scope="application" type="net.bitacademy.java41.services.ProjectService"/>
<%
pageContext.setAttribute("projectList", projectService.getMyProjects(member.getEmail()));
%>
<!-- Side Menu Start -->
        <div class="grid_2">
            <div class="box sidemenu">
                <div class="block" id="section-menu">
                    <ul class="section menu">
<c:choose> 
	<c:when test="${member.level == 1}">
		<!-- 관리프로젝트 -->
                        <li><a class="menuitem">관리메뉴</a>
                            <ul class="submenu">
				                <li><a href="${rootPath}/member/list.do">멤버관리</a></li>
								<li><a href="${rootPath}/project/list.do">프로젝트관리</a></li>
                            </ul>
                        </li>		
	</c:when>
	<c:otherwise>
		<!-- 참여프로젝트 -->
                        <li><a class="menuitem">참여 프로젝트</a>
                            <ul class="submenu">
                            	<li class="sidebar_total"><a href="${rootPath}/project/list.do" ><span>전체보기</span></a></li>
		<c:forEach var="myProject" items="${projectList}">			
								<li><a href="${rootPath}/project/view.do?no=${myProject.no}" >${myProject.title}<c:if test="${myProject.level == 0}">&nbsp;&nbsp;★</c:if></a></li>
		</c:forEach>	
                            </ul>
                        </li>		
	</c:otherwise>
</c:choose>                    
<!--                         <li><a class="menuitem">Menu 2</a>
                            <ul class="submenu">
                                <li><a>Submenu 1</a> </li>
                                <li><a>Submenu 2</a> </li>
                                <li><a>Submenu 3</a> </li>
                                <li><a>Submenu 4</a> </li>
                                <li><a>Submenu 5</a> </li>
                            </ul>
                        </li>
                        <li><a class="menuitem">Menu 3</a>
                            <ul class="submenu">
                                <li><a>Submenu 1</a> </li>
                                <li><a>Submenu 2</a> </li>
                                <li><a>Submenu 3</a> </li>
                                <li><a>Submenu 4</a> </li>
                                <li><a>Submenu 5</a> </li>
                                <li><a>Submenu 1</a> </li>
                                <li><a>Submenu 2</a> </li>
                                <li><a>Submenu 3</a> </li>
                                <li><a>Submenu 4</a> </li>
                                <li><a>Submenu 5</a> </li>
                            </ul>
                        </li>
                        <li><a class="menuitem">Menu 4</a>
                            <ul class="submenu">
                                <li><a>Submenu 1</a> </li>
                                <li><a>Submenu 2</a> </li>
                                <li><a>Submenu 3</a> </li>
                                <li><a>Submenu 4</a> </li>
                                <li><a>Submenu 5</a> </li>
                                <li><a>Submenu 6</a> </li>
                                <li><a>Submenu 7</a> </li>
                                <li><a>Submenu 8</a> </li>
                                <li><a>Submenu 9</a> </li>
                                <li><a>Submenu 10</a> </li>
                    
                            </ul>
                        </li> 
-->
                    </ul>
                </div>
            </div>
        </div>
<!-- Side Menu End -->




