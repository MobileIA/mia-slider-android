package com.mobileia.slider.adapter;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mobileia.slider.fragment.ImageFragment;

import java.util.ArrayList;

/**
 * Created by matiascamiletti on 12/9/17.
 */

public class ImageWithTextAdapter extends FragmentStatePagerAdapter {

    /**
     * Almacena las referencias a las imagenes
     */
    protected ArrayList<Integer> mValueImages = new ArrayList<>();
    /**
     * Almacena las referencias del titulo
     */
    protected ArrayList<Integer> mValueTitles = new ArrayList<>();
    /**
     * Almacena las referencias de la segunda linea
     */
    protected ArrayList<Integer> mValueCaptions = new ArrayList<>();
    /**
     * Almacena el margin de la parte inferior
     */
    protected int mMarginBottom = 0;

    /**
     * Constructor por defecto
     * @param fm
     */
    public ImageWithTextAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance(mValueImages.get(position), mValueTitles.get(position), mValueCaptions.get(position), mMarginBottom);
    }

    @Override
    public int getCount() {
        return mValueImages.size();
    }

    /**
     * Agrega una imagen
     * @param imageResource
     * @param titleResource
     * @param captionResource
     */
    public void add(@DrawableRes int imageResource, @StringRes int titleResource, @StringRes int captionResource){
        mValueImages.add(imageResource);
        mValueTitles.add(titleResource);
        mValueCaptions.add(captionResource);
        notifyDataSetChanged();
    }

    /**
     * Configura el margin de la parte inferior
     * @param bottom
     */
    public void setMarginBottom(int bottom){
        this.mMarginBottom = bottom;
        notifyDataSetChanged();
    }
}
