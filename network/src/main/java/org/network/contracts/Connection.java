package org.network.contracts;

import org.workers.contracts.Destroy;
import org.workers.contracts.Init;

public interface Connection extends Init, Destroy {

	public Reader getReader() throws Exception;

	public Writer getWriter() throws Exception;

}
