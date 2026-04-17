package com.epam.employeerestdb.controller;

import com.epam.employeerestdb.entity.Employee;
import com.epam.employeerestdb.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/employee/salary")
public class EmployeeSalaryController {

    @Autowired
    private IEmployeeService employeeService;

    // get the highest salary
    @GetMapping("/highest")
    public Employee getHighestSalary(){
        return employeeService.getHighestSalary();
    }

    //get salary operations
    @GetMapping("/operations/{type}")
    public Map<String, Double> getSalaryOperation(
            @PathVariable String type
    ){
        return employeeService.getSalaryOperations(type);
    }

}
