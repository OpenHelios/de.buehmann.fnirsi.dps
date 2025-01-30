package io.github.openhelios.fnirsi.dps.protocol.response;

/**
 * Enumeration for the two output modes automatically switched by DPS-150.
 */
public enum OutputMode {

  /** Constant current output with ID 0. */
  CC,

  /** Constant voltage output with ID 1. */
  CV,

  ;

  /**
   * Returns the output mode for the given ID.
   *
   * @param id The ID.
   * @return The output mode.
   */
  public static OutputMode by(final byte id) {
    if (0 == id) {
      return CC;
    }
    return CV;
  }

}
