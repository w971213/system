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
    <title>课程信息</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>  
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong class="icon-reorder"> 课程列表</strong></div>
  	<div class="padding border-bottom">
    </div>
  <table class="table table-hover text-center">
    <tr>
      <th width="5%">编号</th>     
      <th>课程名字</th>  
      <th>授课老师</th>  
      <th>课程类型</th>
      <th>课程学分</th>
      <th>课堂人数</th> 
      <th>已选人数</th>  
       <th width="250">操作</th>    
    </tr>
	   <c:forEach items="${Teacher }" var="teacher"  varStatus="status">
	   	<c:forEach items="${teacher.course }" var="course">
	   	 	<tr>
		      <td>${course.cid }</td>      
		      <td>${course.cname }</td> 
		      <td>${teacher.tname }</td>   
		      <td>${course.ctype }</td>   
		      <td>${course.credit }</td>      
		      <td>${course.cmax }</td>
		      <td>${course.cprecid}</td>
		      <td>
			      <div class="button-group">
			      <a style="cursor: pointer;" class="button border-main" onclick="return del(${course.cid })"><span class="icon-edit"></span>查看</a>
			      </div>
		      </td> 
	    	</tr> 
		 </c:forEach> 
	    </c:forEach> 
  </table>
</div>
 <script>
 function del(cid){
 		location.href="${pageContext.request.contextPath }/showStudentInCourse.action?cid="+cid;
 }
</script> 
<div class="panel admin-panel margin-top" id="add">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 增加选修课</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="${pageContext.request.contextPath }/addEleCour.action">    
      <div class="form-group">
        <div class="label">
          <label>课程名字：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50 " name="name" placeholder="请输入课程名字" data-validate="required:请填写课程名字"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>课程类型：</label>
        </div>
        <div class="field">
        	<select class="input w50" name="ctype">
        		<option>必修</option>
        		<option selected>选修</option>
        	</select>
          <div class="tips"></div>
        </div>
      </div>
     <div class="form-group">
        <div class="label">
          <label>教师编号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="password" placeholder="请输入教师编号" data-validate="required:请填写教师编号"/>
          <div class="tips"></div>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>授课教师：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${Teacher[0].tname }" readonly />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>学分结构：</label>
        </div>
        <div class="field">
       		 <input class="input w50" value="1.0" name="credit" list="credit" />
							<datalist id="credit">
								<option>0.5</option>
								<option>1.0</option>
								<option>1.5</option>
								<option>2.0</option>
							</datalist>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>限选人数：</label>
        </div>
        <div class="field">
          <input type="number"  class="input w50"  name="cmax" placeholder="不能查过60人" data-validate="required:请输入限选人数" />
          <div class="tips"></div>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>备注：</label>
        </div>
        <div class="field">
          <textarea  class="input" name="cnote" style="height:75px;width: 65%;"placeholder="请输入。。。" ></textarea>
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
</body></html>