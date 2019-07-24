<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>用户管理 - 角色列表</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/static/css/layout.css" rel="stylesheet">
  </head>

  <body>

    <!-- 头部 -->
    <jsp:include page="header.jsp"/>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <jsp:include page="navibar.jsp"/>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h3 class="page-header">角色列表</h3>
          <div class="row placeholders">
            	<button type="button" style="margin-left: 100px;" class="btn  btn-primary  show-add-form" data-toggle="modal" data-target="#role-form-div">添加新角色</button>
              	<input type="text" id="roleName1" style="width:105px;margin-left: 500px;" name="roleName1"  placeholder="角色名" value="${Params.roleName }">
	            <select id="roleState1" style="width:105px;height:26px;">
              		<option value="" style="display:none" >角色状态</option>
              		<option value=""  ${ empty Params.roleState ?"selected='selected'":""}>全部</option>
              		<option value="1" ${Params.roleState eq 1 ?"selected='selected'":""}>启用</option>
              		<option value="0" ${not empty Params.roleState and Params.roleState eq 0 ?"selected='selected'":""}>禁用</option>
	            </select>
               <button id="check" type="button" class="btn btn-warning" >查询</button>
                <!--添加新角色表单-->
                <div class="modal fade " id="role-form-div" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                  <div class="modal-dialog modal-md" role="document">
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="role-form-title" ></h4>
                      </div>
                      <div class="modal-body">
                      	<form class="role-form" >
                          <input type="hidden" name="roleId" class="form-control" id="roleIdInput">
                          <div class="form-group">
                            <label for="roleNameInput">名称</label>
                            <input type="text" name="roleName" class="form-control" id="roleNameInput" placeholder="角色名">
                         	<label id="roleNameError" class="clean"></label>
                          </div>
                          <div class="form-group">
                            <label for="descInput">描述</label>
                            <input type="text" name="roleDesc" class="form-control" id="descInput" placeholder="角色描述">
                          </div>
                          <div class="form-group" id="code">
                            <label for="codeInput">代码</label>
                            <input type="text" name="roleCode" class="form-control" id="codeInput" placeholder="角色代码">
                          	<label id="roleCodeError" class="clean"></label>
                          </div>
                        </form>
                        <div class="modal-footer">
	                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	                        <button type="button"  class="btn btn-primary role-submit"></button>
	                      </div>
                      </div>
                    </div>
                  </div>
                </div>
            
           
            <div class="space-div"></div>
            <table class="table table-hover table-bordered role-list" >
            	<tr>
                    <td>ID</td>
                    <td>用户名</td>
                    <td>角色描述</td>
                    <td>角色代码</td>
                    <td>创建人</td>
                    <td>状态</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${roles }" var="role">
	                <tr>
	                    <td class="roleid">${role.roleId }</td>
	                    <td class="roleName">${role.roleName }</td>
	                    <td class="roleDesc">${role.roleDesc }</td>
	                    <td class="roleCode">${role.roleCode }</td>
	                    <td> ${role.userInfo.userCode }</td>
	                    <c:if test="${role.roleState eq 1 }">
	                    <td class="roleState"><b>启用</b></td>
	                    <td><a class="glyphicon glyphicon-pencil show-roleinfo-form" aria-hidden="true" title="修改角色信息" href="javascript:void(0);" data-toggle="modal" data-target="#role-form-div"></a>
	                    	<a class="glyphicon glyphicon-remove delete-this-role" aria-hidden="true" title="删除角色" href="javascript:void(0);"></a>
	                    	<button type="button"  class="btn btn-warning ban-this-role" >禁用</button>
	                		<button type="button"  class="btn btn-primary show-role-auth">更改权限</button>
	                    </td>
	                    </c:if>
	                    <c:if test="${role.roleState eq 0 }">
	                    <td class="roleState"><span style="color: red"><b>禁用</b></span></td>
	                     <td><a class="glyphicon glyphicon-pencil show-roleinfo-form" aria-hidden="true" title="修改角色信息" href="javascript:void(0);" data-toggle="modal" data-target="#role-form-div"></a>
	                    	<a class="glyphicon glyphicon-remove delete-this-role" aria-hidden="true" title="删除角色" href="javascript:void(0);"></a>
	                    	<button type="button"  class="btn btn-warning ban-this-role" >启用</button>
	                    </td>
	                    </c:if>
	                </tr>
                </c:forEach>
            </table>
            </div>
             <jsp:include page="standard.jsp"/>
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
    //模糊查询
		  $("#check").click(function(){
			var roleName1=$("#roleName1").val()==""?"":$("#roleName1").val()
		  	var roleState1=$("#roleState1").val();
			console.log(roleState1)
		  	location.href="${pageContext.request.contextPath}/role/list.action?roleName="+roleName1+"&roleState="+roleState1; 
		  });
	    //权限跳转 
	    $(".show-role-auth").click(function(){
	    	var roleId=$(this).parents("tr").find(".roleid").html();
	    	location.href="${pageContext.request.contextPath }/role/selectRoleAuth.action?roleId="+roleId;
		});
    	//禁用
    	$(".role-list").on("click",".ban-this-role",function(){
    		var roleId=$(this).parents("tr").find(".roleid").html();
			var roleState=$(this).parents("tr").find(".roleState").text();
			if(roleState == "启用"){
				if(confirm('确认禁用ID为"'+roleId+'"的用户吗？')){
					location.href="${pageContext.request.contextPath }/role/updateRoleState.action?roleId="+roleId+"&roleState="+roleState;
				}
			}else if(roleState=="禁用"){
				if(confirm('确认启用ID为"'+roleId+'"的用户吗？')){
					location.href="${pageContext.request.contextPath }/role/updateRoleState.action?roleId="+roleId+"&roleState="+roleState;
				}
		   }
		});
    	//删除按钮
	    $(".role-list").on("click",".delete-this-role",function(){
	    	var roleId=$(this).parents("tr").find(".roleid").html();
				if(confirm('确认删除ID为"'+roleId+'"的角色吗？')){
					location.href="${pageContext.request.contextPath }/role/deleteRole.action?roleId="+roleId;
				}
		});
  		//添加
		$(".show-add-form").click(function(){
			$("#code").css("display","inline")
			resetRoleForm("添加新角色","添加");
			//getAllPerms($(".perm-inputs"));
		});
		//将form中的信息加载  包括清楚信息  标题 和按钮的加载
	   	function resetRoleForm(title,button){
			$(".clean").text("")
	       	$(".role-form input[type='text']").val("");
			$(".role-form input[type='checkbox']").prop("checked",false);
	   		$(".role-form-title").html(title);
			$(".role-submit").html(button);
	  	}
    	//验证名称是否可用
	    var flag=false;
	    $("#roleNameInput").blur(function(){
	    	var roleName=$("#roleNameInput").val()
	    	if(roleName==""){
	    		flag=false;
	    		return;
	    	}
	    	$.ajax({
	    		url:"${pageContext.request.contextPath }/role/isRoleName.action",
	    		type:"POST",
	    		data:{
	    			roleName:roleName,
	    		},
	    		dataType:"json",
	    		success:function(data){
	    			if(data.msg){
	    				flag=true;
	    				$("#roleNameError").text("该用户可用").css("color","green")
	    			}else{
	    				flag=false;
	    				$("#roleNameError").text("该用户不可用").css("color","red")	
	    			}
	    		},
	    		error:function(){
	    			alert("系统异常")
	    		}
	    	});
	    });
	    //验证code 是否可用
	    var flag1=false;
	    $("#codeInput").blur(function(){
	    	var roleCode=$("#codeInput").val()
	    	if(roleCode==""){
	    		flag1=false;
	    		return;
	    	}
	    	$.ajax({
	    		url:"${pageContext.request.contextPath }/role/isRoleName.action",
	    		type:"POST",
	    		data:{
	    			roleCode:roleCode,
	    		},
	    		dataType:"json",
	    		success:function(data){
	    			if(data.msg){
	    				flag1=true;
	    				$("#roleCodeError").text("该code可用").css("color","green")
	    			}else{
	    				flag1=false;
	    				$("#roleCodeError").text("该code不可用").css("color","red")	
	    			}
	    		},
	    		error:function(){
	    			alert("系统异常")
	    		}
	    	});
	    });
  //修改信息前 回显信息
		 $(".role-list").on("click",".show-roleinfo-form",function(){ 
			resetRoleForm("更新角色信息","更新");
			getAllPerms($(".perm-inputs"));
			var roleId=$(this).parents("tr").find(".roleid").html();
			var roleName=$(this).parents("tr").find(".roleName").html();
			var roleDesc=$(this).parents("tr").find(".roleDesc").html();
			$("input[name='roleId']").val(roleId);
			$("input[name='roleName']").val(roleName);
			$("input[name='roleDesc']").val(roleDesc);
			$("#code").css("display","none");
			/* getPermsByRoleId(roleId,function(data){
				for(var i in data){
					$('.role-form input[name="permIds"][value="'+data[i].permissionId+'"]').prop("checked",true);
				}
			}); */
		});
  		
  //添加或者修改的提交按钮
		 $(".role-submit").click(function(){
			 var roleName=  $("#roleNameInput").val() 
			 var roledesc=  $("#descInput").val() 
			 var rolecode=  $("#codeInput").val() 
			 	if($(this).html()==="添加"){
					//请求添加新角色 前进行form 表单验证
					 if(!!roleName&&!!roledesc&&!!rolecode&&flag&&flag1){
						 $.ajax({
								url:"${pageContext.request.contextPath }/role/addRole.action",
								type:"POST",
								data:$(".role-form").serialize(),
								dataType:"json",
								success:function(data){
									$("#role-form-div").modal("hide");
									showTips("添加成功！");
									location.href="${pageContext.request.contextPath}/role/list.action?";
								},
								error:function(){
									showTips("服务器异常！");
								}
							});
					 }else{
						 alert("请正确填写！")
						 return ;
					 }
				}else{
					//更新角色信息 提交前进行form验证
					if(!!roleName&&!!roledesc&&flag){
						$.ajax({
								url:"${pageContext.request.contextPath }/role/updateRole.action",
								data:$(".role-form").serialize(),
								type:"POST",
								success:function(){
									location.href="${pageContext.request.contextPath}/role/list.action?";
								},
								error:function(){
									showTips("服务器异常！");
								}
							});	
					}else{
						alert("请正确填写！")
						 return ;
					}
			 }
		});
  
    	function showTips(contents){
    		$("#op-tips-content").html(contents);
			$("#op-tips-dialog").modal("show");
       	}
    	
       
    	//权限查询
		function getPermsByRoleId(roleId,doSuccess){
			$.ajax({
				url:"showroleperms.html",
				data:{roleId:roleId},
				type:"POST",
				dataType:"json",
				success:function(data){
					doSuccess(data);
				}
			});
		}
    	//展示权限表单
		$(".role-list").on("click",".show-role-perms",function(){
			var roleId=$(this).parents("tr").find(".roleid").html();
			var rlTd=$(this).parent();
			//请求查看用户角色
			getPermsByRoleId(roleId,function(data){
				rlTd.html("");
				for(var i in data){
					var role=data[i].permissionDesc+"<br/>";
					rlTd.append(role);
				}
			});
		});
		function getAllPerms(obj){
        	obj.html("");
    		$.ajax({
				url:"listperms.html",
				type:"POST",
				dataType:"json",
				success:function(data){
					for(var i in data){
						obj.append('<input type="checkbox" name="permIds" value="'+
								data[i].permissionId+'"/>'+data[i].permissionDesc+':');
						if(data[i].isNavi===1){
							obj.append('<font color="red">导航结点</font>');
						}else{
							obj.append("非导航结点");
						}
						obj.append("<br/>");
					}
				}
			});
       	}
    </script>
  </body>
</html>
