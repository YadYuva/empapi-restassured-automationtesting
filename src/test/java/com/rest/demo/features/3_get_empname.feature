Feature: Get Employee by name
  Background:
    Given service request timeout is set
   
   Scenario: Get Employee by valid name
    Given the employee first name Sarath to search
    When the client get employee by employee name
    Then the service status code is 200    
    
   Scenario: Get Employee by Invalid employeename 
    Given the employee first name Srihari123 to search
    When the client get employee by employee name
    Then the service status code is 404
 