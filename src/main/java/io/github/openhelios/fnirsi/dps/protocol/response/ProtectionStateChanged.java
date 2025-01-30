package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Index;

/**
 * The response for the protection state change.
 *
 * @param protectionState The protection state.
 */
public record ProtectionStateChanged(ProtectionState protectionState) implements Response {
  /**
   * Constructor.
   *
   * @param message The message.
   */
  ProtectionStateChanged(final byte[] message) {
    this(ProtectionState.byIndex(message[Index.DATA.get()]));
  }

}
