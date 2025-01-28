package de.buehmann.fnirsi.dps.protocol.response;

public interface VoltageResponse extends Response {

  /**
   * @return The voltage in V.
   */
  float voltageInV();

}
