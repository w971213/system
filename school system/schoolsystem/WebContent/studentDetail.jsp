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
  <div class="panel-head"><strong class="icon-reorder">${CLASSDETAIL.cname }(${CLASSDETAIL.ctype })</strong></div>
  	<div class="padding border-bottom">
    </div>
  <table class="table table-hover text-center">
    <tr>
      <th width="5%">序号</th>     
      <th>课程名字</th>
      <th>学号</th>
      <th>学生名字</th>
      <th>课程学分</th>  
      <th>学生成绩</th>  
       <th width="250">操作</th>    
    </tr>
	   <c:forEach items="${STUDENTS }" var="student"  varStatus="status">
	   	 	<tr>
		      <td>${status.count }</td>      
		      <td>${CLASSDETAIL.cname }</td>      
		      <td>${student.elect[0].sid }</td> 
		      <td>${student.s_name }</td>   
		      <td>${CLASSDETAIL.credit }</td> 
	      <c:if test="${student.elect[0].score eq  0.0 }">
	      	 <td>未定义</td>   
	      </c:if>   
	      <c:if test="${student.elect[0].score ne  0.0 }">
	      	 <td>${student.elect[0].score }</td>   
	      </c:if>  
		      <td>
			      <div class="button-group">
			      <a style="cursor: pointer;" class="button border-main" onclick="del(${student.elect[0].sid },${CLASSDETAIL.cid })"><span class="icon-edit"></span>上传/更改成绩</a>
			      </div>
		      </td> 
	    	</tr> 
		 </c:forEach> 
  </table>
</div>
</body>
<script>
 function del(sid,cid,cname){
	 console.log(cid)
 		location.href="${pageContext.request.contextPath }/updateStudentScore.action?sid="+sid+"&cid="+cid;
 }
</script> 
</html>