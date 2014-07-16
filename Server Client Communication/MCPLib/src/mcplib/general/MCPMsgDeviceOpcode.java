/**
 * @author Sapir Caduri 
 * 
 * 29/07/14
 * at TOM 
 * tomisrael.org
 * 
 */

package mcplib.general;

import java.io.Serializable;

import mcplib.general.Utils.DeviceOpcode;

public class MCPMsgDeviceOpcode implements Serializable {

	private static final long serialVersionUID = 4727954564051194870L;

	DeviceOpcode deviceOpcode = DeviceOpcode.Unkown;
	
	public MCPMsgDeviceOpcode(DeviceOpcode deviceOpcode){
		this.deviceOpcode = deviceOpcode;
	}
	
	public DeviceOpcode getDeviceOpcode() {
		return deviceOpcode;
	}

	public void setDeviceOpcode(DeviceOpcode deviceOpcode) {
		this.deviceOpcode = deviceOpcode;
	}

}
