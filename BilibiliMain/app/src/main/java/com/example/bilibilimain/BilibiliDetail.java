package com.example.bilibilimain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.bilibilimain.Beans.itemBean;
import com.example.bilibilimain.databinding.ActivityBilibiliDetailBinding;

public class BilibiliDetail extends AppCompatActivity {
    private ActivityBilibiliDetailBinding detailBinding;
    private boolean mState;
    private int mPosition;
    private itemBean itemBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilibili_detail);

        detailBinding= DataBindingUtil.setContentView(this,R.layout.activity_bilibili_detail);

        detailBinding.attention.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(BilibiliDetail.this,isChecked?"关注成功":"取消关注",Toast.LENGTH_SHORT).show();
                if(!isChecked){
                    detailBinding.fans.setText("粉丝数:9999");
                    mState=false;
                    Log.e("a", "onCheckedChanged: false");
                }else{
                    detailBinding.fans.setText("粉丝数:10000");
                    mState=true;
                }
                Intent intent=new Intent(BilibiliDetail.this,MainActivity.class);
                intent.putExtra("delete",mPosition);
                intent.putExtra("delete2",mState);
                setResult(2,intent);
            }
        });

        itemBean=(itemBean) getIntent().getSerializableExtra("Data");
        detailBinding.icon.setImageResource(itemBean.icon);
        detailBinding.name.setText(itemBean.name);
        detailBinding.pager1.setImageResource(itemBean.pager);


        mPosition=getIntent().getIntExtra("Position",0);

        detailBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BilibiliDetail.this,MainActivity.class);
                intent.putExtra("Date",itemBean);
                intent.putExtra("Position",mPosition);
                finish();
            }
        });

    }
}