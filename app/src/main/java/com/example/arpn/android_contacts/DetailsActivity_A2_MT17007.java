package com.example.arpn.android_contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailsActivity_A2_MT17007 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent i = getIntent();
        ContactData_A2_MT17007 contactDataA2MT17007Obj = (ContactData_A2_MT17007)i.getParcelableExtra("contact");
        ContactDetailsFragment_A2_MT17007 contactDetailsFragmentA2MT17007Obj = (ContactDetailsFragment_A2_MT17007) getFragmentManager().findFragmentById(R.id.contactDetailsFragment);
        if (contactDetailsFragmentA2MT17007Obj != null)
        {
            contactDetailsFragmentA2MT17007Obj.updateData(contactDataA2MT17007Obj);
        }
    }
}
