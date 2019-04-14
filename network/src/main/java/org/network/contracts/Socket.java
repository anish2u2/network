package org.network.contracts;

import java.net.InetAddress;

public interface Socket {

	public Connection connect(String host, int port);

	public Connection connect(InetAddress host, int port);

	public Connection connect(int port);

	public Connection connect(String address);

	public void terminate();

	public void close();

}
