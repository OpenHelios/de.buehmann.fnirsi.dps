package io.github.openhelios.fnirsi.dps.protocol;

public enum ConstCommand implements Command {

  CONNECT(CommandId.CONNECTION, true),

  DISCONNECT(CommandId.CONNECTION, false),

  BAUD_RATE_115200(CommandId.BAUD_RATE, (byte) 5),

  REQUEST_MODEL_NAME(CommandId.GET, RequestId.MODEL_NAME),

  REQUEST_HARDWARE_VERSION(CommandId.GET, RequestId.HARDWARE_VERSION),

  REQUEST_FIRMWARE_VERSION(CommandId.GET, RequestId.FIRMWARE_VERSION),

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
