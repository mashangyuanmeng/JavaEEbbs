<%@ page contentType="text/html; charset=GBK"%>
<SCRIPT language=JavaScript src="include/check.js" type="text/javascript"></SCRIPT>
<link rel=stylesheet href="include/style.css">
<script language="JavaScript">
function bbsInsert(forum_id){
    //输入验证：话题和内容不能为空
    if(document.frmAction.title.value == null ||
    checkLength(document.frmAction.title.value)<1){
    	alert('请输入话题！');
        return ;
    }
    if(document.frmAction.content.value == null ||
    checkLength(document.frmAction.content.value)<1){
    	alert('请输入内容！');
        return ;
    }
    //请求提交给TopicServlet
    document.frmAction.action="topicservlet?method=topic_add&forum_id=" + forum_id;
    document.frmAction.submit();
}
</script>
<%
//防止重复提交，在session中写入一个变量，
//TopicServlet判断该变量是否为空，如果为空，判断为重复提交
//如果不为空，判断是第一次提交，并将该变量从session中删除
session.setAttribute("topic_add", "topic_add");
//记录用户输入
String title = request.getParameter("title");
if(title == null) title = "";
String content = request.getParameter("content");
if(content == null) content = "";
//从request获取讨论区ID和帖子ID
String forum_id = request.getParameter("forum_id");
%>

<html>
<head>
<link rel=stylesheet href="include/main.css">
<title>
发表新贴-<%=bbs.constant.title%>
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
        <jsp:param name="desc" value="topic_add_error" />
</jsp:include>

<table border="1px #76aef0 solid" cellspacing="0" cellpadding="1" align=center bgColor=#E4E8EF>
    <tr>
        <td>话题：</td>
        <td><input type="text"  size="80" name="title" value="<%=title%>"/></td>
    </tr>
    <tr>
        <td>内容：</td>
        <td>
           <textarea name="content" rows=8 cols=80><%=content%></textarea>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <center>
            <input type="submit" onclick="bbsInsert(<%=forum_id%>);"  value="确定" class="button"/>
            </center>
        </td>
    </tr>
</table>
</center>
</FORM>
</body>
</html>
