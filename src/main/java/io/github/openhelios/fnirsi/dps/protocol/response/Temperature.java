package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Data;
import io.github.openhelios.fnirsi.dps.protocol.Index;

/**
 * The response for a temperature in °C.
 *
 * @param temperatureInC The temperature in C.
 */
public record Temperature(float temperatureInC) implements Response {
  /**
   * Constructor.
   *
   * @param message The message
   */
  Temperature(final byte[] message) {
    this(Data.float32(message, Index.DATA.get()));
  }

  /**
   * Compares the given temperature with this temperature, where a minimum change of 0.33°C is needed.
   *
   * @param otherTemperatuerInC The other temperature in °C.
   * @return True, if the given temperature and this temperature is lower than 0.33°C.
   */
  public boolean isSameDisplay(final float otherTemperatuerInC) {
    return Math.abs(temperatureInC - otherTemperatuerInC) < 0.33f;
  }
}
