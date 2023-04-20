package com.flag.myapplication.car.fargment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.flag.myapplication.car.bean.User;
import com.flag.myapplication.hotel.R;


public class LiaotianFragment extends Fragment implements View.OnClickListener {

    TextView name,totype,touser,gereni;
    Button tuichu;
    static  User useraa;
    public LiaotianFragment() {
        // Required empty public constructor
    }


    public static LiaotianFragment newInstance(String param1, User user
    ) {
        LiaotianFragment fragment = new LiaotianFragment();
        Bundle args = new Bundle();
        useraa=user;
        fragment.setArguments(args);
        return fragment;
    }
    public static LiaotianFragment newInstance(int id) {
        LiaotianFragment fragment = new LiaotianFragment();
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
        View view = inflater.inflate(R.layout.fragment_liaotian, container, false);


        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
