package com.commons.dto;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.commons.entity.Address;
import com.commons.entity.UserDetail;

public class HibernateMain {

	public static void main(String[] arg) {
		UserDetail user = new UserDetail();
		user.setUserName("1st user");
		user.setDescription("first user description");
		user.setJoindate(new Date());
		
		Address add = new Address();
		add.setCity("Rajkot");
		add.setState("Gujarat");
		add.setStreet("Street main");
		add.setPostCode("360002");
		
		Address add1 = new Address();
		add1.setCity("Morbi");
		add1.setState("Gujarat");
		add1.setStreet("Street main");
		add1.setPostCode("560002");
		
		user.getListAddress().add(add);
		user.getListAddress().add(add1);
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(user);
		
		session.getTransaction().commit();
		session.close();
		
	}
}
