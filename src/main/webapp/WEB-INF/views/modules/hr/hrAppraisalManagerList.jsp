<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考核管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.summary').bind('click',function(){
				alert("123");
				var url=$(this).attr('data');
				var td=$(this).closest("tr").children("td").eq(5);
				var html='<a href="'+url+'">chengji</a>';
				td.html(html);
			});
			$(".detailView").click(function(){
				var url=$(this).attr('data')
				$.jBox.open("iframe:"+url,"detailView",800,$(top.document).height()-240,{
					buttons:{"close":true},bottomText:"",submit:function(v,h,f){
						return true;
					},loaded:function(h){
						$(".jbox-content",top.document).css("overflow-y","hidden");
					}
				});
			});
			
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
	<!--  <shiro:hasPermission name="hr:hrAppraisalManager:edit"><li><a href="#">考核管理view</a></li></shiro:hasPermission>-->	
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
					<fmt:formatDate value="${hrAppraisalManager.startTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${hrAppraisalManager.endTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
				
				</td>
				<td>
					 <c:if test="${hrAppraisalManager.status==0}">暂无</c:if>
					 <c:if test="${hrAppraisalManager.status==1}">完成</c:if>
					${hrAppraisalManager.status}
				</td>
				<td>
					 <c:if test="${hrAppraisalManager.status==0}">暂无</c:if>
					 <c:if test="${hrAppraisalManager.status==1}"><a href="${ctx}/hr/hrAppraisalManager/summary?id=${hrAppraisalManager.id}">成绩</a></c:if>
					 <a href="javascript:void(0)" data="${ctx}/hr/hrAppraisalManager/detailView?id=${hrAppraisalManager.id}" class="detailView">详情</a>
				</td>
				
				<td>
					<shiro:hasPermission name="hr:hrAppraisalManager:edit">
						<a href="javascript:void(0)" data="${ctx}/hr/hrAppraisalManager/summary?id=${hrAppraisalManager.id}" class="summary">汇总</a>
						<a href="javascript:void(0)" data="${ctx}/hr/hrAppraisalManager/summary?id=${hrAppraisalManager.id}" class="summary">详情</a>
					</shiro:hasPermission>
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>