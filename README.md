# Application-System-With-Spring-Boot
This is the first part of a project made for "Distributed Systems" course. It contains a spring boot app serving both as web app and a rest api, which is used by the second part of the project.

# Installation

## For eclipse
* Download the code
* Import project as existing maven project
* Select run configurations and as arguments put (on separate lines):
  
  --spring.datasource.username=user , --spring.datasource.password=password
  
  , where "user" and "password" are your mysql credentials
* Connect to localhost:8080 and use Admin as username and pass123 as password to connect as Admin
* Full list of users exists in data.sql script under resources. Note that pass123 is the password used for everyone

## For terminal
* Clone the code
* Run this command: mvn spring-boot:run -Dspring-boot.run.arguments="--spring.datasource.username=user --spring.datasource.password=password"

  , where "user" and "password" are your mysql credentials
* Connect to localhost:8080 and use Admin as username and pass123 as password to connect as Admin
* Full list of users exists in data.sql script under resources. Note that pass123 is the password used for everyone
