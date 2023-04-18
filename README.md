# UserLocationAPI
This REST API building project was designed to help me better understand how Spring Boot and Rest APIs function.
This project aims to create a RESTful web service using Spring Boot and Maven that allows two types of users, ADMIN and READER, to perform different types of operations. ADMIN users can perform all CRUD operations, including POST, DELETE, PATCH, etc., while READER users can only perform GET operations.

The web service includes three REST APIs:

"create_data" API creates a table named "user_location" in the HSQL database with three fields: NAME, Latitude, and Longitude.
"update_data" API allows users to update the user_location table in the database.
"get_users/N" API returns the N nearest users to the location (0,0), where N is a parameter provided in the API endpoint.
The project uses the HSQL database system and JDBC template to interact with the database. The code is designed to follow best coding practices and minimize code smells. The project also includes unit tests to ensure proper functionality and uses Checkstyle to identify and fix coding errors. In addition, the code includes error handling and exception management to improve application reliability. Finally, the project is designed to be scalable, secure, and reliable in a production environment.
