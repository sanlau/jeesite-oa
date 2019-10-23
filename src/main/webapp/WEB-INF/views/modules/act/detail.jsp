<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>待办任务</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<act:histoicFlow procInsId="${param.procInsId}"></act:histoicFlow>
</body>
</html>
