package com.mindex.challenge.data;

// import com.mindex.challenge.dao.EmployeeRepository;
// import com.mindex.challenge.DataBootstrap;
import com.mindex.challenge.service.impl.EmployeeServiceImpl;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Employee extends EmployeeServiceImpl{
    private String employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private List<Employee> directReports;
    private static HashMap<String, Employee> employee_lst;

    private int salary;
    private String effectiveDate;
    


    public Employee() {
        directReports = new ArrayList<Employee>();
        
        if( employee_lst == null){
            employee_lst = new HashMap<String, Employee>();
        }

        setSalary(1000);
        setEffectiveDate("01/01/1970");
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(List<Employee> directReports) {
        this.directReports = directReports;
    }


    public int getNumberOfReports( boolean is_child ){

        List<Employee> lst_of_employees = this.directReports;

        if( lst_of_employees == null ){
            return -1;
        }

        if(lst_of_employees.size() == 0 ){
            return is_child == true ? 1 : 0;
        }
        else{
            int total = 0;
            for( Employee employee : lst_of_employees ){

                Employee e = employee_lst.get( employee.employeeId );
                if( e == null )
                {
                    continue;
                }

                total += e.getNumberOfReports( true );
            }
            total += is_child == true ? 1 : 0;
            return total;
        }
    }

    public String generateReportStructString(){

        String output_string = "{";
        output_string += "\"employee\": " + this.getEmployeeId() + ",";
        output_string += "\"numberOfReports\": " + this.getNumberOfReports( false );
        output_string += "}";
        return output_string;
    }

    public void append_employee( Employee employee ){
        employee_lst.put(employee.employeeId, employee);
    }

    public Employee get_employee( String employeeID ){
        return employee_lst.get( employeeID );
    }

    public int get_employee_size(){
        return employee_lst.size();
    }


    public void SetCompensationReadWrite( String employeeId, int salary, String effectiveDate ){
        // Employee employee = employee_lst.get( employeeId );

        // employee.setSalary(salary);
        // employee.setEffectiveDate(effectiveDate);
        
        // Employee employee = employee_lst.get( employeeId );

        this.setSalary(salary);
        this.setEffectiveDate(effectiveDate);

        // return employee.generateCompensationReadString();
    }


    public String generateCompensationReadString() {
        String output_string = "{";
        output_string += "\"employee\": " + this.getEmployeeId() + ",";
        output_string += "\"salary\": " + this.getSalary() + ",";
        output_string += "\"effectiveDate\": " + this.getEffectiveDate();
        output_string += "}";
        return output_string;
    }

}
