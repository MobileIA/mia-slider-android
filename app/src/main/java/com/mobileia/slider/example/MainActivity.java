package com.mobileia.slider.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mobileia.slider.activity.SliderActivity;

import java.util.ArrayList;

public class MainActivity extends SliderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter.add(R.drawable.image1);
        mAdapter.add(R.drawable.image2);
        mAdapter.add(R.drawable.image3);
        mAdapter.add(R.drawable.image4);
        mAdapter.add(R.drawable.image5);
    }

    @Override
    protected int getBottomView() {
        return R.layout.item_bottom;
    }

    @Override
    protected int getTopView() {
        return R.layout.item_top;
    }
}
