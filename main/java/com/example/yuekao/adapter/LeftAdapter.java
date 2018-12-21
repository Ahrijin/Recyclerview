package com.example.yuekao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuekao.R;
import com.example.yuekao.bean.MyData;

import java.util.List;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {
    private List<MyData.DataBean> mList;
    private Context context;


    public LeftAdapter(List<MyData.DataBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public LeftAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.left_item, null);
        LeftAdapter.ViewHolder holder = new LeftAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeftAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(mList.get(i).getName() + "");

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickLeft != null){
                    onClickLeft.leftClick(v,i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }

    public interface OnClickLeft{
        void leftClick(View v,int position);
    }
    private OnClickLeft onClickLeft;

    //提供方法，适配器的点击事件
    public void OnItemClick(OnClickLeft onClickLeft){
        this.onClickLeft = onClickLeft;
    }



}
