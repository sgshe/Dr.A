package com.flag.myapplication.car;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myweb.StrUtil;
import com.example.myweb.util.Xutils;
import com.flag.myapplication.car.bean.User;
import com.flag.myapplication.car.utils.GlideUtils;
import com.flag.myapplication.hotel.R;

import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @ViewInject(R.id.tv_regi)
    TextView tv_regi;
    @ViewInject(R.id.tv_login)
    TextView tv_login;
    @ViewInject(R.id.et_phone)
    EditText et_phone;
    @ViewInject(R.id.et_pwd)
    EditText et_pwd;

    @ViewInject(R.id.touxiang)
    ImageView touxiang;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        x.view().inject(LoginActivity.this);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initData() {

        GlideUtils.getInstance().intoRoundImageView("111",touxiang,50);

        try {
            User user=  db.selector(User.class).where("zhuangt","=",1).findFirst();
            if(user!=null)
            {
                et_phone.setText(user.getUsername());
                et_pwd.setText(user.getPassword());
                GlideUtils.getInstance().intoRoundImageView(user.getImg(),touxiang,50);



            }

        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initListener() {

        tv_regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//

                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempPhone = et_phone.getText().toString().trim();
                String tempPwd = et_pwd.getText().toString().trim();
                if (StrUtil.isEmpty(tempPhone) || StrUtil.isEmpty(tempPwd)) {
                    Toast.makeText(myContext,"Please enter content", Toast.LENGTH_SHORT).show();
                    return;
                }

//                startActivity(new Intent(myContext, MainActivity.class));
//                finish();
                loginhttpbendi( tempPhone , tempPwd);


            }
        });
    }



    public void loginhttpbendi(String username ,String password)
    {
        try {
            User li33st= Xutils.initDbConfiginit().selector(User.class).where("username","=" ,username)
                    .and("password","=",password).findFirst();
            if(li33st!=null)
            {
                li33st.setZhuangt(1);
                Xutils.initDbConfiginit().update(li33st);
                Intent intent=new Intent(myContext, MainActivity.class);
                intent.putExtra("user",li33st);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(myContext,"error", Toast.LENGTH_SHORT).show();
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }






    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
//    Map<String,Object> map = new HashMap<>();
//map.put("pageNumber",page);
//map.put("typeid",typeid);
////如果请求不需要参数,传null
//// GetDataTask.post("app/types", null, new GetDataTask.GetDataCallback(){}
//GetDataTask.post("app/types", map, new GetDataTask.GetDataCallback() {
//        @Override
//        public void success(String response) {
//            Gson gson = new Gson(); //后台返回来的json格式，其他格式自己处理
//            Result result = gson.fromJson(response, Result.class);
//        }
//        @Override
//        public void failed(String... args) {
//        }
//    });

}
