package com.school.util;

import javax.servlet.http.HttpServletRequest;

public class Method {
	//分页
	public static void page(String pages,int count,int num,HttpServletRequest request) {
  		int pageNum= count%num==0?count/num:(count/num)+1;   //一共多少页
  		int currNum=pages==null?1:Integer.parseInt(pages);  //当前第几页
  		int begin=(currNum-1)*num;   //开始下标
  		int end= currNum==pageNum?count-1:num*currNum-1;//结束下标
  		System.out.println(begin);
  		System.out.println(end);
  		request.getSession().setAttribute("Begin",begin);
  		request.getSession().setAttribute("End",end);
  		request.getSession().setAttribute("PageNum",pageNum);
  		request.getSession().setAttribute("CurrNum",currNum);
	}
}
