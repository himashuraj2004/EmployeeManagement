package com.epam.employeerestdb.controller;

import com.epam.employeerestdb.dto.EmployeeRequestDTO;
import com.epam.employeerestdb.entity.Employee;
import com.epam.employeerestdb.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeCrudController {

    @Autowired
    private IEmployeeService employeeService;

    // GET ALL
    @GetMapping("/all")
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    // CREATE BULK — accepts DTOs instead of raw Entity

//    public boolean createEntry(@RequestBody List<Employee> employees) {
//        return employeeService.createEmployeeData(employees);
//    }

@PostMapping("/add")
    public boolean createEntry(@RequestBody List<EmployeeRequestDTO> employeeDTOs) {
        List<Employee> employees = employeeDTOs.stream()
                .map(dto -> new Employee(
                        dto.getName(),
                        dto.getAge(),
                        dto.getGender(),
                        dto.getDepartment(),
                        dto.getYearOfJoining(),
                        dto.getSalary()
                ))
                .collect(Collectors.toList());
        return employeeService.createEmployeeData(employees);
    }

}