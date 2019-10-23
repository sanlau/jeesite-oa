<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考核管理view</title>
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
		<li><a href="${ctx}/hr/hrAppraisalManager/">考核管理view</a></li>
		<li class="active"><a href="${ctx}/hr/hrAppraisalManager/viewform?id=${hrAppraisalManager.id}">考核管理<shiro:hasPermission name="hr:hrAppraisalManager:edit">${not empty hrAppraisalManager.id?'查看':'添加'}</shiro:hasPermission><shiro:lacksPermission name="hr:hrAppraisalManager:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<p class="form-title">考核详情</p>
	<form:form id="inputForm" modelAttribute="hrAppraisalManager" action="${ctx}/hr/hrAppraisalManager/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">name：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">start_time：</label>
			<div class="controls">
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${hrAppraisalManager.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">end_time：</label>
			<div class="controls">
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${hrAppraisalManager.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">status：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">负责人：</label>
			<div class="controls">
				<%-- <form:input path="executorId" htmlEscape="false" maxlength="64" class="input-xlarge "/> --%>
				<sys:treeselect id="executorId" name="executorId" value="${user.role.id}" labelName="executorId.name" labelValue="${user.role.name}"
					title="角色" url="/sys/role/treeData?type=1" cssClass="required" allowClear="true" notAllowSelectParent="true" checked="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">考核模板：</label>
			<div class="controls">
				<%-- <form:input path="appraisalTemplateId" htmlEscape="false" maxlength="64" class="input-xlarge "/> --%>
				<form:select path="appraisalTemplateId" class="input-mini">
 					<form:options items="${list}" itemLabel="name" itemValue="appraisalTemplateId" htmlEscape="false" />	
 						<span class="help-inline"><font color="red">*</font> </span>
					</form:select>
			</div>
		</div>
		
		<p class="form-title">
				考核对象
			</p>
			<div class="form-group">
				<div class="col-sm-9" id="itembox">
					<table class="table table-bordered">
						<tr>
							<td>评分人员</td>
							<td>考核对象</td>
						</tr>
						<tr>
							<td>${hrAppraisalManager.examinerUserIdName}</td>
							<td>${hrAppraisalManager.examineeUserIdName}</td>
						</tr>
					</table>
				</div>
			</div>
			<p class="form-title">
				考核详细
			</p>
			<div class="form-group">
				<div class="col-sm-9" id="itembox">
					<table class="table table-bordered">
						<tr>
							<td>名称</td>
							<td>标准分</td>
							<td>评分最低分</td>
							<td>评分最高分</td>
							<td>评分细则</td>
						</tr>
						
						<!-- 
						<volist name="appraisalmanager['template']['score']" id="vo">
						<tr>
							<td>{$vo.name}</td>
							<td>{$vo.standard_score}</td>
							<td>{$vo.low_scope}&nbsp;至&nbsp;{$vo.high_scope}</td>
							<td>{$vo.description}</td>
						</tr>
						</volist>
						 -->
						<tbody>
								<c:forEach items="${scoreList}" var="score" >
									<tr>
										<td><input type="hidden" name="hrscore[0].id" value="${score.id}"/>
											<input type="text" name="hrscore[0].name" value="${score.name}"/>
										</td>
										<td>
											<input type="text" name="hrscore[0].standardScore" value="${score.standardScore}"/>
										</td>
										<td>
											<input type="text" name="hrscore[0].lowScope" value="${score.lowScope}"/>
										</td>
										<td>
											<input type="text" name="hrscore[0].highScope" value="${score.highScope}"/>
										</td>
										<td>	
											<input type="text" name="hrscore[0].description" value="${score.description}"/>
										</td>
									</tr>
								</c:forEach>
							</tbody>
							
							
					</table>
				</div>
			</div>
		</div>
		
		
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	
	<script type="text/javascript">
	/**
	 * 选择模板
	 * 
	 **/
	$('#dialog_template_name').click(function(){
		$('#alert').modal({
			show:true,
			remote:'{:U("hrm/appraisaltemplate/templateListDialog")}'		
 			
 		});
	});
	</script>
	
</body>
</html>