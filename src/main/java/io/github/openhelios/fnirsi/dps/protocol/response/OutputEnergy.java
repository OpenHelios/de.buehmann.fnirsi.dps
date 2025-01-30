package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Data;
import io.github.openhelios.fnirsi.dps.protocol.Index;

/**
 * The response for the output energy.
 *
 * @param energyInWh The energy in Wh.
 */
public record OutputEnergy(float energyInWh) implements EnergyResponse {
  /**
   * Constructor.
   *
   * @param message The message.
   */
  OutputEnergy(final byte[] message) {
    this(Data.float32(message, Index.DATA.get()));
  }

  /**
   * Compares the given output energy with this output energy by rounding to mWh.
   *
   * @param outputEnergy The output energy in Wh.
   * @return True, if the given output energy has the same value as this output energy.
   */
  public boolean isSameDisplay(final OutputEnergy outputEnergy) {
    return Math.round(outputEnergy.energyInWh * 1000) == Math.round(energyInWh * 1000);
  }
}
