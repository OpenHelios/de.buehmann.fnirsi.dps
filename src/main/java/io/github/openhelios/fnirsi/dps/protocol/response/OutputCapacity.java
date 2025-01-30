package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Data;
import io.github.openhelios.fnirsi.dps.protocol.Index;

/**
 * The response for the output capacity.
 *
 * @param capacityInAh The capacity in Ah.
 */
public record OutputCapacity(float capacityInAh) implements CapacityResponse {
  /**
   * Constructor.
   *
   * @param message The message.
   */
  OutputCapacity(final byte[] message) {
    this(Data.float32(message, Index.DATA.get()));
  }
}
