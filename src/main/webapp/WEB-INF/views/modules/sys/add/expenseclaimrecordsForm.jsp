<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报销记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			var procInsId='${expenseclaimrecords.act.procInsId}';
			if(procInsId)
				{
					$('#inputForm').attr('action','${ctx}/sys/expenseclaimrecords/addAudit');
				}
			else
				{
					$('#inputForm').attr('action','${ctx}/sys/expenseclaimrecords/addSave');
				}
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
		<li><a href="${ctx}/sys/expenseclaimrecords/">报销记录列表</a></li>
		<li class="active"><a href="${ctx}/sys/expenseclaimrecords/add?id=${expenseclaimrecords.id}">报销记录<shiro:hasPermission name="sys:expenseclaimrecords:edit">${not empty expenseclaimrecords.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:expenseclaimrecords:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="expenseclaimrecords" action="${ctx}/sys/expenseclaimrecords/addSave" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="act.taskId"/>
		<form:hidden path="act.taskName"/>
		<form:hidden path="act.taskDefKey"/>
		<form:hidden path="act.procInsId"/>
		<form:hidden path="act.procDefId"/>
		<form:hidden id="flag" path="act.flag"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">project_name：</label>
			<div class="controls">
				<form:input path="projectName" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">date：</label>
			<div class="controls">
				<input name="date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${expenseclaimrecords.date}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">total：</label>
			<div class="controls">
				<form:input path="total" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">remarks：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<c:if test="${not empty expenseclaimrecords.act.procInsId}">
			<div class="control-group">
				<label class="control-label">标注:</label>
				<div class="controls">
					<form:textarea path="act.comment" class="required" rows="5" maxlength="20" cssStyle="width:500px"/>
				</div>
			</div>
		</c:if>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交申请" onclick="$('#flag').val('yes')"/>&nbsp;
				<c:if test="${not empty expenseclaimrecords.act.procInsId}">
					<input id="btnSubmit2" class="btn btn-primary" type="submit" value="销毁申请" onclick="$('#flag').val('no')">&nbsp;
				</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<act:histoicFlow procInsId="${expenseclaimrecords.act.procInsId}"/>
	</form:form>
</body>
</html>