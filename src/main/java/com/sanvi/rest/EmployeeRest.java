package com.sanvi.rest;

import com.sanvi.dto.Employee;
import com.sanvi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jagapathiraju on 19/07/17.
 */

@RestController
public class EmployeeRest {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/listAllEmployees", method = RequestMethod.GET)
    public List<Employee> getAllEmployees(){
        return employeeService.getEmployees();
    }
}
