<%@ page contentType="text/html; charset=GBK"%>
<%@page import="bbs.*" %>
<SCRIPT language=JavaScript src="include/check.js" type="text/javascript"></SCRIPT>
<link rel=stylesheet href="include/style.css">
<script language="JavaScript">
//�жϺ������ж����ƺ����к��Ƿ�����
function forumUpdate(forum_id){
    if(document.frmAction.name.value == null ||
    checkLength(document.frmAction.name.value)<1){
    	alert('�������������');
        return ;
    }
    if(document.frmAction.sort.value == null ||
    checkLength(document.frmAction.sort.value)<1){
    	alert('������˳��ţ�');
        return ;
    }
    document.frmAction.action="forumservlet?method=forum_update&forum_id=" + forum_id;
    document.frmAction.submit();
}
</script>
<%
session.setAttribute("forum_update", "forum_update");
forum forum = (forum)request.getAttribute("forum");
//��request�����ж�ȡ����������
String forum_id = request.getParameter("forum_id");
//ȡ��������ID
%>

<html>
<head>
<link rel=stylesheet href="include/main.css">
<title>
�޸�������-<%=bbs.constant.title%>
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
        <jsp:param name="desc" value="forum_error" />
</jsp:include>

<table border="1px" cellspacing="0" cellpadding="3" align=center bgColor=#E4E8EF>
    <tr>
        <td>���ƣ�</td>
        <td><input type="text" name="name" value="<%=forum.getName()%>"/></td>
    </tr>
    <tr>
        <td>˳��ţ�</td>
        <td><input type="text" name="sort" value="<%=forum.getSort()%>"/></td>
    </tr>
    <tr>
        <td align="left" valign="top">������</td>
        <td>
            <textarea name="description" rows=5 cols=50><%=forum.getDescription()%></textarea>
        </td>
    </tr>
    <tr>
        <td  colspan="2">
            <center>
            <input type="submit" onclick="forumUpdate(<%=forum_id%>);"  value="ȷ��" class="button"/>
            </center>
        </td>
    </tr>
</table>
</center>
</FORM>
</body>
</html>
