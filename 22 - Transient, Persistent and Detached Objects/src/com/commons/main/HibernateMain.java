package com.commons.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.commons.entity.UserDetail;

public class HibernateMain {

	public static void main(String[] arg) {

		
		UserDetail user = new UserDetail();
		user.setUserName("Test User");
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
			
		session.save(user);
		
		user.setUserName("Updte User");
		user.setUserName("Again Updte User");
		
		session.getTransaction().commit();
		session.close();
		
		user.setUserName("Once Again Updte User");

	}
}
