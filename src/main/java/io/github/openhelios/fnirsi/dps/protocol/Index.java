package io.github.openhelios.fnirsi.dps.protocol;

public enum Index {

  HEADER(0),

  COMMAND(1),

  TYPE(2),

  DATA_SIZE(3),

  DATA(4),

  ;

  private final int index;

  Index(final int index) {
    this.index = index;
  }

  public int get() {
    return index;
  }
}
