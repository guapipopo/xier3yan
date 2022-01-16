package com.example.bilibilimain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bilibilimain.Adapter.MypagerAdapter;
import com.example.bilibilimain.Adapter.RecyclerViewAdapter;
import com.example.bilibilimain.Adapter.ViewPager2Adapter;
import com.example.bilibilimain.Beans.itemBean;
import com.example.bilibilimain.Data.Datas;
import com.example.bilibilimain.Data.Views;
import com.example.bilibilimain.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView list;
    private ViewPager2 list2;
    private List<itemBean> datas;
    private RecyclerViewAdapter adapter;
    private  ViewPager2Adapter pagerAdater;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        initDate();

        initListener();


    }

    private void initListener() {
        adapter.setOnLongItemClickListener(new RecyclerViewAdapter.OnLongItemClickListener() {
            @Override
            public void onLongItemClick(int position) {
                Intent intent=new Intent(MainActivity.this,BilibiliDetail.class);
                intent.putExtra("Data",datas.get(position));
                intent.putExtra("Position", position);
                startActivityForResult(intent,1);
            }
    });
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mBinding.viewPager.setCurrentItem(position);
            }
        });
    }




    //创建模拟数据
    private void initDate(){
        //创建数据集合
        datas = new ArrayList<>();

        for(int i = 0; i< Datas.icons.length; i++){
            //创建数据对象
            itemBean date= new itemBean();
            date.name="up"+(i+1);
            date.icon=Datas.icons[i];
            date.pager= Views.icons[i];
            //添加到集合
            datas.add(date);
        }



        //创建适配器
        adapter=new RecyclerViewAdapter(datas);
        pagerAdater=new ViewPager2Adapter(datas,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.recy1.setLayoutManager(linearLayoutManager);
        //设置到recyclerView和ViewPager2
        mBinding.recy1.setAdapter(adapter);
        mBinding.viewPager.setAdapter(pagerAdater);

    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);
        if(requestCode==1&&resultCode==2){
            int num=intent.getIntExtra("delete",0);
            boolean state=intent.getBooleanExtra("delete2",true);
            Log.e("a", "onActivityResult: "+num );

            if(state) {

            }else {
                datas.remove(num);
                adapter.notifyDataSetChanged();
                pagerAdater.notifyDataSetChanged();
            }
        }
    }

}