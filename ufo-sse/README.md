#### UFO SSE  
Microservice that:  
  
- Provides `GET /ufos/stream` to get a Server Sent Event Stream (SSE) of all UFOs (1 stream per Web client)
  
#### EXPOSED  
Exposed on PORT 8081  
  
#### TO LAUNCH THIS MICRO-SERVICE WITHOUT DOCKER  
*(from within ufo-generator directory)*
Launch an Artemis Broker locally (if not yet done): 
  
`docker-compose  -f ../docker-compose-artemis-only.yaml up -d`  
  
_Launch the micro-service itself (dev mode):_  
  
`mvn clean quarkus:dev`  
  
  
#### TO LAUNCH THIS MICRO-SERVICE WITH DOCKER
*(from within ufo-generator directory)*  
_Launch an Artemis Broker locally (if not yet done):_  
  
`docker-compose  -f ../docker-compose-artemis-only.yaml up -d`  
  
_Launch the micro-service itself:_  
  

    mvn clean package
    docker build -f src/main/docker/Dockerfile.jvm -t ufo-sse .
    docker run -i --rm -p 8081:8081 --network ufo_network ufo-sse

#### TO LAUNCH THIS MICRO-SERVICE NATIVE (BINARY) WITH DOCKER  
*(from within ufo-generator directory)*  
_Launch an Artemis Broker locally (if not yet done):_  
  
`docker-compose  -f ../docker-compose-artemis-only.yaml up -d`  
  
_Launch the micro-service itself:_  
  

    mvn clean package -Pnative -Dquarkus.native.container-build=true
    docker build -f src/main/docker/Dockerfile.native -t ufo-sse .
    docker run -i --rm -p 8081:8081 --network ufo_network ufo-sse
