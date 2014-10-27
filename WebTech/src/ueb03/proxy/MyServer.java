package ueb03.proxy;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServer {

	public static void handleRequest(Socket client) throws IOException {

		PrintWriter out = new PrintWriter(client.getOutputStream());

		// Resource vom Webserver holen
		ArrayList<String> html = MyClient.doRequest(); // Resource vom Webserver holen

		// Daten zum Client übermitteln
		for (String s : html) {
			out.println(s);
		}
		out.flush();
	}

	public static void main(String[] args) {

		Socket client = null;
		int port = 8080;

		try {

			// Erstelle Server- Socket
			ServerSocket server = new ServerSocket(port);

			while (true) { // Warte auf Verbindung
				client = server.accept(); // Verbindung annehmen
				handleRequest(client); // Request verarbeiten
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
