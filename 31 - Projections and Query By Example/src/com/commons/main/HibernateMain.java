package com.commons.main;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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

		Criteria criteria = session.createCriteria(UserDetail.class)
				.setProjection(Projections.property("userName"));

		List<String> datalist = (List<String>) criteria.list();
		for (String userDetail : datalist) {
			System.out.println("userName: "+userDetail);
		}

		session.getTransaction().commit();
		session.close();
		
		
		session = sf.openSession();
		session.beginTransaction();

		//Example ignore primary key and null key.......
		UserDetail exampleUserDetail = new UserDetail();
		//exampleUserDetail.setUserId(5);
		exampleUserDetail.setUserName("%User_1%");
		
		Example example = Example.create(exampleUserDetail).enableLike();
		
		Criteria criteria1 = session.createCriteria(UserDetail.class);
		criteria1.add(example);
		
		List<UserDetail> datalist1 = (List<UserDetail>) criteria1.list();


		for (UserDetail userDetail : datalist1) {
			System.out.println("userId: "+userDetail.getUserId()+"		userName: "+userDetail.getUserName()+"		description: "+userDetail.getDescription());
		}

		session.getTransaction().commit();
		session.close();
	}
}
