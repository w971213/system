package com.school.dao;

import java.util.List;

import com.school.vo.Admin;
import com.school.vo.Course;
import com.school.vo.Student;
import com.school.vo.Teacher;
import com.school.vo.User;

public interface AdminMapper {
	
	public Admin findAdminById(String aid);
	public List<Student> findAllStudent(Student student);
	public List<Teacher> findAllTeacher(Teacher teacher);
	public int addStudent(Student student);
	public int addTeacher(Teacher teacher);
	public int deleteStudent(User user); 
	public int deleteTeacher(User user); 
	public List<Course> findCourseBytid(User user);
	public int updateStudent(Student student);
	public int updateTeacher(Teacher teacher);
	public Student findStuById(User user);
	public Teacher findTeaById(User user);
}
