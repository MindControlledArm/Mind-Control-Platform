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

public class MCPMsg implements Serializable {

	CommandType commandType;

//	private final static Logger LOG = Logger.getLogger(MCPMsg.class.getName());

	private static final long serialVersionUID = -5067329687768214843L;

	public MCPMsg(CommandType commandType) {
    	this.commandType = commandType;
    }
	
    public CommandType getCommandType() {
		return commandType;
	}

	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}


}
