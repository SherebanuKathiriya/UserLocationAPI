package com.ambula.taskproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.springframework.http.ResponseEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@RunWith(SpringRunner.class) // declare that the test should run with SpringRunner
@SpringBootTest(webEnvironment = org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserControllerTest {

    @Autowired // inject the necessary beans from the Spring context
    private TestRestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUserLocation() throws Exception {
        // prepare a request body for the create_data API
        String requestBody = "{\"name\": \"John Doe\", \"latitude\": 37.7749, \"longitude\": -122.4194}";
        
        // send a POST request to the create_data API with the request body
        ResponseEntity<String> response = restTemplate.postForEntity("/create_data", requestBody, String.class);
        
        // assert that the response status code is 200 OK and the response body is not empty
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testUpdateUserLocation() throws Exception {
        // insert a user location record into the in-memory database
        userRepository.save(new UserLocation("Jane Doe", 37.7749, -122.4194));
        
        // prepare a request body for the update_data API
        String requestBody = "{\"name\": \"Jane Doe\", \"latitude\": 37.7858, \"longitude\": -122.4064}";
        
        // send a PATCH request to the update_data API with the request body
        ResponseEntity<String> response = restTemplate.exchange("/update_data/Jane Doe", HttpMethod.PATCH, new HttpEntity<>(requestBody), String.class);
        
        // assert that the response status code is 200 OK and the response body is not empty
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetNearestUsers() throws Exception {
        // insert two user location records into the in-memory database
        userRepository.save(new UserLocation("John Doe", 37.7749, -122.4194));
        userRepository.save(new UserLocation("Jane Doe", 37.7858, -122.4064));
        
        // send a GET request to the get_users/N API with N = 1
        ResponseEntity<String> response = restTemplate.getForEntity("/get_users/1", String.class);
        
        // assert that the response status code is 200 OK and the response body is not empty
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        
        // parse the response body as a JSON array of user location objects
        JSONArray jsonArray = new JSONArray();
        
        // assert that the JSON array contains one user location object with name "John Doe"
        assertEquals(1, ((CharSequence) jsonArray).length());
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        assertEquals("John Doe", jsonObject.getAsString("name"));
        assertEquals(37.7749, (Double) jsonObject.get("latitude"), 0.0001);
        assertEquals(-122.4194, (Double)jsonObject.get("longitude"), 0.0001);
    }

}
