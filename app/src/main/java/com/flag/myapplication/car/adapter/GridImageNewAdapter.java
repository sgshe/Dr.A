package com.flag.myapplication.car.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.flag.myapplication.car.utils.GlideUtils;
import com.flag.myapplication.car.utils.SquareImageView;
import com.flag.myapplication.hotel.R;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加、点击、删除图片
 */
public class GridImageNewAdapter extends
        RecyclerView.Adapter<GridImageNewAdapter.ViewHolder> {
    public static final int TYPE_CAMERA = 1;
    public static final int TYPE_PICTURE = 2;
    private LayoutInflater mInflater;
    private List<LocalMedia> list = new ArrayList<>();
    private int selectMax = 9;
    private Context context;
    private int default_add = R.mipmap.icon_good_create3;

    public ImageGridListener listener;

    public interface ImageGridListener {
        void onItemClick(int position);
        void onAddPicClick();
        void onDelClick(int position);
    }

    public GridImageNewAdapter(Context context, List<LocalMedia> list, ImageGridListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
        mInflater = LayoutInflater.from(context);
    }

    public void setSelectMax(int selectMax) {
        this.selectMax = selectMax;
    }

    public void setDefaultAdd(int default_add) {
        this.default_add = default_add;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        SquareImageView mImg;
        ImageView iv_del;

        public ViewHolder(View view) {
            super(view);
            mImg = (SquareImageView) view.findViewById(R.id.fiv);
            iv_del = (ImageView) view.findViewById(R.id.iv_del);
        }
    }

    @Override
    public int getItemCount() {
        if (list.size() < selectMax) {
            return list.size() + 1;
        } else {
            return list.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowAddItem(position)) {
            return TYPE_CAMERA;
        } else {
            return TYPE_PICTURE;
        }
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_filter_image, viewGroup, false);
        return new ViewHolder(view);
    }

    private boolean isShowAddItem(int position) {
        int size = list == null ? 0 : list.size();
        return position == size;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        //少于8张，显示继续添加的图标
        if (getItemViewType(position) == TYPE_CAMERA) {
            viewHolder.mImg.setImageResource(default_add);
            viewHolder.mImg.setOnClickListener(v -> listener.onAddPicClick());
            viewHolder.iv_del.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.iv_del.setVisibility(View.VISIBLE);
            viewHolder.iv_del.setOnClickListener(view -> listener.onDelClick(position));
            LocalMedia media = list.get(position);
            if (media == null || TextUtils.isEmpty(media.getPath())) {
                return;
            }
            String path;
            if (media.isCut() && !media.isCompressed()) {
                // 裁剪过
                path = media.getCutPath();
            } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
                // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                path = media.getCompressPath();
            } else {
                // 原图
                path = media.getPath();
            }
            //Xutils.display(viewHolder.mImg,path,3);
            GlideUtils.getInstance().intoRoundImageView(path,viewHolder.mImg,3);
            //itemView 的点击事件
            if (listener != null) {
                viewHolder.itemView.setOnClickListener(v -> listener.onItemClick(position));
            }
        }
    }

}
