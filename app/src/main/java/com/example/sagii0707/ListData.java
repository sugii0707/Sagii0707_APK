package com.example.sagii0707;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by User on 2/28/2017.
 */

public class ListData extends AppCompatActivity {

    private static final String TAG = "List";

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;

    ArrayList<Mon> listData;
    ListView listView2;
    Mon user;

    private int selectedOrder2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        mListView = (ListView) findViewById(R.id.listView2);
        mDatabaseHelper = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();

        selectedOrder2 = receivedIntent.getIntExtra("orders" , -1);

        populateListView();
    }

    private void populateListView() {



        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData2(selectedOrder2);

        listData = new ArrayList<>();
       /* while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }*/

        int numRows = data.getCount();
        if(numRows == 0){
            Toast.makeText(ListData.this,"The Database is empty  :(.",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                user = new Mon(data.getString(1), data.getString(3));
                listData.add(user);
            }
           // MonListAdapter adapter =  new MonListAdapter(this,R.layout.adapter_view_layout, listData);
           // listView2 = (ListView) findViewById(R.id.listView2);
            //listView2.setAdapter(adapter);
        }


        //create the list adapter and set the adapter
        ListAdapter adapter = new MonListAdapter(this, R.layout.adapter_view_layout, listData);
        mListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //String name = adapterView.getItemAtPosition(i).toString();
                String name = ((TextView) view.findViewById(R.id.textFirstName)).getText().toString();
                String name1 = ((TextView) view.findViewById(R.id.textLastName)).getText().toString();

                Cursor data = mDatabaseHelper.getItemID(name); //get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    toastMessage(" id = " + itemID);
                    Intent editScreenIntent = new Intent(ListData.this, EditDataActivity.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("name",name);
                    editScreenIntent.putExtra("mongol", name1);
                    startActivity(editScreenIntent);
                }
                else{
                    toastMessage("No ID associated with that name");
                }
            }
        });
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
