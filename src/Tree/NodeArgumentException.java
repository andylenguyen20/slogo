package Tree;

/**
 * Exception for when there are too many or too little nodes for a parent node
 *
 * author: Charles Dracos
 */
public class NodeArgumentException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public NodeArgumentException() {
		super("Error: There are an improper amount of arguments");
	}
}
