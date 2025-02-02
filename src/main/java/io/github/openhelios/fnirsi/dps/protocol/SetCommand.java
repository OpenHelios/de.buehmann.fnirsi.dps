package io.github.openhelios.fnirsi.dps.protocol;

import io.github.openhelios.fnirsi.dps.protocol.response.Group;

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

  /**
   * Command to set the group voltage.
   *
   * @param group The group.
   * @param index The index between 0 and 5.
   * @return The created command.
   */
  public static SetCommand groupVoltage(final Group group, final int index) {
    return new SetCommand( //
        RequestId.findById((byte) ((RequestId.GROUP_0_VOLTAGE.get() & 0xFF) + 2 * index)), //
        group.voltageInV());
  }

  /**
   * Command to set the group current.
   *
   * @param group The group.
   * @param index The index between 0 and 5.
   * @return The created command.
   */
  public static SetCommand groupCurrent(final Group group, final int index) {
    return new SetCommand( //
        RequestId.findById((byte) ((RequestId.GROUP_0_CURRENT.get() & 0xFF) + 2 * index)), //
        group.currentInA());
  }

  /**
   * Command to set the over output voltage protection value.
   *
   * @param maxVoltageInV The maximum output voltage in V.
   * @return The created command.
   */
  public static SetCommand overVoltageProtectionInV(final float maxVoltageInV) {
    return new SetCommand(RequestId.OVER_VOLTAGE_PROTECTION, maxVoltageInV);
  }

  /**
   * Command to set the over current protection value.
   *
   * @param maxCurrentInA The maximum current in A.
   * @return The created command.
   */
  public static SetCommand overCurrentProtectionInA(final float maxCurrentInA) {
    return new SetCommand(RequestId.OVER_CURRENT_PROTECTION, maxCurrentInA);
  }

  /**
   * Command to set the over power protection value.
   *
   * @param maxPowerInW The maximum power in W.
   * @return The created command.
   */
  public static SetCommand overPowerProtectionInW(final float maxPowerInW) {
    return new SetCommand(RequestId.OVER_POWER_PROTECTION, maxPowerInW);
  }

  /**
   * Command to set the over temperature protection value.
   *
   * @param maxTemperatureInC The maximum temperature in °C.
   * @return The created command.
   */
  public static SetCommand overTemperatureProtectionInC(final float maxTemperatureInC) {
    return new SetCommand(RequestId.OVER_TEMPERATURE_PROTECTION, maxTemperatureInC);
  }

  /**
   * Command to set the under input voltage protection value.
   *
   * @param minVoltageInV The minimum input voltage in V.
   * @return The created command.
   */
  public static SetCommand underVoltageProtectionInV(final float minVoltageInV) {
    return new SetCommand(RequestId.UNDER_VOLTAGE_PROTECTION, minVoltageInV);
  }

}
