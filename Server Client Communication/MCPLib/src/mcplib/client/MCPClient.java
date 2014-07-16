/**
 * @author Sapir Caduri 
 * 
 * 29/07/14
 * at TOM 
 * tomisrael.org
 * 
 */

package mcplib.client;

import mcplib.general.*;
import mcplib.general.Utils.DeviceOpcode;
import java.io.*; 
import java.net.*;
import java.util.logging.Logger;


public class MCPClient implements ICommands {

	private final static Logger LOG = Logger.getLogger(MCPClient.class.getName());

	Utils.DeviceOpcode m_deviceOpcode;
	
	public Utils.DeviceOpcode getDeviceOpcode() {
		return m_deviceOpcode;
	}

	Socket 				m_skServer = null;
//	DataOutputStream	m_stOutput = null;
	
	ObjectOutputStream 	m_stOutput = null;
	ObjectInputStream 	m_stInput  = null;
	
	/**
	 * connect() 
	 * 
	 * @param remoteHost - server's IP address. for a localhost, set this field to "127.0.0.1"
	 * @return true if a connection established, false otherwise.
	 */
	public boolean connect(String remoteHost) {
		
		try {
			// Connect to the server
			m_skServer = new Socket(remoteHost, Utils.PORT_NUM);
			
			// Get the output stream object
			m_stOutput  = new ObjectOutputStream(m_skServer.getOutputStream());   
			m_stInput	= new ObjectInputStream(m_skServer.getInputStream()); 
//			BufferedReader 		inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			
			MCPMsgDeviceOpcode msg = (MCPMsgDeviceOpcode) m_stInput.readObject();
			this.m_deviceOpcode = msg.getDeviceOpcode();
			if (m_deviceOpcode  == DeviceOpcode.Unkown || 
					((m_deviceOpcode != DeviceOpcode.CameraArm) && (m_deviceOpcode != DeviceOpcode.RoboticArm))) {
				LOG.severe("connect() - Can't tell wich device we're currently operating ");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.severe("connect() " + e.toString());
			return (false);
		}
		
		return (true);
	}
	
	/**
	 * disconnect() - closes the connection to the server
	 * 
	 */
	public void disconnect(){
		
		try {
			m_skServer.close();
		} catch (IOException e) {
			e.printStackTrace();
			LOG.severe("disconnect() " + e.toString());
		}
	}

	
	/**
	 * sendCommand() - Sends the server the desired command that should be operated.  
	 * 
	 * @param MCPMsg
	 * @return
	 */
	boolean sendCommand(MCPMsg msg)
	{
		try {
			//m_stOutput.writeBytes("CommandType: " + commandType.toString() + "\n");
			m_stOutput.writeObject(msg);
			m_stOutput.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
			LOG.severe("sendCommand() " + e.toString());
			return (false);
		}
		return (true);
	}
	
	//-----------------------------------------------------
	//				Devices functionality
	//-----------------------------------------------------
	
	
	@Override
	public void setInAFoldedPosition() {
		MCPMsg msg = new MCPMsg(CommandType.setInAFoldedPosition); 
		sendCommand(msg);
	}

	@Override
	public void gripperClose() {
		MCPMsg msg = new MCPMsg(CommandType.gripperClose); 
		sendCommand(msg);
	}

	@Override
	public void gripperOpen() {
		MCPMsg msg = new MCPMsg(CommandType.gripperOpen); 
		sendCommand(msg);
	}

	@Override
	public void gripperMoveUp() {
		MCPMsg msg = new MCPMsg(CommandType.gripperMoveUp); 
		sendCommand(msg);
	}

	@Override
	public void gripperMoveDown() {
		MCPMsg msg = new MCPMsg(CommandType.gripperMoveDown); 
		sendCommand(msg);
	}

	@Override
	public void gripperMoveRight() {
		MCPMsg msg = new MCPMsg(CommandType.gripperMoveRight); 
		sendCommand(msg);
	}

	@Override
	public void gripperMoveLeft() {
		MCPMsg msg = new MCPMsg(CommandType.gripperMoveLeft); 
		sendCommand(msg);
	}

	@Override
	public void gripperRotateRight() {
		MCPMsg msg = new MCPMsg(CommandType.gripperRotateRight); 
		sendCommand(msg);
	}

	@Override
	public void gripperRotateLeft() {
		MCPMsg msg = new MCPMsg(CommandType.gripperRotateLeft); 
		sendCommand(msg);
	}

	@Override
	public void gripperRotateLeft(int angle) {
		MCPMsgWithParams msg = new MCPMsgWithParams(CommandType.gripperRotateLeft_byAngle, angle, 0); 
		sendCommand(msg);
	}

	@Override
	public void gripperRotateRight(int angle) {
		MCPMsgWithParams msg = new MCPMsgWithParams(CommandType.gripperRotateRight_byAngle, angle, 0); 
		sendCommand(msg);
		
	}

	@Override
	public void armRotateLeft() {
		MCPMsg msg = new MCPMsg(CommandType.armRotateLeft); 
		sendCommand(msg);
	}

	@Override
	public void armRotateRight() {
		MCPMsg msg = new MCPMsg(CommandType.armRotateRight); 
		sendCommand(msg);
	}

	@Override
	public void armRotateLeft(int angle) {
		MCPMsgWithParams msg = new MCPMsgWithParams(CommandType.armRotateLeft_byAngle, angle, 0); 
		sendCommand(msg);
	}

	@Override
	public void armRotateRight(int angle) {
		MCPMsgWithParams msg = new MCPMsgWithParams(CommandType.armRotateRight_byAngle, angle, 0); 
		sendCommand(msg);
	}

	@Override
	public void armUp() {
		MCPMsg msg = new MCPMsg(CommandType.armUp); 
		sendCommand(msg);
	}

	@Override
	public void armDown() {
		MCPMsg msg = new MCPMsg(CommandType.armDown); 
		sendCommand(msg);
	}

	@Override
	public void armUp(int angle) {
		MCPMsgWithParams msg = new MCPMsgWithParams(CommandType.armUp_angle, angle, 0); 
		sendCommand(msg);
	}

	@Override
	public void armDown(int angle) {
		MCPMsgWithParams msg = new MCPMsgWithParams(CommandType.armDown_angle, angle, 0); 
		sendCommand(msg);
	}

	@Override
	public void turnCameraOn() {
		MCPMsg msg = new MCPMsg(CommandType.turnCameraOn); 
		sendCommand(msg);
	}

	@Override
	public void turnCameraOff() {
		MCPMsg msg = new MCPMsg(CommandType.turnCameraOff); 
		sendCommand(msg);
	}

	@Override
	public void ZoomIn() {
		MCPMsg msg = new MCPMsg(CommandType.ZoomIn); 
		sendCommand(msg);
	}

	@Override
	public void ZoomOut() {
		MCPMsg msg = new MCPMsg(CommandType.ZoomOut); 
		sendCommand(msg);
	}

	@Override
	public void snap() {
		MCPMsg msg = new MCPMsg(CommandType.snap); 
		sendCommand(msg);
	}

	@Override
	public void playMidiBuffer(short[] buffer) {
		MCPMsgWithBuffer msg = new MCPMsgWithBuffer(CommandType.playMidiBuffer, buffer); 
		sendCommand(msg);
	}
}
