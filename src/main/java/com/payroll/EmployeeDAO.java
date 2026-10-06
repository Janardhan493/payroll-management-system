package com.payroll;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // ADD EMPLOYEE
    public boolean addEmployee(Employee employee) {

        String sql = """
                INSERT INTO employees
                (employee_code, first_name, last_name, email, phone,
                 department, designation, joining_date, employment_status)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, employee.getEmployeeCode());
            ps.setString(2, employee.getFirstName());
            ps.setString(3, employee.getLastName());
            ps.setString(4, employee.getEmail());
            ps.setString(5, employee.getPhone());
            ps.setString(6, employee.getDepartment());
            ps.setString(7, employee.getDesignation());
            ps.setDate(8, Date.valueOf(employee.getJoiningDate()));
            ps.setString(9, employee.getEmploymentStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding employee: " + e.getMessage());
            return false;
        }
    }

    // GET ALL EMPLOYEES
    public List<Employee> getAllEmployees() {

        List<Employee> employees = new ArrayList<>();

        String sql = "SELECT * FROM employees ORDER BY employee_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Employee employee = new Employee();

                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setEmployeeCode(rs.getString("employee_code"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone(rs.getString("phone"));
                employee.setDepartment(rs.getString("department"));
                employee.setDesignation(rs.getString("designation"));
                employee.setJoiningDate(
                        rs.getDate("joining_date").toLocalDate()
                );
                employee.setEmploymentStatus(
                        rs.getString("employment_status")
                );

                employees.add(employee);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching employees: " + e.getMessage());
        }

        return employees;
    }

    // FIND EMPLOYEE BY CODE
    public Employee findByCode(String employeeCode) {

        String sql = "SELECT * FROM employees WHERE employee_code = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, employeeCode);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Employee employee = new Employee();

                    employee.setEmployeeId(rs.getInt("employee_id"));
                    employee.setEmployeeCode(rs.getString("employee_code"));
                    employee.setFirstName(rs.getString("first_name"));
                    employee.setLastName(rs.getString("last_name"));
                    employee.setEmail(rs.getString("email"));
                    employee.setPhone(rs.getString("phone"));
                    employee.setDepartment(rs.getString("department"));
                    employee.setDesignation(rs.getString("designation"));
                    employee.setJoiningDate(
                            rs.getDate("joining_date").toLocalDate()
                    );
                    employee.setEmploymentStatus(
                            rs.getString("employment_status")
                    );

                    return employee;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error finding employee: " + e.getMessage());
        }

        return null;
    }

    // DELETE EMPLOYEE
    public boolean deleteEmployee(String employeeCode) {

        String sql = "DELETE FROM employees WHERE employee_code = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, employeeCode);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
            return false;
        }
    }
}