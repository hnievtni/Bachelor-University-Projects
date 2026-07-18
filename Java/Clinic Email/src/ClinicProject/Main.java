package ClinicProject;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Objects;
import java.util.Vector;

public class Main{
    static File projectFolder;

    public static void main(String[] args) {
        projectFolder= new File("Project Data");
        if (!projectFolder.mkdir() && !projectFolder.exists())
            return;
        setMainFrame();
    }
    public static void setMainFrame(){
        JFrame mainFrame=frameTemplate("Medical Zone");

        JLabel clinicLogoLabel = new JLabel();{
            ImageIcon ImageIcon = new ImageIcon("logo label.png");
            clinicLogoLabel.setHorizontalAlignment(JLabel.CENTER);
            clinicLogoLabel.setVerticalAlignment(JLabel.CENTER);
            clinicLogoLabel.setBounds(0, 100, 600, 130);
            clinicLogoLabel.setIcon(ImageIcon);
        }
        JLabel signingFormLabel=new JLabel();{
            signingFormLabel.setText("Please select one of this signing forms.");
            signingFormLabel.setBounds(150,250,300,50);
            signingFormLabel.setHorizontalTextPosition(JLabel.CENTER);
            signingFormLabel.setVerticalTextPosition(JLabel.CENTER);
            signingFormLabel.setFont(new Font("Consoles",Font.PLAIN,17));
        }

        JButton signUpButton=new JButton("Sign Up");{
            signUpButton.setBounds(235, 300, 115, 30);
            signUpButton.setFocusable(false);
            signUpButton.setBackground(Color.lightGray);
            signUpButton.setBorderPainted(false);
            signUpButton.setFont(new Font("Consoles", Font.PLAIN, 17));
            signUpButton.addActionListener(e -> {
                mainFrame.setVisible(false);
                roleFrameMethod("signUp");
            });
        }
        JButton signInButton=new JButton("Sign In");{
            signInButton.setBounds(235,340,115,30);
            signInButton.setFocusable(false);
            signInButton.setBackground(Color.lightGray);
            signInButton.setBorderPainted(false);
            signInButton.setFont(new Font("Consoles",Font.PLAIN,17));
            signInButton.addActionListener(e ->{
                mainFrame.setVisible(false);
                roleFrameMethod("signIn");
            });
        }

        {
            mainFrame.add(clinicLogoLabel);mainFrame.add(signUpButton);
            mainFrame.add(signInButton);mainFrame.add(signingFormLabel);
            mainFrame.setLayout(null);mainFrame.setVisible(true);
        } //frame setting
    }
    public static void signUp(User user){
        JFrame signUpFrame=frameTemplate("Medical Zone - Sign Up page");

        JLabel nameLabel=new JLabel();{
            nameLabel.setText("Full Name:");
            nameLabel.setBounds(50,50,100,50);
        }
        String[] title={"Mr.", "Mrs.", "Ms.", "Miss"};
        JComboBox<String> titleComboBox=new JComboBox<>(title);{
            titleComboBox.setBounds(50,100,50,18);
            titleComboBox.setBackground(Color.LIGHT_GRAY);
            titleComboBox.setSelectedIndex(-1);
            titleComboBox.addActionListener(e1 ->
                    user.setTitle(String.valueOf(Objects.requireNonNull(titleComboBox.getSelectedItem()))));
        }
        JTextField nameTextField = new JTextField();{
            nameTextField.setBounds(110, 100, 100, 20);
        }

        JLabel userLabel=new JLabel();{
            userLabel.setText("Username:");
            userLabel.setBounds(50,117,100,50);
        }
        JTextField userTextField = new JTextField();{
            userTextField.setBounds(50, 167, 100, 20);
        }

        JLabel passLabel=new JLabel();{
            passLabel.setText("Password:");
            passLabel.setBounds(50,183,100,50);
        }
        JTextField passTextField = new JTextField();{
            passTextField.setBounds(50, 233, 100, 20);
        }

        JLabel confirmPassLabel=new JLabel();{
            confirmPassLabel.setText("Confirm Password:");
            confirmPassLabel.setBounds(50,255,150,50);
        }
        JTextField confirmPassTextField = new JTextField();{
            String confirmPass=confirmPassTextField.getText();
            if (!Objects.equals(passTextField.getText(), confirmPass)) {
                JOptionPane.showMessageDialog(null, "your passwords don't match, please try again.");
            }
            confirmPassTextField.setBounds(50, 310, 100, 20);
        }

        var typeStr=new Vector<String>();
        JLabel typeLabel=new JLabel();{
            if (user instanceof Doctor)
                typeLabel.setText("Doctor Type:");
            else {
                if (user instanceof Patient)
                    typeLabel.setText("Patient Sickness Type:");
                else if (user instanceof Receptionist)
                    typeLabel.setText("Section Type:");
            }
            typeLabel.setBounds(50,327,150,50);
        }
        JComboBox<String> typeComboBox = new JComboBox<>(typeStr);{
            typeComboBox.setBounds(50, 375, 100, 20);
            typeComboBox.setBackground(Color.LIGHT_GRAY);
            typeComboBox.setSelectedIndex(-1);
            typeComboBox.setEnabled(false);
        }

        JLabel dateOfBirthLabel=new JLabel();{
            dateOfBirthLabel.setText("Date of Birth:");
            dateOfBirthLabel.setBounds(320,50,100,50);
        }
        JLabel yearLabel=new JLabel();{
            yearLabel.setText("Year:");
            yearLabel.setBounds(320,80,80,50);
        }
        JLabel monthLabel=new JLabel();{
            monthLabel.setText("Month:");
            monthLabel.setBounds(320,106,80,50);
        }
        JLabel dayLabel=new JLabel();{
            dayLabel.setText("Day:");
            dayLabel.setBounds(320,136,80,50);
        }

        String[] yearStr,monthStr,dayStr;{
            yearStr = new String[]{"2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012"
                    , "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000"
                    , "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988"
                    , "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980"};
            monthStr = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
            dayStr = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
                    , "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"
                    , "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        }
        JComboBox<String> yearComboBox=new JComboBox<>(yearStr);{
            yearComboBox.setBounds(370,97,60,18);
            yearComboBox.setBackground(Color.LIGHT_GRAY);
            yearComboBox.setSelectedIndex(-1);
            yearComboBox.addActionListener(e ->
                    user.setYear(String.valueOf(Objects.requireNonNull(yearComboBox.getSelectedItem()))));
        }
        JComboBox<String> monthComboBox=new JComboBox<>(monthStr);{
            monthComboBox.setBounds(370,124,60,18);
            monthComboBox.setBackground(Color.LIGHT_GRAY);
            monthComboBox.setSelectedIndex(-1);
        }
        JComboBox<String> dayComboBox=new JComboBox<>(dayStr);{
            dayComboBox.setBounds(370,154,50,18);
            dayComboBox.setBackground(Color.LIGHT_GRAY);
            dayComboBox.setSelectedIndex(-1);
            dayComboBox.setEnabled(false);
            monthComboBox.addActionListener(e ->{
                String chosenMonth=String.valueOf(monthComboBox.getSelectedItem());
                switch (chosenMonth){
                    case "Feb" -> {
                        dayComboBox.removeItemAt(30);
                        dayComboBox.removeItemAt(29);
                        dayComboBox.removeItemAt(28);
                    }
                    case "Apr", "Jun", "Sep", "Nov" -> dayComboBox.removeItemAt(30);
                }
                chosenMonth=String.valueOf(monthComboBox.getSelectedIndex()+1);
                user.setMonth(chosenMonth);
                dayComboBox.setEnabled(true);
                dayComboBox.addActionListener(e1 ->
                        user.setDay(String.valueOf(Objects.requireNonNull(dayComboBox.getSelectedItem()))));
            });
        }

        JLabel emailLabel=new JLabel();{
            emailLabel.setText("Email:");
            emailLabel.setBounds(320,170,100,50);
        }
        JTextField emailTextField = new JTextField();{
            emailTextField.setBounds(320, 220, 200, 20);
        }

        JLabel phoneNumberLabel=new JLabel();{
            phoneNumberLabel.setText("Phone Number:");
            phoneNumberLabel.setBounds(320,242,100,50);
        }
        JTextField phoneNumberTextField = new JTextField();{
            phoneNumberTextField.setBounds(320, 292, 150, 20);
        }

        String[] clinicsList=DataBase.getClinics().keySet().toArray(new String[0]);
        JLabel chooseClinicLabel=new JLabel();{
            chooseClinicLabel.setText("Clinic Name:");
            chooseClinicLabel.setBounds(320,322,100,50);
        }
        JComboBox<String> clinicComboBox= new JComboBox<>(clinicsList);{
            clinicComboBox.setBounds(400,340,80,18);
            clinicComboBox.setBackground(Color.LIGHT_GRAY);
            clinicComboBox.setSelectedIndex(-1);
            clinicComboBox.setEnabled(true);
            clinicComboBox.addActionListener(e ->{
                Clinic clinic=DataBase.getClinics().get(Objects.requireNonNull(clinicComboBox.getSelectedItem()).toString());
                for (Section section:clinic.getSections()) {
                    typeStr.add(section.getType());
                }
                typeComboBox.setEnabled(true);
            });
        }

        JButton backButton=backButtonFormat();{
            backButton.addActionListener(e -> {
                signUpFrame.setVisible(false);
                roleFrameMethod("signUp");
            });
        }
        JButton submit=new JButton("Submit");   {
            submit.setBounds(250, 410, 100, 30);
            submit.setHorizontalAlignment(JButton.CENTER);
            submit.setVerticalAlignment(JButton.CENTER);
            submit.setFocusable(false);
            submit.setBackground(Color.lightGray);
            submit.setBorderPainted(false);
            submit.setFont(new Font("Consoles", Font.PLAIN, 17));
            submit.addActionListener(e -> {
                user.setName(Objects.requireNonNull(nameTextField.getText()));
                user.setUsername(Objects.requireNonNull(userTextField.getText()));
                user.setPass(Objects.requireNonNull(passTextField.getText()));
                user.setEmail(Objects.requireNonNull(emailTextField.getText()));
                user.setPhoneNumber(Integer.parseInt(phoneNumberTextField.getText()));
                signUpFrame.setVisible(false);
                if (user instanceof Manager) {
                    user.setWalletInventory(1000);
                    registerClinic((Manager) user);
                }
                else {
                    Clinic clinic=DataBase.getClinics().get(String.valueOf(clinicComboBox.getSelectedItem()));
                    for (Section section :DataBase.getClinics().get(String.valueOf(clinicComboBox.getSelectedItem())).getSections()){
                        if (section.getType().equals(typeComboBox.getSelectedItem())) {
                            if (user instanceof Doctor){
                                user.setWalletInventory(0);
                                ((Doctor) user).setType(section.type);
                            }
                            if (user instanceof Patient){
                                user.setWalletInventory(700);
                                ((Patient) user).setSicknessType(section.getType());
                                section.setPatients((Patient) user);
                                DataBase.setSectionPatients(section);
                            }
                            if (user instanceof Receptionist){
                                user.setWalletInventory(0);
                                ((Receptionist) user).setSection(section);
                            }
                        }
                        clinic.setUsers(user);
                        new UserFrame(DataBase.getClinics().get(String.valueOf(clinicComboBox.getSelectedItem())),user);
                    }
                }
            });
        }

        {
            signUpFrame.add(titleComboBox);
            signUpFrame.add(nameLabel); signUpFrame.add(nameTextField);
            signUpFrame.add(userLabel); signUpFrame.add(userTextField);
            signUpFrame.add(passLabel); signUpFrame.add(passTextField);
            signUpFrame.add(confirmPassLabel); signUpFrame.add(confirmPassTextField);
            signUpFrame.add(dateOfBirthLabel);
            signUpFrame.add(yearLabel); signUpFrame.add(yearComboBox);
            signUpFrame.add(monthLabel); signUpFrame.add(monthComboBox);
            signUpFrame.add(dayLabel); signUpFrame.add(dayComboBox);
            signUpFrame.add(emailLabel); signUpFrame.add(emailTextField);
            signUpFrame.add(phoneNumberLabel); signUpFrame.add(phoneNumberTextField);
            signUpFrame.add(chooseClinicLabel); signUpFrame.add(clinicComboBox);
            signUpFrame.add(typeLabel); signUpFrame.add(typeComboBox);
            if (user instanceof Manager){
                signUpFrame.remove(chooseClinicLabel); signUpFrame.remove(clinicComboBox);
                signUpFrame.remove(typeLabel); signUpFrame.remove(typeComboBox);
            }
            signUpFrame.add(submit); signUpFrame.add(backButton);
            signUpFrame.setLayout(null);signUpFrame.setVisible(true);
        } //frame setting
    }
    public static void signIn(User user) {
        JFrame signInFrame=frameTemplate("Medical Zone - Sign In page");

        JLabel signInUserLabel=new JLabel();{
            signInUserLabel.setText("Username:");
            signInUserLabel.setBounds(255,75,100,50);
            signInUserLabel.setHorizontalTextPosition(JLabel.CENTER);
            signInUserLabel.setVerticalTextPosition(JLabel.CENTER);
        }
        JTextField userTextField = new JTextField();{
            userTextField.setBounds(200, 125, 200, 20);
        }

        JLabel signInPassLabel=new JLabel();{
            signInPassLabel.setText("Password:");
            signInPassLabel.setBounds(255,155,100,50);
            signInPassLabel.setHorizontalTextPosition(JLabel.CENTER);
            signInPassLabel.setVerticalTextPosition(JLabel.CENTER);
        }
        JTextField passTextField = new JTextField();{
            passTextField.setBounds(200, 205, 200, 20);
        }

        String[] clinicsList=DataBase.getClinics().keySet().toArray(new String[0]);
        JLabel chooseClinicLabel=new JLabel("Clinic Name:");{
            chooseClinicLabel.setBounds(250,235,100,50);
        }
        JComboBox<String> clinicComboBox= new JComboBox<>(clinicsList);{
            clinicComboBox.setBounds(250,285,100,18);
            clinicComboBox.setBackground(Color.LIGHT_GRAY);
            clinicComboBox.setSelectedIndex(-1);
            clinicComboBox.setEnabled(true);
        }

        JButton backButton = backButtonFormat();{
            backButton.addActionListener(e -> {
                signInFrame.setVisible(false);
                roleFrameMethod("signIn");
            });
        }
        JButton done=new JButton("Done");{
            done.setBounds(250, 345, 100, 30);
            done.setHorizontalAlignment(JButton.CENTER);
            done.setVerticalAlignment(JButton.CENTER);
            done.setFocusable(false);
            done.setBackground(Color.lightGray);
            done.setBorderPainted(false);
            done.setFont(new Font("Consoles", Font.PLAIN, 17));
            done.addActionListener(e -> {
                signInFrame.setVisible(false);
                Clinic clinic=DataBase.getClinics().get(Objects.requireNonNull(clinicComboBox.getSelectedItem()).toString());
                User user1=user.userValidity(clinic,userTextField.getText(),passTextField.getText());
                if (user1!=null)
                    new UserFrame(clinic,user1);
                else {
                    signInFrame.setVisible(true);
                    JOptionPane.showMessageDialog(null,
                            "Your username and password are not valid.Please try again.");
                }
            });
        }

        {
            signInFrame.add(chooseClinicLabel); signInFrame.add(clinicComboBox);
            signInFrame.add(backButton); signInFrame.add(done);
            signInFrame.add(signInUserLabel); signInFrame.add(userTextField);
            signInFrame.add(signInPassLabel); signInFrame.add(passTextField);
            signInFrame.setLayout(null);signInFrame.setVisible(true);
        } //frame setting
    }
    public static void roleFrameMethod(String signingForm){
        JFrame roleFrame=frameTemplate("Medical Zone - Specify Position");

        JLabel roleLabel=new JLabel("Please select your position in the clinic.");{
            roleLabel.setBounds(150,150,300,40);
            roleLabel.setVerticalAlignment(JLabel.CENTER);
            roleLabel.setHorizontalAlignment(JLabel.CENTER);
            roleLabel.setVerticalTextPosition(JLabel.CENTER);
            roleLabel.setVerticalTextPosition(JLabel.CENTER);
            roleLabel.setBackground(Color.WHITE);
            roleLabel.setOpaque(true);
            roleLabel.setFont(new Font("Consoles",Font.PLAIN,17));
        }

        JButton managerButton=new JButton("Manager");{
            managerButton.setBounds(100, 250, 150, 40);
            managerButton.setHorizontalAlignment(JButton.CENTER);
            managerButton.setVerticalAlignment(JButton.CENTER);
            managerButton.setFocusable(false);
            managerButton.setBackground(Color.WHITE);
            managerButton.setBorderPainted(false);
            managerButton.setFont(new Font("Consoles", Font.PLAIN, 17));
            managerButton.addActionListener(e -> {
                User manager=new Manager();
                roleFrame.setVisible(false);
                if (signingForm.equals("signUp")) signUp(manager);
                else if (signingForm.equals("signIn")) signIn(manager);
            });
        }
        JButton doctorButton=new JButton("Doctor");{
            doctorButton.setBounds(350, 250, 150, 40);
            doctorButton.setHorizontalAlignment(JButton.CENTER);
            doctorButton.setVerticalAlignment(JButton.CENTER);
            doctorButton.setFocusable(false);
            doctorButton.setBackground(Color.WHITE);
            doctorButton.setBorderPainted(false);
            doctorButton.setFont(new Font("Consoles", Font.PLAIN, 17));
            doctorButton.addActionListener(e -> {
                User doctor=new Doctor();
                roleFrame.setVisible(false);
                if (signingForm.equals("signUp")) signUp(doctor);
                else if (signingForm.equals("signIn")) signIn(doctor);
            });
        }
        JButton receptionistButton=new JButton("Receptionist");{
            receptionistButton.setBounds(100, 350, 150, 40);
            receptionistButton.setHorizontalAlignment(JButton.CENTER);
            receptionistButton.setVerticalAlignment(JButton.CENTER);
            receptionistButton.setFocusable(false);
            receptionistButton.setBackground(Color.WHITE);
            receptionistButton.setBorderPainted(false);
            receptionistButton.setFont(new Font("Consoles", Font.PLAIN, 17));
            receptionistButton.addActionListener(e -> {
                User receptionist=new Receptionist();
                roleFrame.setVisible(false);
                if (signingForm.equals("signUp")) signUp(receptionist);
                else if (signingForm.equals("signIn")) signIn(receptionist);
            });
        }
        JButton patientButton=new JButton("Patient");{
            patientButton.setBounds(350, 350, 150, 40);
            patientButton.setHorizontalAlignment(JButton.CENTER);
            patientButton.setVerticalAlignment(JButton.CENTER);
            patientButton.setFocusable(false);
            patientButton.setBackground(Color.WHITE);
            patientButton.setBorderPainted(false);
            patientButton.setFont(new Font("Consoles", Font.PLAIN, 17));
            patientButton.addActionListener(e -> {
                User patient=new Patient();
                roleFrame.setVisible(false);
                if (signingForm.equals("signUp")) signUp(patient);
                else if (signingForm.equals("signIn")) signIn(patient);
            });
        }

        JButton backButton = backButtonFormat();{
            backButton.addActionListener(e -> {
                roleFrame.setVisible(false);
                setMainFrame();
            });
        }

        {
            roleFrame.setContentPane(new JLabel(new ImageIcon("clinic background.jpg")));
            roleFrame.add(roleLabel); roleFrame.add(managerButton);
            roleFrame.add(doctorButton); roleFrame.add(receptionistButton);
            roleFrame.add(patientButton);roleFrame.add(backButton);
            roleFrame.setLayout(null);roleFrame.setVisible(true);
        } //frame setting
    }
    public static void registerClinic(Manager manager) {
        JFrame registerClinicFrame=frameTemplate("Medical Zone -  Register Clinic");

        JLabel clinicNameLabel=new JLabel();{
            clinicNameLabel.setText("Clinic Name:");
            clinicNameLabel.setBounds(50,50,150,50);
        }
        JTextField clinicNameTextField = new JTextField();{
            clinicNameTextField.setBounds(50, 100, 100, 20);
        }

        JLabel clinicTypeLabel=new JLabel();{
            clinicTypeLabel.setText("Clinic Type:");
            clinicTypeLabel.setBounds(50,130,100,50);
        }
        String[] clinicType={"Common", "round-the-clock Clinic"};
        JComboBox<String> clinicTypeComboBox=new JComboBox<>(clinicType);{
            clinicTypeComboBox.setBounds(50,180,150,20);
            clinicTypeComboBox.setBackground(Color.LIGHT_GRAY);
            clinicTypeComboBox.setSelectedIndex(-1);
        }

        JLabel sectionsList=new JLabel();{
            sectionsList.setText("Sections list:");
            sectionsList.setBounds(50,210,200,50);
        }
        JLabel sectionRecep=new JLabel();{
            sectionRecep.setText("Sections Receptionist name:");
            sectionRecep.setBounds(350,210,250,50);
        }

        JTextField emergencyRecepTextField = new JTextField();{
            emergencyRecepTextField.setBounds(350, 260, 150, 20);
        }
        JCheckBox emergencyCheckBox=new JCheckBox("Emergency Section");{
            emergencyCheckBox.setBounds(50, 260, 200, 20);
            emergencyCheckBox.setFocusable(false);
            emergencyCheckBox.setFont(new Font("Consoles", Font.PLAIN, 15));
            emergencyCheckBox.setBackground(Color.lightGray);
        }

        JTextField primaryRecepTextField = new JTextField();{
            primaryRecepTextField.setBounds(350, 290, 150, 20);
        }
        JCheckBox primaryCheckBox=new JCheckBox("Primary Section");{
            primaryCheckBox.setBounds(50,290,200,20);
            primaryCheckBox.setFocusable(false);
            primaryCheckBox.setFont(new Font("Consoles",Font.PLAIN,15));
            primaryCheckBox.setBackground(Color.lightGray);
        }

        JTextField homeCareRecepTextField = new JTextField();{
            homeCareRecepTextField.setBounds(350, 320, 150, 20);
        }
        JCheckBox homeCareCheckBox=new JCheckBox("Home Care Section");{
            homeCareCheckBox.setBounds(50, 320, 200, 20);
            homeCareCheckBox.setFocusable(false);
            homeCareCheckBox.setFont(new Font("Consoles", Font.PLAIN, 15));
            homeCareCheckBox.setBackground(Color.lightGray);
        }

        JTextField longTermRecepTextField = new JTextField();{
            longTermRecepTextField.setBounds(350, 350, 150, 20);
        }
        JCheckBox longTermCheckBox=new JCheckBox("Long-Term Section");{
            longTermCheckBox.setBounds(50, 350, 200, 20);
            longTermCheckBox.setFocusable(false);
            longTermCheckBox.setFont(new Font("Consoles", Font.PLAIN, 15));
            longTermCheckBox.setBackground(Color.lightGray);
        }

        JTextField diagnosticRecepTextField = new JTextField();{
            diagnosticRecepTextField.setBounds(350, 380, 150, 20);
        }
        JCheckBox diagnosticCheckBox=new JCheckBox("Diagnostic Section");{
            diagnosticCheckBox.setBounds(50, 380, 200, 20);
            diagnosticCheckBox.setFocusable(false);
            diagnosticCheckBox.setFont(new Font("Consoles", Font.PLAIN, 15));
            diagnosticCheckBox.setBackground(Color.lightGray);
        }

        JButton backButton = backButtonFormat();{
            backButton.addActionListener(e -> {
                registerClinicFrame.setVisible(false);
                roleFrameMethod("signUp");
            });
        }
        JButton submit=new JButton("Submit");{
            submit.setBounds(250, 420, 100, 30);
            submit.setHorizontalAlignment(JButton.CENTER);
            submit.setVerticalAlignment(JButton.CENTER);
            submit.setFocusable(false);
            submit.setBackground(Color.lightGray);
            submit.setBorderPainted(false);
            submit.setFont(new Font("Consoles", Font.PLAIN, 17));
            submit.addActionListener(e -> {
                Clinic clinic=new Clinic();
                clinic.setName(Objects.requireNonNull(clinicNameTextField.getText()));
                clinic.setType(Objects.requireNonNull(clinicTypeComboBox.getSelectedItem()).toString());
                if (diagnosticCheckBox.isSelected()) {
                    Receptionist receptionist=new Receptionist();
                    Section section=new Section();
                    section.setName("Diagnostic Section");
                    section.setType("Diagnostic");
                    receptionist.setName(diagnosticRecepTextField.getText());
                    receptionist.setSalary(500);
                    receptionist.setSection(section);
                    section.setReceptionist(receptionist);
                    clinic.setSections(section);
                }
                if (longTermCheckBox.isSelected()) {
                    Receptionist receptionist=new Receptionist();
                    Section section=new Section();
                    section.setName("Long-Term Section");
                    section.setType("Long-Term");
                    receptionist.setName(longTermRecepTextField.getText());
                    receptionist.setSalary(500);
                    receptionist.setSection(section);
                    section.setReceptionist(receptionist);
                    clinic.setSections(section);
                }
                if (homeCareCheckBox.isSelected()) {
                    Receptionist receptionist=new Receptionist();
                    Section section=new Section();
                    section.setName("Home Care Section");
                    section.setType("Home Care");
                    receptionist.setName(homeCareRecepTextField.getText());
                    receptionist.setSalary(500);
                    receptionist.setSection(section);
                    section.setReceptionist(receptionist);
                    clinic.setSections(section);
                }
                if (primaryCheckBox .isSelected()) {
                    Receptionist receptionist=new Receptionist();
                    Section section=new Section();
                    section.setName("Primary Section");
                    section.setType("Primary");
                    receptionist.setName(primaryRecepTextField.getText());
                    receptionist.setSalary(500);
                    receptionist.setSection(section);
                    section.setReceptionist(receptionist);
                    clinic.setSections(section);
                }
                if (emergencyCheckBox.isSelected()) {
                    Receptionist receptionist=new Receptionist();
                    Section section=new Section();
                    section.setName("Emergency Section");
                    section.setType("Emergency");
                    receptionist.setName(emergencyRecepTextField.getText());
                    receptionist.setSalary(500);
                    receptionist.setSection(section);
                    section.setReceptionist(receptionist);
                    clinic.setSections(section);
                }
                manager.setClinic(clinic);
                manager.setSalary(2000);
                clinic.setManager(manager);
                DataBase.setClinicSections(clinic);
                DataBase.setClinics(clinic.getName(),clinic);
                DataBase.setClinicInfo(clinic);
                clinic.setUsers(manager);
                registerClinicFrame.setVisible(false);
                new UserFrame(clinic,manager);
            });
        }

        {
            registerClinicFrame.add(clinicNameLabel); registerClinicFrame.add(clinicNameTextField);
            registerClinicFrame.add(clinicTypeLabel); registerClinicFrame.add(clinicTypeComboBox);
            registerClinicFrame.add(sectionsList); registerClinicFrame.add(sectionRecep);
            registerClinicFrame.add(emergencyCheckBox); registerClinicFrame.add(emergencyRecepTextField);
            registerClinicFrame.add(primaryCheckBox); registerClinicFrame.add(primaryRecepTextField);
            registerClinicFrame.add(homeCareCheckBox); registerClinicFrame.add(homeCareRecepTextField);
            registerClinicFrame.add(longTermCheckBox); registerClinicFrame.add(longTermRecepTextField);
            registerClinicFrame.add(diagnosticCheckBox); registerClinicFrame.add(diagnosticRecepTextField);
            registerClinicFrame.add(submit); registerClinicFrame.add(backButton);
            registerClinicFrame.setLayout(null);registerClinicFrame.setVisible(true);
        } //frame setting
    }
    public static JFrame frameTemplate(String frameTitle){
        JFrame frameTemplate=new JFrame();{
            frameTemplate.setTitle(frameTitle);
            ImageIcon framesIcon = new ImageIcon("clinic logo.jpg");
            frameTemplate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameTemplate.setBounds(375, 100, 600, 550);
            frameTemplate.getContentPane().setBackground(Color.WHITE);
            frameTemplate.setIconImage(framesIcon.getImage());
            frameTemplate.setResizable(false);
        }
        return frameTemplate;
    }
    public static JButton backButtonFormat(){
        JButton backButton = new JButton();{
            backButton.setBounds(550, 5, 18, 15);
            ImageIcon backButtonIcon = new ImageIcon("back button.png");
            backButton.setFocusable(false);
            backButton.setBorderPainted(false);
            backButton.setIcon(backButtonIcon);
            backButton.setBackground(Color.WHITE);
            backButton.setHorizontalAlignment(JButton.CENTER);
            backButton.setVerticalAlignment(JButton.CENTER);
            backButton.setBorder(BorderFactory.createEtchedBorder());
        }
        return backButton;
    }
    public static String dateOfBirth(String chosenDay, String chosenMonth, String chosenYear){
        return chosenYear+" / "+chosenMonth+" / "+chosenDay;
    }
}
