package Structure;

import Program.User;

import java.util.Objects;


class Node {
    User user;
    ArrayList adjList;

    boolean visited;

    public Node(User user) {
        this.user = user;
        this.adjList = new ArrayList(100);
        this.visited = false;
    }

    public void addAdj(Node adj) {
        this.adjList.add(adj);
    }
    public void removeAdj(Node adj) {
        this.adjList.remove(adj);
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public User getUser() {
        return user;
    }
    public ArrayList getAdjList() {
        return adjList;
    }
    public boolean getVisited() {
        return visited;
    }
}

public class DirectedGraph { //directed weightless graph
    ArrayList vertices; //list of users vertices (nodes)

    int capacity;

    public DirectedGraph(int capacity) {
        this.capacity = capacity;
        this.vertices = new ArrayList(capacity);
    }

    public void addUser(User user) {
        Node node = new Node(user);
        this.vertices.add(node);
    }
    public void dfs1(Object node, ArrayList stack) {
        ((Node) node).setVisited(true);
        for (int i = 0; i < ((Node) node).getAdjList().getLast() + 1; i++) {
            Node adj = (Node) ((Node) node).getAdjList().get(i);
            if (!adj.getVisited()) {
                dfs1(adj, stack);
            }
        }
        for (int i = 0; i < ((Node) node).getAdjList().getLast() + 1; i++) {
            Node adj = (Node) ((Node) node).getAdjList().get(i);
        }
        stack.add(node); //push the vertex to the stack after all adjacent vertices are visited
    }
    public void dfs2() {

    }
    public ArrayList transposedGraph() {
        ArrayList transposed = new ArrayList(capacity); //transposed vertices

        for (int i = 0; i < vertices.getLast() + 1; i++) {
            Node newNode = new Node(((Node) vertices.get(i)).getUser());
            transposed.add(newNode);
        }

        for (int i = 0; i < transposed.getLast() + 1; i++) {
            for (int j = 0; j < vertices.getLast() + 1; j++) {
                if (((Node) vertices.get(j)).getAdjList().contains(transposed.get(i))) {
                    ((Node) transposed.get(i)).addAdj((Node) vertices.get(j));
                }
            }
        }

        return transposed;
    }
    public void printSCC() {

    }

    public ArrayList getVertices() {
        return vertices;
    }
    public int getCapacity() {
        return capacity;
    }

    private Object searchNode(Node node, ArrayList graph) {
        for (int i = 0; i < graph.getLast() + 1; i++) {
            if (Objects.equals(node, graph.get(i)))
                return graph.get(i);
        }
        return null;
    }
    private void kosarajuAlgorithm() {
        ArrayList stack = new ArrayList(capacity);

        //first DFS to fill the stack with vertices in their finishing order
        for (int i = 0; i < vertices.getLast() + 1; i++) {
            if (!((Node) vertices.get(i)).getVisited()) {
                dfs1(vertices.get(i), stack);
            }
        }

        ArrayList transposed = transposedGraph();

        //reset visited boolean for each node for the second DFS
        for (int i = 0; i < vertices.getLast() + 1; i++) {
            ((Node) this.vertices.get(i)).setVisited(false);
        }
    }
}
