package com.example.arpn.android_contacts;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by arpn on 12/04/18.
 */
public class DetailsActivity_A2_MT17007Test {
    ContactData_A2_MT17007 contactData_a2_mt17007;
    public void initialize()
    {
        contactData_a2_mt17007 = new ContactData_A2_MT17007("Arpan Mukherjee", "8350030602", "arpan17007@iiitd.ac.in", "imageTest");
    }
    @Test
    public void testEmail()
    {
        assertNotNull(contactData_a2_mt17007.getcEmail());
    }

    @Test
    public void testObject()
    {
        assertNotNull(contactData_a2_mt17007);
    }

    @Test
    public void testPhoneNo()
    {
        assertNotNull(contactData_a2_mt17007.getcPhoneNo());
    }

    @Test
    public void validationEmail()
    {
        ArrayList<String> emails = new ArrayList<String>();
        emails.add("arpan17007@iiitd.ac.in");
        emails.add("arpanmukherjee@gmail.com");
        for (int i = 0; i < emails.size(); i++) {
            assertTrue(contactData_a2_mt17007.verifyEmail(emails.get(i)));
        }
    }

}