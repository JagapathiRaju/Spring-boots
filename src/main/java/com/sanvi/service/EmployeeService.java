package com.sanvi.service;

import com.sanvi.dto.Employee;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jagapathiraju on 19/07/17.
 */

@Service("employeeService")
public class EmployeeService {
    public List<Employee> getEmployees(){

        List <Employee> list = new ArrayList<>();
        list.add(new Employee(37,"Raju","M"));
        list.add(new Employee(4,"Sanvi","F"));

        return  list;
    }
}
