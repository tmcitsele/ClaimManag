@Smoke @Login @login
Feature: Employee Login to Tata Memorial Hospital Claim Management Portal

  As a registered employee
  I want to log in using my portal credentials
  So that I can access internal services

  Background:
    Given The user is on the Tata Memorial Hospitals Claim Management login page

  @Positive
  Scenario Outline: Login with credentials
    When The user enters "<username>" as username
    And The user enters "<password>" as password
    And The user clicks the Sign In button
    Then The user should see <outcome>

    Examples:
      | username  | password  | outcome                                         |
      | 126267    | 491809    | the Claim Management landing page               |
      | wronguser | wrongpass | an error message "Invalid Username or Password" |

  @Negative
  Scenario: Login with empty credentials
    When The user leaves the username and password fields empty
    And The user clicks the Sign In button
    Then The user should see a validation message "Please Enter Employee-Portal User ID & Password"
