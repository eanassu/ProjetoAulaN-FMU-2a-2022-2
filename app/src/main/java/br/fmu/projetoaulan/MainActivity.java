package br.fmu.projetoaulan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirCalculadoraDeGorjeta(View view) {
        Intent intent = new Intent(this, TipCalculatorActivity.class);
        startActivity(intent);
    }

    public void abrirDesenho(View view) {
        Intent intent = new Intent(this, DesenhoActivity.class);
        startActivity(intent);
    }

    public void testarSensores(View view) {
        Intent intent = new Intent(this, SensorActivity.class);
        startActivity(intent);
    }

    public void abrirTesteBD(View view) {
        Intent intent = new Intent(this, TesteBDActivity.class);
        startActivity(intent);
    }

    public void abrirTesteTCP(View view) {
        Intent intent = new Intent(this, TesteTCPActivity.class);
        startActivity(intent);
    }
}