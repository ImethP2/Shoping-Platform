package com.ClientSide;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;



public class SignUpPanel extends ClientFrame {
    public static ArrayList<User> userArrayList = new ArrayList<>();
    JPanel SUPInit() throws IOException {
        JPanel panelFull = new JPanel();


        panelFull.add(QuizPanel());
        panelFull.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelFull.setPreferredSize(new Dimension(700,500));

        return panelFull;
    }
    private JPanel QuizPanel() throws IOException {
        UserLoader();
        JPanel SignUpPanel = new JPanel();
        JPanel WelcomePanel = new JPanel();

        JPanel QuizPanel = new JPanel();

        JPanel Quiz1 = new JPanel();
        JPanel Quiz2 = new JPanel();
        JPanel Quiz3 = new JPanel();
        JPanel Quiz4 = new JPanel();
        JPanel Quiz5 = new JPanel();
        JPanel Quiz6 = new JPanel();

        JPanel Quiz1L = new JPanel();
        JPanel Quiz2L= new JPanel();
        JPanel Quiz3L = new JPanel();
        JPanel Quiz4L = new JPanel();
        JPanel Quiz5L = new JPanel();
        JPanel Quiz6L = new JPanel();

        JPanel Quiz1TF = new JPanel();
        JPanel Quiz2TF = new JPanel();
        JPanel Quiz3TF = new JPanel();
        JPanel Quiz4TF = new JPanel();
        JPanel Quiz5PF = new JPanel();
        JPanel Quiz6PF = new JPanel();

        JPanel BtnPanel = new JPanel();
        JPanel BtnSignUpPanel = new JPanel();
        JPanel BtnSignInPanel = new JPanel();

        JLabel WelcomeLabel = new JLabel("Welcome to Westminster Shopping Manager");
        WelcomePanel.add(WelcomeLabel);
        WelcomePanel.setPreferredSize(new Dimension(400, 40));
        WelcomePanel.setLayout(new FlowLayout(FlowLayout.CENTER));


        JLabel Q1Label = new JLabel("First Name");
        Quiz1L.add(Q1Label);
        Quiz1L.setPreferredSize(new Dimension(150, 40));
        Quiz1L.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTextField Q1TF = new JTextField();
        Q1TF.setPreferredSize(new Dimension(200, 30));
        Quiz1TF.add(Q1TF);
        Quiz1TF.setPreferredSize(new Dimension(200, 40));
        Quiz1TF.setLayout(new FlowLayout(FlowLayout.LEFT));
        Quiz1.add(Quiz1L);
        Quiz1.add(Quiz1TF);
        Quiz1.setPreferredSize(new Dimension(400, 40));
        Quiz1.setLayout(new GridLayout(1, 2));

        JLabel Q2Label = new JLabel("Last Name");
        Quiz2L.add(Q2Label);
        Quiz2L.setPreferredSize(new Dimension(150, 40));
        Quiz2L.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTextField Q2TF = new JTextField();
        Q2TF.setPreferredSize(new Dimension(200, 30));
        Quiz2TF.add(Q2TF);
        Quiz2TF.setPreferredSize(new Dimension(200, 40));
        Quiz2TF.setLayout(new FlowLayout(FlowLayout.LEFT));
        Quiz2.add(Quiz2L);
        Quiz2.add(Quiz2TF);
        Quiz2.setPreferredSize(new Dimension(400, 40));
        Quiz2.setLayout(new GridLayout(1, 2));

        JLabel Q3Label = new JLabel("Email");
        Quiz3L.add(Q3Label);
        Quiz3L.setPreferredSize(new Dimension(150, 40));
        Quiz3L.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTextField Q3TF = new JTextField();
        Q3TF.setPreferredSize(new Dimension(200,30));
        Quiz3TF.add(Q3TF);
        Quiz3TF.setPreferredSize(new Dimension(200, 40));
        Quiz3TF.setLayout(new FlowLayout(FlowLayout.LEFT));
        Quiz3.add(Quiz3L);
        Quiz3.add(Quiz3TF);
        Quiz3.setPreferredSize(new Dimension(400, 40));
        Quiz3.setLayout(new GridLayout(1, 2));

        JLabel Q4Label = new JLabel("User Name");
        Quiz4L.add(Q4Label);
        Quiz4L.setPreferredSize(new Dimension(150, 40));
        Quiz4L.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTextField Q4TF = new JTextField();
        Q4TF.setPreferredSize(new Dimension(200,30));
        Quiz4TF.add(Q4TF);
        Quiz4TF.setPreferredSize(new Dimension(200, 40));
        Quiz4TF.setLayout(new FlowLayout(FlowLayout.LEFT));
        Quiz4.add(Quiz4L);
        Quiz4.add(Quiz4TF);
        Quiz4.setPreferredSize(new Dimension(400, 40));
        Quiz4.setLayout(new GridLayout(1, 2));

        JLabel Q5Label = new JLabel("Password");
        Quiz5L.add(Q5Label);
        Quiz5L.setPreferredSize(new Dimension(150, 40));
        Quiz5L.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPasswordField Q5PF = new JPasswordField();
        Q5PF.setPreferredSize(new Dimension(200,30));
        Quiz5PF.add(Q5PF);
        Quiz5PF.setPreferredSize(new Dimension(200, 40));
        Quiz5PF.setLayout(new FlowLayout(FlowLayout.LEFT));
        Quiz5.add(Quiz5L);
        Quiz5.add(Quiz5PF);
        Quiz5.setPreferredSize(new Dimension(400, 40));
        Quiz5.setLayout(new GridLayout(1, 2));

        JLabel Q6Label = new JLabel("Re-Enter Password");
        Quiz6L.add(Q6Label);
        Quiz6L.setPreferredSize(new Dimension(150, 40));
        Quiz6L.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPasswordField Q6PF = new JPasswordField();
        Q6PF.setPreferredSize(new Dimension(200,30));
        Quiz6PF.add(Q6PF);
        Quiz6PF.setPreferredSize(new Dimension(200, 40));
        Quiz6PF.setLayout(new FlowLayout(FlowLayout.LEFT));
        Quiz6.add(Quiz6L);
        Quiz6.add(Quiz6PF);
        Quiz6.setPreferredSize(new Dimension(400, 40));
        Quiz6.setLayout(new GridLayout(1, 2));

        QuizPanel.add(Quiz1);
        QuizPanel.add(Quiz2);
        QuizPanel.add(Quiz3);
        QuizPanel.add(Quiz4);
        QuizPanel.add(Quiz5);
        QuizPanel.add(Quiz6);
        QuizPanel.setLayout(new BoxLayout(QuizPanel, BoxLayout.Y_AXIS));
        QuizPanel.setPreferredSize(new Dimension(400, 250));

        JButton QuizBtn = new JButton("Sign Up");
        QuizBtn.addActionListener(e -> {
            try {
                String firstName = Q1TF.getText();
                String lastName = Q2TF.getText();
                String email = Q3TF.getText();
                String userName = Q4TF.getText();
                String password = Q5PF.getText();
                String rePassword = Q6PF.getText();
                System.out.println(firstName +"∆"+ lastName +"∆"+ email +"∆"+ userName +"∆"+ password +"∆"+ rePassword);
                QuizHandler(firstName, lastName, email, userName, password, rePassword, QuizPanel);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        JButton SignInBtn = new JButton("Sign In");
        SignInBtn.addActionListener(e -> {
            SignInPanel signInPanel = new SignInPanel();
            FrameInit(signInPanel.SIPInit(), "Westminster Shopping Center - Sign In");
        });
        BtnSignUpPanel.add(QuizBtn);
        BtnSignUpPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        BtnSignUpPanel.setPreferredSize(new Dimension(150, 40));

        BtnSignInPanel.add(SignInBtn);
        BtnSignInPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        BtnSignInPanel.setPreferredSize(new Dimension(150, 40));

        BtnPanel.add(BtnSignInPanel);
        BtnPanel.add(BtnSignUpPanel);
        BtnPanel.setPreferredSize(new Dimension(400, 40));
        BtnPanel.setLayout(new GridLayout(1, 2));

        SignUpPanel.add(WelcomePanel);
        SignUpPanel.add(QuizPanel);
        SignUpPanel.add(BtnPanel);
        SignUpPanel.setLayout(new BoxLayout(SignUpPanel, BoxLayout.Y_AXIS));
        SignUpPanel.setPreferredSize(new Dimension(400, 450));

        return SignUpPanel;
    }
    private void QuizHandler(String firstName, String lastName, String email, String userName, String password, String rePassword, JPanel QuizPanel) throws IOException {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || userName.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            JOptionPane.showMessageDialog(QuizPanel, "Please fill out all the fields", "Error", JOptionPane.ERROR_MESSAGE);

        } else {
            if (Pattern.matches("[a-zA-Z]+", firstName) && Pattern.matches("[a-zA-Z]+", lastName)) {

                if (Pattern.matches("^[\\w-\\.-_]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) {

                    if (!(userArrayList.isEmpty())) {
                        if (checkUserIdExist(userName)) {
                            JOptionPane.showMessageDialog(QuizPanel, "Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (password.contains("∆")) {
                                JOptionPane.showMessageDialog(QuizPanel, "Password cannot contain ∆ symbol", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                if (password.equals(rePassword)) {
                                    User.UserCreation(userName, password, firstName, lastName, email, true);
                                    ShoppingCenterPanel shoppingCenterPanel = new ShoppingCenterPanel();
                                    System.out.println(userArrayList.size());
                                    FrameInit(shoppingCenterPanel.SCPInit(), "Westminster Shopping Center");
                                } else {
                                    JOptionPane.showMessageDialog(QuizPanel, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }

                    }else{
                        if (password.contains("∆")) {
                            JOptionPane.showMessageDialog(QuizPanel, "Password cannot contain ∆ symbol", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (password.equals(rePassword)) {
                                User.UserCreation(userName, password, firstName, lastName, email, true);
                                ShoppingCenterPanel shoppingCenterPanel = new ShoppingCenterPanel();
                                System.out.println(userArrayList.size());
                                FrameInit(shoppingCenterPanel.SCPInit(), "Westminster Shopping Center");
                            } else {
                                JOptionPane.showMessageDialog(QuizPanel, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(QuizPanel, "Please enter a valid email", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(QuizPanel, "First and Last name can only contain letters", "Error", JOptionPane.ERROR_MESSAGE);

            }
        }


    }
    public static void UserLoader() throws IOException {
        File file = new File("UserList.txt");
        if (file.exists()) {
            try {
                BufferedReader UserLineReader = new BufferedReader(new FileReader("UserList.txt"));
                String line;
                while ((line = UserLineReader.readLine()) != null) {
                    String[] UserArray = line.split("∆");
                    String userName = UserArray[0];
                    String password = UserArray[1];
                    String firstName = UserArray[2];
                    String lastName = UserArray[3];
                    String email = UserArray[4];
                    Boolean newUser = Boolean.parseBoolean(UserArray[5]);
                    User user = new User(userName, password, firstName, lastName, email, newUser);
                    userArrayList.add(user);
                }
                UserLineReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            file.createNewFile();
        }
    }
    public static boolean checkUserIdExist(String userName) throws IOException {

        boolean UserNameExist = false;
        User.UserArrayList();
        for(User user: userArrayList){

            if(user.getUserName().equals(userName)){

                UserNameExist = true;
                break;
            }
        }

        return UserNameExist;
    }

}
