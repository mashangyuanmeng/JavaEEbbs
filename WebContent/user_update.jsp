<%@ page contentType="text/html; charset=GBK"%>
<%@page import="bbs.*" %>
<SCRIPT language=JavaScript src="include/check.js" type="text/javascript"></SCRIPT>
<html>
<head>
<link rel=stylesheet href="include/main.css">
<title>
�޸�ע����Ϣ-<%=constant.title%>
</title>
</head>
<script language="JavaScript">
function userUpdate(){
    if(document.frmAction.password.value == null ||
    checkLength(document.frmAction.password.value)<1){
    	alert('�������û����룡');
        return false;
    }
    if(document.frmAction.repassword.value == null ||
    checkLength(document.frmAction.repassword.value)<1){
    	alert('������ȷ�����룡');
        return false;
    }
    if(document.frmAction.password.value != document.frmAction.repassword.value){
    	alert('�����������벻��ͬ��');
        return false;
    }
    if(document.frmAction.email.value == null ||
    checkLength(document.frmAction.email.value)<1){
    	alert('������Email��');
        return false;
    }
    if(document.frmAction.nickname.value == null ||
    checkLength(document.frmAction.nickname.value)<1){
    	alert('�������سƣ�');
        return false;
    }
    document.frmAction.action="loginservlet?method=user_update";
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
    session.setAttribute("user_update", "user_update");
    user user = (user)session.getAttribute("user");
    String passWord = user.getPassword();
    String email = user.getEmail();
    String nickname = user.getNickname();
//    String passWord = (String)request.getParameter("password");
//    String email = (String)request.getParameter("email");
//    String nickname = (String)request.getParameter("nickname");
    if(passWord == null) passWord="";
    if(email == null) email="";
    if(nickname == null) nickname="";
%>
<jsp:include page="error_info.jsp">
    <jsp:param name="desc" value="user_update_error"/>
</jsp:include>
<table border="1px #76aef0 solid" cellspacing="0" cellpadding="1" align=center bgColor=#E4E8EF>
    <tr>
        <td width="50%">�����룺</td>
        <td width="50%"><input type="password" name="password"  value="<%=passWord%>"/></td>
    </tr>
    <tr>
        <td width="50%">����ȷ�ϣ�</td>
        <td width="50%"><input type="password" name="repassword"  value="<%=passWord%>"/></td>
    </tr>
    <tr>
        <td width="50%">Email��</td>
        <td width="50%"><input type="text" name="email"  value="<%=email%>"/></td>
    </tr>
    <tr>
        <td width="50%">�سƣ�</td>
        <td width="50%"><input type="text" name="nickname"  value="<%=nickname%>"/></td>
    </tr>
    <tr>
        <td>
            <center>
            <input type="submit" onclick="userUpdate();"  value="ȷ��" class="button"/>
            </center>
        </td>
    </tr>
</table>
</center>
</FORM>
</body>
</html>
