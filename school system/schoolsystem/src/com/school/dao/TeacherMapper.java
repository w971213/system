package com.school.dao;


import java.util.List;

import com.school.vo.Course;
import com.school.vo.Elect;
import com.school.vo.Student;
import com.school.vo.Teacher;
import com.school.vo.User;

public interface TeacherMapper {
	
	public Teacher findTeacherById(String tid);
	public int updatePassword(Teacher tea);
	public List<Teacher> showRequireCourse(User user);
	public int addEleCour(Course cour);
	public List<Student> showStudentInCourse(int cid);
	public int writeScore(Elect elect);
	public int updateStudentCredit(Student stu);
	public Course findCourseById(int cid);
	public Elect findSccoreBySidAndCid(Elect elect);
}
