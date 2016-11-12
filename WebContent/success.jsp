<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<link rel=stylesheet href="include/main.css">
<title>
操作结果-<%=bbs.constant.title%>
</title>
</head>
<body bgcolor="#ffffff">
<%
    String flag = request.getParameter("flag");
    if(flag == null || flag.equalsIgnoreCase("true")){
%>
<h2>
操作成功！
</h2>
<%} else {%>
<div id="text">
<h4>
操作失败！您没有相应的权限
</h4>
</div>
<%} %>

<%
    String url = request.getParameter("url");
    if(url == null){
%>
<div id="text">
<h3><a href = "index.jsp">返回主页面</a></h3>
<div>
<%} else {%>
<a href = "<%=url%>">返回</a>
<%}%>
</body>
</html>
