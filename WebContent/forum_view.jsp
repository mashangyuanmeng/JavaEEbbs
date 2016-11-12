<%@ page contentType="text/html; charset=GBK"%>
<%@page import="bbs.*" %>
<html>
<title>
讨论区信息浏览<%=bbs.constant.title%>
</title>
<link rel=stylesheet href="include/style.css">
<body>
<center>
<%
String forum_id = request.getParameter("forum_id");
forum forum = (forum) request.getAttribute("forum");
request.removeAttribute("forum");
if(forum == null){
    out.println("<h4>获取类型信息失败</h4>");
} else {
%>
<br>
    <br>
<table border="1px #76aef0 solid" cellspacing="0" cellpadding="1" align=center bgColor=#E4E8EF>
    <tr>
        <td>类别名</td>
        <td><%=forum.getName()%></td>
    </tr>
    <tr>
        <td>顺序号</td>
        <td><%=forum.getSort()%></td>
    </tr>
    <tr>
        <td>描述</td>
        <td><%=forum.getDescription()%></td>
    </tr>
</table>
<%}%>
</center>
</body>
</html>
