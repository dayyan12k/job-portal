package com.JB2.demo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JB2.demo.Repository.CompanyRepo;
import com.JB2.demo.ServiceInterface.CompanyService;
import com.JB2.demo.entity.Company;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyRepo companyRepo;

    @Override
    public Company saveCompany(Company company) {
        return companyRepo.save(company);
    }

}
