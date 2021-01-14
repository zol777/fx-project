## fx-project

[![Build Status](https://github.com/ericccw/fx-project/workflows/build/badge.svg)](https://github.com/ericccw/fx-project/actions)

fx-project is a project that produces and consumes trade messages in multiple threads via an API endpoint and render the received messages in real-time without persistent storage. Messages are stored in an in-memory blocking queue.

The queue and microservice structure are designed to fulfill the requirement and maintain simplicity for future extension using mysql/redis/load-balancer for example.

Containerizing the project with minimal dependency solves Java JDK incompatibility with the testing environment.

## Setup

Port 8090 and 8443 are used for producing and browsing trade messages respectively.

1. IntelliJ

    * Environment:
       ```
       Java: AdoptOpenJDK 15 (Hotspot)
       Node: v15.5.1
       Yarn: v1.22.10
       ```

    * Import and load the project as gradle project
    * To run backend, configure the working directory as `$MODULE_WORKING_DIR$`. Run Main.java in `backend/trade-service/src/main/java`
    * To run frontend, execute gradle task `buildFrontend` in `frontend/backoffice/Tasks/build/buildFrontend`. Configure the working directory as `$MODULE_WORKING_DIR$`. Run Main.java in `frontend/backoffice/src/main/java`
    * (optional) Configure the blocking queue parameters in `backend/trade-service/src/main/resources/app.properties`.

2. Docker
    * Run `docker-compose up -d` at the root of the project.
    * (optional) Configure the blocking queue parameters in `docker-compose.yml` according to container memory allocation.
   
## Configuration

  * `APP_TRADE_MAX` refers to max no. of trade message in the queue.
  * `APP_POLLSIZE_MAX` refers to max no. of trade message polled from the queue for each time.
  * Together with rate limiting, parameters could be tuned to avoid problems such as producer API timeout.
   
## API Endpoint

1. trade message producer
   ```
   curl -XPOST -k https://localhost:8090/trade -H "Content-Type:application/json" -d '{"userId":"134256","currencyFrom":"EUR","currencyTo":"GBP","amountSell":1000,"amountBuy":747.1,"rate":0.7471,"timePlaced":"24-JAN-18 10:27:44","originatingCountry":"FR"}'
   ```
2. trade message consumer
   ```
   curl -XGET -k https://localhost:8443/ajax/trade
   ```
3. frontend
   ```
   curl -XGET -k https://localhost:8443
   ```

## Continuous Delivery

Continuous delivery has not been implemented in this project due to time constraint and security concern on image registry secret management. The plan is to build image on cloud image registry and create a new container with that image for deployment.  

Most importantly, each deployment/release should not break any API/interface in order to achieve multiple releases without users feeling about it.
