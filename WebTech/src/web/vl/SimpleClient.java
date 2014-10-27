package web.vl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient {
	// Our socket connection to the server
	protected Socket serverConn;

	public SimpleClient(String host, int port) throws IllegalArgumentException {
		try {
			System.out.println("Trying to connect to " + host + " " + port);
			serverConn = new Socket(host, port);
		} catch (UnknownHostException e) {
			throw new IllegalArgumentException("Bad host name given.");
		} catch (IOException e) {
			System.out.println("SimpleClient: " + e);
			System.exit(1);
		}
		System.out.println("Made server connection.");
	}

	public static void main(String argv[]) {
		SimpleClient client = new SimpleClient("www.informatik.fh-trier.de", 80);
		client.sendCommands();
	}

	public void sendCommands() {
		try {
			DataOutputStream dout = new DataOutputStream(serverConn.getOutputStream());
			BufferedReader din = new BufferedReader(new InputStreamReader(serverConn.getInputStream()));
			// Send a command...
			dout.writeBytes("GET /~schneider/weitere.html HTTP/1.1 \n");
			dout.writeBytes("HOST: www.informatik.fh-trier.de \n");
			dout.writeByte('\n');
			// ...and receive the results
			System.out.println("Server says: ");
			String result;
			while ((result = din.readLine()) != null) {
				System.out.println(result);
			}
		} catch (IOException e) {
			System.out.println("Communication SimpleClient: " + e);
			System.exit(1);
		}
	}

	@Override
	public synchronized void finalize() {
		System.out.println("Closing down SimpleClient...");
		try {
			serverConn.close();
		} catch (IOException e) {
			System.out.println("Close SimpleClient: " + e);
			System.exit(1);
		}
	}
}