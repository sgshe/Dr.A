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
import com.flag.myapplication.car.bean.Jiaotong;
import com.flag.myapplication.hotel.R;

import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_addjiaotong)
public class AddjiaotongActivity extends BaseActivity implements View.OnClickListener {



      @ViewInject(R.id.time)
    EditText time;
      @ViewInject(R.id.newcar)
    EditText newcar;

      @ViewInject(R.id.oldcar)
    EditText oldcar;

    @ViewInject(R.id.carshi)
    EditText carshi;

    @ViewInject(R.id.chujing)
    EditText chujing;


    @ViewInject(R.id.beizhu)
    EditText beizhu;





      @ViewInject(R.id.commotbutton)
    Button commotbutton;
      @ViewInject(R.id.text)
    TextView text;



      @ViewInject(R.id.backimage)
    ImageView backimage;




    Jiaotong shipinbean;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        x.view().inject(AddjiaotongActivity.this);
        super.onCreate(savedInstanceState);
        shipinbean= (Jiaotong) getIntent().getSerializableExtra("data");

        initData();
        initListener();

    }

    protected void initData() {
        backimage.setVisibility(View.VISIBLE);

        commotbutton.setOnClickListener(this);
        text.setText("create data");

        if(shipinbean!=null)
        {
            time.setText(shipinbean.getTime()+"");
            newcar.setText(shipinbean.getNewcar()+"");
            oldcar.setText(shipinbean.getOldcar()+"");
            carshi.setText(shipinbean.getCarshi()+"");
            chujing.setText(shipinbean.getChujing()+"");
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
            String newcarst = (newcar.getText()+"").trim();
            String oldcarst = (oldcar.getText()+"").trim();
            String carshist = (carshi.getText()+"").trim();
            String chujingst = (chujing.getText()+"").trim();
            String beizhust = (beizhu.getText()+"").trim();


            //判断输入框

            if (StrUtil.isEmpty(timest))
            {
                Toast.makeText(AddjiaotongActivity.this,"Please enter content", Toast.LENGTH_SHORT).show();
                return;
            }

            if (StrUtil.isEmpty(newcarst))
            {
                Toast.makeText(AddjiaotongActivity.this,"Please enter content", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StrUtil.isEmpty(oldcarst))
            {
                Toast.makeText(AddjiaotongActivity.this,"Please enter content", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StrUtil.isEmpty(carshist))
            {
                Toast.makeText(AddjiaotongActivity.this,"Please enter content", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StrUtil.isEmpty(chujingst))
            {
                Toast.makeText(AddjiaotongActivity.this,"Please enter content", Toast.LENGTH_SHORT).show();
                return;
            }



            if(shipinbean==null)
            {
                shipinbean=new Jiaotong();
            }

            shipinbean.setTime(timest);
            shipinbean.setNewcar(newcarst);
            shipinbean.setOldcar(oldcar.getText()+"");
            shipinbean.setCarshi(carshist+"");
            shipinbean.setChujing(chujingst);
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
