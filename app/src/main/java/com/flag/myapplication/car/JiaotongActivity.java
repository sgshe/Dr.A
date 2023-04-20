package com.flag.myapplication.car;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ImageUtils;
import com.example.myweb.util.Xutils;
import com.flag.myapplication.car.bean.Jiaotong;
import com.flag.myapplication.car.bean.User;
import com.flag.myapplication.car.utils.FileUtil;
import com.flag.myapplication.car.utils.MyMarkerView;
import com.flag.myapplication.car.utils.UriUtil;
import com.flag.myapplication.hotel.R;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.YLabels;
import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.bean.Permissions;
import com.qw.soul.permission.callbcak.CheckRequestPermissionsListener;

import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JiaotongActivity extends BaseActivity {

    private static User userbean;
    public Context myContext;
    private TextView text;
    ListView list;
    String titlelist;
    List<Jiaotong> dataBeans=new ArrayList<>();

    int baishuiint=0;
    int keleshuiint=0;

    int naichashuiint=0;
    int niunaishuiint=0;


    LinearLayout viewtub;

    private LineChart chartTall;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiaotong);
        list=findViewById(R.id.list);
        text=findViewById(R.id.text);
        chartTall=findViewById(R.id.chartTall);
        viewtub=findViewById(R.id.viewtub);


        text.setText("Data analyse");

        findViewById(R.id.add).setVisibility(View.VISIBLE);

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(JiaotongActivity.this,AddjiaotongActivity.class));

            }
        });
        findViewById(R.id.daoru).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

                intent.setType("*/*");

                intent.addCategory(Intent.CATEGORY_OPENABLE);

                startActivityForResult(Intent.createChooser(intent,"需要选择文件"),1);



            }
        });



        findViewById(R.id.tv_jiaoliu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SoulPermission.getInstance().checkAndRequestPermissions(
                        Permissions.build(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        new CheckRequestPermissionsListener() {
                            @Override
                            public void onAllPermissionOk(Permission[] allPermissions) {
                                Bitmap bitmap = ImageUtils.view2Bitmap(viewtub);
                                if (bitmap != null) {
                                    ImageUtils.save2Album(bitmap, Bitmap.CompressFormat.JPEG);
                                    //toast("生成海报成功");
                                    startActivity(new Intent(JiaotongActivity.this,AddimageActivity.class));
                                }
                            }

                            @Override
                            public void onPermissionDenied(Permission[] refusedPermissions) {
                                //toast("拒绝此权限将不能exchange");
                            }
                        });

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {


            Uri uri = data.getData();

            String pathString = UriUtil.uriToFileApiQ(this,uri);

         //   filePathtextView.setText(pathString);
            ArrayList<Map<String,String>> arrayList=   FileUtil.readliaotian(JiaotongActivity.this,pathString);


            if(arrayList!=null)
            {
                for (int i=0;i<arrayList.size();i++)
                {
                    Jiaotong   oneoldbean=new Jiaotong();
                    oneoldbean.setChujing(arrayList.get(i).get("1")+"");
                    oneoldbean.setOldcar(arrayList.get(i).get("2")+"");
                    oneoldbean.setCarshi(arrayList.get(i).get("3")+"");
                    oneoldbean.setChujing(arrayList.get(i).get("4")+"");
                    oneoldbean.setUserid(user.getId());
                    oneoldbean.setTime((i+1)+"");


                    try {
                        Xutils.initDbConfiginit().save(oneoldbean);
                    } catch (DbException e) {
                        throw new RuntimeException(e);
                    }
                }

            }


            setType();
        }

    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


    @Override
    public void onResume() {
        super.onResume();
        initdata ();

        setType();
    }

    public void   initdata ()
    {
        dataBeans.clear();
        try {
            dataBeans.addAll(db.selector(Jiaotong.class).where("userid","=",user.getId()).findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(dataBeans!=null)
        {
            list.setAdapter(mydapter);
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(JiaotongActivity.this,AddjiaotongActivity.class).putExtra("data",dataBeans.get(position)));

            }
        });



    }




    BaseAdapter mydapter=new BaseAdapter() {
        @Override
        public int getCount() {
            return dataBeans.size();
        }

        @Override
        public Object getItem(int position) {
            return dataBeans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return dataBeans.size();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView=   LayoutInflater.from(JiaotongActivity.this).inflate(R.layout.jiaotong_item,parent,false);
            TextView name= (TextView) convertView.findViewById(R.id.title);
            TextView time= (TextView) convertView.findViewById(R.id.time);
            name.setText("Data 1:"+dataBeans.get(position).getNewcar()+"       Data 2:"+dataBeans.get(position).getOldcar()+
                    "\nData 3:"+dataBeans.get(position).getCarshi()+"          Data 4:"+dataBeans.get(position).getChujing());
            time.setText("Time："+dataBeans.get(position).getTime());

            convertView.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Xutils.initDbConfiginit().delete(dataBeans.get(position));
                        dataBeans.remove(position);
                        notifyDataSetChanged();
                        setType();
                    } catch (DbException e) {
                        throw new RuntimeException(e);
                    }
                }
            });


            return convertView;
        }
    };




    private void setType() {
        // 设置在Y轴上是否是从0开始显示
        chartTall.setStartAtZero(true);
        //是否在Y轴显示数据，就是曲线上的数据
        chartTall.setDrawYValues(true);
        //设置网格
        chartTall.setDrawBorder(true);
        chartTall.setBorderPositions(new BarLineChartBase.BorderPosition[] {
                BarLineChartBase.BorderPosition.BOTTOM});
        //在chart上的右下角加描述
        chartTall.setDescription("Data Analysis");
        //设置Y轴上的单位
        chartTall.setUnit("");
        //设置透明度
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            chartTall.setAlpha(0.8f);
        }
        //设置网格底下的那条线的颜色
        chartTall.setBorderColor(Color.rgb(213, 216, 214));
        //设置Y轴前后倒置
        chartTall.setInvertYAxisEnabled(false);
        //设置高亮显示
        chartTall.setHighlightEnabled(true);
        //设置是否可以触摸，如为false，则不能拖动，缩放等
        chartTall.setTouchEnabled(true);
        //设置是否可以拖拽，缩放
        chartTall.setDragEnabled(true);
        chartTall.setScaleEnabled(true);
        //设置是否能扩大扩小
        chartTall.setPinchZoom(true);
        //设置点击chart图对应的数据弹出标注
        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        mv.setOffsets(-mv.getMeasuredWidth() / 2, -mv.getMeasuredHeight());
        chartTall.setMarkerView(mv);
        chartTall.setHighlightIndicatorEnabled(false);
        //设置字体格式，如正楷
