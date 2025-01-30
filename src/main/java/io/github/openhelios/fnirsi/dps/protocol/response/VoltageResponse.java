package io.github.openhelios.fnirsi.dps.protocol.response;

/**
 * The voltage response.
 */
public interface VoltageResponse extends Response {

  /**
   * The output voltage in V.
   *
   * @return The voltage in V.
   */
  float voltageInV();

}
