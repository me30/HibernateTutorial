package com.commons.dto;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.commons.entity.UserDetail;

public class HibernateMain {

	public static void main(String[] arg) {
		UserDetail user = new UserDetail();
		user.setUserId(2);
		user.setUserName("second user");
		user.setDescription("first user description");
		user.setJoindate(new Date());
		user.setTesting("testing");
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}
}
