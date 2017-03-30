<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<frameset rows="80px,*,20px" border="0" frameborder="0" framespacing="0" marginwidth="0" marginheight="0">
<frame name="header" src="<%=path%>/jsp/header.jsp" scrolling="no">
<frameset cols="200px,*" border="0" frameborder="0" framespacing="0">
<frame name="menu" src="<%=path%>/jsp/menu.jsp" scrolling="no">
<frame name="home" src="<%=path%>/jsp/home.jsp" scrolling="auto">
</frameset>
</frameset>
<noframes>
<body>
</body>
</noframes>

</html>