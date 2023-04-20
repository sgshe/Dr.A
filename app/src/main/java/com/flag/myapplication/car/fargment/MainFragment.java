package com.flag.myapplication.car.fargment;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweb.util.Xutils;
import com.flag.myapplication.car.AddimageActivity;
import com.flag.myapplication.car.TieziinifActivity;
import com.flag.myapplication.car.adapter.JiliuAdapter;
import com.flag.myapplication.car.bean.Tiezi;
import com.flag.myapplication.car.bean.User;
import com.flag.myapplication.hotel.R;

import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {
    private static User userbean;
    public Context myContext;
     TextView text,nodate;
    ImageView add;
    private RecyclerView recycler_view;

    List<Tiezi> danzis=new ArrayList<>();
    JiliuAdapter adapter;
    EditText edsele;
    Button selebt;

    String titlelist;
    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, User user) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        userbean=user;
        args.putString("title", param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            titlelist = getArguments().getString("title");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        text=view.findViewById(R.id.text);
         add=view.findViewById(R.id.add);
        nodate=view.findViewById(R.id.nodate);
        edsele=view.findViewById(R.id.edsele);
        selebt=view.findViewById(R.id.selebt);

        recycler_view=view.findViewById(R.id.recycler_view);
        recycler_view.setOverScrollMode(View.OVER_SCROLL_NEVER);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycler_view.setLayoutManager(layoutManager);

        selebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleall();
            }
        });


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        initdata ();


    }

    public void   initdata ()
    {

        add.setVisibility(View.VISIBLE);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(myContext, AddimageActivity.class),1);

            }
        });

        myContext=getActivity();
        text.setText(titlelist);




        seleall();
    }

    public void seleall()
    {
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);//这两行是收起软键盘
        imm.hideSoftInputFromWindow(edsele.getWindowToken(), 0);
        danzis.clear();
        String string=edsele.getText()+"";
        string=string+"";
        String typenamestirng="";


        try {
            danzis.addAll(Xutils.initDbConfiginit().selector(Tiezi.class).where("titile","like","%"+string+"%").findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(danzis.size()==0)
            {
                nodate.setVisibility(View.VISIBLE);
            }
        else
        {
            nodate.setVisibility(View.GONE);

        }

        adapter = new JiliuAdapter(false,userbean,getActivity(), danzis, new JiliuAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {

                Intent intent=new Intent(myContext, TieziinifActivity.class);
                intent.putExtra("data",danzis.get(pos));
                startActivity(intent);

            }

            @Override
            public void ondele(int pos)
            {
            }

            @Override
            public void onupdata(int pos) {


            }

            @Override
            public void dianzan(int pos, TextView textView) {

                danzis.get(pos).setZan(danzis.get(pos).getZan()+1);
                try {
                    Xutils.initDbConfiginit().update(danzis.get(pos));
                } catch (DbException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }
        });
        recycler_view.setAdapter(adapter);

    }





}
