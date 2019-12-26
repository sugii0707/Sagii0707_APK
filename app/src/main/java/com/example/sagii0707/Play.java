package com.example.sagii0707;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Play extends AppCompatActivity {

    Button button, algasah;
    TextView textView, hariult;
    EditText editText;
    ImageView imageView;
    int rankIncrement = 0;

    DatabaseHelper mDatabaseHelper;
    public static String[] answers ;

    List<Item> question;
    int curQuestion = 0;
    private int selectedOrder;

    ArrayList<String> listData;
    ArrayList<String> listData2;
    int random=0;

    Data data;

    Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);

        button = findViewById(R.id.continue1);
        textView = findViewById(R.id.ug);
        editText = findViewById(R.id.hariu);
        mDatabaseHelper = new DatabaseHelper(this);
        algasah = findViewById(R.id.algasah);
        hariult = findViewById(R.id.hariult);

        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedOrder = receivedIntent.getIntExtra("order" , -1);

        button.setVisibility(View.INVISIBLE);

        question = new ArrayList<>();


/*        Cursor data = mDatabaseHelper.Questions(selectedOrder);

        listData = new ArrayList<>();



        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(0));
        }

        Cursor data2 = mDatabaseHelper.Answers(selectedOrder);

        listData2 = new ArrayList<>();
        while(data2.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData2.add(data2.getString(0));
        }


        /*for (int i = 0; i < questions.length; i++) {
            question.add(new Item(questions[i], answers[i]));
        }

        for (int i = 0; i < listData.size(); i++) {
            question.add(new Item(listData.get(i), listData2.get(i)));
        }
        */

        Cursor data = mDatabaseHelper.getData2(selectedOrder);

        while(data.moveToNext()){
            question.add(new Item(data.getString(1), data.getString(3)));
        }

        Collections.shuffle(question);

        textView.setText(question.get(curQuestion).getQuestion());


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText.getText().toString().equalsIgnoreCase(question.get(curQuestion).getAnswer())) {
                    button.setVisibility(View.VISIBLE);

                } else {
                    button.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curQuestion < question.size() - 1) {

                    curQuestion++;

                    textView.setText(question.get(curQuestion).getQuestion());

                    button.setVisibility(View.INVISIBLE);
                    editText.setText("");
                    hariult.setText("");

                } else {
                    Cursor ranking = mDatabaseHelper.Rating(selectedOrder);

                    int star = 0;

                    while (ranking.moveToNext()){
                        star=ranking.getInt(0);
                    }
                    hariult.setText("");

                    star = star + 1;
                    mDatabaseHelper.updateRank(star, selectedOrder);
                    Toast.makeText(Play.this, "Нэг од нэмэгдлээ! ", Toast.LENGTH_SHORT).show();
                    curQuestion = 0;

                    textView.setText(question.get(curQuestion).getQuestion());
                    button.setVisibility(View.INVISIBLE);
                    editText.setText("");
                }
            }
        });

        algasah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hariult.setText(" Зөв хариулт бол " + question.get(curQuestion).getAnswer());
                button.setVisibility(View.VISIBLE);
            }
        });
    }
}
