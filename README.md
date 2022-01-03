# SPRING BOOT KOTLIN SERVICE
This is Spring boot kotlin based sample project.
* It includes an index.html page
* It has a h2 database in memory configured with a database named test
* It has the h2 console enabled on http://127.0.0.1:9000/h2/
* It also inserts some data to populate the database using CommandLineRunner on main class
* It defines an Entity, Repository, Service and Controller for --> Product
* It provides basic endpoints like /products and /products/{id}
* It also includes kotlin-logging 
* It connects to a redis server and cache the result of a Service call. The redis
server has to be started separately using docker: $ docker run --name my-redis -p 6379:6379 -d redis
* It generates a swagger-ui api test page on (http://localhost:9000/swagger-ui/index.html)

  


