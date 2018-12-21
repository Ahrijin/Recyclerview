package com.example.yuekao.model;

import android.os.AsyncTask;

import com.example.yuekao.bean.MyData;
import com.example.yuekao.callback.MyCallBack;
import com.example.yuekao.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

public class ModelImpls implements Model {
    private MyCallBack callBack;
    @Override
    public void getData(String url, MyCallBack callBack) {
        this.callBack = callBack;
        new MyTask().execute(url);
    }

    class MyTask extends AsyncTask<String,Void,MyData>{

        @Override
        protected MyData doInBackground(String... strings) {
            try {
                String jsonStr = OkHttpUtils.getInstance().get(strings[0]);
                Gson gson = new Gson();
                MyData myData = gson.fromJson(jsonStr, MyData.class);
                return myData;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(MyData myData) {
            super.onPostExecute(myData);
            callBack.success(myData);
        }
    }
}
