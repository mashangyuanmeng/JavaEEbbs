<%@ page contentType="text/html; charset=utf-8"%>
<%
    String desc = (String)request.getParameter("desc");
    String error = (String)request.getAttribute(desc);
    if(error != null){
%>
        <font color=red>
        <link rel=stylesheet href="include/main.css">	
            <p><h1> 出错啦！！！！请联系管理员：易敏</h1>
            <p><h2>1306410106</h2>
            <%=error%></font>
<br>
<br>
<%
    }
%>