package com.example.arpn.android_contacts;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactListFragment_A2_MT17007 extends Fragment implements AdapterView.OnItemClickListener{

    Communicator communicator;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    private List<ContactData_A2_MT17007> contactDataA2MT17007List = new ArrayList<>();
    private RecyclerView recyclerView;
    private ContactAdapter_A2_MT17007 contactAdapterA2MT17007;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.contact_recyclearview);

        FloatingActionButton myFab = (FloatingActionButton) view.findViewById(R.id.add_contact);
        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addContactIntent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
                startActivity(addContactIntent);
            }
        });


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(new ContactAdapter_A2_MT17007(contactDataA2MT17007List, new ContactAdapter_A2_MT17007.OnItemClickListener() {
            @Override
            public void onItemClick(ContactData_A2_MT17007 contactDataA2MT17007) {
                Toast.makeText(getActivity(), "Item clicked", Toast.LENGTH_LONG).show();
                communicator.respond(contactDataA2MT17007);
            }
        }));
        contactAdapterA2MT17007 = (ContactAdapter_A2_MT17007) recyclerView.getAdapter();
        loadContacts(view);
        return view;
    }

    private void loadContacts(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getActivity().checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {

            ContentResolver contentResolver = getActivity().getContentResolver();
            Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.Contacts.SORT_KEY_PRIMARY + " ASC");

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    String email = "";
                    String imageURI = "";

                    imageURI = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.Photo.PHOTO_URI));

                    Cursor getEmail = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID+" = ?", new String[] {id}, null);
                    if(getEmail.moveToFirst())
                    {
                        email = getEmail.getString(getEmail.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
                    }
                    getEmail.close();

                    int hasPhone = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));

                    Set<String> contactUnique = new HashSet<String>();
                    if (hasPhone > 0) {

                        Cursor cr2 = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{id}, null);
                        while (cr2.moveToNext()) {
                            String phoneNumber = cr2.getString(cr2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            contactUnique.add(phoneNumber);
                        }
                        cr2.close();
                    }
                    for (String phoneNumber : contactUnique) {
                        ContactData_A2_MT17007 contactDataA2MT17007Obj = new ContactData_A2_MT17007();
                        contactDataA2MT17007Obj.setcName(name);
                        contactDataA2MT17007Obj.setcPhoneNo(phoneNumber);
                        contactDataA2MT17007Obj.setcEmail(email);
                        contactDataA2MT17007Obj.setcImage(imageURI);
                        contactDataA2MT17007List.add(contactDataA2MT17007Obj);
                    }
                }

            }
            cursor.close();
            contactAdapterA2MT17007.notifyDataSetChanged();

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        communicator.respond(contactDataA2MT17007List.get(i));
    }

    public void setCommunicator(Communicator communicator)
    {
        this.communicator = communicator;
    }

    public interface Communicator
    {
        public void respond(ContactData_A2_MT17007 contactDataA2MT17007);
    }
}
