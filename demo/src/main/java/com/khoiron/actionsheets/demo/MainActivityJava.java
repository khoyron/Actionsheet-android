package com.khoiron.actionsheets.demo;

import com.khoiron.actionsheets.ActionSheet;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivityJava extends AppCompatActivity {

    ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java);

        data.add("Delete it");
        data.add("Copy");
        data.add("Move");
        data.add("Duplicate");

        Button button = findViewById(R.id.testbtn);
        button.setOnClickListener(view -> new ActionSheet(MainActivityJava.this, data)
                .setTitle("What do you want to do with the file")
                .setCancelTitle("Cancel")
                .setColorTitle(getResources().getColor(R.color.title))
                .setColorBackground(getResources().getColor(R.color.background))
//                        .hideTitle()
//                        .setFontData(R.font.meryana_script)
//                        .setFontCancelTitle(R.font.meryana_script)
//                        .setFontTitle(R.font.meryana_script)
//                        .setSizeTextCancel(30)
//                        .setSizeTextData(30)
//                        .setSizeTextTitle(30)
                .setColorTitleCancel(getResources().getColor(R.color.action))
                .setColorData(getResources().getColor(R.color.action))
                .setColorSelected(getResources().getColor(R.color.colorAccent))
                .create((data, position) -> {
                    switch (position) {
                        case 0:
                            // your action
                            setLog(data);
                        case 1:
                            // your action
                        case 2:
                            // your action
                        case 3:
                            // your action
                    }
                }));
    }

    private void setLog(String s) {
        Log.e("Tag", s);
    }
}
