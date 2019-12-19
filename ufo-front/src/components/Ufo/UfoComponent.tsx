import React from 'react';
import styles from './UfoComponent.module.scss';

interface Props {
  lat: number;
  lng: number;
  ufoName: string;
  hit: boolean;
}


const UfoComponent: React.FunctionComponent<Props> = ({ ufoName, hit }) => {
  return (
    <div className={styles.container}>
      <h6>{hit ? '*' : '+'}</h6>
      <label className={hit ? styles.labelHit : styles.label}>{ufoName}</label>
    </div>
  );
};

export default UfoComponent;
