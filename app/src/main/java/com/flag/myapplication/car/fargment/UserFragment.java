package com.flag.myapplication.car.fargment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myweb.util.Xutils;
import com.flag.myapplication.car.LoginActivity;
import com.flag.myapplication.car.UpdateuserActivity;
import com.flag.myapplication.car.UpdateuserinfoActivity;
import com.flag.myapplication.car.bean.User;
import com.flag.myapplication.hotel.R;

import org.xutils.ex.DbException;


public class UserFragment extends Fragment implements View.OnClickListener {

    TextView name,totype,touser,gereni;
    Button tuichu;
    static  User useraa;
    public UserFragment() {
        // Required empty public constructor
    }


    public static UserFragment newInstance(String param1,    User user
    ) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        useraa=user;
        fragment.setArguments(args);
        return fragment;
    }
    public static UserFragment newInstance(int id) {
        UserFragment fragment = new UserFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        name=view.findViewById(R.id.name);

        tuichu=view.findViewById(R.id.tuichu);

        totype=view.findViewById(R.id.totype);
        gereni=view.findViewById(R.id.gereni);



        touser=view.findViewById(R.id.touser);
        initdata ();

        return view;
    }
    public void initdata()
    {

        tuichu.setOnClickListener(this);
        touser.setOnClickListener(this);
        totype.setOnClickListener(this);
        gereni.setOnClickListener(this);
        try {
           User user= Xutils.initDbConfiginit().selector(User.class).where("zhuangt","=","1").findFirst();
            if(user!=null)
            {
                name.setText(user.getUsername()+"");
            }
        } catch (DbException e) {
            e.printStackTrace();
        }


    }



    @Override
    public void onClick(View v) {
 if(v.getId()==R.id.tuichu)
        {

            try {
                useraa.setZhuangt(0);
                Xutils.initDbConfiginit().update(useraa);
            } catch (DbException e) {
                e.printStackTrace();
            }
            getActivity().finish();

            startActivity(new Intent(getActivity(), LoginActivity.class));
        }

 else  if(v.getId()==R.id.touser)
 {
     startActivity(new Intent(getActivity(), UpdateuserinfoActivity.class).putExtra("data",useraa));
 }
 else  if(v.getId()==R.id.gereni)
 {
     startActivity(new Intent(getActivity(), UpdateuserActivity .class).putExtra("data",useraa));
 }
    }
    @Override
    public void onResume() {
        super.onResume();
        try {
            useraa= Xutils.initDbConfiginit().selector(User.class).where("zhuangt","=","1").findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
