package com.demo.module.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.DTO.UserDTO;
import com.demo.entity.Commune;
import com.demo.entity.District;
import com.demo.entity.Province;
import com.demo.entity.User;
import com.demo.module.commune.CommuneRepository;
import com.demo.module.district.DistrictRepository;
import com.demo.module.province.ProvinceRepository;
import com.demo.module.user.UserRepository;


@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	@Autowired
	private ProvinceRepository pr;
	@Autowired 
	private DistrictRepository dr;
	@Autowired
	private CommuneRepository cr;
	
	public List<UserDTO> findAll() {
		List<UserDTO> allUsers = new ArrayList<UserDTO>();
		
		for (User u: repo.findAll()) {
			allUsers.add(this.convertToDTO(u));
		}
		return allUsers;
		
	}
	
	
	public Page<User> findAllBySearch (int page, int size, String sortBy){
		return repo.findAll(PageRequest.of(page,size,Sort.by(sortBy)));		
		
	}
	

	public UserDTO findById(int id) {
		Optional<User> user = repo.findById(id);
        if(user.isPresent()) {
            return this.convertToDTO(user.get());
        } else {
            return null;
        }
	}
	
	public UserDTO findByName(String name) {
		User userByName = new User();
		for(User user : repo.findAll()) {
			if(user.getName().equalsIgnoreCase(name)) {
				userByName = user; 
				
			}
		}
		return this.convertToDTO(userByName);
	
	}
	
	public User updateUser (UserDTO dto){
		
		User user = this.convertToUser(dto);
		Optional<User> users = repo.findById(user.getId());
		
		if (users.isPresent()) {
			User updateUser = users.get();
			updateUser.setCode(user.getCode());
			updateUser.setAge(user.getAge());
			updateUser.setName(user.getName());
			updateUser.setProvinceId(user.getProvinceId());
			updateUser.setDistrictId(user.getDistrictId());
			updateUser.setCommuneId(user.getCommuneId());
			updateUser.setAddress(setFullAddress(user.getProvinceId(), user.getDistrictId(),user.getCommuneId()));
			
			updateUser = repo.save(updateUser);
			System.out.println("has id");
			return updateUser;
		} else {
			
			return null;
		}
		
	}
	
	public User createUser(UserDTO dto) {
		User user = this.convertToUser(dto);
		user.setAddress(setFullAddress(user.getProvinceId(), user.getDistrictId(),user.getCommuneId()));
		User newUser = repo.save(user);
		return newUser;
	}
	
	public void deleteUserById(int id) {
		Optional<User> users = repo.findById(id);
		
		if(users.isPresent()) {
			repo.deleteById(id);
		}
	}

	
	
	//=============== CHECK EXISTING USER ==============
	
	public boolean isUserDuplicate(User user) {
		//boolean isUserDuplicate = false;
		
		for(User u: repo.findAll()) {
			if (user.getCode().equals(u.getCode())) {
				user.setId(u.getId());
				
				u.setName(user.getName());
				u.setAddress(user.getAddress());
				u.setAge(user.getAge());
				repo.save(user);
				return true;
			}
		}
		
		return false;
	}
	
	public String setFullAddress(int provinceId, int districtId, int communeId) {
		String address = "";
		for(Province user : pr.findAll()) {
			if(user.getId() == provinceId) {
				address += user.getName();
				address += "&";
				
			}
		}
		
		for(District user : dr.findAll()) {
			if(user.getId() == districtId) {
				address += user.getName();
				address += "&";
				
			}
		}
		
		for(Commune user : cr.findAll()) {
			if(user.getId() == communeId) {
				address += user.getName(); 
				
			}
		}
		
		return address;
	}
	
	private User convertToUser(UserDTO dto) {
		User user = new User();
		user.setId(dto.getId());
		user.setName(dto.getName());
		user.setAge(dto.getAge());
		user.setAddress(dto.getAddress());
		user.setProvinceId(dto.getProvinceId());
		user.setDistrictId(dto.getDistrictId());
		user.setCommuneId(dto.getCommuneId());
		
		return user;
		
	}
	
	public UserDTO convertToDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setAge(user.getAge());
		dto.setAddress(user.getAddress());
		dto.setProvinceId(user.getProvinceId());
		dto.setDistrictId(user.getDistrictId());
		dto.setCommuneId(user.getCommuneId());
		
		return dto;
	}
}