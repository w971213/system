package com.school.test;




import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.school.dao.StudentMapper;
import com.school.dao.TeacherMapper;
import com.school.vo.Course;
import com.school.vo.User;



public class TestSchool {
	
public static void main(String[] args) {
	/*ApplicationContext ac=new ClassPathXmlApplicationContext("Spring.xml");
	StudentMapper sm=(StudentMapper)ac.getBean("studentMapper");
	TeacherMapper tm=(TeacherMapper)ac.getBean("teacherMapper");
	StudentImpl stu =new StudentImpl();
	List<Teacher> teachers=sm.findElectCour("15110101000");
	System.out.println(teachers);
	Course c = sm.findCourByCid(105);
	System.out.println(c);
	System.out.println(tm.findTeacherById("1006"));*/
	User user =new User();
	float num=5.0f;
	user.setScore(-num);
	System.out.println(user.getScore());
}


}
