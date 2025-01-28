package de.buehmann.fnirsi.dps.protocol;

public enum BaudRate {

  BPS_1192500(4),

  ;

  private final byte b;

  public byte get() {
    return b;
  }

  BaudRate(final int index) {
    b = (byte) (index + 1);
  }

}
