package Payroll.ui;

import Payroll.dao.TimesheetDAO;
import Payroll.entity.EmployeeEntity;
import Payroll.entity.TimesheetEntity;
import Payroll.usecase.EmployeeHelper;
import Payroll.usecase.TimesheetHelper;
import Payroll.usecase.PayrollCalculator;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Map;

/**
 * Swing-based GUI for payroll feature
 * Interacts with PayrollCalculator and DAO classes to display employee and timesheet data
 */
public class PayrollGUI extends JFrame {
    private JTable employeeTable;

    PayrollCalculator payrollCalculator;
    Object[][] employees;
    Map<String, TimesheetEntity> timesheetMap;

    public PayrollGUI() {
    }

    /**
     * Initialize and configure the JFrame with all of its components
     */
    public void run(TimesheetDAO timesheetDAO) {

        // Set Frame properties
        setTitle("Payroll Calculator");
        setSize(800, 500); // Set the width and height of the frame
        setPreferredSize(new Dimension(800, 500)); // Set the preferred size of the frame
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"Select", "Employee Id", "Last Name", "First Name", "Address", "Phone Number", "Role"};

        // TableModel customizes appearance of employee table in payroll
        DefaultTableModel tableModel = new DefaultTableModel(employees, columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                if (column == 0) {
                    boolean currentValue = (boolean) getValueAt(row, column);
                    aValue = !currentValue; // Toggle the checkbox state
                }

                // Clear the status of all rows.
                for(int i=0; i < employees.length; i++)
                    super.setValueAt(false, i, 0);

                super.setValueAt(aValue, row, column);
            }
        };

        employeeTable = new JTable(tableModel);
        employeeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        employeeTable.setAutoCreateRowSorter(true);
        // Increase the row height of the table
        employeeTable.setRowHeight(25); // Set the desired height in pixels

        // Set a custom CheckBox renderer for the first column
        employeeTable.getColumnModel().getColumn(0).setCellRenderer(new TableCellRenderer() {
            private final JCheckBox checkBox = new JCheckBox() {
                @Override
                public void setHorizontalAlignment(int alignment) {
                    // Ignore the alignment parameter and always set to center
                    super.setHorizontalAlignment(CENTER);
                }
            };

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                if (value == null) {
                    checkBox.setSelected(false);
                } else {
                    checkBox.setSelected((boolean) value);
                }

                for (int i = 1; i < employeeTable.getColumnCount(); i++) {
                    employeeTable.getColumnModel().getColumn(0);
                }

                return checkBox;
            }
        });

        // Indent the content of each column
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER); // Center align the content
        renderer.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10)); // Set left and right padding
        for (int i = 1; i < employeeTable.getColumnCount(); i++) {
            employeeTable.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        // Set the column names (headers) to bold
        JTableHeader header = employeeTable.getTableHeader();
        Font originalFont = header.getFont();
        Font boldFont = new Font(originalFont.getName(), Font.BOLD, originalFont.getSize());
        header.setFont(boldFont);

        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton calculateButton = new JButton("Calculate Salary"); // calculate salary button
        JButton closeButton = new JButton("Close"); // close button
        buttonPanel.add(calculateButton);
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);


        // load employee data when employee's row is selected
        calculateButton.addActionListener(e -> {
            int selectedRow = getSelectedEmployeeRow();
            if (selectedRow != -1) {
                // 1. build an employee entity for the selected employee.
                EmployeeEntity selectedEmployee = EmployeeHelper.buildEmployeeEntity(employeeTable, selectedRow);

                // 2. Show an input dialog to get additional data from the user
                TimesheetWindow timesheetWindow = new TimesheetWindow(PayrollGUI.this, timesheetMap);
                String[] timesheetData = timesheetWindow.showInputDialog(selectedEmployee);

                // 3. If cancel button is clicked, the return value is empty.
                if (timesheetData == null)
                    return;

                // 4. Calculate the employee's salary using the role and timesheet data.
                double salary = payrollCalculator.calculateSalary(selectedEmployee, timesheetData);

                // 5. Build a timesheet entity using the employee and payroll data.
                TimesheetEntity timesheet = TimesheetHelper.createTimesheetEntity(selectedEmployee, timesheetData, salary);

                // Save the timesheet entity to database and update internal map of timesheets.
                String employName = EmployeeHelper.getEmployeeName(selectedEmployee);
                timesheetDAO.saveTimesheet(timesheet);
                timesheetMap.put(employName, timesheet);

                displayPayrollInfo(selectedEmployee, timesheet);
            } else {
                JOptionPane.showMessageDialog(PayrollGUI.this,
                        "Please select an employee first.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        closeButton.addActionListener(e -> dispose());

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit"); // exit menu button
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        exitMenuItem.addActionListener(e -> dispose());

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * The setter for payroll calculator.
     * @param payrollCalculator an instance of Payroll calculator
     */
    public void setPayrollCalculator(PayrollCalculator payrollCalculator) {
        this.payrollCalculator = payrollCalculator;
    }

    /**
     * The setter for all the employees.
     * @param employees a two dimensional array of String.
     */
    public void setEmployees(Object[][] employees) {
        this.employees = employees;
    }

    /**
     * The setter for timesheets.
     * @param timesheetMap a map of timesheet entity.
     */
    public void setTimesheetMap(Map<String, TimesheetEntity> timesheetMap) {
        this.timesheetMap = timesheetMap;
    }


    /**
     * Determines which row (which employee) is currently selected in table
     * @return row index
     */
    private int getSelectedEmployeeRow() {
        for (int i = 0; i < employeeTable.getRowCount(); i++) {
            boolean isSelected = (boolean) employeeTable.getValueAt(i, 0);
            if (isSelected) {
                return i;
            }
        }
        return -1;
    }

    private void displayPayrollInfo(EmployeeEntity selectedEmployee, TimesheetEntity timesheet){
        String employeeId = timesheet.getEmployeeId();
        String firstName = timesheet.getFirstName();
        String lastName = timesheet.getLastName();
        String startDate = timesheet.getStartDate();
        String endDate = timesheet.getEndDate();
        double salary = timesheet.getSalary();
        String role = selectedEmployee.getRole();
        String employeeName = EmployeeHelper.getEmployeeName(firstName, lastName);

        String salaryMessage =
                "Employee ID: " + employeeId + "\n"
                        + "Name: " + employeeName + "\n"
                        + "Role: " + role + "\n"
                        + "Start Date: " + startDate + "\n"
                        + "End Date: " + endDate + "\n"
                        + "Salary: " + "$" + salary + "\n"
                        + "\n"
                        + "Timesheet data saved!" + "\n"
                        + "\n";

        // Display the salary calculation result in a message dialog
        JOptionPane.showMessageDialog(PayrollGUI.this,
                salaryMessage,
                "Salary Calculation Result",
                JOptionPane.INFORMATION_MESSAGE);

    }
}