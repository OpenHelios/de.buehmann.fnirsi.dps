package io.github.openhelios.fnirsi.dps.protocol;

/**
 * Interface for a command.
 */
public interface Command {

  /**
   * Returns the message bytes.
   *
   * @return The bytes of the message.
   */
  byte[] get();

}
