<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>权限管理 - 权限列表</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/static/css/layout.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/zTree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/zTree/css/demo.css"
	type="text/css">
	<style type="text/css">
   	.red{color:red}
   	.green{color:green}
   </style>
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/zTree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript">
	var setting = {

		check : {
			enable : true,
			chkStyle : "radio", //单选框
			radioType :"all"//对所有节点设置单选
		},
		data : {
			simpleData : {//是否使用简单数据模式
				enable : true
			}
		},
		callback : {
			onCheck : onCheck
		},
		view : {
			fontCss : setFontCss
		}
	};
	//id 标识 ，pId 父id，name 名称，open 是否展开节点， checked  是否选中        
	$(document).ready(function() {
		/* 展示树 */
		var zNodes = ${AuthInfo}
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		/*修改权限 回显数据 */
		$(".update-Auth").click(function(){
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = treeObj.getCheckedNodes(true);
			if(nodes==''){
				alert("请先选择权限");
				return false;
			}else{
				$("#authNameInput").val(nodes[0].name);
				$("#descInput").val(nodes[0].desc);
				$(".authId").val(nodes[0].id);
			}
		});
		//修改验证用户名可用 光标失去事件
		var flag=false;
		$("#authNameInput").blur(function(){
			var authName=$("#authNameInput").val();
			if(authName==""){
				return;
			}
			$.ajax({
				url:"${pageContext.request.contextPath}/auth/isAvailableAuthName.action",
				type:"POST",
				data:{
					authName:authName
				},
				dataType:"json",
				success:function(data){
					if(data.msg){
						$("#authNameInputError").css("color","green").text("该用户名可用")
						flag=true;
					}else{
						flag=false;
						$("#authNameInputError").css("color","red").text("该用户名不可用")
					}
				},
				error:function(){
					alert("服务器异常");
				}
			}); 
		});
		/* 提交修改表单 */
		$(".updateAuthInfo").submit(function(){
			var authName=$("#authNameInput").val();
			var authName=$("#descInput").val();
			if(!!authName&&!!authName&&flag){
				return true;
			}else{
				alert("请正确填写！")
				return false;
			} 
		});
		
		/* 恢复权限 */
		$(".rein-auth").click(function(){
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = treeObj.getCheckedNodes(true);
			if(nodes==''){
				alert("请先选择权限");
				return false;
			}else if(nodes[0].state!=0){
				alert("该权限已是启用状态");
				return false;
			}else{
				if(confirm('确认恢复：'+nodes[0].name+'？')){
					location.href="${pageContext.request.contextPath}/auth/reinAuth.action?authId="+nodes[0].id;
				}
			}
		});
		//禁用权限
		$(".ban-auth").click(function(){
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = treeObj.getCheckedNodes(true);
			if(nodes==''){
				alert("请先选择权限");
				return false;
			}else if(nodes[0].state!=1){
				alert("该权限已是启用状态");
				return false;
			}else{
				if(confirm('确认禁用：'+nodes[0].name+'？')){
					location.href="${pageContext.request.contextPath}/auth/banAuth.action?authId="+nodes[0].id;
				}
			}
		});
		/* 准备数据 */
		$(".add-auth-before").click(function(){
			/* 清空原数据 */
			$("#addAuth-form-div .clean").val("")
			$("#addAuth-form-div .error").text("")
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = treeObj.getCheckedNodes(true);
			if(nodes==''){  //没选则添加一级权限
				$(".parentId").val(0)
				$("#code").css("display","none")
				$("#url").css("display","none")
				$("#firstAuth").attr("selected","selected")
				$("#secondAuth").attr("disabled","disabled")
				$("#thirdAuth").attr("disabled","disabled")
				return ;
			}else{
				if(nodes[0].state==0){ //选择状态为禁用的 禁止添加
					alert("该权限为禁用状态");
				return ;
				}else{
					if(nodes[0].level==0){
						/* pId为0 默认为null 
							添加二级权限
						*/
						$(".parentId").val(nodes[0].id)
						$("#code").css("display","none")
						$("#url").css("display","block")
						$("#firstAuth").attr("disabled","disabled")
						$("#secondAuth").attr("selected","selected")
						$("#thirdAuth").attr("disabled","disabled")
						return ;
					}else if(nodes[0].level>=1){
						/* 添加三级权限或者三级以上的*/
						$("#code").css("display","block")
						$("#url").css("display","block")						
						$(".parentId").val(nodes[0].id)
						$("#firstAuth").attr("disabled","disabled")
						$("#secondAuth").attr("disabled","disabled")
						$("#thirdAuth").attr("selected","selected")
						return ;
					}/* else if(nodes[0].level=2){
						$(".parentId").val(nodes[0].id)
						$("#code").css("display","block")
						$("#url").css("display","block")						
						$(".parentId").val(nodes[0].pId)
						$("#firstAuth").attr("disabled","disabled")
						$("#secondAuth").attr("disabled","disabled")
						$("#thirdAuth").attr("selected","selected")
						return ;
					} */
				}
			}
		});
		//判断添加的数据  url .action 结尾   authName 不能为添加过的
		var flag1=false;
			$("#authNameInput1").blur(function(){
				var authName=$("#authNameInput1").val();
				if(authName==""){
					return;
				}
				$.ajax({
					url:"${pageContext.request.contextPath}/auth/isAvailableAuthName.action",
					type:"POST",
					data:{
						authName:authName
					},
					dataType:"json",
					success:function(data){
						if(data.msg){
							$("#authNameError").css("color","green").text("该用户名可用")
							flag1=true;
						}else{
							flag1=false;
							$("#authNameError").css("color","red").text("该用户名不可用")
						}
					},
					error:function(){
						alert("服务器异常");
					}
				}); 
			});
			/* 以.action结尾 */
			var flag2=false;
			$("#authUrlInput").blur(function(){
			var authUrl=$("#authUrlInput").val()
			if(authUrl==""){
				return;
			}
			if(/.action$/.test(authUrl)){
				$("#authurlError").text("地址格式正确").css("color","green")
				flag2=true;
			}else{
				$("#authurlError").text("地址格式不正确").css("color","red")
				flag2=false;
			}
			});
	//添加权限
		$(".add-auth-form").click(function(){
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = treeObj.getCheckedNodes(true);
					var an=$("#authNameInput1").val();//权限名
		     		var ac=$("#codeInput1").val(); //权限code
		     		var au=$("#authUrlInput").val();//权限地址
		     		var ad=$("#descInput1").val();//权限描述
		     		var pId=$(".parentId").val(); //权限父id
		     	//提交判断
		     		if(nodes==''){
		     			if(!!an&&!!ad&&flag1){
							$.ajax({
								url:"${pageContext.request.contextPath}/auth/insertAuth.action",
								dataType:"json",
								type:"POST",
								data:{
								authName:an,
								authDesc:ad,
								parentId:pId,
								},
								success:function(data){
									if(data.msg){
										alert("添加成功")
										location.href="${pageContext.request.contextPath}/user/showAllAuth.action?";
									}else{
										location.href="${pageContext.request.contextPath}/user/showAllAuth.action?";
										alert("添加失败")
									}
								},
								error:function(){ 
								alert("执行异常");
								}
							});
						}else{ 
						alert("请正确填写");
						}
		     		}else{//二三级权限添加判断
		     			if(ac==''){
		     				if(!!an&&!!au&&!!pId&&flag1&&flag2){
								$.ajax({
									url:"${pageContext.request.contextPath}/auth/insertAuth.action",
									dataType:"json",
									type:"POST",
									data:{
									authName:an,
									authCode:ac,
									authDesc:ad,
									authUrl:au,
									parentId:pId,
									},
									success:function(data){
										if(data.msg){
											alert("添加成功")
											location.href="${pageContext.request.contextPath}/user/showAllAuth.action?";
										}else{
											alert("添加失败")
										}
									},
									error:function(){ 
									alert("执行异常");
									}
								});
							}else{ 
							alert("请正确填写");
							}
		     			}else{
		     				if(!!an&&!!ac&&!!au&&!!pId&&flag1&&flag2){
								$.ajax({
									url:"${pageContext.request.contextPath}/auth/insertAuth.action",
									dataType:"json",
									type:"POST",
									data:{
									authName:an,
									authCode:ac,
									authDesc:ad,
									authUrl:au,
									parentId:pId,
									},
									success:function(data){
										if(data.msg){
											alert("添加成功")
											location.href="${pageContext.request.contextPath}/user/showAllAuth.action?";
										}else{
											alert("添加失败")
										}
									},
									error:function(){ 
									alert("执行异常");
									}
								});
							}else{ 
							alert("请正确填写");
							}
			     			
			     		}
		     			}
		});
	//删除权限		
	 	$(".delete-auth-submit").click(function(){ 
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = treeObj.getCheckedNodes(true), v = "";
			if(nodes==''){
				alert("请先选择权限");
				return false;
			}else{
				if(confirm('确认删除：'+nodes[0].name+'？')){
					 $.ajax({
							url : "${pageContext.request.contextPath}/auth/deleteAuth.action",
							type : "POST",
							data : {
								authId : nodes[0].id,
							},
							success : function(date) {
								if(date.msg){
									alert("删除成功")	
									location.href="${pageContext.request.contextPath}/user/showAllAuth.action?";
								}else{
									alert("删除失败")	
								}
							},
							error : function() {
								alert("执行异常");
							},
						}); 
				}
			}
		});	
	});
	//禁用状态为灰色
	function setFontCss(treeId, treeNode) {
		return treeNode.state == 0 ? {color : "gray"} : {};
	}
	//选中复选框后触发事件
	function onCheck(e, treeId, treeNode) {
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo"), nodes = treeObj
				.getCheckedNodes(true), v = "";
		//获取选中的复选框的值
		for (var i = 0; i < nodes.length; i++) {
			v += nodes[i].id;
			//alert(nodes[i].id); //获取选中节点的值
			$("#authIdInput").val(v);
		}
	}
	
