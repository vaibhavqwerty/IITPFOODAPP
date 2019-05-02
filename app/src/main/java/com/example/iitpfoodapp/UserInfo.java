package com.example.iitpfoodapp;

public class UserInfo {
//    profileMap.put("device_token",deviceToken);
//                    profileMap.put("uid",currentUserId);
//                    profileMap.put("name",Username);
//                    profileMap.put("phone_number",PhoneNo);
//                    profileMap.put("address",Address);
    private String device_token;
    private String uid;
    private String name;
    private String phone_number;
    private String address;

    public UserInfo()
    {
        this.device_token="NULL";
        this.uid="NULL";
        this.name="abc";
        this.phone_number="00";
        this.address="NULL";
    }
    public UserInfo(String device_token,String uid,String name,String phone_number,String address)
    {
        this.device_token=device_token;
        this.uid=uid;
        this.name=name;
        this.phone_number=phone_number;
        this.address=address;
    }

    public String getUid() {
        return uid;
    }

    public String getAddress() {
        return address;
    }

    public String getDevice_token() {
        return device_token;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
