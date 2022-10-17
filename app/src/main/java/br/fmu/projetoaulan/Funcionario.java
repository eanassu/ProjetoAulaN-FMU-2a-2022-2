package br.fmu.projetoaulan;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Funcionario {
    @PrimaryKey
    private int re;
    private String nome;
    private Date dataAdmissao;
    private double salario;
    private String cargo;

    public Funcionario() {
    }

    public Funcionario(int re, String nome, Date dataAdmissao, double salario, String cargo) {
        this.re = re;
        this.nome = nome;
        this.dataAdmissao = dataAdmissao;
        this.salario = salario;
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "re=" + re +
                ", nome='" + nome + '\'' +
                ", dataAdmissao=" + dataAdmissao +
                ", salario=" + salario +
                ", cargo='" + cargo + '\'' +
                '}';
    }

    public int getRe() {
        return re;
    }

    public void setRe(int re) {
        this.re = re;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
