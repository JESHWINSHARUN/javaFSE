package com.cognizant.employeemanagement.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {

    private static ArrayList<com.cognizant.employeemanagement.model.Employee> EMPLOYEE_LIST;

    @Autowired
    public EmployeeDao(ArrayList<com.cognizant.employeemanagement.model.Employee> employeeList) {
        EMPLOYEE_LIST = employeeList;
    }

    public ArrayList<com.cognizant.employeemanagement.model.Employee> getAllEmployees() {
        return EMPLOYEE_LIST;
    }

}