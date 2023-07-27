package Payroll;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainPay {

    public static void main(String[] args) throws ParseException {
        // temporary for testing purposes, delete later and replace with input/output class
        ContractWorker worker = new ContractWorker("johndoe1", "John Doe", 0.3, 25.00);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date startDate = sdf.parse("06/24/2023");
        Date endDate = sdf.parse("06/30/2023");

        ContractTimeSheet timeSheet = new ContractTimeSheet(startDate, endDate, 8);
        ContractPayrollCalculator calculator = new ContractPayrollCalculator();
        double grossPay = calculator.calculateTotalPay(worker, timeSheet);
        double netPay = calculator.calculateNetPay(worker, timeSheet);

        System.out.println("Username: " + worker.getUsername());
        System.out.println("Name: " + worker.getName());
        System.out.println("Hourly Wage: $" + worker.getHourlyWage());
        System.out.println("Total Hours: " + calculator.getTotalHoursWorked(timeSheet));
        System.out.println("Gross Pay: $" + calculator.calculateTotalPay(worker, timeSheet));
        System.out.println("Tax: $" + calculator.calculateTaxWithholding(grossPay, worker));
        System.out.println("Net Pay: $" + netPay);
    }
}
