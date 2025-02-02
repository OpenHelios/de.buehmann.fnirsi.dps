package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Data;
import io.github.openhelios.fnirsi.dps.protocol.Index;

/**
 * The response for an upper limit temperature in °C.
 *
 * @param temperatureInC The temperature in °C.
 */
public record UpperLimitTemperature(float temperatureInC) implements Response {
  /**
   * Constructor.
   *
   * @param message The message
   */
  UpperLimitTemperature(final byte[] message) {
    this(Data.float32(message, Index.DATA.get()));
  }

}
