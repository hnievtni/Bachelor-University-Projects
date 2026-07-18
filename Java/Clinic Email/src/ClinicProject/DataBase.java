package ClinicProject;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Formatter;
import java.util.HashMap;

interface Services {
    void findDoctor(Manager manager);
    void findReceptionist(Manager manager);
    User userValidity(Clinic clinic,String username, String pass);
}

public class DataBase {

    static File clinicFolder,sectionFolder;

    static HashMap<String, Clinic> clinics=new HashMap<>();// <clinic name, clinic>

    public static void setClinics(String clinicName, Clinic clinic) {
        clinics.put(clinicName,clinic);
    }
    public static HashMap<String, Clinic> getClinics() {
        return clinics;
    }

    public static void setClinicInfo(Clinic clinic){
        Formatter formatter = new Formatter();
        String format = "|%-30s|%-30s|%-30s|%-30s|%n";{
            formatter.format(format, centerString("Clinic Name", 30),
                    centerString("Clinic Type", 30), centerString("Manager Name", 30),
                    centerString("Manager Salary", 30));
            formatter.format(format, centerString(clinic.getName(), 30), centerString(clinic.getType(), 30),
                    centerString(clinic.getManager().getName(), 30),
                    centerString(String.valueOf(clinic.getManager().getSalary()), 30));
        }
        clinicFolder= new File(Main.projectFolder,clinic.getName());
        if (!clinicFolder.mkdir() && !clinicFolder.exists()) return;
        File clinicInfo=new File(Path.of(clinicFolder.getAbsolutePath(),"Clinic Info.txt").toString());
        try{
            FileWriter writer=new FileWriter(clinicInfo,true);
            writer.write(formatter.toString());
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"ERROR!");
        }
    }
    public static void setClinicSections(Clinic clinic) {
        Formatter formatter = new Formatter();
        String format = "|%-30s|%-30s|%-30s|%-30s|%n";{
            formatter.format(format, centerString("Section Name", 30),
                    centerString("Section Type", 30), centerString("Receptionist Name", 30),
                    centerString("Receptionist Salary", 30));
            for (Section section:clinic.getSections()){
                formatter.format(format, centerString(section.getName(), 30), centerString(section.getType(), 30),
                        centerString(section.getReceptionist().getName(), 30),
                        centerString(String.valueOf(section.getReceptionist().getSalary()), 30));
            }
        }
        clinicFolder= new File(Main.projectFolder,clinic.getName());
        if (!clinicFolder.mkdir() && !clinicFolder.exists()) return;
        File sectionsList=new File(Path.of(clinicFolder.getAbsolutePath(),"Sections List.txt").toString());
        try{
            FileWriter writer=new FileWriter(sectionsList,true);
            writer.write(formatter.toString());
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"ERROR!");
        }
    }
    public static void setSectionDoctors(Section section){
        Formatter formatter = new Formatter();
        String format = "|%-30s|%-30s|%-30s|%-30s|%n";{
            formatter.format(format, centerString("Doctor Name", 30),
                    centerString("Doctor Type", 30), centerString("Doctor Salary", 30),
                    centerString("Doctor VisitPayment", 30));
            for (Doctor doctor:section.getDoctors()){
                formatter.format(format, centerString(doctor.getName(), 30), centerString(doctor.getType(), 30),
                        centerString(String.valueOf(doctor.getSalary()), 30), centerString(String.valueOf(doctor.getVisitPayment()), 30));
            }
        }
        sectionFolder= new File(clinicFolder.getAbsolutePath(),section.getName());
        if (!sectionFolder.mkdir() && !sectionFolder.exists()) return;
        File doctorsList=new File(Path.of(sectionFolder.getAbsolutePath(),"Doctors List.txt").toString());
        try{
            FileWriter writer=new FileWriter(doctorsList,true);
            writer.write(formatter.toString());
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"ERROR!");
        }
    }
    public static void setSectionPatients(Section section){
        Formatter formatter = new Formatter();
        String format = "|%-30s|%-30s|%-30s|%n";{
            formatter.format(format, centerString("Patient Name", 30),
                    centerString("Patient Sickness Type", 30), centerString("Patient Wallet Inventory", 30));
            for (Patient patient:section.getPatients()){
                formatter.format(format, centerString(patient.getName(), 30),
                        centerString(patient.getSicknessType(), 30),
                        centerString(patient.getWalletInventory(), 30));
            }
        }
        sectionFolder= new File(clinicFolder.getAbsolutePath(),section.getName());
        if (!sectionFolder.mkdir() && !sectionFolder.exists()) return;
        File patientsList=new File(Path.of(sectionFolder.getAbsolutePath(),"Patients List.txt").toString());
        try{
            FileWriter writer=new FileWriter(patientsList,true);
            writer.write(formatter.toString());
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"ERROR!");
        }
    }
    public static void setSectionsReferralsHistory(Section section){
        Formatter formatter = new Formatter();
        String format = "|%-30s|%-30s|%-30s|%-30s|%n";{
            formatter.format(format, centerString("Patient Name", 30),
                    centerString("Sickness Type", 30), centerString("Doctor Name", 30),
                    centerString("Visit Payment", 30));
            for (Patient patient:section.getReceptionist().getDoctorReferralsHistory().keySet()){
                formatter.format(format, centerString(patient.getName(), 30), centerString(patient.getSicknessType(), 30),
                        centerString(section.getReceptionist().getDoctorReferralsHistory().get(patient).getName(), 30),
                        centerString(String.valueOf(section.getReceptionist().getDoctorReferralsHistory().get(patient).getVisitPayment()),30));
            }
        }
        sectionFolder= new File(clinicFolder.getAbsolutePath(),section.getName());
        if (!sectionFolder.mkdir() && !sectionFolder.exists()) return;
        File referralsHistoryList=new File(Path.of(sectionFolder.getAbsolutePath(),"Referrals History List.txt").toString());
        try{
            FileWriter writer=new FileWriter(referralsHistoryList,true);
            writer.write(formatter.toString());
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"ERROR!");
        }
    }
    public static String centerString(String str,int count){
        return " ".repeat(Math.max(0, (count-str.length())/2)) + str;
    }
}
