<%@ page contentType="text/html; charset=GBK" %>
<%@page import="bbs.*" %>
<%@page import="java.util.*" %>
<link rel=stylesheet href="include/style.css">
<SCRIPT language=JavaScript src="include/check.js" type="text/javascript"></SCRIPT>
<script language="JavaScript">
function deleteTopic(forum_id, bbs_id){
     var flag = window.confirm("ȷ��Ҫɾ����������");
     if (flag) {
 	document.frmAction.action="topicservlet?method=topic_delete&forum_id=" + forum_id + "&bbs_id=" + bbs_id;
    	document.frmAction.submit();
     }
}
</script>
<html>
<%
ArrayList array = (ArrayList)request.getAttribute("topics");
//ȡ��request�����б���������б���չʾ��JSPҳ����
if(array == null){
    array = new ArrayList();
    //���Ϊ�գ�����һ��������
}
String forum_id = request.getParameter("forum_id");
//��request�����л�ȡ������id
page pageInfo = (page)request.getAttribute("pageInfo");
//ȡ��������request�еķ�ҳ����
%>
<head>
<link rel=stylesheet href="include/style.css">
<title>
<%=bbs.forumhandle.getForumName(forum_id)%>-<%=bbs.constant.title%>
</title>
</head>

<body bgcolor="#ffffff">
<FORM  method="POST"  name="frmAction">
<center>
<jsp:include page="error_info.jsp">
    <jsp:param name="desc" value="topic_error"/>
</jsp:include>
    <br><img src="images/return.gif">
    <a href="forumservlet?method=forum_select">������һ��</a>&nbsp;&nbsp;<img src="images/new.gif">
    <a href="topic_add.jsp?forum_id=<%=forum_id%>">��������</a>
    <br>
<table  border="1px #76aef0 solid" cellspacing="0" cellpadding="10" align=center bgColor=#E4E8EF  width="90%">
<tr bgcolor="#76aef0">
<th>����</th>
<th>����</th>
<th>�ظ�</th>
<th>ʱ��</th>
<%
   user user = (user)session.getAttribute("user");
   if(user != null && user.getDegree() == user.superadmin) {//����ǳ�������Ա���ṩ��������ѡ��
%>
<th>����</th>
<%}%>
</tr>
<%
for(int i = 0; i < array.size(); i++){
    topic ann = (topic)array.get(i);
%>
<tr>
  <td><img src="images/hottopic.gif" align=left>
<a href="topicservlet?method=topic_view&bbs_id=<%=ann.getId()%>&forum_id=<%=forum_id%>" style=""><%=ann.getTitle()%></a>
  </td>
<td><%=ann.getUsername()%></td>
<td><%=ann.getReply()%></td>
<td><%=ann.getPostdate()%></td>
<%
   if(user != null && user.getDegree() == user.superadmin) { //����ǳ�������Ա���ṩ��ɾ��������
%>
<td>
<a href="#" onclick="deleteTopic(<%=forum_id%>, <%=ann.getId()%>);">ɾ��</a>
</td>
<%}%>
</tr>
<%}%>
<tr>
<td align="left" colspan="5"><%=pageInfo.getFooter()%> </td>
</tr>
</table>
</center>
</Form>
</body>
</html>