//        Typeface tf = Typeface.createFromAsset(getAssets(),
//                "OpenSans-Regular.ttf");
//        chartTall.setValueTypeface(tf);

        XLabels xl = chartTall.getXLabels();
        xl.setPosition(XLabels.XLabelPosition.BOTTOM); // 设置X轴的数据在底部显示
        // xl.setTypeface(tf); // 设置字体
        xl.setTextSize(10f); // 设置字体大小
        xl.setSpaceBetweenLabels(3); // 设置数据之间的间距

        YLabels yl = chartTall.getYLabels();
        // yl.setTypeface(tf); // 设置字体
        yl.setTextSize(10f); // s设置字体大小
        yl.setLabelCount(5); // 设置Y轴最多显示的数据个数
        // 加载数据
        setData();
        //从X轴进入的动画
//        chartTall.animateX(4000);
//        chartTall.animateY(3000);   //从Y轴进入的动画
       // chartTall.animateXY(3000, 3000);    //从XY轴一起进入的动画
//
//        //设置最小的缩放
//        chartTall.setScaleMinima(0.5f, 1f);

    }

    private void setData() {

        String[] timest = new String[dataBeans.size()];
        String[] newcarst =new String[dataBeans.size()];

        String[] oldcarst =new String[dataBeans.size()];

        String[] carshi =new String[dataBeans.size()];

        String[] chujing = new String[dataBeans.size()];


        for (int index=0;index<dataBeans.size();index++)
        {
            timest[index]=dataBeans.get(index).getTime();
            newcarst[index]=dataBeans.get(index).getNewcar();
            oldcarst[index]=dataBeans.get(index).getOldcar();
            carshi[index]=dataBeans.get(index).getCarshi();
            chujing[index]=dataBeans.get(index).getChujing();


        }



        LineData data=new LineData(timest,setLine(timest,newcarst,1,"newcar"));    //创建LineData实体类并添加第一条曲线
        data.addDataSet(setLine(timest,oldcarst,2,"Scrapped vehicle"));      //添加第二条曲线
        data.addDataSet(setLine(timest,carshi,3,"malfunction"));      //添加第二条曲线
        data.addDataSet(setLine(timest,chujing,4,"Alert"));      //添加第二条曲线

        chartTall.setData(data);







    }




    //画线
    private LineDataSet setLine(String[] babAge, String[] Tall, int flag, String name) {
        ArrayList<String> xValsAge = new ArrayList<String>();
        for (int i = 0; i < babAge.length; i++) {
            xValsAge.add(babAge[i]);
        }
        ArrayList<Entry> yValsBabyTall = new ArrayList<Entry>();
        for (int i = 0; i < Tall.length; i++) {
            yValsBabyTall.add(new Entry(Float.parseFloat(Tall[i]), i));
        }
        //设置baby的成长曲线
        LineDataSet setData = new LineDataSet(yValsBabyTall,name);
        setData.setDrawCubic(true);  //设置曲线为圆滑的线
        setData.setCubicIntensity(0.2f);
        setData.setDrawFilled(false);  //设置包括的范围区域填充颜色
        setData.setDrawCircles(true);  //设置有圆点
        setData.setLineWidth(2f);    //设置线的宽度
        setData.setCircleSize(5f);   //设置小圆的大小
        setData.setHighLightColor(Color.rgb(244, 117, 117));
        //设置曲线颜色
        if(flag==1)
            setData.setColor(Color.rgb(104, 241, 175));
        else if(flag==2)
            setData.setColor(Color.rgb(255, 0, 0));
        else if(flag==3)
            setData.setColor(Color.rgb(255, 152, 0));
        else if(flag==4)
            setData.setColor(Color.rgb(76, 176, 80));
        return setData;    //返回曲线
    }






}
