package com.commons.main;

import java.util.ArrayList;
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
			session.save(user);
		}
		
		Query query = session.createQuery("from UserDetail where userId = 5");
		UserDetail data = (UserDetail) query.list().get(0);
		System.out.println("id: "+data.getUserId()+" and username: "+data.getUserName());
		session.getTransaction().commit();
		session.close();
		
	}
}
