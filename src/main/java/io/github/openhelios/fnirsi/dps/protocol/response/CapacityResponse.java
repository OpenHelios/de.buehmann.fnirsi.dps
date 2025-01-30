package io.github.openhelios.fnirsi.dps.protocol.response;

/**
 * The capacity response.
 */
public interface CapacityResponse extends Response {

  /**
   * The output capacity in Ah.
   *
   * @return The capacity in Ah.
   */
  float capacityInAh();
}
