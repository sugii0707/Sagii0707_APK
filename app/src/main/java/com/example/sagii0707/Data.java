package com.example.sagii0707;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Data extends AppCompatActivity {

    public static String[] questions ;

    public static String[] answers ;
    DatabaseHelper mDatabaseHelper;

    private int selectedOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedOrder = receivedIntent.getIntExtra("order" , -1);

        mDatabaseHelper = new DatabaseHelper(this);

    }

}