</script>
</head>
<body>

	<!-- 头部 -->
	<jsp:include page="header.jsp" />

	<div class="container-fluid" style="margin-top: 30px;">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<jsp:include page="navibar.jsp" />
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">权限列表</h1>
				<div class="row placeholders">
					<!--修改权限表单 start-->
					<div>
						<button type="button" class="btn btn-default update-Auth" data-toggle="modal" data-target="#update-auth-form-div ">修改权限</button>
						<button type="button" class="btn btn-default add-auth-before" data-toggle="modal" data-target="#addAuth-form-div" >添加权限</button>
						<div class="modal fade " id="update-auth-form-div" tabindex="-1"
							role="dialog" aria-labelledby="mySmallModalLabel">
							<div class="modal-dialog modal-md" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="role-form-title">修改权限</h4>
									</div>
									<div class="modal-body">
										<form action="${pageContext.request.contextPath }/auth/updateAuth.action" class="role-form updateAuthInfo" method="post">
											<!-- <input type="text" name="authId" class="form-control" id="authIdInput"> -->
											<input type="text" name="authId" style="display:none" class="form-control authId" >
											<div class="form-group">
												<label for="authNameInput">名称</label>
												<input type="text"name="authName" class="form-control" id="authNameInput"
													placeholder="要修改的权限名称">
												<label id="authNameInputError"></label>
											</div>
											<div class="form-group">
												<label for="descInput">权限描述</label>
												 <input type="text" name="authDesc" class="form-control" id="descInput" placeholder="要修改的权限描述">
												<label id="authDescInputError"></label>
											</div>
											<div class="modal-footer">
												<input type="button" class="btn btn-default" value="取消" data-dismiss="modal"></input> 
												<input type="submit" class="btn btn-primary"></input>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					<!-- 添加权限 -->
					<div class="modal fade " id="addAuth-form-div" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-md" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="role-form-title">添加权限</h4>
								</div>
								<div class="modal-body">
									<form class="role-form" >
										<input type="text" name="parentId" style="display:none" class="form-control parentId" >	
										<div class="form-group ">
											<label for="authNameInput1">名称</label>
											<input type="text" name="authName" class="form-control clean" id="authNameInput1" placeholder="权限名称">
											<label id="authNameError" class="error"></label>
										</div>
										<div class="form-group" id="code">
											<label for="codeInput1">权限code</label>
											<input type="text" name="authCode" class="form-control clean" id="codeInput1" placeholder="权限代码">
											<label ></label>
										</div>
										<div class="form-group">
											<label for="descInput1">权限描述</label> 
											<input type="text" name="authDesc" class="form-control clean" id="descInput1" placeholder="权限描述">
											<label ></label>
										</div>
										<div class="form-group" id="url">
												<label for="descInput1">权限url</label> 
												<input type="text" name="authurl" class="form-control clean" id="authUrlInput" placeholder="权限url">
												<label id="authurlError" class="error"></label> 
										</div>
										<div class="form-group">
											<label for="descInput1">权限等级</label> 
											<select style="width:200px" class="form-control" >
											<option id="firstAuth" value=0>模块 </option>
											<option id="secondAuth" value= >列表 </option>
											<option id="thirdAuth" >按钮 </option>
											</select>
										</div> 
									</form>
										<div class="modal-footer">
											<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
											<button type="button"  class="btn btn-primary add-auth-form"> 提交</button>
										</div>
								</div>
								
							</div>
						</div>
					</div>
				</div>
				<!--添加权限表单 end-->
				<div class="space-div"></div>
				<div class="zTreeDemoBackground left">
					<ul id="treeDemo" class="ztree" style="width: 1024px;"></ul>
				</div>
				<div class="space-div"></div>
				<div>
					<button type="button" class="btn btn-primary rein-auth" >恢复权限</button>
						<button type="button" class="btn btn-primary ban-auth" >禁用权限</button>
					<button type="button"  class="btn btn-primary delete-auth-submit" >删除权限</button>
				</div>
			</div>
		</div>
	</div>
	</div>

	<!-- 提示框 -->
	<div class="modal fade" id="op-tips-dialog" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">提示信息</h4>
				</div>
				<div class="modal-body" id="op-tips-content"></div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<script
		src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>

</body>
</html>
