package com.flag.myapplication.car;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myweb.util.StrUtil;
import com.flag.myapplication.car.bean.User;
import com.flag.myapplication.hotel.R;

import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {

    @ViewInject(R.id.tv_login)
    TextView tv_login;
    @ViewInject(R.id.et_phone)
    EditText et_phone;
    @ViewInject(R.id.et_pwd)
    EditText et_pwd;
    @ViewInject(R.id.et_pwd2)
    EditText et_pwd2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        x.view().inject(RegisterActivity.this);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempPhone = et_phone.getText().toString().trim();
                String tempPwd = et_pwd.getText().toString().trim();

                if (StrUtil.isEmpty(et_phone.getText().toString())) {
                    Toast.makeText(myContext,"Please enter an account", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (StrUtil.isEmpty(et_pwd.getText().toString())) {
                    Toast.makeText(myContext,"Please input a password", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (StrUtil.isEmpty(et_pwd2.getText().toString())) {
                    Toast.makeText(myContext,"Please enter the password again", Toast.LENGTH_SHORT).show();

                    return;
                }


                if (!et_pwd.getText().toString().equals(et_pwd2.getText().toString())) {
                    Toast.makeText(myContext,"The two passwords do not match", Toast.LENGTH_SHORT).show();

                    return;
                }

                User user=new User();
                user.setUsername(et_phone.getText().toString());
                user.setPassword(et_pwd .getText().toString());
                user.setName(et_phone.getText().toString());

                loginhttpbend(user);


            }
        });
    }

    public void loginhttpbend(User user)
    {
        try {
            db.save(user);
        } catch (DbException e) {
            e.printStackTrace();
        }

        Intent intent=new Intent(myContext, LoginActivity.class);
        startActivity(intent);

        finish();

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
