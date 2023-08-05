package Payroll;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class PayrollGUI extends JFrame {
    private JTable employeeTable;
    private JButton calculateButton;
    private JButton closeButton;
    private JMenuItem exitMenuItem;

    PayrollCalculator payrollCalculator;
    Object[][] employees;
    Map<String, TimesheetEntity> timesheetMap;

    public void setPayrollCalculator(PayrollCalculator payrollCalculator) {
        this.payrollCalculator = payrollCalculator;
    }

    public void setEmployees(Object[][] employees) {
        this.employees = employees;
    }

    public void setTimesheetMap(Map<String, TimesheetEntity> timesheetMap) {
        this.timesheetMap = timesheetMap;
    }

    public PayrollGUI() {
    }

    public void run() {

        // Set Frame properties
        setTitle("Employee Salary Calculator");
        setSize(800, 500); // Set the width and height of the frame
        setPreferredSize(new Dimension(800, 500)); // Set the preferred size of the frame
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"Select", "Employee Id", "Last Name", "First Name", "Address", "Phone Number", "Role"};

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
        calculateButton = new JButton("Calculate Salary");
        closeButton = new JButton("Close");
        buttonPanel.add(calculateButton);
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = getSelectedEmployeeRow();
                if (selectedRow != -1) {
                    // Show an input dialog to get additional data from the user
                    String employeeId = (String) employeeTable.getValueAt(selectedRow, 1);
                    String lastName = (String) employeeTable.getValueAt(selectedRow, 2);
                    String firstName = (String) employeeTable.getValueAt(selectedRow, 3);
                    String role = (String) employeeTable.getValueAt(selectedRow, 6);
                    String employName = firstName + " " + lastName;
                    String worksheetData[] = showInputDialog(employName);

                    // Cancel button is clicked.
                    if (worksheetData == null)
                        return;

                    // Perform the salary calculation based on the additional data
                    //String role = (String) employeeTable.getValueAt(selectedRow, 6);
                    double salary = payrollCalculator.calculateSalary(role, worksheetData);

                    String salaryMessage =
                            "Employee ID: " + employeeId + "\n"
                                    + "Name: " + firstName + " " + lastName + "\n"
                                    + "Role: " + role + "\n"
                                    + "Start Date: " + worksheetData[0] + "\n"
                                    + "End Date: " + worksheetData[1] + "\n"
                                    + "Salary: " + "$" + salary + "\n"
                                    + "\n";


                    // Display the salary calculation result in a message dialog
                    JOptionPane.showMessageDialog(PayrollGUI.this,
                            salaryMessage,
                            "Salary Calculation Result",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(PayrollGUI.this,
                            "Please select an employee first.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    private int getSelectedEmployeeRow() {
        for (int i = 0; i < employeeTable.getRowCount(); i++) {
            boolean isSelected = (boolean) employeeTable.getValueAt(i, 0);
            if (isSelected) {
                return i;
            }
        }
        return -1;
    }

    // Method to show the input dialog
    private String[] showInputDialog(String employeeName) {
        JPanel panel = new JPanel(new GridLayout(6, 2));

        // Set start date.
        String strStartDate = timesheetMap.get(employeeName).getStartDate();
        SpinnerDateModel startDateSpinnerDateModel = null;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(strStartDate);
            // Create a new SpinnerDateModel with the parsed date
            startDateSpinnerDateModel = new SpinnerDateModel(startDate, null, null, java.util.Calendar.DAY_OF_MONTH);

        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
            startDateSpinnerDateModel = new SpinnerDateModel();
        }

        // Set end date.
        String strEndDate = timesheetMap.get(employeeName).getEndDate();
        SpinnerDateModel endDateSpinnerDateModel = null;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date endDate = dateFormat.parse(strEndDate);
            // Create a new SpinnerDateModel with the parsed date
            endDateSpinnerDateModel = new SpinnerDateModel(endDate, null, null, java.util.Calendar.DAY_OF_MONTH);

        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
            endDateSpinnerDateModel = new SpinnerDateModel();
        }



        JLabel employeeNameLabel = new JLabel("Employee Name:");
        employeeNameLabel.setFont(employeeNameLabel.getFont().deriveFont(Font.PLAIN, 13)); // Increase the font size
        JLabel employeeNameValueLabel = new JLabel(employeeName);;

        JLabel startDateLabel = new JLabel("Start Date:");
        startDateLabel.setFont(startDateLabel.getFont().deriveFont(Font.PLAIN, 13)); // Increase the font size
        JSpinner startDateSpinner = new JSpinner(startDateSpinnerDateModel);
        JSpinner.DateEditor startDateEditor = new JSpinner.DateEditor(startDateSpinner, "MM/dd/yyyy");
        startDateSpinner.setEditor(startDateEditor);

        JLabel endDateLabel = new JLabel("End Date:");
        endDateLabel.setFont(endDateLabel.getFont().deriveFont(Font.PLAIN, 13)); // Increase the font size
        JSpinner endDateSpinner = new JSpinner(endDateSpinnerDateModel);
        JSpinner.DateEditor endDateEditor = new JSpinner.DateEditor(endDateSpinner, "MM/dd/yyyy");
        endDateSpinner.setEditor(endDateEditor);

        JLabel hoursPerDayLabel = new JLabel("Hours Per Day:");
        hoursPerDayLabel.setFont(hoursPerDayLabel.getFont().deriveFont(Font.PLAIN, 13)); // Increase the font size
        JTextField hoursPerDayField = new JTextField(10);
        hoursPerDayField.setFont(hoursPerDayLabel.getFont().deriveFont(Font.PLAIN, 13)); // Increase the font size
        hoursPerDayField.setText(String.valueOf(PayrollConstant.HoursPerDay));

        JLabel bonusLabel = new JLabel("Bonus:");
        bonusLabel.setFont(bonusLabel.getFont().deriveFont(Font.PLAIN, 13)); // Increase the font size
        JTextField bonusField = new JTextField(10);
        bonusField.setFont(bonusLabel.getFont().deriveFont(Font.PLAIN, 13)); // Increase the font size
        bonusField.setText(String.valueOf(PayrollConstant.DefaultBonus));

        JLabel blankLabel = new JLabel("");

        panel.add(employeeNameLabel);
        panel.add(employeeNameValueLabel);

        panel.add(startDateLabel);
        panel.add(startDateSpinner);
        panel.add(endDateLabel);
        panel.add(endDateSpinner);
        panel.add(hoursPerDayLabel);
        panel.add(hoursPerDayField);
        panel.add(bonusLabel);
        panel.add(bonusField);
        panel.add(blankLabel);

        // Set the preferred width and height for the input dialog
        panel.setPreferredSize(new Dimension(200, 100));

        while (true) {
            Object[] options = {"Calculate", "Cancel"};
            int result = JOptionPane.showOptionDialog(PayrollGUI.this, panel,
                    "Timesheet", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if (result == JOptionPane.OK_OPTION) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                String startDateStr = dateFormat.format(startDateSpinner.getValue());
                String endDateStr = dateFormat.format(endDateSpinner.getValue());
                String hoursPerDay = hoursPerDayField.getText();
                String bonus = bonusField.getText();

                // Perform date validation
                if (!DataValidator.isValidDate(startDateStr) || !DataValidator.isValidDate(endDateStr)) {
                    JOptionPane.showMessageDialog(PayrollGUI.this,
                            "Invalid date format. Please use the format MM/dd/yyyy.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (!DataValidator.isValidDouble(hoursPerDay))
                {
                    JOptionPane.showMessageDialog(PayrollGUI.this,
                            "Invalid number.  A sample of hours per day is: 7.5",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (!DataValidator.isValidDouble(bonus))
                {
                    JOptionPane.showMessageDialog(PayrollGUI.this,
                            "Invalid bonus value. ",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {
                    // Convert dates to Date objects for comparison
                    Date startDate = DataValidator.parseDate(startDateStr);
                    Date endDate = DataValidator.parseDate(endDateStr);

                    // Check if the End Date is after the Start Date
                    if (endDate.before(startDate)) {
                        JOptionPane.showMessageDialog(PayrollGUI.this,
                                "End Date must be after Start Date.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        return new String[]{startDateStr, endDateStr, hoursPerDay, bonus};
                    }
                }
            } else {
                // Return null if the user cancels the input
                return null;
            }
        }
    }

}

