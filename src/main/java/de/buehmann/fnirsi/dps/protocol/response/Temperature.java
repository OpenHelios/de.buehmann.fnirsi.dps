package de.buehmann.fnirsi.dps.protocol.response;

import de.buehmann.fnirsi.dps.protocol.Data;
import de.buehmann.fnirsi.dps.protocol.Index;

public record Temperature(float temperatureInC) implements Response {
  public Temperature(final byte[] message) {
    this(Data.float32(message, Index.DATA.get()));
  }

  public boolean isSameDisplay(final float temperatureInC2) {
    return Math.abs(temperatureInC - temperatureInC2) < 0.33f;
  }
}
