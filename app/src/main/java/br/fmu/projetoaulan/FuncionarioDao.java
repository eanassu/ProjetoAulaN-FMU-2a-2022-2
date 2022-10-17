package br.fmu.projetoaulan;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FuncionarioDao {
    @Query("SELECT * FROM Funcionario")
    List<Funcionario> getFuncionarios();
    @Query("SELECT * FROM Funcionario where re=:reFuncionario")
    Funcionario buscarPorRe(int reFuncionario);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Funcionario funcionario);
    @Delete
    void delete(Funcionario funcionario);
    @Update
    void update(Funcionario funcionario);
}
