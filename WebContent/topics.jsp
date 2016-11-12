<%@ page contentType="text/html; charset=GBK" %>
<%@page import="bbs.*" %>
<%@page import="java.util.*" %>
<link rel=stylesheet href="include/style.css">
<SCRIPT language=JavaScript src="include/check.js" type="text/javascript"></SCRIPT>
<script language="JavaScript">
function deleteTopic(forum_id, bbs_id){
     var flag = window.confirm("确定要删除该帖子吗？");
     if (flag) {
 	document.frmAction.action="topicservlet?method=topic_delete&forum_id=" + forum_id + "&bbs_id=" + bbs_id;
    	document.frmAction.submit();
     }
}
</script>
<html>
<%
ArrayList array = (ArrayList)request.getAttribute("topics");
//取出request对象中保存的帖子列表，并展示在JSP页面中
if(array == null){
    array = new ArrayList();
    //如果为空，生成一个空数组
}
String forum_id = request.getParameter("forum_id");
//从request对象中获取讨论区id
page pageInfo = (page)request.getAttribute("pageInfo");
//取出保存在request中的分页对象
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
    <a href="forumservlet?method=forum_select">返回上一级</a>&nbsp;&nbsp;<img src="images/new.gif">
    <a href="topic_add.jsp?forum_id=<%=forum_id%>">发表新贴</a>
    <br>
<table  border="1px #76aef0 solid" cellspacing="0" cellpadding="10" align=center bgColor=#E4E8EF  width="90%">
<tr bgcolor="#76aef0">
<th>主题</th>
<th>作者</th>
<th>回复</th>
<th>时间</th>
<%
   user user = (user)session.getAttribute("user");
   if(user != null && user.getDegree() == user.superadmin) {//如果是超级管理员，提供“操作”选项
%>
<th>操作</th>
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
   if(user != null && user.getDegree() == user.superadmin) { //如果是超级管理员，提供“删除”功能
%>
<td>
<a href="#" onclick="deleteTopic(<%=forum_id%>, <%=ann.getId()%>);">删除</a>
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