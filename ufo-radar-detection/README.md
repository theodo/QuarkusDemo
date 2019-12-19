#### UFO RADAR DETECTION
Microservice that:  
  
- Listen for RADAR Messages (1 message per Radar Creation) on `"radars" AMQP topic`
- Listen for UFO-DETECTION Messages (1 message per UFO per period) on `"ufos" AMQP ANYCAST`
- Emmit a HIT Message if a UFO is within RADAR circle on `"hits" AMQP topic`
  
#### EXPOSED  
No port exposed
  
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
    docker build -f src/main/docker/Dockerfile.jvm -t ufo-radar-detection .
    docker run -i --rm --network ufo_network ufo-radar-detection

#### TO LAUNCH THIS MICRO-SERVICE NATIVE (BINARY) WITH DOCKER  
*(from within ufo-generator directory)*  
_Launch an Artemis Broker locally (if not yet done):_  
  
`docker-compose  -f ../docker-compose-artemis-only.yaml up -d`  
  
_Launch the micro-service itself:_  
  

    mvn clean package -Pnative -Dquarkus.native.container-build=true
    docker build -f src/main/docker/Dockerfile.native -t ufo-radar-detection .
    docker run -i --rm --network ufo_network ufo-radar-detection
