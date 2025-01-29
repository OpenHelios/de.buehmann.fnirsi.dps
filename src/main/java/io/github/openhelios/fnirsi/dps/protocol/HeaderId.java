package io.github.openhelios.fnirsi.dps.protocol;

/**
 * The header byte is the first byte of a message.
 */
public enum HeaderId {

  INPUT(0xF0),

  OUTPUT(0xF1),

  ;

  private final byte b;

  public byte get() {
    return b;
  }

  HeaderId(final int b) {
    this.b = (byte) (b & 0xFF);
  }
}
