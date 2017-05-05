package com.example.mynote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity extends AppCompatActivity {

    private TextView tv_time;
    private EditText edt_noteContent;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        initView();
        initData();
    }

    private void initData() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date data = new Date();
        time = format.format(data);
        tv_time.setText(time);


    }

    private void initView() {
        tv_time = (TextView) findViewById(R.id.tv_time);
        edt_noteContent = (EditText) findViewById(R.id.edt_noteContent);

    }


    @Override
    protected void onPause() {
        SaveToDB();
        super.onPause();
    }

    private void SaveToDB() {
        String content = edt_noteContent.getText().toString().trim();
        //String str = content.substring(0,10)+"...";
        if(content.length()>0){
            Message msg = new Message();
            msg.setTime(time);
            msg.setContent(content);
            msg.save();

        }
        finish();
    }



}
