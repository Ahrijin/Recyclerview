package com.example.yuekao.presenter;

import com.example.yuekao.callback.MyCallBack;
import com.example.yuekao.model.ModelImpls;
import com.example.yuekao.view.IView;

public class PresenterImpls implements IPresenter {

    private ModelImpls modelImpls;
    private IView iView;

    public PresenterImpls(IView iView) {
        this.iView = iView;
        modelImpls = new ModelImpls();
    }

    @Override
    public void startRequest(String url) {
        modelImpls.getData(url, new MyCallBack() {
            @Override
            public void success(Object d) {
                iView.setSuccess(d);
            }

            @Override
            public void error(Object e) {
                iView.setError(e);
            }
        });
    }

    private void sets(){
        if(modelImpls != null){
            modelImpls = null;
        }
        if (iView != null){
            iView = null;
        }
    }
}
