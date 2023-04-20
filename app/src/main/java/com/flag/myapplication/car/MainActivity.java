package com.flag.myapplication.car;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.flag.myapplication.car.bean.User;
import com.flag.myapplication.car.fargment.LiaotianFragment;
import com.flag.myapplication.car.fargment.MainFragment;
import com.flag.myapplication.car.fargment.ShipinFragment;
import com.flag.myapplication.car.fargment.UserFragment;
import com.flag.myapplication.hotel.R;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener{

    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;
    private String TAG = MainActivity.class.getSimpleName();
    private UserFragment userFragment;
    private LiaotianFragment liaotianFragment;

    private MainFragment mainFragment;
    private ShipinFragment shipinFragment;


    User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * bottomNavigation 设置
         */
        requestMyPermissions();

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        /** 导航基础设置 包括按钮选中效果 导航栏背景色等 */
        bottomNavigationBar
                .setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setActiveColor("#FF107FFD") //选中颜色

                .setBarBackgroundColor(R.color.huise);//导航栏背景色

        /** 添加导航按钮 */
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.tubiao, "Line chart"))

                .addItem(new BottomNavigationItem(R.mipmap.luntan, "forum"))
//                .addItem(new BottomNavigationItem(R.mipmap.liaotian, "chat"))

                .addItem(new BottomNavigationItem(R.mipmap.wode, "my"))
                .setFirstSelectedPosition(lastSelectedPosition )
                .initialise(); //initialise 一定要放在 所有设置的最后一项

        setDefaultFragment();//设置默认导航栏



    }

    @Override
    protected void initData() {
        user=(User)getIntent().getSerializableExtra("user");


    }

    @Override
    protected void initListener() {

    }

    /**
     * 设置默认导航栏
     */
    private void setDefaultFragment() {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        shipinFragment = ShipinFragment.newInstance("Home", user);
        transaction.replace(R.id.tb, shipinFragment);
        bottomNavigationBar.selectTab(0);
        transaction.commit();
    }

    /**
     * 设置导航选中的事件
     */
    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        //开启事务
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                if (shipinFragment == null) {
                    shipinFragment = ShipinFragment.newInstance("", user);
                }
                transaction.replace(R.id.tb, shipinFragment);
                break;
            case 1:
                if (mainFragment == null) {
                    mainFragment = MainFragment.newInstance("", user);
                }
                transaction.replace(R.id.tb, mainFragment);
                break;
            case 2:
//                if (liaotianFragment == null) {
//                    liaotianFragment = LiaotianFragment.newInstance("1",user);
//                }
//                transaction.replace(R.id.tb, liaotianFragment);

                if (userFragment == null) {
                    userFragment = UserFragment.newInstance("1",user);
                }
                transaction.replace(R.id.tb, userFragment);
                break;
//            case 3:
//                if (userFragment == null) {
//                    userFragment = UserFragment.newInstance("1",user);
//                }
//                transaction.replace(R.id.tb, userFragment);
//                break;
            default:
                break;
        }

        transaction.commit();// 事务Submit
    }

    /**
     * 设置未选中Fragment 事务
     */
    @Override
    public void onTabUnselected(int position) {

    }

    /**
     * 设置释放Fragment 事务
     */
    @Override
    public void onTabReselected(int position) {

    }


    /*
     * @param bottomNavigationBar，需要修改的 BottomNavigationBar
     * @param space 图片与文字之间的间距
     * @param imgLen 单位：dp，图片大小，应 <= 36dp
     * @param textSize 单位：dp，文字大小，应 <= 20dp
     *
     *  使用方法：直接调用setBottomNavigationItem(bottomNavigationBar, 6, 26, 10);
     *  代表将bottomNavigationBar的文字大小设置为10dp，图片大小为26dp，二者间间距为6dp
     *
     * */

    public int dip2px(float dpValue) {
        final float scale = getApplication().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }




    static final String[] all_permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,

    };
    private boolean isPermissionRequested;

    private void requestMyPermissions() {
        if (Build.VERSION.SDK_INT >= 23 && !isPermissionRequested) {
            isPermissionRequested = true;
            ArrayList<String> permissionsList = new ArrayList<>();

            for (String perm : all_permissions) {
                if (PackageManager.PERMISSION_GRANTED != checkSelfPermission(perm)) {
                    permissionsList.add(perm);
                    // 进入到这里代表没有权限.
                }
            }

            if (!permissionsList.isEmpty()) {
                String[] strings = new String[permissionsList.size()];
                requestPermissions(permissionsList.toArray(strings), 0);
            }
        }
    }




}

