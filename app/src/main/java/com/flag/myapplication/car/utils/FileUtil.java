package com.flag.myapplication.car.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;

/**

 */
public class FileUtil {

    public static ArrayList<Map<String,String>> readliaotian(Context context,String path) {
        ArrayList<Map<String,String>> mapArrayList=new ArrayList<>();
        String logFilePath = Environment.getExternalStorageDirectory() + File.separator + path;
        File file = new File(path);
        Log.e("FileUtil", "file=" + file.getAbsolutePath());
        try {
            InputStream is = new FileInputStream(file);
            Workbook book = Workbook.getWorkbook(is);
            book.getNumberOfSheets();
            Sheet sheet = book.getSheet(0);
            int Rows = sheet.getRows();

            for (int i = 1; i < Rows; ++i) {
                Map<String,String> map=new HashMap<>();
                map.put("1",(sheet.getCell(0, i)).getContents());
                map.put("2",(sheet.getCell(1, i)).getContents());
                map.put("3",(sheet.getCell(2, i)).getContents());
                map.put("4",(sheet.getCell(3, i)).getContents());

                mapArrayList.add(map);
            }
            book.close();

        } catch (Exception e) {

            Log.e("FileUtil", "e" + e);
        }

        return mapArrayList;
    }


}

