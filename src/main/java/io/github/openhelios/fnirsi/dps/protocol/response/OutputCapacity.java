package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Data;
import io.github.openhelios.fnirsi.dps.protocol.Index;

public record OutputCapacity(float capacityInAh) implements CapacityResponse {
  public OutputCapacity(final byte[] message) {
    this(Data.float32(message, Index.DATA.get()));
  }
}
