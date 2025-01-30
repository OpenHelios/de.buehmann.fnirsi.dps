package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Data;

/**
 * The settings group for a voltage in V and a current in A, which can be user defined.
 *
 * @param voltageInV The voltage in V.
 * @param currentInA The current in A.
 */
public record Group(float voltageInV, float currentInA) {
  /**
   * Constructor.
   *
   * @param message The message.
   * @param index The index, where the voltage in V starts followed by the current in A.
   */
  Group(final byte[] message, final int index) {
    this(Data.float32(message, index), Data.float32(message, index + 4));
  }
}
