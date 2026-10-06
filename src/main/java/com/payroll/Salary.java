package com.payroll;

import java.time.LocalDate;

public class Salary {

    private int salaryId;
    private int employeeId;
    private double basicSalary;
    private double hra;
    private double allowances;
    private double deductions;
    private LocalDate effectiveFrom;

    public Salary() {
    }

    public Salary(int employeeId, double basicSalary, double hra,
                   double allowances, double deductions,
                   LocalDate effectiveFrom) {

        this.employeeId = employeeId;
        this.basicSalary = basicSalary;
        this.hra = hra;
        this.allowances = allowances;
        this.deductions = deductions;
        this.effectiveFrom = effectiveFrom;
    }

    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getHra() {
        return hra;
    }

    public void setHra(double hra) {
        this.hra = hra;
    }

    public double getAllowances() {
        return allowances;
    }

    public void setAllowances(double allowances) {
        this.allowances = allowances;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(LocalDate effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public double calculateGrossSalary() {
        return basicSalary + hra + allowances;
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId +
               " | Basic: " + basicSalary +
               " | HRA: " + hra +
               " | Allowances: " + allowances +
               " | Deductions: " + deductions +
               " | Gross: " + calculateGrossSalary();
    }
}