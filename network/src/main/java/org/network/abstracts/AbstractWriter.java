package org.network.abstracts;

import java.io.OutputStream;

import org.network.contracts.Writer;

public abstract class AbstractWriter implements Writer {

	private OutputStream outputStream;

	public AbstractWriter(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public void inti() {

	}

	public void destroy() {

	}

	protected OutputStream getOutputStream() {
		return outputStream;
	}

}
