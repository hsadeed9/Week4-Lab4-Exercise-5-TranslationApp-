package demotcpserversidedate.src;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class launch the server side application using TCP.
 * The server generates current date.
 * Each connected client will received current date from the server.
 * 
 * @author emalianakasmuri
 *
 */

public class MyServerSideApplication {

	/**
	 * Main entry point to the server side application
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		// Launch the server frame
		ServerDataFrame serverFrame = new ServerDataFrame();
		serverFrame.setVisible(true);
		
		// Binding to a port or any other port no you are fancy of
		int portNo = 4228;
		ServerSocket serverSocket = new ServerSocket(portNo);
		
		ServerDataGenerator myDataGenerator = new ServerDataGenerator();
		
		// Counter to keep track the number of requested connection
		int totalRequest = 0;
		
		// Server needs to be alive forever
		while (true) {
			
			if (totalRequest>0)
			// Message to indicate server is alive
			serverFrame.updateServerStatus(false);
			else 
				serverFrame.updateServerStatus(true);
			
			// Accept client request for connection
			Socket clientSocket = serverSocket.accept();
			
			// Generate current date
			
			
			myDataGenerator.setCurrentDate(serverFrame.AppendedString);
			myDataGenerator.setNoOFWordsInAString(serverFrame.GetInputFromGui);
			
			
			// Create stream to write data on the network
			DataOutputStream outputStream = 
					new DataOutputStream(clientSocket.getOutputStream());
			
			
			// Send current date back to the client
		     outputStream.writeBytes(myDataGenerator.getCurrentDate());
			
			outputStream.writeBytes(myDataGenerator.getNoOFWordsInAString());
			
			
			
			// Close the socket
			clientSocket.close();
		
			// Update the request status
			serverFrame.updateRequestStatus(
					"Data sent to the client: " + myDataGenerator.getCurrentDate()+"\nAnd Number of Words ="+myDataGenerator.getNoOFWordsInAString());
			serverFrame.updateRequestStatus("Accepted connection to from the "
					+ "client. Total request = " + ++totalRequest );
			
			
			
			
		}
		
		

	}

}
