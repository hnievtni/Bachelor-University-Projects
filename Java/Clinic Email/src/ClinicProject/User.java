package ClinicProject;

abstract class User implements Services{
    String name;
    String username;
    String pass;
    String title;
    String email;
    int phoneNumber;
    String day;
    String month;
    String year;
    int walletInventory=0;

    public void setName(String name){
        this.name=name;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public void setWalletInventory(int walletInventory){
        this.walletInventory+=walletInventory;
    }

    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }
    public String getPass() {
        return pass;
    }
    public String getTitle() {
        return title;
    }
    public String getEmail() {
        return email;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public String getDay() {
        return day;
    }
    public String getMonth() {
        return month;
    }
    public String getYear() {
        return year;
    }
    public abstract String getWalletInventory();

    public static String showUsersName(String title, String name) {
        return title+". "+name;
    }

    @Override
    public void findDoctor(Manager manager) {
    }
    @Override
    public void findReceptionist(Manager manager) {
    }
    @Override
    public User userValidity(Clinic clinic,String username, String pass) {
        User validUser=null;
        for (User user:clinic.getUsers()) {
            if (user.getUsername().equals(username) && user.getPass().equals(pass)) {
                validUser = user;
                break;
            }
        }
        return validUser;
    }
}
