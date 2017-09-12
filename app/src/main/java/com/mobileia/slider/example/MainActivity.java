package com.mobileia.slider.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mobileia.slider.activity.SliderActivity;
import com.mobileia.slider.adapter.ImageAdapter;

import java.util.ArrayList;

public class MainActivity extends SliderActivity {

    @Override
    protected void setUpAdapter(ImageAdapter adapter) {
        adapter.add(R.drawable.image1);
        adapter.add(R.drawable.image2);
        adapter.add(R.drawable.image3);
        adapter.add(R.drawable.image4);
        adapter.add(R.drawable.image5);
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
