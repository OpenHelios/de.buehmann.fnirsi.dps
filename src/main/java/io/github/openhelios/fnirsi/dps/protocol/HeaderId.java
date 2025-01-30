package io.github.openhelios.fnirsi.dps.protocol;

/**
 * The header byte is the first byte of a message.
 */
public enum HeaderId {

  /** The header byte for an input message. */
  INPUT(0xF0),

  /** The header byte for an output message. */
  OUTPUT(0xF1),

  ;

  private final byte b;

  /**
   * The byte for the header ID.
   *
   * @return The byte for the header ID.
   */
  public byte get() {
    return b;
  }

  HeaderId(final int b) {
    this.b = (byte) (b & 0xFF);
  }
}
