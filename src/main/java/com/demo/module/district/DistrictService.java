package com.demo.module.district;

import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.District;
import com.demo.module.district.DistrictRepository;

@Service
public class DistrictService {
	@Autowired
	private DistrictRepository repo;

	public List<District> findAll() {
		return repo.findAll();
	}
	
	
	
	public District findById(int id) {
		Optional<District> user = repo.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
	}
	
	public District findByName(String name) {
		District userByName = new District();
		for(District user : repo.findAll()) {
			if(user.getName().equalsIgnoreCase(name)) {
				userByName = user; 
				
			}
		}
		return userByName;
	
	}
	
	public District updateDistrict (District district){
		Optional<District> districts = repo.findById(district.getId());
		
		if (districts.isPresent()) {
			District newDistrict = districts.get();
			newDistrict.setCode(district.getCode());
			newDistrict.setProvinceID(district.getProvinceID());
			newDistrict.setProvince(district.getProvince());
			newDistrict.setName(district.getName());
			
			newDistrict = repo.save(newDistrict);
			
			return newDistrict;
		} else {
			return null;
		}
	}
	
	public District createDistrict (District district) {
		District newDistrict = repo.save(district);
		
		return newDistrict;
	}
	
}