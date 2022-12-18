1.	Application that simulates agents chase a targets in a graph. 
2.	Created a Node class, which is used to represent a node.  
It has the following attributes:
a.	Index: the index of the node
b.	ConnectedNodes: a list of nodes who is accessible from the current node.
c.	AddConnected(): the method to add the adjacent (neighbor node). 
i.	If node a’s has node b as its neighbor; then we made node a also to be included in node b’ s Adjacent list.  
3.	Created a Test class which has the main() method. 

4.	Created a graph consisting of 20 Node objects and called AddConnected () method to create the graph.
5.	We will first add a path from the first node to the last node, i.e., the 2nd node will be added the ConnectedNodes list of the 1st node, the 3rd node will be added to that of the 2nd and so on so forth so that there are no nodes left unconnected. 
6.	We will then add the neighbor node for some nodes in the graph randomly. We will add 5 more edges.  
7.	Then created an abstract class Agent, which has the following attributes and method: 
a.	Position: the agent’s current position on the graph, which is the nodes’ index.
b.	Status:   the agent’s status (alive or dead).
c.	Score:   the score of the agent 
d.	Move(): it is an abstract method to describe how the agent moves. 
8.	Extended the Agent class to create a RandomTarget class by implementing the Move() method. The RandomTarget will start from a rand node and then move to its neighbor randomly.
9.	Extended the Agent class to create a StaticAgent class by implementing the Move() method. The StaticAgent will stay at the initialized random position, i.e., not moving actually
10.	Then extended the Agent class to create a MoveAgent class by implementing the Move() method. The MoveAgent will always move to the first neighbor in the Adjacent ArrayList. 

11.	Then in the main() method
Created 10 RandomTarget objects and initialize their position randomly. 
Create 1 StaticAgent and 1 MoveAgent, where the positions are randomly generated.
Defined and invoked Simulate() method, within this method, we will simulate the target and agents are moving in the graph, in every step, they will move according the Move() method defined for themselves. 
a.	Once the target and agent move to the same node, i.e., they meet each other, the agent will eat the target by setting the target status to dead and increase the score of the agent. 
b.	The simulation will stop once all the targets are eaten or the number of steps are more than 10.
