import React, {useEffect, useState} from 'react';
import GoogleMapReact, {Coords} from 'google-map-react';
import {AddUfoAction, Ufo, Ufos} from 'redux/Ufos';
import UfoComponent from 'components/Ufo/UfoComponent';
import {AddRadarAction, LoadRadarsAction, Radar, Radars} from "redux/Radars";
import {
  addExistingRadarHere,
  addNewRadarHere,
  GoogleMapDrawing,
  initialMap
} from "components/World/GoogleMapHelpers";

interface Props {
  url: string;
  center: Coords;
  zoom: number;
  currentUfos: Ufos;
  currentRadars: Radars;
  submitUfos: (ufo: Ufo[]) => AddUfoAction;
  loadRadars: () => LoadRadarsAction;
  submitRadar: (radar: Radar) => AddRadarAction;
}

const World: React.FunctionComponent<Props> = ({ center, zoom, url, currentUfos, currentRadars,
                                                 submitUfos, loadRadars, submitRadar }) => {
  const [maps, storeGoogleMap] = useState<GoogleMapDrawing>(initialMap);

  useEffect(
    () => {
      function sseUfoListen() {
        const newSource = new EventSource(url, {withCredentials: true});
        const cb = (message: MessageEvent) => {
          const ufos = JSON.parse(message.data);
          submitUfos(ufos);
        };
        newSource.addEventListener('message', cb, false);
        newSource.onerror = function(this: EventSource, ev: Event) {
          console.log(ev);
        };
      }

      sseUfoListen();
      loadRadars();
    },
    [url, submitUfos, loadRadars],
  );

  // @ts-ignore
  return (
    <div style={{ height: '100vh', width: '100%' }}>
      <GoogleMapReact
        bootstrapURLKeys={{key: '{{PUT YOUR GOOGLEMAP API KEY HERE}}',}}
        defaultCenter={center}
        defaultZoom={zoom}
        yesIWantToUseGoogleMapApiInternals={true}
        onGoogleApiLoaded={({ map, maps }) => {
          storeGoogleMap({map: map, maps: maps, circles: []});
        }}
        onClick={value => {
          addNewRadarHere(submitRadar, maps, {lat: value.lat, lng: value.lng })}
        }
      >
        {
          addExistingRadarHere(maps, currentRadars)
        }
        {currentUfos &&
          Object.values(currentUfos).map(ufo => {
            return <UfoComponent key={ufo.name} lat={ufo.current.lat} lng={ufo.current.lng}
                                 ufoName={ufo.name} hit={ufo.hit}/>;
          })}
      </GoogleMapReact>
    </div>
  );
};

export default World;
