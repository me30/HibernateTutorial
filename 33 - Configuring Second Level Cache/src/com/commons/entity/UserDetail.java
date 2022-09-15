package com.commons.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@NamedQuery(name="UserDetail.byId", query="from UserDetail where userId = ?")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true)
@Table(name = "USER_DETAILS")
public class UserDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;

	@Column(name = "user_name")
	private String userName;
	
	private String description;

	public UserDetail() {
	}
	
	public UserDetail(int userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}
	
	public UserDetail(int userId, String userName, String description) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.description = description;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
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
}
