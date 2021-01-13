## fx-project

fx-project is a project that consumes trade messages from multiple threads via an API endpoint and render the received messages in real-time without persistent storage. Messages are stored in an in-memory blocking queue.  

The queue and microservice structure are designed to fulfill the base requirement and maintain simplicity for future extension using mysql/redis/load-balancer for example.  

Containerizing the project with minimal dependency solves Java JDK incompatibility with the testing environment. 

[![Build Status](https://github.com/ericccw/fx-project/workflows/build/badge.svg)](https://github.com/ericccw/fx-project/actions)

