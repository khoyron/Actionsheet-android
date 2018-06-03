package android.actionsheet.demo.com.khoiron.demo;

import android.actionsheet.demo.com.khoiron.actionsheetiosforandroid.ActionSheet;
import android.actionsheet.demo.com.khoiron.actionsheetiosforandroid.Interface.ActionSheetCallBack;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ActionSheet(MainActivityJava.this,data)
                        .setTitle("What do you want to do with the file")
                        .setCancelTitle("Cancle")
                        .setColorTitle(getResources().getColor(R.color.title))
                        .setColorTitleCancel(getResources().getColor(R.color.action))
                        .setColorData(getResources().getColor(R.color.action))
                        .setColorSelected(getResources().getColor(R.color.colorAccent))
                        .create(new ActionSheetCallBack() {
                            @Override
                            public void data(@NotNull String data, int position) {
                                switch (position){
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
                            }
                        });
            }
        });
    }

    private void setLog(String s) {
        Log.e("Tag",s);
    }
}
