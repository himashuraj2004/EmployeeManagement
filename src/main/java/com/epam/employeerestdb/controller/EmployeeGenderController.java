package com.epam.employeerestdb.controller;

import com.epam.employeerestdb.entity.Employee;
import com.epam.employeerestdb.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee/gender")
public class EmployeeGenderController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/age/average/{gender}")
    public double getAverageAge(@PathVariable String gender) {
        return employeeService.getAverageAgeByGender(gender);
    }

    @GetMapping("/{gender}/all")
    public List<Employee> getDataByGender(@PathVariable String gender){
        return employeeService.getDataByGender(gender);
    }

    @GetMapping("/{gender}/salary/average")
    public double getAverageSalaryByGender(@PathVariable String gender){
        return employeeService.getAverageSalaryByGender(gender);
    }

}
