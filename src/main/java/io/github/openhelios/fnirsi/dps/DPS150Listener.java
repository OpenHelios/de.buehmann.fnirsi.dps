package io.github.openhelios.fnirsi.dps;

import io.github.openhelios.fnirsi.dps.protocol.response.Response;

public interface DPS150Listener {

  void onMessage(Response response);

}
