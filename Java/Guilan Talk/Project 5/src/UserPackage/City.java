package UserPackage;

import Program.User;
import Structure.ArrayList;

public class City {
    String name;
    int index; //index of the city in the matrices

    ArrayList adjList;
    ArrayList citizens;

    public City(String name, int index) {
        this.name = name;
        this.index = index;
        this.adjList = new ArrayList(10);
        this.citizens = new ArrayList(50);
    }

    public void addAdjList(City city) {
        this.adjList.add(city);
    }
    public void addCitizen(User user) {
        this.citizens.add(user);
    }

    public String getName() {
        return name;
    }
    public int getIndex() {
        return index;
    }
    public ArrayList getAdjList() {
        return adjList;
    }
    public ArrayList getCitizens() {
        return citizens;
    }
}
