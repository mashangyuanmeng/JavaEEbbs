<%@ page contentType="text/html; charset=GBK"%>
<SCRIPT language=JavaScript src="include/check.js" type="text/javascript"></SCRIPT>
<link rel=stylesheet href="include/style.css">
<script language="JavaScript">
function bbsInsert(forum_id){
    //������֤����������ݲ���Ϊ��
    if(document.frmAction.title.value == null ||
    checkLength(document.frmAction.title.value)<1){
    	alert('�����뻰�⣡');
        return ;
    }
    if(document.frmAction.content.value == null ||
    checkLength(document.frmAction.content.value)<1){
    	alert('���������ݣ�');
        return ;
    }
    //�����ύ��TopicServlet
    document.frmAction.action="topicservlet?method=topic_add&forum_id=" + forum_id;
    document.frmAction.submit();
}
</script>
<%
//��ֹ�ظ��ύ����session��д��һ��������
//TopicServlet�жϸñ����Ƿ�Ϊ�գ����Ϊ�գ��ж�Ϊ�ظ��ύ
//�����Ϊ�գ��ж��ǵ�һ���ύ�������ñ�����session��ɾ��
session.setAttribute("topic_add", "topic_add");
//��¼�û�����
String title = request.getParameter("title");
if(title == null) title = "";
String content = request.getParameter("content");
if(content == null) content = "";
//��request��ȡ������ID������ID
String forum_id = request.getParameter("forum_id");
%>

<html>
<head>
<link rel=stylesheet href="include/main.css">
<title>
��������-<%=bbs.constant.title%>
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
        <td>���⣺</td>
        <td><input type="text"  size="80" name="title" value="<%=title%>"/></td>
    </tr>
    <tr>
        <td>���ݣ�</td>
        <td>
           <textarea name="content" rows=8 cols=80><%=content%></textarea>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <center>
            <input type="submit" onclick="bbsInsert(<%=forum_id%>);"  value="ȷ��" class="button"/>
            </center>
        </td>
    </tr>
</table>
</center>
</FORM>
</body>
</html>
