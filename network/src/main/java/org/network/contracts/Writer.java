package org.network.contracts;

import org.workers.contracts.Destroy;
import org.workers.contracts.Init;

public interface Writer extends Init, Destroy {

	public void write(String line) throws Exception;

	public void write(byte[] buffer) throws Exception;

	public void write(byte[] buffer, int start, int numberOfBytes) throws Exception;

}
