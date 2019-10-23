<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>绩效考核详情</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".detailpoint").click(function(){
				var url=$(this).attr('data')
				$.jBox.open("iframe:"+url,"detailPoint",800,$(top.document).height()-240,{
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
		<li class="active"><a href="${ctx}/hr/hrAppraisalManager/">绩效考核详情</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="hrAppraisalManager" action="${ctx}/hr/hrAppraisalManager/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>考核对象</th>
				<th>岗位</th>
				<th>考核表</th>
				<th>分数</th>
				<shiro:hasPermission name="hr:hrAppraisalManager:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${hrAppraisalAvgPointList}" var="hrAvgPointList">
			<tr>
				<td>${hrAvgPointList.examineeUserName}</td>
				<td>
					 
				</td>
				<td>
					 ${hrAvgPointList.appraisalManagerName}
				</td>
				 <td>
					${hrAvgPointList.avgPoint}
				</td>
				<td>
					<shiro:hasPermission name="hr:hrAppraisalManager:view">
						<a href="javascript:void(0)" data="${ctx}/hr/hrAppraisalManager/detailpoint?id=${hrAppraisalManager.id}&examineeUserId=${hrAvgPointList.examineeUserId}" class="detailpoint">详细</a>
						<!--  <a href="${ctx}/hr/hrAppraisalManager/detailpoint?id=${hrAppraisalManager.id}&examineeUserId=${hrAvgPointList.examineeUserId}">详细</a>-->
					</shiro:hasPermission>
				</td>
				 
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>