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

		//create new records
		System.out.println("Create 10 records:");
		for (int i=1; i <=10 ; i++) {
			UserDetail user = new UserDetail();
			user.setUserName("User - "+i);
			session.save(user);
		}
		System.out.println();
		for (int i=1; i <=10 ; i++) {
			UserDetail user = (UserDetail) session.get(UserDetail.class, i);
			System.out.println("Id: "+user.getUserId()+ " - Name: "+user.getUserName());
		}
		
		//update user 1
		System.out.println();
		System.out.println("Update user Id: 1");
		UserDetail user = (UserDetail) session.get(UserDetail.class, 1);
		user.setUserName("Update user");
		session.update(user);
		
		for (int i=1; i <=10 ; i++) {
			UserDetail user_ = (UserDetail) session.get(UserDetail.class, i);
			System.out.println("Id: "+user_.getUserId()+ " - Name: "+user_.getUserName());
		}
		
		//delete user 1
		System.out.println();
		System.out.println("Deleted user Id: 1");
		session.delete(user);
		for (int i=2; i <=10 ; i++) {
			UserDetail user_ = (UserDetail) session.get(UserDetail.class, i);
			System.out.println("Id: "+user_.getUserId()+ " - Name: "+user_.getUserName());
		}
		
		session.getTransaction().commit();
		session.close();

	}
}
