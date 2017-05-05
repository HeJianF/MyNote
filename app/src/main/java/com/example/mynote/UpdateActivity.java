package com.example.mynote;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateActivity extends AppCompatActivity {

    private TextView tv_time;
    private EditText edt_noteContent;
    private String time;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        initView();
        initData();
    }

    private void initData() {
        time = getIntent().getStringExtra("tv_time");
        content = getIntent().getStringExtra("edt_noteContent");
        tv_time.setText(time);
        edt_noteContent.setText(content);


    }

    private void initView() {
        tv_time = (TextView) findViewById(R.id.tv_time);
        edt_noteContent = (EditText) findViewById(R.id.edt_noteContent);

    }

    @Override
    protected void onStart() {
        //光标定到文本末尾
        edt_noteContent.setSelection(edt_noteContent.getText().length());
        super.onStart();
    }

    @Override
    protected void onPause() {
        UpdateToDB();
        super.onPause();
    }

    private void UpdateToDB() {
        String newContent = edt_noteContent.getText().toString().trim();
        if(newContent.length()>0 && !newContent.equals(content)){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date data = new Date();
            String newTime = format.format(data);
            ContentValues values = new ContentValues();
            values.put("time",newTime);
            values.put("content",newContent);
            DataSupport.updateAll(Message.class, values, "time = ?", time);

        }
        finish();
    }



}
