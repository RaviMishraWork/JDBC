package com.example.JDBC;

public class Record {

	private int sno;
	private String name;
	private String purchase;
	private String email;
	private String password;
	private String phone;
	public Record(int sno, String name, String purchase, String email, String password, String phone) {
		super();
		this.sno = sno;
		this.name = name;
		this.purchase = purchase;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPurchase() {
		return purchase;
	}
	public void setPurchase(String purchase) {
		this.purchase = purchase;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
	
}
