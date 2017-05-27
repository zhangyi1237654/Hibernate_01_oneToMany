package com.zy.test;

import java.util.Set;

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
/*单向一对多关系(班级--->学生)
 * 
 */
public class test01 {
	
	//将学生添加到班级
	@Test
	public void add(){
		Grade g = new Grade("一班","中山一班");
		Student stu1 = new Student("张三","男");
		Student stu2 = new Student("李四","女");
		//建立关联关系
		g.getStudents().add(stu1);
		g.getStudents().add(stu2);
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(g);
		session.save(stu1);
		session.save(stu2);
		tx.commit();
		HibernateUtil.closeSession(session);
	}
	
	//查询班级中包含的学生
	@Test
	public void findStudentByGrade(){
		Session session = HibernateUtil.getSession();
		Grade grade = session.get(Grade.class, 1);
		System.out.println(grade.getGname()+","+grade.getGdesc());
		
		Set<Student> students = grade.getStudents();
		students.forEach(t->System.out.println(t.getSname()+","+t.getSex()));
	}
	
	//修改学生信息
	@Test
	public void update01(){
		Grade grade = new Grade("二班","中山二班");
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Student stu = session.get(Student.class, 1);
		grade.getStudents().add(stu);
		session.save(grade);
		tx.commit();
		HibernateUtil.closeSession(session);
	}
	
	//删除学生信息
	@Test
	public void delete(){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Student stu = session.get(Student.class, 2);
		session.delete(stu);
		tx.commit();
		HibernateUtil.closeSession(session);
	}
	
	
}
