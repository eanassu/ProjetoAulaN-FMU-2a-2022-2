package br.fmu.projetoaulan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TipCalculatorActivity extends AppCompatActivity {
    private EditText editTextValor;
    private TextView textViewValor;
    private SeekBar seekBar;
    private TextView textViewPct;
    private TextView textViewGorjeta;
    private TextView textViewTotal;
    private double valor;
    private double porcentagem;
    private double gorjeta;
    private double total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
    }
}