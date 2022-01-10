Feature: Validating Place APIs

	@AddPlace
  Scenario Outline: Verify if place is added successfully
    Given Add "<name>", "<address>" and "<language>" To AddPlace Payload
    When User Calls "addPlaceAPI" Using "POST" Http Request
    Then The API Call Get Success With SatusCode 200
    And "status" In Response Body Is "OK"
    And Verify PlaceId Created Maps To "<name>" Using "getPlaceAPI" 
    
    Examples: 
      | name    | address   | language |
      | arun    | India     | Telugu   |
      #| sai |   | UK        | English  |
      #| bharath | Australia | English  |  
      
	@DeletePlace
	Scenario: Verify if place is deleted successfully
		Given Add PlaceId To DeletePlace Payload
		When User Calls "deletePlaceAPI" Using "DELETE" Http Request
    Then The API Call Get Success With SatusCode 200
    And "status" In Response Body Is "OK"