package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Data;
import io.github.openhelios.fnirsi.dps.protocol.Index;

/**
 * The unknown.
 *
 * @param isEnabled True, if the unknown is enabled.
 */
public record Unknown(boolean isEnabled) implements Response {
  /**
   * Constructor.
   *
   * @param message The message
   */
  Unknown(final byte[] message) {
    this(Data.bool(message, Index.DATA.get()));
  }

}
