<%@ page contentType="text/html; charset=GBK"%>
<SCRIPT language=JavaScript src="include/check.js" type="text/javascript"></SCRIPT>
<link rel=stylesheet href="include/style.css">
<script language="JavaScript">
//��������������
function forumAdd(){
    if(document.frmAction.name.value == null ||
    checkLength(document.frmAction.name.value)<1){
    	alert('����������������');
        return ;
    }
    if(document.frmAction.sort.value == null ||
    checkLength(document.frmAction.sort.value)<1){
    	alert('������˳��ţ�');
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
//��δ������ڼ�¼�û���������Ϣ
//����<input type="text" name="name" value="<%=name% >">�õ���
//����Ϊ�˷�ֹ�û�����֮�󣬻�û���ύ��ת������ҳ�棬�ڻ�����ʱ����������Ϣȫû��
//Ҳ���Է�ֹ�ύʧ��֮�󣬵��µ�������Ϣȫ����ʧ
%>
<html>
<head>
<link rel=stylesheet href="include/main.css">
<title>
����������-<%=bbs.constant.title%>
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
        <td>���ƣ�</td>
        <td><input type="text" name="name" value="<%=name%>"/></td>
    </tr>
    <tr>
        <td>˳��ţ�</td>
        <td><input type="text" name="sort" value="<%=sort%>"/></td>
    </tr>
    <tr>
        <td align="left" valign="top">������</td>
        <td>
            <textarea name="description" rows=5 cols=50><%=description%></textarea>
        </td>
    </tr>
    <tr>
        <td  colspan="2">
            <center>
            <input type="submit" onclick="forumAdd();"  value="ȷ��" class="button"/>
            </center>
        </td>
    </tr>
</table>
</center>
</FORM>
</body>
</html>
