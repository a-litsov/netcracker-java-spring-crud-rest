package com.netcracker.adlitsov.domain;

public class Buyer {
    private int id;
    private String lastName;
    private String district;
    private int discount;

    public Buyer() {
    }

    public Buyer(int id, String lastName, String district, int discount) {
        this.id = id;
        this.lastName = lastName;
        this.district = district;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
