package io.github.openhelios.fnirsi.dps.protocol.response;

/**
 * The current response.
 */
public interface CurrentResponse extends Response {

  /**
   * The output current in A.
   *
   * @return The current in A.
   */
  float currentInA();

}
