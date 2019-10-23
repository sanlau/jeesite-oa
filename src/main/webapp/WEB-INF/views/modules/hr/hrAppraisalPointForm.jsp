<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考核评分管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/hr/hrAppraisalPoint/">考核评分列表</a></li>
		<li class="active"><a href="${ctx}/hr/hrAppraisalPoint/form?id=${hrAppraisalPoint.id}">考核评分<shiro:hasPermission name="hr:hrAppraisalPoint:edit">${not empty hrAppraisalPoint.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="hr:hrAppraisalPoint:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="hrAppraisalPoint" action="${ctx}/hr/hrAppraisalPoint/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">被评分对象examinee_user_id：</label>
			<div class="controls">
				<form:input path="examineeUserId" htmlEscape="false" maxlength="5000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评分对象examiner_user_id：</label>
			<div class="controls">
				<form:input path="examinerUserId" htmlEscape="false" maxlength="5000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评分appraisal_score_id：</label>
			<div class="controls">
				<form:input path="appraisalScoreId" htmlEscape="false" maxlength="5000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">考核模板appraisal_manager_id：</label>
			<div class="controls">
				<form:input path="appraisalManagerId" htmlEscape="false" maxlength="5000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分数point：</label>
			<div class="controls">
				<form:input path="point" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评论comment：</label>
			<div class="controls">
				<form:input path="comment" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="hr:hrAppraisalPoint:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>