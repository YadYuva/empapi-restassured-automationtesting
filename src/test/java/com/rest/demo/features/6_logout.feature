Feature: Logout from Employee system

Scenario: Logout from current session
Given the user logs out from the system
When the client get logout session
Then the service status code is 200
  
