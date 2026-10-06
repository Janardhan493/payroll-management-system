package com.payroll;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class PayrollApplication {

    private static final Scanner scanner = new Scanner(System.in);

    private static final EmployeeDAO employeeDAO = new EmployeeDAO();
    private static final SalaryDAO salaryDAO = new SalaryDAO();
    private static final PayrollDAO payrollDAO = new PayrollDAO();
    private static final PayslipService payslipService = new PayslipService();

    public static void main(String[] args) {

        while (true) {

            showMenu();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1 -> addEmployee();

                case 2 -> viewEmployees();

                case 3 -> findEmployee();

                case 4 -> deleteEmployee();

                case 5 -> addSalary();

                case 6 -> viewSalary();

                case 7 -> generatePayroll();

                case 8 -> viewPayroll();

                case 9 -> updatePaymentStatus();

                case 10 -> printPayslip();

                case 0 -> {
                    System.out.println("\nThank you for using Payroll Management System.");
                    scanner.close();
                    return;
                }

                default -> System.out.println("Invalid choice.");
            }

            System.out.println();
        }
    }

    private static void showMenu() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("       PAYROLL MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Add Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Find Employee");
        System.out.println("4. Delete Employee");
        System.out.println("5. Add Salary");
        System.out.println("6. View Salary");
        System.out.println("7. Generate Payroll");
        System.out.println("8. View All Payroll");
        System.out.println("9. Update Payment Status");
        System.out.println("10. Print Payslip");
        System.out.println("0. Exit");
        System.out.println("========================================");
    }

    private static void addEmployee() {

        System.out.println("\n===== ADD EMPLOYEE =====");

        System.out.print("Employee Code: ");
        String code = scanner.nextLine();

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Department: ");
        String department = scanner.nextLine();

        System.out.print("Designation: ");
        String designation = scanner.nextLine();

        System.out.print("Joining Date (YYYY-MM-DD): ");
        LocalDate joiningDate = LocalDate.parse(scanner.nextLine());

        Employee employee = new Employee(
                code,
                firstName,
                lastName,
                email,
                phone,
                department,
                designation,
                joiningDate
        );

        if (employeeDAO.addEmployee(employee)) {
            System.out.println("Employee added successfully.");
        } else {
            System.out.println("Failed to add employee.");
        }
    }

    private static void viewEmployees() {

        System.out.println("\n===== ALL EMPLOYEES =====");

        List<Employee> employees = employeeDAO.getAllEmployees();

        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private static void findEmployee() {

        System.out.println("\n===== FIND EMPLOYEE =====");

        System.out.print("Enter Employee Code: ");
        String code = scanner.nextLine();

        Employee employee = employeeDAO.findByCode(code);

        if (employee == null) {
            System.out.println("Employee not found.");
        } else {
            System.out.println(employee);
        }
    }

    private static void deleteEmployee() {

        System.out.println("\n===== DELETE EMPLOYEE =====");

        System.out.print("Enter Employee Code: ");
        String code = scanner.nextLine();

        if (employeeDAO.deleteEmployee(code)) {
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void addSalary() {

        System.out.println("\n===== ADD SALARY =====");

        System.out.print("Employee ID: ");
        int employeeId = scanner.nextInt();

        System.out.print("Basic Salary: ");
        double basic = scanner.nextDouble();

        System.out.print("HRA: ");
        double hra = scanner.nextDouble();

        System.out.print("Allowances: ");
        double allowances = scanner.nextDouble();

        System.out.print("Deductions: ");
        double deductions = scanner.nextDouble();

        scanner.nextLine();

        System.out.print("Effective From (YYYY-MM-DD): ");
        LocalDate effectiveFrom = LocalDate.parse(scanner.nextLine());

        Salary salary = new Salary(
                employeeId,
                basic,
                hra,
                allowances,
                deductions,
                effectiveFrom
        );

        if (salaryDAO.addSalary(salary)) {
            System.out.println("Salary added successfully.");
        } else {
            System.out.println("Failed to add salary.");
        }
    }

    private static void viewSalary() {

        System.out.println("\n===== VIEW SALARY =====");

        System.out.print("Employee ID: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine();

        Salary salary = salaryDAO.getSalaryByEmployee(employeeId);

        if (salary == null) {
            System.out.println("Salary not found.");
        } else {
            System.out.println(salary);
        }
    }

    private static void generatePayroll() {

        System.out.println("\n===== GENERATE PAYROLL =====");

        System.out.print("Employee ID: ");
        int employeeId = scanner.nextInt();

        System.out.print("Month: ");
        int month = scanner.nextInt();

        System.out.print("Year: ");
        int year = scanner.nextInt();

        scanner.nextLine();

        Salary salary = salaryDAO.getSalaryByEmployee(employeeId);

        if (salary == null) {
            System.out.println("Salary not found for employee.");
            return;
        }

        Payroll payroll = payrollDAO.generatePayroll(
                employeeId,
                salary.getSalaryId(),
                month,
                year
        );

        if (payroll != null) {
            System.out.println(payroll);
        }
    }

    private static void viewPayroll() {

        System.out.println("\n===== ALL PAYROLL =====");

        List<Payroll> payrollList = payrollDAO.getAllPayroll();

        if (payrollList.isEmpty()) {
            System.out.println("No payroll records found.");
            return;
        }

        for (Payroll payroll : payrollList) {
            System.out.println(payroll);
        }
    }

    private static void updatePaymentStatus() {

        System.out.println("\n===== UPDATE PAYMENT STATUS =====");

        System.out.print("Payroll ID: ");
        int payrollId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Status (PENDING/PAID): ");
        String status = scanner.nextLine().toUpperCase();

        if (payrollDAO.updatePaymentStatus(payrollId, status)) {
            System.out.println("Payment status updated successfully.");
        } else {
            System.out.println("Payroll record not found.");
        }
    }

    private static void printPayslip() {

        System.out.println("\n===== PRINT PAYSLIP =====");

        System.out.print("Employee Code: ");
        String employeeCode = scanner.nextLine();

        Employee employee = employeeDAO.findByCode(employeeCode);

        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.print("Payroll Month: ");
        int month = scanner.nextInt();

        System.out.print("Payroll Year: ");
        int year = scanner.nextInt();

        scanner.nextLine();

        List<Payroll> payrollList = payrollDAO.getAllPayroll();

        for (Payroll payroll : payrollList) {

            if (payroll.getEmployeeId() == employee.getEmployeeId()
                    && payroll.getPayrollMonth() == month
                    && payroll.getPayrollYear() == year) {

                payslipService.printPayslip(employee, payroll);
                return;
            }
        }

        System.out.println("Payroll record not found for this month.");
    }
}