<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模版类型管理</title>
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
		<li class="active"><a href="${ctx}/hr/hrAppraisalTemplateCategory1/">模版类型列表</a></li>
		<shiro:hasPermission name="hr:hrAppraisalTemplateCategory1:edit"><li><a href="${ctx}/hr/hrAppraisalTemplateCategory1/form">模版类型添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="hrAppraisalTemplateCategory1" action="${ctx}/hr/hrAppraisalTemplateCategory1/" method="post" class="breadcrumb form-search">
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
				<th>模本类型名称</th>
				<th>描述</th>
				<shiro:hasPermission name="hr:hrAppraisalTemplateCategory1:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="hrAppraisalTemplateCategory1">
			<tr>
				<td><a href="${ctx}/hr/hrAppraisalTemplateCategory1/form?id=${hrAppraisalTemplateCategory1.id}">
					${hrAppraisalTemplateCategory1.name}
				</a></td>
				<td>
					${hrAppraisalTemplateCategory1.description}
				</td>
				<shiro:hasPermission name="hr:hrAppraisalTemplateCategory1:edit"><td>
    				<a href="${ctx}/hr/hrAppraisalTemplateCategory1/form?id=${hrAppraisalTemplateCategory1.categoryId}">修改</a>
					<a href="${ctx}/hr/hrAppraisalTemplateCategory1/delete?id=${hrAppraisalTemplateCategory1.categoryId}" onclick="return confirmx('确认要删除该模版类型吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>