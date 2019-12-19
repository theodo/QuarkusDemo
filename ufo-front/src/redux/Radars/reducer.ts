import {Coords} from 'google-map-react';
import {AnyAction} from 'redux';
import {AddRadarAction, RadarsLoadedAction} from "redux/Radars/actions";

export interface Radar {
  name: string;
  position: Coords;
  distance: number;
}

export interface Radars {
  [radarName: string]: Radar;
}

export type RadarsState = Readonly<{
  radars: Radars;
}>;

const initialState: RadarsState = {
  radars: {},
};

const reducer = (state: RadarsState = initialState, action: AnyAction) => {
  switch (action.type) {
    case 'ADD_RADAR': {
      const typedAction = action as AddRadarAction;
      const newRadars = { ...state.radars };
      newRadars[typedAction.radar.name] = typedAction.radar;
      return { ...state, radars: newRadars };
    }
    case 'RADARS_LOADED': {
      const typedAction = action as RadarsLoadedAction;
      return { ...state, radars: typedAction.radars };
    }
  }
  return state;
};

export default reducer;
