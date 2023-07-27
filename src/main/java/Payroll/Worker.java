package Payroll;

// Entity

public class Worker {
    private String username;
    private String name;
    private double taxRate;

    public Worker(String username, String name, double taxRate) {
        this.username = username;
        this.name = name;
        this.taxRate = taxRate;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public double getTaxRate() {
        return taxRate;
    }
}