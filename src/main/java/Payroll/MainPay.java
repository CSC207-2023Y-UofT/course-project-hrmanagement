package Payroll;

import javax.swing.*;

public class MainPay {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PayrollGUI();
            }
        });
    }
}