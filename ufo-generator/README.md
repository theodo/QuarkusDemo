#### UFO GENERATOR  
Microservice that:  
  
- Provides `GET /ufos` to get all UFOs running  
- Provides `POST /ufo {body}` to add a new UFO in the game   
  - Provides random start and destination positions  
  - Provides random Pokemon name for the UFO (using REST call to /pokemon API)  
- Generate new positions for all UFOs on a regular basis (UfoGenerator class)  
- Emit UFO Messages (1 per UFO per period of time) on `"ufos" AMQP topic` (UfoGenerator class)  
- Listen UFO Detection Messages (1 message per UFO detection) on `"hits" AMQP topic` (UfoDetected class)  
  
#### EXPOSED  
Exposed on PORT 8080  
  
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
    docker build -f src/main/docker/Dockerfile.jvm -t ufo-generator .
    docker run -i --rm -p 8080:8080 --network ufo_network ufo-generator

#### TO LAUNCH THIS MICRO-SERVICE NATIVE (BINARY) WITH DOCKER  
*(from within ufo-generator directory)*  
_Launch an Artemis Broker locally (if not yet done):_  
  
`docker-compose  -f ../docker-compose-artemis-only.yaml up -d`  
  
_Launch the micro-service itself:_  
  

    mvn clean package -Pnative -Dquarkus.native.container-build=true
    docker build -f src/main/docker/Dockerfile.native -t ufo-generator .
    docker run -i --rm -p 8080:8080 --network ufo_network ufo-generator
