package com.example.arpn.android_contacts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;


public class ContactDetailsFragment_A2_MT17007 extends Fragment {
    private TextView cName, cPhoneNo, cEmail;
    private ImageView cImage;
    private ContactData_A2_MT17007 contactDataA2MT17007Obj;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_details, container, false);
        cName = (TextView) view.findViewById(R.id.contactName);
        cPhoneNo = (TextView) view.findViewById(R.id.contactNo);
        cEmail = (TextView) view.findViewById(R.id.contactEmail);
        cImage = (ImageView) view.findViewById(R.id.contactImage);




        //Call Button Click
        ImageView callView = (ImageView) view.findViewById(R.id.make_call);
        callView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contactDataA2MT17007Obj.getcPhoneNo()));
                startActivity(intent);
            }
        });


        //Email Button Click
        ImageView emailView = (ImageView) view.findViewById(R.id.make_mail);
        emailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addresses[] = new String[1];
                    addresses[0] = contactDataA2MT17007Obj.getcEmail();
                    String subject = "Android Testing";
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                    intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    }
            }
        });


        //Edit Button Click
        FloatingActionButton myFab = (FloatingActionButton) view.findViewById(R.id.edit_contact);
        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addContactIntent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
                addContactIntent.putExtra(ContactsContract.Intents.Insert.NAME, contactDataA2MT17007Obj.getcName());
                addContactIntent.putExtra(ContactsContract.Intents.Insert.EMAIL, contactDataA2MT17007Obj.getcEmail());
                addContactIntent.putExtra(ContactsContract.Intents.Insert.PHONE, contactDataA2MT17007Obj.getcPhoneNo());
//                addContactIntent.putExtra(ContactsContract.Intents.Insert.D)
                startActivity(addContactIntent);
            }
        });



        return view;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void updateData(ContactData_A2_MT17007 contactDataA2MT17007)
    {
        if (contactDataA2MT17007 == null)
        {

        }
        else {
            contactDataA2MT17007Obj = contactDataA2MT17007;
            cName.setText(contactDataA2MT17007.getcName());
            cPhoneNo.setText(contactDataA2MT17007.getcPhoneNo());
            cEmail.setText(contactDataA2MT17007.getcEmail());
            if(contactDataA2MT17007.getcImage() != null)
                cImage.setImageURI(Uri.parse(contactDataA2MT17007.getcImage()));
            else
            {
                ColorGenerator generator = ColorGenerator.MATERIAL;
                int color1;
                color1 = generator.getRandomColor();
                TextDrawable drawable = TextDrawable.builder().buildRound(Character.toString(contactDataA2MT17007.getcName().charAt(0)), color1);
                cImage.setImageDrawable(drawable);
            }
        }
    }
}
