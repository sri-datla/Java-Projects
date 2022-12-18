import java.util.Random;

public class RandomTarget extends Agent{
	Node currentNode;
	public RandomTarget(Node[] nodes)
	{
		
		super(nodes);
		currentNode = nodes[super.PickRandomNumber(20)];
	}
	@Override
	public void Move() {
		// Random target moves from start node to it's neighboring node
		
		currentNode = nodes[super.PickRandomNumber(currentNode.neighbour.size())];
	}

}
