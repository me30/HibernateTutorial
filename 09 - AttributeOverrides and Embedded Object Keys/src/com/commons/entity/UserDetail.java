package com.commons.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_details_info")
public class UserDetail {

	@EmbeddedId
	private UserId userId;

	@Column(name = "user_name")
	private String userName;
	
	@Lob
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date joindate;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "street", column = @Column(name="home_street_name")),
		@AttributeOverride(name = "city", column = @Column(name="home_city_name")),
		@AttributeOverride(name = "state", column = @Column(name="home_state_name")),
		@AttributeOverride(name = "postCode", column = @Column(name="home_post_code_name"))
	})
	private Address homeAddress;
	
	@Embedded
	private Address officeAddress;

	public UserId getUserId() {
		return userId;
	}

	public void setUserId(UserId userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Address getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}
	
}
