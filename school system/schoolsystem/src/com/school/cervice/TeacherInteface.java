package com.school.cervice;

import java.util.List;

import com.school.vo.Course;
import com.school.vo.Elect;
import com.school.vo.Student;
import com.school.vo.Teacher;
import com.school.vo.User;

public interface TeacherInteface {
	
	public Teacher findTeacherById(String tid);
	public boolean updatePassword(Teacher tea);
	public List<Teacher> showRequireCourse(User user);
	public boolean addEleCour(Course cour);
	public List<Student> showStudentInCourse(int cid);
	public boolean writeScore(Elect elect);
	public boolean updateStudentCredit(Student stu);
	public Course findCourseById(int cid);
	public Elect findSccoreBySidAndCid(Elect elect);
}
