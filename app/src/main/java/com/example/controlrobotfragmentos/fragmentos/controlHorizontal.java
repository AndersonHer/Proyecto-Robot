package com.example.controlrobotfragmentos.fragmentos;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.media.MediaPlayer;


import com.example.controlrobotfragmentos.R;


public class controlHorizontal extends Fragment implements View.OnClickListener, View.OnLongClickListener{
    ImageButton botonarriba;
    ImageButton botonabajo;
    ImageButton botonderecha;
    ImageButton botonizquierda;
    ImageButton botonluces;
    ImageButton botonbluetooth;
    ImageButton botonstart;
    ImageButton botonstop;
    ImageButton botonrobot;
    ImageButton botonfreno;
    ImageView imagenestado;
    SeekBar seekBar1;
    PopupWindow popupWindow;
    PopupWindow popupWindow5;
    boolean estadoStop=false;
    boolean estadocomienzo;
    private boolean estadoluces=false;
    boolean estadoblootu=false;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer2;
    private MediaPlayer mediaPlayer3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_control_horizontal, container, false);
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.turbo);
        mediaPlayer2 = MediaPlayer.create(getActivity(), R.raw.encender);
        mediaPlayer3 = MediaPlayer.create(getActivity(), R.raw.plus);

        popup5(view);
        variable(view);
        Action();
        velocidad();
        return view;
    }

    public void variable(View view){
        botonarriba=view.findViewById(R.id.botonarriba);
        botonabajo=view.findViewById(R.id.botonabajo);
        botonderecha=view.findViewById(R.id.botonderecha);
        botonizquierda=view.findViewById(R.id.botonizquierda);
        botonstart=view.findViewById(R.id.botonstart);
        botonbluetooth=view.findViewById(R.id.botonbluetooth);
        botonluces=view.findViewById(R.id.botonluces);
        botonstop=view.findViewById(R.id.botonstop);
        botonrobot=view.findViewById(R.id.botonrobot);
        botonfreno=view.findViewById(R.id.botonfreno);
        imagenestado=view.findViewById(R.id.imagenestado);
        seekBar1=view.findViewById(R.id.seekBar1);

    }
    public void Action(){
        botonarriba.setOnClickListener(this);
        botonabajo.setOnClickListener(this);
        botonderecha.setOnClickListener(this);
        botonizquierda.setOnClickListener(this);
        botonstart.setOnClickListener(this);
        botonbluetooth.setOnClickListener(this);
        botonluces.setOnClickListener(this);
        botonstop.setOnClickListener(this);
        botonrobot.setOnClickListener(this);
        botonfreno.setOnClickListener(this);

        botonrobot.setOnLongClickListener(this);
        botonstop.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v==botonstart){
            estadocomienzo=!estadocomienzo;
            cambiarImagenInicial(estadocomienzo,1);
            vibrarCorto();
        }
        if(v==botonfreno){
            seekBar1.setProgress(0);
            vibrarCorto();
        }
        if(v==botonabajo){
            vibrarCorto();
        }
        if(v==botonarriba){
            vibrarCorto();
        }
        if(v==botonizquierda){
            vibrarCorto();
        }
        if(v==botonderecha){
            vibrarCorto();
        }
        if(v==botonbluetooth){
            estadoblootu=!estadoblootu;
            if(estadoblootu){
                botonbluetooth.setImageResource(R.drawable.abc);
            }
            if(!estadoblootu){
                botonbluetooth.setImageResource(R.drawable.abcoff);
            }
            vibrarCorto();
        }
        if(v==botonluces){
            estadoluces=!estadoluces;
            if(estadoluces){
                botonluces.setImageResource(R.drawable.lucesonn);
            }
            if(!estadoluces){
                botonluces.setImageResource(R.drawable.luces);
            }
            vibrarCorto();
        }
        if(v==botonrobot){
            popup(botonrobot);
            vibrarCorto();
        }

    }
    @Override
    public boolean onLongClick(View v) {

        if(v==botonstop){
            seekBar1.setProgress(0);
            estadoStop=!estadoStop;
            cambiarImagenInicial(estadoStop,2);
            vibrarLargo();
        }
        if(v==botonrobot){
            vibrarLargo();
            popuplargo(botonrobot);
            mediaPlayer3.start();
            return true;
        }
        return false;
    }

    private void cambiarImagenInicial(boolean estado,int numero){
        if(estado==false && numero==1){
            cambiarImagenInicialoff();
            estadooff();
        }
        if(estado==true && numero==1){
            cambiarImagenIniciaOnn();
            estadooN();
            mediaPlayer2.start();
        }
        if(estado==false && numero==2){
            cambiarImagenStopRojo();
            estadooff();
        }
        if(estado==true && numero==2){
            cambiarImagenIniciaOnn();
            estadooN();
        }
    }
    private void cambiarImagenStopRojo(){
        botonarriba.setImageResource(R.drawable.direccionarribaroja);
        botonderecha.setImageResource(R.drawable.direccionderecharoja);
        botonizquierda.setImageResource(R.drawable.direccionizquierdaroja);
        botonabajo.setImageResource(R.drawable.direccionabajoroja);
        botonfreno.setImageResource(R.drawable.frenorojo);
        botonbluetooth.setImageResource(R.drawable.abcrojo);
        botonluces.setImageResource(R.drawable.lucesrojo);
        botonrobot.setImageResource(R.drawable.imagenrobotrojo);
    }

    private void cambiarImagenIniciaOnn(){
        botonarriba.setImageResource(R.drawable.direccionarriba);
        botonabajo.setImageResource(R.drawable.direccionabajo);
        botonderecha.setImageResource(R.drawable.direccionderecha);
        botonizquierda.setImageResource(R.drawable.direccionizquierda);
        botonstart.setImageResource(R.drawable.start);
        imagenestado.setImageResource(R.drawable.verde);
        botonluces.setImageResource(R.drawable.lucesonn);
        botonrobot.setImageResource(R.drawable.imagenrobotonn);
        botonbluetooth.setImageResource(R.drawable.abc);
        botonstop.setImageResource(R.drawable.frenadodeemergencia);
        botonfreno.setImageResource(R.drawable.frenoazul);
    }
    private void cambiarImagenInicialoff(){
        botonarriba.setImageResource(R.drawable.direccionarribaoff);
        botonabajo.setImageResource(R.drawable.direccionabajooff);
        botonderecha.setImageResource(R.drawable.direccionderechaoff);
        botonizquierda.setImageResource(R.drawable.direccionizquierdaoff);
        botonstart.setImageResource(R.drawable.startrojo);
        imagenestado.setImageResource(R.drawable.rojo);
        botonluces.setImageResource(R.drawable.luces);
        botonrobot.setImageResource(R.drawable.imagenrobot);
        botonbluetooth.setImageResource(R.drawable.abcoff);
        botonstop.setImageResource(R.drawable.stopoff);
        botonfreno.setImageResource(R.drawable.frenooff);
        seekBar1.setProgress(0);
    }



    public void popup (ImageButton posicion)
    {
        // Crea un objeto LayoutInflater para inflar el layout del popup
        LayoutInflater inflater = (LayoutInflater)
                getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Infla el layout del popup
        View popupView = inflater.inflate(R.layout.pop_up_horizontal, null);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        // Inicializa el PopupWindow con el layout inflado
        popupWindow = new PopupWindow(popupView, width, height, focusable);
        // Establece el listener de toque para el layout del popup
        popupView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                //Cierra la ventana emergente cuando se toca fuera de ella
                popupWindow.dismiss();
                return true;
            }
        });
        //Muestra la ventana emergente en el centro de la pantalla
        popupWindow.showAtLocation(posicion, Gravity.TOP, 0, 0);
    }
    private void popuplargo(ImageButton posicion){
        // Crea un objeto LayoutInflater para inflar el layout del popup
        LayoutInflater inflater = (LayoutInflater)
                getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Infla el layout del popup
        View popupView = inflater.inflate(R.layout.pop_up_horizontal_reglas, null);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        // Inicializa el PopupWindow con el layout inflado
        popupWindow = new PopupWindow(popupView, width, height, focusable);
        // Establece el listener de toque para el layout del popup
        popupView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                //Cierra la ventana emergente cuando se toca fuera de ella
                popupWindow.dismiss();
                return true;
            }
        });
        //Muestra la ventana emergente en el centro de la pantalla
        popupWindow.showAtLocation(posicion, Gravity.CENTER, 0, 0);
    }
    private void velocidad ()
    {
        // Set listener to track seek bar changes
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar velocidad, int progress, boolean fromUser)
            {
                // Update background color based on progress
                if (progress == 0) {
                    velocidad.setBackgroundColor(Color.RED);
                    vibrarCorto();
                }
                if (progress >= 1 && progress < 25) {
                    velocidad.setBackgroundColor(Color.YELLOW);
                    mediaPlayer.start();
                    vibrarCorto();
                } else if (progress >= 25 && progress < 50) {
                    velocidad.setBackgroundColor(Color.parseColor("#FFA500")); // Orange
                    mediaPlayer.start();
                    vibrarCorto();
                } else if (progress >= 50 && progress < 75) {
                    velocidad.setBackgroundColor(Color.GREEN);
                    mediaPlayer.start();
                    vibrarCorto();
                } else if (progress >= 100){
                    velocidad.setBackgroundColor(Color.BLUE);
                    mediaPlayer.start();
                    vibrarCorto();
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar velocidad)
            {
            }
            @Override
            public void onStopTrackingTouch(SeekBar velocidad)
            {
            }
        });
    }


    private void vibrarCorto()
    {
        // Obtener una referencia al servicio de vibración
        Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        // Verificar si el dispositivo soporta la vibración
        if (vibrator != null && vibrator.hasVibrator())
        {
            // Duración de la vibración en milisegundos
            final int vibracionDuracion = 100;
            // Hacer vibrar el dispositivo
            vibrator.vibrate(vibracionDuracion);
        }
    }
    private void vibrarLargo(){
        // Obtener una referencia al servicio de vibración
        Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        // Verificar si el dispositivo soporta la vibración
        if (vibrator != null && vibrator.hasVibrator())
        {
            // Duración de la vibración en milisegundos
            final int vibracionDuracion = 700;
            // Hacer vibrar el dispositivo
            vibrator.vibrate(vibracionDuracion);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if(mediaPlayer2!=null){
            mediaPlayer2.release();
            mediaPlayer2 = null;
        }
        if(mediaPlayer3!=null){
            mediaPlayer3.release();
            mediaPlayer3=null;
        }
    }

    public void popup5(View v)
    {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pop_up_inicial, null);
        // Inicializar el PopupWindow
        int width = ViewGroup.LayoutParams.WRAP_CONTENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        popupWindow5 = new PopupWindow(popupView, width, height, focusable);
        // Mostrar la ventana emergente en el centro de la pantalla
        popupWindow5.showAtLocation(v, Gravity.CENTER, 0, 0);
        // Cerrar la ventana emergente después de 5 segundos
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                if (popupWindow5 != null && popupWindow5.isShowing())
                {
                    popupWindow5.dismiss();
                }
            }
        }, 5000); //segundos que aparecera en la pantalla
    }
    private void estadooff(){
        estadoStop=false;
        estadoluces=false;
        estadoblootu=false;
        estadocomienzo=false;
    }
    private void estadooN(){
        estadoStop=true;
        estadoluces=true;
        estadoblootu=true;
        estadocomienzo=true;
    }
}