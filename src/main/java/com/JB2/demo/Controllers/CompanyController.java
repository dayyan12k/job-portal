package com.JB2.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.JB2.demo.Forms.Companyform;
import com.JB2.demo.Impl.CompanyServiceImpl;
import com.JB2.demo.entity.Company;

@Controller
public class CompanyController {

    @Autowired
    private CompanyServiceImpl companyServiceImpl;

    @RequestMapping("/user/register-company")
    public String showForm(Model model){
        Companyform companyform = new Companyform();
        model.addAttribute("companyform" , companyform);
        return "companies/register-company";
    }

    @RequestMapping(value = "/companies" , method=RequestMethod.POST)
    public String processCompany(@ModelAttribute Companyform companyform){
        Company company = new Company();
        company.setName(companyform.getName());
        company.setIndustry(companyform.getIndustry());
        company.setDescription(companyform.getDescription());
        companyServiceImpl.saveCompany(company);
        return "redirect:/register-company";

    }
}
