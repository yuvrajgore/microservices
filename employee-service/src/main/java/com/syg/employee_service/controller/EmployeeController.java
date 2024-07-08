package com.syg.employee_service.controller;

import com.syg.employee_service.model.Employee;
import com.syg.employee_service.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        logger.info("employee add : {}", employee);
        return employeeRepository.addEmployee(employee);
    }

    @GetMapping
    public List<Employee> findAll(){
        logger.info("employee findAll:");
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id) {
        logger.info("employee findById : {}", id);
        return employeeRepository.findById(id);
    }
    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartmentId(@PathVariable("departmentId") Long id) {
        logger.info("employee findByDepartmentId : {}", id);
        return employeeRepository.findByDepartment(id);
    }
}
