<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考核模板管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					///
					$(":hidden[name^='hrscore[0].id']").each(function(i,o){$(o).attr('name','hrscore['+i+'].id')}); 
					$(":text[name^='hrscore[0].name']").each(function(i,o){$(o).attr('name','hrscore['+i+'].name')}); 
					$(":text[name^='hrscore[0].standardScore']").each(function(i,o){$(o).attr('name','hrscore['+i+'].standardScore')}); 
					$(":text[name^='hrscore[0].lowScope']").each(function(i,o){$(o).attr('name','hrscore['+i+'].lowScope')}); 
					$(":text[name^='hrscore[0].highScope']").each(function(i,o){$(o).attr('name','hrscore['+i+'].highScope')}); 
					$(":text[name^='hrscore[0].description']").each(function(i,o){$(o).attr('name','hrscore['+i+'].description')}); 
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
		<li><a href="${ctx}/hr/hrAppraisalTemplate/">考核模板列表</a></li>
		<li class="active"><a href="${ctx}/hr/hrAppraisalTemplate/form?id=${hrAppraisalTemplate.appraisalTemplateId}">考核模板<shiro:hasPermission name="hr:hrAppraisalTemplate:edit">${not empty hrAppraisalTemplate.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="hr:hrAppraisalTemplate:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="hrAppraisalTemplate" action="${ctx}/hr/hrAppraisalTemplate/save" method="post" class="form-horizontal">
		<form:hidden path="appraisalTemplateId"/>
		<sys:message content="${message}"/>		
 
		<div class="control-group">
			<label class="control-label">name：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="1000" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">category_id：</label>
			<div class="controls">
			<form:select path="categoryId" class="input-mini">
 				<form:options items="${list}" itemLabel="name" itemValue="categoryId" htmlEscape="false" />
 				<span class="help-inline"><font color="red">*</font> </span>
			</form:select>
			</div>
		</div>
		<div class="control-group">  
			<label class="control-label">creator_user_id：</label>
			<div class="controls">
			<form:select path="creatorUserId" class="input-mini">
 				<form:options items="${rolelist}" itemLabel="name" itemValue="id" htmlEscape="false" />
 				<span class="help-inline"><font color="red">*</font> </span>
			</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">create_time：</label>
			<div class="controls">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${hrAppraisalTemplate.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">description：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="1000" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-group">
					<label class="col-sm-2 control-label"></label>
						<div class="form-actions">
							<shiro:hasPermission name="hr:hrAppraisalTemplate:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
							<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
						</div>
		</div>
		<p class="form-title">考核内容</p>
				<div class="form-group">
					<label for="insurance_type" class="col-sm-2 control-label">考核详细</label>
					<div class="col-sm-9" id="itembox">
						<table class="table table-bordered">
							<tr>
								<th>名称</th>
								<th>标准分</th>
								<th>最低评分</th>
								<th>最高评分</th>
								<th>评分细则</th>
								<td width="1%"><input type="button" id="additem" class="btn btn-primary btn-xs" value="+" /></td>
							</tr>
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
										<td width="1%"><input type="button" class="btn btn-primary btn-xs deleteitem" value="-" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>	
		
	</form:form>
	<script>
	/**
	 * 添加绩效考核评分细则
	**/
	$(function(){
		var num = 0;
		$('#additem').click(function(){
			var str = '<tr><td>';
			str += '<input type="text" name="hrscore['+num+'].name"/></td><td><input  type="text" name="hrscore['+num+'].standardScore"/></td><td><input type="text" name="hrscore['+num+'].lowScope" /></td><td><input type="text" name="hrscore['+num+'].highScope" /></td><td><input type="text"  name="hrscore['+num+'].description" /></td><td width="1%"><input type="button" class="btn btn-primary btn-xs deleteitem" value="-" /></td></tr>';
			$(this).parent().parent().parent().append(str);
		//	num++;
		});
		$('#itembox').on('click','.deleteitem',function(){
			$(this).parent().parent().remove();
			
			var val=$('input:hidden:eq(0)',$(this).parent().parent()).attr('value');
			$.get("${ctx}/hr/hrAppraisalTemplateScore/delete?id="+val, function(){
				
			});
		});
	});
</script>
</body>
</html>
