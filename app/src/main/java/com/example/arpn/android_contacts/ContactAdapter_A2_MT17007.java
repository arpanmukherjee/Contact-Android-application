package com.example.arpn.android_contacts;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.List;

/**
 * Created by arpn on 02/04/18.
 */

public class ContactAdapter_A2_MT17007 extends RecyclerView.Adapter<ContactAdapter_A2_MT17007.MyContactViewHolder> {

    private List<ContactData_A2_MT17007> contactDataA2MT17007List;
    private OnItemClickListener clickListener;

    public ContactAdapter_A2_MT17007(List<ContactData_A2_MT17007> contactDataA2MT17007List, final OnItemClickListener clickListener)
    {
        this.contactDataA2MT17007List = contactDataA2MT17007List;
        this.clickListener = clickListener;
    }

    @Override
    public MyContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_contact_item, parent, false);
        return new MyContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyContactViewHolder holder, int position) {
        ContactData_A2_MT17007 contactDataA2MT17007 = contactDataA2MT17007List.get(position);
        holder.contactName.setText(contactDataA2MT17007.getcName());
        holder.contactPhoneNo.setText(contactDataA2MT17007.getcPhoneNo());
        if(contactDataA2MT17007.getcImage() != null)
            holder.contactImage.setImageURI(Uri.parse(contactDataA2MT17007.getcImage()));
        else
        {
            ColorGenerator generator = ColorGenerator.MATERIAL;
            int color1;
            color1 = generator.getRandomColor();
            TextDrawable drawable = TextDrawable.builder().buildRound(Character.toString(contactDataA2MT17007.getcName().charAt(0)), color1);
            holder.contactImage.setImageDrawable(drawable);
        }
        holder.Bind(contactDataA2MT17007List.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return contactDataA2MT17007List.size();
    }

    public interface OnItemClickListener
    {
        void onItemClick(ContactData_A2_MT17007 contactDataA2MT17007);
    }

    public class  MyContactViewHolder extends RecyclerView.ViewHolder {
        public TextView contactName, contactPhoneNo;
        public ImageView contactImage;


        public MyContactViewHolder(View itemView) {
            super(itemView);
            contactName = (TextView) itemView.findViewById(R.id.contact_name);
            contactPhoneNo = (TextView) itemView.findViewById(R.id.contact_no);
            contactImage = (ImageView) itemView.findViewById(R.id.image_contact);
        }

        public void Bind(final ContactData_A2_MT17007 contactDataA2MT17007, final OnItemClickListener clickListener)
        {
            contactName.setText(contactDataA2MT17007.getcName());
            contactPhoneNo.setText(contactDataA2MT17007.getcPhoneNo());
            if (contactDataA2MT17007.getcImage() != null)
                contactImage.setImageURI(Uri.parse(contactDataA2MT17007.getcImage()));
            else
            {
                ColorGenerator generator = ColorGenerator.MATERIAL;
                int color1;
                color1 = generator.getRandomColor();
                TextDrawable drawable = TextDrawable.builder().buildRound(Character.toString(contactDataA2MT17007.getcName().charAt(0)), color1);
                contactImage.setImageDrawable(drawable);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(contactDataA2MT17007);
                }
            });
        }
    }
}
