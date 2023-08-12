# HR Management System

This project delivers a system that allows a company to manage its employee schedules and pay.
A User of the system may be a manager or an employee. An employee can set their availability and can request time off using this system. The manager can generate their employees' schedules, have the system calculate their pay, and accept or reject time off requests.
___

## Table of Contents

- [Project Overview](#project-overview)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Design Patterns](#design-patterns)
___

## Project Overview
The HR Management System project is a Java software solution designed to streamline and automate various human resources processes within an organization. This project aims to simplify HR operations, improve efficiency, and enhance communication between HR personnel, employees, and management. The project provides a user-friendly interface for managing HR-related tasks and information.
___

## Technologies Used
**User interface components:** Java Swing - JFrame, JPanel, JButton, etc <br> 
**Layout Management:** layout managers - FlowLayout, GridLayout, etc <br> 
**Event handling:** Action Listener, Key Listener, MouseListener, etc <br> 
**Persistence and data storage:** JDBC for MYSQL Database, File I/O for CSV and TXT Files <br> 
**Version control and collaboration:** GitHub
___

## Installation
1. Clone the repository: `git clone https://github.com/CSC207-2023Y-UofT/course-project-hrmanagement.git`
2. Import the project in your preferred Java IDE.
3. Set up MySQL database and configure connection details.
4. Run the program from your IDE.
___

## Usage
1. Launch the application.
2. Select user role: either manager or employee
3. In the manager portal, managers can approve time-off requests, calculate payroll, and view schedules.
4. In the employee portal, employees can log in, sign up, and request time-off. 
___

## Features
___
### User Login Feature

The Login Feature is designed to allow a manager or employee to log in to their respective portals. This is the initial portal that will send people to where they need to go.

#### Key Functionalities

The key functionalities include:

##### Username/Password Validation

The functionality of this feature is not only to send each person to their proper portals, but to also check if their user and password are valid, allowing them to pass onto the system.

##### Employee Sign-Up

In the employee portal, the user is once again given two choices; they can either sign in or sign-up. The employee user is then allowed to input all information required to begin as a new employee.

##### GUI Function

There are different portals in the GUI login. Selection between managers and employees, and selection between sign in and sign-up.

___

### Payroll Feature

The payroll feature streamlines salary calculation, employee data management, and timesheet integration. It provides a user-friendly graphical interface for efficient interaction and computation of salaries for both employees and managers. As well, the feature saves employees' salary calculation result to their corresponding timesheets. 

#### Key Functionalities

The key functionalities of the payroll feature include:

##### Salary Calculation
The core functionality of payroll is to calculate salaries for employees and managers. This feature calculates employee salary based on the number of days and hours worked. 
It uses role-specific hourly rates and includes an option for adding bonuses. 

##### Employee Data Management
The Payroll Feature integrates with the employee database. It extracts employee information, including roles and other relevant data for the salary calculation process. This integration minimizes data entry efforts and enhances accuracy.

##### Timesheet Integration
Users have the flexibility to fine-tune timesheet information to match the actual hours worked by employees. The system allows adjustments to start and end dates, hours per day, and bonuses. The customizable timesheet GUI enables precise salary computations. Finally, **data persistence** allows the user to save the calculated salary to the timesheet. 

##### Data Validation
To maintain the integrity of calculations and prevent erroneous inputs, the payroll feature verifies the correctness of input data, including date formats and numeric values. If any discrepancies or errors are detected, informative error messages guide users toward correcting the inputs.

##### Database Flexibility
The payroll feature offers the versatility to read employee and timesheet data from either MySQL database or CSV files. 
This flexibility enables the system to adapt to diverse data sources, enhancing its compatibility and practicality. **Note:** MYSQL Database is designed as the main database and is regularly updated with the creation of new employees and employees' corresponding timesheets. 
CSV is designed as a backup database with information on existing employees and does not currently support update features. All updates are done through MYSQL. 

#### GUI Functionality
The graphical interface (GUI) of the Payroll Feature is accessed through the manager portal.

**Employee List**: The GUI displays a list of employees, presenting their IDs, names and key information.
**Employee Selection**: Managers can select individual employees from the list for whom they want to calculate salaries.

![Payroll Calculator Menu](./images/payroll_gui.png)

**Timesheet Adjustment**: Once an employee is selected, the interface allows adjustments to timesheet details such as start date, end date, hours per day, and bonus amount.
The interface also displays the employee's previous calculated salary amount. Upon making necessary adjustments, users can initiate salary calculations by clicking the "Calculate" button.

![Timesheet GUI](./images/timesheet_gui.png)

**Payroll Information**: After calculation, the GUI presents a comprehensive payroll information list for the selected employee. This information includes base salary, worked hours, bonus, and the final computed salary. The employee's new timesheet information is saved to the database. 

![Salary result](./images/salary_calculation_result.png)

**Data Validation Error Messages**: If the user selects or inputs invalid data, error message panels inform the user and guide them toward correcting the inputs.

![Select employee error GUI](./images/select_error_message_gui.png)
![Date error gui](./images/date_error_gui.png)
![Invalid bonus gui](./images/invalid_bonus_gui.png)
![Invalid hours gui](./images/invalid_hours_gui.png)

This combination of functionalities allows users to efficiently manage payroll calculations for employees and managers, backed by accurate data and an intuitive interface.
___

### Time-Off Feature

(Time-off request dates must be given in dd/mm/yy format to be appropriately entered into the database and read)

___

### Schedule Feature
The Schedule Feature allows managers to view a brief overview of the current week's schedule, detailing the names and ids of employees working on each day. This feature keeps track of each worker's start/end dates as well as any time offs that they have which are approved.

![Schedule GUI](./images/schedule_gui.png)
___

## Design Patterns

In payroll, several design patterns are implemented: 
- **Strategy Design Pattern**: Two concrete classes, `MySQLDataAccess` and `CSVDataAccess`, implement the `DataAccessStrategy` interface. These strategies encapsulate the details of loading employee and timesheet data from their respective sources. The strategy pattern ensures the data access logic is separate from the core logic. It also allows for easy switching between data sources and better modularity and extendability. 
- **Singleton Pattern**: The `PayrollCalculator` class follows the Singleton pattern, ensuring a single instance is shared across the application to improve memory usage and performance.
- **Data Access Object Pattern**: The concrete classes `CSVEmployeeDAO`, `MySQLEmployeeDAO`, `CSVTimesheetDAO`, and `MySQLTimesheetDAO` implement the respective `EmployeeDAO` and `TimesheetDAO` interfaces. The DAO pattern encapsulates data operations within the DAO classes and decouples the application logic from the details of data storage.
- **Model-View-Controller (MVC) Pattern**: The `PayrollGUI` design follows the MVC pattern, facilitating a clear separation of concerns between data representation (Model), user interface (View), and user interactions (Controller).
