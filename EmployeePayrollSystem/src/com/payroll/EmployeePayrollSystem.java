package com.payroll;

import javax.swing.*;
import com.payroll.ui.EmployeePayrollSystemUI;

public class EmployeePayrollSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeePayrollSystemUI());
    }
}