package org.network.contracts;

import org.workers.contracts.Destroy;
import org.workers.contracts.Init;

public interface Reader extends Init, Destroy {

	public String readLine() throws Exception;

	public int read(byte[] buffer) throws Exception;

	public int read(byte[] buffer, int start, int length) throws Exception;

}
