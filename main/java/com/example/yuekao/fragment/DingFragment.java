package com.example.yuekao.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuekao.R;
import com.example.yuekao.adapter.LeftAdapter;
import com.example.yuekao.adapter.RigthAdapter;
import com.example.yuekao.bean.MyData;
import com.example.yuekao.presenter.PresenterImpls;
import com.example.yuekao.view.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DingFragment extends Fragment implements IView {


    private View v;
    private TextView shopName;
    private TextView heji;
    private RecyclerView recyLeft;
    private RecyclerView recyRight;
    private LeftAdapter leftAadapter;
    private RigthAdapter rigthAadapter;
    private List<MyData.DataBean> datas = new ArrayList<>();
    List<MyData.DataBean.SpusBean> spus = new ArrayList<>();

    private String mUrl = "http://www.wanandroid.com/tools/mockapi/6523/restaurant-list";
    private PresenterImpls presenterImpls;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_ding, container, false);
        initView();
        leftAadapter = new LeftAdapter(datas, getActivity());
        recyLeft.setAdapter(leftAadapter);
        presenterImpls = new PresenterImpls(this);
        presenterImpls.startRequest(mUrl);
        rigthAadapter = new RigthAdapter(spus, getActivity());
        recyRight.setAdapter(rigthAadapter);
        leftAadapter.OnItemClick(new LeftAdapter.OnClickLeft() {
            @Override
            public void leftClick(View v, int position) {
                spus.clear();
                spus.addAll(datas.get(position).getSpus());
                for (int i = 0; i < spus.size(); i++) {
                    spus.get(i).setPraise_num(0);
                }
                rigthAadapter.notifyDataSetChanged();
                //把组的店名展示出来
                shopName.setText(datas.get(position).getName());
            }
        });
        rigthAadapter.OnChange(new RigthAdapter.AdapterCallBack() {
            @Override
            public void shuaxin() {
                //调用rigthAadapter中的setAllGoodsPrice的方法
                heji.setText(rigthAadapter.setAllGoodsPrice() + "");
            }
        });

        return v;
    }


    private void initView() {
        recyLeft = v.findViewById(R.id.recy_left);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyLeft.setLayoutManager(linearLayoutManager);

        recyRight = v.findViewById(R.id.recy_right);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity());
        recyRight.setLayoutManager(linearLayoutManager2);

        shopName = v.findViewById(R.id.shop_name);
        heji = v.findViewById(R.id.hejis);
    }

    @Override
    public void setSuccess(Object da) {
        MyData myData = (MyData) da;
        datas.addAll(myData.getData());
        leftAadapter.notifyDataSetChanged();

    }

    @Override
    public void setError(Object error) {

    }
}
