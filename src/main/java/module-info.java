@org.jspecify.annotations.NullMarked //
module de.buehmann.fnirsi.dps {
  // Java
  requires java.base;
  requires java.desktop;
  // static means only available at compile time
  requires static org.jspecify;
  // SLF4Js
  requires org.slf4j;
  // jCommSerial
  requires transitive com.fazecast.jSerialComm;

  exports de.buehmann.fnirsi.dps;
  exports de.buehmann.fnirsi.dps.protocol;
  exports de.buehmann.fnirsi.dps.protocol.response;

}
