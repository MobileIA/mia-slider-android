package com.mobileia.slider.adapter;

import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mobileia.slider.fragment.ImageFragment;

import java.util.ArrayList;

/**
 * Created by matiascamiletti on 12/9/17.
 */

public class ImageAdapter extends FragmentStatePagerAdapter {
    /**
     * Almacena las referencias a las imagenes
     */
    protected ArrayList<Integer> mValues = new ArrayList<>();

    /**
     * Constructor por defecto
     * @param fm
     */
    public ImageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance(mValues.get(position));
    }

    @Override
    public int getCount() {
        return mValues.size();
    }

    /**
     * Agrega una imagen al slider
     * @param imageResource
     */
    public void add(@DrawableRes int imageResource){
        mValues.add(imageResource);
        notifyDataSetChanged();
    }

    /**
     * Agregar un listado de imagenes
     * @param images
     */
    public void add(ArrayList<Integer> images){
        mValues.addAll(images);
        notifyDataSetChanged();
    }
}
