package com.example.sagii0707;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by User on 2/28/2017.
 */

public class EditDataActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button btnSave,btnDelete;
    private EditText editable_item, editable_item2;

    DatabaseHelper mDatabaseHelper;

    private String selectedName, selectedName2;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editable_item = (EditText) findViewById(R.id.editable_item);
        editable_item2 = (EditText) findViewById(R.id.editable_item2);
        mDatabaseHelper = new DatabaseHelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");
        selectedName2 = receivedIntent.getStringExtra("mongol");

        //set the text to show the current selected name
        editable_item.setText(selectedName);
        editable_item2.setText(selectedName2);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                String item2 = editable_item2.getText().toString();
                if(!item.equals("") && !item2.equals("")){
                    mDatabaseHelper.updateName(item,selectedID,selectedName);
                    mDatabaseHelper.updateName2(item2,selectedID,selectedName2);
                    toastMessage("Shinjlegdlee");
                    Intent editScreenIntent = new Intent(EditDataActivity.this, MainActivity.class);
                    startActivity(editScreenIntent);
                }else{
                    toastMessage("Shinjlegdsengvi");
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID,selectedName);
                editable_item.setText("");
                toastMessage("Ustgalaa");

                finish();
                moveTaskToBack(true);

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
