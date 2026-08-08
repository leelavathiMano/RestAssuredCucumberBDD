package com.restassuredcucumberframework.resources;

public enum APIResourcesWithEnumClass {

//Enum is the special class which is the collection of constant varaibles and methods
AddPlaceAPI("maps/api/place/add/json"),
GetPlaceAPI("maps/api/place/get/json"),
DeletePlaceAPI("maps/api/place/delete/json");
private String resource;

APIResourcesWithEnumClass(String resource) {
    this.resource=resource;
}
public String getResource() {
    return resource;
}
}
