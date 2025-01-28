package de.buehmann.fnirsi.dps.protocol.response;

public interface CapacityResponse extends Response {

  /**
   * @return The capacity in Ah.
   */
  float capacityInAh();
}
