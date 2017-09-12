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

abstract public class SliderActivity extends AppCompatActivity {
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
    /**
     * Almacena contender de la vista superior
     */
    protected RelativeLayout mContainerTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        // Configurar vistas
        setUpViews();
        // Configurar vista de la parte inferior
        setUpCustomBottom();
        // Configurar vista de la parte superior
        setUpCustomTop();
    }

    /**
     * Infla la vista que se mostrara en la parte inferior
     */
    protected void setUpCustomBottom(){
        // Verificar si se asigno
        if(getBottomView() == 0){
            return;
        }
        // Inflamos la vista
        View view = LayoutInflater.from(this).inflate(getBottomView(), mContainerBottom, false);
        // La agregamos al contenedor
        mContainerBottom.addView(view);
    }

    /**
     * Infla la vista que se mostrara en la parte inferior
     */
    protected void setUpCustomTop(){
        // Verificar si se asigno
        if(getTopView() == 0){
            return;
        }
        // Inflamos la vista
        View view = LayoutInflater.from(this).inflate(getTopView(), mContainerTop, false);
        // La agregamos al contenedor
        mContainerTop.addView(view);
    }

    /**
     * Configura los elementos de la vista
     */
    protected void setUpViews(){
        // Obtenemos viewPager
        mViewPager = (ViewPager)findViewById(R.id.pager);
        // Creamos Adapter
        mAdapter = new ImageAdapter(getSupportFragmentManager());
        // Configuración custom del adapter
        setUpAdapter(mAdapter);
        // Asignamos el adapter
        mViewPager.setAdapter(mAdapter);
        // Asignamos la animación de paginado
        mViewPager.setPageTransformer(true, new FadePageTransformer());
        // Obtenemos el Indicador
        mIndicator = (Indicator)findViewById(R.id.indicator);
        // Setear el viewPager
        mIndicator.setViewPager(mViewPager);
        // Obtener contendor de la parte inferior
        mContainerBottom = (RelativeLayout)findViewById(R.id.container_bottom);
        // Obtener contendor de la parte superior
        mContainerTop = (RelativeLayout)findViewById(R.id.container_top);
    }

    abstract protected void setUpAdapter(ImageAdapter adapter);

    /**
     * Devuelve el recurso de layout para mostrar en la parte inferior de la pantalla
     * @return
     */
    protected int getBottomView(){ return 0; }

    /**
     * Devuelve el recurso de layout para mostrar en la parte superior de la pantalla
     * @return
     */
    protected int getTopView(){ return 0; }
}
