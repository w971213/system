package com.school.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.cervice.StudentInteface;
import com.school.dao.StudentMapper;
import com.school.vo.Course;
import com.school.vo.Elect;
import com.school.vo.Student;
import com.school.vo.Teacher;
@Service
public class StudentImpl implements StudentInteface {
	@Autowired
	private StudentMapper studentMapper;
	@Override
	public Student findStuById(String sid) {
		return studentMapper.findStuById(sid);
	}
	@Override
	public boolean updatePassword(Student stu) {
		int num=studentMapper.updatePassword(stu);
		return num==1?true:false;
	}
	@Override
	public List<Teacher> showRequireCourse(Course cour) {
		return studentMapper.showRequireCourse(cour);
	}
	@Override
	public List<Teacher> showElectiveCourse(Course cour) {
		return studentMapper.showElectiveCourse(cour);
	}
	@Override
	public List<Teacher> findElectCour(String sid) {
		return  studentMapper.findElectCour(sid);
	}
	@Override
	public boolean addElectCour(Elect ele) {
			int num=studentMapper.addElectCour(ele);
			return num==1?true:false;
	}
	@Override
	public boolean deleteCourse(Elect ele) {
		int num=studentMapper.deleteCourse(ele);
		return num==1?true:false;
	}
	@Override
	public Course findCourByCid(int cid) {
		System.out.println("我进来了"+cid);
		System.out.println(studentMapper.findCourByCid(cid));
		return studentMapper.findCourByCid(cid);
	}
	@Override
	public boolean updateCoursePre(int cid) {
		int num=studentMapper.updateCoursePre(cid);
		return num==1?true:false;
	}
	@Override
	public boolean updateCoursePre1(int cid) {
		int num=studentMapper.updateCoursePre1(cid);
		return num==1?true:false;
	}

}
