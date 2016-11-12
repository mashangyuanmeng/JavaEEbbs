<%@ page contentType="text/html; charset=GBK"%>
<%@page import="bbs.*" %>
<%@page import="java.util.*" %>
<SCRIPT language=JavaScript src="include/check.js" type="text/javascript"></SCRIPT>
<link rel=stylesheet href="include/style.css">
<script language="JavaScript">
function deleteTopic(parent_id, forum_id, bbs_id){
     var flag = window.confirm("确定要删除该帖子吗？");
     if (flag) {
 	document.frmAction.action="topicservlet?method=answer_delete&parent_id=" + parent_id + "&forum_id=" + forum_id + "&bbs_id=" + bbs_id;
    	document.frmAction.submit();
     }
}
function bbsInsert(forum_id, bbs_id){
    //发表回帖
    //判断输入内容是否为空，如果为空，不做处理，否则提交给TopicServlet
    if(document.frmAction.content.value == null ||
    checkLength(document.frmAction.content.value)<1){
    	alert('请输入内容！');
        document.frmAction.action="topicservlet?method=topic_view&forum_id=" + forum_id + "&bbs_id=" + bbs_id;
        document.frmAction.submit();
        return ;
    }
    document.frmAction.action="topicservlet?method=answer_add&forum_id=" + forum_id + "&parent_id=" + bbs_id;
    document.frmAction.submit();
}
</script>
<html>
<link rel=stylesheet href="include/style.css">
<title>
    <%=bbs.constant.title%>
</title>
<body>
<center>
<%
session.setAttribute("answer_add", "answer_add");
//防止重复提交，在session中写入一个变量，
//TopicServlet判断该变量是否为空，如果为空，判断为重复提交
//如果不为空，判断是第一次提交，并将该变量从session中删除
page pageInfo = (page)request.getAttribute("pageInfo");
ArrayList array = (ArrayList)request.getAttribute("topic_answer");
//取出request对象中保存的帖子列表，并展示在JSP页面中
user user = (user)session.getAttribute("user");
//从session中取出用户信息
String forum_id = request.getParameter("forum_id");
String bbs_id = request.getParameter("bbs_id");
//从request中取出讨论区ID和帖子ID
String title = null;
//帖子标题
String replyContent = request.getParameter("replyContent");
//从请求中获取回复内容
if(replyContent == null) replyContent = "";
%>
<FORM  method="POST"  name="frmAction"><img src="images/return.gif">
	<a href="topicservlet?method=topic_select&forum_id=<%=forum_id%>">返回上一级</a>
<%
if(array == null){
    //如果结果集对象为空，展示“获取帖子信息失败！”信息
    out.println("<h3>获取帖子信息失败！</h3>");
} else if(array.size() < 1){
    //如果结果集为空，提示“该帖子已被删除！”
%>
<%
    out.println("<h3>该帖子已被删除！</h3>");
} else {
    //获取帖子标题
    title = ((topic)array.get(0)).getTitle();
%>
<table   border="1px #76aef0 solid" cellspacing="0" cellpadding="10"  align=center width="90%">
    <tr>
        <th colspan="2" align="left" bgcolor="#76aef0">
            帖子主题：<%=title%>
        </th>
    </tr>
<%
   for(int i = 0; i < array.size(); i++){
       //在页面上列出结果集中的帖子以及回帖内容
       topic ann = (topic)array.get(i);
%>
    <tr>
        <td  align="center" valign="top" width="16%"  bgColor=#E4E8EF>
            <img src="images/zayan.gif"><%=ann.getUsername()%><br>
            <img src="images/moon.gif" align=center>
        <br><%=ann.getPostdate()%>
<%
   if(user != null && user.getDegree() == user.superadmin) {
       //如果是超级管理员，提供“删除”“编辑”帖子内容的功能
%>
        <br>
<a href="#" onclick="deleteTopic(<%=bbs_id%>, <%=forum_id%>, <%=ann.getId()%>);">删除</a>
<a href="topic_update.jsp?forum_id=<%=forum_id%>&parent_id=<%=bbs_id%>&bbs_id=<%=ann.getId()%>">编辑</a>
<%}%>
	</td>
        <td  align="left" valign="top" width="84%"><%=StringUtil.getBRString(ann.getContent())%></td>
    </tr>
<%}%>
    <tr>
        <td  bgColor=#E4E8EF>快速回复：</td>
        <td   bgColor=#E4E8EF>
           <textarea name="content" rows=8 cols=80 align=left><%=replyContent%></textarea><br>
           <img src="images/newreply.gif" align=left onclick="bbsInsert(<%=forum_id%>, <%=bbs_id%>);"/>
        </td>
    </tr>
    <tr>
        <td colspan="2">
                <%=pageInfo.getFooter()%>
        </td>
    </tr>
</table>
</FORM>
<%}%>
</center>
</body>
</html>
