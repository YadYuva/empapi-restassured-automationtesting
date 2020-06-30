Feature: Delete employee

Background:
Given service request timeout is set

Scenario Outline: Delete employee using Delete with valid logged in user
Given the user logged in with <username> and <password>
And the <employeename> of the employee to be deleted
When the client sends the Delete call
Then the service status code is 203 

Examples:
|username|password|employeename|
|Lakshmi.K@cts.com|pass123|Yadvik|

Scenario: User should not be able to Delete with incorrect username
Given the user logged in with Ram@ffs.com and pass123
Given the Yadvik of the employee to be deleted
When the client sends the Delete call
Then the service status code is 404 

Scenario: User should not be able to Delete with incorrect password
Given the user logged in with Lakshmi.K@cts.com and pass456
Given the Yadvik of the employee to be deleted
When the client sends the Delete call
Then the service status code is 404

