package com.example.holmesk.recyclerviewtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.holmesk.recyclerviewtest.R;

import java.util.ArrayList;

/**
 * 作者：holmes k
 * 时间：2017.05.10 10:25
 */


public class MyViewAdapter extends RecyclerView.Adapter<MyViewAdapter.MyHolder> {

    Context context;
    ArrayList<String> datas;

    public MyViewAdapter(Context context, ArrayList<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    //定义回调的接口
    public interface OnMyItemClickListener {

        void onMyItemClick(View view, int position);

        void onMyItemLongClick(View view, int position);

    }

    //回调方法
    private OnMyItemClickListener myClick;

    public void setOnItemClickListener(OnMyItemClickListener onMyItemClickListener) {
        this.myClick = onMyItemClickListener;
    }


    //把view绑定给viewholder
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    //数据赋值给控件
    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {

        holder.textView.setText(datas.get(position));

        //为接口参数赋值
        if (myClick != null) {

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int myposition = holder.getLayoutPosition();
                    myClick.onMyItemClick(holder.itemView, myposition);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    myClick.onMyItemLongClick(holder.itemView, pos);
                    removeData(pos);
                    return true;
                }
            });

        }

    }

    public void removeData(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.mytext);

        }
    }
}
