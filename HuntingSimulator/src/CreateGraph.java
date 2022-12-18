import java.util.ArrayList;

public class CreateGraph {

	public static void main(String[] args) {
		Node[] nodes = new Node[20];
		ArrayList<Integer> indecies = new ArrayList<Integer>();
		
		
		for(int i=0;i<nodes.length;i++)
		{
			nodes[i] = new Node(i);
			indecies.add(i);
		}
		
		for(int i=0;i<nodes.length-1;i++)
		{
			nodes[i].AddConnected(nodes[i+1]);
			nodes[i+1].AddConnected(nodes[i]);
		}
		
		nodes[0].AddConnected(nodes[nodes.length-1]);
		nodes[nodes.length-1].AddConnected(nodes[0]);
		// connecting the nodes from start to end so that there will not be any unreachable nodes
		
		for(int i=0;i<nodes.length;i++)
		{
			nodes[i].AddConnected(nodes);
		}

		
		// create 10 Random Target objects 
		RandomTarget[] RandomTargets = new RandomTarget[10];
		
		for(int i=0; i<RandomTargets.length;i++)
		{
			RandomTargets[i] = new RandomTarget(nodes);
			
		}
		
		simulate(nodes, RandomTargets);

	}
	
	public static void simulate(Node[] nodes, RandomTarget[] RandomTargets)
	{
		ArrayList<Integer> deadTargets = new ArrayList<Integer>();
		ArrayList<Integer> deadTargetPositions = new ArrayList<Integer>();
		MoveAgent moveAgent = new MoveAgent(nodes);
		StaticAgent staticAgent = new StaticAgent(nodes);
		staticAgent.Move();
		
		for(int i=0; i<10;i++)
		{
			for (int j=0;j< RandomTargets.length;j++)
			{
				if(RandomTargets[j].status == "alive")
				{
					RandomTargets[j].Move();
					if(RandomTargets[j].currentNode.index == moveAgent.currentNode.index)
					{
						deadTargets.add(j);
						deadTargetPositions.add(RandomTargets[j].currentNode.index);
						moveAgent.score += 1;
						RandomTargets[j].status = "dead";
					}
					else if (RandomTargets[j].currentNode.index == staticAgent.currentNode.index)
					{
						deadTargets.add(j);
						deadTargetPositions.add(RandomTargets[j].currentNode.index);
						staticAgent.score += 1;
						RandomTargets[j].status = "dead";
						
					}
				}
				

				
			}
			moveAgent.Move();
		
		}
		System.out.println("status of random targets: ");
		for(int i=0;i<RandomTargets.length;i++)
		{
			System.out.println(i + " is " + RandomTargets[i].status);
		}
		for(int i=0;i<deadTargets.size();i++)
		{
			System.out.println("random target : "+ deadTargets.get(i) + " dead at the node " + deadTargetPositions.get(i));
		}
		
		System.out.println("Score of moving target is " + moveAgent.score);
		
		System.out.println("Score of static target is " + staticAgent.score);
		
		
		
		
		
		
		
		
		
		
		
	}

}
