package org.network.impl;

import java.io.InputStream;

import org.network.abstracts.AbstractReader;

public class ReaderImpl extends AbstractReader {

	private byte[] buffer = new byte[1024 * 4];

	public ReaderImpl(InputStream stream) {
		super(stream);
	}

	public String readLine() throws Exception {
		if (read(buffer, 0, buffer.length) == -1) {
			return null;
		}
		return new String(buffer);
	}

	public int read(byte[] buffer) throws Exception {

		return getInputStream().read(buffer);
	}

	public int read(byte[] buffer, int start, int length) throws Exception {
		return getInputStream().read(buffer, start, length);
	}

}
