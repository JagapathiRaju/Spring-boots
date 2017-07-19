Feature: Employee Default Rest Service call for all
  Scenario: client makes call to GET /listAllEmployees
    When the client calls /listAllEmployees
    Then the client receives status code of 200
    And the client response should be JSON: /response/allEmployeeresponse.json