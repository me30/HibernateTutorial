package com.commons.main;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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

		Criteria criteria = session.createCriteria(UserDetail.class);
		criteria.add(Restrictions.like("userName", "%Test%"))
						.add(Restrictions.le("userId", 5))
						.add(Restrictions.between("userId", 2, 5));
		
		List<UserDetail> datalist = (List<UserDetail>) criteria.list();


		for (UserDetail userDetail : datalist) {
			System.out.println("userId: "+userDetail.getUserId()+"		userName: "+userDetail.getUserName()+"		description: "+userDetail.getDescription());
		}

		session.getTransaction().commit();
		session.close();
		
		
		session = sf.openSession();
		session.beginTransaction();


		Criteria criteria1 = session.createCriteria(UserDetail.class);
		criteria1.add(Restrictions.or(Restrictions.between("userId", 3, 5), Restrictions.between("userId", 10, 20)));
		
		List<UserDetail> datalist1 = (List<UserDetail>) criteria1.list();


		for (UserDetail userDetail : datalist1) {
			System.out.println("userId: "+userDetail.getUserId()+"		userName: "+userDetail.getUserName()+"		description: "+userDetail.getDescription());
		}

		session.getTransaction().commit();
		session.close();
	}
}
