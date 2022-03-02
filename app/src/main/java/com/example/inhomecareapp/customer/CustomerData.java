package com.example.inhomecareapp.customer;

public class CustomerData {
    private String customerNameRegister,customerEmailRegister,customerPhoneRegister,customerAddressRegister;

    public CustomerData() {
    }

    public CustomerData(String customerNameRegister, String customerEmailRegister, String customerPhoneRegister,
                        String customerAddressRegister) {
        this.customerNameRegister = customerNameRegister;
        this.customerEmailRegister = customerEmailRegister;
        this.customerPhoneRegister = customerPhoneRegister;
        this.customerAddressRegister = customerAddressRegister;
    }

    public String getCustomerNameRegister() {
        return customerNameRegister;
    }

    public void setCustomerNameRegister(String customerNameRegister) {
        this.customerNameRegister = customerNameRegister;
    }

    public String getCustomerEmailRegister() {
        return customerEmailRegister;
    }

    public void setCustomerEmailRegister(String customerEmailRegister) {
        this.customerEmailRegister = customerEmailRegister;
    }

    public String getCustomerPhoneRegister() {
        return customerPhoneRegister;
    }

    public void setCustomerPhoneRegister(String customerPhoneRegister) {
        this.customerPhoneRegister = customerPhoneRegister;
    }

    public String getCustomerAddressRegister() {
        return customerAddressRegister;
    }

    public void setCustomerAddressRegister(String customerAddressRegister) {
        this.customerAddressRegister = customerAddressRegister;
    }
}
