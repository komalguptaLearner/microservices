This is online Shopping Application using microservices that involves 3 services:
1. Customer Service
2. Item Service
3. Order Servvice

Technology Stack :
Spring Boot version 2.1.0.RELEASE
Spring Cloud version -> Greenwich.RELEASE
For Service Registration and Discovery -> Netflix Eureka
For Centralized logging -> Cloud Config server
For Routing -> Netflix Zuul Gateway
For Load balancing -> Ribbon load balancer is used
For assigning request id -> Sleuth is used
For distributed tracing -> Zipkin is used
Feign client instead of RestTemplate to avoid hardcoding the url while making call to another service.
