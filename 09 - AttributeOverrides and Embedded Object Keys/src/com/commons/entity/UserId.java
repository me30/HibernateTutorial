package com.commons.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String addharNumber;
	private String mobile;
	public String getAddharNumber() {
		return addharNumber;
	}
	public void setAddharNumber(String addharNumber) {
		this.addharNumber = addharNumber;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
