package de.buehmann.fnirsi.dps.protocol.response;

public interface EnergyResponse extends Response {

  /**
   * @return Energy in Wh.
   */
  float energyInWh();
}
