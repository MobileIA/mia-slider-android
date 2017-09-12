package com.mobileia.slider.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mobileia.slider.R;

/**
 * Created by matiascamiletti on 12/9/17.
 */

public class ImageFragment extends Fragment {
    /**
     * Constante para el parametro de Imagen
     */
    public static final String ARGUMENT_IMAGE_REFERENCE = "ARGUMENT_IMAGE_REFERENCE";
    /**
     * Almacena el recurso de imagen
     */
    protected int mImageResource;

    /**
     * Crea el fragment de imagen
     * @param imageResource
     * @return
     */
    public static ImageFragment newInstance(int imageResource){
        // Creamos Fragment
        ImageFragment fragment = new ImageFragment();
        // Creamos bundle para los parametros
        Bundle args = new Bundle();
        // Seteamos la imagen seteada
        args.putInt(ARGUMENT_IMAGE_REFERENCE, imageResource);
        // Agregamos los parametros
        fragment.setArguments(args);
        // Devolvemos el fragment creado
        return fragment;
    }

    /**
     * Constructor por defecto
     */
    public ImageFragment(){}

    /**
     * Funcion que se ejecuta al crear el fragment
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Verificamos si se envio la imagen
        if(getArguments() != null){
            // Obtenemos imagen enviada
            mImageResource = getArguments().getInt(ARGUMENT_IMAGE_REFERENCE);
        }
    }

    /**
     * Funcion que se encarga de crear la vista
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflamos XML
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_image, container, false);
        // Obtenemos vista de la imagen
        ImageView image = (ImageView)rootView.findViewById(R.id.image);
        // Seteamos la imagen
        image.setImageResource(mImageResource);
        // Devolvemos view
        return rootView;
    }
}
