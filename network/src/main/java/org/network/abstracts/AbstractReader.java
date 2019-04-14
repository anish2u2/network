package org.network.abstracts;

import java.io.InputStream;

import org.network.contracts.Reader;

public abstract class AbstractReader implements Reader {

	private InputStream stream;

	public AbstractReader(InputStream stream) {
		this.stream = stream;
	}

	public void inti() {

	}

	public void destroy() {

	}

	protected InputStream getInputStream() {
		return this.stream;
	}

}
