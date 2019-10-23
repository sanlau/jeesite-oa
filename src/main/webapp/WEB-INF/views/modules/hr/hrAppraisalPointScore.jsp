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
	</ul>
	<form:form id="searchForm" modelAttribute="hrAppraisalPoint" action="${ctx}/hr/hrAppraisalPoint/submit" method="post" class="breadcrumb form-search">
		<form:hidden path="id"/>
		<p class="form-title">
			绩效考核评分&nbsp;&nbsp;
			<a href="javascript:void(0);" onclick="close_page()">退出</a>
			<input type="submit" class="pull-right btn btn-primary btn-xs" value="提交">
		</p>
		<sys:message content="${message}"/>
		<div class="form-group">
			<div class="col-sm-12" style="text-align:center;font-size: 20px;margin-top: 15px;">
					${hrAppraisalPoint.name}
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-3 control-label">启动时间:${hrAppraisalPoint.startTime}</label>
				<label for="name" class="col-sm-3 control-label">截止时间:${hrAppraisalPoint.endTime}</label>
				<label class="control-label">考核对象：</label>
				<div class="controls">
					<select name="exameeid" class="input-mini">
					 <c:forEach items="${userList}" var="user">
				 	    <option value="${user.id}">
				  	   	${user.name}
				     	</option>
					 </c:forEach>
					</select>	
				</div>
			</div>
	 	<%--
	 	 <div class="control-group">
			<label class="control-label">考核对象模板：</label>
			<div class="controls">
				<select  class="input-mini">
				 <c:forEach items="${userList}" var="user">
				     <option value="${user.id}">
				     	${user.name}
				     	</option>
				 </c:forEach>
 					<form:options items="${userList}" itemLabel="name" itemValue="id" htmlEscape="false" />	
 						<span class="help-inline"><font color="red">*</font> </span>
					</select>
			</div>
		</div> 
		--%>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>考核内容</th>
				<th>评分细则</th>
				<th>标准分</th>
				<th>评分范围</th>
				<th>得分</th>
				<th>评语</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${hrAppraisalTemplateScoreList}" var="hrScoreList" varStatus="status">
			<tr>
				<td><a href="${ctx}/hr/hrAppraisalPoint/list">
				<input type="hidden" name="hrAppraisalPoints[${status.index}].appraisalManagerId" value="${hrAppraisalPoint.id}"/>
				<input type="hidden" name="hrAppraisalPoints[${status.index}].appraisalScoreId" value="${hrScoreList.id}"/>
				<input type="hidden" name="hrAppraisalPoints[${status.index}].examineeUserId" value=""/>
					${hrScoreList.name}
				</a></td>
				<td>
					${hrScoreList.description}
				</td>
				<td>
					${hrScoreList.standardScore}
				</td>
				<td>
					${hrScoreList.lowScope} ~~ ${hrScoreList.highScope}
				</td>
				<td><input type="text" name="hrAppraisalPoints[${status.index}].point" class="form-control col-sm-2" /></td>
				<td><textarea class="form-control" name=" "></textarea></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</form:form>
	
</body>
</html>