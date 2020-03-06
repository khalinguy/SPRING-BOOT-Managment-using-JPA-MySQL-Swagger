package com.demo.module.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.DTO.StudentDTO;
import com.demo.entity.*;
import com.demo.module.commune.CommuneRepository;
import com.demo.module.district.DistrictRepository;
import com.demo.module.province.*;;



@Service
public class StudentService {
	@Autowired
	private StudentRepository repo;
	@Autowired
	private ProvinceRepository pr;
	@Autowired 
	private DistrictRepository dr;
	@Autowired
	private CommuneRepository cr;


	public List<StudentDTO> findAll() {
		List<StudentDTO> allStudents = new ArrayList<StudentDTO>();

		for (Student u: repo.findAll()) {
			allStudents.add(this.convertToDTO(u));
		}
		return allStudents;
	}
	
	public Page<Student> findAllBySearch(int page, int size, String sortBy){
		return repo.findAll(PageRequest.of(page,size,Sort.by(sortBy)));		
		
	}
	
	public StudentDTO findById(int id) {
		Optional<Student> student = repo.findById(id);
        if(student.isPresent()) {
            return this.convertToDTO(student.get());
        } else {
            return null;
        }
	}
	
	public StudentDTO findByName(String name) {
		Student studentByName = new Student();
		for(Student student : repo.findAll()) {
			if(student.getName().equalsIgnoreCase(name)) {
				studentByName = student; 
				
			}
		}
		return this.convertToDTO(studentByName);
	
	}
	
	public Student updateUser(StudentDTO dto) {
		
		Student student = this.convertToStudent(dto);
		Optional<Student> students = repo.findById(student.getId());

		if (students.isPresent()) {
			Student updateStudent = students.get();
			updateStudent.setCode(student.getCode());
			updateStudent.setAge(student.getAge());
			updateStudent.setName(student.getName());
			updateStudent.setProvinceId(student.getProvinceId());
			updateStudent.setDistrictId(student.getDistrictId());
			updateStudent.setCommuneId(student.getCommuneId());	
			updateStudent.setAddress(setFullAddress(student.getProvinceId(), student.getDistrictId(),student.getCommuneId()));
			
			updateStudent = repo.save(updateStudent);
			
			return updateStudent;
		} else {
		
			return null;
		}	
	}
	
	public Student createUser (StudentDTO dto){
		Student student = this.convertToStudent(dto);
		student.setAddress(setFullAddress(student.getProvinceId(), student.getDistrictId(),student.getCommuneId()));
		Student newStudent = repo.save(student);
		return newStudent;
		
	}
	
	public void deleteUserById(int id) {
		Optional<Student> students = repo.findById(id);
		
		if(students.isPresent()) {
			repo.deleteById(id);
		}
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
	
	private Student convertToStudent(StudentDTO dto) {
		Student student = new Student();
		student.setId(dto.getId());
		student.setName(dto.getName());
		student.setAge(dto.getAge());
		student.setProvinceId(dto.getProvinceId());
		student.setDistrictId(dto.getDistrictId());
		student.setCommuneId(dto.getCommuneId());
		
		return student;
		
	}
	
	public StudentDTO convertToDTO(Student student) {
		StudentDTO dto = new StudentDTO();
		dto.setId(student.getId());
		dto.setName(student.getName());
		dto.setAge(student.getAge());
		dto.setProvinceId(student.getProvinceId());
		dto.setDistrictId(student.getDistrictId());
		dto.setCommuneId(student.getCommuneId());
		
		return dto;
	}
	
}