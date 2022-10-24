package br.fmu.projetoaulan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TesteBDActivity extends AppCompatActivity {
    private EditText editTextNome;
    private EditText editTextRe;
    private EditText editTextDataAdmissao;
    private EditText editTextSalario;
    private EditText editTextFuncao;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_bdactivity);
        editTextRe = findViewById(R.id.editTextRe);
        editTextNome = findViewById(R.id.editTextNome);
        editTextDataAdmissao = findViewById(R.id.editTextDataAdmissao);
        editTextSalario = findViewById(R.id.editTextSalario);
        editTextFuncao = findViewById(R.id.editTextFuncao);
    }
    public void cadastrar(View view ) {
        int re = Integer.parseInt(editTextRe.getText().toString());
        String nome = editTextNome.getText().toString();
        Date dataAdmissao;
        try {
            dataAdmissao = dateFormat.parse(editTextDataAdmissao.getText().toString());
        } catch (ParseException e) {
            dataAdmissao = new Date();
        }
        double salario = Double.parseDouble(editTextSalario.getText().toString());
        String funcao = editTextFuncao.getText().toString();
        AppDatabase db = AppDatabase.getInstance(this);
        FuncionarioDao dao = db.funcionarioDao();
        Funcionario func = new Funcionario(re,nome,dataAdmissao,salario,funcao);
        dao.insert(func);
    }
    public void buscar(View view) {
        int re = Integer.parseInt(editTextRe.getText().toString());
        AppDatabase db = AppDatabase.getInstance(this);
        FuncionarioDao dao = db.funcionarioDao();
        Funcionario funcionario = dao.buscarPorRe(re);
        editTextNome.setText(funcionario.getNome());
        editTextDataAdmissao.setText(dateFormat.format(funcionario.getDataAdmissao()));
        editTextSalario.setText(Double.toString(funcionario.getSalario()));
        editTextFuncao.setText(funcionario.getCargo());
    }
    public void excluir(View view) {
        int re = Integer.parseInt(editTextRe.getText().toString());
        AppDatabase db = AppDatabase.getInstance(this);
        FuncionarioDao dao = db.funcionarioDao();
        Funcionario funcionario = dao.buscarPorRe(re);
        dao.delete(funcionario);
        limpar();
    }
    public void alterar(View view) {
        int re = Integer.parseInt(editTextRe.getText().toString());
        String nome = editTextNome.getText().toString();
        Date dataAdmissao;
        try {
            dataAdmissao = dateFormat.parse(editTextDataAdmissao.getText().toString());
        } catch (ParseException e) {
            dataAdmissao = new Date();
        }
        double salario = Double.parseDouble(editTextSalario.getText().toString());
        String funcao = editTextFuncao.getText().toString();
        AppDatabase db = AppDatabase.getInstance(this);
        FuncionarioDao dao = db.funcionarioDao();
        Funcionario func = new Funcionario(re,nome,dataAdmissao,salario,funcao);
        dao.update(func);
    }
    private void limpar() {
        editTextRe.setText("");
        editTextNome.setText("");
        editTextDataAdmissao.setText("");
        editTextSalario.setText("");
        editTextFuncao.setText("");
    }
    public void listar(View view) {
        Intent intent = new Intent(this,ListaActivity.class);
        startActivity(intent);
    }
}