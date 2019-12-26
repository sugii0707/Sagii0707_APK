package com.example.sagii0707;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Ug>{

    private LayoutInflater mInflater;
    private ArrayList<Ug> users;
    private int mViewResourceId;

    public CustomAdapter(Context context, int textViewResourceId, ArrayList<Ug> users) {
        super(context, textViewResourceId, users);
        this.users = users;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        Ug user = users.get(position);

        if (user != null) {
            TextView item =  convertView.findViewById(R.id.item_text);
            ImageView imageView = convertView.findViewById(R.id.imageView);

                item.setText(user.getNer());

                if(user.getStar() == 0){
                    imageView.setImageResource(R.drawable.star0);
                }else if(user.getStar() == 1){
                    imageView.setImageResource(R.drawable.star1);
                }else if(user.getStar() == 2){
                    imageView.setImageResource(R.drawable.star2);
                }else if(user.getStar() == 3){
                    imageView.setImageResource(R.drawable.star3);
                }else if(user.getStar() == 4){
                    imageView.setImageResource(R.drawable.star4);
                }else if(user.getStar() == 5){
                    imageView.setImageResource(R.drawable.star5);
                }

        }

        return convertView;
    }
}
