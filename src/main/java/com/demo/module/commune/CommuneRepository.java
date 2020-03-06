package com.demo.module.commune;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Commune;

public interface CommuneRepository extends JpaRepository<Commune, Integer> {

}
