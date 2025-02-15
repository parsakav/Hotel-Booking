# An application to book a hotel

## Description
This project used spring boot ,Spring JDBC , Spring AOP , Swagger and flywaydb (Migration)
I wrote migration in resources/db/migration

## Run 

This project is dockerize .   this command is used to run database :
docker-compose run -d phpmyadmin  
Then database will be running . Now just run application with Jdk 17

## Test

Postman exported collection is hotel-booking.postman_collection.json
Import and test the API

## AOP
I used aspect oriented programming to logging and exception handling.

## Database
resources/schema.sql is the schema of database

