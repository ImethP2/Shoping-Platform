package com.ClientSide;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ForgetPwPanel extends ClientFrame{

    JPanel FPInit() {
        JPanel panelAll = new JPanel();
        JPanel panelFull = new JPanel();

        JPanel quizPanel = new JPanel();
        JPanel quizFull = new JPanel();
        JPanel quiz1 = new JPanel();
        JPanel quiz2 = new JPanel();
        JPanel quiz3 = new JPanel();
        JPanel quiz4 = new JPanel();
        JPanel quiz1label = new JPanel();
        JPanel quiz2label = new JPanel();
        JPanel quiz3label = new JPanel();
        JPanel quiz4label = new JPanel();
        JPanel quiz1TF = new JPanel();
        JPanel quiz2TF = new JPanel();
        JPanel quiz3PF = new JPanel();
        JPanel quiz4PF = new JPanel();

        JPanel welcomePanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome to Westminster Shopping Center - Reset Password");
        welcomePanel.add(welcomeLabel);
        welcomePanel.setPreferredSize(new Dimension(500, 50));
        welcomePanel.setLayout(new FlowLayout(FlowLayout.CENTER));




        
        JLabel quizLabel1 = new JLabel("Please enter your User Name");
        JTextField UserNameTF = new JTextField();
        UserNameTF.setPreferredSize(new Dimension(200, 30));
        quiz1label.add(quizLabel1);
        quiz1TF.add(UserNameTF);
        quiz1.add(quiz1label);
        quiz1.add(quiz1TF);
        quiz1.setPreferredSize(new Dimension(300, 50));
        quiz1label.setLayout(new FlowLayout(FlowLayout.LEFT));
        quiz1TF.setLayout(new FlowLayout(FlowLayout.LEFT));
        quiz1.setLayout(new BoxLayout(quiz1, BoxLayout.Y_AXIS));


        JLabel quizLabel2 = new JLabel("Please enter your email address");
        JTextField EmailTF = new JTextField();
        EmailTF.setPreferredSize(new Dimension(200, 30));
        quiz2label.add(quizLabel2);
        quiz2TF.add(EmailTF);
        quiz2.add(quiz2label);
        quiz2.add(quiz2TF);
        quiz2.setPreferredSize(new Dimension(300, 50));
        quiz2label.setLayout(new FlowLayout(FlowLayout.LEFT));
        quiz2TF.setLayout(new FlowLayout(FlowLayout.LEFT));
        quiz2.setLayout(new BoxLayout(quiz2, BoxLayout.Y_AXIS));

        JLabel quizLabel3 = new JLabel("Please enter your new password");
        JTextField PWPF = new JPasswordField();
        PWPF.setPreferredSize(new Dimension(200, 30));
        quiz3label.add(quizLabel3);
        quiz3PF.add(PWPF);
        quiz3.add(quiz3label);
        quiz3.add(quiz3PF);
        quiz3.setPreferredSize(new Dimension(300, 50));
        quiz3label.setLayout(new FlowLayout(FlowLayout.LEFT));
        quiz3PF.setLayout(new FlowLayout(FlowLayout.LEFT));
        quiz3.setLayout(new BoxLayout(quiz3, BoxLayout.Y_AXIS));

        JLabel quizLabel4 = new JLabel("Please enter your new password");
        JTextField RePWPF = new JPasswordField();
        RePWPF.setPreferredSize(new Dimension(200, 30));
        quiz4label.add(quizLabel4);
        quiz4PF.add(RePWPF);
        quiz4.add(quiz4label);
        quiz4.add(quiz4PF);
        quiz4.setPreferredSize(new Dimension(300, 50));
        quiz4label.setLayout(new FlowLayout(FlowLayout.LEFT));
        quiz4PF.setLayout(new FlowLayout(FlowLayout.LEFT));
        quiz4.setLayout(new BoxLayout(quiz4, BoxLayout.Y_AXIS));

        quizFull.add(quiz1);
        quizFull.add(quiz2);
        quizFull.add(quiz3);
        quizFull.add(quiz4);
        quizFull.setLayout(new BoxLayout(quizFull, BoxLayout.Y_AXIS));
        quizFull.setPreferredSize(new Dimension(250, 300));
        quizPanel.add(quizFull);
        quizPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        quizPanel.setPreferredSize(new Dimension(250, 400));


        JPanel btnPanel = new JPanel();
        JPanel SubmitPanel = new JPanel();
        JPanel SpPanel = new JPanel();

        JButton submitBtn = new JButton("Submit");
        submitBtn.addActionListener(e -> {
            //TODO : Sign In
            String UserName = UserNameTF.getText();
            String Email = EmailTF.getText();
            String Password = PWPF.getText();
            String RePassword = RePWPF.getText();
            System.out.println(UserName + " ∆ " + Email + " ∆ " + Password + " ∆ " + RePassword);
            try {

                forgetPassword(UserName, Email, Password, RePassword, SpPanel);

            } catch (Exception ex) {

                throw new RuntimeException(ex);
            }

        });

        JButton SignUpBtn = new JButton("Sign Up");
        SignUpBtn.addActionListener(e -> {
            //TODO : Sign In

            try {
                SignUpPanel signUpPanel = new SignUpPanel();
                signUpPanel.SUPInit();
                panelFull.setVisible(false);
            } catch (Exception ex) {

                throw new RuntimeException(ex);
            }

        });
        SubmitPanel.add(submitBtn);
        SubmitPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        SubmitPanel.setPreferredSize(new Dimension(150, 50));
        SpPanel.add(SignUpBtn);
        SpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        SpPanel.setPreferredSize(new Dimension(150, 50));
        SpPanel.setVisible(false);

        btnPanel.add(SpPanel);
        btnPanel.add(SubmitPanel);

        btnPanel.setLayout(new GridLayout(1, 2));

        panelAll.add(welcomePanel);
        panelAll.add(quizPanel);
        panelAll.add(btnPanel);
        panelAll.setLayout(new BoxLayout(panelAll, BoxLayout.Y_AXIS));
        panelAll.setPreferredSize(new Dimension(500, 400));
        panelFull.add(panelAll);
        panelFull.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelFull.setPreferredSize(new Dimension(700, 500));

        return panelFull;
    }
    private void forgetPassword(String userName, String email, String password, String rePassword, JPanel SignUp) throws IOException {
        //TODO : Forget Password
        SignUpPanel.UserLoader();

        if (SignUpPanel.checkUserIdExist(userName)) {

            for (User user : SignUpPanel.userArrayList){
                if(user.getUserName().equals(userName)){
                    if (user.getEmail().equals(email)) {
                        if (password.equals(rePassword)) {
                            user.setPassword(password);
                            JOptionPane.showMessageDialog(null, "Password changed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                            try {
                                saveUser(user);
                                SignInPanel signInPanel = new SignInPanel();
                                FrameInit(signInPanel.SIPInit(), signInPanel.title);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Password does not match", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Email does not match", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }


        } else {
            JOptionPane.showMessageDialog(null, "User Name does not exist", "Error", JOptionPane.ERROR_MESSAGE);
            SignUp.setVisible(true);
        }

    }

    private void saveUser(User user) throws IOException {
        File file = new File("UserList.txt");
        File tempFile = new File("TempUserList.txt");
        tempFile.createNewFile();
        FileWriter writer = new FileWriter(tempFile, true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] userArray = line.split("∆");
            if (userArray[0].equals(user.getUserName())) {
                bufferedWriter.write(user.getUserName() + "∆" + user.getPassword() + "∆" + user.getFirstName() + "∆" + user.getLastName() + "∆" + user.getEmail() + "∆" + user.isNewUser());
                bufferedWriter.newLine();
            } else {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.close();
        writer.close();
        file.delete();
        tempFile.renameTo(file);
    }
}
