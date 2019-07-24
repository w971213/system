 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%-- 
Word只需要把contentType="application/msexcel"改为contentType="application/msword" 
--%>
<%   
  //独立打开excel软件   
  response.setHeader("Content-disposition","attachment; filename=MyExcel.lsx");   
  
//嵌套在ie里打开excel   
  
//response.setHeader("Content-disposition","inline; filename=MyExcel.xlsx");   
  
//response.setHeader("Content-disposition","inline; filename=MyExcel.doc");   
%>  
<html>  
<head>  
<title>测试导出Excel和Word</title>  
</head>  
<body>  
			<table width="500" border="1" >  
				<tr>
                    <td>用户ID</td>
                    <td>用户名</td>
                    <td>昵称</td>
                    <td>部门</td>
                    <td>用户类型</td>
                    <td>用户状态</td>
                    <td>创建时间</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${downs }" var="user">
                	<tr>
                		<td class="userid">${user.user_id }</td>
                		<td class="userNickName">${user.nick_name }</td>
                		<td class="userCode">${user.user_code }</td>
                		<td class="userGroupName">${user.group_name }</td>
                		<td class="userRoleName">${user.role_name }</td>
                		<%-- ${user.user_state eq 1?"启用"："禁用" } --%>
                		<c:if test="${user.user_state eq 1 }">
                			<td class="userState"><b>启用</b></td>
                			<td class="userTime"><fmt:formatDate value="${user.create_time }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	                    <td>
	                    	<a class="glyphicon glyphicon-wrench show-userrole-form" aria-hidden="true" title="修改资料" href="javascript:void(0);" data-toggle="modal" data-target="#update-userrole-dialog"></a>
	                    	<a class="glyphicon glyphicon-remove delete-this-user" id="deleteuser" aria-hidden="true" title="删除用户" href="javascript:void(0);"></a>
	                   		<button type="button"  class="btn btn-warning ban-this-user"  >禁用</button>
	                   		<button type="button"  class="btn btn-primary update-this-pwd" >重置密码</button>
	                   		<button type="button"  class="btn btn-primary show-user-form" data-toggle="modal" data-target="#update-userrole">分配角色</button>
	                   		<button type="button"  class="btn btn-primary show-user-form" data-toggle="modal" data-target="#add-user-form">更改权限</button>
	                    </td>
                		</c:if>
                		<c:if test="${user.user_state eq 0 }">
                			<td class="userState" style="color:red" ><b>禁用</b></td>
                			<td class="userTime"><fmt:formatDate value="${user.create_time }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                			<td>
		               			<a class="glyphicon glyphicon-wrench show-userrole-form" aria-hidden="true" title="修改资料" href="javascript:void(0);" data-toggle="modal" data-target="#update-userrole-dialog"></a>
		                    	<a class="glyphicon glyphicon-remove delete-this-user" id="deleteuser" aria-hidden="true" title="删除用户" href="javascript:void(0);"></a>
		                   		<button type="button"  class="btn btn-warning ban-this-user" >启用</button>
                			</td>
                		</c:if>
                	</tr>
                </c:forEach>
</table>  
</body>  
</html>  
