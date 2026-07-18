package Structure;

import Program.User;
import java.util.Arrays;
import java.util.Objects;

class DWNode {
    User user;
    ArrayList suggestions;

    int index; //index of the city in the matrices

    public DWNode(User user, int index) {
        this.user = user;
        this.suggestions = new ArrayList(100);
        this.index = index;
    }

    public void addSuggestion(DWNode suggestion) {
        this.suggestions.add(suggestion);
    }
    public void removeSuggestion(DWNode suggestion) {
        this.suggestions.remove(suggestion);
    }
    public void setSuggestions(ArrayList suggestions) {
        this.suggestions = suggestions;
    }

    public User getUser() {
        return user;
    }
    public ArrayList getSuggestion() {
        return suggestions;
    }
    public int getIndex() {
        return index;
    }
}

public class DWGraph {
    ArrayList usersNodes;
    int[][] weights;

    int capacity;
    int last;

    public DWGraph(int capacity) {
        this.capacity = capacity;
        this.usersNodes = new ArrayList(capacity);
        this.weights = new int[capacity][capacity];
        this.last = -1;

        //initialize
        for (int i = 0; i < capacity; i++)
            for (int j = 0; j < capacity; j++)
                this.weights[i][j] = 0;
    }

    public void addUser(User user) {
        this.last++;
        DWNode node = new DWNode(user, last);
        this.usersNodes.add(node);
    }

    public void addSuggestion(User user, User suggestion, int weight) {
        DWNode userNode = (DWNode) searchNode(user);
        DWNode suggestionNode = (DWNode) searchNode(suggestion);

        assert userNode != null;
        userNode.addSuggestion(suggestionNode);
        edge(userNode, suggestionNode, weight);
    }
    public void removeSuggestion(User user, User suggestion) {
        DWNode userNode = (DWNode) searchNode(user);
        DWNode suggestionNode = (DWNode) searchNode(suggestion);

        assert userNode != null;
        userNode.removeSuggestion(suggestionNode);
        edge(userNode, suggestionNode, 0);
    }
    public void setSuggestions(User user) {
        DWNode userNode = (DWNode) searchNode(user);
        assert userNode != null;

        ArrayList suggestions = new ArrayList(capacity);
        int[] list = weights[userNode.getIndex()];
        Arrays.sort(list);

        for (int k = capacity - 1; k >= 0; k--) {
            for (int j = 0; j < capacity; j++) {
                if (weights[userNode.getIndex()][j] == list[k])
                    suggestions.add(searchUser(j));
            }
        }

        userNode.setSuggestions(suggestions);
    }
    public ArrayList getSuggestions(User user) {
        //setSuggestions(user); ERROR!!!!!
        DWNode userNode = (DWNode) searchNode(user);
        assert userNode != null;
        return userNode.getSuggestion();
    }

    public void edge(Object user, Object suggestion, int weight) {
        this.weights[((DWNode) user).getIndex()][((DWNode) suggestion).getIndex()] = weight;
    }
    public int getEdge(Object user, Object suggestion) {
        return weights[((DWNode) user).getIndex()][((DWNode) suggestion).getIndex()];
    }

    public ArrayList getUsers() {
        return usersNodes;
    }
    public int[][] getWeights() {
        return weights;
    }
    public int getCapacity() {
        return capacity;
    }

    private Object searchNode(User user) {
        for (int i = 0; i < usersNodes.getLast() + 1; i++) {
            if (Objects.equals(user, ((DWNode) usersNodes.get(i)).getUser())) {
                return usersNodes.get(i);
            }
        }
        return null;
    }
    private Object searchUser(int index) {
        for (int i = 0; i < usersNodes.getLast() + 1; i++) {
            if (((DWNode) usersNodes.get(i)).getIndex() == index) {
                return usersNodes.get(i);
            }
        }
        return null;
    }
}
