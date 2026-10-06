package com.payroll;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PayrollDAO {

    private static final double TAX_RATE = 0.10;

    // GENERATE PAYROLL
    public Payroll generatePayroll(int employeeId, int salaryId,
                                   int month, int year) {

        SalaryDAO salaryDAO = new SalaryDAO();
        Salary salary = salaryDAO.getSalaryByEmployee(employeeId);

        if (salary == null) {
            System.out.println("Salary record not found.");
            return null;
        }

        double grossSalary = salary.getBasicSalary()
                + salary.getHra()
                + salary.getAllowances();

        double taxAmount = grossSalary * TAX_RATE;

        double totalDeductions = salary.getDeductions()
                + taxAmount;

        double netSalary = grossSalary - totalDeductions;

        Payroll payroll = new Payroll(
                employeeId,
                salaryId,
                month,
                year,
                grossSalary,
                taxAmount,
                totalDeductions,
                netSalary
        );

        String sql = """
                INSERT INTO payroll
                (employee_id, salary_id, payroll_month, payroll_year,
                 gross_salary, tax_amount, total_deductions,
                 net_salary, payment_status)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ps.setInt(2, salaryId);
            ps.setInt(3, month);
            ps.setInt(4, year);
            ps.setDouble(5, grossSalary);
            ps.setDouble(6, taxAmount);
            ps.setDouble(7, totalDeductions);
            ps.setDouble(8, netSalary);
            ps.setString(9, "PENDING");

            ps.executeUpdate();

            System.out.println("Payroll generated successfully.");

            return payroll;

        } catch (SQLIntegrityConstraintViolationException e) {

            System.out.println(
                    "Payroll already exists for this employee and month."
            );

        } catch (SQLException e) {

            System.out.println(
                    "Error generating payroll: " + e.getMessage()
            );
        }

        return null;
    }

    // GET ALL PAYROLL
    public List<Payroll> getAllPayroll() {

        List<Payroll> payrollList = new ArrayList<>();

        String sql = "SELECT * FROM payroll ORDER BY payroll_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Payroll payroll = new Payroll();

                payroll.setPayrollId(
                        rs.getInt("payroll_id")
                );

                payroll.setEmployeeId(
                        rs.getInt("employee_id")
                );

                payroll.setSalaryId(
                        rs.getInt("salary_id")
                );

                payroll.setPayrollMonth(
                        rs.getInt("payroll_month")
                );

                payroll.setPayrollYear(
                        rs.getInt("payroll_year")
                );

                payroll.setGrossSalary(
                        rs.getDouble("gross_salary")
                );

                payroll.setTaxAmount(
                        rs.getDouble("tax_amount")
                );

                payroll.setTotalDeductions(
                        rs.getDouble("total_deductions")
                );

                payroll.setNetSalary(
                        rs.getDouble("net_salary")
                );

                payroll.setPaymentStatus(
                        rs.getString("payment_status")
                );

                payrollList.add(payroll);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error fetching payroll: " + e.getMessage()
            );
        }

        return payrollList;
    }

    // UPDATE PAYMENT STATUS
    public boolean updatePaymentStatus(int payrollId,
                                       String status) {

        String sql = """
                UPDATE payroll
                SET payment_status = ?
                WHERE payroll_id = ?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, payrollId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error updating payment status: "
                            + e.getMessage()
            );

            return false;
        }
    }

    // BATCH PROCESSING
    public void generatePayrollBatch(List<Payroll> payrollList) {

        String sql = """
                INSERT INTO payroll
                (employee_id, salary_id, payroll_month, payroll_year,
                 gross_salary, tax_amount, total_deductions,
                 net_salary, payment_status)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (Payroll payroll : payrollList) {

                ps.setInt(1, payroll.getEmployeeId());
                ps.setInt(2, payroll.getSalaryId());
                ps.setInt(3, payroll.getPayrollMonth());
                ps.setInt(4, payroll.getPayrollYear());
                ps.setDouble(5, payroll.getGrossSalary());
                ps.setDouble(6, payroll.getTaxAmount());
                ps.setDouble(7, payroll.getTotalDeductions());
                ps.setDouble(8, payroll.getNetSalary());
                ps.setString(9, payroll.getPaymentStatus());

                ps.addBatch();
            }

            ps.executeBatch();

            System.out.println(
                    "Payroll batch processed successfully."
            );

        } catch (SQLException e) {

            System.out.println(
                    "Batch processing error: " + e.getMessage()
            );
        }
    }
}