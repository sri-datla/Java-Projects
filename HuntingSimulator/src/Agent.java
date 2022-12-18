
public abstract class Agent {
	int currentPosition;
	String status; // Alive - 1, Dead - 0
	int score;
	Node[] nodes;
	int randomNumber;
	public Agent(Node[] nodes)
	{
		this.status = "alive";
		this.score = 0;
		this.nodes = nodes;
	}
	
	public int PickRandomNumber(int size)
	{
		randomNumber = (int) (Math.random() * size);
		return randomNumber;
	}
	
	public abstract void Move();

}
