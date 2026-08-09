package com.restassuredcucumberframework.UtilityFiles;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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
    try (InputStream inputStream = Utils.class.getClassLoader()
            .getResourceAsStream("com/restassuredcucumberframework/resources/globalData.properties")) {
        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            String[] candidatePaths = {
                "src/test/resources/com/restassuredcucumberframework/resources/globalData.properties",
                "src/test/java/com/restassuredcucumberframework/resources/globalData.properties",
                "demo/src/test/resources/com/restassuredcucumberframework/resources/globalData.properties",
                "demo/src/test/java/com/restassuredcucumberframework/resources/globalData.properties"
            };

            InputStream fileStream = null;
            for (String candidatePath : candidatePaths) {
                Path path = Paths.get(candidatePath);
                if (Files.exists(path)) {
                    fileStream = Files.newInputStream(path);
                    break;
                }
            }

            if (fileStream == null) {
                throw new IOException("Unable to find globalData.properties in classpath or known project locations");
            }

            try (InputStream resolvedStream = fileStream) {
                prop.load(resolvedStream);
            }
        }
    }
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
