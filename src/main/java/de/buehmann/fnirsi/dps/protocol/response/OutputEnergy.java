package de.buehmann.fnirsi.dps.protocol.response;

import de.buehmann.fnirsi.dps.protocol.Data;
import de.buehmann.fnirsi.dps.protocol.Index;

public record OutputEnergy(float energyInWh) implements EnergyResponse {
  public OutputEnergy(final byte[] message) {
    this(Data.float32(message, Index.DATA.get()));
  }

  public boolean isSameDisplay(final OutputEnergy e) {
    return Math.round(e.energyInWh * 1000) == Math.round(energyInWh * 1000);
  }
}
