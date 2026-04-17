package com.epam.employeerestdb.service;

import com.epam.employeerestdb.entity.Employee;

import java.util.List;
import java.util.Map;

public interface IEmployeeService {

    boolean createEmployeeData(List<Employee> employees);

    List<Employee> getAll();

    List<Employee> getDataByGender(String gender);

    List<String> getAllDepartments();

    double getAverageAgeByGender(String gender);

    Employee getHighestSalary();

    List<Employee> getEmployeesJoinedAfter(int year);

    Map<String, Long> getEmployeeCountByDepartment();

    Map<String, Double> getAverageSalaryByDepartment();

    Employee findYoungestByGenderAndDepartment(String gender, String department);

    Employee findMostExperiencedEmployee();

    Map<String, Long> getGenderCountInSalesAndMarketing();

    double getAverageSalaryByGender(String gender);

    Map<String, List<String>> getEmployeeNamesByDepartment();

    Map<String, Double> getSalaryOperations(String type);

    Map<Boolean, List<Employee>> getEmployeesByAgePartition(int age);

    Map<String, Object> getOldestEmployee();
}