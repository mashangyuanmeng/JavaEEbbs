<%@ page contentType="text/html; charset=GBK"%>
<%@page import="bbs.*" %>
<%@page import="java.util.*" %>
<SCRIPT language=JavaScript src="include/check.js" type="text/javascript"></SCRIPT>
<link rel=stylesheet href="include/style.css">
<script language="JavaScript">
function deleteTopic(parent_id, forum_id, bbs_id){
     var flag = window.confirm("ȷ��Ҫɾ����������");
     if (flag) {
 	document.frmAction.action="topicservlet?method=answer_delete&parent_id=" + parent_id + "&forum_id=" + forum_id + "&bbs_id=" + bbs_id;
    	document.frmAction.submit();
     }
}
function bbsInsert(forum_id, bbs_id){
    //�������
    //�ж����������Ƿ�Ϊ�գ����Ϊ�գ��������������ύ��TopicServlet
    if(document.frmAction.content.value == null ||
    checkLength(document.frmAction.content.value)<1){
    	alert('���������ݣ�');
        document.frmAction.action="topicservlet?method=topic_view&forum_id=" + forum_id + "&bbs_id=" + bbs_id;
        document.frmAction.submit();
        return ;
    }
    document.frmAction.action="topicservlet?method=answer_add&forum_id=" + forum_id + "&parent_id=" + bbs_id;
    document.frmAction.submit();
}
</script>
<html>
<link rel=stylesheet href="include/style.css">
<title>
    <%=bbs.constant.title%>
</title>
<body>
<center>
<%
session.setAttribute("answer_add", "answer_add");
//��ֹ�ظ��ύ����session��д��һ��������
//TopicServlet�жϸñ����Ƿ�Ϊ�գ����Ϊ�գ��ж�Ϊ�ظ��ύ
//�����Ϊ�գ��ж��ǵ�һ���ύ�������ñ�����session��ɾ��
page pageInfo = (page)request.getAttribute("pageInfo");
ArrayList array = (ArrayList)request.getAttribute("topic_answer");
//ȡ��request�����б���������б���չʾ��JSPҳ����
user user = (user)session.getAttribute("user");
//��session��ȡ���û���Ϣ
String forum_id = request.getParameter("forum_id");
String bbs_id = request.getParameter("bbs_id");
//��request��ȡ��������ID������ID
String title = null;
//���ӱ���
String replyContent = request.getParameter("replyContent");
//�������л�ȡ�ظ�����
if(replyContent == null) replyContent = "";
%>
<FORM  method="POST"  name="frmAction"><img src="images/return.gif">
	<a href="topicservlet?method=topic_select&forum_id=<%=forum_id%>">������һ��</a>
<%
if(array == null){
    //������������Ϊ�գ�չʾ����ȡ������Ϣʧ�ܣ�����Ϣ
    out.println("<h3>��ȡ������Ϣʧ�ܣ�</h3>");
} else if(array.size() < 1){
    //��������Ϊ�գ���ʾ���������ѱ�ɾ������
%>
<%
    out.println("<h3>�������ѱ�ɾ����</h3>");
} else {
    //��ȡ���ӱ���
    title = ((topic)array.get(0)).getTitle();
%>
<table   border="1px #76aef0 solid" cellspacing="0" cellpadding="10"  align=center width="90%">
    <tr>
        <th colspan="2" align="left" bgcolor="#76aef0">
            �������⣺<%=title%>
        </th>
    </tr>
<%
   for(int i = 0; i < array.size(); i++){
       //��ҳ�����г�������е������Լ���������
       topic ann = (topic)array.get(i);
%>
    <tr>
        <td  align="center" valign="top" width="16%"  bgColor=#E4E8EF>
            <img src="images/zayan.gif"><%=ann.getUsername()%><br>
            <img src="images/moon.gif" align=center>
        <br><%=ann.getPostdate()%>
<%
   if(user != null && user.getDegree() == user.superadmin) {
       //����ǳ�������Ա���ṩ��ɾ�������༭���������ݵĹ���
%>
        <br>
<a href="#" onclick="deleteTopic(<%=bbs_id%>, <%=forum_id%>, <%=ann.getId()%>);">ɾ��</a>
<a href="topic_update.jsp?forum_id=<%=forum_id%>&parent_id=<%=bbs_id%>&bbs_id=<%=ann.getId()%>">�༭</a>
<%}%>
	</td>
        <td  align="left" valign="top" width="84%"><%=StringUtil.getBRString(ann.getContent())%></td>
    </tr>
<%}%>
    <tr>
        <td  bgColor=#E4E8EF>���ٻظ���</td>
        <td   bgColor=#E4E8EF>
           <textarea name="content" rows=8 cols=80 align=left><%=replyContent%></textarea><br>
           <img src="images/newreply.gif" align=left onclick="bbsInsert(<%=forum_id%>, <%=bbs_id%>);"/>
        </td>
    </tr>
    <tr>
        <td colspan="2">
                <%=pageInfo.getFooter()%>
        </td>
    </tr>
</table>
</FORM>
<%}%>
</center>
</body>
</html>
