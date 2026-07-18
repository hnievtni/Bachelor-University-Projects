import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;

public class View {
    User user;
    Controller controller;

    public void setUser(User user) {
        this.user = user;
    }

    public View(User user, Controller controller) {
        this.user=user;
        this.controller=controller;

        setUserFrame();
    }

    public void setUserFrame(){
        JFrame userFrame=Client.frameTemplate("Users page");

        JMenu menu=new JMenu("Menu");{
            JMenuItem personalInfoMenu = new JMenuItem("Personal Info");{
                personalInfoMenu.addActionListener(e -> {
                    userFrame.setVisible(false);
                    userInfoFrame();
                });
            }
            JMenuItem exit=new JMenuItem("Exit");{
                exit.addActionListener(e -> {
                    userFrame.setVisible(false);
                    Client.setMainFrame();
                });
            }
            menu.add(personalInfoMenu);
            menu.add(exit);
        }
        JMenu emailMenu = new JMenu("Email");{
            JMenuItem newMail=new JMenuItem("New Mail");{
                newMail.addActionListener(e -> {
                    userFrame.setVisible(false);
                    newMail();

                });
            }
            JMenuItem inbox=new JMenuItem("Inbox");{
                inbox.addActionListener(e -> {
                    userFrame.setVisible(false);
                    inbox();
                });
            }
            JMenuItem sentMails=new JMenuItem("Sent");{
                sentMails.addActionListener(e -> {
                    userFrame.setVisible(false);
                    sentMails();
                });
            }
            emailMenu.add(newMail);
            emailMenu.add(inbox);
            emailMenu.add(sentMails);
        }

        JButton backButton =Client.backButtonFormat();{
            backButton.addActionListener(e -> {
                userFrame.setVisible(false);
                Client.setMainFrame();
            });
        }

        JMenuBar menuBar=new JMenuBar();{
            menuBar.add(menu);
            menuBar.add(emailMenu);
        }

        {
            userFrame.setContentPane(new JLabel(new ImageIcon("main background.jpg")));
            userFrame.setJMenuBar(menuBar); userFrame.add(backButton);
            userFrame.setLayout(null);userFrame.setVisible(true);
        } //frame setting
    }
    public void userInfoFrame(){
        JFrame userInfoFrame = Client.frameTemplate("Personal Info");

        JLabel personalInfo = new JLabel();{
            personalInfo.setText("<html>Name:<br>" + user.getName() +
                    "<html> <br> <br>Username:<br>" + user.getUsername()+
                    "<html> <br> <br>Email:<br>" + user.getEmail());
            personalInfo.setBounds(50, 50, 375, 350);
            personalInfo.setHorizontalTextPosition(JLabel.CENTER);
            personalInfo.setVerticalTextPosition(JLabel.CENTER);
        }
        JLabel profile=new JLabel();{
            ImageIcon profileImageIcon = new ImageIcon(user.getProfilePath());
            profile.setIcon(profileImageIcon);
            profile.setBounds(375,50,150,350);
            profile.setVerticalAlignment(JLabel.CENTER);
            profile.setHorizontalAlignment(JLabel.CENTER);
        }

        JButton backButton = Client.backButtonFormat();{
            backButton.addActionListener(e -> {
                userInfoFrame.setVisible(false);
                setUserFrame();
            });
        }

        JMenu editInfoMenu=new JMenu("Edit Info");{
            JMenuItem changeProfile=new JMenuItem("Change Profile Picture");{
                changeProfile.addActionListener(e -> {
                    userInfoFrame.setVisible(false);
                    String temp=JOptionPane.showInputDialog("Write your new Profile picture path or name");
                    if (temp != null) {
                        user.setProfilePath(temp);
                        ImageIcon profileImageIcon = new ImageIcon(user.getProfilePath());
                        profile.setIcon(profileImageIcon);
                        setUser(user);
                        try {
                            controller.messageHandler(user,"Change Profile Picture",null,null,null);
                        } catch (IOException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
            JMenuItem editInfo=new JMenuItem("Edit personal Info");{
                editInfo.addActionListener(e -> {
                    userInfoFrame.setVisible(false);
                    editInfo();
                });
            }
            editInfoMenu.add(changeProfile);
            editInfoMenu.add(editInfo);
        }
        JMenuBar menuBar=new JMenuBar();{
            menuBar.add(editInfoMenu);
        }

        {
            userInfoFrame.setJMenuBar(menuBar);userInfoFrame.add(profile);
            userInfoFrame.add(personalInfo);userInfoFrame.add(backButton);
            userInfoFrame.setLayout(null);userInfoFrame.setVisible(true);
        } // frame setting
    }
    public void newMail(){
        JFrame emailFrame=Client.frameTemplate("New Mail");

        JLabel toLabel=new JLabel(); {
            toLabel.setText("To: ");
            toLabel.setBounds(100,40,50,50);
        }
        JTextField toTextField=new JTextField();{
            toTextField.setBounds(170,53,300,20);
        }

        JLabel fromLabel=new JLabel();{
            fromLabel.setText("From: ");
            fromLabel.setBounds(100,90,50,50);
        }
        JTextField fromTextField=new JTextField(user.getUsername());{
            fromTextField.setBounds(170,103,300,20);
            fromTextField.setEditable(false);
        }

        JLabel subjectLabel=new JLabel();{
            subjectLabel.setText("Subject: ");
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

        JButton backButton =Client.backButtonFormat();{
            backButton.addActionListener(e -> {
                emailFrame.setVisible(false);
                setUserFrame();
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
                Date date=new Date();
                Message message=new Message(toTextField.getText().split("/"),user.name,
                        subjectTextField.getText(), writeEmail.getText(),date.toString());
                user.setSentMessages(message);
                setUser(user);
                try {
                    controller.messageHandler(user,"New Mail",message,null,null);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        }

        {
            emailFrame.add(backButton); emailFrame.add(writeEmail);
            emailFrame.add(toLabel); emailFrame.add(toTextField);
            emailFrame.add(fromLabel); emailFrame.add(fromTextField);
            emailFrame.add(subjectLabel); emailFrame.add(subjectTextField);
            emailFrame.add(sentButton);
            emailFrame.setLayout(null);emailFrame.setVisible(true);
        }  //frame setting
    }
    public void inbox(){
        JFrame inboxFrame=Client.frameTemplate("Inbox");

        JLabel label=new JLabel("Inbox:");{
            label.setBounds(150,80,300,40);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            label.setBackground(Color.WHITE);
            label.setOpaque(true);
            label.setFont(new Font("Consoles",Font.PLAIN,17));
        }

        var mailInfo=new Vector<String>();{
            for (Message text : user.getReceivedMessages()) {
                mailInfo.add(text.getSender().concat("  -  ").concat(text.getSubject()));
            }
        }
        JComboBox<String> mailsListComboBox=new JComboBox<>(mailInfo);{
            mailsListComboBox.setBounds(100, 150, 350, 50);
            mailsListComboBox.setBackground(Color.LIGHT_GRAY);
            mailsListComboBox.setSelectedIndex(-1);
            mailsListComboBox.addActionListener(e -> {
                for (Message text : user.getReceivedMessages()) {
                    if (Objects.requireNonNull(mailsListComboBox.getSelectedItem()).toString().contains(text.getSender())){
                        inboxFrame.setVisible(false);
                        try {
                            controller.messageHandler(user,"mark as read",text,null,null);
                        } catch (IOException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
        }

        JButton backButton =Client.backButtonFormat();{
            backButton.addActionListener(e -> {
                inboxFrame.setVisible(false);
                setUserFrame();
            });
        }

        {
            inboxFrame.setContentPane(new JLabel(new ImageIcon("main background.jpg")));
            inboxFrame.add(backButton);inboxFrame.add(mailsListComboBox);
            inboxFrame.add(label);
            inboxFrame.setLayout(null);inboxFrame.setVisible(true);
        }  //frame setting
    }
    public void sentMails(){
        JFrame sentMailsFrame=Client.frameTemplate("Sent Mails");

        JLabel label=new JLabel("Sent Mails:");{
            label.setBounds(150,80,300,40);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            label.setBackground(Color.WHITE);
            label.setOpaque(true);
            label.setFont(new Font("Consoles",Font.PLAIN,17));
        }

        var mailInfo=new Vector<String>();{
            for (Message text : user.getSentMessages()) {
                mailInfo.add(Arrays.toString(text.getReceivers()).concat("  -  ").concat(text.getSubject()));
            }
        }
        JComboBox<String> mailsListComboBox=new JComboBox<>(mailInfo);{
            mailsListComboBox.setBounds(100, 150, 350, 50);
            mailsListComboBox.setBackground(Color.LIGHT_GRAY);
            mailsListComboBox.setSelectedIndex(-1);
            mailsListComboBox.addActionListener(e -> {
                for (Message text : user.getSentMessages()) {
                    if (Objects.requireNonNull(mailsListComboBox.getSelectedItem()).
                            toString().contains(Arrays.toString(text.getReceivers()))){
                        sentMailsFrame.setVisible(false);
                        displayMail(text,"sent");
                    }
                }
            });
        }

        JButton backButton =Client.backButtonFormat();{
            backButton.addActionListener(e -> {
                sentMailsFrame.setVisible(false);
                setUserFrame();
            });
        }

        {
            sentMailsFrame.setContentPane(new JLabel(new ImageIcon("main background.jpg")));
            sentMailsFrame.add(backButton);sentMailsFrame.add(mailsListComboBox);
            sentMailsFrame.add(label);
            sentMailsFrame.setLayout(null);sentMailsFrame.setVisible(true);
        }  //frame setting
    }
    public void editInfo(){
        JFrame editInfoFrame=Client.frameTemplate("Edit Info");

        JLabel nameLabel=new JLabel("Full Name:");{
            nameLabel.setBounds(250,35,100,50);
            nameLabel.setHorizontalAlignment(JLabel.CENTER);
            nameLabel.setVerticalAlignment(JLabel.CENTER);
        }
        JTextField nameTextField = new JTextField();{
            nameTextField.setBounds(200, 85, 200, 20);
        }

        JLabel userLabel=new JLabel("Username:");{
            userLabel.setBounds(250,105,100,50);
            userLabel.setHorizontalAlignment(JLabel.CENTER);
            userLabel.setVerticalAlignment(JLabel.CENTER);
        }
        JTextField userTextField = new JTextField();{
            userTextField.setBounds(200, 155, 200, 20);
        }

        JLabel emailLabel=new JLabel("Email:");{
            emailLabel.setBounds(250,185,100,50);
            emailLabel.setHorizontalAlignment(JLabel.CENTER);
            emailLabel.setVerticalAlignment(JLabel.CENTER);
        }
        JTextField emailTextField = new JTextField();{
            emailTextField.setBounds(200, 235, 200, 20);
        }

        JLabel passLabel=new JLabel("Password:");{
            passLabel.setBounds(250,265,100,50);
            passLabel.setHorizontalAlignment(JLabel.CENTER);
            passLabel.setVerticalAlignment(JLabel.CENTER);
        }
        JTextField passTextField = new JTextField();{
            passTextField.setBounds(200, 315, 200, 20);
        }

        JButton backButton=Client.backButtonFormat();{
            backButton.addActionListener(e -> {
                editInfoFrame.setVisible(false);
                setUserFrame();
            });
        }
        JButton submit=new JButton("Submit");   {
            submit.setBounds(250, 420, 100, 30);
            submit.setHorizontalAlignment(JButton.CENTER);
            submit.setVerticalAlignment(JButton.CENTER);
            submit.setFocusable(false);
            submit.setBackground(Color.lightGray);
            submit.setBorderPainted(false);
            submit.setFont(new Font("Consoles", Font.PLAIN, 17));
            submit.addActionListener(e -> {
                user.setName(Objects.requireNonNull(nameTextField.getText()));
                user.setUsername(Objects.requireNonNull(userTextField.getText()));
                user.setEmail(Objects.requireNonNull(emailTextField.getText()));
                user.setPass(Objects.requireNonNull(emailTextField.getText()));
                setUser(user);
                editInfoFrame.setVisible(false);
                try {
                    controller.messageHandler(user,"Edit Info",null,null,null);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        }

        {
            editInfoFrame.add(nameLabel); editInfoFrame.add(nameTextField);
            editInfoFrame.add(userLabel); editInfoFrame.add(userTextField);
            editInfoFrame.add(passLabel); editInfoFrame.add(passTextField);
            editInfoFrame.add(emailLabel); editInfoFrame.add(emailTextField);
            editInfoFrame.add(submit); editInfoFrame.add(backButton);
            editInfoFrame.setLayout(null);editInfoFrame.setVisible(true);
        }  //frame setting
    }
    public void displayMail(Message text,String frame){
        JFrame mailFrame=Client.frameTemplate("Sent Mails");

        JLabel rosLabel=new JLabel(); {
            if (frame.equals("sent"))
                rosLabel.setText("To: ");
            else if (frame.equals("inbox"))
                rosLabel.setText("From: ");
            rosLabel.setBounds(100,40,50,50);
        }
        JTextField rosTextField=new JTextField();{
            if (frame.equals("sent"))
                rosTextField.setText(Arrays.toString(text.getReceivers()));
            else if (frame.equals("inbox"))
                rosLabel.setText(text.getSender());
            rosTextField.setBounds(170,53,300,20);
            rosTextField.setEditable(false);
        }

        JLabel subjectLabel=new JLabel("Subject: "); {
            subjectLabel.setBounds(100,90,50,50);
        }
        JTextField subjectTextField=new JTextField(text.getSubject());{
            subjectTextField.setBounds(170,103,300,20);
            subjectTextField.setEditable(false);
        }

        JLabel status=new JLabel("Message Status: "); {
            status.setBounds(100,140,50,50);
        }
        JTextField statusTextField=new JTextField();{
            if (frame.equals("sent")) {
                if (text.getRead())
                    statusTextField.setText("it has been read.");
                else
                    statusTextField.setText("it has not been read yet.");
            }
            else if (frame.equals("inbox"))
                status.setText("-----");
            statusTextField.setBounds(170,153,300,20);
            statusTextField.setEditable(false);
        }

        JTextPane mailTxt=new JTextPane();{
            mailTxt.setBounds(100,200,400,250);
            mailTxt.setBackground(Color.LIGHT_GRAY);
            mailTxt.setForeground(Color.darkGray);
            mailTxt.setFont(new Font("Consoles", Font.PLAIN, 15));
            mailTxt.setEditable(false);
            mailTxt.setText(text.getEmail()+" / "+text.getDateAndTime());
            mailTxt.setVisible(true);
        }

        JButton backButton =Client.backButtonFormat();{
            backButton.addActionListener(e -> {
                mailFrame.setVisible(false);
                if (frame.equals("sent"))
                    sentMails();
                else if (frame.equals("inbox"))
                    inbox();
            });

        }

        JButton deleteButton =new JButton("Delete");{
            deleteButton.setBounds(500, 145, 50, 50);
            deleteButton.setFocusable(false);
            deleteButton.setBorderPainted(false);
            deleteButton.setBackground(Color.WHITE);
            deleteButton.setHorizontalAlignment(JButton.CENTER);
            deleteButton.setVerticalAlignment(JButton.CENTER);
            deleteButton.setBorder(BorderFactory.createEtchedBorder());
            deleteButton.addActionListener(e -> {
                mailFrame.setVisible(false);
                try {
                    controller.messageHandler(user,"delete",text,null,null);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            });

        }

        {
            mailFrame.add(backButton);mailFrame.add(mailTxt);
            mailFrame.add(rosLabel);mailFrame.add(rosTextField);
            mailFrame.add(subjectLabel);mailFrame.add(subjectTextField);
            mailFrame.add(status);mailFrame.add(statusTextField);
            mailFrame.add(deleteButton);
            mailFrame.setLayout(null);mailFrame.setVisible(true);
        }  //frame setting
    }
}
