package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Data;
import io.github.openhelios.fnirsi.dps.protocol.Index;

/**
 * The response for an upper limit voltage in V.
 *
 * @param voltageInV The upper limit voltage in V.
 */
public record UpperLimitVoltage(float voltageInV) implements VoltageResponse {
  /**
   * Constructor.
   *
   * @param message The message.
   */
  UpperLimitVoltage(final byte[] message) {
    this(Data.float32(message, Index.DATA.get()));
  }
}
