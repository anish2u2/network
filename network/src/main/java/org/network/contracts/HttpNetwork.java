package org.network.contracts;

import org.workers.contracts.Destroy;
import org.workers.contracts.Init;

public interface HttpNetwork extends Init, Destroy {

	public void connect() throws Exception;

	public Reader getReader() throws Exception;

	public Writer getWriter() throws Exception;

	public void disconnect() throws Exception;

}
