package com.example.yuekao.model;

import com.example.yuekao.callback.MyCallBack;

public interface Model {
    void getData(String url,MyCallBack callBack);
}
