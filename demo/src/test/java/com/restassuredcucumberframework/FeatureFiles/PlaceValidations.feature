Feature: Place Validations APIs
@AddPlaceAPI
Scenario Outline: verify if places being successfully added using the Add place API
Given Add place Payload with "<name>" "<language>" "<address>"
When User calls "AddPlaceAPI" with "POST" http request
Then API call is success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And Verify if "place_id" created maps to "<name>" using "GetPlaceAPI"

Examples:
        |name   |language  |address|
        |John   |English   |123 Street|
        |leela| french   |456 Avenue|
        |preksha    |spanish   |789 Boulevard|
@DeletePlaceAPI
 Scenario:verify delete place api is working fine
 Given delete place payload
 When User calls "DeletePlaceAPI" with "DELETE" http request
 Then API call is success with status code 200
And "status" in response body is "OK"
