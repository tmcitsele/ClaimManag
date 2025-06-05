@Regression @GovtScheme
Feature: Access options under Govt. Scheme in Claim Management Portal

  As a logged-in employee
  I want to interact with all options under the "Govt. Scheme" menu
  So that I can navigate to Enquiry, Case Trackers, and Reports

  Background:
    Given The user is logged into the Claim Management Portal
    And The user is on the landing page of the portal

  @Smoke
  Scenario: Open the Govt. Scheme dropdown menu
    When The user clicks on the "Govt. Scheme" menu
    Then The user should see the following options:
      | Enquiry                      |
      | Under Treatment Case Tracker |
      | Discharged Case Tracker      |
      | Pre-auth Closed Report       |
      | Request Report               |

  @Navigation
  Scenario: Navigate to Enquiry section
    When The user clicks on the "Govt. Scheme" menu
    And The user selects "Enquiry"
    Then The user should be redirected to the Enquiry page

  @Navigation
  Scenario: Navigate to Under Treatment Case Tracker
    When The user clicks on the "Govt. Scheme" menu
    And The user selects "Under Treatment Case Tracker"
    Then The user should be redirected to the Under Treatment Case Tracker page

  @Navigation
  Scenario: Navigate to Discharged Case Tracker
    When The user clicks on the "Govt. Scheme" menu
    And The user selects "Discharged Case Tracker"
    Then The user should be redirected to the Discharged Case Tracker page

  @Navigation
  Scenario: Navigate to Pre-auth Closed Report
    When The user clicks on the "Govt. Scheme" menu
    And The user selects "Pre-auth Closed Report"
    Then The user should be redirected to the Pre-auth Closed Report page

  @Navigation
  Scenario: Navigate to Request Report
    When The user clicks on the "Govt. Scheme" menu
    And The user selects "Request Report"
    Then The user should be redirected to the Request Report page
