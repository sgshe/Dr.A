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
import com.flag.myapplication.car.bean.Weather;
import com.flag.myapplication.hotel.R;

import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_addweather)
public class AddweatherActivity extends BaseActivity implements View.OnClickListener {



      @ViewInject(R.id.time)
    EditText time;
      @ViewInject(R.id.wendu)
    EditText wendu;

      @ViewInject(R.id.jiangshui)
    EditText jiangshui;

    @ViewInject(R.id.fengli)
    EditText fengli;

    @ViewInject(R.id.shidu)
    EditText shidu;


    @ViewInject(R.id.beizhu)
    EditText beizhu;





      @ViewInject(R.id.commotbutton)
    Button commotbutton;
      @ViewInject(R.id.text)
    TextView text;



      @ViewInject(R.id.backimage)
    ImageView backimage;




    Weather shipinbean;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        x.view().inject(AddweatherActivity.this);
        super.onCreate(savedInstanceState);
        shipinbean= (Weather) getIntent().getSerializableExtra("data");

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
            wendu.setText(shipinbean.getWendu()+"");
            shidu.setText(shipinbean.getShidu()+"");
            fengli.setText(shipinbean.getFengli()+"");
            jiangshui.setText(shipinbean.getJiangshuiliang()+"");
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
            String wendust = (wendu.getText()+"").trim();
            String shidust = (shidu.getText()+"").trim();
            String fenglist = (fengli.getText()+"").trim();
            String jiangshuist = (jiangshui.getText()+"").trim();
            String beizhust = (beizhu.getText()+"").trim();


            //判断输入框

            if (StrUtil.isEmpty(timest))
            {
                Toast.makeText(AddweatherActivity.this,"Please enter content", Toast.LENGTH_SHORT).show();
                return;
            }

            if (StrUtil.isEmpty(wendust))
            {
                Toast.makeText(AddweatherActivity.this,"Please enter content", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StrUtil.isEmpty(shidust))
            {
                Toast.makeText(AddweatherActivity.this,"Please enter content", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StrUtil.isEmpty(fenglist))
            {
                Toast.makeText(AddweatherActivity.this,"Please enter content", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StrUtil.isEmpty(jiangshuist))
            {
                Toast.makeText(AddweatherActivity.this,"Please enter content", Toast.LENGTH_SHORT).show();
                return;
            }



            if(shipinbean==null)
            {
                shipinbean=new Weather();
            }

            shipinbean.setTime(timest);
            shipinbean.setWendu(wendust);
            shipinbean.setShidu(shidu.getText()+"");
            shipinbean.setFengli(fenglist+"");
            shipinbean.setJiangshuiliang(jiangshuist);
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
