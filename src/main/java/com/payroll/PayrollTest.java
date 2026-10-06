package com.payroll;

public class PayrollTest {

    public static void main(String[] args) {

        EmployeeDAO employeeDAO = new EmployeeDAO();
        SalaryDAO salaryDAO = new SalaryDAO();
        PayrollDAO payrollDAO = new PayrollDAO();
        PayslipService payslipService = new PayslipService();

        // Find employee
        Employee employee = employeeDAO.findByCode("EMP001");

        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        // Get salary
        Salary salary = salaryDAO.getSalaryByEmployee(
                employee.getEmployeeId()
        );

        if (salary == null) {
            System.out.println("Salary not found.");
            return;
        }

        // Generate payroll for November 2026
        Payroll payroll = payrollDAO.generatePayroll(
                employee.getEmployeeId(),
                salary.getSalaryId(),
                11,
                2026
        );

        if (payroll == null) {
            System.out.println("Payroll generation failed.");
            return;
        }

        // Generate payslip
        payslipService.printPayslip(employee, payroll);
    }
}