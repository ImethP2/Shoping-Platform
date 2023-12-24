package com.ClientSide;

import java.io.*;



public class User {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean newUser;

    public User(String userName, String password, String firstName, String lastName, String email, Boolean newUser) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.newUser = newUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isNewUser() {
        return this.newUser;
    }

    public void setNewUser(final boolean newUser) {
        this.newUser = newUser;
    }
    static void UserCreation(String username, String password, String firstName, String lastName, String email, Boolean newUser) throws RuntimeException, IOException {

        File file = new File("UserList.txt");
        if (file.exists()) {
            try {
                User user = new User(username, password, firstName, lastName, email, newUser);
                BufferedWriter UserLineWriter = new BufferedWriter(new FileWriter(file, true));
                UserLineWriter.newLine();
                UserLineWriter.write(user.getUserName() + "∆" + user.getPassword() + "∆" + user.getFirstName() + "∆" + user.getLastName() + "∆" + user.getEmail() + "∆" + user.isNewUser());
                UserLineWriter.close();
                SignUpPanel.userArrayList.add(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            file.createNewFile();
            UserCreation(username, password, firstName, lastName, email, newUser);
        }

    }
    static void UserArrayList() throws IOException{
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
                    SignUpPanel.userArrayList.add(user);
                }
                UserLineReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            file.createNewFile();
        }
    }

}
