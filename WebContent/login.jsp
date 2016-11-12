<%@ page contentType="text/html; charset=GBK"%>
<SCRIPT language=JavaScript src="include/check.js" type="text/javascript"></SCRIPT>

<script language="JavaScript">
function userLogin(){<%--调用函数当用户点击登录按钮--%>
    if(document.frmAction.username.value == null ||
    checkLength(document.frmAction.username.value)<1){<%-- 判读用户的输入姓名值是否不为空 --%>
    	alert('管理员易敏（1306410106）提醒您：请输入用户名！');
        return ;
    }
    if(document.frmAction.password.value == null ||
    checkLength(document.frmAction.password.value)<1){<%--判断用户密码输入值是否不为空 --%>
    	alert('请输入用户密码！');
        return ;
    }
    document.frmAction.action="loginservlet?method=user_login";
    document.frmAction.submit();
}
function userReg(){<%--当用户注册时调用该函数 --%>
    document.frmAction.action="user_add.jsp";
    <%--跳转到user_add.jsp页面--%>
    document.frmAction.submit();
}
</script>
<%
String username = request.getParameter("username");
if(username == null) 
    username = "";
    //当姓名为空时
String password = request.getParameter("password");
if(password == null) 
    password = "";
    //当密码为空时
%>

<html>

<head>
<link rel=stylesheet href="include/main.css">
<title>
用户登录
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
    <jsp:param name="desc" value="login_error"/>
</jsp:include>
<table border="1px #76aef0 solid" cellspacing="0" cellpadding="1" align=center bgColor=#E4E8EF>
    <tr>
        <td width="50%">用户名：</td>
        <td width="50%"><input type="text" name="username" value="<%=username%>"/></td>
    </tr>
    <tr>
        <td width="50%">密码：</td>
        <td width="50%"><input type="password" name="password" value="<%=password%>"/></td>
    </tr>
   <tr>
        <td width="50%">
            <center>
            <input type="submit" onclick="userLogin();"  value="登录" class="button"/>
            </center>
        </td>
        <td width="50%">
            <center>
            <input type="submit" onclick="userReg();"  value="注册" class="button"/>
            </center>
        </td>
    </tr>

</table>
</center>
</FORM>
</body>
</html>
