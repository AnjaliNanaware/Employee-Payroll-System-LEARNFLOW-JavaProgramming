package com.payroll.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeePayrollSystemUI {
    private JFrame frame;

    public EmployeePayrollSystemUI() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Employee Payroll System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER,300,25));
        frame.setBackground(new Color(100, 100, 100));

        JButton employeeDetailsButton = new JButton(" Employee Details ");
        JButton attendanceButton = new JButton(" Attendance ");
        JButton payrollButton = new JButton(" Payroll ");
        JButton showPayslip=new JButton(" Show Payslip "); 
        JLabel title=new JLabel("---------------------------Employee Payroll System--------------------------");
        
        employeeDetailsButton.setBackground(new Color(45, 100, 107));
        employeeDetailsButton.setForeground(Color.white);
      
        attendanceButton.setBackground(new Color(45, 100, 107));
        attendanceButton.setForeground(Color.white);
        
        payrollButton.setBackground(new Color(45, 100, 107));
        payrollButton.setForeground(Color.white);
        
        showPayslip.setBackground(new Color(45, 100, 107));
        showPayslip.setForeground(Color.white);

        employeeDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UIActions.showEmployeeDetailsDialog();
                } catch (Exception ex) {
                    handleException(ex);
                }
            }
        });

        attendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UIActions.showAttendanceDialog();
                } catch (Exception ex) {
                    handleException(ex);
                }
            }
        });

        payrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UIActions.showPayrollDialog();
                } catch (Exception ex) {
                    handleException(ex);
                }
            }
        });
        
        showPayslip.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
                    UIActions.showPaySlipDialog();
                } catch (Exception ex) {
                    handleException(ex);
                }
				
			}
        	
        });
        frame.add(title);
        frame.add(employeeDetailsButton);
        frame.add(attendanceButton);
        frame.add(payrollButton);
        frame.add(showPayslip);

        frame.setVisible(true);
    }

    private void handleException(Exception e) {
        JOptionPane.showMessageDialog(frame, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
