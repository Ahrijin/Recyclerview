package com.example.yuekao;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.yuekao.fragment.DingFragment;
import com.example.yuekao.fragment.ShouYeFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frame_layout;
    private RadioButton shouye;
    private RadioButton indent;
    private FragmentManager manager;
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setLisenter();
        manager.beginTransaction().replace(R.id.frame_layout,new ShouYeFragment()).commit();
    }

    private void setLisenter() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.shouye:
                        manager.beginTransaction().replace(R.id.frame_layout,new ShouYeFragment()).commit();
                        break;
                    case R.id.indent:
                        manager.beginTransaction().replace(R.id.frame_layout,new DingFragment()).commit();
                        break;
                }
            }
        });
    }

    private void initView() {
        frame_layout = (FrameLayout) findViewById(R.id.frame_layout);
        shouye = (RadioButton) findViewById(R.id.shouye);
        indent = (RadioButton) findViewById(R.id.indent);
        manager = getSupportFragmentManager();
        rg = (RadioGroup) findViewById(R.id.rg);
    }
}
