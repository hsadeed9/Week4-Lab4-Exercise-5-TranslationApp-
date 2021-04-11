import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This method launch the frame and manage the connection to the server.
 * 
 * @author emalianakasmuri
 *
 */

public class ClientSideApplication {

	public static void main(String[] args) 
			throws UnknownHostException, IOException {
		
		// Launch client-side frame
		ClientSideFrame clientSideFrame = new ClientSideFrame();
		clientSideFrame.setVisible(true);
		
		// Connect to the server @ localhost, port 4228
		Socket socket = new Socket(InetAddress.getLocalHost(), 4228);
		
		
		// Update the status of the connection
		clientSideFrame.updateConnectionStatus(socket.isConnected());
		
		// Read from network
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));


	
		
		// Display the current date
		String ReceivedStingFromServer = bufferedReader.readLine();
		
	
		clientSideFrame.updateServerDate(ReceivedStingFromServer);
		
		
	
		
		
		
		

		
		// Close everything
		bufferedReader.close();
		socket.close();

	}

}
