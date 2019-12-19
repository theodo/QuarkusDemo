import {connect} from 'react-redux';
import {submitClearAllUfos} from 'redux/Ufos';
import Home from "./Home";

const mapDispatchToProps = {
  submitClearAllUfos,
};

export default connect(
  null,
  mapDispatchToProps,
)(Home);
