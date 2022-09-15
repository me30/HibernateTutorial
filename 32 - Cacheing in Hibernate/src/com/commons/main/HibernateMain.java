package com.commons.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.commons.entity.UserDetail;

public class HibernateMain {

	public static void main(String[] arg) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		for(int i = 1; i<=10; i++) {
			UserDetail user = new UserDetail();
			user.setUserName("Test User_"+i);
			user.setDescription("description_"+i);
			session.save(user);
		}
		
		session.getTransaction().commit();
		session.close();
		
		
		session = sf.openSession();
		session.beginTransaction();

		UserDetail userDetailOne = (UserDetail) session.get(UserDetail.class, 1);
		System.out.println("userId: "+userDetailOne.getUserId()+"		userName: "+userDetailOne.getUserName()+"		description: "+userDetailOne.getDescription());

		UserDetail userDetailTwo = (UserDetail) session.get(UserDetail.class, 1);
		System.out.println("userId: "+userDetailTwo.getUserId()+"		userName: "+userDetailTwo.getUserName()+"		description: "+userDetailTwo.getDescription());

		
		session.getTransaction().commit();
		session.close();
		
		
		session = sf.openSession();
		session.beginTransaction();

		UserDetail userDetailThree = (UserDetail) session.get(UserDetail.class, 1);
		System.out.println("userId: "+userDetailThree.getUserId()+"		userName: "+userDetailThree.getUserName()+"		description: "+userDetailThree.getDescription());

		session.getTransaction().commit();
		session.close();
	}
}
