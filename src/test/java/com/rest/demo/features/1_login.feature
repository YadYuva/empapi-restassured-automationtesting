Feature: Login to Employee API
  Background:
    Given service request timeout is set
  Scenario Outline: login to system
    Given the username as <user_id> and password as <password>
    When the client get session
    Then the service status code is 200
    And the response has successful operation
  Examples:
  |user_id|password|
  |Lakshmi.K@cts.com|pass123|
  |Ram.S@idbi.com|pass789|
  
  




