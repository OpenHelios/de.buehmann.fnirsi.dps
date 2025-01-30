package io.github.openhelios.fnirsi.dps.protocol;

/**
 * Enumeration representing constant commands for DPS-150.
 */
public enum ConstCommand implements Command {

  /** Command to connect. */
  CONNECT(CommandId.CONNECTION, true),

  /** Command to disconnect. */
  DISCONNECT(CommandId.CONNECTION, false),

  /** Command to set the baud rate 115200. */
  BAUD_RATE_115200(CommandId.BAUD_RATE, (byte) 5),

  /**
   * Command to request the model name, which results in a
   * {@link io.github.openhelios.fnirsi.dps.protocol.response.ModelName} response.
   */
  REQUEST_MODEL_NAME(CommandId.GET, RequestId.MODEL_NAME),

  /**
   * Command to request the hardware version, which results in a
   * {@link io.github.openhelios.fnirsi.dps.protocol.response.HardwareVersion} response.
   */
  REQUEST_HARDWARE_VERSION(CommandId.GET, RequestId.HARDWARE_VERSION),

  /**
   * Command to request the firmware version, which results in a
   * {@link io.github.openhelios.fnirsi.dps.protocol.response.FirmwareVersion} response.
   */
  REQUEST_FIRMWARE_VERSION(CommandId.GET, RequestId.FIRMWARE_VERSION),

  /**
   * Command to request the all values, which results in a {@link io.github.openhelios.fnirsi.dps.protocol.response.All}
   * response.
   */
  REQUEST_ALL(CommandId.GET, RequestId.ALL),

  ;

  private final byte[] bytes;

  ConstCommand(final CommandId commandId, final byte data) {
    bytes = new byte[6];
    bytes[Index.HEADER.get()] = HeaderId.OUTPUT.get();
    bytes[Index.COMMAND.get()] = commandId.get();
    bytes[Index.TYPE.get()] = 0;
    bytes[Index.DATA_SIZE.get()] = 1;
    bytes[Index.DATA.get()] = data;
    Checksum.write(bytes);
  }

  ConstCommand(final CommandId commandId, final boolean isEnabled) {
    this(commandId, (byte) (isEnabled ? 1 : 0));
  }

  ConstCommand(final CommandId commandId, final RequestId requestId) {
    bytes = new byte[6];
    bytes[Index.HEADER.get()] = HeaderId.OUTPUT.get();
    bytes[Index.COMMAND.get()] = commandId.get();
    bytes[Index.TYPE.get()] = requestId.get();
    bytes[Index.DATA_SIZE.get()] = 1;
    bytes[Index.DATA.get()] = requestId.get();
    Checksum.write(bytes);
  }

  @Override
  public byte[] get() {
    return bytes;
  }
}
