package com.restassuredcucumberframework.resources;

import java.util.ArrayList;
import java.util.List;

import com.restassuredcucumberframework.POJOCLASSES.AddPlaceMapPOJOClass;
import com.restassuredcucumberframework.POJOCLASSES.locationJSONPOJOClass;
import com.restassuredcucumberframework.UtilityFiles.Utils;

public class TestDataBuild extends Utils{
    public AddPlaceMapPOJOClass addPlacePayload(String name, String language, String address){
         AddPlaceMapPOJOClass addplace=new AddPlaceMapPOJOClass();
    addplace.setAccuracy(50);
    addplace.setAddress(address);
    addplace.setLanguage(language);
    addplace.setName(name);
    addplace.setPhone_number("(+91) 983 893 3937");
    addplace.setWebsite("http://google.com");
    List <String> typesList = new ArrayList<String>();
    typesList.add("shoe park"); 
    typesList.add("shop");
    addplace.setTypes(typesList);
    locationJSONPOJOClass location=new locationJSONPOJOClass();
    location.setLat(-38.383494);
    location.setLng(33.427362);
    addplace.setLocation(location);
        return addplace;
    }
public String deletePlacePayload(String place_id1){
    return "{\n" +
            "    \"place_id\":\""+place_id1+"\"\n" +
            "}";

}
}
