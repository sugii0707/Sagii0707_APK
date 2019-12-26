package com.example.sagii0707;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MonListAdapter extends ArrayAdapter<Mon>
{
    private LayoutInflater mInflater;
    private ArrayList<Mon> users;
    private int mViewResourceId;

    public MonListAdapter(Context context, int textViewResourceId, ArrayList<Mon> users) {
        super(context, textViewResourceId, users);
        this.users = users;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        Mon user = users.get(position);

        if (user != null) {
            TextView firstName = (TextView) convertView.findViewById(R.id.textFirstName);
            TextView lastName = (TextView) convertView.findViewById(R.id.textLastName);

            if (firstName != null) {
                firstName.setText(user.getEnglish());
            }
            if (lastName != null) {
                lastName.setText((user.getMongolia()));
            }

        }

        return convertView;
    }
}
