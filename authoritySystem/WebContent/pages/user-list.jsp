 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>用户管理 - 用户列表</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/static/css/layout.css" rel="stylesheet">
    <style type="text/css">
   	.red{color:red}
   </style>
  </head>
  <body>
    <!-- 头部 -->
    <jsp:include page="header.jsp"/>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <jsp:include page="navibar.jsp"/>
        </div>
     </div>
     <!--处理用户的信息 -->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h3 class="page-header">用户列表</h3>
          <div class="row placeholders">
         	<div>
                <button type="button" style="margin-left: 100px;" id="download" class="btn btn-warning delete-query"  >导出数据</button>
                <button type="button" class="btn btn-primary clean-info" data-toggle="modal" data-target="#add-user-form">添加新用户</button>
               	<input type="text" id="userCode" style="width:105px;margin-left: 340px;" name="nickName"  placeholder="用户名" value="${Params.userCode }">
               	<select id="userType" style="width:105px;height:26px;" >
					<option value="" style="display:none">用户类型</option>
					<option value=""  ${Params.userType eq "" ?"selected='selected'":""}>全部</option>
				<c:forEach  items="${userType}" var="role">
					<option value="${role.roleId }" ${Params.userType eq role.roleId  ?"selected='selected'":""}>${role.roleName }</option>
				</c:forEach>
				</select>
	            <select id="state" style="width:105px;height:26px;">
              		<option value="" style="display:none">用户状态</option>
              		<option value="" ${ empty Params.userState ?"selected='selected'":""}>全部</option>
              		<option value="1" ${Params.userState eq 1 ?"selected='selected'":""}>启用</option>
              		<option value="0" ${not empty Params.userState and Params.userState eq 0 ?"selected='selected'":""}>禁用</option>
	            </select>
               <button id="check" type="button" class="btn btn-warning delete-query" >查询</button>
               
                <!--添加新用户表单-->
                <div class="modal fade " id="add-user-form" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                  <div class="modal-dialog modal-sm" role="document">
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" >添加新用户</h4>
                      </div>
                      <div class="modal-body">
                      	<form action="${pageContext.request.contextPath }/user/addUserInfo.action" method="post" class="user-form"  onsubmit="return login()">
                          <div class="form-group">
                            <label for="userNameInput">用户名</label>
                            <input type="text" id="userNameTest" name="userCode"  class="form-control clean"  placeholder="用户名" />
                          	<label id="userCodeError" class="error"></label>
                          </div>
                          <div class="form-group">
                            <label for="userNameInput">昵称</label>
                            <input type="text" name="nickName"  class="form-control clean" id="userNickInput" placeholder="昵称" />
                          	<label id="userNickError" class="error"></label>
                          </div>
                           <label for="userNameInput">部门</label>
                          <select id="groupInput" name="groupId"  class="form-control" >
								<option style="display:none">部门</option>
								<c:forEach  items="${Group}" var="group">
									<option class="group" value="${group.groupId }">${group.groupName }</option>
								</c:forEach>
						  </select>
						  <label id="inputGroupError" class="error"></label>
						  <br/>
                          <div class="form-group">
                            <label for="passwordInput">密码</label>
                            <input type="password" name="userPwd" class="form-control clean" id="userPassword" placeholder="密码"/>
                          	<label id="passwordError" class="error"></label>
                          </div>
                          <div class="form-group">
                            <label for="passwordInput">确认密码</label>
                            <input type="password" id="userPassword1"  class="form-control clean"  placeholder="密码" />
                          	<label id="passwordError1" class="error"></label>
                          </div>
                          <div class="modal-footer">
	                        <input type="reset"  class="btn btn-default" data-dismiss="modal"/>
	                        <input type="submit" class="btn btn-primary" />
	                      </div>
                         </form>
                     </div>
                    </div>
                  </div>
                </div>
            <!-- 展示用户信息 --> 
            </div>
            <div class="space-div"></div>
            <table  class="table table-hover table-bordered user-list">
            	<tr>
                	<td>序号</td>
                    <td>用户ID</td>
                    <td>用户名</td>
                    <td>昵称</td>
                    <td>部门</td>
                    <td>用户类型</td>
                    <td>用户状态</td>
                    <td>创建时间</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${page.resultList }"  var="user">
                	<tr>
                		<td><input type="checkbox" /></td>
                		<td class="userid">${user.user_id }</td>
                		<td class="userCode">${user.user_code }</td>
                		<td class="userNickName">${user.nick_name }</td>
                		<td class="userGroupName" >${user.group_name }</td>
                		<td class="userRoleName">${user.role_name }</td>
                		<c:if test="${user.user_state eq 1 }">
                			<td class="userState"><b>启用</b></td>
                			<td class="userTime"><fmt:formatDate value="${user.create_time }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	                    <td>
	                    	<a class="glyphicon glyphicon-wrench"  id="show-user-info" aria-hidden="true" title="修改资料" href="javascript:void(0);" data-toggle="modal" data-target="#update-userInfo"></a>
	                    	<a class="glyphicon glyphicon-remove delete-this-user" id="deleteuser" aria-hidden="true" title="删除用户" href="javascript:void(0);"></a>
	                   		<button type="button"  class="btn btn-warning ban-this-user" >禁用</button>
	                   		<button type="button"  class="btn btn-primary update-this-pwd" >重置密码</button>
	                   		<button type="button"  class="btn btn-primary update-user-role" data-toggle="modal" data-target="#update-userrole">分配角色</button>
	                   		<button type="button"  class="btn btn-primary show-user-form">更改权限</button>
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
              <jsp:include page="standard.jsp"/>
              <!-- 更改角色 -->
              <div class="modal fade " id="update-userrole" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                  <div class="modal-dialog modal-sm" role="document">
                    <div class="modal-content">
                      <div class="modal-header" >
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" >分配角色</h4>
                      </div>
                      <form  >
	                      <div class="form-group">
	                       <label >用户ID</label>
	                       <input type="text" name="updateRoleUserId"  class="form-control" readonly >
	                      </div>
                     	 <div class="form-group">
                            <label for="userNameInput">用户名</label>
                            <input type="text"  name="updateRoleUserCode"  readonly class="form-control"  placeholder="用户名">
                          </div>
                          <div id="add-element">
	                          <label for="userNameInput">角色</label><label id="roleError"></label>
	                          <br/>
                          </div>
	                      <div class="modal-footer">
	                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	                        <button type="button" id="comfirm-update-roles" class="btn btn-primary ">提交 </button>
	                      </div>
	                   </form>
                    </div>
                  </div>
            </div>
          </div>          
         </div>
      </div>
       <!--修改用户表单-->
       <div class="modal fade " id="update-userInfo" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
             <div class="modal-dialog modal-sm" role="document">
               <div class="modal-content">
                 <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                   <h4 class="modal-title" >修改资料</h4>
                 </div>
                 <form >
                 	<div class="form-group">
                       <label >用户ID<span style="color:red">(不可修改)</span></label>
                       <input type="text" name="updateUserId"  class="form-control" id="updateUserId" readonly >
                     </div>
                     
                	 <div class="form-group">
                       <label >用户名<span style="color:red">(不可修改)</span></label>
                       <input type="text" name="updateUserCode" class="form-control" id="updateUserCode" readonly >
                     </div>
                     <div class="form-group">
                       <label>昵称</label>
                       <input type="text" name="updateUserNick" class="form-control" id="updateUserNick" placeholder="昵称">
                     </div>
                      <div class="form-group">
                     <label>部门</label>
	                     <select id="groupId"  class="form-control" >
							<c:forEach  items="${Group}" var="group">
								<option class="groupName" value="${group.groupId   }" >${group.groupName }</option>
							</c:forEach>
						 </select>
						 <label id="error"></label>
						 <br/>
					</div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" id="comfirm-userInfo" class="btn btn-primary ">提交 </button>
                  </div>
               </form>
               </div>
             </div>
       </div>
         
    <!-- 提示框 -->
	<div class="modal fade" id="op-tips-dialog" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
      <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
        	<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >提示信息</h4>
          </div>
          <div class="modal-body" id="op-tips-content">
          </div>
        </div>
      </div>
    </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script>
	    /* 清空添加用户的表单 */
	    $(".clean-info").click(function(){
	    	$('#add-user-form .clean').val("");
	    	$('#add-user-form .error').text("");
	    })
	    /* 权限管理页面跳转 */
	    $(".show-user-form").click(function(){
	    	var userid=$(this).parents("tr").find(".userid").html();
	    	location.href="${pageContext.request.contextPath }/user/selectUserAuth.action?userId="+userid;
		});
	    $(document).ready(function(){
	    	getAllRoles($("#update-userrole #add-element")); //获取所有当前角色
	    });
	    //重置密码
	    $(".user-list").on("click",".update-this-pwd",function(){
	    	var userid=$(this).parents("tr").find(".userid").html();
	    	if(confirm('确认重置密码？')){
				location.href="${pageContext.request.contextPath }/user/updateUserPwd.action?userId="+userid;
			}
	    });
  	//修改资料时将值传到form表单中
	  $(".user-list").on("click","#show-user-info",function(){
			var userid=$.trim($(this).parents("tr").find(".userid").text());
			var usercode=$(this).parents("tr").find(".userCode").text();
			var userNike=$(this).parents("tr").find(".userNickName").text();
			$("#update-userInfo input[name='updateUserId']").val(userid);
			$("#update-userInfo input[name='updateUserCode']").val(usercode);
			$("#update-userInfo input[name='updateUserNick']").val(userNike);
			$.ajax({
				url:"${pageContext.request.contextPath }/user/selectUserInfoById.action",
				data:{
					userId:userid
				},
				dataType:"json",
				type:"POST",
				success:function(data){
					/* console.log(data)
					console.log("返回数据成功")
					console.log(data.UserInfo.userId)
					console.log(data.UserInfo.groupId) */
					//console.log($("#add-user-form  option[value='"+data.UserInfo.groupId+"']").val())
					/* $("#add-user-form option[value='"+data.UserInfo.groupId+"']").attr("selected",true); */
					/* $("#add-user-form").find("option[value='"+data.UserInfo.groupId+"']").attr("selected", true); */
					/* $("#add-user-form").val(data.UserInfo.groupId); */
					$("#groupId").find("option[value='"+data.UserInfo.groupId+"']").attr("selected", true);
				}
			});
	  });
    //修改资料
    $("#comfirm-userInfo").click(function(){
    	var nickName=$("#updateUserNick").val();
		var userId=$("#updateUserId").val();
		var groupId=$("#groupId").val();
    	location.href="${pageContext.request.contextPath }/user/updateUserInfo.action?userId="+parseInt(userId)+"&groupId="+groupId+"&nickName="+nickName;
    });
    
    //禁用或者启用
    $(".user-list").on("click",".ban-this-user",function(){
			var userid=$(this).parents("tr").find(".userid").html();
			var userState=$(this).parents("tr").find(".userState").text();
			if(userState == "启用"){
				if(confirm('确认禁用ID为"'+userid+'"的用户吗？')){
					location.href="${pageContext.request.contextPath }/user/updateUserState.action?userId="+userid+"&userState="+userState;
				}
			}else if(userState=="禁用"){
				if(confirm('确认启用ID为"'+userid+'"的用户吗？')){
					location.href="${pageContext.request.contextPath }/user/updateUserState.action?userId="+userid+"&userState="+userState;
				}
		   }
	});
		
    //分配角色
     $(".user-list").on("click",".update-user-role",function(){
		var userid=$.trim($(this).parents("tr").find(".userid").text());
		var userCode=$(this).parents("tr").find(".userCode").text();
		$("#update-userrole input[name='updateRoleUserCode']").val(userCode);
		$("#update-userrole input[name='updateRoleUserId']").val(userid);
		getRolesByUserId(userid);//回显当前用户的角色
	});
   //查询当前用户的角色
   	 var oldData=new Array();
     function getRolesByUserId(userid){
		 		$.ajax({
		 			url:"${pageContext.request.contextPath }/user/updateUserRole1.action?userId="+userid,
		 			type:"POST",
		 			data:{
		 				userId:userid
		 			},
		 			dataType:"json",
		 			success:function(data){
		 				$('#update-userrole input[name=roleIds]').prop("checked",false);
		 				if(data[0]!=null){
		 					for(var i in data[0].role){
		 						$("#roleError").text("")
		 						oldData[i]=data[0].role[i].roleId
			 					$('#update-userrole input[name=roleIds][value="'+data[0].role[i].roleId+'"]').prop("checked",true);
			 				}
		 				}else{
		 					$("#roleError").addClass("red").text(" （该用户没有角色）")
		 				}
		 			},
		 			error:function(){
		 				console.log("服务器异常");
		 			}
		 		});
		 	}
     //全查角色方法
	function getAllRoles(obj){
		$.ajax({
			url:"${pageContext.request.contextPath }/user/updateUserRole.action",
			type:"POST",
			dataType:"json",
			success:function(data){
				for(var i in data){
					obj.append("<input type='checkbox' name=roleIds value='"+data[i].roleId+"'/>"+data[i].roleName);
					obj.append("<br/>");
				}
			},
			error:function(data){
				console.log("服务器异常");
			}
		});
	}
    //点击设置角色提交 跳转后台
  	$("#comfirm-update-roles").click(function(){ 
	 	var userId= $("#update-userrole input[name='updateRoleUserId']").val();
		 var cbs=$("input[name='roleIds']:checked")
			 if(cbs.length===0){
				 $("#roleError").addClass("red").text(" （请为该用户设置角色）") 
			}else{
				$("#roleError").text("");
				var roleIds=new Array();
				$.each(cbs,function(i,cb){
					roleIds[i]=cb.value;
				});
				if(roleIds.toString()==oldData.toString){
					 $("#roleError").addClass("red").text(" （前后角色一致）") 
					 return;
				}
				location.href="${pageContext.request.contextPath}/user/updateUserRole2.action?userId="+userId+"&roleIds="+roleIds;
		 }
 	});   
  //模糊查点击跳转
  $("#check").click(function(){
  	var userCode=$("#userCode").val()==""?"":$("#userCode").val()
  	var userType=$("#userType").val();
  	var userState=$("#state").val();
  	location.href="${pageContext.request.contextPath}/user/list.action?userCode="+userCode+"&userType="+userType+"&userState="+userState; 
  });
  //下载点击跳转
  $("#download").click(function(){
		location.href="../pages/user-list-download.jsp" 
  });
		//添加用户
		//1.检测用户名可用  格式+非空+是否可用
		var userCode1=false;
		$("#userNameTest").blur(function(){
			var nameCode=$(this).val();
			if(nameCode==''){
				userCode1=false;
				return
			}else{
				if(!/^\w{4,16}$/.test(nameCode)){
					userCode1=false;
					$("#userCodeError").text("请输入 4-16位，字母，数字，下划线").css("color","red");
					return;
				}else{
					$.ajax({
						type:"POST",
						url:"${pageContext.request.contextPath }/user/validCodeUserCode.action",
						data:{
							nameCode:nameCode	
						},
						dataType:"json",
						success:function(data){
							if(data.msg){
								userCode1=true;
								$("#userCodeError").text("用户名可用！").css("color","green")
							}else{
								userCode1=false;
								$("#userCodeError").text("用户名已被用！").css("color","red")
							}
						},
						error:function(){
							console.log("服务器异常")
						}
					});
				}
			}
		});
		//验证两个密码是否正确 格式+非空
		var userPsw=false;
		$("#userPassword").blur(function(){
			$("#passwordError").text("")
			var psw=$("#userPassword").val();
			if(psw==""){
				userPsw=false;
				return;	
			}
			if(psw.length>16 || psw.length<6){
				userPsw=false;
				$("#passwordError").addClass("red").text("请输入 6-16位密码：字母/数字")
			}else{
				userPsw=true;
			}
		})
		//验证第二个密码 格式+非空+密码一致
		var userPsw1=false;
		$("#userPassword1").blur(function(){
			$("#passwordError1").text("")
			var psw1=$("#userPassword1").val();
			var psw=$("#userPassword").val();
			if(psw1==""){
				userPsw1=false;
				return;	
			}
			if(psw1.length>16 || psw1.length<6){
				userPsw1=false;
				$("#passwordError1").addClass("red").text("请输入 6-16位密码：字母/数字")
			}else if(psw!=psw1){
				userPsw1=false;
				$("#passwordError1").addClass("red").text("两次密码不一致！")
			}else{
				userPsw1=true;
			}
		})
		//昵称验证   非空+格式
		
		$("#userNickInput").blur(function(){
			$("#userNickError").text("");
			var userNick=$("#userNickInput").val();
			if(userNick==""){
				userNick=false;
				return
			}else if(!/^\w{4,16}$/.test($("#userNickInput").val())){
				$("#userNickError").addClass("red").text("请输入 4-16位字母/数字/下划线");
				userNick=false;
			}else{
				userNick = true;
			}
		});
 		//2.判断账号密码
		function login(){
			$("#userNickError").text("");
		    $("#inputGroupError").text("");
		    var userNick=$("#userNickInput").val();
			 if(userCode1&&userPsw&&userPsw1){
					if(userNick==""){
						$("#userNickError").addClass("red").text("请输入昵称");
						return false;
					}else if(!/^\w{4,16}$/.test($("#userNickInput").val())){
						$("#userNickError").addClass("red").text("请输入 4-16位字母/数字/下划线");
						return false;
					}else{
						if($("#groupInput").val()!="部门"){
							return true;
						}else{
							$("#inputGroupError").addClass("red").text("请输入部门")
							return false; 
						}
					}
			} else{
				alert("请正确填写")
				return false; 
			} 
			 
		} 
		//删除
		$(".user-list").on("click",".delete-this-user",function(){
			var userTr=$(this).parents("tr");
			var userid=userTr.find(".userid").html();
			if(confirm('确认删除ID为"'+userid+'"的用户吗？')){
				location.href="${pageContext.request.contextPath }/user/deleteUser.action?userId="+userid;
			}
		});
    </script>
  </body>
</html>
