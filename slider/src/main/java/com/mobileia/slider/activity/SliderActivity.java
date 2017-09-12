package com.mobileia.slider.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.mobileia.slider.R;
import com.mobileia.slider.adapter.ImageAdapter;
import com.mobileia.slider.transformer.FadePageTransformer;
import com.mobileia.slider.view.Indicator;

/**
 * Created by matiascamiletti on 12/9/17.
 */

public class SliderActivity extends AppCompatActivity implements ImageAdapter.OnNotifyAdapter {
    /**
     * Almacena viewPager
     */
    protected ViewPager mViewPager;
    /**
     * Almacena el adapter del slider
     */
    protected ImageAdapter mAdapter;
    /**
     * Almacena el indicador de page
     */
    protected Indicator mIndicator;
    /**
     * Almacena contender de la vista inferior
     */
    protected RelativeLayout mContainerBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        // Configurar vistas
        setUpViews();
        // Configurar vista de la parte inferior
        setUpCustomBottom();
    }

    /**
     * Informa cuando se agregan nuevos items al pager
     */
    @Override
    public void onNewItems() {
        // Asignamos viewPager para calcular los indicadores
        mIndicator.setViewPager(mViewPager);
    }

    /**
     * Infla la vista que se mostrara en la parte inferior
     */
    protected void setUpCustomBottom(){
        // Inflamos la vista
        View view = LayoutInflater.from(this).inflate(getBottomView(), mContainerBottom, false);
        // La agregamos al contenedor
        mContainerBottom.addView(view);
    }

    /**
     * Configura los elementos de la vista
     */
    protected void setUpViews(){
        // Obtenemos viewPager
        mViewPager = (ViewPager)findViewById(R.id.pager);
        // Creamos Adapter
        mAdapter = new ImageAdapter(getSupportFragmentManager());
        mAdapter.setOnNotifyAdapter(this);
        // Asignamos el adapter
        mViewPager.setAdapter(mAdapter);
        // Asignamos la animación de paginado
        mViewPager.setPageTransformer(true, new FadePageTransformer());
        // Obtenemos el Indicador
        mIndicator = (Indicator)findViewById(R.id.indicator);
        // Obtener contendor de la parte inferior
        mContainerBottom = (RelativeLayout)findViewById(R.id.container_bottom);
    }

    /**
     * Devuelve el recurso de layout para mostrar en la parte inferior de la pantalla
     * @return
     */
    protected int getBottomView(){ return 0; }
}