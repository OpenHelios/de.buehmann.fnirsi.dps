package io.github.openhelios.fnirsi.dps.protocol;

/**
 * Enumeration for the index in a message.
 */
public enum Index {

  /** The index for the header, which is the first byte in the message. */
  HEADER(0),

  /** The index for the command, which is the second byte in the message. */
  COMMAND(1),

  /** The index for the type, which is the third byte in the message. */
  TYPE(2),

  /** The index for the data size, which is the fourth byte in the message. */
  DATA_SIZE(3),

  /** The index for the first data byte, which is the fifth byte in the message. */
  DATA(4),

  ;

  private final int index;

  Index(final int index) {
    this.index = index;
  }

  /**
   * The index.
   *
   * @return The index.
   */
  public int get() {
    return index;
  }
}
