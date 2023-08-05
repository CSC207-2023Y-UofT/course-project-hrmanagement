# HR Management System
This project delivers a system that allows a company to manage its employee schedules and pay.
A User of the system may be a manager or an employee. An employee can set their availability and can request time off using this system. The manager can generate their employees' schedules, have the system calculate their pay, and accept or reject time off requests.

## Application Walkthrough
The application is launched from the HRManagement.java class. The application launches into a portal (positionBox.java) that allows the user to select their position in the team. From here, they may either select employee or manager using the buttons. If they select manager, it takes them to a sign-up page directly (ManagerBox.java). Here they log in with an existing username and password, and this takes them to the manager portal where they have access to the manager functionalities. If, in the selection box, they choose employee, this takes them to another selection page which allows for either signup or login. From here, they may either select sign-up which takes them to an employee form, allowing them to enter personal information and create an employee account. Otherwise, they can select login, and log in to their employee account, leading to the employee portal which gives access to the employee functionalities.

## Design Patterns