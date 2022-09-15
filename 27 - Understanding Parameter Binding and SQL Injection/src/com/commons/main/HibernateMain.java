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
			user.setDescription("description_"+i);
			session.save(user);
		}
		
		Query query = session.createQuery("from UserDetail");
		List<UserDetail> datalist = (List<UserDetail>) query.list();
		

		for (UserDetail userDetail : datalist) {
			System.out.println("userId: "+userDetail.getUserId()+"		userName: "+userDetail.getUserName()+"		description: "+userDetail.getDescription());
		}
		
		System.out.println("");
		System.out.println("Using position---");
		
		Query query1 = session.createQuery("from UserDetail where userId = ? and userName = ?");
		query1.setInteger(0, 10);
		query1.setString(1, "Test User_10");
		List<UserDetail> userid = (List<UserDetail>) query1.list();
		
		for (UserDetail userDetail : userid) {
			System.out.println("userId: "+userDetail.getUserId()+"		userName: "+userDetail.getUserName()+"		description: "+userDetail.getDescription());
		}
		
		System.out.println("");
		System.out.println("Using Name---");
		Query query2 = session.createQuery("from UserDetail where userId = :userId and userName = :userName");
		query2.setInteger("userId", 10);
		query2.setString("userName", "Test User_10");
		List<UserDetail> datalist1 = (List<UserDetail>)  query2.list();
		
		for (UserDetail userDetail : datalist1) {
			System.out.println("userId: "+userDetail.getUserId()+"		userName: "+userDetail.getUserName()+"		description: "+userDetail.getDescription());
		}
			
			
		session.getTransaction().commit();
		session.close();
	}
}
