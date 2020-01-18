import * as React from 'react';

import {HomeContainer, Title} from './Home.style';
import World from 'components/World/World.wrap';
import Button from "components/Button";
import styles from './Home.module.scss';
import {awsHttpdIp, ufoClient} from "services/networking/Clients";
import {ClearAllUfosAction} from "redux/Ufos";

interface Props {
  submitClearAllUfos: () => ClearAllUfosAction;
}
const Home: React.FunctionComponent<Props> = ({submitClearAllUfos}) => (
  <HomeContainer>
    <Title>UFOs versus le reste du monde...</Title>

    <div className={styles.buttonContainer}>
      <Button onClick={() => {ufoClient.post("/ufos", {})}}>New UFO</Button>
      <Button onClick={() => {submitClearAllUfos()}}>Clear UFOs</Button>
    </div>

    <World
      url={`http://${awsHttpdIp}:80/ufos/stream`}
      zoom={14}
      center={{ lat: 48.884748, lng: 2.23964 }}
    />

  </HomeContainer>
);

export default Home;
