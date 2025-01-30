package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Data;
import io.github.openhelios.fnirsi.dps.protocol.Index;

/**
 * The response for the current in A.
 *
 * @param currentInA The upper limit current in A.
 */
public record UpperLimitCurrent(float currentInA) implements Response {
  /**
   * Constructor.
   *
   * @param message The message.
   */
  UpperLimitCurrent(final byte[] message) {
    this(Data.float32(message, Index.DATA.get()));
  }
}
