package com.school.cervice;

import java.util.List;

import com.school.vo.Course;
import com.school.vo.Elect;
import com.school.vo.Student;
import com.school.vo.Teacher;

public interface StudentInteface {
	public Student findStuById(String sid);
	public boolean updatePassword(Student stu);
	public List<Teacher> showRequireCourse(Course cour);
	public List<Teacher> showElectiveCourse(Course cour);
	public List<Teacher> findElectCour(String sid);
	public boolean addElectCour(Elect ele);
	public boolean deleteCourse(Elect ele);
	public Course findCourByCid(int cid);
	public boolean updateCoursePre(int cid);
	public boolean updateCoursePre1(int cid);
	
	
}
