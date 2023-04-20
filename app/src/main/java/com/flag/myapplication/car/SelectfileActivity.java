package com.flag.myapplication.car;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.flag.myapplication.car.utils.UriUtil;
import com.flag.myapplication.hotel.R;

public class SelectfileActivity  extends AppCompatActivity {

    TextView filePathtextView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectfile);

        filePathtextView = findViewById(R.id.fileTextView);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

               // intent.setType("*/*");
                intent.setType("image/*");


                intent.addCategory(Intent.CATEGORY_OPENABLE);

                startActivityForResult(Intent.createChooser(intent,"需要选择文件"),1);

            }
        });








    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {


            Uri uri = data.getData();

            String pathString = UriUtil.uriToFileApiQ(this,uri);

            filePathtextView.setText(pathString);





        }

    }



}
