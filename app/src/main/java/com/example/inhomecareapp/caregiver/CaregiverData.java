package com.example.inhomecareapp.caregiver;
import java.io.Serializable;
public class CaregiverData implements Serializable {
    private String imageUrl, caregiverNameRegister, caregiverEmailRegister, caregiverPhoneRegister,
            serviceSelected, selectedCategory, selectedAge, gender, caregiverId;
    private double rate = 0.0;
    public CaregiverData(String imageUrl, String caregiverNameRegister, String caregiverEmailRegister,
                         String caregiverPhoneRegister, String serviceSelected, String selectedCategory, String selectedAge,
                         String gender, String caregiverId) {
        this.imageUrl = imageUrl;
        this.caregiverNameRegister = caregiverNameRegister;
        this.caregiverEmailRegister = caregiverEmailRegister;
        this.caregiverPhoneRegister = caregiverPhoneRegister;
        this.serviceSelected = serviceSelected;
        this.selectedCategory = selectedCategory;
        this.selectedAge = selectedAge;
        this.gender = gender;
        this.caregiverId = caregiverId;
    }
    public CaregiverData() {
    }
    public double getRate() {
        return rate;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }
    public String getCaregiverId() {
        return caregiverId;
    }
    public void setCaregiverId(String caregiverId) {
        this.caregiverId = caregiverId;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getServiceSelected() {
        return serviceSelected;
    }
    public void setServiceSelected(String serviceSelected) {
        this.serviceSelected = serviceSelected;
    }
    public String getSelectedCategory() {
        return selectedCategory;
    }
    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }
    public String getSelectedAge() {
        return selectedAge;
    }
    public void setSelectedAge(String selectedAge) {
        this.selectedAge = selectedAge;
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
    @Override
    public String toString() {
        return "CaregiverData{" +
                "imageUrl='" + imageUrl + '\'' +
                ", caregiverNameRegister='" + caregiverNameRegister + '\'' +
                ", caregiverEmailRegister='" + caregiverEmailRegister + '\'' +
                ", caregiverPhoneRegister='" + caregiverPhoneRegister + '\'' +
                ", serviceSelected='" + serviceSelected + '\'' +
                ", selectedCategory='" + selectedCategory + '\'' +
                ", selectedAge='" + selectedAge + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

}
