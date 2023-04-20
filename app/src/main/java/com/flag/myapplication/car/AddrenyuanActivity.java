package com.flag.myapplication.car;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myweb.StrUtil;
import com.example.myweb.util.Xutils;
import com.flag.myapplication.car.bean.Renyuan;
import com.flag.myapplication.hotel.R;

import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_addrenuan)
public class AddrenyuanActivity extends BaseActivity implements View.OnClickListener {



      @ViewInject(R.id.time)
    EditText time;
      @ViewInject(R.id.chengxiang)
    EditText chengxiang;

      @ViewInject(R.id.sexbiz)
    EditText sexbiz;

    @ViewInject(R.id.xueli)
    EditText xueli;

    @ViewInject(R.id.zongshuo)
    EditText zongshuo;


    @ViewInject(R.id.beizhu)
    EditText beizhu;





      @ViewInject(R.id.commotbutton)
    Button commotbutton;
      @ViewInject(R.id.text)
    TextView text;



      @ViewInject(R.id.backimage)
    ImageView backimage;




    Renyuan shipinbean;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        x.view().inject(AddrenyuanActivity.this);
        super.onCreate(savedInstanceState);
        shipinbean= (Renyuan) getIntent().getSerializableExtra("data");

        initData();
        initListener();

    }

    protected void initData() {
        backimage.setVisibility(View.VISIBLE);

        commotbutton.setOnClickListener(this);
        text.setText("Data analyse");

        if(shipinbean!=null)
        {
            time.setText(shipinbean.getTime()+"");
            xueli.setText(shipinbean.getXueli()+"");
            zongshuo.setText(shipinbean.getZongliang()+"");
            chengxiang.setText(shipinbean.getChengxiang()+"");
            sexbiz.setText(shipinbean.getSexbiz()+"");
            beizhu.setText(shipinbean.getBeizhu()+"");
        }





    }

    protected void initListener() {
        backimage.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.commotbutton)
        {
            ///Submit

            String timest = (time.getText()+"").trim();
            String sexbizst = (sexbiz.getText()+"").trim();
            String xuelist = (xueli.getText()+"").trim();
            String chengxiangst = (chengxiang.getText()+"").trim();
            String zongshuost = (zongshuo.getText()+"").trim();
            String beizhust = (beizhu.getText()+"").trim();


            //判断输入框

            if (StrUtil.isEmpty(timest))
            {
                Toast.makeText(AddrenyuanActivity.this,"Please enter content", Toast.LENGTH_SHORT).show();
                return;
            }

            if (StrUtil.isEmpty(zongshuost))
            {
                Toast.makeText(AddrenyuanActivity.this,"Please enter content", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StrUtil.isEmpty(xuelist))
            {
                Toast.makeText(AddrenyuanActivity.this,"Please enter content", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StrUtil.isEmpty(chengxiangst))
            {
                Toast.makeText(AddrenyuanActivity.this,"Please enter content", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StrUtil.isEmpty(sexbizst))
            {
                Toast.makeText(AddrenyuanActivity.this,"Please enter content", Toast.LENGTH_SHORT).show();
                return;
            }



            if(shipinbean==null)
            {
                shipinbean=new Renyuan();
            }

            shipinbean.setTime(timest);
            shipinbean.setSexbiz(sexbizst);
            shipinbean.setXueli(xueli.getText()+"");
            shipinbean.setZongliang(zongshuost+"");
            shipinbean.setChengxiang(chengxiangst);
            shipinbean.setUserid(user.getId());




            try {
                Xutils.initDbConfiginit().saveOrUpdate(shipinbean);
            } catch (DbException e) {
                e.printStackTrace();
            }
            finish();



        }

        else if (v.getId()==R.id.backimage)
        {
            finish();
        }


    }







    /***
     * Stop location service
     */
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub

        super.onStop();
    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();



    }







}
