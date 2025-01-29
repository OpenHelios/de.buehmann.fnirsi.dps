package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Data;
import io.github.openhelios.fnirsi.dps.protocol.Index;

public record InputVoltage(float voltageInV) implements VoltageResponse {
  public InputVoltage(final byte[] message) {
    this(Data.float32(message, Index.DATA.get()));
  }

  public boolean isSameDisplay(final InputVoltage v) {
    return Math.abs(voltageInV - v.voltageInV) < 0.02;
  }

}
