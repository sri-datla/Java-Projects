
public class StaticAgent extends Agent{
	Node currentNode;
	public StaticAgent(Node[] nodes) {
		super(nodes);
	}

	@Override
	public void Move() {
		this.currentNode = nodes[super.PickRandomNumber(20)];
		
	}
	

}
