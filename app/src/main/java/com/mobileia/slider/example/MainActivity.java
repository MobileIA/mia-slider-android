package com.mobileia.slider.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mobileia.slider.activity.SliderActivity;
import com.mobileia.slider.adapter.ImageAdapter;
import com.mobileia.slider.adapter.ImageWithTextAdapter;

import java.util.ArrayList;

public class MainActivity extends SliderActivity {

    @Override
    protected void setUpAdapter(ImageWithTextAdapter adapter) {
        adapter.add(R.drawable.image1, R.string.title_one, R.string.caption_one);
        adapter.add(R.drawable.image2, R.string.title_two, R.string.caption_two);
        adapter.add(R.drawable.image3, R.string.title_three, R.string.caption_three);
        adapter.add(R.drawable.image4, R.string.title_one, R.string.caption_one);
        adapter.add(R.drawable.image5, R.string.title_one, R.string.caption_one);
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
