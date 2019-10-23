<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报销记录管理</title>
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
		<li class="active"><a href="${ctx}/sys/expenseclaimrecords/">报销记录列表</a></li>
		<shiro:hasPermission name="sys:expenseclaimrecords:edit"><li><a href="${ctx}/sys/expenseclaimrecords/form">报销记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="expenseclaimrecords" action="${ctx}/sys/expenseclaimrecords/" method="post" class="breadcrumb form-search">
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
				<th>name</th>
				<th>update_date</th>
				<shiro:hasPermission name="sys:expenseclaimrecords:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="expenseclaimrecords">
			<tr>
				<td><a href="${ctx}/sys/expenseclaimrecords/form?id=${expenseclaimrecords.id}">
					${expenseclaimrecords.projectName}
				</a></td>
				<td><a href="${ctx}/sys/expenseclaimrecords/form?id=${expenseclaimrecords.id}">
					<fmt:formatDate value="${expenseclaimrecords.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				
				<td>
					<shiro:hasPermission name="sys:expenseclaimrecords:modifyView"><a href="${ctx}/sys/expenseclaimrecords/modify?id=${expenseclaimrecords.id}">修改</a></shiro:hasPermission>
				</td>
				
				<shiro:hasPermission name="sys:expenseclaimrecords:edit"><td>
    				<a href="${ctx}/sys/expenseclaimrecords/modify?id=${expenseclaimrecords.id}">修改</a>
					<a href="${ctx}/sys/expenseclaimrecords/delete?id=${expenseclaimrecords.id}" onclick="return confirmx('确认要删除该报销记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>