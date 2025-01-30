package io.github.openhelios.fnirsi.dps.protocol;

/**
 * Command to set a value, which can be send to the DPS-150.
 */
public class SetCommand implements Command {

  private final byte[] bytes;

  private SetCommand(final RequestId requestId, final byte value) {
    bytes = new byte[6];
    bytes[Index.HEADER.get()] = HeaderId.OUTPUT.get();
    bytes[Index.COMMAND.get()] = CommandId.SET.get();
    bytes[Index.TYPE.get()] = requestId.get();
    bytes[Index.DATA_SIZE.get()] = 1;
    bytes[Index.DATA.get()] = value;
    Checksum.write(bytes);
  }

  private SetCommand(final RequestId requestId, final boolean isEnabled) {
    this(requestId, (byte) (isEnabled ? 1 : 0));
  }

  private SetCommand(final RequestId requestId, final float value) {
    bytes = new byte[9];
    bytes[Index.HEADER.get()] = HeaderId.OUTPUT.get();
    bytes[Index.COMMAND.get()] = CommandId.SET.get();
    bytes[Index.TYPE.get()] = requestId.get();
    bytes[Index.DATA_SIZE.get()] = 4;
    Data.setFloat32(bytes, Index.DATA.get(), value);
    Checksum.write(bytes);
  }

  @Override
  public byte[] get() {
    return bytes;
  }

  /**
   * Command to set the output voltage in V.
   *
   * @param voltageInV The voltage in V.
   * @return The created command.
   */
  public static SetCommand outputVoltage(final float voltageInV) {
    return new SetCommand(RequestId.OUTPUT_VOLTAGE, voltageInV);
  }

  /**
   * Command to set the output current in A.
   *
   * @param currentInA The current in A.
   * @return The created command.
   */
  public static SetCommand outputCurrent(final float currentInA) {
    return new SetCommand(RequestId.OUTPUT_CURRENT, currentInA);
  }

  /**
   * Command to set the output on or off.
   *
   * @param isEnabled True to enable output.
   * @return The created command.
   */
  public static SetCommand outputOn(final boolean isEnabled) {
    return new SetCommand(RequestId.OUTPUT_ON, isEnabled);
  }

  /**
   * Command to set the metering on or off.
   *
   * @param isEnabled True to enable metering.
   * @return The created command.
   */
  public static SetCommand meteringOn(final boolean isEnabled) {
    return new SetCommand(RequestId.METERING_ON, isEnabled);
  }

  /**
   * Command to set the brightness state of the LCD display.
   *
   * @param state The brightness state between 0 and 15.
   * @return The created command.
   */
  public static SetCommand brightness(final int state) {
    return new SetCommand(RequestId.BRIGHTNESS_STATE, (byte) state);
  }

  /**
   * Command to set the volume state for the beep sound.
   *
   * @param state The volume state between 0 and 15.
   * @return The created command.
   */
  public static SetCommand volume(final int state) {
    return new SetCommand(RequestId.VOLUME_STATE, (byte) state);
  }

}
