package org.network.contracts;

import org.workers.contracts.Destroy;
import org.workers.contracts.Init;

public interface Network extends Init, Destroy {

	public Connection connect(String url) throws Exception;

	public void destroyAllActiveConnection() throws Exception;

}
