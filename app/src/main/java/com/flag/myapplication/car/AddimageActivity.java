package com.flag.myapplication.car;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweb.StrUtil;
import com.example.myweb.util.Xutils;
import com.flag.myapplication.car.adapter.GridImageNewAdapter;
import com.flag.myapplication.car.bean.Tiezi;
import com.flag.myapplication.car.utils.GlideEngine;
import com.flag.myapplication.car.utils.decoration.FullyGridLayoutManager;
import com.flag.myapplication.hotel.R;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;

import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ContentView(R.layout.activity_addimage)
public class AddimageActivity extends BaseActivity implements View.OnClickListener {

      @ViewInject(R.id.name)
    EditText name;
      @ViewInject(R.id.address)
    EditText address;

      @ViewInject(R.id.zhengwen)
    EditText zhengwen;

      @ViewInject(R.id.commotbutton)
    Button commotbutton;
      @ViewInject(R.id.text)
    TextView text;



      @ViewInject(R.id.backimage)
    ImageView backimage;

      @ViewInject(R.id.progressBar)
    ProgressBar progressBar;


      @ViewInject(R.id.evaCreateImaList)
    RecyclerView evaCreateImaList;

    private File mFile;
    private Uri mImageUri;

    Tiezi shipinbean;

    GridImageNewAdapter imgAdapter=null;

List<LocalMedia> topListzong = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        x.view().inject(AddimageActivity.this);
        super.onCreate(savedInstanceState);

        initData();
        initListener();

    }

    protected void initData() {
        backimage.setVisibility(View.VISIBLE);

        commotbutton.setOnClickListener(this);
        text.setText("release");


        evaCreateImaList.setLayoutManager(new FullyGridLayoutManager(AddimageActivity.this, 4, GridLayoutManager.VERTICAL, false));

        if(imgAdapter==null)
        {
            imgAdapter = new GridImageNewAdapter(AddimageActivity.this, topListzong , new GridImageNewAdapter.ImageGridListener() {
                @Override
                public void onItemClick(int position) {
                    PictureSelector.create(AddimageActivity.this)
                            .externalPicturePreview(position,topListzong , 0);
                }

                @Override
                public void onAddPicClick() {
                    PictureSelector.create(AddimageActivity.this)
                            .openGallery(PictureMimeType.ofImage())
                            .imageEngine(GlideEngine.createGlideEngine())
                            .isCompress(true)// 是否压缩
                            .maxSelectNum(1)// 最大图片选择数量 int
                            .forResult(new OnResultCallbackListener<LocalMedia>() {
                                @Override
                                public void onResult(List<LocalMedia> result) {
                                    if (result != null ) {
                                        topListzong.add(result.get(0));
                                        imgAdapter.notifyDataSetChanged();

                                    }
                                }

                                @Override
                                public void onCancel() {

                                }
                            });
                }

                @Override
                public void onDelClick(int position) {
                    topListzong.remove(position);
                    imgAdapter.notifyDataSetChanged();

                }
            });


        }


        imgAdapter.setDefaultAdd(R.mipmap.icon_eva_img_add);
        imgAdapter.setSelectMax(5);

        evaCreateImaList.setAdapter( imgAdapter);


    }

    protected void initListener() {
        backimage.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.commotbutton)
        {
            ///Submit

            String namest = (name.getText()+"").trim();
            String addressst = (address.getText()+"").trim();
            String zhengwenst = (zhengwen.getText()+"").trim();


            //判断输入框

            if (StrUtil.isEmpty(namest)|| StrUtil.isEmpty(zhengwenst))
            {
                Toast.makeText(AddimageActivity.this,"不能为空", Toast.LENGTH_SHORT).show();
                return;
            }


            if(shipinbean==null)
            {
                shipinbean=new Tiezi();
            }





            String imgnamestring="";
            for (int i=0;i<topListzong.size();i++)
            {
                imgnamestring=imgnamestring+","+topListzong.get(i).getCompressPath();
            }

            shipinbean.setImgurl(imgnamestring);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");// HH:mm:ss
//获取当前Time
            Date date = new Date(System.currentTimeMillis());
            shipinbean.setTime(simpleDateFormat.format(date));
            shipinbean.setNeirong(zhengwen.getText()+"");
            shipinbean.setTitile(namest+"");
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
