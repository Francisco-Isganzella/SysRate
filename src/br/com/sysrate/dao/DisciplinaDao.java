/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysrate.dao;

import br.com.sysrate.entidade.Disciplina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author migue
 */
public class DisciplinaDao {
    
    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;
    
    public void salvar(Disciplina disciplina) throws SQLException{
        String sql = "INSERT INTO Disciplina (disciplina, visivel) VALUES (?,?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setString(1, disciplina.getDisciplina());
            preparando.setBoolean(2, disciplina.getVisivel());
            preparando.executeUpdate();
            resultSet.next();
            disciplina.setDisciplinaID(resultSet.getInt(1));
            
            
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao salvar a disciplina:" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
}
