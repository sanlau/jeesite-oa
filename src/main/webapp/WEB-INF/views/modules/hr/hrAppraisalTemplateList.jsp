<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考核模板管理</title>
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
		<li class="active"><a href="${ctx}/hr/hrAppraisalTemplate/">考核模板列表</a></li>
		<shiro:hasPermission name="hr:hrAppraisalTemplate:edit"><li><a href="${ctx}/hr/hrAppraisalTemplate/form">考核模板添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="hrAppraisalTemplate" action="${ctx}/hr/hrAppraisalTemplate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>name：</label>
				<form:input path="name" htmlEscape="false" maxlength="1000" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>考核模板名称</th>
				<th>启用时间</th>
				<th>创建人</th>
				<th>考核模板类型</th>
				<th>描述</th>
				<shiro:hasPermission name="hr:hrAppraisalTemplate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="hrAppraisalTemplate">
			<tr>
				<td><a href="${ctx}/hr/hrAppraisalTemplate/form?id=${hrAppraisalTemplate.appraisalTemplateId}">
					${hrAppraisalTemplate.name}
				</a></td>
				<td>
					<fmt:formatDate value="${hrAppraisalTemplate.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${hrAppraisalTemplate.sysRoleName}
				</td>
				<td>
					${hrAppraisalTemplate.cateName}
				</td>
				<td>
					${hrAppraisalTemplate.description}
				</td>
				<shiro:hasPermission name="hr:hrAppraisalTemplate:edit"><td>
    				<a href="${ctx}/hr/hrAppraisalTemplate/form?id=${hrAppraisalTemplate.appraisalTemplateId}">修改</a>
					<a href="${ctx}/hr/hrAppraisalTemplate/delete?id=${hrAppraisalTemplate.appraisalTemplateId}" onclick="return confirmx('确认要删除该考核模板吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>