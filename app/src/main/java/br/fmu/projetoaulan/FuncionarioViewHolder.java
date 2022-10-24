package br.fmu.projetoaulan;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class FuncionarioViewHolder extends RecyclerView.ViewHolder {
    final TextView re;
    final TextView nome;
    final TextView dataAdmissao;
    final TextView salario;
    final TextView funcao;
    public FuncionarioViewHolder( View itemView ) {
        super(itemView);
        re = itemView.findViewById(R.id.item_re);
        nome = itemView.findViewById(R.id.item_nome);
        dataAdmissao = itemView.findViewById(R.id.item_dataAdm);
        salario = itemView.findViewById(R.id.item_salario);
        funcao = itemView.findViewById(R.id.item_funcao);
    }
}
