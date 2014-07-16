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

import mcplib.general.ICommands.CommandType;

public class MCPMsgWithBuffer extends MCPMsg implements Serializable {

//	private final static Logger LOG = Logger.getLogger(MCPMsgWithBuffer.class.getName());

	private static final long serialVersionUID = -498261256600625891L;
	short[] 	buffer;
    
    public short[] getBuffer() {
		return buffer;
	}

	public void setBuffer(short[] buffer) {
		this.buffer = buffer;
	}

	public MCPMsgWithBuffer(CommandType commandType, short[] buffer) {
    	super(commandType);
    	this.buffer = 	buffer;
    }
    
}