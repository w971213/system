package com.school.cervice;

import java.util.List;


import com.school.vo.Student;
import com.school.vo.Teacher;
import com.school.vo.User;

public interface AdminInteface {
	public boolean findAdminById(User user);
	public List<Student> findAllStudent(Student student);
	public List<Teacher> findAllTeacher(Teacher teacher);
	public boolean addStudent(Student student);
	public boolean addTeacher(Teacher teacher);
	public boolean deleteStudent(User user); 
	public boolean deleteTeacher(User user); 
	public boolean updateStudent(Student student);
	public boolean updateTeacher(Teacher teacher);
	public Student findStuById(User user);
	public Teacher findTeaById(User user);
}
