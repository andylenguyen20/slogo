package Tree;

import java.util.ArrayList;


import java.util.List;

import commandNode.*;
import nodes.Node;;

public class TreeMaker {
	private ArrayList<Node> nodes;
	private ArrayList<Node> heads;
	private int index;
	
	public TreeMaker (List<Node> nodes) {
		this.nodes = (ArrayList<Node>) nodes;
		heads = new ArrayList<Node>();
		index = 0;
		while (index < this.nodes.size()) {
			if (!this.nodes.get(index).hasNext()) {
				throw new HeadException();
			}
			heads.add(makeTree(this.nodes.get(index)));
		}
	}
	
	public List<Node> getHeads() {
		return heads;
	}
	
	private Node makeTree(Node node) {
		index+=1;
		while (node!=null && node.hasNext()) {
			try {
				node.addChild(nodes.get(index));
			}
			catch (IndexOutOfBoundsException e){
				throw new NodeArgumentException();
			}
			Node curr = node.getChild();
			makeTree(curr);
		}
		node.reset();
		return node;
	}
}
