@Regression @EnquiryPage
Feature: Claim search functionality in the Enquiry Page

  As a logged-in employee
  I want to search for claim details on the "Enquiry" page
  So that I can view relevant claim information

  Background:
    Given The user logs in with valid credentials
    And The user has navigated to the "Enquiry" page

  @Smoke
  Scenario: Search for a valid claim number on the Enquiry Page
    When The user enters the claim number "12345" and clicks the search button
    Then The user should see the details of the claim "12345"

  @ErrorHandling
  Scenario: Search for an invalid claim number on the Enquiry Page
    When The user enters an invalid claim number "99999" and clicks the search button
    Then The user should see an error message "Claim not found"

  @InputValidation
  Scenario: Attempt to search without entering a claim number
    When The user leaves the claim number field empty and clicks the search button
    Then The user should see a validation message "Claim number cannot be empty"

  @Functional
  Scenario: Clear the claim search results
    Given The user has searched for a claim number "12345"
    When The user clicks on the "Clear" button
    Then The claim number field should be empty
    And The claim results should be cleared
