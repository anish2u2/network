package org.network.impl;

import java.io.OutputStream;

import org.network.abstracts.AbstractWriter;

public class WriterImpl extends AbstractWriter {

	public WriterImpl(OutputStream outputStream) {
		super(outputStream);
	}

	public void write(String line) throws Exception {
		byte[] buffer = line.getBytes();
		write(buffer, 0, buffer.length);
	}

	public void write(byte[] buffer) throws Exception {
		write(buffer, 0, buffer.length);
	}

	public void write(byte[] buffer, int start, int numberOfBytes) throws Exception {
		getOutputStream().write(buffer, start, numberOfBytes);
	}

}
