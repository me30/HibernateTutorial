package com.comons.main;

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
		vehicle.setUserDetail(user);

		Vehicle vehicle2 = new Vehicle();
		vehicle2.setVehicleName("Jeep");
		vehicle2.setUserDetail(user);

		user.getVehicle().add(vehicle);
		user.getVehicle().add(vehicle2);

		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(user);
		//session.save(vehicle);
		//session.save(vehicle2);
		session.getTransaction().commit();
		session.close();

	}
}
