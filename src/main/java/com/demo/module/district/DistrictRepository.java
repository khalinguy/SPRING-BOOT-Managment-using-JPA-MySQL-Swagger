package com.demo.module.district;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.District;

public interface DistrictRepository extends JpaRepository<District, Integer> {

}
