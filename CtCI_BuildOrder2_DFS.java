/* 4.7 Build Order: You are given a list of projects and a list of dependencies (which is a list of pairs of
projects, where the second project is dependent on the first project). All of a project's dependencies
must be built before the project is. Find a build order that will allow the projects to be built. If there
is no valid build order, return an error.
EXAMPLE
Input:
projects: a, b, c, d, e, f
dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)
Output: f, e, a, b, d, c */

/*
1. O(V + E) time.
2. use DFS to find the build path. The basic idea is once a node's children have been added, add this node to the front. Same time complexity.
3. This is the topological sort problem.
4. be careful about cycles and avoid infinite loop. A cycle will happen if we run back into the same path. So we need a status to mark a node as "processing" and if we see this node again, we know that there is a cycle here. Always remember to update the status when its DFS is done.

*/

//find a correct build order
Stack<Project> buildOrder(String[] projects, String[][] dependencies){
	//build a graph based on the given projects and deoendencies
	Graph g = buildGraph(projects, dependencies);
	//topological sort projects
	return sortProjects(g);
}

Graph buildGraph(String[] projects, String[][] dependencies){
	Graph g = new Graph();
	//add each project into the graph
	for (String p : projects){
		Project node = new Project(p);
		g.addNode(node);
	}

	//add edges
	for (String[] d : dependencies){
		g.addEdge(d[0],d[1]);
	}
	return g;
}

Stack<Project> sortProjects(Graph g){
	Arraylist<Project> nodes = g.getNodes();
	Stack<Project> result = new Stack<Project>();

	for (Project node:nodes){
		if (node.getState() == Project.State.BLANK) {
			if (!doDFS(result, node)){
				return null;
			}
		}
	}
	return result;
}

boolean doDFS(Stack<Project> result, Project node){
	if(node.getState() == Project.State.PROCESSING) {
		return false;
	}
	if (node.getState() == Project.State.BLANK){
		node.setState(Project.State.PROCESSING);
		Arraylist<Project> children = node.getChildren();
		for (Project child : chidren){
			if (!doDFS(result, child)){
				return false;
			}
		}
		node.setState(Project.State.DONE);
		result.push(node);
	}
}

public class Graph{
	private Arraylist<Project> nodes = new Arraylist<Project>();
	private HashMap<String, Project> map = new HashMap<String, Project>();

	public void addNode(String project){
		if (!map.containsKey(project)){
			Project p = new Project(project);
			nodes.add(p);
			map.put(project, p);
		}
	}
	public Project getNode(String project){
		if (!map.containsKey(project)){
			return null;
		}
		return map.get(project);
	}

	public void addEdge(String start, String End){
		Project start = getNode(start);
		Project end = getNode(end);
		start.addNeighbor(end);
	}

	public Arraylist<Project> getNodes(){
		return nodes;
	}
}

public class Project{
	public enum State {BLANK, PROCESSING, DONE};
	private Arraylist<Project> children = new Arraylist<Project>();
	private HashMap<String, Project> map = new HashMap<String, Project>();
	private String name;
	private State state = State.BLANK;

	public Project(String s){
		name = s;
	}

	public Arraylist<Project> getChildren(){
		return children;
	}

	public String getName(){return name;}
	public State getState(){return sate;}
	public void setState(State s){state = s;}

	public void addNeighbor(Project p){
		if (!map.containsKey(p.getName())){
			children.add(p);
			map.put(p.getName(), p);
			p.addDependencies();
		}
	}
}