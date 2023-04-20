package com.flag.myapplication.car.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myweb.StrUtil;
import com.example.myweb.util.Xutils;
import com.flag.myapplication.car.bean.Tiezi;
import com.flag.myapplication.car.bean.User;
import com.flag.myapplication.car.utils.GlideUtils;
import com.flag.myapplication.car.utils.SquareImageView;
import com.flag.myapplication.hotel.R;

import org.xutils.ex.DbException;

import java.util.List;

public class JiliuAdapter extends RecyclerView.Adapter<JiliuAdapter.ViewHolder> {
    //内容列表适配器
    public List<Tiezi> lubeans;
    private OnItemClickListener mListener;
    public Context mContext;
    User user;
    boolean showdele;



    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView time,zhengwen,yonghu;
        ImageView delebutton,touxiang;
        TextView zantv;
        TextView pjtv;

        LinearLayout imglayout;

        View orderlayout;
        public ViewHolder(View view) {
            super(view);
            time = (TextView) view.findViewById(R.id.time);


            delebutton= view.findViewById(R.id.delebutton);

            orderlayout= view.findViewById(R.id.orderlayout);
            zhengwen = (TextView) view.findViewById(R.id.zhengwen);

            zantv = (TextView) view.findViewById(R.id.zantv);
            pjtv =  view.findViewById(R.id.pjtv);
            yonghu =  view.findViewById(R.id.yonghu);
            imglayout =  view.findViewById(R.id.imglayout);
            touxiang =  view.findViewById(R.id.touxiang);


        }

    }


    public JiliuAdapter(boolean shoudele, User user, Context context, List<Tiezi> tavernListdata, OnItemClickListener listener){
        this.mContext = context;
        this.lubeans = tavernListdata;
        this.mListener = listener;
        this.user=user;
        this.showdele=shoudele;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shi_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Tiezi lubean = lubeans.get(position);
        if(lubean!=null)
        {



            holder.time.setText(lubean.getTime()+"");
            holder.zhengwen.setText("");
            holder.zantv.setText(lubean.getZan()+"");
            holder.pjtv.setText(lubean.getPingjianum()+"");

            try {
                User userpne=Xutils.initDbConfiginit().findById(User.class,lubean.getUserid());
                GlideUtils.getInstance().intoRoundImageView(userpne.getImg(), holder.touxiang,3);
                holder.yonghu.setText(userpne.getName()+"");

            } catch (DbException e) {
                e.printStackTrace();
            }


            if(!StrUtil.isEmpty(lubean.getImgurl()))
            {
                String[]imgarray=lubean.getImgurl().split(",");
                holder.imglayout.removeAllViews();
                for (int i=0;i<imgarray.length;i++)
                {
                    if(!StrUtil.isEmpty(imgarray[i])&&i<4)
                    {
                        SquareImageView squareImageView=new SquareImageView(mContext);

                        int widtg = ((Activity)mContext).getWindowManager().getDefaultDisplay().getWidth()-100; // 屏幕高(像素，如：800p)
                        squareImageView.setLayoutParams(new LinearLayout.LayoutParams(widtg/3,widtg/3));
                        squareImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        squareImageView.setPadding(10,10,10,10);
                       // Xutils.display(squareImageView,new File(imgarray[i]).toURI().toString(),0);
                        GlideUtils.getInstance().intoRoundImageView(imgarray[i],squareImageView,3);


                        holder.imglayout.addView(squareImageView);
                    }
                }

            }







        }
        if (showdele)
        {
            holder.delebutton.setVisibility(View.VISIBLE);
            holder.delebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.ondele(position);
                }
            });


        }

        holder.zantv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.dianzan(position,holder.zantv);
            }
        });

        holder.orderlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return lubeans.size();
    }



    // 外部接口回调监听
    public interface OnItemClickListener {
        void onClick(int pos);
        void ondele(int pos);
        void onupdata(int pos);
        void dianzan(int pos, TextView textView);


    }
}