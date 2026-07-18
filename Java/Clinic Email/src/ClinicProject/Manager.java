package ClinicProject;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

class Manager extends User{
    int salary;

    private Clinic clinic;

    public void setSalary(int salary) {
        this.salary = salary;
    }
    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public int getSalary() {
        return salary;
    }
    public Clinic getClinic() {
        return clinic;
    }

    public void displaySections(Manager manager){
        JFrame sectionListFrame = Main.frameTemplate("Medical Zone - Sections List");

        String[] column={"Name", "Type","Receptionist Name", "Receptionist Salary"};
        String[][] sectionListStr=new String[manager.getClinic().getSections().size()][4];
        for (int i=0;i<manager.getClinic().getSections().size();){
            for (Section section:manager.getClinic().getSections()){
                sectionListStr[i][0] = section.getName();
                sectionListStr[i][1] = section.getType();
                sectionListStr[i][2] = section.getReceptionist().getName();
                sectionListStr[i][3] = String.valueOf(section.getReceptionist().getSalary());
                i++;
            }
        }

        JTable sectionList=new JTable(sectionListStr,column);{
            sectionList.setBounds(100,100,400,350);
            sectionList.setBackground(Color.LIGHT_GRAY);
        }
        JScrollPane scrollPane=new JScrollPane(sectionList);{
            scrollPane.setBackground(Color.LIGHT_GRAY);
        }

        JButton backButton =Main.backButtonFormat();{
            backButton.addActionListener(e -> {
                sectionListFrame.setVisible(false);
                new UserFrame(manager.getClinic(),manager);
            });
        }

        {
            sectionListFrame.add(scrollPane);sectionListFrame.add(backButton);
            sectionListFrame.setLayout(new GridBagLayout());sectionListFrame.setVisible(true);
        } //frame setting
    }
    public void addSection(Manager manager) {
        JFrame addSectionFrame = Main.frameTemplate("Medical Zone - Add Section");

        JLabel sectionsList=new JLabel();{
            sectionsList.setText("Sections list:");
            sectionsList.setBounds(50,150,200,50);
        }
        JLabel sectionRecep=new JLabel();{
            sectionRecep.setText("Sections Receptionist name:");
            sectionRecep.setBounds(350,150,250,50);
        }

        JTextField emergencyRecepTextField = new JTextField();{
            emergencyRecepTextField.setBounds(350, 200, 150, 20);
        }
        JCheckBox emergencyCheckBox=new JCheckBox("Emergency Section");{
            emergencyCheckBox.setBounds(50, 200, 200, 20);
            emergencyCheckBox.setFocusable(false);
            emergencyCheckBox.setFont(new Font("Consoles", Font.PLAIN, 15));
            emergencyCheckBox.setBackground(Color.lightGray);
        }

        JTextField primaryRecepTextField = new JTextField();{
            primaryRecepTextField.setBounds(350, 230, 150, 20);
        }
        JCheckBox primaryCheckBox=new JCheckBox("Primary Section");{
            primaryCheckBox.setBounds(50,230,200,20);
            primaryCheckBox.setFocusable(false);
            primaryCheckBox.setFont(new Font("Consoles",Font.PLAIN,15));
            primaryCheckBox.setBackground(Color.lightGray);
        }

        JTextField homeCareRecepTextField = new JTextField();{
            homeCareRecepTextField.setBounds(350, 260, 150, 20);
        }
        JCheckBox homeCareCheckBox=new JCheckBox("Home Care Section");{
            homeCareCheckBox.setBounds(50, 260, 200, 20);
            homeCareCheckBox.setFocusable(false);
            homeCareCheckBox.setFont(new Font("Consoles", Font.PLAIN, 15));
            homeCareCheckBox.setBackground(Color.lightGray);
        }

        JTextField longTermRecepTextField = new JTextField();{
            longTermRecepTextField.setBounds(350, 290, 150, 20);
        }
        JCheckBox longTermCheckBox=new JCheckBox("Long-Term Section");{
            longTermCheckBox.setBounds(50, 290, 200, 20);
            longTermCheckBox.setFocusable(false);
            longTermCheckBox.setFont(new Font("Consoles", Font.PLAIN, 15));
            longTermCheckBox.setBackground(Color.lightGray);
        }

        JTextField diagnosticRecepTextField = new JTextField();{
            diagnosticRecepTextField.setBounds(350, 320, 150, 20);
        }
        JCheckBox diagnosticCheckBox=new JCheckBox("Diagnostic Section");{
            diagnosticCheckBox.setBounds(50, 320, 200, 20);
            diagnosticCheckBox.setFocusable(false);
            diagnosticCheckBox.setFont(new Font("Consoles", Font.PLAIN, 15));
            diagnosticCheckBox.setBackground(Color.lightGray);
        }

        for (Section section:manager.getClinic().getSections()){
            switch (section.getName()){
                case "Emergency Section" -> {
                    emergencyCheckBox.setEnabled(false);
                    emergencyRecepTextField.setEditable(false);
                }
                case "Primary Section" -> {
                    primaryCheckBox.setEnabled(false);
                    primaryRecepTextField.setEditable(false);
                }
                case "Home Care Section" -> {
                    homeCareCheckBox.setEnabled(false);
                    homeCareRecepTextField.setEditable(false);
                }
                case "Long-Term Section" -> {
                    longTermCheckBox.setEnabled(false);
                    longTermRecepTextField.setEditable(false);
                }
                case "Diagnostic Section" -> {
                    diagnosticCheckBox.setEnabled(false);
                    diagnosticRecepTextField.setEditable(false);
                }
            }
        }

        JButton backButton =Main.backButtonFormat();{
            backButton.addActionListener(e -> {
                addSectionFrame.setVisible(false);
                new UserFrame(clinic,manager);
            });
        }
        JButton submit=new JButton("Submit");{
            submit.setBounds(250, 360, 100, 30);
            submit.setHorizontalAlignment(JButton.CENTER);
            submit.setVerticalAlignment(JButton.CENTER);
            submit.setFocusable(false);
            submit.setBackground(Color.lightGray);
            submit.setBorderPainted(false);
            submit.setFont(new Font("Consoles", Font.PLAIN, 17));
            submit.addActionListener(e -> {
                if (diagnosticCheckBox.isSelected()) {
                    Receptionist receptionist=new Receptionist();
                    Section section=new Section();
                    section.setName("Diagnostic Section");
                    section.setType("diagnostic");
                    receptionist.setName(diagnosticRecepTextField.getText());
                    receptionist.setSalary(500);
                    receptionist.setSection(section);
                    section.setReceptionist(receptionist);
                    manager.getClinic().setSections(section);
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
                    manager.getClinic().setSections(section);
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
                    manager.getClinic().setSections(section);
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
                    manager.getClinic().setSections(section);
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
                    manager.getClinic().setSections(section);
                }
                DataBase.setClinicSections(clinic);
                addSectionFrame.setVisible(false);
                new UserFrame(manager.getClinic(),manager);
            });
        }

        {
            addSectionFrame.add(sectionsList); addSectionFrame.add(sectionRecep);
            addSectionFrame.add(emergencyCheckBox);addSectionFrame.add(emergencyRecepTextField);
            addSectionFrame.add(primaryCheckBox);addSectionFrame.add(primaryRecepTextField);
            addSectionFrame.add(homeCareCheckBox);addSectionFrame.add(homeCareRecepTextField);
            addSectionFrame.add(longTermCheckBox);addSectionFrame.add(longTermRecepTextField);
            addSectionFrame.add(diagnosticCheckBox);addSectionFrame.add(diagnosticRecepTextField);
            addSectionFrame.add(backButton); addSectionFrame.add(submit);
            addSectionFrame.setLayout(null);addSectionFrame.setVisible(true);
        } //frame setting
    }
    public void deleteSection(Manager manager) {
        JFrame deleteSectionFrame = Main.frameTemplate("Medical Zone - Delete Section");

        JLabel sectionsList=new JLabel();{
            sectionsList.setText("Sections list:");
            sectionsList.setBounds(200,150,200,50);
            sectionsList.setHorizontalTextPosition(JLabel.CENTER);
        }

        JCheckBox emergencyCheckBox=new JCheckBox("Emergency Section");{
            emergencyCheckBox.setBounds(200, 200, 200, 20);
            emergencyCheckBox.setFocusable(false);
            emergencyCheckBox.setFont(new Font("Consoles", Font.PLAIN, 15));
            emergencyCheckBox.setBackground(Color.lightGray);
            emergencyCheckBox.setEnabled(false);
        }
        JCheckBox primaryCheckBox=new JCheckBox("Primary Section");{
            primaryCheckBox.setBounds(200,230,200,20);
            primaryCheckBox.setFocusable(false);
            primaryCheckBox.setFont(new Font("Consoles",Font.PLAIN,15));
            primaryCheckBox.setBackground(Color.lightGray);
            primaryCheckBox.setEnabled(false);
        }
        JCheckBox homeCareCheckBox=new JCheckBox("Home Care Section");{
            homeCareCheckBox.setBounds(200, 260, 200, 20);
            homeCareCheckBox.setFocusable(false);
            homeCareCheckBox.setFont(new Font("Consoles", Font.PLAIN, 15));
            homeCareCheckBox.setBackground(Color.lightGray);
            homeCareCheckBox.setEnabled(false);
        }
        JCheckBox longTermCheckBox=new JCheckBox("Long-Term Section");{
            longTermCheckBox.setBounds(200, 290, 200, 20);
            longTermCheckBox.setFocusable(false);
            longTermCheckBox.setFont(new Font("Consoles", Font.PLAIN, 15));
            longTermCheckBox.setBackground(Color.lightGray);
            longTermCheckBox.setEnabled(false);
        }
        JCheckBox diagnosticCheckBox=new JCheckBox("Diagnostic Section");{
            diagnosticCheckBox.setBounds(200, 320, 200, 20);
            diagnosticCheckBox.setFocusable(false);
            diagnosticCheckBox.setFont(new Font("Consoles", Font.PLAIN, 15));
            diagnosticCheckBox.setBackground(Color.lightGray);
            diagnosticCheckBox.setEnabled(false);
        }

        for (Section section:manager.getClinic().getSections()){
            switch (section.getName()){
                case "Emergency Section" -> emergencyCheckBox.setEnabled(true);
                case "Primary Section" -> primaryCheckBox.setEnabled(true);
                case "Home Care Section" -> homeCareCheckBox.setEnabled(true);
                case "Long-Term Section" -> longTermCheckBox.setEnabled(true);
                case "Diagnostic Section" -> diagnosticCheckBox.setEnabled(true);
            }
        }

        JButton backButton =Main.backButtonFormat();{
            backButton.addActionListener(e -> {
                deleteSectionFrame.setVisible(false);
                new UserFrame(clinic,manager);
            });
        }
        JButton submit=new JButton("Submit");{
            submit.setBounds(250, 360, 100, 30);
            submit.setHorizontalAlignment(JButton.CENTER);
            submit.setVerticalAlignment(JButton.CENTER);
            submit.setFocusable(false);
            submit.setBackground(Color.lightGray);
            submit.setBorderPainted(false);
            submit.setFont(new Font("Consoles", Font.PLAIN, 17));
            submit.addActionListener(e -> {
                if (diagnosticCheckBox.isSelected()) {
                    manager.getClinic().getSections().removeIf(section -> section.getName().equals("Diagnostic Section"));
                }
                if (longTermCheckBox.isSelected()) {
                    manager.getClinic().getSections().removeIf(section -> section.getName().equals("Long-Term Section"));
                }
                if (homeCareCheckBox.isSelected()) {
                    manager.getClinic().getSections().removeIf(section -> section.getName().equals("Home Care Section"));
                }
                if (primaryCheckBox .isSelected()) {
                    manager.getClinic().getSections().removeIf(section -> section.getName().equals("Primary Section"));
                }
                if (emergencyCheckBox.isSelected()) {
                    manager.getClinic().getSections().removeIf(section -> section.getName().equals("Emergency Section"));
                }
                deleteSectionFrame.setVisible(false);
                new UserFrame(manager.getClinic(),manager);
            });
        }

        {
            deleteSectionFrame.add(sectionsList);deleteSectionFrame.add(emergencyCheckBox);
            deleteSectionFrame.add(primaryCheckBox);deleteSectionFrame.add(homeCareCheckBox);
            deleteSectionFrame.add(longTermCheckBox);deleteSectionFrame.add(diagnosticCheckBox);
            deleteSectionFrame.add(backButton); deleteSectionFrame.add(submit);
            deleteSectionFrame.setLayout(null);deleteSectionFrame.setVisible(true);
        } //frame setting
    }
    public void staffList(Manager manager){
        JFrame staffListFrame = Main.frameTemplate("Medical Zone - Staff List");
        int employeeNumber=0;
        for (Section section:manager.getClinic().getSections()) {
            employeeNumber += section.getDoctors().size() + 1;
        }
        String[] column={"Name", "Position","Salary", "Section Name","Section Type"};
        String[][] staffListStr=new String[employeeNumber][5];
        for  (int i = 0; i < manager.getClinic().getSections().size(); i++) {
            for (Section section : manager.getClinic().getSections()) {
                if (section.getReceptionist()!=null) {
                    staffListStr[i][0] = section.getReceptionist().getName();
                    staffListStr[i][1] = "Receptionist";
                    staffListStr[i][2] = String.valueOf(section.getReceptionist().getSalary());
                    staffListStr[i][3] = section.getName();
                    staffListStr[i][4] = section.getType();
                    i++;
                }
                for (int j = 0; j < section.getDoctors().size();) {
                    for (Doctor doctor : section.getDoctors()) {
                        staffListStr[i][0] = doctor.getName();
                        staffListStr[i][1] = "Doctor";
                        staffListStr[i][2] = String.valueOf(doctor.getSalary());
                        staffListStr[i][3] = section.getName();
                        staffListStr[i][4] = section.getType();
                        j++;
                        i++;
                    }
                }
            }
        }

        JTable sectionList=new JTable(staffListStr,column);{
            sectionList.setBounds(100,100,400,350);
            sectionList.setBackground(Color.LIGHT_GRAY);
        }
        JScrollPane scrollPane=new JScrollPane(sectionList);{
            scrollPane.setBackground(Color.LIGHT_GRAY);
        }

        JButton backButton =Main.backButtonFormat();{
            backButton.addActionListener(e -> {
                staffListFrame.setVisible(false);
                new UserFrame(manager.getClinic(),manager);
            });
        }
        {
            staffListFrame.add(scrollPane);
            staffListFrame.add(backButton);
            staffListFrame.setLayout(new GridBagLayout());staffListFrame.setVisible(true);
        } //frame setting
    }
    public void hireOrFireEmployee(Manager manager) {
        JFrame hireOrFireEmployeeFrame = Main.frameTemplate("Medical Zone - Hire/Fire Employee");

        JLabel employeeName=new JLabel("Employee Name:");{
            employeeName.setBounds(150,70,150,50);
        }
        JTextField employeeNameTextField=new JTextField();{
            employeeNameTextField.setBounds(300,85,150,20);
        }

        JLabel doctorType=new JLabel("Doctor Type:");{
            doctorType.setBounds(150,170,150,50);
        }
        var doctorTypeList=new Vector<String>();{
            for (Section section:manager.getClinic().getSections()) {
                doctorTypeList.add(section.getType());
            }
        }
        JComboBox<String> doctorTypeComboBox=new JComboBox<>(doctorTypeList);{
            doctorTypeComboBox.setBounds(300,185,120,20);
            doctorTypeComboBox.setBackground(Color.LIGHT_GRAY);
            doctorTypeComboBox.setSelectedIndex(-1);
            doctorTypeComboBox.setEnabled(false);
        }

        JLabel sectionName=new JLabel("Section Name:");{
            sectionName.setBounds(150,220,150,50);
        }
        var sectionsList=new Vector<String>();{
            for (Section section:manager.getClinic().getSections()) {
                sectionsList.add(section.getName());
            }
        }
        JComboBox<String> sectionNameComboBox=new JComboBox<>(sectionsList);{
            sectionNameComboBox.setBounds(300,235,170,20);
            sectionNameComboBox.setBackground(Color.LIGHT_GRAY);
            sectionNameComboBox.setSelectedIndex(-1);
        }

        JLabel employeeSalary=new JLabel("Employee Salary:");{
            employeeSalary.setBounds(150,270,150,50);
        }
        JTextField employeeSalaryTextField=new JTextField();{
            employeeSalaryTextField.setBounds(300,285,80,20);
        }

        JLabel doctorVisitPayment=new JLabel("Doctor VisitPayment");{
            doctorVisitPayment.setBounds(150,320,150,50);
        }
        JTextField doctorVisitPaymentTextField=new JTextField();{
            doctorVisitPaymentTextField.setBounds(300,335,180,20);
            doctorVisitPaymentTextField.setEnabled(false);
        }

        JLabel employeePosition=new JLabel("Employee Position:");{
            employeePosition.setBounds(150,120,150,50);
        }
        String[] positionsList={"Doctor", "Receptionist"};
        JComboBox<String> employeePositionComboBox=new JComboBox<>(positionsList);{
            employeePositionComboBox.setBounds(300,135,80,20);
            employeePositionComboBox.setBackground(Color.LIGHT_GRAY);
            employeePositionComboBox.setSelectedIndex(-1);
            employeePositionComboBox.addActionListener(e -> {
                if (employeePositionComboBox.getSelectedIndex()==0) {
                    doctorTypeComboBox.setEnabled(true);
                    doctorVisitPaymentTextField.setEnabled(true);
                }
            });
        }

        JButton backButton =Main.backButtonFormat();{
            backButton.addActionListener(e -> {
                hireOrFireEmployeeFrame.setVisible(false);
                new UserFrame(clinic,manager);
            });
        }
        JButton hire=new JButton("Hire");{
            hire.setBounds(175, 400, 100, 30);
            hire.setHorizontalAlignment(JButton.CENTER);
            hire.setVerticalAlignment(JButton.CENTER);
            hire.setFocusable(false);
            hire.setBackground(Color.lightGray);
            hire.setBorderPainted(false);
            hire.setFont(new Font("Consoles", Font.PLAIN, 17));
            hire.addActionListener(e -> {
                if (employeePositionComboBox.getSelectedIndex()==0){
                    Doctor doctor=new Doctor();
                    doctor.setName(employeeNameTextField.getText());
                    doctor.setType(String.valueOf(doctorTypeComboBox.getSelectedItem()));
                    doctor.setSalary(Integer.parseInt(employeeSalaryTextField.getText()));
                    doctor.setVisitPayment(Integer.parseInt(doctorVisitPaymentTextField.getText()));
                    for (Section section:manager.getClinic().getSections()) {
                        if (section.getName().equals(String.valueOf(sectionNameComboBox.getSelectedItem()))){
                            section.setDoctors(doctor);
                            DataBase.setSectionDoctors(section);
                            break;
                        }
                    }
                }
                else if (employeePositionComboBox.getSelectedIndex()==1){
                    Receptionist receptionist=new Receptionist();
                    receptionist.setName(employeeNameTextField.getText());
                    receptionist.setSalary(Integer.parseInt(employeeSalaryTextField.getText()));
                    for (Section section:manager.getClinic().getSections()) {
                        if (section.getName().equals(String.valueOf(sectionNameComboBox.getSelectedItem()))){
                            receptionist.setSection(section);
                            break;
                        }
                    }
                }
                hireOrFireEmployeeFrame.setVisible(false);
                new UserFrame(manager.getClinic(),manager);
            });
        }
        JButton fire=new JButton("Fire");{
            fire.setBounds(325, 400, 100, 30);
            fire.setHorizontalAlignment(JButton.CENTER);
            fire.setVerticalAlignment(JButton.CENTER);
            fire.setFocusable(false);
            fire.setBackground(Color.lightGray);
            fire.setBorderPainted(false);
            fire.setFont(new Font("Consoles", Font.PLAIN, 17));
            fire.addActionListener(e -> {
                if (employeePositionComboBox.getSelectedIndex()==0){
                    for (Section section:manager.getClinic().getSections()){
                        section.getDoctors().removeIf(doctor -> doctor.getName().equals(employeeNameTextField.getText()) &&
                                doctor.getType().equals(doctorTypeComboBox.getSelectedItem()) &&
                                String.valueOf(doctor.getSalary()).equals(employeeSalaryTextField.getText()));
                    }
                }
                else if (employeePositionComboBox.getSelectedIndex()==1){
                    for (Section section:manager.getClinic().getSections()){
                        if (section.getReceptionist().getName().equals(employeeNameTextField.getText())&&
                                String.valueOf(section.getReceptionist().getSalary()).equals(employeeSalaryTextField.getText())) {
                           section.setReceptionist(null);
                            break;
                        }
                    }
                }
                hireOrFireEmployeeFrame.setVisible(false);
                new UserFrame(manager.getClinic(),manager);
            });
        }

        {
            hireOrFireEmployeeFrame.add(employeeName); hireOrFireEmployeeFrame.add(employeeNameTextField);
            hireOrFireEmployeeFrame.add(employeePosition); hireOrFireEmployeeFrame.add(employeePositionComboBox);
            hireOrFireEmployeeFrame.add(doctorType); hireOrFireEmployeeFrame.add(doctorTypeComboBox);
            hireOrFireEmployeeFrame.add(sectionName); hireOrFireEmployeeFrame.add(sectionNameComboBox);
            hireOrFireEmployeeFrame.add(employeeSalary); hireOrFireEmployeeFrame.add(employeeSalaryTextField);
            hireOrFireEmployeeFrame.add(doctorVisitPayment); hireOrFireEmployeeFrame.add(doctorVisitPaymentTextField);
            hireOrFireEmployeeFrame.add(backButton); hireOrFireEmployeeFrame.add(hire); hireOrFireEmployeeFrame.add(fire);
            hireOrFireEmployeeFrame.setLayout(null);hireOrFireEmployeeFrame.setVisible(true);
        } //frame setting
    }
    public void paySalary(Manager manager) {
        JFrame paySalaryFrame = Main.frameTemplate("Medical Zone - Pay Employees Salary");

        JLabel sectionName=new JLabel("Section Name:");{
            sectionName.setBounds(225,95,150,50);
        }
        var sectionsList=new Vector<String>();{
            for (Section section:clinic.getSections()) {
                sectionsList.add(section.getName());
            }
        }
        JComboBox<String> sectionNameComboBox=new JComboBox<>(sectionsList);{
            sectionNameComboBox.setBounds(215,145,170,20);
            sectionNameComboBox.setBackground(Color.LIGHT_GRAY);
            sectionNameComboBox.setSelectedIndex(-1);
        }

        var employeeList=new Vector<String>();
        JLabel employeeName=new JLabel("Employee Name:");{
            employeeName.setBounds(225,255,150,50);
        }
        JComboBox<String> employeeNameComboBox=new JComboBox<>(employeeList);{
            employeeNameComboBox.setBounds(150,305,300,20);
            employeeNameComboBox.setBackground(Color.LIGHT_GRAY);
            employeeNameComboBox.setSelectedIndex(-1);
            employeeNameComboBox.setEnabled(false);
        }

        JLabel employeePosition=new JLabel("Employee Position:");{
            employeePosition.setBounds(225,175,150,50);
        }
        JCheckBox doctorCheckBox=new JCheckBox("Doctor");{
            doctorCheckBox.setBounds(95, 225, 200, 20);
            doctorCheckBox.setFocusable(false);
            doctorCheckBox.setFont(new Font("Consoles", Font.PLAIN, 15));
            doctorCheckBox.setBackground(Color.lightGray);
            doctorCheckBox.addActionListener(e -> {
                for (Section section:clinic.getSections()) {
                    if (section.getName().equals(sectionNameComboBox.getSelectedItem())) {
                        for (Doctor doctor:section.getDoctors()){
                            employeeList.add(doctor.getName()+". "+doctor.getSalary());
                        }
                        break;
                    }
                }
                employeeNameComboBox.setEnabled(true);
            });
        }
        JCheckBox recepCheckBox=new JCheckBox("Receptionist");{
            recepCheckBox.setBounds(305, 225, 200, 20);
            recepCheckBox.setFocusable(false);
            recepCheckBox.setFont(new Font("Consoles", Font.PLAIN, 15));
            recepCheckBox.setBackground(Color.lightGray);
            recepCheckBox.addActionListener(e -> {
                for (Section section:clinic.getSections()) {
                    if (section.getName().equals(sectionNameComboBox.getSelectedItem())) {
                        employeeList.add(section.getReceptionist().getName()+". "+section.getReceptionist().getSalary());
                        break;
                    }
                }
                employeeNameComboBox.setEnabled(true);
            });
        }

        JButton backButton =Main.backButtonFormat();{
            backButton.addActionListener(e -> {
                paySalaryFrame.setVisible(false);
                new UserFrame(clinic,manager);
            });
        }
        JButton pay=new JButton("Pay");{
            pay.setBounds(250, 395, 100, 30);
            pay.setHorizontalAlignment(JButton.CENTER);
            pay.setVerticalAlignment(JButton.CENTER);
            pay.setFocusable(false);
            pay.setBackground(Color.lightGray);
            pay.setBorderPainted(false);
            pay.setFont(new Font("Consoles", Font.PLAIN, 17));
            pay.addActionListener(e -> {
                if (doctorCheckBox.isSelected()){
                    for (Section section:manager.getClinic().getSections()){
                        if (section.getName().equals(sectionNameComboBox.getSelectedItem())) {
                            for (Doctor doctor : section.getDoctors()) {
                                if (doctor.getName().equals(employeeNameComboBox.getSelectedItem())) {
                                    doctor.setWalletInventory(doctor.getSalary());
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
                if (recepCheckBox.isSelected()){
                    for (Section section:manager.getClinic().getSections()){
                        if (section.getName().equals(sectionNameComboBox.getSelectedItem())) {
                            section.getReceptionist().setWalletInventory(section.getReceptionist().getSalary());
                            break;
                        }
                    }
                }
                paySalaryFrame.setVisible(false);
                new UserFrame(manager.getClinic(),manager);
            });
        }

        {
            paySalaryFrame.add(employeeName);paySalaryFrame.add(employeeNameComboBox);
            paySalaryFrame.add(sectionName);paySalaryFrame.add(sectionNameComboBox);
            paySalaryFrame.add(employeePosition);paySalaryFrame.add(doctorCheckBox);
            paySalaryFrame.add(recepCheckBox);
            paySalaryFrame.add(backButton); paySalaryFrame.add(pay);
            paySalaryFrame.setLayout(null);paySalaryFrame.setVisible(true);
        } //frame setting
    }

    @Override
    public String getWalletInventory() {
        return walletInventory+"$";
    }
    @Override
    public void findDoctor(Manager manager) {
        JFrame findDoctorFrame= Main.frameTemplate("Medical Zone - Find Doctor");

        JLabel doctorName=new JLabel("Doctors Name");{
            doctorName.setBounds(250,215,100,50);
        }
        JTextField doctorsNameTextField=new JTextField();{
            doctorsNameTextField.setBounds(225,265,150,20);
        }

        JButton backButton =Main.backButtonFormat();{
            backButton.addActionListener(e -> {
                findDoctorFrame.setVisible(false);
                new UserFrame(manager.getClinic(),manager);
            });
        }
        JButton submit=new JButton("Submit");{
            submit.setBounds(250, 360, 100, 30);
            submit.setHorizontalAlignment(JButton.CENTER);
            submit.setVerticalAlignment(JButton.CENTER);
            submit.setFocusable(false);
            submit.setBackground(Color.lightGray);
            submit.setBorderPainted(false);
            submit.setFont(new Font("Consoles", Font.PLAIN, 17));
            submit.addActionListener(e -> {
                findDoctorFrame.setVisible(false);
                JFrame frame=Main.frameTemplate("Medical Zone - Doctor Info");
                JLabel personalInfo = new JLabel();{
                    personalInfo.setBounds(50, 50, 375, 350);
                    personalInfo.setHorizontalTextPosition(JLabel.CENTER);
                    personalInfo.setVerticalTextPosition(JLabel.CENTER);
                }
                for (var section:manager.getClinic().getSections()){
                    for (var doctor:section.getDoctors()){
                        if (doctor.getName().equals(doctorsNameTextField.getText())){
                            personalInfo.setText("<html>Doctor Name:<br>" + User.showUsersName(doctor.getTitle(), doctor.getName()) +
                                    "<html> <br> <br>Email:<br>" + doctor.getEmail() +
                                    "<html> <br> <br>Phone Number:<br>" + doctor.getPhoneNumber() +
                                    "<html> <br> <br>Date Of Birth:<br>" + Main.dateOfBirth(doctor.getDay(),
                                    doctor.getMonth(), doctor.getYear()));
                            break;
                        }
                    }
                }
                JButton backButton1 = Main.backButtonFormat();{
                    backButton1.addActionListener(e1 -> {
                        frame.setVisible(false);
                        new UserFrame(clinic,manager);
                    });
                }
                {
                    frame.add(personalInfo); frame.add(backButton1);
                    frame.setLayout(null);frame.setVisible(true);
                } //frame setting
            });
        }

        {
            findDoctorFrame.add(doctorName); findDoctorFrame.add(doctorsNameTextField);
            findDoctorFrame.add(submit);findDoctorFrame.add(backButton);
            findDoctorFrame.setLayout(null);findDoctorFrame.setVisible(true);
        } //frame setting
    }
    @Override
    public void findReceptionist(Manager manager) {
        JFrame findRecepFrame= Main.frameTemplate("Medical Zone - Find Receptionist");

        JLabel recepName=new JLabel("Receptionist Name");{
            recepName.setBounds(250,215,100,50);
        }
        JTextField recepNameTextField=new JTextField();{
            recepNameTextField.setBounds(225,265,150,20);
        }

        JButton backButton =Main.backButtonFormat();{
            backButton.addActionListener(e -> {
                findRecepFrame.setVisible(false);
                new UserFrame(manager.getClinic(),manager);
            });
        }
        JButton submit=new JButton("Submit");{
            submit.setBounds(250, 360, 100, 30);
            submit.setHorizontalAlignment(JButton.CENTER);
            submit.setVerticalAlignment(JButton.CENTER);
            submit.setFocusable(false);
            submit.setBackground(Color.lightGray);
            submit.setBorderPainted(false);
            submit.setFont(new Font("Consoles", Font.PLAIN, 17));
            submit.addActionListener(e -> {
                findRecepFrame.setVisible(false);
                JFrame frame=Main.frameTemplate("Medical Zone - Receptionist Info");
                JLabel personalInfo = new JLabel();{
                    personalInfo.setBounds(50, 50, 375, 350);
                    personalInfo.setHorizontalTextPosition(JLabel.CENTER);
                    personalInfo.setVerticalTextPosition(JLabel.CENTER);
                }
                for (var section:manager.getClinic().getSections()){
                    Receptionist recep=section.getReceptionist();
                    if (recep.getName().equals(recepNameTextField.getText())){
                        personalInfo.setText("<html> Receptionist Name:<br>" + User.showUsersName(recep.getTitle(), recep.getName()) +
                                "<html> <br> <br>Email:<br>" + recep.getEmail() +
                                "<html> <br> <br>Phone Number:<br>" + recep.getPhoneNumber() +
                                "<html> <br> <br>Date Of Birth:<br>" + Main.dateOfBirth(recep.getDay(),
                                recep.getMonth(), recep.getYear()));
                        break;
                    }
                }
                JButton backButton1 = Main.backButtonFormat();{
                    backButton1.addActionListener(e1 -> {
                        frame.setVisible(false);
                        new UserFrame(clinic,manager);
                    });
                }
                {
                    frame.add(personalInfo); frame.add(backButton1);
                    frame.setLayout(null);frame.setVisible(true);
                } //frame setting
            });
        }

        {
            findRecepFrame.add(recepName);findRecepFrame.add(recepNameTextField);
            findRecepFrame.add(submit);findRecepFrame.add(backButton);
            findRecepFrame.setLayout(null);findRecepFrame.setVisible(true);
        } //frame setting
    }
}