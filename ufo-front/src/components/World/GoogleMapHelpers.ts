import {Coords} from "google-map-react";
import {AddRadarAction, Radar, Radars} from "redux/Radars";

export interface Circles {
  // @ts-ignore
  [radarName: string]: google.maps.Circle;
}

export interface GoogleMapDrawing {
  map: any;
  maps: any;
  circles: Circles;
}

export const initialMap: GoogleMapDrawing = {maps: undefined, map: undefined, circles: []};

export const addRadarOnTheMap = (googleMapDrawing: GoogleMapDrawing, radar: Radar) => {
  if (googleMapDrawing.maps) {
    if (googleMapDrawing.circles[radar.name]){
      return;
    }

    const circle = new googleMapDrawing.maps.Circle({
      strokeColor: "#FF0000",
      strokeOpacity: 0.8,
      strokeWeight: 2,
      fillColor: "#FF0000",
      fillOpacity: 0.3,
      map: googleMapDrawing.map,
      center: radar.position,
      radius: radar.distance
    });
    googleMapDrawing.circles[radar.name] = circle;
  }
};

export function addNewRadarHere(submitRadar: (radar: Radar) => AddRadarAction,
                                maps: GoogleMapDrawing, coords: Coords) {
  submitRadar({name: `Radar here: ${coords.lat} ${coords.lng}`, position: coords, distance: 400});
}


export function addExistingRadarHere(maps: GoogleMapDrawing, radars: Radars) {
  if (maps.maps) {
    Object.values(radars).map(radar => addRadarOnTheMap(maps, radar))
  }
}


