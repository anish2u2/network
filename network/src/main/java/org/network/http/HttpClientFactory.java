package org.network.http;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

import javax.net.ssl.HttpsURLConnection;

import org.network.contracts.HttpNetwork;
import org.network.contracts.Reader;
import org.network.contracts.Writer;
import org.network.impl.ReaderImpl;
import org.network.impl.WriterImpl;

public class HttpClientFactory implements HttpNetwork {

	private URL url;

	private HttpURLConnection httpConnection;

	private HttpsURLConnection httpsConnection;

	private Reader reader;

	private Writer writer;

	private static Queue<HttpNetwork> networkQueue = new LinkedList<HttpNetwork>();

	{
		networkQueue.add(this);
	}

	private HttpClientFactory(URL url) {
		this.url = url;
	}

	public static HttpNetwork getInstance(URL url) {
		return new HttpClientFactory(url);
	}

	public void inti() {

	}

	public void destroy() {
		if (httpConnection != null) {
			httpConnection.disconnect();
			httpConnection = null;
		} else if (httpsConnection != null) {
			httpsConnection.disconnect();
			httpsConnection = null;
		}
		url = null;
		networkQueue.clear();
	}

	public void connect() throws Exception {
		try {
			httpConnection = (HttpURLConnection) url.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Reader getReader() throws Exception {
		if (reader == null) {
			reader = new ReaderImpl(
					httpConnection != null ? httpConnection.getInputStream() : httpsConnection.getInputStream());
		}
		return reader;
	}

	public Writer getWriter() throws Exception {
		if (writer == null) {
			writer = new WriterImpl(
					httpConnection != null ? httpConnection.getOutputStream() : httpsConnection.getOutputStream());
		}
		return writer;
	}

	public void disconnect() throws Exception {
		if (httpConnection != null) {
			httpConnection.disconnect();
		} else if (httpsConnection != null) {
			httpsConnection.disconnect();
		}
	}

}
