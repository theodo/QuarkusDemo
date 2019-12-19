import {call, takeEvery} from 'redux-saga/effects';
import {ufoClient} from "services/networking/Clients";

export function* submitClearAll() {
  yield call(clearUfosInBackend);
}

const clearUfosInBackend = async (): Promise<void> => {
  return await ufoClient.post('/ufos/clean', {});
};

export default function* ufosSagas() {
  yield takeEvery("CLEAR_UFOS", submitClearAll);
}
