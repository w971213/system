package com.school.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.cervice.AdminInteface;
import com.school.dao.AdminMapper;
import com.school.dao.TeacherMapper;
import com.school.vo.Admin;
import com.school.vo.Course;
import com.school.vo.Student;
import com.school.vo.Teacher;
import com.school.vo.User;

@Service
public class AdminImpl implements AdminInteface {
	
	@Autowired
	private AdminMapper adminMapper;
	
	
	@Override
	public boolean findAdminById(User user) {
		Admin admin=adminMapper.findAdminById(user.getName());
		if(admin!=null&&admin.getA_key().equals(user.getPassword())) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Student> findAllStudent(Student student) {
		return adminMapper.findAllStudent(student);
	}

	@Override
	public List<Teacher> findAllTeacher(Teacher teacher) {
		return adminMapper.findAllTeacher(teacher);
	}

	@Override
	public boolean addStudent(Student student) {
		int num=adminMapper.addStudent(student);
		return num==1?true:false;
	}

	@Override
	public boolean addTeacher(Teacher teacher) {
		int num=adminMapper.addTeacher(teacher);
		return num==1?true:false;
	}

	@Override
	public boolean deleteStudent(User user) {
		int num=adminMapper.deleteStudent(user);
		return num==1?true:false;
	}

	@Override
	public boolean deleteTeacher(User user) {
		List<Course> cour=adminMapper.findCourseBytid(user);
		if(cour==null) {
			int num=adminMapper.deleteTeacher(user);
			return num==1?true:false;
		}
		return false;
	}

	@Override
	public boolean updateStudent(Student student) {
		System.out.println("adminImpl"+student);
		System.out.println("adminImpl***id"+student.getSid());
		student.setSid(student.getSid());
		int num=adminMapper.updateStudent(student);
		System.out.println("adminImpl"+num);
		return num==1?true:false;
	}

	@Override
	public boolean updateTeacher(Teacher teacher) {
		int num=adminMapper.updateTeacher(teacher);
		return num==1?true:false;
	}

	@Override
	public Student findStuById(User user) {
		return adminMapper.findStuById(user);
	}

	@Override
	public Teacher findTeaById(User user) {
		return adminMapper.findTeaById(user);
	}

	
}
