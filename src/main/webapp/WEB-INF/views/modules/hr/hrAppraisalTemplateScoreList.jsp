<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模板评分管理</title>
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
		<li class="active"><a href="${ctx}/hr/hrAppraisalTemplateScore/">模板评分列表</a></li>
		<shiro:hasPermission name="hr:hrAppraisalTemplateScore:edit"><li><a href="${ctx}/hr/hrAppraisalTemplateScore/form">模板评分添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="hrAppraisalTemplateScore" action="${ctx}/hr/hrAppraisalTemplateScore/" method="post" class="breadcrumb form-search">
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
				<th>name</th>
				<shiro:hasPermission name="hr:hrAppraisalTemplateScore:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="hrAppraisalTemplateScore">
			<tr>
				<td><a href="${ctx}/hr/hrAppraisalTemplateScore/form?id=${hrAppraisalTemplateScore.id}">
					${hrAppraisalTemplateScore.name}
				</a></td>
				<shiro:hasPermission name="hr:hrAppraisalTemplateScore:edit"><td>
    				<a href="${ctx}/hr/hrAppraisalTemplateScore/form?id=${hrAppraisalTemplateScore.id}">修改</a>
					<a href="${ctx}/hr/hrAppraisalTemplateScore/delete?id=${hrAppraisalTemplateScore.id}" onclick="return confirmx('确认要删除该模板评分吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>