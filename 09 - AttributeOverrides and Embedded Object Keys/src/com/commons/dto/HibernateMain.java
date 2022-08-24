package com.commons.dto;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.commons.entity.Address;
import com.commons.entity.UserDetail;
import com.commons.entity.UserId;

public class HibernateMain {

	public static void main(String[] arg) {
		
		UserId userid = new UserId();
		userid.setAddharNumber("1234 4321 0987 0099");
		userid.setMobile("90909090");
		
		UserDetail user = new UserDetail();
		user.setUserId(userid);
		user.setUserName("1st user");
		user.setDescription("first user description");
		user.setJoindate(new Date());
		
		Address addhome = new Address();
		addhome.setCity("Rajkot");
		addhome.setState("Gujarat");
		addhome.setStreet("Street main");
		addhome.setPostCode("360002");
		
		user.setHomeAddress(addhome);
		
		Address addoffice = new Address();
		addoffice.setCity("Rajkot Office");
		addoffice.setState("Gujarat Office");
		addoffice.setStreet("Street main office");
		addoffice.setPostCode("360005");
		
		user.setOfficeAddress(addoffice);
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(user);
		
		session.getTransaction().commit();
		session.close();
		
	}
}
