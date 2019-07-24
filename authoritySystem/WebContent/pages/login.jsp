<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <title>登录页面</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/static/css/login.css" rel="stylesheet">
   <style type="text/css">
   	.red{color:red}
   </style>
   <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.cookie.js"></script>
   <script type="text/javascript">	
   //验证码验证
   
	$(document).ready(function(){
		//验证cookie是否有值
       	var rem = $.cookie('userName');
       	if(rem){
           $("#rememberMe").prop("checked",true);
           $("#inputEmail").val($.cookie("userName"));
           $("#inputPassword").val($.cookie("userPwd"));
       	}else if(!rem){
       	 $("#rememberMe").prop("checked",false);
       	}
		$("#inputCode").blur(function (){	
			var vCode=$("#inputCode").val();
		 	 if(!!vCode){
		 		$("#error_html").html("");
		 	 	$.ajax({  
		                type:"POST", //请求方式 
		                url:"${pageContext.request.contextPath}/user/validCode.action",
		                dataType:"json", //返回数据类型
		                data:{//请求参数
		               		vCode:vCode
		                },
		                success:function(data){ //请求成功后
		                	if(data.msg){
	                      	 	$("#error_html").addClass("green").html("验证码正确");
	                       }else{
	                        	$("#error_html").addClass("red").html("验证码不正确!");
	                       }
		                },
		                error:function (){
		                	alert("系统出现异常！");
		                }    
		         }); 
	         }else{
	        	 $("#error_html").addClass("red").html("验证码为空");
	         }
			});
	});
	//登录	
	function login(){
		var userName=$("#inputEmail").val();//获取用户名
		var psw=$("#inputPassword").val();
		var vCode=$("#inputCode").val();
		$("#userNameError").text("");
		$("#userPwdError").text("");
		$("#error_html").html("");
		if(!/^\w{4,16}$/.test(userName)){
			//alert("用户名不合法! 4-16位，字母，数字，下划线");
			$("#userNameError").addClass("red").text("用户名不合法! 4-16位，字母，数字，下划线");
			return false;
		}else if(psw.length>16 || psw.length<6){
			$("#userPwdError").addClass("red").text("密码不合法! 6-16位，字母，数字")
		   //alert("密码不合法! 6-16位，字母，数字");
			return false;
		}else {
			if(!!vCode){
				$.ajax({  
	                type:"POST", //请求方式 
	                url:"${pageContext.request.contextPath}/user/validCode.action",
	                dataType:"json", //返回数据类型
	                data:{//请求参数
	               		vCode:vCode
	                },
	                success:function(data){ //请求成功后
	                	if(data.msg){
	                  	 	$("#error_html").removeClass("green").html("验证码正确");
	                  	 	$.ajax({  
	        	                type:"POST",  
	        	                url:"${pageContext.request.contextPath}/user/isAvailableUser.action",
	        	                dataType:"json",  
	        	                data:{
	        	               		userPwd:psw,
	        	                	userCode:userName
	        	                },  
	        	                success:function(data){ 
	        	                  if(data.res){//登录成功
	        	                  	 var chc=$("#rememberMe").prop("checked")
	        		                  //判断checkbox是否被选中
	        		                  if(chc){
	        		                    $.cookie("userName", userName,{expires:7});
	        		                    $.cookie("userPwd",psw,{expires:7})
	        		                  }else{
	        		                  	//清空
	        		                  	$.cookie("userName", null);
	        		                  	$.cookie("userPwd", null);
	        		                   } 
	        	                 	  window.location.href="${pageContext.request.contextPath}/user/index.action";
	        	                 	}
	        	                }
	        	            });
	                	}else{
	                		$("#error_html").removeClass("red").html("验证码不正确");
	                		return false;
	                	}
	                }
	            }); 
			}else{
				 $("#error_html").addClass("red").html("验证码为空");
				 return false;
			}
		}
   }
		
	//回车登录
	$(function(){$("body").keydown(function(ev){
		if(ev.keyCode=="13"){
			login();
		}
	})
	});
</script>
  </head>
  <body>
    	<form class="form-signin" method="post" action="##">
			<h3 class="form-signin-heading">请登录</h3>
			<label for="inputEmail" class="sr-only">用户名</label>
			<input type="text" id="inputEmail" class="form-control class123456" placeholder="用户名"  name="username" maxlength="20">
			 <label id="userNameError"></label>
			<label for="inputPassword" class="sr-only">密码</label>
			<input type="password"  id="inputPassword" class="form-control" placeholder="密码"  name="password" maxlength="10">
			<label id="userPwdError"></label><br/>
			<label for="inputEmail" class="sr-only" >验证码</label>
			<input type="text" id="inputCode" placeholder="验证码" name="code" tabindex="6" style="width:80px;text-transform:uppercase;ime-mode:disabled" maxlength="4">
			<img id="vdimgck"  src="${pageContext.request.contextPath}/pages/image.jsp?d="+new Date()+"" alt="看不清？点击更换" align="absmiddle" width="75" style="cursor:pointer" onclick="this.src=this.src+'?'" />
			<label id="error_html" style="font-size:18px;"></label>
		   <div class="checkbox" id="checkboxid" >
			  <label>
				<input type="checkbox" id="rememberMe" name="rememberMe" > 记住我&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  </label>
			</div>
			<p class="bg-warning">${error}</p>
			<button class="btn btn-lg btn-primary btn-block" type="button" onclick="return login()" >登录</button>
     </form>
    
    
    
</body>
</html>
