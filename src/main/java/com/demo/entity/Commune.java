package com.demo.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="commune")
public class Commune implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "commune_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String code;
	
	private String name;
	
	private String type;
	
	private String description;
	
	@Basic
	@Column(name = "district_id")
	private int districtId;
	
	@ManyToOne
	@JoinColumn(name = "district_id" , insertable = false, updatable = false)
	@JsonIgnoreProperties("communes")
	private District district;
	

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name = "name")
	@Basic
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "type")
	@Basic
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "description")
	@Basic
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	
	

}
