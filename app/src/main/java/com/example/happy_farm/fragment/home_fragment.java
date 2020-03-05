package com.example.happy_farm.fragment;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

public class home_fragment extends Fragment {

    //定义保存图片资源IDeas的数组
    private int[] arrayImageid = new int[]{R.drawable.ico_bar1,R.drawable.ico_bar2,R.drawable.ico_bar3,R.drawable.ico_bar1,R.drawable.ico_bar2};
    //定义ListView字符串资源
    private String[] title = new String[]{"桃树","梨树","苹果树","hahah","?????"};
    private String[] introduction = new String[]{"哈","哈哈","哈哈哈","?????","hhahaha"};

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        return view;
    }
    public void onViewCreated(View view,@Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        /********************************ListView实现*****************************************/
        mRecyclerView = view.findViewById(R.id.home_fragment_RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //mRecyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));


        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();//建立ListMap对象
        for (int i = 0; i < arrayImageid.length; i++) {//遍历数组，将资源保存进map中
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", title[i]);
            map.put("introduction", introduction[i]);
            listitem.add(map);//将map保存在ListView中
        }

        //创建LinearApter适配器
        LinearAdapter adapter = new LinearAdapter(getActivity(),listitem,arrayImageid,R.layout.home_fragment_listview,
                new String[]{"title","introduction"},
                new int[]{R.id.home_fragment_ListView_imageView,R.id.home_fragment_ListView_title,R.id.home_fragment_ListView_Introduction});

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
        //画线
        mRecyclerView.addItemDecoration(new MyDecoration());
        //关联适配器
        mRecyclerView.setAdapter(adapter);

        /**********************************************************************************************/

    }
    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
        }

//        @Override
//        public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//            super.onDraw(c, parent, state);
//        }
    }
}
