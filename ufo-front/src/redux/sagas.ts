import { all } from 'redux-saga/effects';
import radarsSagas from "redux/Radars/sagas";
import ufosSagas from "redux/Ufos/sagas";

// single entry point to start all Sagas at once
export default function* rootSaga() {
  yield all([radarsSagas(), ufosSagas()]);
}
