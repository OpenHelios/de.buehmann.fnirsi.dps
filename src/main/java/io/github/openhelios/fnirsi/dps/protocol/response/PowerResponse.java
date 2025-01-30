package io.github.openhelios.fnirsi.dps.protocol.response;

/**
 * The power response.
 */
public interface PowerResponse extends Response {

  /**
   * The output power in W.
   *
   * @return The power in W.
   */
  float powerInW();
}
