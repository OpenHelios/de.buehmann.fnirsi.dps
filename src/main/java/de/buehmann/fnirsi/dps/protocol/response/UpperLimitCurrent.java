package de.buehmann.fnirsi.dps.protocol.response;

import de.buehmann.fnirsi.dps.protocol.Data;
import de.buehmann.fnirsi.dps.protocol.Index;

public record UpperLimitCurrent(float current) implements Response {
  public UpperLimitCurrent(final byte[] message) {
    this(Data.toFloat(message, Index.DATA.get()));
  }
}
