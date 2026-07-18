package ClinicProject;

import javax.swing.*;

class Doctor extends User{
    String type;
    int salary;
    int visitPayment;

    public void setType(String type) {
        this.type = type;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public void setVisitPayment(int visitPayment) {
        this.visitPayment = visitPayment;
    }
    public String getType() {
        return type;
    }
    public int getSalary() {
        return salary;
    }
    public int getVisitPayment() {
        return visitPayment;
    }

    public void visitPatient(Doctor doctor) {
        doctor.setWalletInventory(doctor.visitPayment);
        JOptionPane.showMessageDialog(null,"you visited the patient.");
    }

    @Override
    public String getWalletInventory() {
        return walletInventory +"$";
    }
}
