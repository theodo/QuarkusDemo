import {call, takeEvery} from 'redux-saga/effects';
import {Radar} from "redux/Radars/reducer";
import {AddRadarAction, radarsLoaded} from "redux/Radars/actions";
import {put} from "redux-saga-test-plan/matchers";
import {clients} from "services/networking/Clients";

export function* submitRadar(action: AddRadarAction) {
  yield call(addRadarInBackend, action.radar);
  yield loadRadars();
}

const addRadarInBackend = async (radar: Radar): Promise<Radar> => {
  return await clients.post('/radars', radar);
};

export function* loadRadars() {
  const radars = yield call(loadRadarsFromBackend);
  yield put(radarsLoaded(radars))
}

const loadRadarsFromBackend = async (): Promise<Radar[]> => {
  return await clients.get('/radars');
};

export default function* radarsSagas() {
  yield takeEvery("ADD_RADAR", submitRadar);
  yield takeEvery("LOAD_RADARS", loadRadars);
}
