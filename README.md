
## JAVA QUARKUS DEMO: UFOs versus RADARs  
  
![alt text](./docs/quarkus.png "Quarkus Logo")

### Purposes  
- Demonstrate usages of Quarkus framework.  (https://quarkus.io/) 
- Demonstrate how to create REST APIs.  (https://quarkus.io/guides/rest-json)
- Demonstrate how to call REST APIs (local or external) (https://quarkus.io/guides/rest-client)
- Use JAVA Reactive Programming (https://smallrye.io/smallrye-reactive-messaging/#_getting_started)
- Produce AMQP messages on topics  (https://quarkus.io/guides/amqp)
- Consume AMQP messages from queues (broadcast or anycast) (https://quarkus.io/guides/amqp)  
- Broadcast SSE (Server Sent Events) to Web browser  (https://quarkus.io/guides/amqp)
- Consume SSE using JS React Application  
- Launch microservices with JVM  
- Launch same microservices with JVM in Dockers containers  
- Launch same microservices without JVM (native mode) in Dockers containers 
- Use SSL in REST APIs 
  
### Story  
- UFOs are created by the users (initial position and destination are random)  
- RADARs are created by the users by clicking on the map  
- When RADARs detect UFOs, UFOs are highlighted on the map  
  
### Architecture  
- Microservice `ufo-generator`: Manage list of UFOs and UFOs positions  
  - Expose REST API to Get all UFOs  
  - Expose REST API to Create (POST) new UFOs in the game  
  - Send UFO new positions every 50ms on an AMQP Topic (broadcasted to many queues) named `ufos`  
  - Created UFOs are named using the /pokemon API (to demonstrate external REST HTTPS Calls)  
For more details on this micro-service see [UFO-GENERATOR README](./ufo-generator/README.md)
  
- Microservice `ufo-radar`: Manage list of RADARs and RADARs positions  
  - Expose REST API to Get all current Radars positions  
  - Expose REST API to Create a new RADAR in the game  
  - Send RADAR creation message on an AMQP Topic (broadcasted to many queues) named `radars`  
For more details on this micro-service see [UFO-RADAR README](./ufo-radar/README.md)

- Microservice `ufo-radar-detection`: Compute Detection  
  - Listen to UFO position messages (listen to `ufos-detection`queue (*))  
  - Listen to RADAR creation messages (listen to `radars` queue)  
  - Detect intersection of RADAR and UFO  
  - Emit an AMQP HIT message for each UFO Detected named `hits`  
  - At startup of the microservice, retrieve the list of current RADARs from `ufo-radar` API.  
Multiple instances of this service can be deployed. A UFO message will be consumed by only one.  
For more details on this micro-service see [RADAR-DETECTION README](./ufo-radar-detection/README.md)
    

(*): `ufos-detection` queue is a queue diverted from `ufos` (broadcasted). A message published on `ufos` queue will be duplicated (by the AMQP broker) to `ufos-detection` In `ufos-detection` this message will be consumed by only one consumer.
See snipets files (in snipets directory) that describe AMQP brokers settings.
  
- Microservice `ufo-sse`: Manage SSE (Server Sent Stream) to Browsers  
  - Expose REST API to Create a new UFO positions STREAM (to the browser)  
  - Listen to UFO position messages (listen to `ufos`queue)  
  - Group UFO position by packet (125ms approx.) to be sent by packet to Browser on SSE  
For more details on this micro-service see [UFO-SSE README](./ufo-sse/README.md)
  
### Architecture Diagram  
  
![alt text](./docs/schema-ufos.png "System schema")  
  
### Install  JVM Mode
This is the classical JAVA way of deploying application. 
The build phase will create a JAR file (including an embedded application server).
The deploy phase is based on a JAVA docker image (java-alpine-openjdk8-jre). The Jar is put inside the container. 
##### To start the AMQP Broker  
    docker-compose -f docker-compose-artemis-only.yaml  up -d --build --force-recreate

  
##### To build all microservices in JVM mode  
In UFO root directory:  

    mvn clean package 
or

    ./compile.sh   

  
##### To start all microservices in JVM mode  
The docker-compose uses the `docker-compose.yaml` file.
In UFO root directory:  

    docker-compose up -d --build --force-recreate 

##### To start FRONT WebPage  
In ufo-front directory:  
  

    yarn install  
    yarn start
NB: you need to setup a GOOGLEAPI API KEY in `World.tsx` file in front module.

      <GoogleMapReact
        bootstrapURLKeys={{key: '{{PUT YOUR API KEY HERE}}',}}
        defaultCenter={center}
        defaultZoom={zoom}
        yesIWantToUseGoogleMapApiInternals={true}

    
Navigate to: http://localhost:3000
You can create radars by clicking in the map.

### Install  NATIVE Mode
You need to have GraalVM installed on your computer.
See here: [https://www.graalvm.org/](https://www.graalvm.org/)

This is the Quarkus NATIVE way of deploying application. 
The build phase will create an EXECUTABLE file (including an embedded application server). This is not JAVA anymore.
The deploy phase is based on a LINUX docker image (fedora-minimal). The binary application is put inside the container. 

##### To start the AMQP Broker   (idem JVM mode)
    docker-compose -f docker-compose-artemis-only.yaml  up -d --build --force-recreate

  
##### To build all microservices in JVM mode  
In UFO root directory:  

    mvn clean package -Pnative -Dquarkus.native.container-build=true 

or

    ./compile-native.sh    
Native compilation is slow and may take some time (about 5min per module).

##### To start all microservices in JVM mode  
In UFO root directory:  

    docker-compose -f docker-compose-native.yaml up -d --build --force-recreate  

##### To start FRONT WebPage  
In ufo-front directory:  

    yarn install  
    yarn start
Navigate to: http://localhost:3000
You can create radars by clicking in the map.

![alt text](./docs/demo.png "Quarkus Demo")  
