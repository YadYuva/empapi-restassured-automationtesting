Feature: Update employees call using PUT
 Background:
Given service request timeout is set

Scenario Outline: Update employee using PUT
Given the user logged in with <username> and <password>
And the employee name <employeename> of the employee to be updated
When the client sends the Put call
Then the service status code is 202

Examples:
|username|password|employeename|
|Lakshmi.K@cts.com|pass123|Ram|

Scenario: User should not be able to Update incorrect login credential
Given the user logged in with Lakshmi.K@cts.com and pass456
Given the employee name Ram of the employee to be updated
When the client sends the Put call
Then the service status code is 404


