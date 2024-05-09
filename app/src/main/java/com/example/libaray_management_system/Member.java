package com.example.libaray_management_system;

public class Member {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String unpaid;

    public Member(int id, String name, String address, String phone,String unpaid) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.unpaid = unpaid;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address; // Corrected from this.name = name
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(String unpaid) {
        this.unpaid = unpaid;
    }
}
