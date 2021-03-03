package Resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

public static AddPlace addPlacePayLoad(String name,String address, String language){
    AddPlace place1 = new AddPlace();
    List<String> types = new ArrayList<String>();
    Location location = new Location();

    place1.setAccuracy(50);
    place1.setName(name);
    place1.setPhone_number("(+91) 983 893 3937");
    place1.setAddress(address);
    place1.setLanguage(language);
    place1.setWebsite("http://google.com");

    location.setLat(-38.383494);
    location.setLng(33.427362);
    place1.setLocation(location);

    types.add("shoe park");
    types.add("shop");
    place1.setTypes(types);
    return place1;
}

public static String deletePlacePayload(String placeid){
    return "{\r\n    \"place_id\":\""+placeid+"\"\r\n}";
}
}
