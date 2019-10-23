<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考核管理管理</title>
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
		<li class="active"><a href="${ctx}/hr/hrAppraisalManager/">考核管理列表</a></li>
		<shiro:hasPermission name="hr:hrAppraisalManager:edit"><li><a href="${ctx}/hr/hrAppraisalManager/form">考核管理添加</a></li></shiro:hasPermission>
		<shiro:hasPermission name="hr:hrAppraisalManager:edit"><li><a href="#">考核管理view</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="hrAppraisalManager" action="${ctx}/hr/hrAppraisalManager/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>name：</label>
				<form:input path="name" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>启用时间</th>
				<th>截止时间</th>
				<th>进度</th>
				<th>状态</th>
				<th>考核成绩</th>
				<shiro:hasPermission name="hr:hrAppraisalManager:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="hrAppraisalManager">
			<tr>
				<td><a href="${ctx}/hr/hrAppraisalManager/viewform?id=${hrAppraisalManager.id}">
					${hrAppraisalManager.name}
				</a></td>
				<td>
					${hrAppraisalManager.startTime}
				</td>
				<td>
					${hrAppraisalManager.endTime}
				</td>
				<td>
				
				</td>
				<td>
					${hrAppraisalManager.status}
				</td>
				<td>
					<a href="${ctx}/hr/hrAppraisalManager/form?id=${hrAppraisalManager.id}">成绩汇总</a>
				</td>
				<shiro:hasPermission name="hr:hrAppraisalManager:edit"><td>
    				<a href="${ctx}/hr/hrAppraisalManager/form?id=${hrAppraisalManager.id}">修改</a>
					<a href="${ctx}/hr/hrAppraisalManager/delete?id=${hrAppraisalManager.id}" onclick="return confirmx('确认要删除该考核管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>