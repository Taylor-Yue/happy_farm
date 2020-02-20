package com.example.happy_farm.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.LayoutInflaterFactory;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happy_farm.R;

import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/*
*Created by Taylor_Lee on 2020/2/17
 */

/*
*专用Adapter  通用Adater功能为完成
* 未实现查找布局文件的控件与为控件赋值
*/


public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.LinearViewHolder> {

    private Context mContext;

    private onRecyclerItemClickerListener mListener;

    private int[] mTo; // 指向布局里面控件的id 比如：R.id.btn

    private int[] marray;

    private List<? extends Map<String, ?>> mData;  // 用List打包的Map数据源

    private int mResource;// 布局

    private String[] mFrom; // 数据来源，来自Map里面的key

    public LinearAdapter(Context context,List<? extends Map<String, ?>> data,int resource,String[] from,int[] to){

        this.mContext = context;
        if(mData != null){
            this.mData.clear();;
            this.mData = data;
            this.mResource = resource;
            this.mFrom = from;
            this.mTo = to;
        }

    }

    public LinearAdapter(Context context, List<? extends Map<String, ?>> data,int[] array){

        this.mContext = context;
        //这么写存在bug
        //if(mData != null){
        //   this.mData.clear();
            this.mData = data;
            this.marray = array;
       // }
    }

    public LinearAdapter(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    public LinearAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.find_fragment_listview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LinearAdapter.LinearViewHolder holder, final int position) {

        Map<String, Object> map = (Map<String, Object>) mData.get(position);
        holder.imageView.setImageResource(marray[position]);
        holder.title.setText(map.get("title").toString());
        holder.introduction.setText(map.get("introduction").toString());

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                mListener.onClick(position);
            }
        });

    }



    @Override
    public int getItemCount() {
        return mData.size();
//        return  30;
    }


    /**
     * 增加点击监听
     */
    public void setItemListener(onRecyclerItemClickerListener mListener) {

        this.mListener = mListener;
    }

    /**
     * 点击监听回调接口
     */
    public interface onRecyclerItemClickerListener {
        void onClick(int pos);
    }


    class LinearViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView title;
        private TextView introduction;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.find_fragment_ListView_imageView);
            title = itemView.findViewById(R.id.find_fragment_ListView_title);
            introduction = itemView.findViewById(R.id.find_fragment_ListView_Introduction);
        }
    }
}
