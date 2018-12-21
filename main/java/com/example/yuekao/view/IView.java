package com.example.yuekao.view;

public interface IView<T> {
    void  setSuccess(T da);
    void setError(T error);
}
