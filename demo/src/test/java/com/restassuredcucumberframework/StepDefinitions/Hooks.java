package com.restassuredcucumberframework.StepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
    @Before("@DeletePlaceAPI")
    public void beforeScenario() throws IOException {
        System.out.println("Before Scenario");
        stepDefinations stepdef = new stepDefinations();
        if (stepDefinations.place_id1 == null) {
            stepdef.add_place_payload("Leelavathi", "Tamil", "Trinity House");
            stepdef.user_calls_add_place_api_with_post_http_request("AddPlaceAPI", "POST");
            stepdef.verify_if_created_maps_to_using("place_id", "Leelavathi", "GetPlaceAPI");
        }
    }
}


