package com.example.arpn.android_contacts;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity_A2_MT17007 extends AppCompatActivity implements ContactListFragment_A2_MT17007.Communicator {

    FragmentManager fragmentManager ;
    ContactListFragment_A2_MT17007 contactListFragmentA2MT17007;
    ContactDetailsFragment_A2_MT17007 contactDetailsFragmentA2MT17007;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        contactListFragmentA2MT17007 = (ContactListFragment_A2_MT17007) fragmentManager.findFragmentById(R.id.contactListFragment);
        contactListFragmentA2MT17007.setCommunicator(this);
    }


    @Override
    public void respond(ContactData_A2_MT17007 contactDataA2MT17007) {
        contactDetailsFragmentA2MT17007 = (ContactDetailsFragment_A2_MT17007) fragmentManager.findFragmentById(R.id.contactDetailsFragment);

        //Landscape
        if(contactDetailsFragmentA2MT17007 != null && contactDetailsFragmentA2MT17007.isVisible())
        {
            contactDetailsFragmentA2MT17007.updateData(contactDataA2MT17007);
        }

        //Portrait mode
        else
        {
            Intent i = new Intent(this, DetailsActivity_A2_MT17007.class);
            i.putExtra("contact", contactDataA2MT17007);
            startActivity(i);
        }
    }
}
