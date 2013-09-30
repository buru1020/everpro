<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>EverProject - Login</title>
  <link rel="icon" type="image/png" href="${rootPath}/res/logo_sim.png">
  
<link rel="stylesheet" href="${rootPath}/css/login_1.css" type="text/css" />
<link rel="stylesheet" href="${rootPath}/css/login_2.css" type="text/css" />
  
</head>
<body class="production workflow-layout">

<div id="page">
  <div id="wrapper">
    <div id="content" role="main">
	  <div class="login-box workflow-box small">
	  	<img alt="logo" src="${rootPath}/res/logo_text_grey_1.png" id="log_img" >
	  </div>
    
	  <div id="login" class="login-box workflow-box small" data-method="standard">
<!-- 	    <header>
	      <h1>Log in</h1>
	    </header> -->
	    <section class="clearfix" id="log-in-container">
			<div>
			  <form action="login.do" method="post" id="login-form" class="aui login-form">
			  	<!-- <input type="hidden" name="tryLogin" value="true"> -->
		        <div class="field-group username">
		          <input type="text" class="text" id="id_username" name="email" 
		          <c:if test="${email != ''}">value='${email}'</c:if>
		           autofocus="autofocus" placeholder="Email" />
		        </div>
			    <div class="field-group">
			      <input type="password" id="id_password" class="password" name="password" placeholder="password" />
			    </div>
			    <div class="buttons-container">
			      <div class="buttons">
			        <button type="submit" name="submit" class="aui-button aui-style aui-button-primary">
			            Log in
			        </button>
			        <Span style="margin-left: 10px">
			        	<input type="checkbox" name="saveId" 
			        	<c:if test="${isSaveId}">checked</c:if>><span>save ID</span>
			        </span>
			      </div>
					<div class="sign-up">
			          <a href="${rootPath}/member/signup.do">Need an account? Sign up free.</a>
			        </div>
			    </div>
			  </form>
			</div>
	    </section>
 	 </div>
 	 
 	 
    </div>
  </div>
</div>

</html>