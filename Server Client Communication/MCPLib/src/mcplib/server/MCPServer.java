/**
 * @author Sapir Caduri 
 * 
 * 29/07/14
 * at TOM 
 * tomisrael.org
 * 
 */

package mcplib.server;

import mcplib.general.ICommands;
import mcplib.general.MCPMsg;
import mcplib.general.MCPMsgDeviceOpcode;
import mcplib.general.MCPMsgWithBuffer;
import mcplib.general.MCPMsgWithParams;
import mcplib.general.Utils;
import mcplib.general.Utils.DeviceOpcode;

import java.io.*; 
import java.net.*;
import java.util.logging.Logger;

public class MCPServer extends Thread {

	private final static Logger LOG = Logger.getLogger(MCPServer.class.getName());
	
	// A flag indicates which device is connected. 
	DeviceOpcode 	m_deviceOpcode;

	ServerSocket 	m_skServer = null;
	Socket			m_skClient = null;
	
	ICommands 		commandsManager = null;

	/**
	 * Server's constructor
	 * 
	 * @param Opcode 	- The device that is connected to the Arduino. Should not be "DeviceOpcode.Unkown"!
	 * @param cManager 	- the class that implements the interface of the commands. Should not be a null ! 
	 * 
	 * If one of two (null\Unknown) will received, function does not handle it properly.
	 */
	public MCPServer(DeviceOpcode Opcode, ICommands cManager) {
		
		
		if	(cManager == null)
			LOG.severe("MCPServer constructor - arg2 is null.");
		
		if	(m_deviceOpcode == DeviceOpcode.Unkown)
			LOG.severe("MCPServer constructor - arg1 - Device unkown.");
		
		m_deviceOpcode = Opcode;
		commandsManager = cManager;
		
		try {
			m_skServer = new ServerSocket(Utils.PORT_NUM);
		} catch (IOException e) {
			e.printStackTrace();
			LOG.severe("MCPServer constructor " + e.toString());
		}
	}

	
	/**
	 * run - thread's main function. being call when thread is created.
	 * it opens a socket and wait for connection from the client.
	 */
	public void run()
	{
		if (m_skServer == null){
			LOG.severe("run() - Socket is null.");
			return;
		}
		
		try {
			waitForConnection();
			handleClient();
		} catch (IOException e) {
			e.printStackTrace();
			LOG.severe("run() " + e.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			LOG.severe("run() " + e.toString());
		}
	}
	
	/**
	 * waitForConnection - waits for a connection from the client
	 * 
	 * @return 	true if the connection with the client established, 
	 * 			false if server socket is not initialized or any other failure.
	 * @throws 	IOException
	 */
	boolean waitForConnection() throws IOException {

		if (m_skServer == null){
			LOG.severe("waitForConnection() - Socket is null.");
			return (false);
		}

		LOG.info("Server is Waiting for connection...");
//		System.out.println("Server waiting for connection...");
		
		// Wait for a client connection
		m_skClient = m_skServer.accept();

		//Send him the deviceOpcode 
		ObjectOutputStream out  = new ObjectOutputStream(m_skClient.getOutputStream());
		MCPMsgDeviceOpcode msg = new MCPMsgDeviceOpcode(m_deviceOpcode);			
		out.writeObject(msg);
		
//		System.out.println("Server accepted connection from: " + m_skClient.getInetAddress().toString());
		LOG.info("Server accepted connection from: " + m_skClient.getInetAddress().toString());
		
		return (true);
	}
	
	
	/**
	 * handleClient - waits for a message from the client, parse it and operate the desired command.  
	 * 
	 * @return false if the socket is null, otherwise does not return (infinite loop)
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	boolean handleClient() throws IOException, ClassNotFoundException {
		
		if (m_skClient == null){
			LOG.severe("handleClient() - Socket is null.");
			return (false);
		}
		
		// Client messages reader
		ObjectInputStream in;
		
		in = new ObjectInputStream(m_skClient.getInputStream());
			
		// Infinite loop
		for (;;) {

			// Wait for data to arrive from the client
			MCPMsg msg = (MCPMsg) in.readObject();
			executeCommand(msg);
		}
	}
	
	boolean executeCommand(MCPMsg msg)
	{
		if (msg == null){
			LOG.warning("executeCommand() - msg is null.");
			return (false);
		}
		
		switch (msg.getCommandType())
		{
			case setInAFoldedPosition: 	commandsManager.setInAFoldedPosition(); break;
			case gripperClose:			commandsManager.gripperClose(); 		break;
			case gripperOpen:			commandsManager.gripperOpen(); 			break;
			case gripperMoveUp:			commandsManager.gripperMoveUp(); 		break;
			case gripperMoveDown:		commandsManager.gripperMoveDown();		break;
			case gripperMoveRight:		commandsManager.gripperMoveRight();		break;
			case gripperMoveLeft:		commandsManager.gripperMoveLeft();		break;
			case gripperRotateRight:	commandsManager.gripperRotateRight();	break;
			case gripperRotateLeft:		commandsManager.gripperRotateLeft();	break;
			case armRotateLeft:			commandsManager.armRotateLeft();		break;
			case armRotateRight:		commandsManager.armRotateRight();		break;
			case armUp:					commandsManager.armUp();				break;
			case armDown:				commandsManager.armDown();				break;
			case turnCameraOn:			commandsManager.turnCameraOn();			break;
			case turnCameraOff:			commandsManager.turnCameraOff();		break;
			case ZoomIn:				commandsManager.ZoomIn();				break;
			case ZoomOut:				commandsManager.ZoomOut();				break;
			case snap:					commandsManager.setInAFoldedPosition();	break;
			case armUp_angle:			commandsManager.armUp(
										((MCPMsgWithParams)msg).getParam1()); 	break;
			case armDown_angle:			commandsManager.armDown(
										((MCPMsgWithParams) msg).getParam1());	break;
			case gripperRotateRight_byAngle:
										commandsManager.gripperRotateRight(
										((MCPMsgWithParams) msg).getParam1());	break;
			case gripperRotateLeft_byAngle:
										commandsManager.gripperRotateLeft(
										((MCPMsgWithParams) msg).getParam1());	break;
			case armRotateRight_byAngle:
										commandsManager.armRotateRight(
										((MCPMsgWithParams) msg).getParam1());	break;
			case armRotateLeft_byAngle:
										commandsManager.armRotateLeft(
										((MCPMsgWithParams) msg).getParam1());	break;
			case playMidiBuffer:
										commandsManager.playMidiBuffer(
										((MCPMsgWithBuffer) msg).getBuffer()); 	break;
			default: break;
		}
		
		return true;
	}

}
