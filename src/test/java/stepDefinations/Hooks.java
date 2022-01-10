package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void getPlaceId() throws IOException {
		RsaMaps rm = new RsaMaps();
		if (RsaMaps.place_id == null) {
			rm.add_to_addplace_payload("bunny", "karimnagar", "telugu");
			rm.user_calls_using_http_request("addPlaceAPI", "POST");
			rm.verify_placeId_created_maps_to_using("bunny", "getPlaceAPI");
		}
	}
}
