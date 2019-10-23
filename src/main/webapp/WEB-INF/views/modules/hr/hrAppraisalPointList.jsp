<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考核评分管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/hr/hrAppraisalPoint/">考核评分列表</a></li>
		<shiro:hasPermission name="hr:hrAppraisalPoint:edit"><li><a href="${ctx}/hr/hrAppraisalPoint/form">考核评分添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="hrAppraisalPoint" action="${ctx}/hr/hrAppraisalPoint/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>考核名称</th>
				<th>考核类型</th>
				<th>启用时间</th>
				<th>截止时间</th>
				<th>状态</th>
				<th>待评分人数</th>
				<th>评分操作</th>
				<shiro:hasPermission name="hr:hrAppraisalPoint:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${hrAppraisalManagerList}" var="hrList">
			<tr>
				<td><a href="${ctx}/hr/hrAppraisalPoint/list">
					${hrList.name}
				</a></td>
				<td>
					${hrList.templateName}
				</td>
				<td>
					<fmt:formatDate value="${hrList.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${hrList.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${hrList.status}
				</td>
				<td>
					${hrList.examineeNum}
				</td>
				<td>
					<a href="${ctx}/hr/hrAppraisalPoint/score?id=${hrList.id}">评分</a>
				</td>
				
				<shiro:hasPermission name="hr:hrAppraisalPoint:edit"><td>
    				<a href="${ctx}/hr/hrAppraisalPoint/form?id=${hrAppraisalPoint.id}">修改</a>
					<a href="${ctx}/hr/hrAppraisalPoint/delete?id=${hrAppraisalPoint.id}" onclick="return confirmx('确认要删除该考核评分吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>