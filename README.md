# Payroll Management System

A console-based Payroll Management System developed using **Java, JDBC, MySQL, and Maven**.

## 📌 Project Overview

The Payroll Management System is designed to manage employee information, salary details, payroll generation, tax calculations, payment status, and payslip generation.

The application follows a layered approach using Java classes, DAO classes, JDBC, and MySQL.

## 🚀 Features

- Add Employee
- View All Employees
- Find Employee
- Delete Employee
- Add Salary
- View Salary
- Generate Payroll
- Calculate Gross Salary
- Calculate Tax
- Calculate Total Deductions
- Calculate Net Salary
- View All Payroll Records
- Update Payment Status
- Generate Employee Payslip

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Application development |
| JDBC | Database connectivity |
| MySQL | Database management |
| Maven | Project and dependency management |
| Git & GitHub | Version control |

## 🏗️ Project Structure

```text
payroll-management-system
│
├── pom.xml
├── .gitignore
│
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── payroll
│   │               ├── App.java
│   │               ├── DBConnection.java
│   │               ├── Employee.java
│   │               ├── EmployeeDAO.java
│   │               ├── Salary.java
│   │               ├── SalaryDAO.java
│   │               ├── Payroll.java
│   │               ├── PayrollDAO.java
│   │               ├── PayslipService.java
│   │               ├── PayrollApplication.java
│   │               └── PayrollTest.java
│   │
│   └── test
│       └── java
│           └── com
│               └── payroll
│                   └── AppTest.java



🗄️ Database
Database name:
payroll_management

Tables
- employees
- salaries
- payroll
Employee Table
Stores employee information such as:
- Employee ID
- Employee Code
- Name
- Email
- Phone
- Department
- Designation
- Joining Date
- Employment Status
Salary Table
Stores:
- Basic Salary
- HRA
- Allowances
- Deductions
- Effective Date
Payroll Table
Stores:
- Employee
- Salary
- Payroll Month
- Payroll Year
- Gross Salary
- Tax
- Total Deductions
- Net Salary
- Payment Status
💰 Payroll Calculation
The application calculates payroll using:
Gross Salary = Basic Salary + HRA + Allowances

Tax = Gross Salary × 10%

Total Deductions = Existing Deductions + Tax

Net Salary = Gross Salary - Total Deductions

Example
Basic Salary       : Rs. 30000
HRA                : Rs. 1000
Allowances         : Rs. 5000
Existing Deduction : Rs. 2000

Gross Salary       : Rs. 36000
Tax (10%)          : Rs. 3600
Total Deductions   : Rs. 5600
Net Salary         : Rs. 30400

▶️ How to Run
Prerequisites
Make sure the following are installed:
- JDK 17 or higher
- Maven
- MySQL Server
- Git
1. Clone the Repository
git clone https://github.com/Janardhan493/payroll-management-system.git

2. Open the Project
cd payroll-management-system

3. Configure MySQL
Create the database:
CREATE DATABASE payroll_management;

Create the required tables:
CREATE TABLE employees (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_code VARCHAR(20) NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(15),
    department VARCHAR(50),
    designation VARCHAR(50),
    joining_date DATE NOT NULL,
    employment_status VARCHAR(20) DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE salaries (
    salary_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    basic_salary DECIMAL(10,2) NOT NULL,
    hra DECIMAL(10,2) DEFAULT 0.00,
    allowances DECIMAL(10,2) DEFAULT 0.00,
    deductions DECIMAL(10,2) DEFAULT 0.00,
    effective_from DATE NOT NULL,
    FOREIGN KEY (employee_id)
        REFERENCES employees(employee_id)
        ON DELETE CASCADE
);

CREATE TABLE payroll (
    payroll_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    salary_id INT NOT NULL,
    payroll_month INT NOT NULL,
    payroll_year INT NOT NULL,
    gross_salary DECIMAL(10,2) NOT NULL,
    tax_amount DECIMAL(10,2) DEFAULT 0.00,
    total_deductions DECIMAL(10,2) DEFAULT 0.00,
    net_salary DECIMAL(10,2) NOT NULL,
    payment_status VARCHAR(20) DEFAULT 'PENDING',
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id)
        REFERENCES employees(employee_id),
    FOREIGN KEY (salary_id)
        REFERENCES salaries(salary_id),
    UNIQUE (employee_id, payroll_month, payroll_year)
);

4. Build the Project
mvn clean compile

5. Run the Application
mvn exec:java "-Dexec.mainClass=com.payroll.PayrollApplication"

📋 Application Menu
========================================
       PAYROLL MANAGEMENT SYSTEM
========================================
1. Add Employee
2. View All Employees
3. Find Employee
4. Delete Employee
5. Add Salary
6. View Salary
7. Generate Payroll
8. View All Payroll
9. Update Payment Status
10. Print Payslip
0. Exit
========================================

🧩 JDBC Concepts Used
- JDBC Driver
- Connection
- PreparedStatement
- ResultSet
- SQLException
- CRUD Operations
- Foreign Keys
- Transactions through database operations
- Batch Processing
- SQL Queries
- DAO Pattern
💡 OOP Concepts Used
- Classes and Objects
- Encapsulation
- Constructors
- Getters and Setters
- Abstraction through DAO classes
- Separation of responsibilities
📄 Payslip
The system generates a payslip containing:
Employee Code
Employee Name
Department
Designation
Payroll Month
Gross Salary
Tax
Total Deductions
Net Salary
Payment Status

👨‍💻 Developer
Janardhan
GitHub:
https://github.com/Janardhan493
🔗 Repository
https://github.com/Janardhan493/payroll-management-system

Save and close Notepad.

### Push README to GitHub

Run:

```powershell
git add README.md
git commit -m "Add project README"
git push
