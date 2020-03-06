package com.demo.module.commune;

import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Commune;
import com.demo.module.commune.CommuneRepository;

@Service
public class CommuneService {
	@Autowired
	private CommuneRepository repo;

	public List<Commune> findAll() {
		return repo.findAll();
	}
	
	public Commune findById(int id) {
		Optional<Commune> commune = repo.findById(id);
        if(commune.isPresent()) {
            return commune.get();
        } else {
            return null;
        }
	}
	
	public Commune findByName(String name) {
		Commune communeByName = new Commune();
		for(Commune commune : repo.findAll()) {
			if(commune.getName().equalsIgnoreCase(name)) {
				communeByName = commune; 
				
			}
		}
		return communeByName;
	
	}
	
	public Commune updateCommune (Commune commune){
		Optional<Commune> communes = repo.findById(commune.getId());
		
		if (communes.isPresent()) {
			Commune newCommune = communes.get();
			newCommune.setCode(commune.getCode());
			newCommune.setDistrictId(commune.getDistrictId());
			//newDistrict.setProvince(district.getProvince());
			newCommune.setName(commune.getName());
			
			newCommune = repo.save(newCommune);
			
			return newCommune;
		} else {
			commune = repo.save(commune);
			return commune;
		}
	}
	
	public Commune createCommune(Commune commune) {
		Commune newCommune = repo.save(commune);
		return newCommune;
	}
	
}