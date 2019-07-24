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
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/zTree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript">
       var setting = {    
        	/*    check:{
               enable:true,
               chkStyle: "radio",  //单选框
               radioType: "all"   //对所有节点设置单选
            },   */
               check:{//使用复选框
                enable:true
            },  
            data: {
                simpleData:{//是否使用简单数据模式
                    enable:true
                }
            },
            callback:{
                onCheck:onCheck
            }   
        };
       
        $(document).ready(function(){
        	var zNodes=${data }
        	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
        	//获取原数组
        	var oldId="";
        	var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
        	nodes=treeObj.getCheckedNodes(true)
        	for(var i=0;i<nodes.length;i++){
        		oldId+=nodes[i].id;
        	}
        	//点击提交事件
        	$("#updateUserAuth").click(function(){
        		var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	nodes=treeObj.getCheckedNodes(true)
            	var ids=new Array();
        		var newId="";
            	//获取选中的复选框的值
            	for(var i=0;i<nodes.length;i++){
            		ids[i]=nodes[i].id;
            		newId+=nodes[i].id
            	}
            	
            	if(oldId==""){
            		if(newId==""){
            			alert("请选择权限")
                		return;	
            		}else{
            			if(confirm('确认修改权限？')){
                			location.href="${pageContext.request.contextPath}/role/updateRoleAuth.action?ids="+ids+"&roleId="+${RoleId };	
                		}
            		}
            	}else{
            		if(newId==""){
            			if(confirm('确认清空权限？')){
                			location.href="${pageContext.request.contextPath}/role/deleteAuth.action?roleId="+${RoleId };
                		}
            		}else if(newId==oldId){
            			alert("修改权限一致")
                		return;	
            		}else{
            			if(confirm('确认修改权限？')){
                			location.href="${pageContext.request.contextPath}/role/updateRoleAuth.action?ids="+ids+"&roleId="+${RoleId };	
                		}
            		}
            	}
        	});
        	//取消按钮
        	$("#cancel").click(function(){
        		location.href="${pageContext.request.contextPath}/role/list.action?";
        	});
        	
        });
        function onCheck(e,treeId,treeNode){
        	var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
        	nodes=treeObj.getCheckedNodes(true),
        	v="";
        	//获取选中的复选框的值
        	for(var i=0;i<nodes.length;i++){
        		v+=nodes[i].id;
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
					<div class="space-div"></div>
					<div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree" style="width:1024px;"></ul>
					</div>
					<div class="space-div"></div>
					<div>
						<button type="button" id="updateUserAuth" class="btn btn-default" style="width: 100px;">确认修改</button>
						<button type="button" style="width:100px;" id="cancel" class="btn btn-primary">取消</button>		
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
