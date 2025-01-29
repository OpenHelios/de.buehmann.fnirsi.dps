package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Data;
import io.github.openhelios.fnirsi.dps.protocol.Index;

public record OutputEnergy(float energyInWh) implements EnergyResponse {
  public OutputEnergy(final byte[] message) {
    this(Data.float32(message, Index.DATA.get()));
  }

  public boolean isSameDisplay(final OutputEnergy e) {
    return Math.round(e.energyInWh * 1000) == Math.round(energyInWh * 1000);
  }
}
