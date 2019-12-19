import {UfosActions, UfosState} from 'redux/Ufos';
import {RadarsState} from "redux/Radars/reducer";
import {RadarsActions} from "redux/Radars";

export type RootState = Readonly<{
  ufos: UfosState;
  radars: RadarsState;
}>;
export type RootAction = RadarsActions | UfosActions;
