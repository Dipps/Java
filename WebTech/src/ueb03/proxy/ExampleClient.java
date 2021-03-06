package ueb03.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

//Quelle: http://de.wikibooks.org/wiki/Java_Standard:_Socket_ServerSocket_(java.net)_UDP_und_TCP_IP
public class ExampleClient {
	public static void main(String[] args) {
		ExampleClient client = new ExampleClient();
		try {
			client.test();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void test() throws IOException {
		String ip = "127.0.0.1"; // localhost
		int port = 11111;
		java.net.Socket socket = new java.net.Socket(ip, port); // verbindet sich mit Server

		String zuSendendeNachricht = "Hello, world!";
		schreibeNachricht(socket, zuSendendeNachricht);
		String empfangeneNachricht = leseNachricht(socket);
		System.out.println(empfangeneNachricht);
	}

	void schreibeNachricht(java.net.Socket socket, String nachricht) throws IOException {
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		printWriter.print(nachricht);
		printWriter.flush();
	}

	String leseNachricht(java.net.Socket socket) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		char[] buffer = new char[200];
		int anzahlZeichen = bufferedReader.read(buffer, 0, 200); // blockiert bis Nachricht empfangen

		String nachricht = new String(buffer, 0, anzahlZeichen);
		return nachricht;
	}
}
