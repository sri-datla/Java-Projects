
public class MoveAgent extends Agent {
	Node currentNode = nodes[super.PickRandomNumber(20)];
	public MoveAgent(Node[] nodes) {
		super(nodes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Move() {
		// TODO Auto-generated method stub
		currentNode = nodes[currentNode.neighbour.get(0).index]; // Move agent will always move to the first neighbor node
	}
	

}
