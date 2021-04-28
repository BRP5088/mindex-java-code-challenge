package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);

        return employeeService.create(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return employeeService.read(id);
    }



    // Task 1
    @GetMapping("/ReportingStructure/{id}")
    public String reportStruct(@PathVariable String id) {
        LOG.debug("Received Reporting Structure request for id [{}]", id);
        Employee employee = employeeService.read(id);
        return employee.generateReportStructString();
    }


    // Task 2
    @GetMapping("/CompensationWrite/{id}/{salary}/{effectiveDate}")
    public Employee read_sal_eff(@PathVariable String id, @PathVariable int salary, @PathVariable String effectiveDate ) {
        LOG.debug("Received CompensationWrite request for id [{}], salary [{}], effective date [{}]", id, salary, effectiveDate);

        Employee employee = employeeService.read(id);
        // return employee.generateCompensationReadString();
        employee.SetCompensationReadWrite( id, salary, effectiveDate);
        // return "success";
        // return employee;
        // employee.setEmployeeId(id);
        return employeeService.update(employee);
    }
    




    @GetMapping("/CompensationRead/{id}")
    public String read_sal_eff(@PathVariable String id) {
        LOG.debug("Received CompensationRead request for id [{}]", id);

        Employee employee = employeeService.read(id);
        // return employee.generateCompensationReadString();
        return employee.generateCompensationReadString();
    }





    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee create request for id [{}] and employee [{}]", id, employee);

        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }
}
