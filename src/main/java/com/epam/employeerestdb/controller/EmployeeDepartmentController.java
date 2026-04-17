package com.epam.employeerestdb.controller;


import com.epam.employeerestdb.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee/department")
public class EmployeeDepartmentController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/all")
    public List<String> getDepartment(){
        return employeeService.getAllDepartments();
    }

    //Employee count by department
    @GetMapping("/count")
    public Map<String, Long> getEmployeeCountByDepartment() {
        return employeeService.getEmployeeCountByDepartment();
    }

    //Average salary by department
    @GetMapping("/avg-salary")
    public Map<String, Double> getAverageSalaryByDepartment() {
        return employeeService.getAverageSalaryByDepartment();
    }

    // Employee names by department
    @GetMapping("/names")
    public Map<String, List<String>> getEmployeeNamesByDepartment() {
        return employeeService.getEmployeeNamesByDepartment();
    }

}
