package ClinicProject;

import javax.swing.*;

class Patient extends User{
    String sicknessType;

    public void setSicknessType(String sicknessType) {
        this.sicknessType = sicknessType;
    }
    public String getSicknessType() {
        return sicknessType;
    }

    public void payVisitPayment(Clinic clinic,Patient patient) {
        for (var section:clinic.getSections()){
            if (section.getPatients().contains(patient)) {
                for (var doctor:section.getReceptionist().getDoctorReferralsHistory().values()) {
                    if (section.getReceptionist().getDoctorReferralsHistory().get(patient).equals(doctor)){
                        int visitPayment = doctor.getVisitPayment();
                        visitPayment *= -1;
                        patient.setWalletInventory(visitPayment);
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(null,"you paid the visit payment.");

    }

    @Override
    public String getWalletInventory() {
        return walletInventory +"$";
    }
}