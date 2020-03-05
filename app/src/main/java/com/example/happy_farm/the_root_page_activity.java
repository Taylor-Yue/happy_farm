package com.example.happy_farm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.happy_farm.fragment.find_fragment;
import com.example.happy_farm.fragment.home_fragment;
import com.example.happy_farm.fragment.user_fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class the_root_page_activity extends AppCompatActivity {

    private String Tag;
    //创建fragment对象
    private find_fragment findFragment;
    private home_fragment homeFragment;
    private user_fragment userFragment;

    //标记
    private String fFragment = "find";
    private String hFragment = "home";
    private String uFragment = "user";

    //fragment图片资源
    private ImageButton imageButFind,imageButHome,imageButUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_root_page_activity);


        imageButFind = findViewById(R.id.Image_find);
        imageButHome = findViewById(R.id.Image_home);
        imageButUser = findViewById(R.id.Image_user);


        //实例化find_fragment
        findFragment = new find_fragment();
        //把find_fragment添加到activity中
        Tag = "find";
        getSupportFragmentManager().beginTransaction().add(R.id.fragment,findFragment,"find").commitAllowingStateLoss();
        setListener();
    }
    private void setListener(){
        OnClick onClick = new OnClick();
        imageButFind.setOnClickListener(onClick);
        imageButHome.setOnClickListener(onClick);
        imageButUser.setOnClickListener(onClick);
    }
    private class OnClick implements View.OnClickListener{
        public void onClick(View view){
            switch(view.getId()){
                case R.id.Image_find:
                    if(Tag != "find"){
                        Fragment fragment = getSupportFragmentManager().findFragmentByTag(Tag);
                        if(fragment != null){
                            getSupportFragmentManager().beginTransaction().hide(fragment).add(R.id.fragment,findFragment).addToBackStack(null).commitAllowingStateLoss();
                        }
                        else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,findFragment).addToBackStack(null).commitAllowingStateLoss();
                        }
                    }
                    //Toast.makeText(the_root_page_activity.this, "12345", Toast.LENGTH_LONG).show();
                    break;
                case R.id.Image_home:
                    if(homeFragment == null){
                        homeFragment = new home_fragment();
                    }
                    if(Tag != "home"){
                        Fragment fragment = getSupportFragmentManager().findFragmentByTag(Tag);
                        if(fragment != null){
                            getSupportFragmentManager().beginTransaction().hide(fragment).add(R.id.fragment,homeFragment).addToBackStack(null).commitAllowingStateLoss();
                        }
                        else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,homeFragment).addToBackStack(null).commitAllowingStateLoss();
                        }
                    }
                        //Toast.makeText(the_root_page_activity.this, "45??6", Toast.LENGTH_LONG).show();
                    break;
                case R.id.Image_user:
                    //Toast.makeText(the_root_page_activity.this, "123", Toast.LENGTH_LONG).show();
                        if(userFragment == null){
                            userFragment = new user_fragment();
                        }
                    if(Tag != "user"){
                        Fragment fragment = getSupportFragmentManager().findFragmentByTag(Tag);
                        if(fragment != null){
                            getSupportFragmentManager().beginTransaction().hide(fragment).add(R.id.fragment,userFragment).addToBackStack(null).commitAllowingStateLoss();
                        }
                        else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,userFragment).addToBackStack(null).commitAllowingStateLoss();
                        }
                    }
//                        if(userFragment != null)
//                            Toast.makeText(the_root_page_activity.this, "123", Toast.LENGTH_LONG).show();
                    break;
                default:
//                       Toast.makeText(the_root_page_activity.this, "?????", Toast.LENGTH_LONG).show();
                    break;
            }
            if(view.getId() == R.id.Image_find){
                Tag = "find";
            } else if(view.getId() == R.id.Image_home){
                Tag = "home";
            } else if(view.getId() == R.id.Image_user){
                Tag = "user";
            }
        }
    }
    /**************************创建Handler对象，实现3秒更新一次图片********************
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == FLAG_MSG){
                viewFlipper.showPrevious();//切换下一张图片
                message = handler.obtainMessage(FLAG_MSG);//获取Message
                handler.sendMessageDelayed(message,3000);//延迟3秒发送消息
            }
        }
    };
    *************************************************************************************/
}
