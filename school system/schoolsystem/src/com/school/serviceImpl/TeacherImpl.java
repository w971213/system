package com.school.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.cervice.TeacherInteface;
import com.school.dao.TeacherMapper;
import com.school.vo.Course;
import com.school.vo.Elect;
import com.school.vo.Student;
import com.school.vo.Teacher;
import com.school.vo.User;
@Service
public class TeacherImpl implements TeacherInteface {
	@Autowired
	private TeacherMapper teacherMapper;
	@Override
	public Teacher findTeacherById(String tid) {
		return teacherMapper.findTeacherById(tid);
	}
	@Override
	public boolean updatePassword(Teacher tea) {
		int num=teacherMapper.updatePassword(tea);
		return num==1?true:false;
	}
	@Override
	public List<Teacher> showRequireCourse(User user) {
		return  teacherMapper.showRequireCourse(user);
	}
	@Override
	public boolean addEleCour(Course cour) {
		int num=teacherMapper.addEleCour(cour);
		return num==1?true:false;
	}
	@Override
	public List<Student> showStudentInCourse(int cid) {
		return teacherMapper.showStudentInCourse(cid);
	}
	@Override
	public boolean writeScore(Elect elect) {
		int num=teacherMapper.writeScore(elect);
		return num==1?true:false;
	}
	@Override
	public Course findCourseById(int cid) {
		return teacherMapper.findCourseById(cid);
	}
	@Override
	public boolean updateStudentCredit(Student stu) {
		int num=teacherMapper.updateStudentCredit(stu);
		return num==1?true:false;
	}
	@Override
	public Elect findSccoreBySidAndCid(Elect elect) {
		return teacherMapper.findSccoreBySidAndCid(elect);
	}
	
	

}
