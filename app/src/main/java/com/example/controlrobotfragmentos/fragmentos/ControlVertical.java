package com.example.controlrobotfragmentos.fragmentos;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

public class ControlVertical extends Fragment implements View.OnClickListener,View.OnLongClickListener {
    ImageButton btnarriba;
    ImageButton btnabajo;
    ImageButton btnderecha;
    ImageButton btnizquierda;
    ImageButton btnstart;
    ImageButton btnstop;
    ImageButton btnfrenox;
    ImageButton btnbluetooth;
    ImageButton btnluces;
    ImageButton btnrobot;
    ImageView focoestado;
    SeekBar seekBarVertical;
    PopupWindow popupWindow;

    boolean estadoInicial=false;
    boolean estadoluces=false;
    boolean estadoblooth=false;
    boolean estadostop2=false;
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer2;
    private MediaPlayer mediaPlayer3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_control_vertical, container, false);
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.turbo);
        mediaPlayer2 = MediaPlayer.create(getActivity(), R.raw.encender);
        mediaPlayer3 = MediaPlayer.create(getActivity(), R.raw.plus);

        variable(view);
        Action();
        velocidad();
        return view;
    }

    public void variable(View view){
        btnarriba=view.findViewById(R.id.btnarriba);
        btnabajo=view.findViewById(R.id.btnabajo);
        btnderecha=view.findViewById(R.id.btnderecha);
        btnizquierda=view.findViewById(R.id.btnizquierda);
        btnfrenox =view.findViewById(R.id.btnfrenox);
        btnbluetooth=view.findViewById(R.id.btnbluetooth);
        btnstop=view.findViewById(R.id.btnstop);
        btnstart=view.findViewById(R.id.btnstart);
        btnrobot=view.findViewById(R.id.btnrobot);
        btnluces=view.findViewById(R.id.btnluces);
        focoestado=view.findViewById(R.id.focoestado);
        seekBarVertical=view.findViewById(R.id.seekBarVertical);
    }
    public void Action(){
        btnarriba.setOnClickListener(this);
        btnabajo.setOnClickListener(this);
        btnderecha.setOnClickListener(this);
        btnizquierda.setOnClickListener(this);
        btnfrenox.setOnClickListener(this);
        btnbluetooth.setOnClickListener(this);
        btnstop.setOnClickListener(this);
        btnstart.setOnClickListener(this);
        btnrobot.setOnClickListener(this);
        btnluces.setOnClickListener(this);

        btnrobot.setOnLongClickListener(this);
        btnstop.setOnLongClickListener(this);
    }
    @Override
    public void onClick(View v){

        if(v==btnstart){
            estadoInicial=!estadoInicial;
            cambiarImagenInicial(estadoInicial,1);
            vibrar();
        }
        if(v== btnfrenox){
            seekBarVertical.setProgress(0);
            vibrar();
        }
        if(v==btnrobot){
            popup(btnrobot);
            vibrar();
        }
        if(v==btnstop){
            vibrar();

        }
        if(v==btnarriba){
            vibrar();
        }
        if(v==btnabajo){
            vibrar();
        }
        if(v==btnderecha){
            vibrar();
        }
        if(v==btnizquierda){
            vibrar();
        }
        if(v==btnbluetooth){

            estadoblooth=!estadoblooth;
            if(estadoblooth){
                btnbluetooth.setImageResource(R.drawable.abc);
            }
            if(!estadoblooth){
                btnbluetooth.setImageResource(R.drawable.abcoff);
            }
            vibrar();
        }
        if(v==btnluces){
            estadoluces=!estadoluces;
            if(estadoluces){
                btnluces.setImageResource(R.drawable.lucesonn);
            }
            if(!estadoluces){
                btnluces.setImageResource(R.drawable.luces);
            }
            vibrar();
        }
    }
    @Override
    public boolean onLongClick(View v) {
        if(v==btnrobot){
            vibrar2();
            popuplargo(btnrobot);
            mediaPlayer3.start();
            return true;
        }
        if(v==btnstop){
            estadostop2=!estadostop2;
            cambiarImagenInicial(estadostop2,2);
            seekBarVertical.setProgress(0);

            vibrar2();
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
            mediaPlayer2.start();
            estadooN();
        }
        if(estado==false && numero==2){
            estadooff();
            cambiarImagenStopRojo();
        }
        if(estado==true && numero==2){
            cambiarImagenIniciaOnn();
            estadooN();
        }
    }
    private void cambiarImagenStopRojo(){
        btnarriba.setImageResource(R.drawable.direccionarribaroja);
        btnderecha.setImageResource(R.drawable.direccionderecharoja);
        btnizquierda.setImageResource(R.drawable.direccionizquierdaroja);
        btnabajo.setImageResource(R.drawable.direccionabajoroja);
        btnfrenox.setImageResource(R.drawable.frenorojo);
        btnbluetooth.setImageResource(R.drawable.abcrojo);
        btnluces.setImageResource(R.drawable.lucesrojo);
        btnrobot.setImageResource(R.drawable.imagenrobotrojo);
    }
    private void cambiarImagenIniciaOnn(){
        btnarriba.setImageResource(R.drawable.direccionarriba);
        btnabajo.setImageResource(R.drawable.direccionabajo);
        btnderecha.setImageResource(R.drawable.direccionderecha);
        btnizquierda.setImageResource(R.drawable.direccionizquierda);
        btnstart.setImageResource(R.drawable.start);
        focoestado.setImageResource(R.drawable.verde);
        btnluces.setImageResource(R.drawable.lucesonn);
        btnrobot.setImageResource(R.drawable.imagenrobotonn);
        btnbluetooth.setImageResource(R.drawable.abc);
        btnfrenox.setImageResource(R.drawable.frenoazul);
        btnstop.setImageResource(R.drawable.frenadodeemergencia);
    }
    private void cambiarImagenInicialoff(){
        btnarriba.setImageResource(R.drawable.direccionarribaoff);
        btnabajo.setImageResource(R.drawable.direccionabajooff);
        btnderecha.setImageResource(R.drawable.direccionderechaoff);
        btnizquierda.setImageResource(R.drawable.direccionizquierdaoff);
        btnstart.setImageResource(R.drawable.startrojo);
        focoestado.setImageResource(R.drawable.rojo);
        btnluces.setImageResource(R.drawable.luces);
        btnrobot.setImageResource(R.drawable.imagenrobot);
        btnbluetooth.setImageResource(R.drawable.abcoff);
        btnfrenox.setImageResource(R.drawable.frenooff);
        btnstop.setImageResource(R.drawable.stopoff);
    }
    private void vibrar()
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
    private void vibrar2() {
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
    private void velocidad ()
    {
        // Set listener to track seek bar changes
        seekBarVertical.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar velocidad, int progress, boolean fromUser)
            {
                // Update background color based on progress
                if (progress == 0) {
                    velocidad.setBackgroundColor(Color.RED);
                    vibrar();
                }
                if (progress >= 1 && progress < 25) {
                    velocidad.setBackgroundColor(Color.YELLOW);
                    mediaPlayer.start();
                    vibrar();
                } else if (progress >= 25 && progress < 50) {
                    velocidad.setBackgroundColor(Color.parseColor("#FFA500")); // Orange
                    mediaPlayer.start();
                    vibrar();
                } else if (progress >= 50 && progress < 75) {
                    velocidad.setBackgroundColor(Color.GREEN);
                    vibrar();
                    mediaPlayer.start();
                } else if (progress >= 100){
                    velocidad.setBackgroundColor(Color.BLUE);
                    mediaPlayer.start();
                    vibrar();
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
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (mediaPlayer2 != null) {
            mediaPlayer2.release();
            mediaPlayer2 = null;
        }
        if(mediaPlayer3!=null){
            mediaPlayer3.release();
            mediaPlayer3=null;
        }
    }
    private void estadooff(){
       estadoInicial=false;
       estadoluces=false;
       estadoblooth=false;
       estadostop2=false;
    }
    private void estadooN(){
        estadoInicial=true;
        estadoluces=true;
        estadoblooth=true;
        estadostop2=true;
    }
}