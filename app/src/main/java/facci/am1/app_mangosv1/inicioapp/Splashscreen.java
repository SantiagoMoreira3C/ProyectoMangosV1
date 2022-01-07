package facci.am1.app_mangosv1.inicioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import facci.am1.app_mangosv1.MainActivity;
import facci.am1.app_mangosv1.R;
import facci.am1.app_mangosv1.Registro;

public class Splashscreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Splashscreen.this, Registro.class);
                startActivity(intent);
                finish();

            }
        };
         Timer tiempo = new Timer();
         tiempo.schedule(tarea, 5000);

    }
}