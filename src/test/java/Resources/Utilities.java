package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.path.json.JsonPath;

import java.io.*;
import java.util.Properties;

public class Utilities {
        public static RequestSpecification request;

    public RequestSpecification requestSpecification() throws IOException {

        if(request == null) {
            PrintStream log = new PrintStream(new FileOutputStream("log.txt"));
            request = new RequestSpecBuilder()
                    .setBaseUri(getGlobalValue("baseurl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();

        }
        return request;
    }
    public String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("/Users/swathisanthosh/Downloads/Selenium/Workspace/RestAPIFramework/src/test/java/Resources/global.properties");
        prop.load(fis);
        return prop.getProperty(key);

    }

    public String getJsonPath(Response response,String key)
    {
        String resp=response.asString();
        JsonPath   js = new JsonPath(resp);
        return js.get(key).toString();
    }
}
