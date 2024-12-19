package com.ndm.appbanhang.enitities;

public class User {
    private int id;
    private String userName ;
    private int age ;
    private String phone ;
    private String address ;
    private String avatar;
    private String passWord;
    private String email;

    public User() {
    }

    public User(int id, String userName, int age,String phone, String address, String avatar, String email, String passWord) {
        this.id = id;
        this.userName = userName;
        this.age = age;
        this.address = address;
        this.avatar = avatar;
        this.passWord = passWord;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
