import {Ufo} from 'redux/Ufos/reducer';

export interface AddUfoAction {
  type: string;
  ufos: Ufo[];
}

export interface ClearAllUfosAction {
  type: string;
}

export type UfosActions = AddUfoAction | ClearAllUfosAction;

export const submitUfos = (ufos: Ufo[]): AddUfoAction => {
  return { type: 'ADD_UFO', ufos };
};
export const submitClearAllUfos = (): ClearAllUfosAction => {
  return { type: 'CLEAR_UFOS' };
};
export default {
  submitUfos,
  submitClearAllUfos
};
