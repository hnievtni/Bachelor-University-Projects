package ClinicProject;

import java.util.ArrayList;

public class Clinic {
    String name;
    String type;

    private Manager manager;

    ArrayList<Section> sections=new ArrayList<>();
    ArrayList<User> users=new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setManager(Manager manager) {
        this.manager = manager;
    }
    public void setSections(Section section){
        this.sections.add(section);
    }
    public void setUsers(User user) {
        this.users.add(user);
    }

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public Manager getManager() {
        return manager;
    }
    public ArrayList<Section> getSections() {
        return sections;
    }
    public ArrayList<User> getUsers() {
        return users;
    }
}
