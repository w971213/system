package com.school.dao;

import java.util.List;

import com.school.vo.Course;
import com.school.vo.Elect;
import com.school.vo.Student;
import com.school.vo.Teacher;

public interface StudentMapper {

	/*根据id查学生*/
	public Student findStuById(String sid);
	public int updatePassword(Student stu);
	public List<Teacher> showRequireCourse(Course cour);
	public List<Teacher> showElectiveCourse(Course cour);
	public List<Teacher> findElectCour(String sid);
	public int addElectCour(Elect ele);
	public int deleteCourse(Elect ele);
	public Course findCourByCid(int cid);
	public int updateCoursePre(int cid);
	public int updateCoursePre1(int cid);
}
