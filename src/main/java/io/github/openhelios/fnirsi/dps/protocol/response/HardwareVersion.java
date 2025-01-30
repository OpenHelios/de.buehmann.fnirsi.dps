package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Data;
import io.github.openhelios.fnirsi.dps.protocol.Index;

/**
 * Response for the hardware version.
 *
 * @param version The hardware version.
 */
public record HardwareVersion(String version) implements VersionResponse {

  /**
   * Constructor for a hardware version.
   *
   * @param message The hardware version message.
   */
  HardwareVersion(final byte[] message) {
    this(Data.text(message, Index.DATA.get(), message[Index.DATA_SIZE.get()] & 0xFF));
  }
}
