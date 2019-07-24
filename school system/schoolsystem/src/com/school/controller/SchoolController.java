package com.school.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.school.cervice.StudentInteface;
import com.school.util.Method;
import com.school.vo.Course;
import com.school.vo.Teacher;





@Controller
public class SchoolController {
	@Autowired
	private StudentInteface studentImpl;
	static List<Teacher> reqCour;
	static List<Teacher> eleCour;
	
	
	
	
	
	//必修课程展示
		@RequestMapping("/requireCourse.action")
		public void requireCourse(String keywords,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("***ItemController*****requireCourse****");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			String kw="%"+keywords+"%";
			Course cour=new Course() ;
			cour.setCname(keywords);
			cour.setCnote(kw);
			if(reqCour==null||keywords!=null) {
				this.reqCour=studentImpl.showRequireCourse(cour);
			}
			request.getSession().setAttribute("ReqCour",reqCour);
			String pages=request.getParameter("pageNum");
			int count=reqCour.size();  //获取所有数据
			Method.page(pages,count,5,request);
			out.print("<script>location.href='RequireCourse.jsp';</script>");
			
		}
		//必修和选修排序
		@RequestMapping("/sortRequireCourse.action")
		public void sortRequireCourse(String cid,HttpServletRequest request, HttpServletResponse response) throws IOException {
			System.out.println("***ItemController*****sortRequireCourse****");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			if(cid.equals("1")) { //必修排序
				Collections.reverse(reqCour);
				request.getSession().setAttribute("ReqCour",reqCour);
				out.print("<script>location.href='RequireCourse.jsp';</script>");
			}else if(cid.equals("2")) { //选修
				Collections.reverse(eleCour);
				request.getSession().setAttribute("EleCour",eleCour);
				out.print("<script>location.href='electivecourse.jsp';</script>");
			}
		}
		
		//选修课程展示
		@RequestMapping("/electiveCourse.action")
		public void electiveCourse(String cid,String keywords,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("***ItemController*****electiveCourse****");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset:UTF-8");
			PrintWriter out=response.getWriter();
			String kw="%"+keywords+"%";
			Course cour =new Course();
			cour.setCname(keywords);
			cour.setCnote(kw);
			cour.setCtid(cid);
			if(eleCour==null||keywords!=null) {
				this.eleCour=studentImpl.showElectiveCourse(cour);
			}
			System.out.println(eleCour);
			String pages=request.getParameter("pageNum");
			int count=eleCour.size();  //获取所有数据
			Method.page(pages,count,4,request);
			request.getSession().setAttribute("EleCour",eleCour);
			out.print("<script>location.href='electivecourse.jsp';</script>");
		}
}