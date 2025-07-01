package org.example.task3;

public class Company {
    static String companyName;
    final int employeeID;
    String employeeName;

    Company(int employeeID, String employeeName){
        this.employeeName = employeeName;
        this.employeeID = employeeID;
    }

    String getEmployeeName(){
        return this.employeeName;
    }

    void setEmployeeName(String employeeName){
        this.employeeName = employeeName;
    }

   static void printCompanyName(){
        System.out.println(companyName);
   }

}
