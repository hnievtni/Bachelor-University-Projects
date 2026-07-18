import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Client {

    static Controller controller;

    public static void main(String[] args) {
        try {
            controller = new Controller();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        setMainFrame();
    }
    public static void setMainFrame(){
        JFrame mainFrame=frameTemplate("Main Frame");

        JLabel signingFormLabel=new JLabel("Please select one of this signing forms");{
            signingFormLabel.setBounds(150,120,300,50);
            signingFormLabel.setHorizontalAlignment(JLabel.CENTER);
            signingFormLabel.setVerticalAlignment(JLabel.CENTER);
            signingFormLabel.setFont(new Font("Consoles",Font.PLAIN,17));
            signingFormLabel.setBackground(Color.WHITE);
            signingFormLabel.setOpaque(true);
        }

        JButton signUpButton=new JButton("Sign Up");{
            signUpButton.setBounds(165, 320, 115, 40);
            signUpButton.setFocusable(false);
            signUpButton.setBackground(Color.WHITE);
            signUpButton.setBorderPainted(false);
            signUpButton.setFont(new Font("Consoles", Font.PLAIN, 17));
            signUpButton.addActionListener(e -> {
                mainFrame.setVisible(false);
                signUp();
            });
        }
        JButton signInButton=new JButton("Sign In");{
            signInButton.setBounds(320,320,115,40);
            signInButton.setFocusable(false);
            signInButton.setBackground(Color.WHITE);
            signInButton.setBorderPainted(false);
            signInButton.setFont(new Font("Consoles",Font.PLAIN,17));
            signInButton.addActionListener(e ->{
                mainFrame.setVisible(false);
                signIn();
            });
        }

        {
            mainFrame.setContentPane(new JLabel(new ImageIcon("main background.jpg")));
            mainFrame.add(signUpButton);mainFrame.add(signInButton);
            mainFrame.add(signingFormLabel);
            mainFrame.setLayout(null);mainFrame.setVisible(true);
        } //frame setting
    }
    public static void signUp(){
        JFrame signUpFrame=frameTemplate("Sign Up page");

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

        String[] positions={"Doctor", "Patient"};
        JLabel positionLabel=new JLabel("Position:");{
            positionLabel.setBounds(200,345,100,50);
            positionLabel.setHorizontalAlignment(JLabel.CENTER);
            positionLabel.setVerticalAlignment(JLabel.CENTER);
        }
        JComboBox<String> positionComboBox = new JComboBox<>(positions);{
            positionComboBox.setBounds(310, 360, 90, 20);
            positionComboBox.setBackground(Color.LIGHT_GRAY);
            positionComboBox.setSelectedIndex(-1);
        }

        JButton backButton=backButtonFormat();{
            backButton.addActionListener(e -> {
                signUpFrame.setVisible(false);
                setMainFrame();
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
                if (emailTextField.getText().contains("/")) {
                    JOptionPane.showMessageDialog(null,"email is not valid; Please try again");
                    signUpFrame.setVisible(false);
                    signUp();
                }
                else {
                    User user = new User(nameTextField.getText(), userTextField.getText(), emailTextField.getText(),
                            passTextField.getText(), (String) positionComboBox.getSelectedItem(), "blank profile.png");
                    signUpFrame.setVisible(false);
                    try {
                        controller.messageHandler(user, "new user", null, null,null);
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }

        {
            signUpFrame.add(nameLabel); signUpFrame.add(nameTextField);
            signUpFrame.add(userLabel); signUpFrame.add(userTextField);
            signUpFrame.add(passLabel); signUpFrame.add(passTextField);
            signUpFrame.add(emailLabel); signUpFrame.add(emailTextField);
            signUpFrame.add(positionLabel); signUpFrame.add(positionComboBox);
            signUpFrame.add(submit); signUpFrame.add(backButton);
            signUpFrame.setLayout(null);signUpFrame.setVisible(true);
        } //frame setting
    }
    public static void signIn(){
        JFrame signInFrame=frameTemplate("Sign In page");

        JLabel userLabel=new JLabel("Username:");{
            userLabel.setBounds(250,105,100,50);
            userLabel.setHorizontalAlignment(JLabel.CENTER);
            userLabel.setVerticalAlignment(JLabel.CENTER);
        }
        JTextField userTextField = new JTextField();{
            userTextField.setBounds(200, 155, 200, 20);
        }

        JLabel passLabel=new JLabel("Password:");{
            passLabel.setBounds(250,200,100,50);
            passLabel.setHorizontalAlignment(JLabel.CENTER);
            passLabel.setVerticalAlignment(JLabel.CENTER);
        }
        JTextField passTextField = new JTextField();{
            passTextField.setBounds(200, 250, 200, 20);
        }

        JButton backButton=backButtonFormat();{
            backButton.addActionListener(e -> {
                signInFrame.setVisible(false);
                setMainFrame();
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
               signInFrame.setVisible(false);
                try {
                    controller.messageHandler(null, "Sign In",null, userTextField.getText(), passTextField.getText());
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        }

        {
            signInFrame.add(userLabel); signInFrame.add(userTextField);
            signInFrame.add(passLabel); signInFrame.add(passTextField);
            signInFrame.add(submit); signInFrame.add(backButton);
            signInFrame.setLayout(null);signInFrame.setVisible(true);
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
}