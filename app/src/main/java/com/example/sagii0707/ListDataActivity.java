package com.example.sagii0707;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by User on 2/28/2017.
 */

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";
    private String name;

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        context = this;
        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);


        populateListView();
    }

    private void populateListView() {


        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        final ArrayList<Ug> listData = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            Ug gg = new Ug(data.getString(1), data.getInt(2));
            listData.add(gg);
        }
        //create the list adapter and set the adapter
        //ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);

        ListAdapter customListAdapter = new CustomAdapter(this,R.layout.custom_row, listData );// Pass the food arrary to the constructor.

        mListView.setAdapter(customListAdapter);

        //mListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                name = listData.get(i).getNer();

                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.bottom_sheet);

                Button button1 = dialog.findViewById(R.id.addSheet);
                Button button2 = dialog.findViewById(R.id.memorize);
                Button button3 = dialog.findViewById(R.id.see1);
                Button button4 = dialog.findViewById(R.id.arhiw);

                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Cursor data = mDatabaseHelper.getOrder(name); //get the id associated with that name
                        int itemID = -1;

                        while(data.moveToNext()){
                            itemID = data.getInt(0);
                        }

                        if(itemID > -1){

                            Intent editScreenIntent = new Intent(ListDataActivity.this, BlaBla.class);
                            editScreenIntent.putExtra("order", itemID);

                            startActivity(editScreenIntent);
                        }
                        else{
                            toastMessage("");
                        }
                    }
                });

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Cursor data = mDatabaseHelper.getOrder(name); //get the id associated with that name
                        int itemID = -1;



                        while(data.moveToNext()){
                            itemID = data.getInt(0);
                        }

                        Cursor check = mDatabaseHelper.getData2(itemID);

                        int numRows = check.getCount();

                        if(itemID > -1 && numRows != 0){
                            toastMessage("Order is: " + itemID);
                            Intent editScreenIntent = new Intent(ListDataActivity.this, Play.class);
                            editScreenIntent.putExtra("order", itemID);

                            startActivity(editScreenIntent);
                        }
                        else{
                            toastMessage("Үгээ оруулана уу!");
                        }
                    }
                });

                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor data = mDatabaseHelper.getOrder(name); //get the id associated with that name
                        int itemID = -1;

                        while(data.moveToNext()){
                            itemID = data.getInt(0);
                        }

                        if(itemID > -1){

                            Intent editScreenIntent = new Intent(ListDataActivity.this, ListData.class);
                            editScreenIntent.putExtra("orders", itemID);

                            startActivity(editScreenIntent);
                        }
                        else{
                            toastMessage("");
                        }
                    }
                });
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Cursor data1 = mDatabaseHelper.getOrder(name); //get the id associated with that name
                        String Angil, Mongolian;
                        int itemID = -1;

                        while(data1.moveToNext()){
                            itemID = data1.getInt(0);
                        }

                        Cursor data2 = mDatabaseHelper.getData2(itemID); //get the id associated with that name


                        StringBuilder data = new StringBuilder();

                        while(data2.moveToNext()){
                            Angil = data2.getString(1);
                            Mongolian = data2.getString(3);

                            data.append("\n"+String.valueOf(Angil)+","+String.valueOf(Mongolian));

                        }

                        // data.append("Time,Distance");


                        try{
                            //saving the file into device
                            FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
                            out.write((data.toString()).getBytes());
                            out.close();

                            //exporting
                            Context context = getApplicationContext();
                            File filelocation = new File(getFilesDir(), "data.csv");
                            Uri path = FileProvider.getUriForFile(context, "com.example.exportcsv.fileprovider", filelocation);
                            Intent fileIntent = new Intent(Intent.ACTION_SEND);
                            fileIntent.setType("text/csv");
                            fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Data");
                            fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                            startActivity(Intent.createChooser(fileIntent, "Send mail"));
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }

                    }
                });


                dialog.show();

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
