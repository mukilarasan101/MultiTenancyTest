//$Id$
package com.example.tenancy.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LoginDetail {
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="BROWSER")
	private String browser;
	@Column(name="LOGIN_DATE")
	private long loginDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public long getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(long loginDate) {
		this.loginDate = loginDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoginDetail [id=");
		builder.append(id);
		builder.append(", browser=");
		builder.append(browser);
		builder.append(", loginDate=");
		builder.append(loginDate);
		builder.append("]");
		return builder.toString();
	}
}
