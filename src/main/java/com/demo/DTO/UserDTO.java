package com.demo.DTO;

import lombok.Data;

/*Data Transfer Object*/

@Data
public class UserDTO {
	private int id;
	
	private String address;
	
	private String name;
	
	private Integer age;
	
	
	private int provinceId;
	
	private int districtId;
	
	private int communeId;

	
	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public int getCommuneId() {
		return communeId;
	}

	public void setCommuneId(int communeId) {
		this.communeId = communeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}
