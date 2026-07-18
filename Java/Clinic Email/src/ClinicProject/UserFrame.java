package ClinicProject;

import javax.swing.*;
import java.awt.*;

public class UserFrame {
    JMenuBar menuBar=new JMenuBar();

    UserFrame(Clinic clinic,User user) {
        setUserFrame(clinic,user);
    }

    public JFrame definingUserFrame(Clinic clinic,User user){
        JFrame frame=new JFrame();
        if (user instanceof Manager)
            frame= managerMainFrame((Manager)user);
        else {
            if (user instanceof Doctor)
                frame= doctorMainFrame((Doctor) user);
            else {
                if (user instanceof Receptionist)
                    frame= receptionistMainFrame(clinic,(Receptionist) user);
                else if (user instanceof Patient)
                    frame= patientMainFrame(clinic,(Patient) user);
            }
        }
        return frame;
    }
    public void setUserFrame(Clinic clinic,User user) {
        JFrame userFrame=definingUserFrame(clinic,user);

        JMenu menu = new JMenu("Menu");{
            JMenuItem clinicInfo=new JMenuItem("Clinic Info");{
                clinicInfo.addActionListener(e -> {
                    userFrame.setVisible(false);
                    clinicInfoFrame(clinic,user);
                });
            }
            JMenuItem userInfo = new JMenuItem("Profile");{
                userInfo.addActionListener(e -> {
                    userFrame.setVisible(false);
                    userInfoFrame(clinic,user);
                });
            }
            JMenu email = new JMenu("Email");{
                JMenuItem newEmail=new JMenuItem("New Mail");{
                    newEmail.addActionListener(e -> {
                        userFrame.setVisible(false);
                        email(clinic,user);
                    });
                }
                email.add(newEmail);
            }
            menu.add(clinicInfo);
            menu.add(userInfo);
            menu.add(email);
        }

        JButton backButton =Main.backButtonFormat();{
            backButton.addActionListener(e -> {
                userFrame.setVisible(false);
                Main.setMainFrame();
            });
        }

        menuBar.add(menu);
        {
            userFrame.setContentPane(new JLabel(new ImageIcon("background.jpg")));
            userFrame.setJMenuBar(menuBar); userFrame.add(backButton);
            userFrame.setLayout(null);userFrame.setVisible(true);
        } //frame
    }
    public void clinicInfoFrame(Clinic clinic,User user){
        JFrame clinicInfoFrame = Main.frameTemplate("Medical Zone - Clinic Info");

        JLabel clinicInfoLabel=new JLabel();{
            clinicInfoLabel.setText("<html>Clinic Name:<br>"+clinic.getName()+
                    "<html> <br> <br>Clinic Type:<br>"+clinic.getType()+
                    "<html> <br> <br>Manager Name:<br>"+clinic.getManager().getName());
            clinicInfoLabel.setBounds(150,150,300,250);
            clinicInfoLabel.setHorizontalTextPosition(JLabel.CENTER);
            clinicInfoLabel.setVerticalTextPosition(JLabel.CENTER);
            clinicInfoLabel.setHorizontalAlignment(JLabel.CENTER);
            clinicInfoLabel.setVerticalAlignment(JLabel.CENTER);
            clinicInfoLabel.setFont(new Font("Consoles", Font.PLAIN, 20));
            clinicInfoLabel.setBackground(Color.LIGHT_GRAY);
        }

        JButton backButton =Main.backButtonFormat();{
            backButton.addActionListener(e -> {
                clinicInfoFrame.setVisible(false);
                new UserFrame(clinic,user);
            });
        }

        {
            clinicInfoFrame.add(clinicInfoLabel);clinicInfoFrame.add(backButton);
            clinicInfoFrame.setLayout(null);clinicInfoFrame.setVisible(true);
        } //frame setting
    }
    public JFrame managerMainFrame(Manager manager){
        JFrame frame=Main.frameTemplate("Medical Zone - Managers page");

        JMenu clinicMenu=new JMenu("Clinic");{
            JMenu sections=new JMenu("Sections");{
                JMenuItem display = new JMenuItem("Display Sections");{
                    display.addActionListener(e -> {
                        frame.setVisible(false);
                        manager.displaySections(manager);
                    });
                }
                JMenuItem addSection = new JMenuItem("Add Section");{
                    addSection.addActionListener(e -> {
                        frame.setVisible(false);
                        manager.addSection(manager);
                    });
                }
                JMenuItem deleteSection = new JMenuItem("Delete Section");{
                    deleteSection.addActionListener(e -> {
                        frame.setVisible(false);
                        manager.deleteSection(manager);
                    });
                }
                sections.add(display);
                sections.add(addSection);
                sections.add(deleteSection);
            }
            JMenu staffList=new JMenu("Staff list");{
                JMenuItem displayStaffList=new JMenuItem("Display Staff List");{
                    displayStaffList.addActionListener(e -> {
                        frame.setVisible(false);
                        manager.staffList(manager);
                    });
                }
                JMenuItem paySalaries=new JMenuItem("Pay Salaries");{
                    paySalaries.addActionListener(e -> {
                        frame.setVisible(false);
                        manager.paySalary(manager);
                    });
                }
                JMenuItem hireOrFireEmployee=new JMenuItem("Hire/Fire Employee");{
                    hireOrFireEmployee.addActionListener(e -> {
                        frame.setVisible(false);
                        manager.hireOrFireEmployee(manager);
                    });
                }
                staffList.add(displayStaffList); staffList.add(paySalaries);
                staffList.add(hireOrFireEmployee);
            }
            JMenu findEmployee=new JMenu("Find Employee");{
                JMenuItem findDoctorItem=new JMenuItem("Find Doctor");{
                    findDoctorItem.addActionListener(e -> {
                        frame.setVisible(false);
                        manager.findDoctor(manager);
                    });
                }
                JMenuItem findRecepItem = new JMenuItem("Find Receptionist");{
                    findRecepItem.addActionListener(e -> {
                        frame.setVisible(false);
                        manager.findReceptionist(manager);
                    });
                }
                findEmployee.add(findDoctorItem); findEmployee.add(findRecepItem);
            }
            clinicMenu.add(sections);
            clinicMenu.add(staffList);
        }

        menuBar.add(clinicMenu);
        return frame;
    }
    public JFrame doctorMainFrame(Doctor doctor){
        JFrame frame=Main.frameTemplate("Medical Zone - Doctors page");

        JMenu appointmentsMenu=new JMenu("Appointments");{
            JMenuItem visitPatient=new JMenuItem("Visit Patient");{
               visitPatient.addActionListener(e -> doctor.visitPatient(doctor));
            }
            appointmentsMenu.add(visitPatient);
        }

        menuBar.add(appointmentsMenu);
        return frame;
    }
    public JFrame receptionistMainFrame(Clinic clinic,Receptionist receptionist){
        JFrame frame=Main.frameTemplate("Medical Zone - Receptionist page");

        JMenu sectionDoctors=new JMenu("Section Doctors");{
            JMenuItem chooseDoctor=new JMenuItem("Choose Doctor");{
                chooseDoctor.addActionListener(e -> {
                    frame.setVisible(false);
                    receptionist.chooseDoctor(clinic,receptionist);
                });
            }
            JMenuItem displayReferralHistory=new JMenuItem("Section Referral History");{
                displayReferralHistory.addActionListener(e -> {
                    frame.setVisible(false);
                    receptionist.sectionReferralHistory(clinic,receptionist);
                });
            }
            sectionDoctors.add(chooseDoctor); sectionDoctors.add(displayReferralHistory);
        }

        menuBar.add(sectionDoctors);
        return frame;
    }
    public JFrame patientMainFrame(Clinic clinic,Patient patient){
        JFrame frame=Main.frameTemplate("Medical Zone - Patients page");

        JMenu appointments=new JMenu("Visit Info");{
            JMenuItem payVisitPayment=new JMenuItem("Pay Visit Payment");{
                payVisitPayment.addActionListener(e -> patient.payVisitPayment(clinic,patient));
            }
            appointments.add(payVisitPayment);
        }

        menuBar.add(appointments);
        return frame;
    }
    public void userInfoFrame(Clinic clinic,User user) {
        JFrame userInfoFrame = Main.frameTemplate("Medical Zone - Personal Info");

        JLabel personalInfo = new JLabel();{
            personalInfo.setText("<html>Name:<br>" + User.showUsersName(user.getTitle(), user.getName()) +
                    "<html> <br> <br>Wallet Inventory:<br>"+user.getWalletInventory()+
                    "<html> <br> <br>Email:<br>" + user.getEmail() +
                    "<html> <br> <br>Phone Number:<br>" + user.getPhoneNumber() +
                    "<html> <br> <br>Date Of Birth:<br>" + Main.dateOfBirth(user.getDay(), user.getMonth(), user.getYear()));
            personalInfo.setBounds(50, 50, 375, 350);
            personalInfo.setHorizontalTextPosition(JLabel.CENTER);
            personalInfo.setVerticalTextPosition(JLabel.CENTER);
        }
        JLabel usernameLabel=new JLabel();{
            usernameLabel.setText("@"+user.getUsername());
            usernameLabel.setBounds(380,250,125,100);
            usernameLabel.setHorizontalTextPosition(JLabel.CENTER);
            usernameLabel.setVerticalTextPosition(JLabel.CENTER);
            usernameLabel.setHorizontalAlignment(JLabel.CENTER);
            usernameLabel.setVerticalAlignment(JLabel.CENTER);
        }
        JLabel profile=new JLabel();{
            ImageIcon profileImageIcon = new ImageIcon("blank profile.png");
            profile.setIcon(profileImageIcon);
            profile.setBounds(375,50,150,350);
            profile.setVerticalAlignment(JLabel.CENTER);
            profile.setHorizontalAlignment(JLabel.CENTER);
        }

        JButton backButton = Main.backButtonFormat();{
            backButton.addActionListener(e -> {
                userInfoFrame.setVisible(false);
                new UserFrame(clinic,user);
            });
        }

        {
            userInfoFrame.add(profile); userInfoFrame.add(usernameLabel);
            userInfoFrame.add(personalInfo); userInfoFrame.add(backButton);
            userInfoFrame.setLayout(null);userInfoFrame.setVisible(true);
        } //frame setting
    }
    public void email(Clinic clinic,User user){
        JFrame emailFrame=Main.frameTemplate("Medical Zone - New Mail");

        JLabel toLabel=new JLabel(); {
            toLabel.setText("To");
            toLabel.setBounds(100,40,50,50);
        }
        JTextField toTextField=new JTextField();{
            toTextField.setBounds(170,53,300,20);
        }

        JLabel fromLabel=new JLabel();{
            fromLabel.setText("From");
            fromLabel.setBounds(100,90,50,50);
        }
        JTextField fromTextField=new JTextField(user.getEmail());{
            fromTextField.setBounds(170,103,300,20);
            fromTextField.setEditable(false);
        }

        JLabel subjectLabel=new JLabel();{
            subjectLabel.setText("Subject");
            subjectLabel.setBounds(100,140,50,50);
        }
        JTextField subjectTextField=new JTextField();{
            subjectTextField.setBounds(170,153,300,20);
        }

        JTextPane writeEmail=new JTextPane();{
            writeEmail.setBounds(100,200,400,250);
            writeEmail.setBackground(Color.LIGHT_GRAY);
            writeEmail.setForeground(Color.darkGray);
            writeEmail.setFont(new Font("Consoles", Font.PLAIN, 15));
            writeEmail.setEditable(true);
            writeEmail.setVisible(true);
        }

        JButton backButton = Main.backButtonFormat();{
            backButton.addActionListener(e -> {
                emailFrame.setVisible(false);
                new UserFrame(clinic,user);
            });
        }
        JButton sentButton=new JButton();{
            ImageIcon sentButtonIcon = new ImageIcon("sent.png");
            sentButton.setIcon(sentButtonIcon);
            sentButton.setBounds(500, 145, 50, 50);
            sentButton.setHorizontalAlignment(JButton.CENTER);
            sentButton.setVerticalAlignment(JButton.CENTER);
            sentButton.setBorder(BorderFactory.createEtchedBorder());
            sentButton.setFocusable(false);
            sentButton.setBackground(Color.WHITE);
            sentButton.setBorderPainted(false);
            sentButton.addActionListener(e -> {
                emailFrame.setVisible(false);
                new UserFrame(clinic,user);
            });
        }

        {
            emailFrame.add(backButton); emailFrame.add(writeEmail);
            emailFrame.add(toLabel); emailFrame.add(toTextField);
            emailFrame.add(fromLabel); emailFrame.add(fromTextField);
            emailFrame.add(subjectLabel); emailFrame.add(subjectTextField);
            emailFrame.add(sentButton);
            emailFrame.setLayout(null);emailFrame.setVisible(true);
        } //frame setting
    }
}
