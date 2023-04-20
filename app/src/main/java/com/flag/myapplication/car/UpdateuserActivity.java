package com.flag.myapplication.car;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myweb.util.StrUtil;
import com.flag.myapplication.car.bean.User;
import com.flag.myapplication.car.utils.GlideEngine;
import com.flag.myapplication.car.utils.GlideUtils;
import com.flag.myapplication.car.utils.SquareImageView;
import com.flag.myapplication.hotel.R;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;

import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

@ContentView(R.layout.activity_userinfo)
public class UpdateuserActivity extends BaseActivity {

    @ViewInject(R.id.nametv)
    EditText nametv;
    @ViewInject(R.id.agetv)
    EditText agetv;
    @ViewInject(R.id.phonetv)
    EditText phonetv;
    @ViewInject(R.id.emailtv)
    EditText emailtv;

    @ViewInject(R.id.touimg)
    SquareImageView touimg;


    @ViewInject(R.id.text)
    TextView text;

    @ViewInject(R.id.save)
    TextView save;



    User data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        x.view().inject(UpdateuserActivity.this);
        super.onCreate(savedInstanceState);
        data= (User) getIntent().getSerializableExtra("data");
        text.setText("personal information");
        if(data!=null)
        {

            if(!StrUtil.isEmpty(data.getName()))
            {
                nametv.setText(data.getName());
            }

            if(!StrUtil.isEmpty(data.getAge()))
            {
                agetv.setText(data.getAge());
            }
            if(!StrUtil.isEmpty(data.getPhone()))
            {
                phonetv.setText(data.getPhone());
            }
            if(!StrUtil.isEmpty(data.getEmail()))
            {
                emailtv.setText(data.getEmail());
            }
            GlideUtils.getInstance().intoRoundImageView(data.getImg(),touimg,3);


        }

    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initListener() {

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                data.setName(nametv.getText().toString());
                data.setAge(agetv.getText().toString());
                data.setPhone(phonetv.getText().toString());
                data.setEmail(emailtv.getText().toString());

                loginhttpbend(data);


            }
        });

        touimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(UpdateuserActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .imageEngine(GlideEngine.createGlideEngine())
                        .isCompress(true)// 是否压缩
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .forResult(new OnResultCallbackListener<LocalMedia>() {
                            @Override
                            public void onResult(List<LocalMedia> result) {
                                if (result != null ) {
                                    data.setImg(result.get(0).getCompressPath());
                                    GlideUtils.getInstance().intoRoundImageView(data.getImg(),touimg,3);

                                }
                            }

                            @Override
                            public void onCancel() {

                            }
                        });
            }
        });
    }

    public void loginhttpbend(User user)
    {
        try {
            db.update(user);
        } catch (DbException e) {
            e.printStackTrace();
        }
        finish();

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
