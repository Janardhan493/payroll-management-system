package com.payroll;

public class PayslipService {

    public void printPayslip(Employee employee, Payroll payroll) {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("              COMPANY PAYSLIP");
        System.out.println("==============================================");

        System.out.println("Employee Code    : " + employee.getEmployeeCode());
        System.out.println("Employee Name    : "
                + employee.getFirstName() + " "
                + employee.getLastName());

        System.out.println("Department       : " + employee.getDepartment());
        System.out.println("Designation      : " + employee.getDesignation());

        System.out.println("----------------------------------------------");

        System.out.println("Payroll Month    : "
                + payroll.getPayrollMonth() + "/"
                + payroll.getPayrollYear());

        System.out.println("Gross Salary     : Rs." + payroll.getGrossSalary());
        System.out.println("Tax              : Rs." + payroll.getTaxAmount());
        System.out.println("Total Deductions : Rs." + payroll.getTotalDeductions());

        System.out.println("----------------------------------------------");

        System.out.println("NET SALARY       : Rs." + payroll.getNetSalary());
        System.out.println("Payment Status   : " + payroll.getPaymentStatus());

        System.out.println("==============================================");
        System.out.println("              END OF PAYSLIP");
        System.out.println("==============================================");
    }
}