package com.demo.module.province;

import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Province;
import com.demo.module.province.ProvinceRepository;

@Service
public class ProvinceService {
	@Autowired
	private ProvinceRepository repo;

	public List<Province> findAll() {
		return repo.findAll();
	}
	
	public Province findById(int id) {
		Optional<Province> user = repo.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
	}
	
	public Province findByName(String name) {
		Province userByName = new Province();
		for(Province user : repo.findAll()) {
			if(user.getName().equalsIgnoreCase(name)) {
				userByName = user; 
				
			}
		}
		return userByName;
	
	}
	
	
	public Province updateProvince (Province province){
		Optional<Province> provinces = repo.findById(province.getId());
		
		if (provinces.isPresent()) {
			Province newProvince = provinces.get();
			newProvince.setCode(province.getCode());
			newProvince.setName(province.getName());
			newProvince.setOrders(province.getOrders());
			newProvince.setDescription(province.getDescription());
			newProvince.setType(province.getType());
			
			newProvince = repo.save(newProvince);
			
			return newProvince;
		} else {
			return null;
		}
	}
	
	public Province createProvince (Province province) {
		Province newProvince = repo.save(province);
		return newProvince;
	}
	
	
}