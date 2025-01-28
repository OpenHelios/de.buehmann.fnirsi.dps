package de.buehmann.fnirsi.dps.protocol;

public class Data {

  private Data() {
    // hide constructor for this utility class
  }

  public static String hex(final byte data) {
    return String.format("%02X ", data & 0xFF);
  }

  public static String hex(final byte[] data) {
    final StringBuilder sb = new StringBuilder(data.length * 2);
    for (final byte b : data) {
      sb.append(String.format("%02X ", b & 0xFF));
    }
    return sb.toString();
  }

  public static String text(final byte[] data, final int index, final int size) {
    final StringBuilder sb = new StringBuilder(size);
    for (int i = index; i < index + size; i++) {
      final char c = (char) (data[i] & 0xFF);
      sb.append(c < 0x20 ? '.' : c);
    }
    return sb.toString();
  }

  public static float float32(final byte[] data, final int index) {
    return Float.intBitsToFloat( //
        (data[index + 3] & 0xFF) << 24 //
            | (data[index + 2] & 0xFF) << 16 //
            | (data[index + 1] & 0xFF) << 8 //
            | data[index] & 0xFF //
    );
  }

  public static void setFloat32(final byte[] data, final int index, final float value) {
    final int bits = Float.floatToIntBits(value);
    data[index + 0] = (byte) (bits & 0xFF);
    data[index + 1] = (byte) (bits >>> 8 & 0xFF);
    data[index + 2] = (byte) (bits >>> 16 & 0xFF);
    data[index + 3] = (byte) (bits >>> 24 & 0xFF);
  }

  public static int uint8(final byte b) {
    return b & 0xFF;
  }

  public static int uint8(final byte[] bytes, final int index) {
    return uint8(bytes[index]);
  }

  public static boolean bool(final byte[] bytes, final int index) {
    return bytes[index] != 0;
  }

}
