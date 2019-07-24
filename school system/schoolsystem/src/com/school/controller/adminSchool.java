package com.school.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.school.cervice.AdminInteface;
import com.school.util.Method;
import com.school.vo.Student;
import com.school.vo.Teacher;
import com.school.vo.User;
@Controller
public class adminSchool {
	@Autowired
	private AdminInteface adminInteface;
	private List<Student> students;
	private List<Teacher> teachers;
	
	
	
	
	@RequestMapping("/showAllStudent.action")
	public void showAllStudent(Student student,String keywords,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***ItemController*****showSelCour****");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String kw="%"+keywords+"%";
		student.setS_name(keywords);
		student.setSid(kw);
		if(students==null||keywords!=null) {
			this.students=adminInteface.findAllStudent(student);
		}
		String pages=request.getParameter("pageNum");
		int count=students.size();  //获取所有数据
		Method.page(pages,count,4,request);
		request.getSession().setAttribute("STUDENTS",students);
		out.print("<script>location.href='allStudent.jsp';</script>");
	}
	
	@RequestMapping("/showAllTeacher.action")
	public void showAllTeacher(Teacher teacher,String keywords,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***ItemController*****showSelCour****");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		System.out.println(keywords);
		String kw="%"+keywords+"%";
		teacher.setTname(keywords);
		teacher.setTid(kw);
		if(teachers==null||keywords!=null) {
			this.teachers=adminInteface.findAllTeacher(teacher);	
		}
		String pages=request.getParameter("pageNum");
		int count=teachers.size();  //获取所有数据
		Method.page(pages,count,4,request);
		request.getSession().setAttribute("TEACHERS", teachers);
		out.print("<script>location.href='allTeacher.jsp';</script>");
		
	}
	@RequestMapping("/addTeacher.action")
	public void addTeacher(Teacher teacher,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***ItemController*****showSelCour****");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		if(teacher.getTsex().equals("1")) {
			teacher.setTsex("男");
		}else if(teacher.getTsex().equals("1")) {
			teacher.setTsex("女");
		}
		if(teacher.getTmail().equals("")) {
			teacher.setTmail(null);
		}
		System.out.println(teacher);
		boolean flag=adminInteface.addTeacher(teacher);
		if(flag) {
			this.teachers=null;
		   out.print("<script>alert('添加教师成功！');location.href='addTeacher.jsp';</script>");
		   return;
		}else {
		   out.print("<script>alert('添加教师失败！');location.href='addTeacher.jsp';</script>");
		   return;
		}

	}
	@RequestMapping("/addStudent.action")
	public void addStudent(Student student,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("***ItemController*****showSelCour****");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		if(student.getS_sex().equals("1")) {
			student.setS_sex("男");
		}else if(student.getS_sex().equals("1")) {
			student.setS_sex("女");
		}
		if(student.getS_email().equals("")) {
			student.setS_email("null");
		}
		boolean flag=adminInteface.addStudent(student);
		if(flag) {
			this.students=null;
		   out.print("<script>alert('添加学生成功！');location.href='addStudent.jsp';</script>");
		   return;
		}else {
		   out.print("<script>alert('添加学生失败！');location.href='addStudent.jsp';</script>");
		   return;
		}
	}
	
	@RequestMapping("/sortTeacher.action")  //排序老师
	public void sortTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("***ItemController*****sortTeacher****");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
			Collections.reverse(teachers);
			request.getSession().setAttribute("TEACHERS",teachers);
			out.print("<script>location.href='allTeacher.jsp';</script>");
		
	}
	@RequestMapping("/sortStudent.action")  //排序学生
	public void sortStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("***ItemController*****sortStudent****");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		System.out.println(students);
		Collections.reverse(students);
		request.getSession().setAttribute("STUDENTS",students);
		out.print("<script>location.href='allStudent.jsp';</script>");
	}
	@RequestMapping("/deleteStudent.action")  //删除学生
	public void deleteStudent(User user,HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("***ItemController*****sortStudent****");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		boolean flag=adminInteface.deleteStudent(user);
		if(flag) {
			this.students=null;
			out.print("<script>alert('删除成功！');location.href='showAllStudent.action';</script>");
		}else {
			out.print("<script>alert('删除失败！');location.href='showAllStudent.action';</script>");
		}
	}
	
	@RequestMapping("/deleteTeacher.action")  //删除老师
	public void deleteTeacher(User user,HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("***ItemController*****sortStudent****");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		boolean flag=adminInteface.deleteTeacher(user);
		if(flag) {
			this.teachers=null;
			out.print("<script>alert('删除成功！');location.href='showAllTeacher.action';</script>");
		}else {
			out.print("<script>alert('删除失败，老子正在上课！');location.href='showAllTeacher.action';</script>");
		}
		
	}
	@RequestMapping("/updateStudentInfo.action")  //查找要更改的学生信息
	public void updateStudentInfo(User user,HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("***ItemController*****sortStudent****");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		Student student=adminInteface.findStuById(user);
		request.getSession().setAttribute("Student", student);
		out.print("<script>location.href='updateStudenInfo.jsp';</script>");
	}
	
	@RequestMapping("/updateStudent.action")  //更改学生信息
	public void updateStudent(Student stu,Student student,HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("***ItemController*****updateStudent****");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		if(student.getS_sex().equals("1")) {
			student.setS_sex("男");
		}else {
			student.setS_sex("女");
		}
		boolean flag=adminInteface.updateStudent(student);
		if(flag) {
			this.students=null;
			System.out.println(students);
			out.print("<script>alert('修改成功！');location.href='showAllStudent.action';</script>");
		}else {
			out.print("<script>alert('修改失败！');location.href='showAllStudent.action';</script>");
		}
	}
	
	@RequestMapping("/updateTeacherInfo.action")  //查找要更改的老师信息
	public void updateTeacherInfo(User user,HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("***ItemController*****sortStudent****");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		Teacher teacher=adminInteface.findTeaById(user);
		System.out.println(teacher);
		request.getSession().setAttribute("Teacher", teacher);
		out.print("<script>location.href='updateTeacherInfo.jsp';</script>");
	}
	
	
	
	@RequestMapping("/updateTeacher.action")  //更改老师信息
	public void updateTeacher(Teacher teacher,Student student,HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("***ItemController*****updateStudent****");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		if(teacher.getTsex().equals("1")) {
			teacher.setTsex("男");
		}else {
			teacher.setTsex("女");
		}
		boolean flag=adminInteface.updateTeacher(teacher);
		if(flag) {
			this.teachers=null;
			out.print("<script>alert('修改成功！');location.href='showAllTeacher.action';</script>");
		}else {
			out.print("<script>alert('修改失败！');location.href='showAllTeacher.action';</script>");
		}
	}
}
