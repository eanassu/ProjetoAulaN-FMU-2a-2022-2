package br.fmu.projetoaulan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class TipCalculatorActivity extends AppCompatActivity {
    private EditText editTextValor;
    private TextView textViewValor;
    private SeekBar seekBar;
    private TextView textViewPct;
    private TextView textViewGorjeta;
    private TextView textViewTotal;
    private double valor = 0;
    private double porcentagem = 0;
    private double gorjeta = 0;
    private double total = 0;
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private NumberFormat percentFormat = NumberFormat.getPercentInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        editTextValor = findViewById(R.id.editTextValor);
        textViewValor = findViewById(R.id.textViewValor);
        seekBar = findViewById(R.id.seekBar);
        textViewPct = findViewById(R.id.textViewPct);
        textViewGorjeta = findViewById(R.id.textViewGorjeta);
        textViewTotal = findViewById(R.id.textViewTotal);
        editTextValor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int valorInt;
                try {
                    valorInt = Integer.parseInt(editTextValor.getText().toString());
                } catch( NumberFormatException e ) {
                    valorInt = 0;
                }
                valor = valorInt/100.0;
                recalcular();
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                porcentagem = i/100.0;
                recalcular();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        recalcular();
    }

    private void recalcular() {
        gorjeta = valor * porcentagem;
        total = valor + gorjeta;
        textViewValor.setText(currencyFormat.format(valor));
        textViewGorjeta.setText(currencyFormat.format(gorjeta));
        textViewTotal.setText(currencyFormat.format(total));
        textViewPct.setText(percentFormat.format(porcentagem));
    }
}