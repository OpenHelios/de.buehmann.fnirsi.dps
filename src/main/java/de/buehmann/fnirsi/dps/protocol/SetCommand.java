package de.buehmann.fnirsi.dps.protocol;

public class SetCommand implements Command {

  byte[] bytes;

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

  public static SetCommand outputVoltage(final float voltageInV) {
    return new SetCommand(RequestId.OUTPUT_VOLTAGE, voltageInV);
  }

  public static SetCommand outputCurrent(final float currentInA) {
    return new SetCommand(RequestId.OUTPUT_CURRENT, currentInA);
  }

  public static SetCommand outputOn(final boolean isEnabled) {
    return new SetCommand(RequestId.OUTPUT_ON, isEnabled);
  }

  public static SetCommand meteringOn(final boolean isEnabled) {
    return new SetCommand(RequestId.METERING_ON, isEnabled);
  }

  public static SetCommand meteringState(final byte state) {
    return new SetCommand(RequestId.METERING_ON, state);
  }

  public static SetCommand brightness(final int state) {
    return new SetCommand(RequestId.BRIGHTNESS_STATE, (byte) state);
  }

  public static SetCommand volume(final int state) {
    return new SetCommand(RequestId.VOLUME_STATE, (byte) state);
  }

}
