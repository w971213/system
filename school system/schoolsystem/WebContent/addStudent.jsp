<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	    <meta name="renderer" content="webkit">
	    <title>添加学生</title>  
	    <link rel="stylesheet" href="css/pintuer.css">
	    <link rel="stylesheet" href="css/admin.css">
	    <script src="js/jquery.js"></script>
	    <script src="js/pintuer.js"></script>  
	</head>
	<body>
		<div class="panel admin-panel">
		  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 基本信息</strong></div>
			  <div class="body-content">
				    <form action="${pageContext.request.contextPath }/addStudent.action" method="post" class="form-x" >
				      <div class="form-group">
				        <div class="label">
				          <label>学号：</label>
				        </div>
				        <div class="field">
				          <input type="text" class="input w50" name="sid" data-validate="required:请输入学号,length#==11:学号只能等于11位"  />
				          <div class="tips"></div>
				        </div>
				      </div>
				      <div class="form-group">
				        <div class="label">
				          <label>密码：</label>
				        </div>
				        <div class="field">
				          <input type="text" class="input w50" name="s_key" data-validate="required:请输入新密码,length#>=5:新密码不能小于5位"   />
				          <div class="tips"></div>
				        </div>
				      </div>
				      <div class="form-group">
				        <div class="label">
				          <label>姓名：</label>
				        </div>
				        <div class="field">
				          <input type="text" class="input" name="s_name" data-validate="required:请输入名字"   />
				        </div>
				      </div>
				      <div class="form-group">
				        <div class="label">
				          <label>年龄：</label>
				        </div>
				        <div class="field">
				          <input type="number" class="input w50" name="s_age"   />
				          <div class="tips"></div>
				        </div>
				      </div>
				      <div class="form-group">
				        <div class="label">
				          <label>性别：</label>
				        </div>
				        <div class="field" style="padding-top:8px;">
				         	<input type="radio" id="ishome"  name="s_sex" value="1"  />男
				         	<input type="radio" id="ishome" name="s_sex" value="2"  />女
				          <div class="tips"></div>
				        </div>
				      </div>
				      <div class="form-group">
				        <div class="label">
				          <label>电话：</label>
				        </div>
				        <div class="field">
				       		<input type="text" class="input" name="s_phone" data-validate="required:请输入电话,length#==11:电话输入有误"   />
				          <div class="tips"></div>
				        </div>
				      </div>
				      <div class="form-group">
				        <div class="label">
				          <label>学分：</label>
				        </div>
				        <div class="field">
				          <input type="text" class="input" name="s_credit" value="0.0" disabled="disabled" />
				          <div class="tips"></div>
				        </div>
				      </div>
				       <div class="form-group">
				        <div class="label">
				          <label>邮箱：</label>
				        </div>
				        <div class="field">
				          <input type="text" class="input" name="s_email"   />
				          <div class="tips"></div>
				        </div>
				      </div>
				      <div class="form-group">
				        <div class="label">
				          <label>地址：</label>
				        </div>
				        <div class="field">
				          <input type="text" class="input" name="s_address" data-validate="required:请输入地址"   /> 
				          <div class="tips"></div>
				        </div>
				      </div>
				      <div class="form-group">
				        <div class="label">
				          <label></label>
				        </div>
				        <div class="field">
				          <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>   
				        </div>
     				  </div>  
			    </form>
		  	</div>
		</div>
	</body>
</html>