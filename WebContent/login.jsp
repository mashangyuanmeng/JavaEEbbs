<%@ page contentType="text/html; charset=GBK"%>
<SCRIPT language=JavaScript src="include/check.js" type="text/javascript"></SCRIPT>

<script language="JavaScript">
function userLogin(){<%--���ú������û������¼��ť--%>
    if(document.frmAction.username.value == null ||
    checkLength(document.frmAction.username.value)<1){<%-- �ж��û�����������ֵ�Ƿ�Ϊ�� --%>
    	alert('����Ա������1306410106�����������������û�����');
        return ;
    }
    if(document.frmAction.password.value == null ||
    checkLength(document.frmAction.password.value)<1){<%--�ж��û���������ֵ�Ƿ�Ϊ�� --%>
    	alert('�������û����룡');
        return ;
    }
    document.frmAction.action="loginservlet?method=user_login";
    document.frmAction.submit();
}
function userReg(){<%--���û�ע��ʱ���øú��� --%>
    document.frmAction.action="user_add.jsp";
    <%--��ת��user_add.jspҳ��--%>
    document.frmAction.submit();
}
</script>
<%
String username = request.getParameter("username");
if(username == null) 
    username = "";
    //������Ϊ��ʱ
String password = request.getParameter("password");
if(password == null) 
    password = "";
    //������Ϊ��ʱ
%>

<html>

<head>
<link rel=stylesheet href="include/main.css">
<title>
�û���¼
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
        <td width="50%">�û�����</td>
        <td width="50%"><input type="text" name="username" value="<%=username%>"/></td>
    </tr>
    <tr>
        <td width="50%">���룺</td>
        <td width="50%"><input type="password" name="password" value="<%=password%>"/></td>
    </tr>
   <tr>
        <td width="50%">
            <center>
            <input type="submit" onclick="userLogin();"  value="��¼" class="button"/>
            </center>
        </td>
        <td width="50%">
            <center>
            <input type="submit" onclick="userReg();"  value="ע��" class="button"/>
            </center>
        </td>
    </tr>

</table>
</center>
</FORM>
</body>
</html>
