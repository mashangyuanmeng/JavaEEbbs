<%@ page contentType="text/html; charset=GBK" %>
<%@page import="bbs.*" %>
<%@page import="java.util.*" %>
<SCRIPT language=JavaScript src="include/check.js" type="text/javascript"></SCRIPT>
<script language="JavaScript">
//����ɾ������������
function deleteForum(forum_id){
     var flag = window.confirm("ȷ��Ҫɾ������������");
     if (flag) 
     {
 	    document.frmAction.action="forumservlet?method=forum_delete&forum_id=" + forum_id;
    	document.frmAction.submit();
     }
}
</script>
<html>
<head>
<link rel=stylesheet href="include/style.css">
<title>
<%=bbs.constant.title%>
</title>
</head>
<%
 ArrayList array = (ArrayList)request.getAttribute("forums");
//��request�����а��������б��ȡ�����������Ǵ����ݿ��е�������
%>
<jsp:include page="error_info.jsp">
    <jsp:param name="desc" value="forum_error"/>
</jsp:include>
<body bgcolor="#ffffff">
<FORM  method="POST"  name="frmAction">
<center>
    <br><img src="images/return.gif">
    <a href="index.jsp">������һ��</a>

<%
   user user = (user)session.getAttribute("user");
   if(user != null && user.getDegree() == user.superadmin) 
   {
%>
    <img src="images/new.gif">
    <a href="forum_add.jsp">����������</a>
<%}%>
    <br>
<table border="1px" cellspacing="0" cellpadding="10" align=center width="90%" bgColor=#E4E8EF>
<tr bgcolor="#76aef0">
<th width="15%">����</th>
<th width="10%">˳���</th>
<th width="45%">����</th>
<th width="20%">����</th>
</tr>
<%
for(int i = 0; i < array.size(); i++){
    forum forum = (forum)array.get(i);
%>
<tr>
  <td><img src="images/topics.gif" align=left>
<a href="topicservlet?method=topic_select&forum_id=<%=forum.getId()%>"><%=forum.getName()%></a>
  </td>
<td align="center"><%=forum.getSort()%></td>
<td><%=forum.getDescription()%></td>
<td align="center">
<a href="#" onclick="MM_openBrWindow('forumservlet?method=forum_view&forum_id=<%=forum.getId()%>', 'popup', 'scrollbars=yes,resizable=yes,width=400,height=200,center=yes')">����</a>
<%
   if(user != null && user.getDegree() == user.superadmin) {
%>
<a href="forumservlet?method=forum_edit&forum_id=<%=forum.getId()%>">�༭</a>
<a href="#" onclick="deleteForum(<%=forum.getId()%>);">ɾ��</a>
<%}%>
</td>
</tr>
<%}%>
</table>
</center>
</Form>
</body>
</html>
