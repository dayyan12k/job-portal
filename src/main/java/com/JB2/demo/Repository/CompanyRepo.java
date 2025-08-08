package com.JB2.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JB2.demo.entity.Company;

public interface CompanyRepo extends JpaRepository<Company, Long> {

}
