package com.sandbox.testing.entity;

public class employee_table {

	private int employee_id;
	private String employee_name;
	private String phone;
	private String address;
	public employee_table() {
		
	}
	public employee_table(int employee_id, String employee_name, String phone, String address) {
		super();
		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.phone = phone;
		this.address = address;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
