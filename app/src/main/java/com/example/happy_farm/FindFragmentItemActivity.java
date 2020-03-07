package com.example.happy_farm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.happy_farm.fragment.find_fragment;

public class FindFragmentItemActivity extends AppCompatActivity {

    //定义保存图片资源IDeas的数组
    private int[] arrayPicture = new int[]{R.drawable.ico_bar1,R.drawable.ico_bar2,R.drawable.ico_bar3,R.drawable.ico_bar1};

    //定义动画数组，为ViewFlipper指定切换动画
    private Animation[] animations = new Animation[5];
    //定义ViewFlipper
    private ViewFlipper viewFlipper;

    private TextView Name,Address,Introduction;

    //滑动距离判定
    private static final int FLING_MIN_DISTANCE = 80;
    private static final int FLING_MIN_VELOCITY = 150;


    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_fragment_item);

        Bundle bundle = getIntent().getExtras();
        int pos = bundle.getInt("key");
        Toast.makeText(this,"click" + pos,Toast.LENGTH_SHORT).show();
        Name = findViewById(R.id.Name);
        Address = findViewById(R.id.Address);
        Introduction = findViewById(R.id.Introduction);

        Name.setText("????");
        Address.setText("???");
        Introduction.setText("???");


        viewFlipper = findViewById(R.id.find_fragment_item_ViewFlipper);//获取ViewFlipper
        for (int i = 0; i < arrayPicture.length; i++) {//遍历数组中的图片
            ImageView imageView = new ImageView(this);//创建ImageView对象
            imageView.setImageResource(arrayPicture[i]);//将遍历的图片保存在ImageView中
            viewFlipper.addView(imageView);//加载图片
        }

        //初始化动画数组
        animations[0] = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);//右侧平移进入动画
        animations[1] = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);//左侧平移退出动画
        animations[2] = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);//左侧平移进入动画
        animations[3] = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);//右侧平移退出动画


        //生成手势识别实例
        gestureDetector = new GestureDetector(this, new FindFragmentItemActivity.GerstureListener());
        // 给ViewFlipper设置一个TouchListener
        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //暂停自动播放
                //viewFlipper.stopFlipping();
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
        // 允许长按ViewFlipper
        viewFlipper.setLongClickable(true);

    }

    private class GerstureListener implements GestureDetector.OnGestureListener {
        public boolean onDown(MotionEvent e) {
            return false;
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            //viewFlipper.setFlipInterval(0);

            if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
                    && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                // 左滑时,按照规定动画切换
                viewFlipper.setInAnimation(animations[0]);
                viewFlipper.setOutAnimation(animations[1]);
                viewFlipper.showNext();
            } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
                    && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                // 右滑时,按照规定动画切换
                viewFlipper.setInAnimation(animations[2]);
                viewFlipper.setOutAnimation(animations[3]);
                viewFlipper.showPrevious();
            }
            //启动自动播放
           // viewFlipper.startFlipping();
            // 滑动动作完毕后,设置回右进左出的动画
            viewFlipper.setInAnimation(animations[0]);
            viewFlipper.setOutAnimation(animations[1]);
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            // TODO Auto-generated method stub
            Toast.makeText(FindFragmentItemActivity.this, "hahaha", Toast.LENGTH_LONG).show();
            return false;
        }
    }

}
