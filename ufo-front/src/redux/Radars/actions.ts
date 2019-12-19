import {Radar, Radars} from "redux/Radars/reducer";

export interface LoadRadarsAction {
  type: string;
}

export interface RadarsLoadedAction {
  type: string;
  radars: Radars;
}

export interface AddRadarAction {
  type: string;
  radar: Radar;
}

export type RadarsActions =
  | AddRadarAction
  | LoadRadarsAction
  | RadarsLoadedAction;

export const loadRadars = (): LoadRadarsAction => {
  return { type: 'LOAD_RADARS' };
};

export const radarsLoaded = (radars: Radars): RadarsLoadedAction => {
  return { type: 'RADARS_LOADED', radars: radars };
};

export const submitRadar = (radar: Radar): AddRadarAction => {
  return { type: 'ADD_RADAR', radar };
};

export default {
  submitRadar,
  loadRadars
};
