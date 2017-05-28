package com.zy.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.zy.entity.Grade;
import com.zy.entity.Student;
import com.zy.util.HibernateUtil;

/**
* @author zy007
* 
*/
public class test02 {
	//添加保存
	@Test
	public void save(){
		Grade grade = new Grade("一班","中山一班");
		Student stu1 = new Student("张三","男");
		Student stu2 = new Student("李四","女");
		//设置关联关系
		grade.getStudents().add(stu1);
		grade.getStudents().add(stu2);
		stu1.setGrade(grade);
		stu2.setGrade(grade);
		
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		session.save(grade);//级联操作
		//session.save(stu1);
		//session.save(stu2);
		tx.commit();
		HibernateUtil.closeSession(session);
	}
	
	//查询学生所在班级的信息
	@Test
	public void findGradeByStudent(){
		
		Session session = HibernateUtil.getSession();
		
		Student stu = session.get(Student.class, 2);
		System.out.println(stu.getSid()+","+stu.getSname()+","+stu.getSex());
		
		Grade grade = stu.getGrade();
		System.out.println(grade.getGid()+","+grade.getGname()+","+grade.getGdesc());
		HibernateUtil.closeSession(session);
		
	}
}
