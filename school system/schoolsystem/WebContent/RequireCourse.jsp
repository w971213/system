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
      <ul class="search" style="padding-left:10px;">
        <li><button type="button" onclick="sort(1)"  class="button border-green" id="checkall"><span class="icon-check"></span>排序</button></li>
        <li>
          <input type="text" placeholder="请输入搜索课程关键字" id="keyword"  class="input" style="width:245px; line-height:17px;display:inline-block" />
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="del(3)" > 搜索</a></li>
      </ul>
    </div>
  <table class="table table-hover text-center">
    <tr>
      <th>序号</th> 
      <th width="5%">编号</th>     
      <th>课程名字</th>  
      <th>授课老师</th>  
      <th>课程学分</th>
      <th>课堂人数</th>
    </tr>
   <c:forEach items="${ReqCour }" var="ReqCour" begin="${Begin }" end="${End }" varStatus="status">
   	<c:forEach items="${ReqCour.course }" var="course" varStatus="status1">
	   	 <tr>
	   	 	  <td>${status.count + Begin}</td> 
		      <td>${course.cid }</td>      
		      <td>${course.cname }</td> 
		      <td>${ReqCour.tname }</td>   
		      <td>${course.credit }</td>      
		      <td>${course.cmax }</td>  
	    </tr> 
	 </c:forEach> 
   </c:forEach>
   <tr>
 	<td colspan="6">   
 		<div class="pagelist" >
	 		<c:if test="${CurrNum eq 1 }">
	 			<span class="current">首页</span>
	 			<a >上一页</a>
	 		</c:if>
	 		<c:if test="${CurrNum ne 1 }">
	 			<a href="${pageContext.request.contextPath }/requireCourse.action?pageNum=1">首页</a>
 				<a href="${pageContext.request.contextPath }/requireCourse.action?pageNum=${CurrNum-1 }">上一页</a> 
	 		</c:if>
 				<a >${CurrNum }</a>
 				<a >共${PageNum }页</a>
 			<c:if test="${PageNum ne CurrNum  }">
	 			<a href="${pageContext.request.contextPath }/requireCourse.action?pageNum=${CurrNum+1 }" >下一页</a>
 				<a href="${pageContext.request.contextPath }/requireCourse.action?pageNum=${PageNum}">末页</a>
	 		</c:if>	
 			<c:if test="${PageNum eq CurrNum  }">
	 			<a>下一页</a>
	 			<span class="current">末页</span>
	 		</c:if>	
 		</div>
 		</td>	
   </tr>
  </table>
</div>
<script>

function sort(id){
	console.log(id)
	location.href="${pageContext.request.contextPath }/sortRequireCourse.action?cid="+id;
}

 function del(id){
	 console.log(id);
	var keywords= document.getElementById("keyword")
	 console.log(keywords.value);
 		location.href="${pageContext.request.contextPath }/requireCourse.action?keywords="+keywords.value; 
 } 
</script> 
</body></html>