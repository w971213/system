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
	    <title>教师个人信息</title>  
	    <link rel="stylesheet" href="css/pintuer.css">
	    <link rel="stylesheet" href="css/admin.css">
	    <script src="js/jquery.js"></script>
	    <script src="js/pintuer.js"></script>  
	</head>
	<body>
		<div class="panel admin-panel">
		  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 个人信息</strong></div>
			  <div class="body-content">
			  	 <form method="post" class="form-x" >
				      <div class="form-group">
				        <div class="label">
				          <label>编号：</label>
				        </div>
				        <div class="field">
				          <input type="text" class="input" name="stitle" value=" ${TEACHER.tid }" readonly />
				          <div class="tips"></div>
				        </div>
				      </div>
				      <div class="form-group">
				        <div class="label">
				          <label>姓名：</label>
				        </div>
				        <div class="field">
				          <input type="text" class="input" name="surl" value="${TEACHER.tname }" disabled="disabled"/>
				        </div>
				      </div>
				      <div class="form-group" style="display:none">
				        <div class="label">
				          <label>年龄：</label>
				        </div>
				        <div class="field">
				          <input type="text" class="input" name="sentitle" value="${TEACHER.tage }" disabled="disabled" />
				          <div class="tips"></div>
				        </div>
				      </div>
				      <div class="form-group">
				        <div class="label">
				          <label>性别：</label>
				        </div>
				        <div class="field">
				         	<input type="text" class="input" name="sentitle" value="${TEACHER.tsex }" disabled="disabled" />
				         <!--  <textarea class="input" name="skeywords" style="height:80px"></textarea> -->
				          <div class="tips"></div>
				        </div>
				      </div>
				      <div class="form-group">
				        <div class="label">
				          <label>电话：</label>
				        </div>
				        <div class="field">
				       		<input type="text" class="input" name="sentitle" value="${TEACHER.tphone }" disabled="disabled" />
				          <!-- <textarea class="input" name="sdescription"></textarea> -->
				          <div class="tips"></div>
				        </div>
				      </div>
				       <div class="form-group">
				        <div class="label">
				          <label>邮箱：</label>
				        </div>
				        <div class="field">
				          <input type="text" class="input" name="s_tel" value="${TEACHER.tmail }" disabled="disabled" />
				          <div class="tips"></div>
				        </div>
				      </div>
				      <div class="form-group">
				        <div class="label">
				          <label></label>
				        </div>
				      </div>
				 </form>
		  	</div>
		</div>
	</body>
</html>