package com.example.inhomecareapp.customer;

import java.io.Serializable;

public class Contract  implements Serializable {

    private String contractId;

    private String customerId;

    private String caregiverId;

    private String customerName;

    private String customerAddress;

    private String dateRange;

    private String timeRange;

    public Contract(String contractId, String customerId, String caregiverId, String customerName, String customerAddress, String dateRange, String timeRange) {
        this.contractId = contractId;
        this.customerId = customerId;
        this.caregiverId = caregiverId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.dateRange = dateRange;
        this.timeRange = timeRange;
    }

    public Contract() {
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(String caregiverId) {
        this.caregiverId = caregiverId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }
}
