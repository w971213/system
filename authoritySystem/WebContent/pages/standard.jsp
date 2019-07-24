<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
 
总 ${page.totalNum} 条&nbsp; 
每页 ${page.pageSize} 条&nbsp; 
总 ${page.pageCount} 页 &nbsp; 
第 ${page.currNo} 页&nbsp; 
  <c:if test="${page.pageCount eq 0}">
	<c:if test="${page.currNo==1}">首页</c:if>
	<c:if test="${page.currNo==1}">上一页</c:if>
	<c:if test="${page.currNo==page.pageCount}">下一页</c:if>
	<c:if test="${page.currNo==page.pageCount}">尾页</c:if>
	第<select >
	<option>1</option>
	 </select>页 
  </c:if>
  <c:if test="${page.pageCount ne 0}">
 		<c:if test="${page.currNo==1}">首页</c:if>
		<c:if test="${page.currNo!=1}"><a href="${pageContext.request.contextPath}/${page.url}?${page.params}&currNo=1&pageSize=${page.pageSize}">首页</a></c:if>
		&nbsp;
		<c:if test="${page.currNo==1}">上一页</c:if>
		<c:if test="${page.currNo!=1}"><a href="${pageContext.request.contextPath}/${page.url}?${page.params}&currNo=${page.currNo-1}&pageSize=${page.pageSize}">上一页</a></c:if>
		&nbsp;
		<c:if test="${page.currNo==page.pageCount}">下一页</c:if>
		<c:if test="${page.currNo!=page.pageCount}"><a href="${pageContext.request.contextPath}/${page.url}?${page.params}&currNo=${page.currNo+1}&pageSize=${page.pageSize}" >下一页</a></c:if>
		&nbsp;
		<c:if test="${page.currNo==page.pageCount}">尾页</c:if>
		<c:if test="${page.currNo!=page.pageCount}"><a href="${pageContext.request.contextPath}/${page.url}?${page.params}&currNo=${page.pageCount}&pageSize=${page.pageSize}">尾页</a></c:if>
		&nbsp;
		第<select onchange="location.href='${pageContext.request.contextPath}/${page.url}?${page.params}&currNo='+this.value+'&pageSize=${page.pageSize}'">
			<c:forEach var="selectvalue" begin="1" end="${page.pageCount}" step="1">
				<option value="${selectvalue}" ${page.currNo eq selectvalue ?"selected='selected'":""}>
					${selectvalue}   
				</option>
			</c:forEach>
		</select>
		页 
  </c:if>
每页
<select onchange="location.href='${pageContext.request.contextPath}/${page.url}?${page.params}&currNo=${page.currNo}&pageSize='+this.value">
	<option value="5" ${page.pageSize eq 5 ?"selected='selected'":""}>5</option>
	<option value="6" ${page.pageSize eq 6 ?"selected='selected'":""}>6</option>
	<option value="7" ${page.pageSize eq 7 ?"selected='selected'":""}>7</option>
	<option value="8" ${page.pageSize eq 8 ?"selected='selected'":""}>8</option>
	<option value="9" ${page.pageSize eq 9 ?"selected='selected'":""}>9</option>
	<option value="10" ${page.pageSize eq 10 ?"selected='selected'":""}>10</option>
</select>
条


