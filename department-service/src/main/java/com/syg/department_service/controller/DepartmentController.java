package com.syg.department_service.controller;

import com.syg.department_service.client.EmployeeClient;
import com.syg.department_service.model.Department;
import com.syg.department_service.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeClient employeeClient;

    @PostMapping
    public Department add(@RequestBody Department department) {
        logger.info("department add : {}", department);
        return departmentRepository.addDepartment(department);
    }

    @GetMapping
    public List<Department> findAll(){
        logger.info("department findAll:");
        return departmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        logger.info("department findById : {}", id);
        return departmentRepository.findById(id);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees(){
        logger.info("department findAllWithEmployees:");
        List<Department> departmentList = departmentRepository.findAll();
        departmentList.forEach(department -> department.setEmployeeList(employeeClient.findByDepartmentId(department.getId())));
        return departmentList;
    }
}
