package com.example.controlrobotfragmentos.fragmentos;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Vibrator;
import android.speech.RecognizerIntent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.Toast;
import android.media.MediaPlayer;
import com.example.controlrobotfragmentos.R;

import java.util.ArrayList;


public class ControlVoz extends Fragment implements View.OnClickListener,View.OnLongClickListener{
    ImageButton btrobot;
    ImageButton btbluetooth;
    ImageButton btvoz;
    ImageButton btstop;
    ImageButton btstart;
    ImageButton btluces;
    ImageButton btrecargar;
    ImageButton btfreno;
    SeekBar seekBarvelocidad;
    PopupWindow popupWindow;


    boolean estado11=false;
    private boolean estadoblootho=false;
    private boolean estadoLuces=false;
    private boolean estadoStop=false;

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer2;
    private MediaPlayer mediaPlayer3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_control_voz, container, false);
        // Inflate the layout for this fragment
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.turbo);
        mediaPlayer2 = MediaPlayer.create(getActivity(), R.raw.encender);
        mediaPlayer3 = MediaPlayer.create(getActivity(), R.raw.plus);


        variables_botones(view);
        botones_accion();
        velocidad();
        return view;
    }


    private void variables_botones(View view)
    {
        btrobot=view.findViewById(R.id.btrobot);
        btbluetooth=view.findViewById(R.id.btbluetooth);
        btvoz=view.findViewById(R.id.btvoz);
        btstart=view.findViewById(R.id.btstart);
        btstop=view.findViewById(R.id.btstop);
        btluces=view.findViewById(R.id.btluces);
        seekBarvelocidad=view.findViewById(R.id.seekBarvelocidad);
        btrecargar=view.findViewById(R.id.btrecargar);
        btfreno=view.findViewById(R.id.btfreno);
    }


    private void botones_accion()
    {
        //click corto
        btrobot.setOnClickListener(this);
        btstop.setOnClickListener(this);
        btstart.setOnClickListener(this);
        btluces.setOnClickListener(this);
        btbluetooth.setOnClickListener(this);
        btvoz.setOnClickListener(v -> startVoiceRecognition());
        btfreno.setOnClickListener(this);
        //click largo
        btbluetooth.setOnLongClickListener(this);
        btrobot.setOnLongClickListener(this);
        btstop.setOnLongClickListener(this);
        btrecargar.setOnLongClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v==btstart){
            estado11=!estado11;
            cambiarImagenInicial(estado11,1);
            vibrar();
        }


        if(v==btbluetooth){
            estadoblootho=!estadoblootho;
            if(estadoblootho){
                btbluetooth.setImageResource(R.drawable.abc);
            }
            if(!estadoblootho){
                btbluetooth.setImageResource(R.drawable.abcoff);
            }
            vibrar();
        }
        if(v==btluces){
            estadoLuces=!estadoLuces;
            if(estadoLuces){
                btluces.setImageResource(R.drawable.lucesonn);
            }
            if(!estadoLuces){
                btluces.setImageResource(R.drawable.luces);
            }
            vibrar();
        }
        if(v==btvoz){
            vibrar();
        }
        if(v==btstop){
            seekBarvelocidad.setProgress(0);
            vibrar();
        }
        if(v==btrobot){
            popup(btrobot);
            vibrar();
        }
        if(v==btfreno){
            seekBarvelocidad.setProgress(0);
            vibrar();
        }

    }
    @Override
    public boolean onLongClick(View v) {
        if(v==btrecargar){
            vibrar();
        }
        if(v==btstop){
            seekBarvelocidad.setProgress(0);
            estadoStop=!estadoStop;
            cambiarImagenInicial(estadoStop,2);

        }
        if(v==btrobot){
            vibrar2();
            popuplargo(btrobot);
            mediaPlayer3.start();
        }
        return false;
    }
    private void cambiarImagenInicial(boolean estado,int numero){
        if(estado && numero==1){
            cambiarImagenIniciaOnn();
            mediaPlayer2.start();
        }
        if(!estado && numero==1){
            cambiarImagenIniciaOff();
        }
        if(estado==false && numero==2){
            cambiarImagenStopRojo();
        }
        if(estado==true && numero==2){
            cambiarImagenIniciaOnn();
        }
    }
    private void cambiarImagenStopRojo(){
        btfreno.setImageResource(R.drawable.frenorojo);
        btbluetooth.setImageResource(R.drawable.abcrojo);
        btluces.setImageResource(R.drawable.lucesrojo);
        btrobot.setImageResource(R.drawable.imagenrobotrojo);
    }

    private void cambiarImagenIniciaOnn(){
        btbluetooth.setImageResource(R.drawable.abc);
        btvoz.setImageResource(R.drawable.imagenvoz);
        btluces.setImageResource(R.drawable.lucesonn);
        btrobot.setImageResource(R.drawable.imagenrobotonn);
        btstart.setImageResource(R.drawable.start);
        btrecargar.setImageResource(R.drawable.recargar);
    }
    private void cambiarImagenIniciaOff(){
        btbluetooth.setImageResource(R.drawable.abcoff);
        btvoz.setImageResource(R.drawable.imagenvozoff);
        btluces.setImageResource(R.drawable.luces);
        btrobot.setImageResource(R.drawable.imagenrobot);
        btstart.setImageResource(R.drawable.startrojo);
        btrecargar.setImageResource(R.drawable.recargaroff);
    }

    public void vibrar()
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
        seekBarvelocidad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
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
                    mediaPlayer.start();
                    vibrar();
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


    private void startVoiceRecognition()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-ES"); // Fuerza el uso del español (España)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "es-ES"); // Preferencia de idioma español   (España)

        intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, "es-ES"); // Forzar el idioma
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Controle el Robot");
        try
        {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        }
        catch (ActivityNotFoundException e)
        {
            Toast.makeText(getContext(), "El reconocimiento de voz no es compatible con este dispositivo.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT)
        {
            if (resultCode == getActivity().RESULT_OK && data != null)
            {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (result != null && result.contains("adelante"))
                {
                    btrecargar.setImageResource(R.drawable.direccionarriba);
                    vibrar2();
                }
                if (result != null && result.contains("atrás"))
                {
                    btrecargar.setImageResource(R.drawable.direccionabajo);
                    vibrar2();
                }
                if(result != null && result.contains("izquierda")){
                    btrecargar.setImageResource(R.drawable.direccionizquierda);
                    vibrar2();
                }
                if(result != null && result.contains("derecha")){
                    btrecargar.setImageResource(R.drawable.direccionderecha);
                    vibrar2();
                }
            }
        }
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
}