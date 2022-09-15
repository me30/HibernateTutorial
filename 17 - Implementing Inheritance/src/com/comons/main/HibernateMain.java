package com.comons.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.commons.entity.FourWheeler;
import com.commons.entity.TwoWheeler;
import com.commons.entity.Vehicle;

public class HibernateMain {

	public static void main(String[] arg) {

		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("car");

		TwoWheeler bike = new TwoWheeler();
		bike.setVehicleName("Bike");
		bike.setSteeringHandle("Bike Steering wheel");
		
		FourWheeler car = new FourWheeler();
		car.setVehicleName("BMW");
		car.setSteeringWheel("BMW Steering Wheel");

		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(vehicle);
		session.save(bike);
		session.save(car);
		//session.save(vehicle);
		//session.save(vehicle2);
		session.getTransaction().commit();
		session.close();

	}
}
