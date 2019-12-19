import {RootState} from 'redux/types';
import {Radars} from './reducer';

export const getRadars = (store: RootState): Radars => store.radars.radars;
