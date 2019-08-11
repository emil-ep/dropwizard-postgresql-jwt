package com.fyle.bankservice.models;

import com.google.gson.annotations.SerializedName;

public class BankModel {

    public static final String IFSC = "ifsc";
    public static final String BANK_ID = "bank_id";
    public static final String BRANCH = "branch";
    public static final String ADDRESS = "address";
    public static final String CITY = "city";
    public static final String DISTRICT = "district";
    public static final String STATE = "state";
    public static final String BANK_NAME = "bank_name";


    @SerializedName(IFSC)
    private String ifsc;

    @SerializedName(BANK_ID)
    private String bankId;

    @SerializedName(BRANCH)
    private String branch;

    @SerializedName(ADDRESS)
    private String address;

    @SerializedName(CITY)
    private String city;

    @SerializedName(DISTRICT)
    private String district;

    @SerializedName(STATE)
    private String state;

    @SerializedName(BANK_NAME)
    private String bankName;

    public BankModel(String ifsc, String bankId, String branch, String address, String city, String district, String state, String bankName) {
        this.ifsc = ifsc;
        this.bankId = bankId;
        this.branch = branch;
        this.address = address;
        this.city = city;
        this.district = district;
        this.state = state;
        this.bankName = bankName;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
