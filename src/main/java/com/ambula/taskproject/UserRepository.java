package com.ambula.taskproject;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
   
    public void createTable() {
        String sql = "CREATE TABLE user_location (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), latitude DOUBLE, longitude DOUBLE)";
        jdbcTemplate.execute(sql);
    }

    public void updateUserLocation(UserLocation userLocation) {
        String sql = "UPDATE user_location SET name=?, latitude=?, longitude=?";
        jdbcTemplate.update(sql, userLocation.getName(), userLocation.getLatitude(), userLocation.getLongitude());
    }

    public List<UserLocation> getNearestUsers(int n) {
        String sql = "SELECT * FROM user_location ORDER BY SQRT(POWER(latitude, 2) + POWER(longitude, 2)) LIMIT ?";
        return (List<UserLocation>) jdbcTemplate.query(sql, (rs, rowNum) -> new UserLocation(rs.getString("name"), rs.getDouble("latitude"), rs.getDouble("longitude")), new Object[] {n});
    }
    
    public void save(UserLocation userLocation) {
        String sql = "INSERT INTO user_location (name, latitude, longitude) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, userLocation.getName(), userLocation.getLatitude(), userLocation.getLongitude());
    }

}
