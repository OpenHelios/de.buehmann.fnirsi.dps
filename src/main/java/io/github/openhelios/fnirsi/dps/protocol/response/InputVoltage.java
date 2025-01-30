package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Data;
import io.github.openhelios.fnirsi.dps.protocol.Index;

/**
 * The response for the input voltage in V.
 *
 * @param voltageInV The voltage in V.
 */
public record InputVoltage(float voltageInV) implements VoltageResponse {
  /**
   * Constructor.
   *
   * @param message The message.
   */
  InputVoltage(final byte[] message) {
    this(Data.float32(message, Index.DATA.get()));
  }

  /**
   * Method for comparing the given input voltage with this one.
   *
   * @param inputVoltage The input voltage in V.
   * @return True, if the given input voltage has the same value this one with lower than 0.02V difference.
   */
  public boolean isSameDisplay(final InputVoltage inputVoltage) {
    return Math.abs(voltageInV - inputVoltage.voltageInV) < 0.02;
  }

}
