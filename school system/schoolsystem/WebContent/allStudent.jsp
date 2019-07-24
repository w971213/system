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
    <title>学生信息</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>  
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong class="icon-reorder">展示学生</strong></div>
  	<div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li><button type="button" onclick="sort()"  class="button border-green" id="checkall"><span class="icon-check"></span>排序</button></li>
        <li>
          <input type="text" placeholder="请输入搜索名字关键字" id="keyword"  class="input" style="width:245px; line-height:17px;display:inline-block" />
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="searchStudent()" > 搜索</a></li>
      </ul>
    </div>
  	<div class="padding border-bottom">
    </div>
  <table class="table table-hover text-center">
    <tr>
      <th width="5%">序号</th>     
      <th>学号</th>
      <th>密码</th>
      <th>名字</th>
      <th>性别</th>
      <th>年龄</th>
      <th>学分</th>  
      <th>地址</th>  
      <th>电话</th>  
      <th>邮箱</th>  
       <th width="250">操作</th>    
    </tr>
	   <c:forEach items="${STUDENTS }" var="student" begin="${Begin }" end="${End }" varStatus="status">
	   	 	<tr>
		      <td>${status.count }</td>      
		      <td>${student.sid }</td>      
		      <td>${student.s_key }</td>      
		      <td>${student.s_name }</td> 
		      <td>${student.s_sex }</td>   
		      <td>${student.s_age }</td>   
		      <td>${student.s_credit }</td> 
		      <td>${student.s_address }</td> 
		      <td>${student.s_phone}</td> 
		      <c:if test="${student.s_email eq  '' }">
		      	 <td>未填写</td> 
		      </c:if>
		       <c:if test="${student.s_email ne  ''  }">
		      	 <td>${student.s_email}</td>  
		      </c:if>
		      <td>
			      <div class="button-group">
			      <a style="cursor: pointer;" class="button border-main" onclick="update(${student.sid })"><span class="icon-edit"></span>修改资料</a>
			      <a style="cursor: pointer;" class="button border-main" onclick="del(${student.sid })"><span class="icon-edit"></span>删除信息</a>
			      </div>
		      </td> 
	    	</tr> 
		 </c:forEach> 
		 <tr>
 	<td colspan="10">   
 		<div class="pagelist" >
	 		<c:if test="${CurrNum eq 1 }">
	 			<span class="current">首页</span>
	 			<a >上一页</a>
	 		</c:if>
	 		<c:if test="${CurrNum ne 1 }">
	 			<a href="${pageContext.request.contextPath }/showAllStudent.action?pageNum=1">首页</a>
 				<a href="${pageContext.request.contextPath }/showAllStudent.action?pageNum=${CurrNum-1 }">上一页</a> 
	 		</c:if>
 				<a >${CurrNum }</a>
 				<a >共${PageNum }页</a>
 			<c:if test="${PageNum ne CurrNum  }">
	 			<a href="${pageContext.request.contextPath }/showAllStudent.action?pageNum=${CurrNum+1 }" >下一页</a>
 				<a href="${pageContext.request.contextPath }/showAllStudent.action?pageNum=${PageNum}">末页</a>
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
</body>
<script>
	function update(sid){
		 console.log(sid)
			location.href="${pageContext.request.contextPath }/updateStudentInfo.action?sid="+sid;
	}
	function sort(){
		location.href="${pageContext.request.contextPath }/sortStudent.action?";
	}
	function searchStudent(){
		var keywords= document.getElementById("keyword")
		 console.log(keywords.value);
			location.href="${pageContext.request.contextPath }/showAllStudent.action?keywords="+keywords.value; 
	} 
	 function del(sid){
		 console.log(sid)
	 		location.href="${pageContext.request.contextPath }/deleteStudent.action?sid="+sid;
	 }
</script> 
</html>