<%@ page contentType="text/html; charset=GBK"%>
<%@page import="bbs.*" %>
<SCRIPT language=JavaScript src="include/check.js" type="text/javascript"></SCRIPT>
<html>
<head>
<link rel=stylesheet href="include/main.css">
<title>
用户注册
</title>
</head>
<script language="JavaScript">
function userReg(){
    if(document.frmAction.username.value == null ||
    checkLength(document.frmAction.username.value)<1){
    	alert('请输入用户名！');
        return false;
    }
    if(document.frmAction.password.value == null ||
    checkLength(document.frmAction.password.value)<1){
    	alert('请输入用户密码！');
        return false;
    }
    if(document.frmAction.repassword.value == null ||
    checkLength(document.frmAction.repassword.value)<1){
    	alert('请输入确认密码！');
        return false;
    }
    if(document.frmAction.password.value != document.frmAction.repassword.value){
    	alert('两次输入密码不相同！');
        return false;
    }
    if(document.frmAction.email.value == null ||
    checkLength(document.frmAction.email.value)<1){
    	alert('请输入Email！');
        return false;
    }
    if(document.frmAction.nickname.value == null ||
    checkLength(document.frmAction.nickname.value)<1){
    	alert('请输入呢称！');
        return false;
    }
    document.frmAction.action="loginservlet?method=user_add";
    document.frmAction.submit();
}
</script>
<body bgcolor="#ffffff">
<br>
<br>
<br>
<br>
<FORM  method="POST"  name="frmAction" >
<center>

<%
    session.setAttribute("user_add", "user_add");
    String username = (String)request.getParameter("username");
    String email = (String)request.getParameter("email");
    String nickname = (String)request.getParameter("nickname");
    if(username == null) 
           username="";//判断用户名是否为空
    if(email == null) 
           email="";//判断email是否为空
    if(nickname == null) 
           nickname="";//判断用户昵称是否为空
%>
<jsp:include page="error_info.jsp">
    <jsp:param name="desc" value="user_add_error"/>
</jsp:include>
<table border="1px #76aef0 solid" cellspacing="0" cellpadding="1" align=center bgColor=#E4E8EF>
    <tr>
        <td width="50%">用户名：</td>
        <td width="50%"><input type="text" name="username" value="<%=username%>" /></td>
    </tr>
    <tr>
        <td width="50%">密码：</td>
        <td width="50%"><input type="password" name="password"/></td>
    </tr>
    <tr>
        <td width="50%">密码确认：</td>
        <td width="50%"><input type="password" name="repassword"/></td>
    </tr>
    <tr>
        <td width="50%">Email：</td>
        <td width="50%"><input type="text" name="email"  value="<%=email%>" /></td>
    </tr>
    <tr>
        <td width="50%">用户呢称：</td>
        <td width="50%"><input type="text" name="nickname"  value="<%=nickname%>" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <center>
            <input type="submit" onclick="userReg();"  value="确定" class="button"/>
            </center>
        </td>
    </tr>
</table>
</center>
</FORM>
</body>
</html>
