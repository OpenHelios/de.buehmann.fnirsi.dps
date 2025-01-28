package de.buehmann.fnirsi.dps.protocol.response;

import de.buehmann.fnirsi.dps.protocol.Data;
import de.buehmann.fnirsi.dps.protocol.Index;

public record InputVoltage(float voltageInV) implements VoltageResponse {
  public InputVoltage(final byte[] message) {
    this(Data.toFloat(message, Index.DATA.get()));
  }

  public boolean isSameDisplay(final InputVoltage v) {
    return Math.abs(voltageInV - v.voltageInV) < 0.02;
  }

}
