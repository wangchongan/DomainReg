<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%
  response.setStatus(HttpServletResponse.SC_OK);
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目佬 - 对不起，您访问的页面不存在！</title>
</head>
<body>
对不起，您访问的页面不存在！请<a href="<%=path %>/index.jsp">点击访问本站主页</a>
</body>
</html>