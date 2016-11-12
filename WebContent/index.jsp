<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="bbs.*" %>
<html>
<head>
	<link rel=stylesheet href="include/main.css">
<title>
欢迎来到<%=bbs.constant.title%>
</title>
</head>
<body>
<div class="wrap">
		<h1 id="logo">JAVA  BBS</h1>
		    <ul id="menu">
			   欢迎光临！！！！
		     </ul>
		    <div id="text">
			<h2>欢迎来到javaEEbbs</h2>
			</div>
			
			<div id="text">
			<h3> 您可以进行如下操作：</h3>
			</div>
			
			<div id="text">
			<h3><a href="forumservlet?method=forum_select">进入BBS论坛</a></h3>
			</div> 
			
			<div id="text">
			<h3><a href="login.jsp">注册登录</a></h3> 
			</div>
			<div id="text">
			<h3> 
<% 
    user user = (user)session.getAttribute("user"); 
    if(user != null) { 
%> 

<a href="user_update.jsp">修改注册信息</a>
 
<%}%> 

</h3></div>
	<div id="green_bubble">
	
		</div>
	</div>
	<div id="footer">
		<div class="wrap">
			
			<div id="copyright">
				<p>This bbs user is yimin</p>
			</div>

			<div class="clear"></div>
		</div>
	</div>
</body>
</html>
