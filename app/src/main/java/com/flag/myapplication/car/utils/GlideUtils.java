package com.flag.myapplication.car.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.myweb.util.net.http.MyApplication;
import com.flag.myapplication.hotel.R;

/**
 * Description:
 */
public class GlideUtils {


    public GlideUtils() {
    }


    private static class GlideLoadUtilsHolder {
        private final static GlideUtils INSTANCE = new GlideUtils();
    }

    public static GlideUtils getInstance() {
        return GlideLoadUtilsHolder.INSTANCE;
    }


    /**
     * 普通图片
     *
     * @param url
     * @param imageView
     */
    public void intoImageView(String url, ImageView imageView) {
        Glide.with(MyApplication.getInstance().getApplicationContext())
                .load(url)
//                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)//关键代码，加载原始大小
                .thumbnail(0.2f)
                .placeholder(R.mipmap.ic_launcher)//图片加载出来前，显示的图片
                .error(R.mipmap.ic_launcher)
                .into(imageView);

    }


    /**
     * 普通图片
     *
     * @param url
     * @param imageView
     */
    public void intoHeadImageView(String url, ImageView imageView) {
        Glide.with(MyApplication.getInstance().getApplicationContext())
                .load(url)
                .apply(new RequestOptions()
                        .error(R.mipmap.ic_launcher))
                .into(imageView);
    }

    /**
     * 普通图片
     *
     * @param url
     * @param imageView
     */
    public void intoImageView(Integer url, ImageView imageView) {
        Glide.with(MyApplication.getInstance().getApplicationContext())
                .load(url)
                .apply(new RequestOptions()
                        .error(R.mipmap.ic_launcher))
                .into(imageView);
    }

    /**
     * 圆图片
     *
     * @param url
     * @param imageView
     */
    public void intoCircleImageView(String url, ImageView imageView) {
        Glide.with(MyApplication.getInstance().getApplicationContext())
                .load(url)
                .apply(RequestOptions.bitmapTransform(new CircleCrop())
                        .error(R.mipmap.ic_launcher))
                .into(imageView);
    }

    /**
     * 圆图片
     *
     * @param url
     * @param imageView
     */
    public void intoCircleImageView(int url, ImageView imageView) {
        Glide.with(MyApplication.getInstance().getApplicationContext())
                .load(url)
                .apply(RequestOptions.bitmapTransform(new CircleCrop())
                        .error(R.mipmap.ic_launcher))
                .into(imageView);
    }

    /**
     * 圆角图片
     *
     * @param url
     * @param imageView
     */
    public void intoRoundImageView(String url, ImageView imageView) {
        Glide.with(MyApplication.getInstance().getApplicationContext())
                .load(url)
                .apply(RequestOptions.bitmapTransform(new GlideRoundTransform(imageView.getContext()))
                        .error(R.mipmap.ic_launcher))
                .into(imageView);
    }

    /**
     * 圆角图片
     *
     * @param url
     * @param imageView
     */
    public void intoRoundImageView(String url, ImageView imageView, int dp) {
        Glide.with(MyApplication.getInstance().getApplicationContext())
                .load(url)
                .apply(RequestOptions.bitmapTransform(new GlideRoundTransform(imageView.getContext(), dp))
                        .error(R.mipmap.applogo)
                        .placeholder(imageView.getDrawable()))
                .into(imageView);
    }

    /**
     * 圆角图片
     *
     * @param url
     * @param imageView
     */
    public void intoRoundImageView(Integer url, ImageView imageView) {
        Glide.with(MyApplication.getInstance().getApplicationContext())
                .load(url)
                .apply(RequestOptions.bitmapTransform(new GlideRoundTransform(imageView.getContext()))
                        .error(R.mipmap.ic_launcher))
                .into(imageView);
    }




}
