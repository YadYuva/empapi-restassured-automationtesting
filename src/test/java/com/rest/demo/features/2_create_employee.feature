Feature: Create Employee
  Background:
    Given service request timeout is set
  Scenario Outline: create employee [Single]
  	Given the user logged in with Lakshmi.K@cts.com and pass123
    And the employee first name as <first_name>
    And the employee last name as <last_name>
    And the employee email as <email>
    When the client create single employee
    Then the service status code is 201
    And the response has successful operation
    
  Examples:
  |first_name|last_name|email|
  |Ashwini|Kumar|Ashwini.K@cts.com|
  |Ram|kumar|Ram.S@gmail.com|
  |Yadvik|Ram|Yad.R@cts.com|
  

Scenario: create employees [Multiple - List]
Given the user logged in with Lakshmi.K@cts.com and pass123
Given the employee names as list
		| Priya | Sarath | LakshmiK | 
When the client create an employee records as list 
Then the service status code is 201 
And the response has successful operation


Scenario: User should not be able to Create with incorrect login credential
Given the user logged in with Priya@gmail.com and pass123
And the employee first name as <first_name>
And the employee last name as <last_name>
And the employee email as <email>
When the client create single employee
Then the service status code is 404



