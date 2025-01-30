package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Data;
import io.github.openhelios.fnirsi.dps.protocol.Index;

/**
 * The response for an output voltage, current and power.
 *
 * @param voltageInV The output voltage in V.
 * @param currentInA The output current in A.
 * @param powerInW The output power in W, which is normally the product of voltage and current.
 */
public record OutputVoltageCurrentPower(float voltageInV, float currentInA, float powerInW)
    implements VoltageResponse, CurrentResponse, PowerResponse {
  /**
   * Constructor.
   *
   * @param message The message.
   */
  OutputVoltageCurrentPower(final byte[] message) {
    this( //
        Data.float32(message, Index.DATA.get()), //
        Data.float32(message, Index.DATA.get() + 4), //
        Data.float32(message, Index.DATA.get() + 8));

  }

  /**
   * Compares the given output voltage, current and power with this output voltage, current and power by using the
   * rounded values of 10mV, mA and 10mW.
   *
   * @param outputVoltageCurrentPower The other output voltage, current and power.
   * @return True, if the given one has the same values as this one.
   */
  public boolean isSameDisplay(final OutputVoltageCurrentPower outputVoltageCurrentPower) {
    return Math.round(voltageInV * 100) == Math.round(outputVoltageCurrentPower.voltageInV * 100)
        && Math.round(currentInA * 1000) == Math.round(outputVoltageCurrentPower.currentInA * 1000)
        && Math.round(powerInW * 100) == Math.round(outputVoltageCurrentPower.powerInW * 100);
  }
}
