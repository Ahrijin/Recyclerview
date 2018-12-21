package com.example.yuekao.callback;

public interface MyCallBack<T> {
    void success(T d);
    void error(T e);
}
