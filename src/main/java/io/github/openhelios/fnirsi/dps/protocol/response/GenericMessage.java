package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Data;

/**
 * A generic message, which can be used for any response.
 *
 * @param message The bytes.
 */
public record GenericMessage(byte[] message) implements Response {

  @Override
  public final String toString() {
    return Data.hex(message);
  }

}
