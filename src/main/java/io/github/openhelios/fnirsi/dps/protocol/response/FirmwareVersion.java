package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Data;
import io.github.openhelios.fnirsi.dps.protocol.Index;

/**
 * Response for the firmware version.
 *
 * @param version The firmware version.
 */
public record FirmwareVersion(String version) implements VersionResponse {

  /**
   * Constructor for the firmware version.
   *
   * @param message The firmware version message.
   */
  FirmwareVersion(final byte[] message) {
    this(Data.text(message, Index.DATA.get(), message[Index.DATA_SIZE.get()] & 0xFF));
  }
}
