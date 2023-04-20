package com.flag.myapplication.car;

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
public class UpdateuserinfoActivity extends BaseActivity {

    @ViewInject(R.id.tv_login)
    TextView tv_login;
    @ViewInject(R.id.et_phone)
    EditText et_phone;
    @ViewInject(R.id.et_pwd)
    EditText et_pwd;
    @ViewInject(R.id.et_pwd2)
    EditText et_pwd2;

    @ViewInject(R.id.text)
    TextView text;




    User data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        x.view().inject(UpdateuserinfoActivity.this);
        super.onCreate(savedInstanceState);
        data= (User) getIntent().getSerializableExtra("data");
        text.setText("update");
        if(data!=null)
        {
            et_phone.setText(data.getUsername());
            et_pwd.setText(data.getPassword());
            et_pwd2.setText(data.getPassword());

        }

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

                data.setUsername(et_phone.getText().toString());
                data.setPassword(et_pwd .getText().toString());

                loginhttpbend(data);


            }
        });
    }

    public void loginhttpbend(User user)
    {
        try {
            db.update(user);
        } catch (DbException e) {
            e.printStackTrace();
        }


        finish();

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
