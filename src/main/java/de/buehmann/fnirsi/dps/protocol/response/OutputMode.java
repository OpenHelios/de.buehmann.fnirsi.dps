package de.buehmann.fnirsi.dps.protocol.response;

public enum OutputMode {

  /** Constant Current Output. */
  CC,

  /** Constant Voltage Output. */
  CV,

  ;

  public static OutputMode by(final byte id) {
    if (0 == id) {
      return CC;
    }
    return CV;
  }

}
