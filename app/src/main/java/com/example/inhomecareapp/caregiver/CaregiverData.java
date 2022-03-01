package com.example.inhomecareapp.caregiver;

public class CaregiverData {
    private String caregiverNameRegister,caregiverEmailRegister,caregiverPhoneRegister,caregiverAddressRegister;

    public CaregiverData() {
    }

    public CaregiverData(String caregiverNameRegister, String caregiverEmailRegister, String caregiverPhoneRegister, String caregiverAddressRegister ) {
        this.caregiverNameRegister = caregiverNameRegister;
        this.caregiverEmailRegister = caregiverEmailRegister;
        this.caregiverPhoneRegister = caregiverPhoneRegister;
        this.caregiverAddressRegister = caregiverAddressRegister;

    }

    public String getCaregiverNameRegister() {
        return caregiverNameRegister;
    }

    public void setCaregiverNameRegister(String caregiverNameRegister) {
        this.caregiverNameRegister = caregiverNameRegister;
    }

    public String getCaregiverEmailRegister() {
        return caregiverEmailRegister;
    }

    public void setCaregiverEmailRegister(String caregiverEmailRegister) {
        this.caregiverEmailRegister = caregiverEmailRegister;
    }

    public String getCaregiverPhoneRegister() {
        return caregiverPhoneRegister;
    }

    public void setCaregiverPhoneRegister(String caregiverPhoneRegister) {
        this.caregiverPhoneRegister = caregiverPhoneRegister;
    }

    public String getCaregiverAddressRegister() {
        return caregiverAddressRegister;
    }

    public void setCaregiverAddressRegister(String caregiverAddressRegister) {
        this.caregiverAddressRegister = caregiverAddressRegister;
    }
}
