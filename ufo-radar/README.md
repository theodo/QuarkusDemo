#### UFO RADAR  
Microservice that:  
  
- Provides `GET /radars` to get all RADARS running  
- Provides `POST /radars {body}` to add a new RADAR in the game   
- Emit RADAR Messages (1 per RADAR creation) on `"radars" AMQP topic` (RadarResource class)  
  
#### EXPOSED  
Exposed on PORT 8082  
  
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
    docker build -f src/main/docker/Dockerfile.jvm -t ufo-radar .
    docker run -i --rm -p 8082:8082 --network ufo_network ufo-radar

#### TO LAUNCH THIS MICRO-SERVICE NATIVE (BINARY) WITH DOCKER  
*(from within ufo-generator directory)*  
_Launch an Artemis Broker locally (if not yet done):_  
  
`docker-compose  -f ../docker-compose-artemis-only.yaml up -d`  
  
_Launch the micro-service itself:_  
  

    mvn clean package -Pnative -Dquarkus.native.container-build=true
    docker build -f src/main/docker/Dockerfile.native -t ufo-radar .
    docker run -i --rm -p 8082:8082 --network ufo_network ufo-radar
