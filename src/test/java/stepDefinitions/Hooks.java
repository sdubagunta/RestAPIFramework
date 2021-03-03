package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        MyStepDefinitions ms = new MyStepDefinitions();
        if(ms.placeid == null){
            ms.add_place_payload_with("Swathi","NewYork City", "Spanish");
            ms.user_calls_with_http_request("addPlaceAPI","POST");
            ms.verify_placeid_created_maps_to_using("Swathi","getPlaceAPI");
        }
    }
}
