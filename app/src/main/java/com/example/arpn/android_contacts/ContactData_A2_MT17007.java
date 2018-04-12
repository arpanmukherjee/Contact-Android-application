package com.example.arpn.android_contacts;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.regex.Pattern;

/**
 * Created by arpn on 02/04/18.
 */

public class ContactData_A2_MT17007 implements Parcelable {
    String cName, cPhoneNo, cEmail;
    String cImage;

    public ContactData_A2_MT17007(String cName, String cPhoneNo, String cImage) {
        this.cName = cName;
        this.cPhoneNo = cPhoneNo;
        this.cImage = cImage;
    }

    public ContactData_A2_MT17007(String cName, String cPhoneNo, String cEmail, String cImage) {
        this.cName = cName;
        this.cPhoneNo = cPhoneNo;
        this.cEmail = cEmail;
        this.cImage = cImage;
    }

    public ContactData_A2_MT17007() {
    }

    protected ContactData_A2_MT17007(Parcel in) {
        cName = in.readString();
        cPhoneNo = in.readString();
        cEmail = in.readString();
    }

    public boolean verifyEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static final Creator<ContactData_A2_MT17007> CREATOR = new Creator<ContactData_A2_MT17007>() {
        @Override
        public ContactData_A2_MT17007 createFromParcel(Parcel in) {
            return new ContactData_A2_MT17007(in);
        }

        @Override
        public ContactData_A2_MT17007[] newArray(int size) {
            return new ContactData_A2_MT17007[size];
        }
    };

    public String getcName() {
        return cName;
    }

    public String getcPhoneNo() {
        return cPhoneNo;
    }

    public String getcEmail() {
        return cEmail;
    }

    public String getcImage() {
        return cImage;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public void setcPhoneNo(String cPhoneNo) {
        this.cPhoneNo = cPhoneNo;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public void setcImage(String cImage) {
        this.cImage = cImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(cName);
        parcel.writeString(cPhoneNo);
        parcel.writeString(cEmail);
    }
}
