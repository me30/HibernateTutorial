package com.commons.dto;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.commons.entity.UserDetail;

public class HibernateMain {

	public static void main(String[] arg) {
		UserDetail user = new UserDetail();
		user.setUserName("1st user");
		user.setDescription("first user description");
		user.setJoindate(new Date());
		user.setTesting("testing");
		
		UserDetail user1 = new UserDetail();
		user1.setUserName("2nd user");
		user1.setDescription("second user description");
		user1.setJoindate(new Date());
		user1.setTesting("testing");
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(user);
		session.save(user1);
		session.getTransaction().commit();
		session.close();
		
	}
}
