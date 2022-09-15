package com.commons.main;

import java.util.List;

import org.hibernate.Query;
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

		Query query = session.createQuery("from UserDetail where userId = 1");
		query.setCacheable(true);
		List data = query.list();
		
		session.getTransaction().commit();
		session.close();
		
		
		Session session2 = sf.openSession();
		session2.beginTransaction();

		Query query2 = session2.createQuery("from UserDetail where userId = 1");
		query2.setCacheable(true);
		data = query2.list();
		
		session2.getTransaction().commit();
		session2.close();
	}
}
