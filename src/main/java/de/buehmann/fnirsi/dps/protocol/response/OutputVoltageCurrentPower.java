package de.buehmann.fnirsi.dps.protocol.response;

import de.buehmann.fnirsi.dps.protocol.Data;
import de.buehmann.fnirsi.dps.protocol.Index;

public record OutputVoltageCurrentPower(float voltageInV, float currentInA, float powerInW)
    implements VoltageResponse, CurrentResponse, PowerResponse {
  public OutputVoltageCurrentPower(final byte[] message) {
    this( //
        Data.float32(message, Index.DATA.get()), //
        Data.float32(message, Index.DATA.get() + 4), //
        Data.float32(message, Index.DATA.get() + 8));

  }

  public boolean isSameDisplay(final OutputVoltageCurrentPower o) {
    return Math.round(voltageInV * 100) == Math.round(o.voltageInV * 100)
        && Math.round(currentInA * 1000) == Math.round(o.currentInA * 1000)
        && Math.round(powerInW * 100) == Math.round(o.powerInW * 100);
  }
}
