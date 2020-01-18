#### UFO RADAR  
Microservice that:  
  
- Provides `GET /radars` to get all RADARS running  
- Provides `POST /radars {body}` to add a new RADAR in the game   
- Emit RADAR Messages (1 per RADAR creation) on `"radars" AMQP topic` (RadarResource class)  
  
#### EXPOSED  
Exposed on PORT 8082  
