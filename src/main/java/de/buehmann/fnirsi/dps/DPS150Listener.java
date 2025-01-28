package de.buehmann.fnirsi.dps;

import de.buehmann.fnirsi.dps.protocol.response.Response;

public interface DPS150Listener {

  void onMessage(Response response);

}
