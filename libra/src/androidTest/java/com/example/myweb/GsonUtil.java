package com.example.myweb;

import android.app.Activity;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by seven on 2017/3/11.
 */

public class GsonUtil {
    private static Gson gson;

    //是否合法json
    public static boolean isRightJson(Activity activity,String t){
        boolean isRight =false;
        try {
            JSONObject jo = new JSONObject(t);
            if (jo.getString("status").equals("0"))
                isRight =true;
            else
                ToastUtil.showToast(activity,"json不合法");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return isRight;
    }

    public static Gson getInstance(){
        if(null==gson){
            return new Gson();
        }
        return gson;
    }
}
