package com.commons.main;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.commons.entity.UserDetail;
import com.commons.entity.Vehicle;

public class HibernateMain {

	public static void main(String[] arg) {
		UserDetail user = new UserDetail();
		user.setUserName("1st user");
		user.setDescription("first user description");
		user.setJoindate(new Date());

		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("car");

		user.setVehicle(vehicle);

		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(user);
		session.save(vehicle);
		session.getTransaction().commit();
		session.close();
		
		System.out.println("####################################");
		session = sf.openSession();
		user = null;
		user = (UserDetail) session.get(UserDetail.class, 1);
		System.out.println(user.getVehicle());
		
		
		vehicle = null;
		vehicle = (Vehicle) session.get(Vehicle.class, 1);
	}
}
