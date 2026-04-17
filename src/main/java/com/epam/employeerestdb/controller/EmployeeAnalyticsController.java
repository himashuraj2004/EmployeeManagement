package com.epam.employeerestdb.controller;

import com.epam.employeerestdb.entity.Employee;
import com.epam.employeerestdb.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee/analytics")
public class EmployeeAnalyticsController {

 @Autowired
 private IEmployeeService employeeService;

 @GetMapping("/sales/gender-count")
 public Map<String, Long> getGenderCountInSales() {
  return employeeService.getGenderCountInSalesAndMarketing();
 }

 @GetMapping("/oldest")
 public Map<String, Object> getOldestEmployee() {
  return employeeService.getOldestEmployee();
 }

 @GetMapping("/joinedAfter/{year}")
 public List<Employee> getEmployeeJoinedAfter(@PathVariable int year) {
  return employeeService.getEmployeesJoinedAfter(year);
 }

 @GetMapping("/youngest/{gender}/{department}")
 public Employee findYoungestByGenderAndDepartment(
         @PathVariable String gender,
         @PathVariable String department
 ) {
  return employeeService.findYoungestByGenderAndDepartment(gender, department);
 }

 @GetMapping("/partition/{age}")
 public Map<Boolean, List<Employee>> getEmployeeByAgePartition(@PathVariable int age) {
  return employeeService.getEmployeesByAgePartition(age);
 }

 @GetMapping("experience/highest")
 public Employee getMostExperiencedEmployee() {
  return employeeService.findMostExperiencedEmployee();
 }
}
