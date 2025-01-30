package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Index;

/**
 * The response for an output mode change.
 *
 * @param outputMode The output mode.
 */
public record OutputModeChanged(OutputMode outputMode) implements Response {
  /**
   * Constructor.
   *
   * @param message The message.
   */
  OutputModeChanged(final byte[] message) {
    this(OutputMode.by(message[Index.DATA.get()]));
  }
}
