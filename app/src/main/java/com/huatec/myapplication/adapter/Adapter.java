package com.huatec.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huatec.myapplication.R;
import com.huatec.myapplication.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    //图标数组
    private int[] icons = {
            R.drawable.icon_1, R.drawable.icon_2, R.drawable.icon_3,
            R.drawable.icon_4, R.drawable.icon_5, R.drawable.icon_6, R.drawable.icon_7,
            R.drawable.icon_8, R.drawable.icon_9, R.drawable.icon_10, R.drawable.icon_11
    };

    //名字数组
    private int[] names = {
            R.string.name1, R.string.name2, R.string.name3, R.string.name4, R.string.name5,
            R.string.name6, R.string.name7, R.string.name8, R.string.name9, R.string.name10,
            R.string.name11
    };

    //信息数组
    private int[] infos = {
            R.string.info1, R.string.info2, R.string.info3, R.string.info4, R.string.info5,
            R.string.info6, R.string.info7, R.string.info8, R.string.info9, R.string.info10,
            R.string.info11
    };

    private Context lContent;//定义上下文

    //集合
    private List<Integer> listIcon = new ArrayList<>();
    private List<Integer> listName = new ArrayList<>();
    private List<Integer> listInfo = new ArrayList<>();

    public Adapter(Context lContent) {
        this.lContent = lContent;
        //设置菜单行数与行内图标、名称、信息
        for (int i = 0; i < 11; i++) {
            listIcon.add(icons[i]);
            listName.add(names[i]);
            listInfo.add(infos[i]);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //获取列表中每行的布局文件
        View view = LayoutInflater.from(lContent).inflate(R.layout.layout_item, parent, false);
        return new MyViewHolder(view);
    }

    //设置列表中行所显示的内容
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        //设置图标
        holder.img.setBackgroundResource(listIcon.get(position));
        //设置名称
        holder.name.setText(listName.get(position));
        //设置信息
        holder.info.setText(listInfo.get(position));
        //设置内容宽度为屏幕的宽度
        holder.layout_content.getLayoutParams().width = Utils.getScreenWidth(lContent);

        //删除按钮的方法
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = holder.getLayoutPosition();//获取要删除行的位置
                removeData(n);//删除列表中指定的行
            }
        });
    }

    //返回行的总数
    @Override
    public int getItemCount() {
        return listIcon.size();
    }

    //删除列表行中信息的方法
    public void removeData(int position){
        listIcon.remove(position);//删除图标
        listName.remove(position);//删除行中名字
        listInfo.remove(position);//删除信息
        notifyItemRemoved(position);//删除行
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView btn_delete;
        public TextView name, info;//名字与信息
        public ImageView img;//图标
        public ViewGroup layout_content;//图标与信息布局

        //获取控件
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            info = itemView.findViewById(R.id.info);
            img = itemView.findViewById(R.id.img);
            layout_content = itemView.findViewById(R.id.layout_content);
            btn_delete = itemView.findViewById(R.id.tv_delete);
        }
    }
}
