package io.github.openhelios.fnirsi.dps.protocol;

/**
 * The baud rate command.
 */
public enum BaudRate {

  /** 1192500 bits per second. */
  BPS_1192500(4),

  ;

  private final byte b;

  /**
   * The byte.
   *
   * @return The byte representing this baud rate.
   */
  public byte get() {
    return b;
  }

  BaudRate(final int index) {
    b = (byte) (index + 1);
  }

}
