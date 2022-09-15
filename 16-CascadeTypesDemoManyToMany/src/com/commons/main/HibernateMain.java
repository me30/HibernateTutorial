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

		UserDetail user2 = new UserDetail();
		user2.setUserName("2nd user");
		user2.setDescription("second user description");
		user2.setJoindate(new Date());
		
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("car");
		vehicle.getUserDetails().add(user);
		vehicle.getUserDetails().add(user2);
		
		Vehicle vehicle2 = new Vehicle();
		vehicle2.setVehicleName("Jeep");
		vehicle2.getUserDetails().add(user);
		vehicle2.getUserDetails().add(user2);
		
		user.getVehicle().add(vehicle);
		user.getVehicle().add(vehicle2);
		
		user2.getVehicle().add(vehicle);
		user2.getVehicle().add(vehicle2);

		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(user);
		session.persist(user2);
		//session.save(vehicle);
		//session.save(vehicle2);
		session.getTransaction().commit();
		session.close();
		
		
	}
}
