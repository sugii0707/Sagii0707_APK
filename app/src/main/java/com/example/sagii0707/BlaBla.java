package com.example.sagii0707;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BlaBla extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData;
    private EditText editText, editText2;
    private int gg = 0;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blabla);
        editText = (EditText) findViewById(R.id.editText2);
        editText2 = (EditText) findViewById(R.id.editText3);
        btnAdd = (Button) findViewById(R.id.btnAdd2);
        btnViewData = (Button) findViewById(R.id.btnView2);
        mDatabaseHelper = new DatabaseHelper(this);


        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("order" , -1);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                String monEntry = editText2.getText().toString();

                if (editText.length() != 0 && editText2.length() != 0) {
                    AddData(newEntry, monEntry);
                    editText.setText("");
                    editText2.setText("");
                } else {
                    toastMessage("Заавал тект бич!");
                }

            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BlaBla.this, ListData.class);
                intent.putExtra("orders", selectedID);
                startActivity(intent);
            }
        });

    }

    public void AddData(String newEntry, String monEntry) {

        boolean insertData = mDatabaseHelper.addData2(newEntry, selectedID, monEntry);

        if (insertData) {
            toastMessage("Data Successfully Inserted!" + selectedID);
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
