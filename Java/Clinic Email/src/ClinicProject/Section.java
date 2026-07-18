package ClinicProject;

import java.util.ArrayList;

public class Section {
    String name;
    String type;

    private Receptionist receptionist;

    ArrayList<Doctor> doctors=new ArrayList<>();
    ArrayList<Patient> patients=new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
    }
    public void setDoctors(Doctor doctor) {
        this.doctors.add(doctor);
    }
    public void setPatients(Patient patient) {
        this.patients.add(patient);
    }

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public Receptionist getReceptionist() {
        return receptionist;
    }
    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }
    public ArrayList<Patient> getPatients() {
        return patients;
    }
}
