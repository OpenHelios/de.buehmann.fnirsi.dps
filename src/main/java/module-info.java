@org.jspecify.annotations.NullMarked //
module io.github.openhelios.fnirsi.dps {
  // Java
  requires java.base;
  requires java.desktop;
  // static means only available at compile time
  requires static org.jspecify;
  // SLF4Js
  requires org.slf4j;
  // jCommSerial
  requires transitive com.fazecast.jSerialComm;

  exports io.github.openhelios.fnirsi.dps;
  exports io.github.openhelios.fnirsi.dps.protocol;
  exports io.github.openhelios.fnirsi.dps.protocol.response;

}
