# Floor-Master
July 2017

Tech Stack:

Java 8
Maven
Spring
ConsoleIO (https://github.com/davidKolesar/Console-IO)
JUnit
Regex

Architecture:
OOP 
AOP
MVC


This program was created for representatives at a flooring company for the purpose of taking customer orders over the telephone. The programmers were given business specifications they were required to meet - some were very specific while others were very general. The program was designed in small teams with a flowchart, UML, user stories, issues, and resolutions were created on JIRA / Crucible / Sprint. The program, however, was written entirely solo. It was also tested with JUnit, although these tests are not available at this time. 

The program has the following functionality:

1. Allows users to create new orders.
2. User orders are saved in a text file in a specific format to work with legacyware (as outlined in business specifications).
3. Program uses validation and makes user experience more convenient with shortcuts and integer options. 
4. Program uses validation. 
5. Program follows MVC model.
6. Program uses dependency injection through Spring - each daoImpl has an interface. 
7. Program relies on fundamentals of OOP.
8. Program also relies on fundamentals of AOP to allow audit procedures. 
9. If an order is edited, added, or deleted, a text file keeps track of which action was taken on what order along wit the date of change.
10. Program encodes all orders to a text file and decodes them when prompted by user (user can read all orders on any given date).
11. User can search for orders within a date by order number.
12. Program automaticallly calculates price and tax for each order by product type, material cost, and amount in square feet.   


Notable Features: 

1. In instances where the program reads options from a text document (lines 109 - 114 in FloorMasterController for example), the program actually reads the input from the file and generates a number based on how many options are there. The purpose of this is to allow refactoring the program (if need be) to require minimal time and effort.

2. Program uses regex to ensure user can input any ASCII characters (including the TOKEN) without altering how the data is read.

3. Program automatically detects the local area date, which is used in the creation of audit files and can be used in the creation of orders.  

4. Program reads in state taxe rate and products offered from a text file, so changes to these can be altered with minimal effort. 

5. Program allows user to edit date -- meaning orders can be placed in the future or can be written to the past in the case of hardware failures. In this case, the program will delete the old entry and place the order within the text file of the new date automatically. 

6. In the process of editing or creating orders to new dates, files are automatically created if they do not exist.
