package com.example.yuekao.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuekao.R;

public class NumberView extends LinearLayout implements View.OnClickListener {
    private TextView jian;
    private TextView jia;
    private TextView num;
    private int mCount=0;
    public NumberView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.number_view,this);
        initView();
    }

    private void initView() {
        jian = findViewById(R.id.jian);
        jian.setOnClickListener(this);
        jia = findViewById(R.id.jia);
        jia.setOnClickListener(this);
        num = findViewById(R.id.shu);
    }

    public void setCount(int i){
        this.mCount = i;
        if(mCount==0){
            jian.setVisibility(GONE);
            num.setVisibility(GONE);
        }else {
            jian.setVisibility(VISIBLE);
            num.setVisibility(VISIBLE);
        }
        num.setText(mCount+"");
    }

    //接口回调
    private getCount mGetCount;
    public interface getCount{
        void Count(int con);
    }
    public void getNums(getCount getCount){
         this.mGetCount = getCount;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jian:
                if(mCount>0){
                    mCount--;
                    num.setText(mCount+"");
                    if(mGetCount != null){
                        mGetCount.Count(mCount);
                    }
                    if(mCount<=0){
                        jian.setVisibility(GONE);
                        num.setVisibility(GONE);
                    }
                }
                break;
            case R.id.jia:
                mCount++;
                num.setText(mCount+"");
                if(mGetCount!=null){
                    mGetCount.Count(mCount);
                }
                break;
        }
    }
}
