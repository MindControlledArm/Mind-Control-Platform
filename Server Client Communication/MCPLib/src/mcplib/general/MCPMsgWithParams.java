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

public class MCPMsgWithParams extends MCPMsg implements Serializable{
	 
//	private final static Logger LOG = Logger.getLogger(MCPMsgWithParams.class.getName());

	private static final long serialVersionUID = 5039525831807276874L;

	int param1;
    int param2;
    
    public MCPMsgWithParams (CommandType commandType, int param1, int param2) {
    	super(commandType);
    	this.param1 = param1;
    	this.param2 = param2;
    }
    
    public int getParam1() {
    	return param1;
    }
    
    public void setParam1(int param1) {
    	this.param1 = param1;
    }
    
    public int getParam2() {
    	return param2;
    }
    
    public void setParam2(int param2) {
    	this.param2 = param2;
    }
}