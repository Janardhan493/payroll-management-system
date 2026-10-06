package com.payroll;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAO {

    // ADD SALARY
    public boolean addSalary(Salary salary) {

        String sql = """
                INSERT INTO salaries
                (employee_id, basic_salary, hra, allowances, deductions, effective_from)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, salary.getEmployeeId());
            ps.setDouble(2, salary.getBasicSalary());
            ps.setDouble(3, salary.getHra());
            ps.setDouble(4, salary.getAllowances());
            ps.setDouble(5, salary.getDeductions());
            ps.setDate(6, Date.valueOf(salary.getEffectiveFrom()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding salary: " + e.getMessage());
            return false;
        }
    }

    // GET SALARY BY EMPLOYEE
    public Salary getSalaryByEmployee(int employeeId) {

        String sql = """
                SELECT * FROM salaries
                WHERE employee_id = ?
                ORDER BY effective_from DESC
                LIMIT 1
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Salary salary = new Salary();

                    salary.setSalaryId(rs.getInt("salary_id"));
                    salary.setEmployeeId(rs.getInt("employee_id"));
                    salary.setBasicSalary(rs.getDouble("basic_salary"));
                    salary.setHra(rs.getDouble("hra"));
                    salary.setAllowances(rs.getDouble("allowances"));
                    salary.setDeductions(rs.getDouble("deductions"));
                    salary.setEffectiveFrom(
                            rs.getDate("effective_from").toLocalDate()
                    );

                    return salary;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error fetching salary: " + e.getMessage());
        }

        return null;
    }

    // GET ALL SALARIES
    public List<Salary> getAllSalaries() {

        List<Salary> salaries = new ArrayList<>();

        String sql = "SELECT * FROM salaries ORDER BY salary_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Salary salary = new Salary();

                salary.setSalaryId(rs.getInt("salary_id"));
                salary.setEmployeeId(rs.getInt("employee_id"));
                salary.setBasicSalary(rs.getDouble("basic_salary"));
                salary.setHra(rs.getDouble("hra"));
                salary.setAllowances(rs.getDouble("allowances"));
                salary.setDeductions(rs.getDouble("deductions"));
                salary.setEffectiveFrom(
                        rs.getDate("effective_from").toLocalDate()
                );

                salaries.add(salary);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching salaries: " + e.getMessage());
        }

        return salaries;
    }
}