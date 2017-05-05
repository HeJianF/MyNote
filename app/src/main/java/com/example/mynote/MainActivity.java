package com.example.mynote;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import org.litepal.crud.DataSupport;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LongClickListener ,ClickListener{

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<Message> data;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initAdapter();
    }

    private void initAdapter() {
        myAdapter = new MyAdapter(MainActivity.this,data);
        recyclerView.setAdapter(myAdapter);
        myAdapter.setClickListener(this);
        myAdapter.setLongClickListener(this);
    }

    private void initData() {
        data.clear();
        List<Message> messages = DataSupport.findAll(Message.class);
        for(int i=0;i<messages.size();i++){
            Message msg = new Message();
            msg.setContent(messages.get(i).getContent());
            msg.setTime(messages.get(i).getTime());
            data.add(msg);
        }

    }



    @Override
    protected void onResume() {
        super.onResume();
        initData();
        myAdapter.notifyDataSetChanged();

    }

    private void initView() {
        data = new ArrayList<Message>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,EditActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void look(Message message) {
        String time=message.getTime();
        String content=message.getContent();
        Intent intent=new Intent(MainActivity.this,UpdateActivity.class);
        intent.putExtra("tv_time",time);
        intent.putExtra("edt_noteContent",content);
        startActivity(intent);

    }

    @Override
    public void del(Message message) {
        showDialog(message);

    }

    private void showDialog(final Message message){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setMessage("删除该便签?"); //设置内容
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataSupport.deleteAll(Message.class,"time=?",message.getTime());
                initData();
                myAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}
