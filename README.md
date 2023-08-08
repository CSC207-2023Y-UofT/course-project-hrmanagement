# HR Management System

This project delivers a system that allows a company to manage its employee schedules and pay.
A User of the system may be a manager or an employee. An employee can set their availability and can request time off using this system. The manager can generate their employees' schedules, have the system calculate their pay, and accept or reject time off requests.

# Project Overview
The application is launched from the HRManagement.java class. The application launches into a portal (positionBox.java) that allows the user to select their position in the team. From here, they may either select employee or manager using the buttons. If they select manager, it takes them to a sign-up page directly (ManagerBox.java). Here they log in with an existing username and password, and this takes them to the manager portal where they have access to the manager functionalities. If, in the selection box, they choose employee, this takes them to another selection page which allows for either signup or login. From here, they may either select sign-up which takes them to an employee form, allowing them to enter personal information and create an employee account. Otherwise, they can select login, and log in to their employee account, leading to the employee portal which gives access to the employee functionalities.

# Overall Design

# Features

## User Login Feature

## Time-Off Feature

## Payroll Feature

The Payroll Feature is a comprehensive tool designed to streamline salary calculation, employee data management, and timesheet integration. It provides a user-friendly graphical interface for efficient interaction and accurate computation of salaries for both employees and managers. 

### Key Functionalities

The key functionalities of the payroll feature include:

#### Salary Calculation
The core functionality of payroll is to calculate salaries for employees and managers. This feature calculates employee salary based on number on days and hours worked. 
It uses role-specific hourly rates and includes an option for adding bonuses. 

#### Employee Data Management
The Payroll Feature seamlessly integrates with the employee database. It efficiently extracts essential employee information, including roles and other relevant data, essential for the salary calculation process. This integration minimizes data entry efforts and enhances accuracy.

#### Timesheet Integration
Users have the flexibility to fine-tune timesheet information to match the actual hours worked by employees. The system allows adjustments to start and end dates, hours per day, and bonuses. These customizable timesheets enable precise salary computations that reflect real-world work schedules.

#### Data Validation
To maintain the integrity of calculations and prevent erroneous inputs, the Payroll Feature incorporates robust data validation mechanisms. These mechanisms verify the correctness of input data, including date formats, numeric values, and more. If any discrepancies or errors are detected, informative error messages guide users toward correcting the inputs.

#### Database Flexibility
The Payroll Feature offers the versatility to read employee and timesheet data from either a MySQL database or CSV files. This flexibility enables the system to adapt to diverse data sources, enhancing its compatibility and practicality.

#### Graphical User Interface
The Payroll Feature offers an intuitive and visually appealing GUI. This GUI facilitates effortless interaction with the salary calculation process. Users can easily input timesheet adjustments, select employees, and initiate salary calculations, all through a streamlined and user-friendly interface.

### GUI Functionality
The graphical interface (GUI) of the Payroll Feature provides an array of functionalities that enhance user experience and streamline salary calculations:

**Employee List**: The GUI displays a list of employees, presenting their names and key information.
**Employee Selection**: Users can select individual employees from the list for whom they want to calculate salaries.

![Payroll Calculator Menu](./images/payroll_gui.png)

**Timesheet Adjustment**: Once an employee is selected, the interface allows adjustments to timesheet details such as start date, end date, hours per day, and bonus amount.
**Calculate Button**: Upon making necessary adjustments, users can initiate salary calculations by clicking the "Calculate" button.

![Timesheet GUI](./images/timesheetgui.png)

**Payroll Information**: After calculation, the GUI presents a comprehensive payroll information list for the selected employee. This information includes base salary, worked hours, bonus, and the final computed salary.

![Salary result](./images/salary_result_gui.png)

**Data Validation Error Messages**: Error messages display if users input invalid data, which guides them towards correcting the inputs.

![Date error](./images/date_error_gui.png)
![Invalid bonus](./images/invalid_bonus_gui.png)
![Invalid hours](./images/invalid_hours_gui.png)

This combination of functionalities empowers users to efficiently manage payroll calculations for employees and managers, backed by accurate data and an intuitive interface.

The Payroll Feature embodies an array of functionalities and database options, which collectively result in a robust and user-friendly payroll management solution.


## Schedule Feature


# Design Patterns

In payroll, several design patterns are implemented: 
- **Singleton Pattern**: The `PayrollCalculator` class follows the Singleton pattern, ensuring a single instance is shared across the application to improve memory usage and performance.
- **Dependency Inversion Principle (DIP)**: The feature adheres to the DIP through abstraction layers that decouple high-level components from low-level details, as seen in interfaces EmployeeDAO and TimesheetDAO the DAO layer that are implemented by CSV and MYSQL concrete classes, promoting code reusability and flexibility.
- **Model-View-Controller (MVC) Pattern**: The GUI design follows the MVC pattern, facilitating a clear separation of concerns between data representation (Model), user interface (View), and user interactions (Controller).
