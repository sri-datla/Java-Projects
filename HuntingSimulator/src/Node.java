import java.util.ArrayList;

public class Node {
	public int index;
	ArrayList<Node> neighbour = new ArrayList<Node>();
	public Node(int index)
	{
		this.index = index;
	}
	public void AddConnected (Node nextNode)
	{
			neighbour.add(nextNode);
	}
	public int CheckForDuplicates(int value)
	{
		int temp = 0;
		for(int j=0; j<neighbour.size();j++)
		{
			if(value == this.neighbour.get(j).index)
			{
				temp = 1;
				break;
			}
		}
		return temp;
	}
	public void AddConnected (Node[] nodes)
	{
		int id;
		ArrayList<Integer> indecies = new ArrayList<Integer>(); //valid index array
		
		//get the valid IDs (nodes)
		for(int i=0;i<20;i++)  //20 is hardcoded 
		{
	
			if(i!=this.index)
			{
				if(CheckForDuplicates(i) !=1)
				{
					indecies.add(i);
				}
				
			}
		}
		
		for(int i=0;i<3;i++)  //5 is hardcoded 
		{
			//System.out.println(indecies.size());
			id = (int) (Math.random()*(indecies.size()-1));
			neighbour.add(nodes[indecies.get(id)]);
			nodes[indecies.get(id)].AddConnected(nodes[this.index]);
			indecies.remove(id);
		}
	}
	@Override
	public String toString()
	{
		String result = "";
		result = "Node index : "+ index + "\nThe neighbour(s) are "  ;
		
		for(int i=0;i<neighbour.size();i++)
		{
			result = result + "\t"+ neighbour.get(i).index;
		}
		return result;
	}
}
