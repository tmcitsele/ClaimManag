@Regression @GovtScheme
Feature: Access options under Govt. Scheme in Claim Management Portal

  As a logged-in employee
  I want to interact with all options under the "Govt. Scheme" menu
  So that I can navigate to Enquiry, Case Trackers, and Reports

  Background:
    Given The user logs in with valid credentials

  @Smoke
  Scenario: Open the Govt. Scheme dropdown menu
    When The user hovers over the "Govt. Scheme" menu
    Then The user should see the following options:
      | Enquiry                      |
      | Under Treatment Case Tracker |
      | Discharged Case Tracker      |
      | Pre-auth Closed Report       |
      | Request Report               |

  @Navigation
  Scenario Outline: Navigate to a specific Govt. Scheme option
    When The user hovers over the "Govt. Scheme" menu
    And The user selects "<option>"
    Then The user should be redirected to the "<option>" page

    Examples:
      | option                        |
      | Enquiry                      |
      | Under Treatment Case Tracker |
      | Discharged Case Tracker      |
      | Pre-auth Closed Report       |
      | Request Report               |
