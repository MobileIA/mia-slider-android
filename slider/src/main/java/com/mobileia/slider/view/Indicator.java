package com.mobileia.slider.view;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import com.mobileia.slider.R;

/**
 * Created by matiascamiletti on 12/9/17.
 */

public class Indicator extends LinearLayout {
    /**
     * Almacena viewPager
     */
    protected ViewPager mViewPager;
    /**
     * Almacena la ultima posicion
     */
    protected int mLastPosition = -1;

    protected Animator mAnimatorOut;
    protected Animator mAnimatorIn;
    protected Animator mImmediateAnimatorOut;
    protected Animator mImmediateAnimatorIn;

    /**
     * Constructor
     * @param context
     */
    public Indicator(Context context) {
        super(context);
        init();
    }

    public Indicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Indicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * Funcion inicial
     */
    protected void init(){
        // Configuramos Gravedad
        setGravity(Gravity.CENTER);

        mAnimatorOut = createAnimatorOut(getContext());
        mImmediateAnimatorOut = createAnimatorOut(getContext());
        mImmediateAnimatorOut.setDuration(0);

        mAnimatorIn = createAnimatorIn(getContext());
        mImmediateAnimatorIn = createAnimatorIn(getContext());
        mImmediateAnimatorIn.setDuration(0);
    }

    private final ViewPager.OnPageChangeListener mInternalPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override public void onPageSelected(int position) {

            if (mViewPager.getAdapter() == null || mViewPager.getAdapter().getCount() <= 0) {
                return;
            }

            if (mAnimatorIn.isRunning()) {
                mAnimatorIn.end();
                mAnimatorIn.cancel();
            }

            if (mAnimatorOut.isRunning()) {
                mAnimatorOut.end();
                mAnimatorOut.cancel();
            }

            View currentIndicator;
            if (mLastPosition >= 0 && (currentIndicator = getChildAt(mLastPosition)) != null) {
                currentIndicator.setBackgroundResource(R.drawable.white_radius);
                mAnimatorIn.setTarget(currentIndicator);
                mAnimatorIn.start();
            }

            View selectedIndicator = getChildAt(position);
            if (selectedIndicator != null) {
                selectedIndicator.setBackgroundResource(R.drawable.white_radius);
                mAnimatorOut.setTarget(selectedIndicator);
                mAnimatorOut.start();
            }
            mLastPosition = position;
        }

        @Override public void onPageScrollStateChanged(int state) {
        }
    };

    public void setViewPager(ViewPager viewPager){
        // Guardamos view Pager
        mViewPager = viewPager;
        // Verificamos que contenga adapter
        if(mViewPager == null || mViewPager.getAdapter() == null){
            return;
        }
        // Reseteamos posicion
        mLastPosition = -1;
        // Creamos los indicadores
        createIndicators();

        mViewPager.removeOnPageChangeListener(mInternalPageChangeListener);
        mViewPager.addOnPageChangeListener(mInternalPageChangeListener);
        mInternalPageChangeListener.onPageSelected(mViewPager.getCurrentItem());
    }

    protected void createIndicators(){
        // Eliminamos todas las vistas
        removeAllViews();
        // Obtenemos el total de items
        int count = mViewPager.getAdapter().getCount();
        // Verificamos si contiene mas de 1
        if (count <= 0) {
            return;
        }
        // Obtenemos item activo
        int currentItem = mViewPager.getCurrentItem();
        // Recorremos la cantidad de items
        for (int i = 0; i < count; i++) {
            if (currentItem == i) {
                addIndicator(R.drawable.white_radius, mImmediateAnimatorOut);
            } else {
                addIndicator(R.drawable.white_radius, mImmediateAnimatorIn);
            }
        }
    }

    protected void addIndicator(@DrawableRes int backgroundDrawableId, Animator animator){
        if (animator.isRunning()) {
            animator.end();
            animator.cancel();
        }

        View Indicator = new View(getContext());
        Indicator.setBackgroundResource(backgroundDrawableId);
        addView(Indicator, dip2px(5), dip2px(5));
        LayoutParams lp = (LayoutParams) Indicator.getLayoutParams();

        lp.leftMargin = dip2px(5);
        lp.rightMargin = dip2px(5);

        Indicator.setLayoutParams(lp);

        animator.setTarget(Indicator);
        animator.start();
    }

    protected Animator createAnimatorOut(Context context) {
        return AnimatorInflater.loadAnimator(context, R.animator.scale_with_alpha);
    }

    protected Animator createAnimatorIn(Context context) {
        Animator animatorIn;
        animatorIn = AnimatorInflater.loadAnimator(context, R.animator.scale_with_alpha);
        animatorIn.setInterpolator(new ReverseInterpolator());
        return animatorIn;
    }

    public DataSetObserver getDataSetObserver() {
        return mInternalDataSetObserver;
    }

    private DataSetObserver mInternalDataSetObserver = new DataSetObserver() {
        @Override public void onChanged() {
            super.onChanged();
            if (mViewPager == null) {
                return;
            }

            int newCount = mViewPager.getAdapter().getCount();
            int currentCount = getChildCount();

            if (newCount == currentCount) {  // No change
                return;
            } else if (mLastPosition < newCount) {
                mLastPosition = mViewPager.getCurrentItem();
            } else {
                mLastPosition = -1;
            }

            createIndicators();
        }
    };

    private class ReverseInterpolator implements Interpolator {
        @Override public float getInterpolation(float value) {
            return Math.abs(1.0f - value);
        }
    }

    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
