package com.restassuredcucumberframework.UtilityFiles;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

public class Utils {
    public static RequestSpecification addplacereq;
    
public RequestSpecification requestSpecification() throws IOException {
    if(addplacereq==null) {
    PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
 addplacereq  = new RequestSpecBuilder()
    .setBaseUri(getGlobalData("baseUrl")).setContentType(ContentType.JSON).addQueryParam("key", "qaclick123")
     .addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log))
    .setContentType(ContentType.JSON).build();
    return addplacereq;
    }
    return addplacereq;
}
public static String getGlobalData(String key) throws IOException {
    Properties prop=new Properties();
    FileInputStream fis=new FileInputStream("src/test/java/com/restassuredcucumberframework/resources/globalData.properties");
    prop.load(fis);
    String value=prop.getProperty(key);
    System.out.println(value);
    return value; 
}
public String getjsonPath(Response res, String key){
    String responseString = res.asString();
    JsonPath js=new JsonPath(responseString);
    return js.getString(key);
}
}
