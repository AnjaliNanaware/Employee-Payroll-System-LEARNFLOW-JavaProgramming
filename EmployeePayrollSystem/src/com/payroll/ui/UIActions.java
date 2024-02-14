package com.payroll.ui;

import javax.swing.*;

import java.awt.Color;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UIActions {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/payroll_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Anjali@9876";

    public static void showEmployeeDetailsDialog() throws SQLException {
        JFrame employeeDetailsFrame = new JFrame("Employee Details");
        employeeDetailsFrame.setSize(400, 200);
        employeeDetailsFrame.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Enter Employee Name:");
        JTextField nameTextField = new JTextField();

        JLabel idLabel = new JLabel("Enter Employee ID:");
        JTextField idTextField = new JTextField();

        JButton saveButton = new JButton("Save Employee Details");
        saveButton.setBackground(new Color(45, 100, 107));
        saveButton.setForeground(Color.white);

        saveButton.addActionListener(e -> {
            try {
                String name = nameTextField.getText();
                String employeeID = idTextField.getText();

                if (!name.isEmpty() && !employeeID.isEmpty()) {
                    saveEmployeeDetails(name, employeeID);
                    JOptionPane.showMessageDialog(null, "Employee details saved successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter valid details.");
                }
            } catch (SQLException ex) {
                handleException(ex);
            }
        });

        employeeDetailsFrame.add(nameLabel);
        employeeDetailsFrame.add(nameTextField);
        employeeDetailsFrame.add(idLabel);
        employeeDetailsFrame.add(idTextField);
        employeeDetailsFrame.add(saveButton);

        employeeDetailsFrame.setVisible(true);
    }

    public static void showAttendanceDialog() throws SQLException {
        JFrame attendanceFrame = new JFrame("Attendance Management");
        attendanceFrame.setSize(400, 200);
        attendanceFrame.setLayout(new GridLayout(3, 2));

        JLabel employeeIdLabel = new JLabel("Enter Employee ID:");
        JTextField employeeIdTextField = new JTextField();

        JButton markAttendanceButton = new JButton("Mark Attendance");
        
        markAttendanceButton.setBackground(new Color(45, 100, 107));
        markAttendanceButton.setForeground(Color.white);
      
        

        markAttendanceButton.addActionListener(e -> {
            try {
                String employeeID = employeeIdTextField.getText();

                if (!employeeID.isEmpty()) {
                    markAttendance(employeeID);
                    JOptionPane.showMessageDialog(null, "Attendance marked successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Employee ID.");
                }
            } catch (SQLException ex) {
                handleException(ex);
            }
        });

        attendanceFrame.add(employeeIdLabel);
        attendanceFrame.add(employeeIdTextField);
        attendanceFrame.add(markAttendanceButton);

        attendanceFrame.setVisible(true);
    }

    public static void showPayrollDialog() throws SQLException {
        JFrame payrollFrame = new JFrame("Payroll Processing");
        payrollFrame.setSize(400, 200);
        payrollFrame.setLayout(new GridLayout(4, 2));

        JLabel employeeIdLabel = new JLabel("Enter Employee ID:");
        JTextField employeeIdTextField = new JTextField();

        JLabel salaryLabel = new JLabel("Enter Salary:");
        JTextField salaryTextField = new JTextField();

        JLabel taxLabel = new JLabel("Tax Deduction:");
        JTextField taxTextField = new JTextField();

        JButton calculateButton = new JButton("Calculate Payroll");
        JButton generatePaySlipButton = new JButton("Generate Pay Slip");
        
        calculateButton.setBackground(new Color(45, 100, 107));
        calculateButton.setForeground(Color.white);
      
        generatePaySlipButton .setBackground(new Color(45, 100, 107));
        generatePaySlipButton .setForeground(Color.white);
      

        calculateButton.addActionListener(e -> {
            try {
                String employeeID = employeeIdTextField.getText();
                double salary = Double.parseDouble(salaryTextField.getText());

                double tax = getTaxDeduction(employeeID);
                double netSalary = calculateNetSalary(salary, tax);
                JOptionPane.showMessageDialog(null, "Net Salary: Rs." + netSalary);
            } catch (NumberFormatException | SQLException ex) {
                handleException(ex);
            }
        });

        generatePaySlipButton.addActionListener(e -> {
            try {
                String employeeID = employeeIdTextField.getText();
                double salary = Double.parseDouble(salaryTextField.getText());
                double tax = Double.parseDouble(taxTextField.getText());

                generatePaySlip(employeeID, salary, tax);
                JOptionPane.showMessageDialog(null, "Pay Slip generated successfully!");
            } catch (NumberFormatException | SQLException ex) {
                handleException(ex);
            }
        });

        payrollFrame.add(employeeIdLabel);
        payrollFrame.add(employeeIdTextField);
        payrollFrame.add(salaryLabel);
        payrollFrame.add(salaryTextField);
        payrollFrame.add(taxLabel);
        payrollFrame.add(taxTextField);
        payrollFrame.add(calculateButton);
        payrollFrame.add(generatePaySlipButton);

        payrollFrame.setVisible(true);
    }

    private static void saveEmployeeDetails(String name, String employeeID) throws SQLException {
    	 try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	    	
            String query = "INSERT INTO employees (name, employee_id) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, employeeID);

               preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	
            
        
    }

    private static void markAttendance(String employeeID) throws SQLException {
    		try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD) ;
	            String query = "INSERT INTO attendance (employee_id, attendance_date) VALUES (?, ?)";
	    		PreparedStatement preparedStatement = connection.prepareStatement(query);
	                preparedStatement.setString(1, employeeID);
	                preparedStatement.setDate(2, new java.sql.Date(System.currentTimeMillis()));

	                preparedStatement.executeUpdate();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
         
    }

    private static double getTaxDeduction(String employeeID) throws SQLException {
        // Implement tax deduction retrieval logic
        // For simplicity, return a fixed value, replace with actual logic
        return 100.0;
    }

    private static double calculateNetSalary(double salary, double tax) {
        return salary - tax;
    }

    private static void generatePaySlip(String employeeID, double salary, double tax) throws SQLException {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "INSERT INTO payslip (employee_id, salary, tax, net_salary, generated_date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, employeeID);
                preparedStatement.setDouble(2, salary);
                preparedStatement.setDouble(3, tax);
                preparedStatement.setDouble(4, calculateNetSalary(salary, tax));
                preparedStatement.setDate(5, new java.sql.Date(System.currentTimeMillis()));

                preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
           
    }
    
    public static void showPaySlipDialog() {
        JFrame paySlipFrame = new JFrame("Pay Slip");
        paySlipFrame.setSize(500, 300);
        paySlipFrame.setLayout(new GridLayout(4, 2));

        JLabel employeeIdLabel = new JLabel("Enter Employee ID:");
        JTextField employeeIdTextField = new JTextField();

        JButton showPaySlipButton = new JButton("Show Pay Slip");
        
        showPaySlipButton.setBackground(new Color(45, 100, 107));
        showPaySlipButton.setForeground(Color.white);

        JTextArea paySlipTextArea = new JTextArea();
        paySlipTextArea.setEditable(false);

        showPaySlipButton.addActionListener(e -> {
            try {
                String employeeID = employeeIdTextField.getText();

                if (!employeeID.isEmpty()) {
                    String paySlip = getPaySlip(employeeID);
                    paySlipTextArea.setText(paySlip);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Employee ID.");
                }
            } catch (SQLException ex) {
                handleException(ex);
            }
        });

        paySlipFrame.add(employeeIdLabel);
        paySlipFrame.add(employeeIdTextField);
        paySlipFrame.add(showPaySlipButton);
        paySlipFrame.add(paySlipTextArea);

        paySlipFrame.setVisible(true);
    }

    private static String getPaySlip(String employeeID) throws SQLException {
        StringBuilder paySlip = new StringBuilder();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "SELECT * FROM payslip WHERE employee_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employeeID);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                paySlip.append("----------------------------------------------------------------------------------------------\n");
                paySlip.append("\t\tPAY SLIP \n");
                paySlip.append("----------------------------------------------------------------------------------------------\n");
                paySlip.append("\tEmployee ID:\t").append(resultSet.getString("employee_id")).append("\n");
                paySlip.append("\tSalary:\tRs.").append(resultSet.getDouble("salary")).append("\n");
                paySlip.append("\tTax Deduction:\tRs.").append(resultSet.getDouble("tax")).append("\n");
                paySlip.append("\tNet Salary:\tRs.").append(resultSet.getDouble("net_salary")).append("\n");
                paySlip.append("\tGenerated Date: ").append(resultSet.getDate("generated_date")).append("\n\n");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return paySlip.toString();
    }


    private static void handleException(Exception e) {
        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
