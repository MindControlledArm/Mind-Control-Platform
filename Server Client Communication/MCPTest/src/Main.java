/**
 * @author Sapir Caduri 
 * 
 * 29/07/14
 * at TOM 
 * tomisrael.org
 * 
 */

import mcplib.client.*;
import mcplib.general.Utils.DeviceOpcode;
import mcplib.server.MCPServer;

public class Main {

	public static void main(String[] args) {
		
		
		String IP_ADDR = "127.0.0.1"; 

		System.out.println("Hello MCP!");
		
		// Start the server
		CommandsManager commands = new CommandsManager();
		MCPServer srv = new MCPServer(DeviceOpcode.RoboticArm, commands);
		srv.start();
		System.out.println("Server is up.");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Connect to the server (fabricate using localhost)
		MCPClient client = new MCPClient();
		client.connect(IP_ADDR);
		
		System.out.println("A command from client has been received.");
		System.out.println("Device opcode: " + client.getDeviceOpcode());

		// Send command to the server
		client.gripperRotateRight(54);
		
		// And another command 
		short[] buff = {1, 2, 3, 34, 55}; 
		client.playMidiBuffer(buff);
		
	}
}
