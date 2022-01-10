package resources.testdata;

import java.util.ArrayList;
import java.util.List;

import pojo.serialization.AddPlace;
import pojo.serialization.Location;

public class Payload {

	public AddPlace addPlacePayload(String name, String address, String language) {
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");
		List<String> list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		ap.setTypes(list);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		return ap;
	}

	public String deletePlacePayload(String place_id) {
		return "{\r\n" + "    \"place_id\":\"" + place_id + "\"\r\n" + "}";
	}

}
