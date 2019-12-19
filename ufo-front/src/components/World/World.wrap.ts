import { connect } from 'react-redux';

import { RootState } from 'redux/types';
import World from 'components/World/World';
import {getUfos, submitUfos} from 'redux/Ufos';
import {getRadars, loadRadars, submitRadar} from "redux/Radars";

const mapDispatchToProps = {
  submitUfos,
  submitRadar,
  loadRadars,
};

const mapStateToProps = (state: RootState) => ({
  currentUfos: getUfos(state),
  currentRadars: getRadars(state)
});

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(World);
