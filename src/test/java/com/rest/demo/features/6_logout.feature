Feature: Logout from Employee system using get

Scenario: Logout from current session
Given the user logs out from the system
When the client get logout session
Then the service status code is 200
And the response has successful operation
  
