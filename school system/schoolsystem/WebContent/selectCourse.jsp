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
  <table class="table table-hover text-center">
    <tr>
      <th width="5%"></th>   
      <th width="5%">序号</th>   
      <th width="5%">编号</th>     
      <th>课程名字</th>  
      <th>授课老师</th>  
      <th>课程学分</th>
      <th>限选人数</th> 
      <th>已选人数</th>     
     <th width="250">操作</th> 
    </tr>
	   <c:forEach items="${SelCour }" var="selCour" begin="${Begin }" end="${End }" varStatus="status">
	   	<c:forEach items="${selCour.course }" var="course">
	   	 	<tr>
	   	 	  <td></td> 
	   	 	  <td>${status.count + Begin }</td> 
		      <td>${course.cid }</td>      
		      <td>${course.cname }</td> 
		      <td>${selCour.tname }</td>   
		      <td>${course.credit }</td>      
		      <td>${course.cmax }</td>
		      <td>${course.cprecid}</td>
		      <td>
			      <div class="button-group">
			      	  <a type="button" style="cursor: pointer;"  class="button border-main" onclick="return del(2,${course.cid })" ><span class=" icon-edit"></span>删除</a>
			      </div>
		      </td>   
	    	</tr> 
		 </c:forEach> 
	    </c:forEach> 
  </table>
</div>
 <script>
 function del(id,cid){
	 console.log(cid);
 	if(confirm("您确定要删除吗?")){
 		location.href="${pageContext.request.contextPath }/deleteCourse.action?cid="+cid;
 	}
 }
</script> 
</body></html>