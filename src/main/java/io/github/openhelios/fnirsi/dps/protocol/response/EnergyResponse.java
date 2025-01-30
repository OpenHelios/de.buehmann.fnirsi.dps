package io.github.openhelios.fnirsi.dps.protocol.response;

/**
 * The energy response.
 */
public interface EnergyResponse extends Response {

  /**
   * The energy in Wh.
   *
   * @return The energy in Wh.
   */
  float energyInWh();
}
