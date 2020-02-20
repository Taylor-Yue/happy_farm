package com.example.happy_farm.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happy_farm.R;
import com.example.happy_farm.recyclerview.FullyLinearLayoutManager;
import com.example.happy_farm.recyclerview.LinearAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class find_fragment extends Fragment {
    //定义保存图片资源IDeas的数组
    private int[] arrayPicture = new int[]{R.drawable.ico_bar1,R.drawable.ico_bar2,R.drawable.ico_bar3,R.drawable.ico_bar1};
    private int[] arrayImageid = new int[]{R.drawable.ico_bar1,R.drawable.ico_bar2,R.drawable.ico_bar3,R.drawable.ico_bar1,R.drawable.ico_bar2};
    //定义ListView字符串资源
    private String[] title = new String[]{"桃树","梨树","苹果树","hahah","?????"};
    private String[] introduction = new String[]{"哈","哈哈","哈哈哈","?????","hhahaha"};
    //private List<CarouselImage> data;//方便数据的写入和读取
    //手动切换起始坐标
    private float startX;
    //定义动画数组，为ViewFlipper指定切换动画
    private Animation[] animations = new Animation[5];
    //定义ViewFlipper
    private ViewFlipper viewFlipper;
    //滑动距离判定
    private static final int FLING_MIN_DISTANCE = 80;
    private static final int FLING_MIN_VELOCITY = 150;


    private GestureDetector gestureDetector;

    private ScrollView scrollView;
    private ListView listView;
    private RecyclerView mRecyclerView;


    //消息代码
    final int FLAG_MSG = 0X001;
    //声明消息对象
    private Message message;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_fragment,container,false);
        return view;
    }
    public void onViewCreated(View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        /********************通过ViewFlipper组件播放广告**********************/
        viewFlipper = view.findViewById(R.id.find_fragment_ViewFlipper);//获取ViewFlipper
        for (int i = 0; i < arrayPicture.length; i++) {//遍历数组中的图片
            ImageView imageView = new ImageView(getActivity());//创建ImageView对象
            imageView.setImageResource(arrayPicture[i]);//将遍历的图片保存在ImageView中
            viewFlipper.addView(imageView);//加载图片
        }
        //初始化动画数组
        animations[0] = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right);//右侧平移进入动画
        animations[1] = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_left);//左侧平移退出动画
        animations[2] = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_left);//左侧平移进入动画
        animations[3] = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_right);//右侧平移退出动画
        viewFlipper.setInAnimation(animations[0]);//为ViewFlipper设置进入动画
        viewFlipper.setOutAnimation(animations[1]);//为ViewFlipper设置退出动画
        /*****************************************************************************/


        /****************************开启广告轮播*************************************/
        //开启自动启动和时间间隔
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(3000);
//        message = Message.obtain();//获取Message对象
//        message.what = FLAG_MSG;//设置消息代码
//        handler.sendMessage(message);//发送消息
        /******************************************************************************/

        /****************************轮播广告的手动播放和点击事件***********************/
        //生成手势识别实例
        gestureDetector = new GestureDetector(getActivity(), new find_fragment.GerstureListener());
        // 给ViewFlipper设置一个TouchListener
        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //暂停自动播放
                viewFlipper.stopFlipping();
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
        // 允许长按ViewFlipper
        viewFlipper.setLongClickable(true);

        /******************************************************************************/

        /********************************ListView实现*****************************************/
        mRecyclerView = view.findViewById(R.id.find_fragment_RecyclerView);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));


        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();//建立ListMap对象
        for (int i = 0; i < arrayImageid.length; i++) {//遍历数组，将资源保存进map中
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", title[i]);
            map.put("introduction", introduction[i]);
            listitem.add(map);//将map保存在ListView中
        }

        //创建LinearApter适配器
        LinearAdapter adapter = new LinearAdapter(getActivity(),listitem,arrayImageid);

        try{
            adapter.setItemListener(new LinearAdapter.onRecyclerItemClickerListener() {
                @Override
                public void onClick(int pos) {
                    Toast.makeText(getActivity(),"click" + pos,Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

        //关联适配器
        mRecyclerView.setAdapter(adapter);

        //创建SimpLAdapter适配器
        //SimpleAdapter adapter = new SimpleAdapter(getActivity(), listitem, R.layout.find_fragment_listview,
          //      new String[]{"image", "title", "introduction"},
            //    new int[]{R.id.find_fragment_ListView_imageView, R.id.find_fragment_ListView_title, R.id.find_fragment_ListView_Introduction});
        //获取ListView
        //listView = view.findViewById(R.id.find_fragment_ListView);
        scrollView = view.findViewById(R.id.find_fragment_scrollview);
        //listView.setAdapter(adapter);//关联适配器
        //为ListView添加监听器，获取选中项的值
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Map<String, Object> map = (Map<String, Object>) adapterView.getItemAtPosition(i);
//                Toast.makeText(getActivity(), map.get("title").toString(), Toast.LENGTH_LONG).show();
//            }
//        });
        /******************************************************************************/

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
            viewFlipper.startFlipping();
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
            Toast.makeText(getActivity(), "hahaha", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
