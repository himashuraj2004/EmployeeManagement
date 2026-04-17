package com.epam.employeerestdb.service;

import com.epam.employeerestdb.entity.Employee;
import com.epam.employeerestdb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // CREATE (Bulk)
    public boolean createEmployeeData(List<Employee> employees) {
        employeeRepository.saveAll(employees);
        return true;
    }

    // READ ALL
    public List<Employee> getAll() {
        return getAllEmployees();
    }

    // FILTER BY GENDER
    public List<Employee> getDataByGender(String gender) {
        return getAllEmployees().stream()
                .filter(e -> e.getGender().equalsIgnoreCase(gender))
                .toList();
    }

    // GET ALL DEPARTMENTS (UNIQUE)
    public List<String> getAllDepartments() {
        return getAllEmployees().stream()
                .map(Employee::getDepartment)
                .distinct()
                .toList();
    }

    // AVERAGE AGE BY GENDER
    public double getAverageAgeByGender(String gender) {
        return getAllEmployees().stream()
                .filter(e -> e.getGender().equalsIgnoreCase(gender))
                .mapToInt(Employee::getAge)
                .average()
                .orElse(0.0);
    }

    // HIGHEST SALARY
    public Employee getHighestSalary() {
        return getAllEmployees().stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }

    // EMPLOYEES JOINED AFTER YEAR
    public List<Employee> getEmployeesJoinedAfter(int year) {
        return getAllEmployees().stream()
                .filter(e -> e.getYearOfJoining() > year)
                .toList();
    }

    // COUNT EMPLOYEES IN EACH DEPARTMENT
    public Map<String, Long> getEmployeeCountByDepartment() {
        return getAllEmployees().stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.counting()
                ));
    }

    // AVERAGE SALARY BY DEPARTMENT
    public Map<String, Double> getAverageSalaryByDepartment() {
        return getAllEmployees().stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
    }

    // YOUNGEST EMPLOYEE BY GENDER & DEPARTMENT
    public Employee findYoungestByGenderAndDepartment(String gender, String department) {
        return getAllEmployees().stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase(department))
                .filter(e -> e.getGender().equalsIgnoreCase(gender))
                .min(Comparator.comparingInt(Employee::getAge))
                .orElse(null);
    }

    // MOST EXPERIENCED EMPLOYEE
    public Employee findMostExperiencedEmployee() {
        return getAllEmployees().stream()
                .min(Comparator.comparingInt(Employee::getYearOfJoining))
                .orElse(null);
    }

    // GENDER COUNT IN SALES & MARKETING
    public Map<String, Long> getGenderCountInSalesAndMarketing() {
        final String SALES_MARKETING = "Sales and Marketing";

        return getAllEmployees().stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase(SALES_MARKETING))
                .collect(Collectors.groupingBy(
                        Employee::getGender,
                        Collectors.counting()
                ));
    }

    // AVERAGE SALARY BY GENDER
    public double getAverageSalaryByGender(String gender) {
        return getAllEmployees().stream()
                .filter(e -> e.getGender().equalsIgnoreCase(gender))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    // EMPLOYEE NAMES BY DEPARTMENT
    public Map<String, List<String>> getEmployeeNamesByDepartment() {
        return getAllEmployees().stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.mapping(Employee::getName, Collectors.toList())
                ));
    }

    // TOTAL OR AVERAGE SALARY
    public Map<String, Double> getSalaryOperations(String type) {
        Map<String, Double> result = new HashMap<>();

        if ("total".equalsIgnoreCase(type)) {
            double totalSalary = getAllEmployees().stream()
                    .mapToDouble(Employee::getSalary)
                    .sum();
            result.put("totalSalary", totalSalary);
        } else if ("average".equalsIgnoreCase(type)) {
            double averageSalary = getAllEmployees().stream()
                    .mapToDouble(Employee::getSalary)
                    .average()
                    .orElse(0.0);
            result.put("averageSalary", averageSalary);
        }
        else{
            throw new IllegalArgumentException("Invalid Salary operation.");
        }

        return result;
    }

    // PARTITION BY AGE
    public Map<Boolean, List<Employee>> getEmployeesByAgePartition(int age) {
        return getAllEmployees().stream()
                .collect(Collectors.partitioningBy(e -> e.getAge() <= age));
    }

    // OLDEST EMPLOYEE (FIXED)
    public Map<String, Object> getOldestEmployee() {
        Employee emp = getAllEmployees().stream()
                .max(Comparator.comparingInt(Employee::getAge))
                .orElse(null);

        if (emp == null) return Collections.emptyMap();

        Map<String, Object> result = new HashMap<>();
        result.put("name", emp.getName());
        result.put("age", emp.getAge());
        result.put("department", emp.getDepartment());

        return result;
    }
}