package com.example.mynote;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by 日不落 on 2017/5/4.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private LongClickListener longClickListener;
    private List<Message> dataList;
    private Context context;
    private ClickListener clickListener;

    public MyAdapter(Context context,List<Message> dataList) {
        this.context=context;
        this.dataList = dataList;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

        final Message msg = dataList.get(position);
        holder.tvTime.setText(msg.getTime());
        holder.tvContent.setText(msg.getContent());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longClickListener.del(msg);
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.look(msg);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setLongClickListener(LongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setClickListener(ClickListener clickListener){

        this.clickListener=clickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvContent;
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }
}
