Feature: Validating place API's

  @AddPlace
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add Place Payload with <name>,<address>,<language>
    When user calls "addPlaceAPI" with "Post" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to <name> using "getPlaceAPI"

    Examples:
      | name   | address          | language |
      | House1 | 120 World center | English  |
   #   | House2 | 140 world center | Spanish  |

  @DeletePlace
  Scenario: Verify if delete place functionality is working
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "POST" http request
    Then the API call is success with status code 200
   And "status" in response body is "OK"

