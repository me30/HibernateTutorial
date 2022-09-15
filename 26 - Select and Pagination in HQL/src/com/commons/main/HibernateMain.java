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
		//query for only one field
		Query query1 = session.createQuery("select userName from UserDetail");
		query1.setFirstResult(3);
		query1.setMaxResults(2);
		List<String> userid = (List<String>) query1.list();
		
		for (String userid_ : userid) {
			System.out.println("userName: "+userid_);
		}
		
		System.out.println("");
		//query for only two field
		Query query2 = session.createQuery("select new UserDetail(userId, userName) from UserDetail");
		List<UserDetail> datalist1 = (List<UserDetail>)  query2.list();
		
		for (UserDetail userDetail : datalist1) {
			System.out.println("userId: "+userDetail.getUserId()+"		userName: "+userDetail.getUserName()+"		description: "+userDetail.getDescription());
		}
			
			
		session.getTransaction().commit();
		session.close();
	}
}
