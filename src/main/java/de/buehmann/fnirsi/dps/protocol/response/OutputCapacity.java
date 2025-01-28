package de.buehmann.fnirsi.dps.protocol.response;

import de.buehmann.fnirsi.dps.protocol.Data;
import de.buehmann.fnirsi.dps.protocol.Index;

public record OutputCapacity(float capacityInAh) implements CapacityResponse {
  public OutputCapacity(final byte[] message) {
    this(Data.toFloat(message, Index.DATA.get()));
  }
}
