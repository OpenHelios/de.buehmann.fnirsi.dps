package de.buehmann.fnirsi.dps.protocol.response;

import de.buehmann.fnirsi.dps.protocol.Data;
import de.buehmann.fnirsi.dps.protocol.Index;

public record UpperLimitVoltage(float voltageInV) implements VoltageResponse {
  public UpperLimitVoltage(final byte[] message) {
    this(Data.float32(message, Index.DATA.get()));
  }
}
