CREATE TABLE user_location (
  name VARCHAR(255),
  latitude DOUBLE,
  longitude DOUBLE
);

INSERT INTO user_location (name, latitude, longitude)
VALUES ('User A', 51.5074, -0.1278),
		('User B', 40.7128, -74.0060),
       ('User C', 37.7749, -122.4194);
