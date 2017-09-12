package com.mobileia.slider.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobileia.slider.R;

import org.w3c.dom.Text;

/**
 * Created by matiascamiletti on 12/9/17.
 */

public class ImageFragment extends Fragment {
    /**
     * Constante para el parametro de Imagen
     */
    public static final String ARGUMENT_IMAGE_REFERENCE = "ARGUMENT_IMAGE_REFERENCE";
    /**
     * Constante para el parametro del titulo
     */
    public static final String ARGUMENT_TITLE_REFERENCE = "ARGUMENT_TITLE_REFERENCE";
    /**
     * Constante para el parametro del descripción
     */
    public static final String ARGUMENT_CAPTION_REFERENCE = "ARGUMENT_CAPTION_REFERENCE";
    /**
     * Constante para el parametro de margen inferior
     */
    public static final String ARGUMENT_MARGIN_BOTTOM = "ARGUMENT_MARGIN_BOTTOM";
    /**
     * Almacena el recurso de imagen
     */
    protected int mImageResource;
    /**
     * Almacena del titulo
     */
    protected int mTitleResource = 0;
    /**
     * Almacena la descripción
     */
    protected int mCaptionResource = 0;
    /**
     * Almacena el margin de la parte inferior
     */
    protected int mMarginBottom = 0;

    /**
     * Crea el fragment de imagen
     * @param imageResource
     * @return
     */
    public static ImageFragment newInstance(int imageResource, int titleResource, int captionResource, int marginBottom){
        // Creamos Fragment
        ImageFragment fragment = new ImageFragment();
        // Creamos bundle para los parametros
        Bundle args = new Bundle();
        // Seteamos la imagen seteada
        args.putInt(ARGUMENT_IMAGE_REFERENCE, imageResource);
        // Seteamos el titulo
        args.putInt(ARGUMENT_TITLE_REFERENCE, titleResource);
        // Seteamos el caption
        args.putInt(ARGUMENT_CAPTION_REFERENCE, captionResource);
        // Seteamos el margen
        args.putInt(ARGUMENT_MARGIN_BOTTOM, marginBottom);
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
            // Obtenemos titulo
            mTitleResource = getArguments().getInt(ARGUMENT_TITLE_REFERENCE, 0);
            // Obtenemos descripcion
            mCaptionResource = getArguments().getInt(ARGUMENT_CAPTION_REFERENCE, 0);
            // Obtenemos el margen inferior
            mMarginBottom = getArguments().getInt(ARGUMENT_MARGIN_BOTTOM, 0);
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
        // Configuramos el container
        setUpContainer((LinearLayout)rootView.findViewById(R.id.container));
        // Configuramos imagen
        setUpImage((ImageView)rootView.findViewById(R.id.image));
        // Configuramos el titulo
        setUpTitle((TextView)rootView.findViewById(R.id.title));
        // Configuramos el texto
        setUpCaption((TextView)rootView.findViewById(R.id.caption));
        // Devolvemos view
        return rootView;
    }

    /**
     * Configura el contenedor del texto
     * @param container
     */
    protected void setUpContainer(LinearLayout container){
        // Creamos parametros del layout
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        // Setemoas que se conecte a la parte inferior
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        params.setMargins(0, 0, 0, mMarginBottom);
        // Setemoas nuevos parametros al contenedor
        container.setLayoutParams(params);
    }

    /**
     * Configura la vista de la imagen de fondo
     * @param image
     */
    protected void setUpImage(ImageView image){
        // Seteamos la imagen
        image.setImageResource(mImageResource);
    }

    /**
     * Configura la vista del titulo
     * @param title
     */
    protected void setUpTitle(TextView title){
        if(mTitleResource == 0){
            return;
        }
        // Seteamos el titulo
        title.setText(mTitleResource);
    }

    /**
     * Configura la vista de la descripción
     * @param caption
     */
    protected void setUpCaption(TextView caption){
        if(mCaptionResource == 0){
            return;
        }
        // Seteamos el titulo
        caption.setText(mCaptionResource);
    }
}
