package Payroll;

// Entity

public class ContractWorker extends Worker {
    private double hourlyWage;

    public ContractWorker(String username, String name, double taxRate, double hourlyWage) {
        super(username, name, taxRate);
        this.hourlyWage = hourlyWage;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    private void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
}
