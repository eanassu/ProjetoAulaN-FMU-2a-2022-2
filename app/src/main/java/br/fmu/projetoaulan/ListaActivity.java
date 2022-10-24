package br.fmu.projetoaulan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class ListaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        RecyclerView recyclerView =findViewById(R.id.recyclerView);
        AppDatabase db = AppDatabase.getInstance(this);
        FuncionarioDao dao = db.funcionarioDao();
        List<Funcionario> funcionarios = dao.getFuncionarios();
        recyclerView.setAdapter(new FuncionarioAdapter(funcionarios,this));
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,
                        false);
        recyclerView.setLayoutManager(layoutManager);
    }
}