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
