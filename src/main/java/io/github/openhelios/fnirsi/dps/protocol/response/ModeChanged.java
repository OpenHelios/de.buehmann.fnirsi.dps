package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Index;

/**
 * The response for a mode change.
 *
 * @param mode The output mode.
 */
public record ModeChanged(OutputMode mode) implements Response {
  /**
   * Constructor.
   *
   * @param message The message.
   */
  ModeChanged(final byte[] message) {
    this(OutputMode.by(message[Index.DATA.get()]));
  }
}
