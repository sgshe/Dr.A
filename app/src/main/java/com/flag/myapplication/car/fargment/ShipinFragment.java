package com.flag.myapplication.car.fargment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myweb.util.StrUtil;
import com.example.myweb.util.Xutils;
import com.flag.myapplication.car.JiaotongActivity;
import com.flag.myapplication.car.RenyuanActivity;
import com.flag.myapplication.car.WeatherActivity;
import com.flag.myapplication.car.bean.Tiezi;
import com.flag.myapplication.car.bean.User;
import com.flag.myapplication.car.utils.GlideUtils;
import com.flag.myapplication.hotel.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;


public class ShipinFragment extends Fragment {
    private static User userbean;
    public Context myContext;
     TextView text,nodate;
    ListView listView;

    List<Tiezi> tieziList ;
    String[] titlearray={"Create data 1","Create data 2","Create data 3"};
    Banner banner;

    int[] fengmian={R.mipmap.a1,R.mipmap.a2,R.mipmap.a3};

    String titlelist;
    public ShipinFragment() {
        // Required empty public constructor
    }

    public static ShipinFragment newInstance(String param1, User user) {
        ShipinFragment fragment = new ShipinFragment();
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
        View view = inflater.inflate(R.layout.fragment_shipin, container, false);
        text=view.findViewById(R.id.text);
        nodate=view.findViewById(R.id.nodate);
        banner=view.findViewById(R.id.banner);



        listView=view.findViewById(R.id.list);

        initBanner();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0)
                {
                    Intent intent=new Intent(myContext, JiaotongActivity.class);
                    startActivity(intent);
                }
                else  if(position==1)
                {
                    Intent intent=new Intent(myContext, WeatherActivity.class);
                    startActivity(intent);
                } else  if(position==2)
                {
                    Intent intent=new Intent(myContext, RenyuanActivity.class);
                    startActivity(intent);
                }



            }
        });



        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        initdata ();


    }


    /**
     * 轮播图
     */
    private void initBanner() {


        try {
           tieziList=Xutils.initDbConfiginit().selector(Tiezi.class).orderBy("zan").findAll();
        } catch (DbException e) {
            throw new RuntimeException(e);
        };

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new FresscoImageLoader());
        //设置banner动画效果
//        banner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播Time
        banner.setDelayTime(3000);
        //设置允许手势滑动
        banner.setViewPagerIsScroll(true);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

        List list_banner = new ArrayList();


        if(tieziList!=null&&tieziList.size()>0)
        {

            for (int i=0;i<tieziList.size();i++)
            {
                if(list_banner.size()==5)
                {
                    break;
                }
                for (int a=0;a<tieziList.get(i).getImgurl().split(",").length;a++)
                {
                    if(list_banner.size()==5)
                    {
                        break;
                    }

                    if(!StrUtil.isEmpty(tieziList.get(i).getImgurl().split(",")[a]))
                    {
                        list_banner.add(tieziList.get(i).getImgurl().split(",")[a]);
                    }
                }
            }

        }
        else
        {
            //list_banner.add(R.mipmap.a);
            list_banner.add(R.mipmap.b);
            list_banner.add(R.mipmap.c);

        }

        banner.setImages(list_banner);

        //banner设置方法全部调用完毕时最后调用
        banner.start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
    }
    public class FresscoImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            if(tieziList!=null&&tieziList.size()>0)
            {
                GlideUtils.getInstance().intoRoundImageView((String) path, imageView,0);
            }
            else
            {
                int url = (int) path;
                imageView.setImageResource(url);

            }


        }

        //提供createImageView 方法，方便fresco自定义ImageView
        @Override
        public ImageView createImageView(Context context) {

            ImageView imageView=new ImageView(getActivity());
            return imageView;
        }
    }
    public void   initdata ()
    {



        myContext=getActivity();
        text.setText(titlelist);




        seleall();
    }

    public void seleall()
    {


            listView.setAdapter(mydapter);
    }



    BaseAdapter mydapter=new BaseAdapter() {
        @Override
        public int getCount() {
            return fengmian.length;
        }

        @Override
        public Object getItem(int position) {
            return fengmian.length;
        }

        @Override
        public long getItemId(int position) {
            return fengmian.length;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {

            view= LayoutInflater.from(getActivity()).inflate(R.layout.item2,parent,false);
            TextView time= (TextView) view.findViewById(R.id.time);
            TextView title= (TextView) view.findViewById(R.id.title);
            TextView update= (TextView) view.findViewById(R.id.update);
            ImageView imageView= (ImageView) view.findViewById(R.id.image);


            imageView.setImageResource(fengmian[position]);

            title.setText(titlearray[position]);

            time.setVisibility(View.GONE);

            update.setVisibility(View.GONE);
            return view;
        }
    };



}
