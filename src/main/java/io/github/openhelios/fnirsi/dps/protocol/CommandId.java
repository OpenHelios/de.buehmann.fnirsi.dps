package io.github.openhelios.fnirsi.dps.protocol;

public enum CommandId {

  /**
   * The connect command followed by 0 and
   * <ol>
   * <li>1 is for connecting and
   * <li>0 is for disconnecting with the device.
   * </ol>
   */
  CONNECTION(0xC1),

  /**
   * Send a request to get some information. The {@link RequestId} follows directly.
   */
  GET(0xA1),

  /**
   * The baud rate command sets the baud rate after connection. Followed by 0 and
   * <ol>
   * <li>1 for 9600,
   * <li>2 for 19200,
   * <li>3 for 38400,
   * <li>4 for 57600,
   * <li>5 for 115200
   * </ol>
   * in bits per second.
   */
  BAUD_RATE(0xB0),

  /**
   * Sets a value.
   */
  SET(0xB1),

  ;

  private final byte b;

  CommandId(final int b) {
    this.b = (byte) b;
  }

  public byte get() {
    return b;
  }

}
