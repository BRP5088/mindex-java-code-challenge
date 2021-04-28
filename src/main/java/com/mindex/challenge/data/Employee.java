package com.mindex.challenge.data;

// import com.mindex.challenge.dao.EmployeeRepository;
// import com.mindex.challenge.DataBootstrap;
import com.mindex.challenge.service.impl.EmployeeServiceImpl;

import java.util.List;
import java.util.ArrayList;

public class Employee extends EmployeeServiceImpl{
    private String employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private List<Employee> directReports;
    // private int ReportingStructure;
    public Employee() {
        directReports = new ArrayList<Employee>();
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

        System.out.println( "called get getNumberOfReports" );

        if( lst_of_employees == null ){
            return -1;
        }

        if(lst_of_employees.size() == 0 ){
            return is_child == true ? 1 : 0;
        }
        else{
            int total = 0;
            for( Employee employee : lst_of_employees ){

                System.out.println( "Here is the employee id: " + employee.employeeId );
                
                Employee e = getEmployee(employee.employeeId);
                if( e == null )
                {
                    System.out.println( "\ne is null!!\n" );
                }

                System.out.println("Here is employee name: " + e.firstName );

                // total += e.getNumberOfReports( true );

                total += employee.getNumberOfReports( true );
            }
            total += is_child == true ? 1 : 0;
            return total;
        }
    }

    // public void setReportingStructure(){
    //     this.ReportingStructure = this.getNumberOfReports( false );
    // }

    public String generateReportStructString(){

        String output_string = "{";
        output_string += "\"employee\": " + this.getEmployeeId() + ",";
        output_string += "\"numberOfReports\": " + this.getNumberOfReports( false );
        // output_string += "\n"+ this.firstName;
        // output_string += "\n"+ this.directReports;

        output_string += "\n";

        // for( int i = 0; i < this.directReports.size(); i++ ){
        //     output_string += "\n" + this.directReports.get( i ).employeeId;
        // }


        // System.out.println( this.directReports.getClass().getName() );
        // System.out.println( this.directReports.get(0).getClass().getName() );


        output_string += "}";

        return output_string;
    }
}
