package com.commons.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Vehicle {
	
	@Id
	@GeneratedValue
	private int vehicleId;
	
	private String vehicleName;
	
	@ManyToMany(mappedBy = "vehicle")
	private Collection<UserDetail> userDetails = new ArrayList<UserDetail>();
	
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public Collection<UserDetail> getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(Collection<UserDetail> userDetails) {
		this.userDetails = userDetails;
	}
	
}
