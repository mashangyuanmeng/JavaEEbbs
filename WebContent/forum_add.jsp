<%@ page contentType="text/html; charset=GBK"%>
<SCRIPT language=JavaScript src="include/check.js" type="text/javascript"></SCRIPT>
<link rel=stylesheet href="include/style.css">
<script language="JavaScript">
//创建讨论区名称
function forumAdd(){
    if(document.frmAction.name.value == null ||
    checkLength(document.frmAction.name.value)<1){
    	alert('请输入讨论区名！');
        return ;
    }
    if(document.frmAction.sort.value == null ||
    checkLength(document.frmAction.sort.value)<1){
    	alert('请输入顺序号！');
        return ;
    }
    document.frmAction.action="forumservlet?method=forum_add";
    document.frmAction.submit();
}
</script>
<%
session.setAttribute("forum_add", "forum_add");
String name = request.getParameter("name");
if(name == null) name = "";
String sort = request.getParameter("sort");
if(sort == null) sort = "";
String description = request.getParameter("description");
if(description == null) description = "";
//这段代码用于记录用户的输入信息
//后面<input type="text" name="name" value="<%=name% >">用到了
//这是为了防止用户输入之后，还没有提交就转到其它页面，在回来的时候发现输入信息全没了
//也可以防止提交失败之后，导致的输入信息全部丢失
%>
<html>
<head>
<link rel=stylesheet href="include/main.css">
<title>
创建讨论区-<%=bbs.constant.title%>
</title>
</head>
<body  bgcolor="#ffffff">
<br>
<br>
<br>
<br>
<FORM  method="POST"  name="frmAction"  >
<center>

<jsp:include page="error_info.jsp">
        <jsp:param name="desc" value="forum_error" />
</jsp:include>

<table border="1px #76aef0 solid" cellspacing="0" cellpadding="1" align=center bgColor=#E4E8EF>
    <tr>
        <td>名称：</td>
        <td><input type="text" name="name" value="<%=name%>"/></td>
    </tr>
    <tr>
        <td>顺序号：</td>
        <td><input type="text" name="sort" value="<%=sort%>"/></td>
    </tr>
    <tr>
        <td align="left" valign="top">描述：</td>
        <td>
            <textarea name="description" rows=5 cols=50><%=description%></textarea>
        </td>
    </tr>
    <tr>
        <td  colspan="2">
            <center>
            <input type="submit" onclick="forumAdd();"  value="确定" class="button"/>
            </center>
        </td>
    </tr>
</table>
</center>
</FORM>
</body>
</html>
