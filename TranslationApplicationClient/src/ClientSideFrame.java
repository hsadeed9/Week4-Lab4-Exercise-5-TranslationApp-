import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class represent the window for the client side TCP application.
 * It display the date retrieve from the server.
 * 
 * 
 *
 */
public class ClientSideFrame extends JFrame {
	
	private static final String CLIENT_ID = "hsadeed3@gmail.com";
	  private static final String CLIENT_SECRET = "715f21356b7f4894a6e0b52381428f28";
	  private static final String ENDPOINT = "http://api.whatsmate.net/v1/translation/translate";
	
	private static final long serialVersionUID = 1L;
	
	// Private frame components
	private static JLabel lblServerDate;
	private JLabel lblStatusValue;
	private JLabel lblServerText;
	
	// Private attributes for frame size
	private int width = 1000;
	private int height = 200;


	/**
	 * The constructor that initialize and organize the Swing components on 
	 * the frame.
	 */
	public ClientSideFrame () {
		
		// Default frame setting
		this.setLayout(new BorderLayout());
		this.setTitle("TCP Application: Client Side");
		this.setSize(width, height);
		
		
		// Center the frame on the screen
        this.setLocationRelativeTo(null);
		
		// Initialize default value for label
		lblServerDate = new JLabel("-");
		lblStatusValue = new JLabel("-");
		lblServerText=new JLabel("-");
		
		// Must close on X
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Organize components vv 
		loadComponent();
		
	}
	
	String fromLang = "en";
    String toLang = "ar";
    
    
	
		public void updateServerDate (String serverDate) {
			
			
		
			
			if (serverDate.contains("Translate to Melayu in the Server Side & sent to Client")) {
				
				serverDate = serverDate.replace("Translate to Melayu in the Server Side & sent to Client","");
				
			
			
			String st = serverDate;
			String st1=st.replaceAll("[^A-Za-z]", " ");
		
			System.out.println("Input String = "+st1);
		
			toLang = "ms";
			String text = st1;
			
			
		       
			    try {
				ClientSideFrame.translate1( fromLang, toLang, text);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
					
		
					
			}
			}
			
			else if(serverDate.contains("Translate to Arab in the Server Side & Sent to Client")) {
				
				serverDate = serverDate.replace("Translate to Arab in the Server Side & Sent to Client","");
				toLang = "ar";
			
			
			String st = serverDate;
			String st1=st.replaceAll("[^A-Za-z]", " ");
		
			System.out.println("Input String = "+st1);
		
		
			String text = st1;
			
			
		       
			    try {
				ClientSideFrame.translate1( fromLang, toLang, text);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
					
				}
					
			}
			
			else {
				
				if(serverDate.contains("Translate to Korean in the Server Side & sent to Client")){
				serverDate = serverDate.replace("Translate to Korean in the Server Side & sent to Client","");
				toLang = "ko";
			
			
			String st = serverDate;
			String st1=st.replaceAll("[^A-Za-z]", " ");
		
			System.out.println("Input String = "+st1);
		
		
			String text = st1;
			
			
		       
			    try {
				ClientSideFrame.translate1( fromLang, toLang, text);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
					
				}
				
				
				
				
				
				
				
				
				
			}
				
			}
				
	}
		
		
		public static void translate1(String fromLang, String toLang, String text) throws Exception {
		    
		    String jsonPayload = new StringBuilder()
		      .append("{")
		      .append("\"fromLang\":\"")
		      .append(fromLang)
		      .append("\",")
		      .append("\"toLang\":\"")
		      .append(toLang)
		      .append("\",")
		      .append("\"text\":\"")
		      .append(text)
		      .append("\"")
		      .append("}")
		      .toString();

		    URL url = new URL(ENDPOINT);
		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    conn.setDoOutput(true);
		    conn.setRequestMethod("POST");
		    conn.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
		    conn.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
		    conn.setRequestProperty("Content-Type", "application/json");

		    OutputStream os = conn.getOutputStream();
		    os.write(jsonPayload.getBytes());
		    os.flush();
		    os.close();

		    int statusCode = conn.getResponseCode();
		   
		    BufferedReader br = new BufferedReader(new InputStreamReader(
		        (statusCode == 200) ? conn.getInputStream() : conn.getErrorStream()
		      ));
		    String output;
		    while ((output = br.readLine()) != null) {
		        System.out.println(output);
		        
		        
		        lblServerDate.setText(output);
		        
		        
				
		    }
		    conn.disconnect();
		 
		  }
		
	
	
	public void updateConnectionStatus (boolean connStatus) {
		

		// Default status. Assuming for the worst case scenario.
		String status = "No connection to server.";
		
		// Validate status of connection
		if (connStatus)
			status = "Connection has established.";
		
				
		// Update the status on frame
		this.lblStatusValue.setText(status);
	}
	
	/**
	 * This method creates and arrange Swing components to display status of 
	 * client's connection to the server.
	 * 
	 * @param font
	 * @return Swing components organized in a panel.
	 */
	private JPanel getConnectionStatusPanel(Font font) {
		
		// Create component
		JPanel panel = new JPanel();
		JLabel lblConnStatus = new JLabel ("Connection status: ");
		
		// Style the component
		lblConnStatus.setFont(font);
		lblStatusValue.setFont(font);
		lblConnStatus.setBackground(Color.WHITE);
		lblConnStatus.setOpaque(true);
		lblStatusValue.setBackground(Color.WHITE);
		lblStatusValue.setOpaque(true);
		
		// Organize components into panel
		panel.add(lblConnStatus);
		panel.add(lblStatusValue);
		
		return panel;
		
	}
	

	
	/**
	 * This method creates and arrange Swing components to date retrieve from 
	 * the server.
 	 *
	 * @param font
	 * @return Swing components organized in panel
	 */
	private JPanel getServerDatePanel(Font font) {
		
		// Create component to display date retrieve from the server
		JPanel panel = new JPanel();
		JLabel lblDate = new JLabel ("Translated Text is: ");
		
		
		
		// Style the component
		lblDate.setFont(font);
		lblServerDate.setFont(font);
		lblDate.setBackground(Color.WHITE);
		lblDate.setOpaque(true);
		lblServerDate.setBackground(Color.WHITE);
		lblServerDate.setOpaque(true);

		// Organize components into panel
		panel.add(lblDate);
		panel.add(lblServerDate);
	
		
		return panel;
	} 
	
	
	/**
	 * This method arrange the Swing components on the frame.
	 */
	private void loadComponent() {
		
		// Get font
		Font font = this.getFontStyle();
		
		// Get server status's panel and add to frame
		JPanel northPanel = this.getConnectionStatusPanel(font);		
		this.add(northPanel, BorderLayout.NORTH);
		
		// Get server date's panel and add to frame
		JPanel center = getServerDatePanel(font);
		this.add(center, BorderLayout.CENTER);
		
	
		
		
	}
	
	
	/**
	 * This method define a font to a generic style.
	 * 
	 * @return font object
	 */
	private Font getFontStyle() {
		
		Font font = new Font( "Nano Sans" , Font.PLAIN, 30);
		
		return font;
		
	}
	
	
	
	
	
}
