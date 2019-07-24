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

import com.school.cervice.TeacherInteface;
import com.school.util.Method;
import com.school.vo.Course;
import com.school.vo.Elect;
import com.school.vo.Student;
import com.school.vo.Teacher;
import com.school.vo.User;

@Controller
public class TeacherController {
	@Autowired
	private TeacherInteface teacherImpl;
	
	//教师修改密码
	@RequestMapping("/setTcPassword.action")
	public void setPassword(Teacher tea,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset:UTF-8");
		PrintWriter out=response.getWriter();
		String oldpass=request.getParameter("mpass");
		String newpass=request.getParameter("newpass");
		Teacher teacher=(Teacher)request.getSession().getAttribute("TEACHER");
		if(teacher!=null&&oldpass.equals(teacher.getTkey())) {
			System.out.println("账号密码准备修改！");
			tea.setTid(teacher.getTid());
			tea.setTkey(newpass);
			boolean flag=teacherImpl.updatePassword(tea);
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
		//本教师选修课程展示
		@RequestMapping("/electCourse.action")
		public void electiveCourse(User user,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("***ItemController*****electiveCourse****");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset:UTF-8");
			PrintWriter out=response.getWriter();
			Teacher teacher=(Teacher)request.getSession().getAttribute("TEACHER");
			user.setName(teacher.getTid());
			List<Teacher> teach=teacherImpl.showRequireCourse(user);
			request.getSession().setAttribute("Teacher",teach);
			out.print("<script>location.href='TelectCourse.jsp';</script>");
		}
		
		@RequestMapping("/addEleCour.action") //教师开课系统
		public void addEleCour(Course cour,User user,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("***ItemController*****addEleCour****");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset:UTF-8");
			PrintWriter out=response.getWriter();
			Teacher teacher=(Teacher)request.getSession().getAttribute("TEACHER");
			cour.setCname(user.getName());
			cour.setCmax(Integer.parseInt(user.getCmax()));
			cour.setCnote(user.getCnote());
			cour.setCredit(Float.parseFloat(user.getCredit()));
			cour.setCtype(user.getCtype());
			cour.setCprecid(0);
			if(teacher.getTid().equals(user.getPassword())) {
				cour.setCtid(teacher.getTid());
			}else {
				out.print("<script>alert('教师编号错误！！');location.href='TelectCourse.jsp';</script>");
				return;
			}
			boolean flag=teacherImpl.addEleCour(cour);
			if(flag) {
				out.print("<script>alert('开课成功！！');location.href='electCourse.action';</script>");
				return;
			}else {
				out.print("<script>alert('教师编号错误！！');location.href='TelectCourse.jsp';</script>");
				return;
			}
		}
		//本教师选修课程展示
				@RequestMapping("/showStudentInCourse.action")//展示选该课的学生
				public void detilCour(String cid,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					System.out.println("***ItemController*****detilCour****");
					request.setCharacterEncoding("UTF-8");
					response.setContentType("text/html;charset:UTF-8");
					PrintWriter out=response.getWriter();
					
					List<Student> students=teacherImpl.showStudentInCourse(Integer.parseInt(cid));
					request.getSession().setAttribute("STUDENTS", students);
					
					Course course=teacherImpl.findCourseById(Integer.parseInt(cid));
					
					request.getSession().setAttribute("CLASSDETAIL",course);
					out.print("<script>location.href='studentDetail.jsp';</script>");
					
				}
				@RequestMapping("/updateStudentScore.action")//更改学生学分
				public void updateStudentScore(Elect elect,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					System.out.println("***ItemController*****updateStudentScore****");
					request.setCharacterEncoding("UTF-8");
					response.setContentType("text/html;charset:UTF-8");
					PrintWriter out=response.getWriter();
					request.getSession().setAttribute("ELECT", elect);
					out.print("<script>location.href='updatescore.jsp';</script>");
				}	
				
				@RequestMapping("/updateStudentScore1.action")//
				public void updateStudentScore1(Student stu,Elect elect,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					System.out.println("***ItemController*****updateStudentScore****");
					request.setCharacterEncoding("UTF-8");
					response.setContentType("text/html;charset:UTF-8");
					PrintWriter out=response.getWriter();
					Elect ele=(Elect)request.getSession().getAttribute("ELECT");
					Elect elect1=teacherImpl.findSccoreBySidAndCid(ele);
					Float oldScore=elect1.getScore();
					float newScore =elect.getScore();  //jsp传过来的成绩
					Course cour=(Course)request.getSession().getAttribute("CLASSDETAIL");
					ele.setScore(elect.getScore()); //准备更改的成绩
					boolean flag=teacherImpl.writeScore(ele);//将成绩写入数据库
					if(oldScore<60&&newScore<60||oldScore>=60&&newScore>=60) {
						if(flag) {
						out.print("<script>alert('更新成绩成功！！');location.href='showStudentInCourse.action?cid="+ele.getCid()+"';</script>");
						return;
						}else {
							out.print("<script>alert('更新成绩失败！！');location.href='showStudentInCourse.action?cid="+ele.getCid()+"';</script>");
							return;
						}
					}else if(oldScore<60&&newScore>=60){
						if(flag) {
							stu.setSid(ele.getSid());
							stu.setS_credit(cour.getCredit());
							boolean flag1=teacherImpl.updateStudentCredit(stu);
							if(flag1) {
								out.print("<script>alert('更新成绩成功！！');location.href='showStudentInCourse.action?cid="+ele.getCid()+"';</script>");
								return;
							}else {
								out.print("<script>alert('更新成绩成功，更新学分失败！！');location.href='showStudentInCourse.action?cid="+ele.getCid()+"';</script>");
								return;
							}
						}else {
							out.print("<script>alert('更新成绩失败！！');location.href='showStudentInCourse.action?cid="+ele.getCid()+"';</script>");
							return;
						}
					}else if(oldScore>=60&&newScore<60) {
						if(flag) {
							stu.setSid(ele.getSid());
							stu.setS_credit(-cour.getCredit());
							boolean flag2=teacherImpl.updateStudentCredit(stu);
							if(flag2) {
								out.print("<script>alert('更新成绩成功！！');location.href='showStudentInCourse.action?cid="+ele.getCid()+"';</script>");
								return;
							}else {
								out.print("<script>alert('更新成绩成功，更新学分失败！！');location.href='showStudentInCourse.action?cid="+ele.getCid()+"';</script>");
								return;
							}
						}else {
							out.print("<script>alert('更新成绩失败！！');location.href='showStudentInCourse.action?cid="+ele.getCid()+"';</script>");
							return;
						}
					}
				}	
}
