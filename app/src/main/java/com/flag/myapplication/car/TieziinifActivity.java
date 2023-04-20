package com.flag.myapplication.car;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myweb.StrUtil;
import com.example.myweb.util.Xutils;
import com.flag.myapplication.car.bean.Pingjia;
import com.flag.myapplication.car.bean.Tiezi;
import com.flag.myapplication.car.bean.User;
import com.flag.myapplication.car.utils.GlideUtils;
import com.flag.myapplication.hotel.R;

import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ContentView(R.layout.activity_tzinif)
public class TieziinifActivity extends BaseActivity  {

    @ViewInject(R.id.text)
    TextView text;

    @ViewInject(R.id.title)
    TextView title;

    @ViewInject(R.id.time)
    TextView time;

    @ViewInject(R.id.xinwenlistview)
    ListView xinwenlistview;

    @ViewInject(R.id.edliuyan)
    EditText edliuyan;

    @ViewInject(R.id.send)
    Button send;



    Tiezi tiziben;
    View xinwentop;
    List<Pingjia> liulans=new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        x.view().inject(TieziinifActivity.this);
        super.onCreate(savedInstanceState);

    }
    @Override
    protected void initData() {
        tiziben= (Tiezi) getIntent().getSerializableExtra("data");


        try {
            user=  Xutils.initDbConfiginit().selector(User.class).where("zhuangt","=",1).findFirst();

        } catch (DbException e) {
            e.printStackTrace();
        }

        if(tiziben!=null)
        {
            xinwentop= LayoutInflater.from(TieziinifActivity.this).inflate(R.layout.xinwentop,null,false);
            TextView neirong=xinwentop.findViewById(R.id.neirong);
            LinearLayout imageviewlayout=xinwentop.findViewById(R.id.imageviewlayout);
//            x.image().bind(imageview, new File(tiziben.getImgurl()).toURI().toString());
//            GlideUtils.getInstance().intoRoundImageView(imgarray[i],imageview,3);
//


            if(!StrUtil.isEmpty(tiziben.getImgurl()))
            {
                String[]imgarray=tiziben.getImgurl().split(",");
                imageviewlayout.removeAllViews();
                for (int i=0;i<imgarray.length;i++)
                {
                    if(!StrUtil.isEmpty(imgarray[i]))
                    {
                        ImageView squareImageView=new ImageView(TieziinifActivity.this);

                        squareImageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                        squareImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        squareImageView.setAdjustViewBounds(true);
                        squareImageView.setPadding(10,10,10,10);
                        GlideUtils.getInstance().intoImageView(imgarray[i],squareImageView);


                        imageviewlayout.addView(squareImageView);
                    }
                }

            }

            neirong.setText("        "+tiziben.getNeirong());
            text.setText("Detail");
            title.setText(tiziben.getTitile());
            time.setText(tiziben.getTime());
            xinwenlistview.addHeaderView(xinwentop);



            showliuyan();
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(StrUtil.isEmpty(edliuyan.getText()+""))
                    {
                        Toast.makeText(TieziinifActivity.this,"Please enter a message",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);//这两行是收起软键盘
                                     imm.hideSoftInputFromWindow(edliuyan.getWindowToken(), 0);
                        Pingjia liulan=new Pingjia();
                        liulan.setXinwenid(tiziben.getId());
                        liulan.setLiuyanneirong(edliuyan.getText()+"");
                        liulan.setUsername(user.getUsername());
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");// HH:mm:ss
//获取当前Time
                        Date date = new Date(System.currentTimeMillis());
                        liulan.setTime(simpleDateFormat.format(date));
                        try {
                            Xutils.initDbConfiginit().save(liulan);
                            tiziben.setPingjianum(tiziben.getPingjianum()+1);
                            Xutils.initDbConfiginit().update(tiziben);

                        } catch (DbException e) {
                            e.printStackTrace();
                        }

                        showliuyan();
                        edliuyan.setText("");

                    }
                }
            });

        }


    }


    public void showliuyan()
    {
        liulans.clear();
        try {
            liulans.addAll(Xutils.initDbConfiginit().selector(Pingjia.class).where("xinwenid","=",tiziben.getId()).findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        xinwenlistview.setAdapter(liuyanadapter);
    }

    BaseAdapter liuyanadapter=new BaseAdapter() {
        @Override
        public int getCount() {
            return liulans.size();
        }

        @Override
        public Object getItem(int position) {
            return liulans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return liulans.size();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            convertView= LayoutInflater.from(TieziinifActivity.this).inflate(R.layout.liuyan_item,parent,false);
            TextView neirong= (TextView) convertView.findViewById(R.id.neirong);
            final TextView dele= (TextView) convertView.findViewById(R.id.dele);
            TextView username= (TextView) convertView.findViewById(R.id.username);
            TextView time= (TextView) convertView.findViewById(R.id.time);




            neirong.setText(liulans.get(position).getLiuyanneirong());
            username.setText(liulans.get(position).getUsername());
            time.setText(liulans.get(position).getTime());
            dele.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        db.delete(liulans.get(position));
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                    liulans.remove(position);
                    liuyanadapter.notifyDataSetChanged();
                }
            });

            return convertView;
        }
    };

    @Override
    protected void initListener() {

    }














}
