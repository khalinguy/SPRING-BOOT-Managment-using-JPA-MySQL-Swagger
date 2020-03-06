package com.demo.DTO;

public class StudentDTO {
	private int id;

	
	private String name;
	
	private Integer age;
	
	
	private int provinceId;
	
	private int districtId;
	
	private int communeId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
}
