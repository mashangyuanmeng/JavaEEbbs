<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<link rel=stylesheet href="include/main.css">
<title>
�������-<%=bbs.constant.title%>
</title>
</head>
<body bgcolor="#ffffff">
<%
    String flag = request.getParameter("flag");
    if(flag == null || flag.equalsIgnoreCase("true")){
%>
<h2>
�����ɹ���
</h2>
<%} else {%>
<div id="text">
<h4>
����ʧ�ܣ���û����Ӧ��Ȩ��
</h4>
</div>
<%} %>

<%
    String url = request.getParameter("url");
    if(url == null){
%>
<div id="text">
<h3><a href = "index.jsp">������ҳ��</a></h3>
<div>
<%} else {%>
<a href = "<%=url%>">����</a>
<%}%>
</body>
</html>
