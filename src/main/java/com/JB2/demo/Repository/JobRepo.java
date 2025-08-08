package com.JB2.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JB2.demo.entity.Job;

public interface JobRepo extends JpaRepository<Job, Long>{

}
