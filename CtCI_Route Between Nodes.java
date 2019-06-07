/*CTCI_Route Between Nodes

4.1 Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a route between two nodes. */

enum State {Unvisited, Visited, Visiting}

boolean search(Graph g, Node start, Node end){
	if (start == end) return true;

	//operates as Queue
	LinkedList<Node> q = new LinkedList<Node>();

	for(Node u : g.getNodes()){
		u.state = State.Unvisited;
	}

	start.state = State.Visiting;
	q.add(start);
	Node u;
	while (!q.isEmpty()){
		u = q.removeFirst(); //i.e., dequeue()
		if (u != null){
			for (Node v : u.getAdjacent()){
				if (v.state == State.Unvisited){
					if (v == end){
						return true;
					}
					else{
						v.state = State.Visiting;
						q.add(v);
					}
				}
			}
		}
		u.state = State.Visited;
	}
	return false;
}

/* 
Can be solved by graph traversal using DFS or BFS. DFS is a bit easier to implement with simple recursion, but BFS is useful to find the shortest path. DFS may traverse one adjacent very deeply before going onto the immediate neighbors. 

Need to mark the "Visited" status to avoid cycles and repetition of the nodes.

Should we traverse twice? Since we dont know which node is the start and which is the end? 
*/
