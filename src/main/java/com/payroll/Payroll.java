package com.payroll;

public class Payroll {

    private int payrollId;
    private int employeeId;
    private int salaryId;
    private int payrollMonth;
    private int payrollYear;
    private double grossSalary;
    private double taxAmount;
    private double totalDeductions;
    private double netSalary;
    private String paymentStatus;

    public Payroll() {
    }

    public Payroll(int employeeId, int salaryId,
                   int payrollMonth, int payrollYear,
                   double grossSalary, double taxAmount,
                   double totalDeductions, double netSalary) {

        this.employeeId = employeeId;
        this.salaryId = salaryId;
        this.payrollMonth = payrollMonth;
        this.payrollYear = payrollYear;
        this.grossSalary = grossSalary;
        this.taxAmount = taxAmount;
        this.totalDeductions = totalDeductions;
        this.netSalary = netSalary;
        this.paymentStatus = "PENDING";
    }

    public int getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(int payrollId) {
        this.payrollId = payrollId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public int getPayrollMonth() {
        return payrollMonth;
    }

    public void setPayrollMonth(int payrollMonth) {
        this.payrollMonth = payrollMonth;
    }

    public int getPayrollYear() {
        return payrollYear;
    }

    public void setPayrollYear(int payrollYear) {
        this.payrollYear = payrollYear;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getTotalDeductions() {
        return totalDeductions;
    }

    public void setTotalDeductions(double totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId +
               " | Month: " + payrollMonth + "/" + payrollYear +
               " | Gross: " + grossSalary +
               " | Tax: " + taxAmount +
               " | Deductions: " + totalDeductions +
               " | Net: " + netSalary +
               " | Status: " + paymentStatus;
    }
}