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
2. Alternatively, can use DFS to find the build path. The basic idea is once a node's children have been added, add this node to the front. Same time complexity.
3. This is the topological sort problem.
4. Find the source node and add them to the order, remove processed source node and update the remaining nodes' dependencies, repeat and find new source nodes and remove them until all the nodes are added. 
*/

//find a correct build order
String[] buildOrder(String[] projects, String[][] dependencies){
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

String[] sortProjects(Graph g){
	Arraylist<Project> nodes = g.getNodes();
	String[] result = new String[nodes.size()];

	//add source nodes' names into result
	int endPos = addZeroDependent(result, nodes, 0); //endPos is the position of the last source node (no incoming edges) 
	int processPos = 0;
	while(processPos < result.length()){
		Project cur = g.getNode(result[processPos]);
		if (cur == null) {
			return null;
		} //cycle dependency since there is no remaining source node

		Arraylist<Project> children = cur.getChildren();
		//remove the dependency from its chidren since it is already finished
		for (Project child : children){
			child.minusDependencies();
		}
		endPos = addZeroDependent(result, children, endPos);
		processPos++;
	}
	return result;
}

int addZeroDependent(String[] result, Arraylist<Project> nodes, int endPos){
	for (Project node : nodes){
		if (node.getDependenciesCount == 0){
			result[endPos] = node.getName();
			endPos++;
		}
	}
	return endPos;
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
	private Arraylist<Project> children = new Arraylist<Project>();
	private HashMap<String, Project> map = new HashMap<String, Project>();
	private String name;
	private int dependenciesCount = 0;

	public Project(String s){
		name = s;
	}

	public Arraylist<Project> getChildren(){
		return children;
	}

	public int getDependenciesCount(){
		return dependenciesCount;
	}

	public String getName(){return name;}

	public void addNeighbor(Project p){
		if (!map.containsKey(p.getName())){
			children.add(p);
			map.put(p.getName(), p);
			p.addDependencies();
		}
	}

	public void addDependencies(){dependenciesCount++;}
	public void minusDependencies(){dependenciesCount--;}
}

public class Graph{
	private Arraylist<Project> nodes = new Arraylist<Project>();
	private HashMap<String, Project> map = new HashMap<String, Project>();

	public Project getOrCreateNode(String name){
		if (!map.containsKey(name)){
			Project node = new Project(name);
			nodes.add(node);
			map.put(name,node);
		}
		return map.get(name);
	}

	public void addEdge(String startName, String endName){
		Project start = getOrCreateNode(startName);
		Project end = getOrCreateNode(endName);
		start.addNeighbor(end);
	}

	pubic Arraylist<Project> getNodes(){return nodes;}
}