import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Iterator;

public class Getrequest
{
    @BeforeTest
    public void setup()
    {
        System.out.println("in set up");
    }

    @Test
    public  void getusers()
    {
        try {
            Response res = RestAssured.given().get("https://reqres.in/api/users?page=2");
            System.out.println(res.statusCode());
            Assert.assertEquals(200, res.statusCode());
            System.out.println(res.asPrettyString());
            System.out.println("getting value from json:" + getJsonvalue(res.asPrettyString(), "first_name"));
        }
        catch (Exception e)
        {
            System.out.println("getvalue"+e.getMessage());
        }
    }

    @Test
    public void getrequest() throws JsonProcessingException {
       Response res = RestAssured.given().auth().oauth2("Bearer b06758aa0fcd5952f7cfcf53dfd095b452899695e5040544071734759c113c32").get("https://restful-booker.herokuapp.com/booking/1689");
        System.out.println(res.statusCode());
        Assert.assertEquals(200,res.statusCode());

        String checkinvalue =  res.jsonPath().getString("bookingdates.checkin");
        System.out.println(res.asPrettyString());
        System.out.println(checkinvalue);
        System.out.println(res.getHeaders());

        System.out.println(res.getTime());
        System.out.println(res.getStatusLine());

        //converting json string to json object using jackson
        ObjectMapper mapper = new ObjectMapper();
          JsonNode json = mapper.readTree(res.asString());
        json.get("bookingdates").get("checkin").toString();
        //json.path("bookingdates").path("checkin").asText();
        System.out.println("jackson databinding:" + json.get("bookingdates").get("checkin").asText());

      //getting jsonpath using json path string
        JsonPath js = new JsonPath(res.asString());
        js.getString("$.bookingdates.checkin");

        System.out.println("values using jsonpath:"+js.getString("bookingdates.checkin"));

        //Using Gson library
        Gson gson = new Gson();
        JsonObject root = JsonParser.parseString(res.asString()).getAsJsonObject();

        // Access nested object and value
        JsonObject bookingDates = root.getAsJsonObject("bookingdates");
        String checkinDate = bookingDates.get("checkin").getAsString();
        System.out.println("using Gson library:"+checkinDate);

        //using org.json
        JSONObject jsonroot = new JSONObject(res.asString());

        // Navigate to nested object
        JSONObject jsonbookingDates = jsonroot.getJSONObject("bookingdates");

        // Get the "checkin" value
        String jsoncheckinDate = jsonbookingDates.getString("checkin");

        System.out.println("json using:"+jsoncheckinDate);
        System.out.println(getJsonvalue(res.toString(),"checkin"));


    }

    public static String getJsonvalue(String response, String key)
    {

        JSONObject json = new JSONObject(response);
        Iterator<?> keys = json.keys();

        while (keys.hasNext()) {
            String currentKey = (String) keys.next();

            if (currentKey.equals(key))
            {
                return json.get(key).toString();
                //break;

            }
           else {
                Object value = json.get(currentKey);

                if (value instanceof JSONObject) {
                    String result = getJsonvalue(((JSONObject) value).toString(), key);
                    if (result != null) return result;
                } else if (value instanceof JSONArray) {
                    JSONArray array = (JSONArray) value;
                    for (int i = 0; i < array.length(); i++) {
                        Object element = array.get(i);
                        if (element instanceof JSONObject) {
                            String result = getJsonvalue(((JSONObject) element).toString(), key);
                            if (result != null) return result;
                        }
                    }
                }
            }
        }

        return null; // Key not found




    }

    @Test
    public void postrequest()
    {

    }

    @AfterTest
    public void closeconnection()
    {
        System.out.println("in Tear down");
    }


}
