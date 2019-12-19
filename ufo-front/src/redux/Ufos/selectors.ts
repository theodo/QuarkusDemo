import { RootState } from 'redux/types';
import {Ufos} from './reducer';

export const getUfos = (store: RootState): Ufos => store.ufos.ufos;
