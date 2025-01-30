package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Data;
import io.github.openhelios.fnirsi.dps.protocol.Index;

/**
 * The model name response normally "DPS-150".
 *
 * @param name The model name.
 */
public record ModelName(String name) implements NameResponse {
  /**
   * Constructor for a model name.
   *
   * @param message The model name response.
   */
  ModelName(final byte[] message) {
    this(Data.text(message, Index.DATA.get(), message[Index.DATA_SIZE.get()] & 0xFF));
  }
}
