#### UFO RADAR DETECTION
Microservice that:  
  
- Listen for RADAR Messages (1 message per Radar Creation) on `"radars" AMQP topic`
- Listen for UFO-DETECTION Messages (1 message per UFO per period) on `"ufos" AMQP ANYCAST`
- Emmit a HIT Message if a UFO is within RADAR circle on `"hits" AMQP topic`
  
#### EXPOSED  
No port exposed
