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
          <h3 class="page-header">用户组列表</h3>
          <div class="row placeholders">
            	<button type="button" style="margin-left: 100px;" class="btn  btn-primary  show-add-form" data-toggle="modal" data-target="#group-form-div">添加新用户组</button>
              	<input type="text" id="groupName1" style="width:105px;margin-left: 500px;" name="groupName1"  placeholder="用户组名" value="${Params.groupName }">
	            <select id="groupState1" style="width:105px;height:26px;">
              		<option value="" style="display:none" >用户组状态</option>
              		<option value=""  ${ empty Params.groupState ?"selected='selected'":""}>全部</option>
              		<option value="1" ${Params.groupState eq 1 ?"selected='selected'":""}>启用</option>
              		<option value="0" ${not empty Params.groupState and Params.groupState eq 0 ?"selected='selected'":""}>禁用</option>
	            </select>
               <button id="check" type="button" class="btn btn-warning" >查询</button>
                <!--添加新角色表单-->
                <div class="modal fade " id="group-form-div" tabindex="-1" group="dialog" aria-labelledby="mySmallModalLabel">
                  <div class="modal-dialog modal-md" group="document">
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="group-form-title" ></h4>
                      </div>
                      <div class="modal-body">
                      	<form class="group-form" >
                          <input type="hidden" name="groupId" class="form-control" id="groupIdInput">
                          <div class="form-group">
                            <label for="groupNameInput">名称</label>
                            <input type="text" name="groupName" class="form-control" id="groupNameInput" placeholder="角色名">
                         	<label id="groupNameError" class="clean"></label>
                          </div>
                          <div class="form-group">
                            <label for="descInput">描述</label>
                            <input type="text" name="groupDesc" class="form-control" id="descInput" placeholder="角色描述">
                          </div>
                          <div class="form-group" id="code">
                            <label for="codeInput">代码</label>
                            <input type="text" name="groupCode" class="form-control" id="codeInput" placeholder="角色代码">
                          	<label id="groupCodeError" class="clean"></label>
                          </div>
                        </form>
                        <div class="modal-footer">
	                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	                        <button type="button"  class="btn btn-primary group-submit"></button>
	                      </div>
                      </div>
                    </div>
                  </div>
                </div>
            
           
            <div class="space-div"></div>
            <table class="table table-hover table-bordered group-list" >
            	<tr>
                    <td>ID</td>
                    <td>名称</td>
                    <td>描述</td>
                    <td>代码</td>
                    <td>状态</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${GroupInfo }" var="group">
	                <tr>
	                    <td class="groupId">${group.groupId }</td>
	                    <td class="groupName">${group.groupName }</td>
	                    <td class="groupDesc">${group.groupDesc }</td>
	                    <td class="groupCode">${group.groupCode }</td>
	                    <c:if test="${group.groupState eq 1 }">
	                    <td class="groupState"><b>启用</b></td>
	                    <td><a class="glyphicon glyphicon-pencil show-groupinfo-form" aria-hidden="true" title="修改用户组信息" href="javascript:void(0);" data-toggle="modal" data-target="#group-form-div"></a>
	                    	<a class="glyphicon glyphicon-remove delete-this-group" aria-hidden="true" title="删除用户组" href="javascript:void(0);"></a>
	                    	<button type="button"  class="btn btn-warning ban-this-group" >禁用</button>
	                		<button type="button"  class="btn btn-primary update-user-role" data-toggle="modal" data-target="#update-userrole">分配角色</button>
	                    </td>
	                    </c:if>
	                    <c:if test="${group.groupState eq 0 }">
	                    <td class="groupState"><span style="color: red"><b>禁用</b></span></td>
	                     <td><a class="glyphicon glyphicon-pencil show-groupinfo-form" aria-hidden="true" title="修改角色信息" href="javascript:void(0);" data-toggle="modal" data-target="#group-form-div"></a>
	                    	<a class="glyphicon glyphicon-remove delete-this-group" aria-hidden="true" title="删除角色" href="javascript:void(0);"></a>
	                    	<button type="button"  class="btn btn-warning ban-this-group" >启用</button>
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
   <!--    分配角色 -->
      <div class="modal fade " id="update-userrole" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                  <div class="modal-dialog modal-sm" role="document">
                    <div class="modal-content">
                      <div class="modal-header" >
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" >分配角色</h4>
                      </div>
                      <form  >
	                      <div class="form-group">
	                       <label >用户组ID</label>
	                       <input type="text" name="GroupId"  class="form-control" readonly >
	                      </div>
                     	 <div class="form-group">
                            <label for="userNameInput">用户组名称</label>
                            <input type="text"  name="GroupName"  readonly class="form-control"  placeholder="用户名">
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
	<!-- 提示框 -->
	<div class="modal fade" id="op-tips-dialog" tabindex="-1" group="dialog" aria-labelledby="mySmallModalLabel">
      <div class="modal-dialog modal-sm" group="document">
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
	    $(document).ready(function(){
	    	getAllRoles($("#update-userrole #add-element")); //获取所有当前角色
	    });
	    
	  //分配角色
	    $(".group-list").on("click",".update-user-role",function(){
	    	//回显当前用户的角色
			var groupId=$.trim($(this).parents("tr").find(".groupId").text());
			var groupName=$(this).parents("tr").find(".groupName").text();
			$("#update-userrole input[name='GroupName']").val(groupName);
			$("#update-userrole input[name='GroupId']").val(groupId);
			getRolesByUserId(groupId);
		});
	  //查询当前用户的角色
	  	var oldroleIds=new Array();
	    function getRolesByUserId(groupId){
			 		$.ajax({
			 			url:"${pageContext.request.contextPath }/group/selectGroupRoleId.action",
			 			type:"POST",
			 			data:{
			 				groupId:groupId
			 			},
			 			dataType:"json",
			 			success:function(data){
			 				$('#update-userrole input[name=roleIds]').prop("checked",false);
			 				if(data[0]!=null){
			 					for(var i in data[0].groupRole){
			 						$("#roleError").text("")
			 						oldroleIds[i]=data[0].groupRole[i].roleId;
				 					$('#update-userrole input[name=roleIds][value="'+data[0].groupRole[i].roleId+'"]').prop("checked",true);
				 				}
			 				}else{
			 					$("#roleError").css("color","red").text(" （该用户组没有设置角色）")
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
		 	var groupId= $("#update-userrole input[name='GroupId']").val();
			 var cbs=$("input[name='roleIds']:checked")
			 console.log(cbs)
				 if(cbs.length===0){
					 $("#roleError").css("color","red").text(" （至少设置一种设置角色）") 
				}else{
					$("#roleError").text("");
					var roleIds=new Array();
					$.each(cbs,function(i,cb){
						roleIds[i]=cb.value;
					});
					if(roleIds.toString()==oldroleIds.toString()){
						$("#roleError").css("color","red").text(" （前后角色一致）") 
						return;
					}
					location.href="${pageContext.request.contextPath}/group/updateGroupRole.action?groupId="+groupId+"&roleIds="+roleIds;
			 }
		});  
    //模糊查询
		  $("#check").click(function(){
			var groupName1=$("#groupName1").val()==""?"":$("#groupName1").val()
		  	var groupState1=$("#groupState1").val();
			console.log(groupName1)
			console.log(groupState1)
		  	location.href="${pageContext.request.contextPath}/group/list.action?groupName="+groupName1+"&groupState="+groupState1; 
		  });
    	//禁用
    	$(".group-list").on("click",".ban-this-group",function(){
    		var groupId=$(this).parents("tr").find(".groupId").html();
			var groupState=$(this).parents("tr").find(".groupState").text();
			if(groupState == "启用"){
				if(confirm('确认禁用ID为"'+groupId+'"的用户组吗？')){
					location.href="${pageContext.request.contextPath }/group/updategroupState.action?groupId="+groupId+"&groupState="+groupState;
				}
			}else if(groupState=="禁用"){
				if(confirm('确认启用ID为"'+groupId+'"的用户组吗？')){
					location.href="${pageContext.request.contextPath }/group/updategroupState.action?groupId="+groupId+"&groupState="+groupState;
				}
		   }
		});
    	//删除按钮
	    $(".group-list").on("click",".delete-this-group",function(){
	    	var groupId=$(this).parents("tr").find(".groupId").html();
	    	alert(groupId)
				if(confirm('确认删除ID为"'+groupId+'"的用户组吗？')){
					location.href="${pageContext.request.contextPath }/group/deletegroup.action?groupId="+groupId;
				}
		});
  		//添加
		$(".show-add-form").click(function(){
			$("#code").css("display","inline")
			resetgroupForm("添加新角色","添加");
			//getAllPerms($(".perm-inputs"));
		});
		//将form中的信息加载  包括清楚信息  标题 和按钮的加载
	   	function resetgroupForm(title,button){
			$(".clean").text("")
	       	$(".group-form input[type='text']").val("");
			$(".group-form input[type='checkbox']").prop("checked",false);
	   		$(".group-form-title").html(title);
			$(".group-submit").html(button);
	  	}
    	//验证名称是否可用
	    var flag=false;
	    $("#groupNameInput").blur(function(){
	    	var groupName=$("#groupNameInput").val()
	    	console.log(groupName)
	    	if(groupName==""){
	    		flag=false;
	    		return;
	    	}
	    	$.ajax({
	    		url:"${pageContext.request.contextPath }/group/isgroupName.action",
	    		type:"POST",
	    		data:{
	    			groupName:groupName,
	    		},
	    		dataType:"json",
	    		success:function(data){
	    			if(data.msg){
	    				flag=true;
	    				$("#groupNameError").text("该名称可用").css("color","green")
	    			}else{
	    				flag=false;
	    				$("#groupNameError").text("该名称不可用").css("color","red")	
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
	    	var groupCode=$("#codeInput").val()
	    	if(groupCode==""){
	    		flag1=false;
	    		return;
	    	}
	    	$.ajax({
	    		url:"${pageContext.request.contextPath }/group/isgroupName.action",
	    		type:"POST",
	    		data:{
	    			groupCode:groupCode,
	    		},
	    		dataType:"json",
	    		success:function(data){
	    			if(data.msg){
	    				flag1=true;
	    				$("#groupCodeError").text("该code可用").css("color","green")
	    			}else{
	    				flag1=false;
	    				$("#groupCodeError").text("该code不可用").css("color","red")	
	    			}
	    		},
	    		error:function(){
	    			alert("系统异常")
	    		}
	    	});
	    });
  //修改信息前 回显信息
		 $(".group-list").on("click",".show-groupinfo-form",function(){ 
			resetgroupForm("更新角色信息","更新");
			getAllPerms($(".perm-inputs"));
			var groupId=$(this).parents("tr").find(".groupId").html();
			var groupName=$(this).parents("tr").find(".groupName").html();
			var groupDesc=$(this).parents("tr").find(".groupDesc").html();
			$("input[name='groupId']").val(groupId);
			$("input[name='groupName']").val(groupName);
			$("input[name='groupDesc']").val(groupDesc);
			$("#code").css("display","none");
			/* getPermsBygroupId(groupId,function(data){
				for(var i in data){
					$('.group-form input[name="permIds"][value="'+data[i].permissionId+'"]').prop("checked",true);
				}
			}); */
		});
  		
  //添加或者修改的提交按钮
		 $(".group-submit").click(function(){
			 var groupName=  $("#groupNameInput").val() 
			 var groupdesc=  $("#descInput").val() 
			 var groupcode=  $("#codeInput").val() 
			 	if($(this).html()==="添加"){
					//请求添加新角色 前进行form 表单验证
					 if(!!groupName&&!!groupdesc&&!!groupcode&&flag&&flag1){
						 $.ajax({
								url:"${pageContext.request.contextPath }/group/addgroup.action",
								type:"POST",
								data:$(".group-form").serialize(),
								dataType:"json",
								success:function(data){
									$("#group-form-div").modal("hide");
									showTips("添加成功！");
									location.href="${pageContext.request.contextPath}/group/list.action?";
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
					if(!!groupName&&!!groupdesc&&flag){
						$.ajax({
								url:"${pageContext.request.contextPath }/group/updategroup.action",
								data:$(".group-form").serialize(),
								type:"POST",
								success:function(){
									location.href="${pageContext.request.contextPath}/group/list.action?";
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
