package com.example.cs496_proj1.contacts;

public class Contact {
    String phone, fullName;
    long thumnailId, personId;
    int id;

    public Contact(String ph, String fn, long tnid, long pid) {
        phone = ph;
        fullName = fn;
        thumnailId = tnid;
        personId = pid;
    }

    public boolean isStartWith (String str) {
        return phone.startsWith(str);
    }

    public String getMsg() {
        return ("name=" + fullName + ", phone=" + phone);
    }

    public void setId(int i) {
        id = i;
    }

}
