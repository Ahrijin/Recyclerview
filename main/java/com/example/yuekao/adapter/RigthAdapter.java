package com.example.yuekao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuekao.R;
import com.example.yuekao.bean.MyData;
import com.example.yuekao.weight.NumberView;

import java.util.List;

public class RigthAdapter extends RecyclerView.Adapter<RigthAdapter.ViewHolder> {
    private List<MyData.DataBean.SpusBean> mLists;
    private Context context;

    public RigthAdapter(List<MyData.DataBean.SpusBean> mLists, Context context) {
        this.mLists = mLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.right_item, viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.price.setText(mLists.get(i).getSkus().get(0).getPrice() + "");
        viewHolder.titls.setText(mLists.get(i).getName());
        Glide.with(context).load(mLists.get(i).getPic_url()).into(viewHolder.imgs);
        viewHolder.myView.setCount(mLists.get(i).getPraise_num());
        viewHolder.myView.getNums(new NumberView.getCount() {
            @Override
            public void Count(int con) {
                mLists.get(i).setPraise_num(con);
                notifyDataSetChanged();
                //调用刷新视图
                adapterCallBack.shuaxin();
            }
        });

    }
    public float setAllGoodsPrice(){
        float price = 0;
        for (int i = 0; i < mLists.size(); i++) {
            MyData.DataBean.SpusBean spusBean = mLists.get(i);
            price += spusBean.getPraise_num()*Float.parseFloat(spusBean.getSkus().get(0).getPrice());
        }
        return price;
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgs;
        private TextView titls;
        private TextView price;
        private NumberView myView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgs = itemView.findViewById(R.id.can_imgs);
            titls = itemView.findViewById(R.id.titles);
            price = itemView.findViewById(R.id.price);
           myView = itemView.findViewById(R.id.myView);
        }
    }

    //接口回调
    private AdapterCallBack adapterCallBack;
    public interface AdapterCallBack{
        void shuaxin();
    }
    public void OnChange(AdapterCallBack adapterCallBack){
        this.adapterCallBack = adapterCallBack;
    }
}
