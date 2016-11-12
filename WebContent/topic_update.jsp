<%@ page contentType="text/html; charset=GBK"%>
<%@page import="bbs.*" %>
<SCRIPT language=JavaScript src="include/check.js" type="text/javascript"></SCRIPT>
<link rel=stylesheet href="include/style.css">
<script language="JavaScript">
function bbsUpdate(forum_id,bbs_id,parent_id){
    if(bbs_id==parent_id){
 	if(document.frmAction.title.value == null ||
	    checkLength(document.frmAction.title.value)<1){
    	    alert('请输入话题！');
	    return ;
	}
    }
    if(document.frmAction.content.value == null ||
    checkLength(document.frmAction.content.value)<1){
    	alert('请输入内容！');
        return ;
    }
    document.frmAction.action="topicservlet?method=answer_update&bbs_id=" + bbs_id +"&forum_id=" + forum_id + "&parent_id=" + parent_id;
    document.frmAction.submit();
}
</script>
<%
session.setAttribute("answer_update", "answer_update");
String title = request.getParameter("title");
String content = request.getParameter("content");
String bbs_id = request.getParameter("bbs_id");
String forum_id = request.getParameter("forum_id");
String parent_id = request.getParameter("parent_id");
try 
{
	topic ann = topichandle.getTopic(Integer.parseInt(bbs_id));
	if(title == null) 
       title = ann.getTitle();
	if(content == null) 
        content = ann.getContent();
} 
catch(Exception es)
{
    es.printStackTrace();
}
%>

<html>
<head>
<link rel=stylesheet href="include/main.css">
<title>
修改帖子-<%=bbs.constant.title%>
</title>
</head>
<body bgcolor="#ffffff">
<br>
<br>
<br>
<br>
<FORM  method="POST"  name="frmAction"  >
<center>

<jsp:include page="error_info.jsp">
        <jsp:param name="desc" value="topic_update_error" />
</jsp:include>

<table border="1px #76aef0 solid" cellspacing="0" cellpadding="1" align=center bgColor=#E4E8EF>
<%
   if(bbs_id.equalsIgnoreCase(parent_id)){
%>
    <tr>
        <td>话题：</td>
        <td><input type="text"  size="80" name="title" value="<%=title%>"/></td>
    </tr>
<%}%>
    <tr>
        <td>内容：</td>
        <td>
           <textarea name="content" rows=8 cols=80><%=content%></textarea>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <center>
            <input type="submit" onclick="bbsUpdate(<%=forum_id%>,<%=bbs_id%>,<%=parent_id%>);"  value="确定" class="button"/>
            </center>
        </td>
    </tr>
</table>
</center>
</FORM>
</body>
</html>
