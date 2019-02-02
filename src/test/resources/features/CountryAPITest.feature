Feature: Verify country API

	@e2e
  Scenario Outline: verify all countries
    Given user calls get all country API and expect status code 200
    Then verify country "<country>" present is according to status "<status>"

    Examples: 
      | country | status |
      | US      | true   |
      | DE      | true   |
      | GB      | true   |
      | asd     | false  |

	@e2e
  Scenario Outline: Verify individual country
    Given user calls get single country api with "<countryCode>" and expect status code 200
    Then verify country name as "<countryName>" information is displayed
    And verify correct message "Country found matching code" is displayed

    Examples: 
      | countryCode | countryName                                          |
      | US          | United States of America                             |
      | DE          | Germany                                              |
      | GB          | United Kingdom of Great Britain and Northern Ireland |

	@e2e
  Scenario Outline: Verify non existing country
    Given user calls get single country api with "<countryCode>" and expect status code 200
    Then verify correct message "No matching country found for requested code" is displayed

    Examples:
      | countryCode |
      | ZZ          |

#	@e2e
  Scenario Outline: Verify by adding new country
    Given user calls add country api with "<name>", "<alpha2_code>" and "<alpha3_code>" and expect status code 201
    When user calls get single country api with "<alpha2_code>" and expect status code 200
		Then verify country name as "<name>" information is displayed
    Examples: 
      | name         | alpha2_code | alpha2_code |
      | Test country | TC          | TCY         |
