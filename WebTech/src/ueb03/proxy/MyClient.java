package ueb03.proxy;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyClient {
	public static void main(String[] args) {
		MyClient client = new MyClient();
		try {
			client.doRequest();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> doRequest() throws IOException {

		ArrayList<String> webPage = new ArrayList<>(); // HTML Code der Webpage

		try {

			// Zum Webserver verbinden
			String host = "public.hochschule-trier.de";
			int port = 80;
			Socket socket = new Socket(host, port);

			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
			BufferedReader din = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// Send a command...
			dout.writeBytes("GET /~schneidg/convert.htm HTTP/1.1 \n");
			dout.writeBytes("HOST: public.hochschule-trier.de \n");
			dout.writeByte('\n');

			// ...and receive the results
			// HTML Code ausgeben:
			/*
			 * System.out.println("Server says: "); String result; while ((result = din.readLine()) != null) { System.out.println(result); }
			 */
			// HTML Code ausgeben ende

			// gefilterter HTML Code
			/*
			 * <td align="center"><img height="100" alt="Schadenfreude" src="ausdruck_1.gif" width="100" /></td>
			 * 
			 * <td align="center">Schadenfreude</td>
			 */
			String txtLine;
			final String regex = "<img[^>]+alt=[\"]([^\"]+)[\"][^>]*>"; // Regulärer Ausdruck für Inhalt des alt-Attribut

			Pattern pattern = Pattern.compile(regex);
			StringBuffer buffer = new StringBuffer();

			while ((txtLine = din.readLine()) != null) {

				Matcher matcher = pattern.matcher(txtLine);
				buffer = new StringBuffer(txtLine.length());

				while (matcher.find()) {
					matcher.appendReplacement(buffer, "$1");
				}

				matcher.appendTail(buffer);

				webPage.add(buffer.toString());

			}

			// gefilterter HTML Code ende

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Page in der Console ausgeben
		// for (String s : webPage) {
		// System.out.println(s);
		// }

		return webPage;

	}
}
