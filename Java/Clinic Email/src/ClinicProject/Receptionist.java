package ClinicProject;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Vector;

class Receptionist extends User{
    int salary;

    private Section section;

    HashMap<Patient,Doctor> doctorReferralsHistory=new HashMap<>();

    public void setSalary(int salary) {
        this.salary = salary;
    }
    public void setSection(Section section) {
        this.section = section;
    }
    public void setDoctorReferralsHistory(Patient patient,Doctor doctor) {
        this.doctorReferralsHistory.put(patient,doctor);
    }

    public int getSalary() {
        return salary;
    }
    public Section getSection() {
        return section;
    }
    public HashMap<Patient, Doctor> getDoctorReferralsHistory() {
        return doctorReferralsHistory;
    }

    public void chooseDoctor(Clinic clinic,Receptionist receptionist) {
        JFrame chooseDoctorFrame = Main.frameTemplate("Medical Zone - Choose Doctor");

        var doctorList=new Vector<String>();
        JLabel doctorName=new JLabel("Doctors List:");{
            doctorName.setBounds(250,260,100,50);
            doctorName.setHorizontalTextPosition(JLabel.CENTER);
            doctorName.setVerticalTextPosition(JLabel.CENTER);
        }
        JComboBox<String> doctorNameComboBox=new JComboBox<>(doctorList);{
            doctorNameComboBox.setBounds(225,310,150,20);
            doctorNameComboBox.setBackground(Color.LIGHT_GRAY);
            doctorNameComboBox.setSelectedIndex(-1);
            doctorNameComboBox.setEnabled(false);
        }

        var patientList=new Vector<String>();{
            for (Patient patient:receptionist.getSection().getPatients()){
                patientList.add(patient.getName()+". "+patient.getWalletInventory());
                for (Doctor doctor:receptionist.getSection().getDoctors()){
                    if (doctor.getType().equals(patient.getSicknessType())){
                        doctorList.add(doctor.getName()+". "+doctor.getVisitPayment());
                    }
                }
            }
        }
        JLabel patientName=new JLabel("Patients List:");{
            patientName.setBounds(225,180,150,50);
            patientName.setHorizontalTextPosition(JLabel.CENTER);
            patientName.setVerticalTextPosition(JLabel.CENTER);
        }
        JComboBox<String> patientNameComboBox=new JComboBox<>(patientList);{
            patientNameComboBox.setBounds(225,230,150,20);
            patientNameComboBox.setBackground(Color.LIGHT_GRAY);
            patientNameComboBox.setSelectedIndex(-1);
            patientNameComboBox.addActionListener(e -> doctorNameComboBox.setEnabled(true));
        }

        JButton backButton =Main.backButtonFormat();{
            backButton.addActionListener(e -> {
                chooseDoctorFrame.setVisible(false);
                new UserFrame(clinic,receptionist);
            });
        }
        JButton submit=new JButton("Submit");{
            submit.setBounds(250, 370, 100, 30);
            submit.setHorizontalAlignment(JButton.CENTER);
            submit.setVerticalAlignment(JButton.CENTER);
            submit.setFocusable(false);
            submit.setBackground(Color.lightGray);
            submit.setBorderPainted(false);
            submit.setFont(new Font("Consoles", Font.PLAIN, 17));
            submit.addActionListener(e -> {
                for (Patient patient:receptionist.getSection().getPatients()){
                    if (String.valueOf(patientNameComboBox.getSelectedItem()).contains(patient.getName())) {
                        for (Doctor doctor : receptionist.getSection().getDoctors()) {
                            if (String.valueOf(doctorNameComboBox.getSelectedItem()).contains(doctor.getName())) {
                                setDoctorReferralsHistory(patient,doctor);
                                DataBase.setSectionsReferralsHistory(receptionist.getSection());
                                break;
                            }
                        }
                        break;
                    }
                }
                chooseDoctorFrame.setVisible(false);
                new UserFrame(clinic,receptionist);
            });
        }
        {
            chooseDoctorFrame.add(doctorName);chooseDoctorFrame.add(doctorNameComboBox);
            chooseDoctorFrame.add(patientName);chooseDoctorFrame.add(patientNameComboBox);
            chooseDoctorFrame.add(backButton); chooseDoctorFrame.add(submit);
            chooseDoctorFrame.setLayout(null); chooseDoctorFrame.setVisible(true);
        }//frame setting
    }
    public void sectionReferralHistory(Clinic clinic,Receptionist receptionist){
        JFrame referralHistoryFrame = Main.frameTemplate("Medical Zone - Section Referrals History");

        String[] column={"Patient Name", "Sickness Type","Doctor Name", "Visit Payment"};
        String[][] referrals=new String[receptionist.getDoctorReferralsHistory().size()][4];
        for (int i=0;i<receptionist.getDoctorReferralsHistory().size();){
            for (Patient patient:receptionist.getDoctorReferralsHistory().keySet()){
                referrals[i][0]=patient.getName();
                referrals[i][1]=patient.getSicknessType();
                referrals[i][2]=receptionist.getDoctorReferralsHistory().get(patient).getName();
                referrals[i][3]= String.valueOf(receptionist.getDoctorReferralsHistory().get(patient).getVisitPayment());
                i++;
            }
        }

        JTable sectionList=new JTable(referrals,column);{
            sectionList.setBounds(100,100,400,350);
            sectionList.setBackground(Color.LIGHT_GRAY);
        }
        JScrollPane scrollPane=new JScrollPane(sectionList);{
            scrollPane.setBackground(Color.LIGHT_GRAY);
        }

        JButton backButton =Main.backButtonFormat();{
            backButton.addActionListener(e -> {
                referralHistoryFrame.setVisible(false);
                new UserFrame(clinic,receptionist);
            });
        }

        {
            referralHistoryFrame.add(scrollPane);referralHistoryFrame.add(backButton);
            referralHistoryFrame.setLayout(new GridBagLayout()); referralHistoryFrame.setVisible(true);
        }//frame setting
    }

    @Override
    public String getWalletInventory() {
        return walletInventory +"$";
    }
}
