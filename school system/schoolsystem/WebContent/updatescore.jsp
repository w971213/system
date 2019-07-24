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
<title>更新学生成绩</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-key"></span> 更新成绩</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="${pageContext.request.contextPath }/updateStudentScore1.action">
       <div class="form-group">
        <div class="label">
          <label>学号：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="sid" value="${ELECT.sid}" disabled="disabled"/>
        </div>
      </div> 
      <div class="form-group">
        <div class="label">
          <label>课程编号：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="cid" value="${ELECT.cid}" disabled="disabled"/>
        </div>
      </div> 
         
      <div class="form-group">
        <div class="label">
          <label for="sitename">成绩：</label>
        </div>
        <div class="field">
          <input type="number" class="input w50" name="score" size="50" placeholder="请输入成绩" data-validate="required:请输入成绩,length#<=3:小于3位" />         
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
</body></html>