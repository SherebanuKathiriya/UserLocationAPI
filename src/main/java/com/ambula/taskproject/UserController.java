/**
 * 
 */
package com.ambula.taskproject;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class UserController {
	@Autowired
    private UserRepository userRepository;

    @PostMapping("/create_data")
    public ResponseEntity<String> createTable() {
        userRepository.createTable();
        return ResponseEntity.ok("Table created successfully!");
    }

    @PostMapping("/update_data")
    public ResponseEntity<String> updateUserLocation(@RequestBody UserLocation userLocation) {
        userRepository.updateUserLocation(userLocation);
        return ResponseEntity.ok("User location updated successfully!");
    }

    @GetMapping("/get_users/{n}")
    public ResponseEntity<List<UserLocation>> getUsers(@PathVariable int n) {
        List<UserLocation> users = userRepository.getNearestUsers(n);
        return ResponseEntity.ok(users);
    }
}
