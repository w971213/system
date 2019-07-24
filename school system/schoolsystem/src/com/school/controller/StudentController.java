package com.school.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.school.cervice.AdminInteface;
import com.school.cervice.StudentInteface;
import com.school.cervice.TeacherInteface;
import com.school.util.Method;
import com.school.vo.Course;
import com.school.vo.Elect;
import com.school.vo.Student;
import com.school.vo.Teacher;
import com.school.vo.User;

@Controller
public class StudentController {
	
	@Autowired
	private StudentInteface studentImpl;
	@Autowired
	private TeacherInteface teacherImpl;
	@Autowired
	private AdminInteface adminImpl;
	private Student student;
	
	@RequestMapping("/queryLogin.action")//登录功能
	public void queryItem(User user,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String identify=request.getParameter("mynum");
		if(identify.equals("1")) {
			student(user,request,out);
		}else if(identify.equals("2")) {
			Teacher teacher=teacherImpl.findTeacherById(user.getName());
			if(teacher!=null&&user.getPassword().equals(teacher.getTkey())) {
				out.print("<script>alert('教师登录成功！');location.href='Tindex.jsp';</script>");
				request.getSession().setAttribute("TEACHER",teacher);
				return;
			}else {
				out.print("<script>alert('账号或者密码错误！');location.href='login.jsp';</script>");
				return;
			}
		}else if(identify.equals("3")) {
			boolean flag=adminImpl.findAdminById(user);
			if(flag) {
				out.print("<script>alert('管理员登录成功！');location.href='Aindex.jsp';</script>");
			}else{
				out.print("<script>alert('账号或者密码错误！');location.href='login.jsp';</script>");
			}
		}
	}
	//学生登录平台
	public void student(User user,HttpServletRequest request,PrintWriter out) {
		this.student=studentImpl.findStuById(user.getName());
		System.out.println(student);
		if(student!=null&&user.getPassword().equals(student.getS_key())) {
			System.out.println("账号密码正确，登录成功！");
			request.getSession().setAttribute("STUDENT",student);
			out.print("<script>alert('登录成功！');location.href='index.jsp';</script>");
			return;
		}else {
			out.print("<script>alert('账号或者密码错误！');location.href='login.jsp';</script>");
		}
	}
	//修改密码功能
	@RequestMapping("/setPassword.action")
	public void setPassword(Student stu,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***ItemController*****setPassword****");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset:UTF-8");
		PrintWriter out=response.getWriter();
		String oldpass=request.getParameter("mpass");
		String newpass=request.getParameter("newpass");
		System.out.println(student.getS_key());
		if(student!=null&&oldpass.equals(student.getS_key())) {
			System.out.println("账号密码准备修改！");
			stu.setSid(student.getSid());
			stu.setS_key(newpass);
			boolean flag=studentImpl.updatePassword(stu);
			if(flag) {
				out.print("<script>alert('修改成功！请重新登录！');parent.location.href='login.jsp';</script>");
				return;	
			}else {
				out.print("<script>alert('修改失败！');location.href='pass.jsp';</script>");
			}
		}else {
				out.print("<script>alert('原密码错误！');location.href='pass.jsp';</script>");
		}
	}
	@RequestMapping("/chouseCourse.action")  //选课
	public void chouseCourse(Elect ele,String cid,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset:UTF-8");
		PrintWriter out=response.getWriter();
		Student studet=(Student)request.getSession().getAttribute("STUDENT");
		List<Teacher> selectCourses =studentImpl.findElectCour(studet.getSid());
		System.out.println(cid);
		System.out.println(selectCourses);
		System.out.println(selectCourses.size());
		if(selectCourses.size()<2){
			Course course=studentImpl.findCourByCid(Integer.parseInt(cid));//获取本节课程的信息
			System.out.println(course);
			if(course.getCprecid()<course.getCmax()) {                     //当前人数小于限选人数
				ele.setSid(student.getSid());
				ele.setCid(Integer.parseInt(cid));
				for(Teacher selectCourse:selectCourses ) {
					for(Course cour:selectCourse.getCourse()) {
						if(cour.getCid()==Integer.parseInt(cid)) {
							out.print("<script>alert('本节课已选过');location.href='electivecourse.jsp'</script>");
							return;
						}
					}
				}
				boolean flag=studentImpl.addElectCour(ele);
				if(flag) {
					studentImpl.updateCoursePre(Integer.parseInt(cid));
					SchoolController.eleCour=null;
					out.print("<script>alert('课程选择成功！');location.href='electiveCourse.action'</script>");	
				}
			}else {
				out.print("<script>alert('对不起 ，本课选课结束！');location.href='electivecourse.jsp'</script>");
				return;	
			}
		}else {
			out.print("<script>alert('您的选修课已选！');location.href='electivecourse.jsp'</script>");
			return;
		}
	}
	@RequestMapping("/showSelCour.action")//展示已选课程
	public ModelAndView showSelCour(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***ItemController*****showSelCour****");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Teacher> selectCourses =studentImpl.findElectCour(student.getSid());
		ModelAndView mav = new ModelAndView();
		mav.addObject("SelCour",selectCourses);
		mav.setViewName("selectCourse.jsp");
		return mav;
	}
	@RequestMapping("/deleteCourse.action")//删除选修课程
	public void deleteCourse(Elect ele,String cid,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***ItemController*****showSelCour****");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		ele.setSid(student.getSid());
		ele.setCid(Integer.parseInt(cid));
		boolean flag=studentImpl.deleteCourse(ele);
		if(flag) {
			SchoolController.eleCour=null;
			studentImpl.updateCoursePre1(Integer.parseInt(cid));
			out.print("<script>alert('删除成功!');location.href='showSelCour.action'</script>");
		}else {
			out.print("<script>alert('删除失败!');location.href='showSelCour.action'</script>");
		}
	}
}
