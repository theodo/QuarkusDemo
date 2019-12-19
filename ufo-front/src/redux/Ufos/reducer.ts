import {Coords} from 'google-map-react';
import {AnyAction} from 'redux';
import {AddUfoAction} from 'redux/Ufos/actions';

export interface Ufo {
  name: string;
  current: Coords;
  destination: Coords;
  browserUUID: string;
  hit: boolean;
  moving: boolean;
}

export interface Ufos {
  [ufoName: string]: Ufo;
}

export type UfosState = Readonly<{
  ufos: Ufos;
}>;

const initialState: UfosState = {
  ufos: {},
};

const reducer = (state: UfosState = initialState, action: AnyAction) => {
  switch (action.type) {
    case 'ADD_UFO': {
      const typedAction = action as AddUfoAction;
      const newUfos = { ...state.ufos };
      typedAction.ufos.forEach(ufo => newUfos[ufo.name] = ufo);
      return { ...state, ufos: newUfos };
    }
    case 'CLEAR_UFOS': {
      return { ...state, ufos: {} };
    }
  }
  return state;
};

export default reducer;
